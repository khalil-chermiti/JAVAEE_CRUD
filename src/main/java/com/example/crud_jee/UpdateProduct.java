package com.example.crud_jee;

import com.example.crud_jee.DAO.ProductDAO;
import com.example.crud_jee.models.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "updateProduct", value = "/updateProduct")
public class UpdateProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // check user session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            request.setAttribute("error", "please login first");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        String productId = request.getParameter("id");
        String title = request.getParameter("title");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");

        if (productId == null || title.isBlank() || price.isBlank() || quantity.isBlank()) {
            request.setAttribute("error", "please provide product id, title, price, and quantity");
            request.getRequestDispatcher("getProducts").forward(request, response);
        }

        try {
            ProductDAO productDAO = new ProductDAO();

            // get product by id
            Product productToUpdate = productDAO.getProductById(Integer.parseInt(productId));

            // check if product is null

            if (productToUpdate == null) {
                request.setAttribute("error", "product not found");
                request.getRequestDispatcher("getProducts").forward(request, response);
                return;
            }

            // update product
            productToUpdate.setTitle(title);
            productToUpdate.setPrice(Double.parseDouble(price));
            productToUpdate.setQuantity(Integer.parseInt(quantity));

            productDAO.updateProduct(productToUpdate);

            // get all products and send them to the view
            request.setAttribute("success", "update product success");
            request.getRequestDispatcher("getProducts").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "update product failed");
            request.getRequestDispatcher("product.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // check user session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            request.setAttribute("error", "please login first");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        String productId = request.getParameter("id");

        if (productId == null) {
            request.setAttribute("error", "please provide product id");
            request.getRequestDispatcher("getProducts").forward(request, response);
        }

        // get product by id
        Product productToUpdate = new ProductDAO().getProductById(Integer.parseInt(productId));

        // check if product is null
        if (productToUpdate == null) {
            request.setAttribute("error", "product not found");
            request.getRequestDispatcher("getProducts").forward(request, response);
            return;
        }

        // navigate to update product page
        request.setAttribute("product", productToUpdate);
        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);

    }
}

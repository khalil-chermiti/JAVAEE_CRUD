package com.example.crud_jee;

import com.example.crud_jee.DAO.ProductDAO;
import com.example.crud_jee.models.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "addProduct", value = "/addProduct")
public class AddProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // check user session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            request.setAttribute("error", "please login first");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }


        String title = request.getParameter("title");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");

        if (title.isBlank() || price.isBlank() || quantity.isBlank()) {
            request.setAttribute("error", "please provide title, price, and quantity");
            request.getRequestDispatcher("product.jsp").forward(request, response);
        }

        try {
            Product product = new Product();
            product.setTitle(title);
            product.setPrice(Double.parseDouble(price));
            product.setQuantity(Integer.parseInt(quantity));

            new ProductDAO().addProduct(product);

            // get all products and send them to the view
            request.setAttribute("products", new ProductDAO().getProducts());
            request.setAttribute("success", "add product success");
            response.sendRedirect("getProducts");
//            request.getRequestDispatcher("product.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "add product failed");
            request.getRequestDispatcher("product.jsp").forward(request, response);
        }
    }

}

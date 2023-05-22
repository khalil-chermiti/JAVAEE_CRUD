package com.example.crud_jee;

import com.example.crud_jee.DAO.ProductDAO;
import com.example.crud_jee.models.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "deleteProduct", value = "/deleteProduct")
public class DeleteProduct extends HttpServlet {
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

        // check if product id is null
        if (productId == null) {
            request.setAttribute("error", "please provide product id");
            response.sendRedirect("getProducts");
            return;
        }

        ProductDAO productDAO = new ProductDAO();

        // get product by id
        Product productToDelete = productDAO.getProductById(Integer.parseInt(productId));

        // check if product is null
        if (productToDelete == null) {
            request.setAttribute("error", "product not found");
            response.sendRedirect("getProducts");
            return;
        }

        // delete product from database
        new ProductDAO().removeProduct(new ProductDAO().getProductById(Integer.parseInt(productId)));

        // get all products and send them to the view
        response.sendRedirect("getProducts");
    }
}

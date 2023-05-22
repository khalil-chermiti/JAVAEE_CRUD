package com.example.crud_jee;

import com.example.crud_jee.DAO.ProductDAO;
import com.example.crud_jee.models.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "getProducts", value = "/getProducts")
public class GetProducts extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // check user session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            request.setAttribute("error", "please login first");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        List<Product> products = new ProductDAO().getProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }
}

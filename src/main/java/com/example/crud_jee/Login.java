package com.example.crud_jee;

import com.example.crud_jee.models.Admin;
import com.example.crud_jee.utils.ConnectionUtil;
import jakarta.persistence.Query;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.isBlank() || password.isBlank()) {
            request.setAttribute("error", "please provide username and password");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        try (Session session = ConnectionUtil.getSessionAnnotationFactory().getCurrentSession()) {
            Transaction tx = session.beginTransaction();

            Query query = session.createQuery("from Admin where username = :username");

            query.setParameter("username", username);
            Admin admin = (Admin) query.getSingleResult();

            if (admin == null) {
                request.setAttribute("error", "can't find admin");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            if (!admin.getPassword().equals(password)) {
                request.setAttribute("error", "wrong password");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            // set user session
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", admin);

            request.setAttribute("success", "login success");
            request.getRequestDispatcher("getProducts").forward(request, response);

        } catch (HibernateException he) {
            request.setAttribute("error", "hibernate error");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}

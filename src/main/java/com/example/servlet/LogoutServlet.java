package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.System.out;


public class LogoutServlet extends HttpServlet {
    @Serial
    private static final long serialVersisonUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Invalidate the session
        HttpSession session = request.getSession();
        session.invalidate();


        // Display logout message
        out.println("<html><body>");
        out.println("<h1>Logout Successful</h1>");
        out.println("<p>You have been logged out.</p>");
        out.println("<a href=\"index.jsp\">Go to Home Page</a>");
        out.println("</body></html>");


//        // Redirect the user to the index page
//        response.sendRedirect("index.jsp");
    }
}

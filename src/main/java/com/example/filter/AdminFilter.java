package com.example.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AdminFilter implements Filter {
    private static final String ALLOWED_EMAIL = "jainmuskan9027@gmail.com";
    private static final String ALLOWED_PASSWORD = "5678";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String email = httpRequest.getParameter("Email");
        String password = httpRequest.getParameter("Password");

        if (email.equals(ALLOWED_EMAIL) && password.equals(ALLOWED_PASSWORD)) {
            // The provided email and password are correct, allow access to the AdminServlet
            chain.doFilter(request, response);
        } else {
            // The provided email and password are incorrect, redirect to the login page with an error message
            httpResponse.sendRedirect("adminLogin.jsp?error=authentication");
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}

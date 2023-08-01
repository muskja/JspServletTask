package com.example.servlet;

import com.example.model.Address;
import com.example.model.AddressDAO;
import com.example.model.User;
import com.example.model.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assuming you have a UserDAO and AddressDAO instances
        UserDAO userDAO = new UserDAO();
        AddressDAO addressDAO = new AddressDAO();

        // Retrieve the user ID from the request (assuming it's passed as a parameter in the URL)
        int userId = Integer.parseInt(request.getParameter("userId"));

        // Get the User object by ID from the UserDAO
        User user = userDAO.getUserById(userId);

        // Get the Address object for the user from the AddressDAO
        Address address = addressDAO.getAddressById(userId);

        // Set the user and address objects as attributes in the request scope
        request.setAttribute("user", user);
        request.setAttribute("address", address);

        // Forward to the edit.jsp page for displaying the editable form
        request.getRequestDispatcher("editUser.jsp").forward(request, response);
    }
}

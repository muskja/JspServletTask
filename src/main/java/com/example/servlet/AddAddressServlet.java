package com.example.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import com.example.util.DBUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.model.Address; // Make sure you import the correct Address class from your project
import java.sql.SQLException;


public class AddAddressServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addAddress".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String addressLine1 = request.getParameter("AddressLine1");
            String addressLine2 = request.getParameter("AddressLine2");
            String city = request.getParameter("City");
            String state = request.getParameter("State");
            String country = request.getParameter("Country");
            String pinCode = request.getParameter("PinCode");

            Address address = new Address();
//            address.setUserId(userId);
            address.setAddressLine1(addressLine1);
            address.setAddressLine2(addressLine2);
            address.setCity(city);
            address.setState(state);
            address.setCountry(country);
            address.setPinCode(pinCode);

            // Save the address to the database using the DBUtil class
            try {
                String query = "INSERT INTO addresses (address_line_1, address_line_2, city, state, country, pin_code) VALUES (?, ?, ?, ?, ?, ?)";
                DBUtil.getConnection();
            } catch (SQLException e) {
                // Handle any potential database errors here
                e.printStackTrace();
            }

            // Redirect the user back to the same page after adding the address
            response.sendRedirect(request.getContextPath() + "index.jsp");
        } else {
            // Handle other actions, such as updating an existing address or deleting an address
            // Implement similar database operations as above depending on the action
        }
    }
}

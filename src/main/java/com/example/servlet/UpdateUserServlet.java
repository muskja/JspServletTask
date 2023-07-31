package com.example.servlet;

import com.example.model.Address;
import com.example.model.User;
import com.example.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            response.sendRedirect("index.jsp"); // Redirect to the appropriate page if the action parameter is missing
            return;
        }

        switch (action) {
            case "updateUser":
                updateUserDetails(request, response);
                response.sendRedirect("success.jsp");
                break;
            case "updateAddress":
                updateAddressDetails(request, response);
                response.sendRedirect("index.jsp"); // Redirect back to the AdminPage.jsp after updating the address
                break;
            default:
                response.sendRedirect("error.jsp"); // Redirect to the appropriate page if the action is unknown
                break;
        }
    }

    private void updateUserDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // The same as before, no changes needed here
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String email = request.getParameter("Email");
        String phoneNumber = request.getParameter("PhoneNumber");
        String gender = request.getParameter("Gender");

        try (Connection connection = DBUtil.getConnection()) {
            String query = "UPDATE users SET FirstName=?, LastName=?, PhoneNumber=?, Gender=? WHERE Email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAddressDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user_Id = Integer.parseInt(request.getParameter("user_Id"));
        int addressId;
        String addressIdParam = request.getParameter("addressId");

        if ("new".equals(addressIdParam)) {
            // If the addressId parameter is "new", it means it's a new address to be inserted
            addressId = -1; // Use a negative value to indicate a new address (since the Id in the database is always positive)
        } else {
            // Otherwise, the addressId parameter contains the address's actual ID
            addressId = Integer.parseInt(addressIdParam);
        }

        String addressLine1 = request.getParameter("AddressLine1");
        String addressLine2 = request.getParameter("AddressLine2");
        String city = request.getParameter("City");
        String state = request.getParameter("State");
        String country = request.getParameter("Country");
        String pinCode = request.getParameter("PinCode");

        try (Connection connection = DBUtil.getConnection()) {
            String query;
            PreparedStatement preparedStatement;

            if (addressId < 0) {
                // Insert new address
                query = "INSERT INTO addresses (User_Id, AddressLine1, AddressLine2, City, State, Country, PinCode) VALUES (?, ?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, user_Id);
                preparedStatement.setString(2, addressLine1);
                preparedStatement.setString(3, addressLine2);
                preparedStatement.setString(4, city);
                preparedStatement.setString(5, state);
                preparedStatement.setString(6, country);
                preparedStatement.setString(7, pinCode);
            } else {
                // Update existing address
                query = "UPDATE addresses SET AddressLine1=?, AddressLine2=?, City=?, State=?, Country=?, PinCode=? WHERE Id=? AND User_Id=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, addressLine1);
                preparedStatement.setString(2, addressLine2);
                preparedStatement.setString(3, city);
                preparedStatement.setString(4, state);
                preparedStatement.setString(5, country);
                preparedStatement.setString(6, pinCode);
                preparedStatement.setInt(7, addressId);
                preparedStatement.setInt(8, user_Id);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

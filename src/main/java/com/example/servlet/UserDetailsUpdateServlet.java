package com.example.servlet;

import com.example.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDetailsUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            response.sendRedirect("index.jsp"); // Redirect to the appropriate page if the action parameter is missing
            return;
        }

        switch (action) {
            case "updateUser":
//                updateUserDetails(request, response);
//                updateAddressDetails(request, response);
                addAddress(request, response);
//                response.sendRedirect("success.jsp");
                break;

//            case "addAddress":
//                addAddress(request, response);
//                response.sendRedirect("index.jsp"); // Redirect to the user details page after adding the address
//                break;

            default:
                response.sendRedirect("index.jsp"); // Redirect to the appropriate page after updating
                break;
        }
    }

    private void updateUserDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        int user_Id = Integer.parseInt(request.getParameter("userId"));
        int addressCount = Integer.parseInt(request.getParameter("addressCount"));

        try (Connection connection = DBUtil.getConnection()) {
            String query = "UPDATE addresses SET AddressLine1=?, AddressLine2=?, City=?, State=?, Country=?, PinCode=? WHERE Id=? AND User_Id=?";
            for (int addressIndex = 0; addressIndex < addressCount; addressIndex++) {
                String addressLine1 = request.getParameter("AddressLine1" + addressIndex);
                String addressLine2 = request.getParameter("AddressLine2" + addressIndex);
                String city = request.getParameter("City" + addressIndex);
                String state = request.getParameter("State" + addressIndex);
                String country = request.getParameter("Country" + addressIndex);
                String pinCode = request.getParameter("PinCode" + addressIndex);
                String addressIdParam = request.getParameter("addressId" + addressIndex);

                // Check if addressIdParam is not null and not empty before parsing it as an integer
                int addressId = (addressIdParam != null && !addressIdParam.isEmpty()) ? Integer.parseInt(addressIdParam) : -1;

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, addressLine1);
                preparedStatement.setString(2, addressLine2);
                preparedStatement.setString(3, city);
                preparedStatement.setString(4, state);
                preparedStatement.setString(5, country);
                preparedStatement.setString(6, pinCode);
                preparedStatement.setInt(7, addressId);
                preparedStatement.setInt(8, user_Id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void addAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String addressLine1 = request.getParameter("AddressLine1");
        String addressLine2 = request.getParameter("AddressLine2");
        String city = request.getParameter("City");
        String state = request.getParameter("State");
        String country = request.getParameter("Country");
        String pinCode = request.getParameter("PinCode");

        // Server-side validation to ensure required fields are not null or empty
        if (addressLine1 == null || addressLine1.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Address Line 1 cannot be null or empty");
            return;
        }

        try (Connection connection = DBUtil.getConnection()) {
            String query = "INSERT INTO addresses (AddressLine1, AddressLine2, City, State, Country, PinCode, User_Id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, addressLine1);
            preparedStatement.setString(2, addressLine2);
            preparedStatement.setString(3, city);
            preparedStatement.setString(4, state);
            preparedStatement.setString(5, country);
            preparedStatement.setString(6, pinCode);
            preparedStatement.setInt(7, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}


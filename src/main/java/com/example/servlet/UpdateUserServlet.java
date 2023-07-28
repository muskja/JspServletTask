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
                updateAddressDetails(request, response);
                response.sendRedirect("success.jsp");
                break;
//            case "updateAddress":
//                updateAddressDetails(request, response);
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
        int user_Id = Integer.parseInt(request.getParameter("user_Id"));
        int addressId = Integer.parseInt(request.getParameter("addressId"));
        String AddressLine1 = request.getParameter("AddressLine1");
        String AddressLine2 = request.getParameter("AddressLine2");
        String City = request.getParameter("City");
        String State = request.getParameter("State");
        String Country = request.getParameter("Country");
        String PinCode = request.getParameter("PinCode");

        try (Connection connection = DBUtil.getConnection()) {
            String query = "UPDATE addresses SET AddressLine1=?, AddressLine2=?, City=?, State=?, Country=?, PinCode=? WHERE Id=? AND User_Id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, AddressLine1);
            preparedStatement.setString(2, AddressLine2);
            preparedStatement.setString(3, City);
            preparedStatement.setString(4, State);
            preparedStatement.setString(5, Country);
            preparedStatement.setString(6, PinCode);
            preparedStatement.setInt(7, addressId);
            preparedStatement.setInt(8, user_Id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



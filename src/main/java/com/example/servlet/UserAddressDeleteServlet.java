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

public class UserAddressDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdString = request.getParameter("user_Id");
        String addressIndexString = request.getParameter("addressIndex"); // Get the address index parameter

        if (userIdString == null || userIdString.isEmpty() || addressIndexString == null || addressIndexString.isEmpty()) {
            response.sendRedirect("index.jsp"); // Redirect to the appropriate page if either userId or addressIndex parameter is missing
            return;
        }

        int userId = Integer.parseInt(userIdString);
        int addressIndex = Integer.parseInt(addressIndexString); // Parse the address index parameter

        try (Connection connection = DBUtil.getConnection()) {
            // Use the addressIndex to construct the name of the addressId parameter
            String addressIdParamName = "addressId" + addressIndex;
            int addressId = Integer.parseInt(request.getParameter(addressIdParamName));

            String query = "DELETE FROM addresses WHERE Id=? AND User_Id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, addressId);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("index.jsp"); // Redirect to the appropriate page after deleting the address
    }
}

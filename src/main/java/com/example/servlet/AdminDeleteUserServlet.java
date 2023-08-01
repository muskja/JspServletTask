package com.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminDeleteUserServlet extends HttpServlet {
    // Update these variables with your MySQL database details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/prac";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Muskan@12";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userId = request.getParameter("userId");

            // Perform database deletion here using userId to delete user and their addresses
            deleteUserAndAddresses(userId);

            // Redirect back to the original page or any other appropriate page.
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any errors that may occur during the deletion process
            // You can redirect to an error page or display an error message to the user.
            response.sendRedirect("error.jsp");
        }
    }

    private void deleteUserAndAddresses(String userId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Start a transaction
            connection.setAutoCommit(false);

            // Delete addresses associated with the user
            String deleteAddressesQuery = "DELETE FROM addresses WHERE user_Id = ?";
            statement = connection.prepareStatement(deleteAddressesQuery);
            statement.setString(1, userId);
            statement.executeUpdate();
            statement.close();

            // Delete the user
            String deleteUserQuery = "DELETE FROM users WHERE Id = ?";
            statement = connection.prepareStatement(deleteUserQuery);
            statement.setString(1, userId);
            statement.executeUpdate();

            // Commit the transaction if both deletions are successful
            connection.commit();
        } catch (SQLException e) {
            // Rollback the transaction if an exception occurs
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            // Restore auto-commit to its default state
            if (connection != null) {
                connection.setAutoCommit(true);
            }

            // Close resources
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}

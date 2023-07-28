package com.example.servlet;

import java.io.IOException;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the updated values from the request parameters
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNumber = request.getParameter("phoneNumber");
        String gender = request.getParameter("gender");
        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String pinCode = request.getParameter("pinCode");

        // Update the user details in the database
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            String url = "jdbc:mysql://localhost:3306/prac";
            String username = "root";
            String password = "Muskan@12";
            connection = DriverManager.getConnection(url, username, password);

            // Prepare SQL statement to update user details
            String sql = "UPDATE user SET FirstName=?, LastName=?, PhoneNumber=?, Gender=?, AddressLine1=?, AddressLine2=?, City=?, State=?, Country=?, PinCode=? WHERE Email=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phoneNumber);
            statement.setString(4, gender);
            statement.setString(5, addressLine1);
            statement.setString(6, addressLine2);
            statement.setString(7, city);
            statement.setString(8, state);
            statement.setString(9, country);
            statement.setString(10, pinCode);
            statement.setString(11, email);

            // Execute the update query
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                // User details updated successfully
                response.sendRedirect("details.jsp"); // Redirect to the updated user details page
            } else {
                // Failed to update user details
                response.getWriter().println("Failed to update user details.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred while updating user details.");
        } finally {
            // Close database resources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

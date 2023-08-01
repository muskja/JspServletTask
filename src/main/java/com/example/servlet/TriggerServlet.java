package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class TriggerServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/prac";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Muskan@12";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            // 1. Delete the addresses associated with the user
            String deleteAddressesQuery = "DELETE FROM addresses WHERE user_Id = " + userId;
            statement.executeUpdate(deleteAddressesQuery);

            // 2. Delete the user from the users table
            String deleteUserQuery = "DELETE FROM users WHERE Id = " + userId;
            statement.executeUpdate(deleteUserQuery);

            // 3. Redirect to the same page or any other page as needed
            response.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
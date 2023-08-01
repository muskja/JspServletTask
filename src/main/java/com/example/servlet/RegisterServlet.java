package com.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data for user details
        String FirstName = request.getParameter("FirstName");
        String LastName = request.getParameter("LastName");
        String Email = request.getParameter("Email");
        String Password = request.getParameter("Password");
        String PhoneNumber = request.getParameter("PhoneNumber");
        String Gender = request.getParameter("Gender");

        // Retrieve other input fields (LastName, Email, Password, PhoneNumber, Gender) here

        // Retrieve address fields dynamically (if added)
        String[] AddressLine1 = request.getParameterValues("AddressLine1");
        String[] AddressLine2 = request.getParameterValues("AddressLine2");
        String[] City = request.getParameterValues("City");
        String[] State = request.getParameterValues("State");
        String[] Country = request.getParameterValues("Country");
        String[] PinCode = request.getParameterValues("PinCode");
        // Retrieve other address fields (City, State, Country, PinCode) here

        Connection conn = null;
        PreparedStatement userStmt = null;
        PreparedStatement addressStmt = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prac", "root", "Muskan@12");

            // Insert user data into the users table and request generated keys
            String userSql = "INSERT INTO users (FirstName, LastName, Email, Password, PhoneNumber, Gender) VALUES (?, ?, ?, ?, ?, ?)";
            userStmt = conn.prepareStatement(userSql, PreparedStatement.RETURN_GENERATED_KEYS); // Request generated keys
            userStmt.setString(1, FirstName);
            userStmt.setString(2, LastName);
            userStmt.setString(3, Email);
            userStmt.setString(4, Password);
            userStmt.setString(5, PhoneNumber);
            userStmt.setString(6, Gender);


            // Set other user data in the prepared statement here
            userStmt.executeUpdate();

            // Get the generated UserId for the address table foreign key
            ResultSet generatedKeys = userStmt.getGeneratedKeys();
            int user_Id = 0;
            if (generatedKeys.next()) {
                user_Id = generatedKeys.getInt(1);
            }

            // Insert address data into the addresses table
            String addressSql = "INSERT INTO addresses (user_Id, AddressLine1, AddressLine2, City, State, Country, PinCode) VALUES (?, ?, ?, ?, ?, ?, ?)";
            addressStmt = conn.prepareStatement(addressSql);
            for (int i = 0; i < AddressLine1.length; i++) {
                addressStmt.setInt(1, user_Id);
                addressStmt.setString(2, AddressLine1[i]);
                addressStmt.setString(3, AddressLine2[i]);
                addressStmt.setString(4, City[i]);
                addressStmt.setString(5, State[i]);
                addressStmt.setString(6, Country[i]);
                addressStmt.setString(7, PinCode[i]);

                // Set other address data in the prepared statement here
                addressStmt.executeUpdate();
            }

            // Redirect to success page
            response.sendRedirect("success.jsp");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close database resources
            try {
                if (userStmt != null)
                    userStmt.close();
                if (addressStmt != null)
                    addressStmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

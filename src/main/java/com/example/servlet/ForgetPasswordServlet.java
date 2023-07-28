// ForgetPasswordServlet.java
package com.example.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgetPasswordServlet extends HttpServlet {
    // Adjust these database connection details accordingly
    private final String DB_URL = "jdbc:mysql://localhost:3306/prac";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "Muskan@12";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Email = request.getParameter("Email");
        String Password = getPasswordFromDatabase(Email);

        if (Password != null) {
            // You may want to implement sending an email to the user with the password here
            // For security, consider sending a password reset link instead of the actual password
            // This example shows how to display the password on the page directly (not recommended)
            response.getWriter().println("Your password is: " + Password);
        } else {
            response.getWriter().println("User not found with the provided email.");
        }
    }

    private String getPasswordFromDatabase(String Email) {
        String Password = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "SELECT Password FROM users WHERE Email=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Password = rs.getString("Password");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return Password;
    }
}

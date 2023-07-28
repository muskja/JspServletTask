package com.example.servlet;

import com.example.model.Address;
import com.example.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private int userId;

    private String FirstName;
    private String LastName;
    private String Email;
    private String PhoneNumber;
    private String Gender;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Email = request.getParameter("Email");
        String Password = request.getParameter("Password");

        // Authenticate the user using email and password
        User user = authenticateUser(Email, Password);

        if (user != null) {
            // Fetch addresses associated with the user
            List<Address> addresses = fetchAddresses(user.getId());

            // Set the addresses to the user model object
            user.setAddresses(addresses);

            // Set the user as an attribute in the request
            request.setAttribute("user", user);

            // Forward the request to a JSP page for displaying the user and addresses
            request.getRequestDispatcher("details.jsp").forward(request, response);
        } else {
            // User authentication failed, handle the appropriate error response
            response.sendRedirect("login.jsp?error=authentication");
        }
    }

    private User authenticateUser(String Email, String Password) {
        User user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prac", "root", "Muskan@12");
            String sql = "SELECT * FROM users WHERE Email = ? AND Password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Email);
            stmt.setString(2, Password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("Id"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setPhoneNumber(rs.getString("PhoneNumber"));
                user.setGender(rs.getString("Gender"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close database resources
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    private List<Address> fetchAddresses(int userId) {
        List<Address> addresses = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prac", "root", "Muskan@12");
            String sql = "SELECT * FROM addresses WHERE user_Id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getInt("Id"));
                address.setAddressLine1(rs.getString("AddressLine1"));
                address.setAddressLine2(rs.getString("AddressLine2"));
                address.setCity(rs.getString("City"));
                address.setState(rs.getString("State"));
                address.setCountry(rs.getString("Country"));
                address.setPinCode(rs.getString("PinCode"));
                addresses.add(address);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close database resources
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return addresses;
    }
}

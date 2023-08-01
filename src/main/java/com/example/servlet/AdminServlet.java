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

public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch all users from the database
        List<User> users = fetchAllUsers();

        // Fetch addresses for all users
        for (User user : users) {
            List<Address> addresses = fetchAddresses(user.getId());
            user.setAddresses(addresses);
        }

        // Set the list of users as an attribute in the request
        request.setAttribute("users", users);

        // Forward the request to the Admin Details JSP page
        request.getRequestDispatcher("AdminPage.jsp").forward(request, response);
    }

    private List<User> fetchAllUsers() {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prac", "root", "Muskan@12");
            String sql = "SELECT * FROM users";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("Id"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setPhoneNumber(rs.getString("PhoneNumber"));
                user.setGender(rs.getString("Gender"));
                users.add(user);
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

        return users;
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

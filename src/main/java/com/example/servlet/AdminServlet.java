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


























//package com.example.servlet;
//
//import com.example.model.Address;
//import com.example.model.User;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//public class AdminServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String Email = request.getParameter("Email");
//        String Password = request.getParameter("Password");
//
//        // Authenticate the user using email and password
//        User user = authenticateUser(Email, Password);
//
//        if (user != null) {
//            // Fetch addresses associated with the user
//            List<Address> addresses = fetchAddresses(user.getId());
//
//            // Set the addresses to the user model object
//            user.setAddresses(addresses);
//
//            // Set the user as an attribute in the request
//            request.setAttribute("user", user);
//
//            // Forward the request to a JSP page for displaying the user and addresses
//            request.getRequestDispatcher("AdminPage.jsp").forward(request, response);
//        } else {
//            // User authentication failed, handle the appropriate error response
//            response.sendRedirect("login.jsp?error=authentication");
//        }
//    }
//
//
//
//    private User authenticateUser(String Email, String Password) {
//        User user = null;
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prac", "root", "Muskan@12");
//            String sql = "SELECT * FROM users WHERE Email = ? AND Password = ?";
//            stmt = conn.prepareStatement(sql);
//            stmt.setString(1, Email);
//            stmt.setString(2, Password);
//            rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                user = new User();
//                user.setId(rs.getInt("Id"));
//                user.setFirstName(rs.getString("FirstName"));
//                user.setLastName(rs.getString("LastName"));
//                user.setEmail(rs.getString("Email"));
//                user.setPassword(rs.getString("Password"));
//                user.setPhoneNumber(rs.getString("PhoneNumber"));
//                user.setGender(rs.getString("Gender"));
//
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // Close database resources
//            try {
//                if (rs != null)
//                    rs.close();
//                if (stmt != null)
//                    stmt.close();
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return user;
//    }
//
//    private List<Address> fetchAddresses(int userId) {
//        List<Address> addresses = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prac", "root", "Muskan@12");
//            String sql = "SELECT * FROM addresses WHERE user_Id = ?";
//            stmt = conn.prepareStatement(sql);
//            stmt.setInt(1, userId);
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                Address address = new Address();
//                address.setId(rs.getInt("Id"));
//                address.setAddressLine1(rs.getString("AddressLine1"));
//                address.setAddressLine2(rs.getString("AddressLine2"));
//                address.setCity(rs.getString("City"));
//                address.setState(rs.getString("State"));
//                address.setCountry(rs.getString("Country"));
//                address.setPinCode(rs.getString("PinCode"));
//                addresses.add(address);
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // Close database resources
//            try {
//                if (rs != null)
//                    rs.close();
//                if (stmt != null)
//                    stmt.close();
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return addresses;
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////package com.example.servlet;
////
////import java.io.IOException;
////import javax.servlet.ServletException;
////import javax.servlet.http.HttpServlet;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////import javax.servlet.http.HttpSession;
////
////public class AdminServlet extends HttpServlet {
////    private static final long serialVersionUID = 1L;
////
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        // Get the email and password from the login form
////        String Email = request.getParameter("Email");
////        String Password = request.getParameter("Password");
////
////        // TODO: You should perform validation, sanitation, and hashing of the password here
////
////        // Check if the provided credentials match the admin's credentials in the database
////        boolean isAdmin = checkAdminCredentials(Email, Password);
////
////        if (isAdmin) {
////            // If login is successful, store the information that the user is logged in as an admin in the session
////            HttpSession session = request.getSession();
////            session.setAttribute("isAdmin", true);
////            response.sendRedirect(request.getContextPath() + "/AdminServlet");
////        } else {
////            // If login fails, redirect back to the login page with an error message
////            response.sendRedirect(request.getContextPath() + "/index.jsp?loginError=true");
////        }
////    }
////
////    private boolean checkAdminCredentials(String Email, String Password) {
////        // Check if the email is not null before calling equals()
////        if (Email != null) {
////            // TODO: Implement the logic to check the provided credentials against the database
////            // You should use your DAO classes to interact with the database and validate the admin's credentials
////            // For simplicity, we will use dummy credentials for demonstration purposes only
////
////            // Replace these dummy credentials with your actual logic to check against the database
////            String adminEmail = "jainmuskan9027@gmail.com";
////            String adminPassword = "5678";
////
////            return Email.equals(adminEmail) && Password.equals(adminPassword);
////        }
////        return false; // Return false if email is null
////    }
////}

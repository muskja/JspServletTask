package com.example.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/prac";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "Muskan@12";

    // CRUD operations

    // Create a new user
    public boolean addUser(User user) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (FirstName, LastName, Email, Password, PhoneNumber, Gender) VALUES (?, ?, ?, ?, ?, ?)")) {

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getPhoneNumber());
            pstmt.setString(6, user.getGender());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read user by ID
    public User getUserById(int userId) {
        User user = null;
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE Id = ?")) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int Id = rs.getInt("Id");
                    String FirstName = rs.getString("FirstName");
                    String LastName = rs.getString("LastName");
                    String Email = rs.getString("Email");
                    String Password = rs.getString("Password");
                    String PhoneNumber = rs.getString("PhoneNumber");
                    String Gender = rs.getString("Gender");

                    user = new User(Id, FirstName, LastName, Email, Password, PhoneNumber, Gender);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Update an existing user
    public boolean updateUser(User user) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET FirstName=?, LastName=?, Email=?, Password=?, PhoneNumber=?, Gender=? WHERE Id=?")) {

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getPhoneNumber());
            pstmt.setString(6, user.getGender());
            pstmt.setInt(7, user.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a user
    public boolean deleteUser(int userId) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE Id=?")) {

            pstmt.setInt(1, userId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all users
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

            while (rs.next()) {
                int Id = rs.getInt("Id");
                String FirstName = rs.getString("FirstName");
                String LastName = rs.getString("LastName");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String PhoneNumber = rs.getString("PhoneNumber");
                String Gender = rs.getString("Gender");

                User user = new User(Id, FirstName, LastName, Email, Password, PhoneNumber, Gender);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}























//package com.example.model;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDAO {
//    private final String jdbcUrl = "jdbc:mysql://localhost:3306/prac";
//    private final String jdbcUsername = "root";
//    private final String jdbcPassword = "Muskan@12";
//    private int userId;
//
//    private String FirstName;
//    private String LastName;
//    private String Email;
//    private String PhoneNumber;
//    private String Gender;
//
//
//
//
//    // CRUD operations
//
//    // Create a new user
//    public boolean addUser(User user) {
//        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
//             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (FirstName, LastName, Email, Password, PhoneNumber, Gender) VALUES (?, ?, ?, ?, ?, ?)")) {
//
//            pstmt.setString(1, user.getFirstName());
//            pstmt.setString(2, user.getLastName());
//            pstmt.setString(3, user.getEmail());
//            pstmt.setString(4, user.getPassword());
//            pstmt.setString(5, user.getPhoneNumber());
//            pstmt.setString(6, user.getGender());
//
//            int rowsAffected = pstmt.executeUpdate();
//            return rowsAffected > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // Read user by ID
//    public User getUserById(int userId) {
//        User user = null;
//        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
//             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE Id = ?")) {
//
//            pstmt.setInt(1, userId);
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    user = new User(userId, FirstName, LastName, Email, PhoneNumber, Gender);
//                    user.setId(rs.getInt("Id"));
//                    user.setFirstName(rs.getString("FirstName"));
//                    user.setLastName(rs.getString("LastName"));
//                    user.setEmail(rs.getString("Email"));
//                    user.setPassword(rs.getString("Password"));
//                    user.setPhoneNumber(rs.getString("PhoneNumber"));
//                    user.setGender(rs.getString("Gender"));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }
//
//    // Update an existing user
//    public boolean updateUser(User user) {
//        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
//             PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET FirstName=?, LastName=?, Email=?, Password=?, PhoneNumber=?, Gender=? WHERE Id=?")) {
//
//            pstmt.setString(1, user.getFirstName());
//            pstmt.setString(2, user.getLastName());
//            pstmt.setString(3, user.getEmail());
//            pstmt.setString(4, user.getPassword());
//            pstmt.setString(5, user.getPhoneNumber());
//            pstmt.setString(6, user.getGender());
//            pstmt.setInt(7, user.getId());
//
//            int rowsAffected = pstmt.executeUpdate();
//            return rowsAffected > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    // Delete a user
//    public boolean deleteUser(int userId) {
//        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
//             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE Id=?")) {
//
//            pstmt.setInt(1, userId);
//            int rowsAffected = pstmt.executeUpdate();
//            return rowsAffected > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//
//    // Get all users
//    public List<User> getAllUsers() {
//        List<User> userList = new ArrayList<>();
//        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {
//
//            while (rs.next()) {
//                User user = new User(userId, FirstName, LastName, Email, PhoneNumber, Gender);
//                user.setId(rs.getInt("Id"));
//                user.setFirstName(rs.getString("FirstName"));
//                user.setLastName(rs.getString("LastName"));
//                user.setEmail(rs.getString("Email"));
//                user.setPassword(rs.getString("Password"));
//                user.setPhoneNumber(rs.getString("PhoneNumber"));
//                user.setGender(rs.getString("Gender"));
//                userList.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return userList;
//    }
//}

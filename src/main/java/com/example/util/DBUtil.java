package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/prac";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Muskan@12";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}







































//package com.example.util;
//
//import com.example.model.User;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DBUtil {
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/prac";
//    private static final String DB_USER = "root";
//    private static final String DB_PASSWORD = "Muskan@12";
//
//
//
//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static List<User> getAllUsers() {
//        List<User> userList = new ArrayList<>();
//
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
//        ) {
//            while (resultSet.next()) {
//                User user = new User(userId, FirstName, LastName, Email, PhoneNumber, Gender);
//                user.setFirstName(resultSet.getString("FirstName"));
//                user.setLastName(resultSet.getString("LastName"));
//                user.setEmail(resultSet.getString("Email"));
//                user.setPhoneNumber(resultSet.getString("PhoneNumber"));
//                user.setGender(resultSet.getString("Gender"));
////                user.setAddressLine1(resultSet.getString("AddressLine1"));
////                user.setAddressLine2(resultSet.getString("AddressLine2"));
////                user.setCity(resultSet.getString("City"));
////                user.setState(resultSet.getString("State"));
////                user.setCountry(resultSet.getString("Country"));
////                user.setPinCode(resultSet.getString("PinCode"));
//
//                userList.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return userList;
//    }
//
//    public static User getUserByEmail(String Email) {
//        User user = null;
//
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//             PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE Email = ?");
//        ) {
//            statement.setString(1, Email);
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                user = new User(userId, FirstName, LastName, Email, PhoneNumber, Gender);
//                user.setFirstName(resultSet.getString("FirstName"));
//                user.setLastName(resultSet.getString("LastName"));
//                user.setEmail(resultSet.getString("Email"));
//                user.setPhoneNumber(resultSet.getString("PhoneNumber"));
//                user.setGender(resultSet.getString("Gender"));
////                user.setAddressLine1(resultSet.getString("AddressLine1"));
////                user.setAddressLine2(resultSet.getString("AddressLine2"));
////                user.setCity(resultSet.getString("City"));
////                user.setState(resultSet.getString("State"));
////                user.setCountry(resultSet.getString("Country"));
////                user.setPinCode(resultSet.getString("PinCode"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return user;
//    }
//
//    public static boolean updateUser(User user) {
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//             PreparedStatement statement = connection.prepareStatement("UPDATE user SET FirstName = ?, LastName = ?, PhoneNumber = ?, Gender = ?, AddressLine1 = ?, AddressLine2 = ?, City = ?, State = ?, Country = ?, PinCode = ? WHERE Email = ?");
//        ) {
//            statement.setString(1, user.getFirstName());
//            statement.setString(2, user.getLastName());
//            statement.setString(3, user.getPhoneNumber());
//            statement.setString(4, user.getGender());
////            statement.setString(5, user.getAddressLine1());
////            statement.setString(6, user.getAddressLine2());
////            statement.setString(7, user.getCity());
////            statement.setString(8, user.getState());
////            statement.setString(9, user.getCountry());
////            statement.setString(10, user.getPinCode());
//            statement.setString(11, user.getEmail());
//
//            int rowsUpdated = statement.executeUpdate();
//
//            return rowsUpdated > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//
//    public static void deleteUserByEmail(String Email) {
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//             PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE Email = ?")) {
//            statement.setString(1, Email);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}

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



































//package com.example.servlet;
//
//import java.io.IOException;
//import java.sql.*;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

//public class RegisterServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Retrieve form data
//        String FirstName = request.getParameter("FirstName");
//        String LastName = request.getParameter("LastName");
//        String Email = request.getParameter("Email");
//        String Password = request.getParameter("Password");
//        String PhoneNumber = request.getParameter("PhoneNumber");
//        String Gender = request.getParameter("Gender");
//
//        // Retrieve address fields dynamically (if added)
//        String[] AddressLine1 = request.getParameterValues("AddressLine1");
//        String[] AddressLine2 = request.getParameterValues("AddressLine2");
//        String[] City = request.getParameterValues("City");
//        String[] State = request.getParameterValues("State");
//        String[] Country = request.getParameterValues("Country");
//        String[] PinCode = request.getParameterValues("PinCode");
//
//        Connection conn = null;
//        PreparedStatement stmt = null;
//
//        try {
//            // Load the MySQL JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Establish database connection
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prac", "root", "Muskan@12");
//
//            // Insert data into the table
//            String sql = "INSERT INTO user (FirstName, LastName, Email, Password, PhoneNumber, Gender, AddressLine1, AddressLine2, City, State, Country, PinCode) " +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//            stmt = conn.prepareStatement(sql);
//
//            // Set values for the common fields
//            stmt.setString(1, FirstName);
//            stmt.setString(2, LastName);
//            stmt.setString(3, Email);
//            stmt.setString(4, Password);
//            stmt.setString(5, PhoneNumber);
//            stmt.setString(6, Gender);
//
//            // Check if address fields are not null and have values
//            if (AddressLine1 != null && AddressLine2 != null && City != null && State != null && Country != null && PinCode != null) {
//                // Iterate over the address fields and insert each address
//                for (int i = 0; i < AddressLine1.length; i++) {
//                    stmt.setString(7, AddressLine1[i]);
//                    stmt.setString(8, AddressLine2[i]);
//                    stmt.setString(9, City[i]);
//                    stmt.setString(10, State[i]);
//                    stmt.setString(11, Country[i]);
//                    stmt.setString(12, PinCode[i]);
//                    stmt.executeUpdate();
//                }
//            }
//
//            // Redirect to success page
//            response.sendRedirect("success.jsp");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // Close database resources
//            try {
//                if (stmt != null)
//                    stmt.close();
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}





























//package com.example.servlet;
//
//import java.io.IOException;
//        import java.sql.Connection;
//        import java.sql.DriverManager;
//        import java.sql.PreparedStatement;
//        import java.sql.SQLException;
//        import java.util.ArrayList;
//        import java.util.List;
//
//        import javax.servlet.ServletException;
//        import javax.servlet.annotation.WebServlet;
//        import javax.servlet.http.HttpServlet;
//        import javax.servlet.http.HttpServletRequest;
//        import javax.servlet.http.HttpServletResponse;
//
//public class RegisterServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Retrieve form data
//        String FirstName = request.getParameter("FirstName");
//        String LastName = request.getParameter("LastName");
//        String Email = request.getParameter("Email");
//        String Password = request.getParameter("Password");
//        String PhoneNumber = request.getParameter("PhoneNumber");
//        String Gender = request.getParameter("Gender");
//
//        // Retrieve address fields dynamically (if added)
//        String[] AddressLine1 = request.getParameterValues("AddressLine1");
//        String[] AddressLine2 = request.getParameterValues("AddressLine2");
//        String[] City = request.getParameterValues("City");
//        String[] State = request.getParameterValues("State");
//        String[] Country = request.getParameterValues("Country");
//        String[] PinCode = request.getParameterValues("PinCode");
//
//        Connection conn = null;
//        PreparedStatement stmt = null;
//
//        try {
//            // Load the MySQL JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Establish database connection
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prac", "root", "Muskan@12");
//
//            // Insert data into the table
//            String sql = "INSERT INTO users (FirstName, LastName, Email, Password, PhoneNumber, Gender, AddressLine1,AddressLine2,City, State,Country,PinCode) " +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//            stmt = conn.prepareStatement(sql);
//
//            // Set values for the common fields
//            stmt.setString(1, FirstName);
//            stmt.setString(2, LastName);
//            stmt.setString(3, Email);
//            stmt.setString(4, Password);
//            stmt.setString(5, PhoneNumber);
//            stmt.setString(6, Gender);
//
//            // Check if address fields are not null and have values
//            if (AddressLine1 != null && AddressLine2 != null && City != null && State != null && Country != null && PinCode != null) {
//                // Iterate over the address fields and insert each address
//                for (int i = 0; i < AddressLine1.length; i++) {
//                    stmt.setString(7, AddressLine1[i]);
//                    stmt.setString(8, AddressLine2[i]);
//                    stmt.setString(9, City[i]);
//                    stmt.setString(10, State[i]);
//                    stmt.setString(11, Country[i]);
//                    stmt.setString(12, PinCode[i]);
//                    stmt.executeUpdate();
//                }
//            }
//
//            // Redirect to success page
//            response.sendRedirect("success.jsp");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // Close database resources
//            try {
//                if (stmt != null)
//                    stmt.close();
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}


//package com.example.servlet;
//
////import com.example.model.User;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import static java.lang.System.out;
//
//public class RegisterServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Retrieve user input from the request
//        String FirstName = request.getParameter("FirstName");
//        String LastName = request.getParameter("LastName");
//        String Email = request.getParameter("Email");
//        String Password = request.getParameter("Password");
//        String PhoneNumber = request.getParameter("PhoneNumber");
//        String Gender = request.getParameter("Gender");
//        String AddressLine1 = request.getParameter("AddressLine1");
//        String AddressLine2 = request.getParameter("AddressLine2");
//        String City = request.getParameter("City");
//        String State = request.getParameter("State");
//        String Country = request.getParameter("Country");
//        String PinCode = request.getParameter("PinCode");
//
//        out.println(FirstName);
//
////        User user = new User();
////        user.setFirstName(FirstName);
////        user.setFirstName(LastName);
////        user.setEmail(Email);
////        user.setPassword(Password);
////        user.setPhoneNumber(PhoneNumber);
////        user.setGender(Gender);
////        user.setAddressLine1(AddressLine1);
////        user.setAddressLine2(AddressLine2);
////        user.setCity(City);
////        user.setState(State);
////        user.setCountry(Country);
////        user.setPinCode(PinCode);
//
//
//        String url="jdbc:mysql://localhost:3306/prac";
//        String username="root";
//        String password="Muskan@12";
//
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            Connection connection = DriverManager.getConnection(url, username, password);
//
//
//            String sql = "insert into user (FirstName, LastName, Email, Password, PhoneNumber,  Gender, AddressLine1, AddressLine2, City, State, Country, PinCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
//            PreparedStatement statement = connection.prepareStatement(sql);
//
////            statement.setString(1, user.getFirstName());
////            statement.setString(2, user.getLastName());
////            statement.setString(3, user.getEmail());
////            statement.setString(4, user.getPassword());
////            statement.setString(5, user.getPhoneNumber());
////            statement.setString(6, user.getGender());
////            statement.setString(7, user.getAddressLine1());
////            statement.setString(8, user.getAddressLine2());
////            statement.setString(9, user.getCity());
////            statement.setString(10, user.getState());
////            statement.setString(11, user.getCountry());
////            statement.setString(12, user.getPinCode());
//
//            statement.setString(1, FirstName);
//            statement.setString(2, LastName);
//            statement.setString(3, Email);
//            statement.setString(4, Password);
//            statement.setString(5, PhoneNumber);
//            statement.setString(6, Gender);
//            statement.setString(7, AddressLine1);
//            statement.setString(8, AddressLine2);
//            statement.setString(9, City);
//            statement.setString(10, State);
//            statement.setString(11, Country);
//            statement.setString(12, PinCode);
//
//            statement.executeUpdate();
//            statement.close();
//
//
//            connection.close();
//
//            PrintWriter out = response.getWriter();
//            out.println("<html><body><b>Your registration is successful. Thank you!"
//                    + "</b></body></html>");
//
//
//
//
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }
//}
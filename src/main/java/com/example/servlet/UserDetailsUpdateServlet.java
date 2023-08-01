package com.example.servlet;

import com.example.model.Address;
import com.example.util.DBUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDetailsUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            response.sendRedirect("index.jsp"); // Redirect to the appropriate page if the action parameter is missing
            return;
        }

        switch (action) {
            case "updateUser":
                updateUserDetails(request, response);
                updateAddressDetails(request, response);
                addNewAddress(request, response);
                response.sendRedirect("index.jsp");
                break;

            case "addAddress": // Handle adding new address
                addNewAddress(request, response);
                response.sendRedirect("index.jsp"); // Redirect to the user details page after adding the address
                break;

            default:
                response.sendRedirect("index.jsp"); // Redirect to the appropriate page after updating
                break;
        }
    }


    private void updateUserDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String email = request.getParameter("Email");
        String phoneNumber = request.getParameter("PhoneNumber");
        String gender = request.getParameter("Gender");

        try (Connection connection = DBUtil.getConnection()) {
            String query = "UPDATE users SET FirstName=?, LastName=?, PhoneNumber=?, Gender=? WHERE Email=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAddressDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user_Id = Integer.parseInt(request.getParameter("user_Id"));
        int addressCount = Integer.parseInt(request.getParameter("addressCount"));

        try (Connection connection = DBUtil.getConnection()) {
            String query = "UPDATE addresses SET AddressLine1=?, AddressLine2=?, City=?, State=?, Country=?, PinCode=? WHERE Id=? AND User_Id=?";
            for (int addressIndex = 0; addressIndex < addressCount; addressIndex++) {
                String addressLine1 = request.getParameter("AddressLine1" + addressIndex);
                String addressLine2 = request.getParameter("AddressLine2" + addressIndex);
                String city = request.getParameter("City" + addressIndex);
                String state = request.getParameter("State" + addressIndex);
                String country = request.getParameter("Country" + addressIndex);
                String pinCode = request.getParameter("PinCode" + addressIndex);
                int Id = Integer.parseInt(request.getParameter("addressId" + addressIndex)); // Updated here

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, addressLine1);
                preparedStatement.setString(2, addressLine2);
                preparedStatement.setString(3, city);
                preparedStatement.setString(4, state);
                preparedStatement.setString(5, country);
                preparedStatement.setString(6, pinCode);
                preparedStatement.setInt(7, Id);
                preparedStatement.setInt(8, user_Id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addNewAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int addressCount = Integer.parseInt(request.getParameter("addressCount"));
        int user_Id = Integer.parseInt(request.getParameter("userId"));

        List<Address> addresses =  new ArrayList<>();

        if(addressCount != 0) {
            addressCount--;
            while (addressCount >= 0) {
                Address address = new Address();
                address.setId(Integer.parseInt(request.getParameter("addressId" + addressCount)));
                address.setAddressLine1(request.getParameter("AddressLine1" + addressCount));
                address.setAddressLine2(request.getParameter("AddressLine2" + + addressCount));
                address.setCity( request.getParameter("City" + addressCount));
                address.setState(request.getParameter("State" + addressCount));
                address.setCountry(request.getParameter("Country" + + addressCount));
                address.setPinCode( request.getParameter("PinCode" + addressCount));

                addresses.add(address);

                addressCount--;
            }

        }

        for (Address address: addresses) {
            // Validate that the 'AddressLine1' is not empty or null before inserting into the database
            if (address.getAddressLine1() == null || address.getAddressLine1().isEmpty()) {
                // Handle the case when 'AddressLine1' is missing or empty
                response.sendRedirect("error.jsp"); // Redirect to an error page or display an error message
                return;
            }

            //If ID of address is -1 then it is new address
            if (address.getId() == -1) {
                try (Connection connection = DBUtil.getConnection()) {
                    String query = "INSERT INTO addresses (AddressLine1, AddressLine2, City, State, Country, PinCode, User_Id) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, address.getAddressLine1());
                    preparedStatement.setString(2, address.getAddressLine2());
                    preparedStatement.setString(3, address.getCity());
                    preparedStatement.setString(4, address.getState());
                    preparedStatement.setString(5, address.getCountry());
                    preparedStatement.setString(6, address.getPinCode());
                    preparedStatement.setInt(7, user_Id);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

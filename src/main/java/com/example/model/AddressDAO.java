package com.example.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {
    private final String jdbcUrl = "jdbc:mysql://localhost:3306/prac";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "Muskan@12";



    // CRUD operations

    // Create a new address for a user
    public boolean addAddress(Address address) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO addresses (user_Id, AddressLine1, AddressLine2, City, State, Country, PinCode) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            pstmt.setInt(1, address.getId());
            pstmt.setString(2, address.getAddressLine1());
            pstmt.setString(3, address.getAddressLine2());
            pstmt.setString(4, address.getCity());
            pstmt.setString(5, address.getState());
            pstmt.setString(6, address.getCountry());
            pstmt.setString(7, address.getPinCode());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read address by ID
    public Address getAddressById(int addressId) {
        Address address = null;
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM addresses WHERE Id = ?")) {

            pstmt.setInt(1, addressId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    address = new Address();
                    address.setId(rs.getInt("id"));
                    address.setId(rs.getInt("user_Id"));
                    address.setAddressLine1(rs.getString("AddressLine1"));
                    address.setAddressLine2(rs.getString("AddressLine2"));
                    address.setCity(rs.getString("City"));
                    address.setState(rs.getString("State"));
                    address.setCountry(rs.getString("Country"));
                    address.setPinCode(rs.getString("PinCode"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    // Update an existing address
    public boolean updateAddress(Address address) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE addresses SET user_Id=?, AddressLine1=?, AddressLine2=?, City=?, State=?, Country=?, PinCode=? WHERE Id=?")) {

            pstmt.setInt(1, address.getId());
            pstmt.setString(2, address.getAddressLine1());
            pstmt.setString(3, address.getAddressLine2());
            pstmt.setString(4, address.getCity());
            pstmt.setString(5, address.getState());
            pstmt.setString(6, address.getCountry());
            pstmt.setString(7, address.getPinCode());
            pstmt.setInt(8, address.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an address
    public boolean deleteAddress(int addressId) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM addresses WHERE Id=?")) {

            pstmt.setInt(1, addressId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all addresses for a user
    public List<Address> getAllAddressesForUser(int userId) {
        List<Address> addressList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM addresses WHERE user_Id = ?")) {

            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Address address = new Address();
                    address.setId(rs.getInt("Id"));
                    address.setId(rs.getInt("user_Id"));
                    address.setAddressLine1(rs.getString("AddressLine1"));
                    address.setAddressLine2(rs.getString("AddressLine2"));
                    address.setCity(rs.getString("City"));
                    address.setState(rs.getString("State"));
                    address.setCountry(rs.getString("Country"));
                    address.setPinCode(rs.getString("PinCode"));
                    addressList.add(address);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressList;
    }
}

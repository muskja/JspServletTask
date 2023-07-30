<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Address" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Details</title>
</head>
<body>
    <h1>All Users Details</h1>

    <% List<User> users = (List<User>) request.getAttribute("users");
       if (users != null) { %>
        <% for (User user : users) { %>
        <h2>User Details</h2>
        <form action="UpdateUserServlet" method="post">
            <input type="hidden" name="action" value="updateUser">
            <input type="hidden" name="userId" value="<%= user.getId() %>">
            <p>FirstName: <input type="text" name="FirstName" value="<%= user.getFirstName() %>"></p>
            <p>LastName: <input type="text" name="LastName" value="<%= user.getLastName() %>"></p>
            <p>Email: <input type="text" name="Email" value="<%= user.getEmail() %>"></p>
            <p>PhoneNumber: <input type="text" name="PhoneNumber" value="<%= user.getPhoneNumber() %>"></p>
            <p>Gender: <input type="text" name="Gender" value="<%= user.getGender() %>"></p>

            <h2>Addresses</h2>
            <ul>
                <% List<Address> addresses = user.getAddresses();
                 if (addresses != null) {
                   for (Address address : addresses) { %>
                    <form action="UpdateUserServlet" method="post">
                        <input type="hidden" name="action" value="updateAddress">
                        <input type="hidden" name="user_Id" value="<%= user.getId() %>">
                        <input type="hidden" name="addressId" value="<%= address.getId() %>">
                        <li>Address Line 1: <input type="text" name="AddressLine1" value="<%= address.getAddressLine1() %>"></li>
                        <li>Address Line 2: <input type="text" name="AddressLine2" value="<%= address.getAddressLine2() %>"></li>
                        <li>City: <input type="text" name="City" value="<%= address.getCity() %>"></li>
                        <li>State: <input type="text" name="State" value="<%= address.getState() %>"></li>
                        <li>Country: <input type="text" name="Country" value="<%= address.getCountry() %>"></li>
                        <li>PinCode: <input type="text" name="PinCode" value="<%= address.getPinCode() %>"></li>
                        <br/>
                        <input type="submit" value="Update">

                        <li>
                              <button type="submit" formaction="AddressDeleteServlet" formmethod="post" onclick="return confirm('Are you sure you want to delete this address?')">
                                    <input type="hidden" name="user_Id" value="<%= user.getId() %>">
                                    <input type="hidden" name="Id" value="<%= address.getId() %>">
                                     Delete
                                      </button>
                                       </li>

                    </form>
                <% } }
                else {
                    // No addresses found
                }
                %>
            </ul>
            <!--<input type="submit" value="Update">-->
        </form>
        <% } %>
    <% } else { %>
        <p>No users found.</p>
    <% } %>
</body>
</html>

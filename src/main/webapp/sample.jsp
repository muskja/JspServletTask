<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Address" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User Details</title>
</head>
<body>
    <h1>Edit User Details</h1>
    <%
        User user = (User) request.getAttribute("user");
        List<Address> addresses = (List<Address>) user.getAddresses();
    %>
    <form action="EditUserServlet" method="POST">
        <input type="hidden" name="userId" value="<%= user.getId() %>">
        <label for="firstName">First Name:</label>
        <input type="text" name="firstName" value="<%= user.getFirstName() %>"><br>

        <label for="lastName">Last Name:</label>
        <input type="text" name="lastName" value="<%= user.getLastName() %>"><br>

        <label for="email">Email:</label>
        <input type="email" name="email" value="<%= user.getEmail() %>"><br>

        <label for="phoneNumber">Phone Number:</label>
        <input type="text" name="phoneNumber" value="<%= user.getPhoneNumber() %>"><br>

        <label for="gender">Gender:</label>
        <input type="text" name="gender" value="<%= user.getGender() %>"><br>

        <h2>Addresses</h2>
        <% for (Address address : addresses) { %>
            <label for="addressLine1">Address Line 1:</label>
            <input type="text" name="addressLine1" value="<%= address.getAddressLine1() %>"><br>

            <label for="addressLine2">Address Line 2:</label>
            <input type="text" name="addressLine2" value="<%= address.getAddressLine2() %>"><br>

            <label for="city">City:</label>
            <input type="text" name="city" value="<%= address.getCity() %>"><br>

            <label for="state">State:</label>
            <input type="text" name="state" value="<%= address.getState() %>"><br>

            <label for="country">Country:</label>
            <input type="text" name="country" value="<%= address.getCountry() %>"><br>

            <label for="pinCode">PinCode:</label>
            <input type="text" name="pinCode" value="<%= address.getPinCode() %>"><br>

            <br>
        <% } %>
        <form action ="TempServlet" method = "POST">
        <input type="text" name="userId" value="<%= user.getId() %>" >
        <input type ="submit" value = "val">
        </form>

        <input type="submit" value="Update">
    </form>
</body>
</html>

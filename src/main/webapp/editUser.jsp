<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.example.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Address" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit User Details</title>
</head>
<body>
    <h1>Edit User Details</h1><% request.getAttribute("user"); %>
    <form action="EditUserServlet" method="post">
        <label for="FirstName">FirstName:</label>
        <input type="text" id="FirstName" name="FirstName" value="${user.FirstName}" required><br>

        <label for="LastName">LastName:</label>
        <input type="text" id="LastName" name="LastName" value="${user.LastName}" required><br>

        <label for="Email">Email:</label>
        <input type="email" id="Email" name="Email" value="${user.Email}" required><br>



        <label for="PhoneNumber">PhoneNumber:</label>
        <input type="text" id="PhoneNumber" name="PhoneNumber" value="${user.PhoneNumber}" required><br>

        <label for="Gender">Gender:</label>
        <input type="text" id="Gender" name="Gender" value="${user.Gender}" required><br>



        <h2>Address Information</h2>
        <label for="AddressLine1">AddressLine1:</label>
        <input type="text" id="AddressLine1" name="AddressLine1" value="${address.AddressLine1}" required><br>

        <label for="AddressLine2">AddressLine2:</label>
        <input type="text" id="AddressLine2" name="AddressLine2" value="${address.AddressLine2}" required><br>

        <label for="City">City:</label>
        <input type="text" id="City" name="City" value="${address.City}" required><br>

        <label for="State">State:</label>
        <input type="text" id="State" name="State" value="${address.State}" required><br>

        <label for="Country">Country:</label>
        <input type="text" id="Country" name="Country" value="${address.Country}" required><br>

        <label for="PinCode">PinCode:</label>
        <input type="text" id="PinCode" name="PinCode" value="${address.PinCode}" required><br>

        <input type="submit" value="Update">
    </form>
    <h1>User Details</h1>
        <%
            User user = (User) request.getAttribute("user");
            List<Address> addresses = user.getAddresses();
        %>

           <p>FirstName: <input type="text" name="FirstName" value="<%= user.getFirstName() %>" required></p>
            <p>LastName: <input type="text" name="LastName" value="<%= user.getLastName() %>" required></p>
             <p>Email: <%= user.getEmail() %></p>
             <p>PhoneNumber: <input type="text" name="PhoneNumber" value="<%= user.getPhoneNumber() %>" required></p>
             <p>Gender: <input type="text" name="Gender" value="<%= user.getGender() %>" required></p>







        <!-- Display other user details as needed -->

        <h2>Addresses</h2>
        <% for (Address address : addresses) { %>

                <p>Address Line 1: <input type="text" name="AddressLine1" value="<%= address.getAddressLine1() %>" required></p>
                <p>Address Line 2: <input type="text" name="AddressLine2" value="<%= address.getAddressLine2() %>" required></p>
                <p>City: <input type="text" name="City" value="<%= address.getCity() %>" required></p>
                <p>State: <input type="text" name="State" value="<%= address.getState() %>" required></p>
                <p>Country: <input type="text" name="Country" value="<%= address.getCountry() %>" required></p>
                <p>PinCode: <input type="text" name="PinCode" value="<%= address.getPinCode() %>" required></p>
                <br>
            <% } %>






</body>
</html>

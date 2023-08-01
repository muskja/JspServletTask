<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Address" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Details</title>
</head>
<body>
    <h2>User Details</h2>
    <%
        User user = (User) request.getAttribute("user");
    %>
    <form action="UserDetailsUpdateServlet" method="post">
        <input type="hidden" name="action" value="updateUser">
        <input type="hidden" name="userId" value="<%= user.getId() %>">
        <p>FirstName: <input type="text" name="FirstName" value="<%= user.getFirstName() %>"></p>
        <p>LastName: <input type="text" name="LastName" value="<%= user.getLastName() %>"></p>
        <p>Email: <input type="text" name="Email" value="<%= user.getEmail() %>"></p>
        <p>PhoneNumber: <input type="text" name="PhoneNumber" value="<%= user.getPhoneNumber() %>"></p>
        <p>Gender: <input type="text" name="Gender" value="<%= user.getGender() %>"></p>

        <h3>Addresses</h3>
        <%
            List<Address> addresses = user.getAddresses();
            if (addresses != null) {
                int addressCount = 0;
                for (Address address : addresses) {
                    %>
                    <input type="hidden" name="action" value="updateAddress">
                    <input type="hidden" name="user_Id" value="<%= user.getId() %>">
                    <input type="hidden" name="addressId<%= addressCount %>" value="<%= address.getId() %>">
                    <li>Address Line 1: <input type="text" name="AddressLine1<%= addressCount %>" value="<%= address.getAddressLine1() %>"></li>
                    <li>Address Line 2: <input type="text" name="AddressLine2<%= addressCount %>" value="<%= address.getAddressLine2() %>"></li>
                    <li>City: <input type="text" name="City<%= addressCount %>" value="<%= address.getCity() %>"></li>
                    <li>State: <input type="text" name="State<%= addressCount %>" value="<%= address.getState() %>"></li>
                    <li>Country: <input type="text" name="Country<%= addressCount %>" value="<%= address.getCountry() %>"></li>
                    <li>PinCode: <input type="text" name="PinCode<%= addressCount %>" value="<%= address.getPinCode() %>"></li>

                    <%-- Add delete button for addresses starting from the second address --%>
                    <% if (addressCount > 0) { %>
                        <li>
                            <button type="submit" formaction="AddressDeleteServlet" formmethod="post" onclick="return confirm('Are you sure you want to delete this address?')">
                                <input type="hidden" name="user_Id" value="<%= user.getId() %>">
                                <input type="hidden" name="Id" value="<%= address.getId() %>">
                                Delete
                            </button>
                        </li>
                    <% } %>

                    <br/>
                    <br>
                    <%
                    addressCount++;
                }
                %>
                <input type="hidden" name="addressCount" value="<%= addressCount %>">
                <%
            }
        %>

        <!-- Add Address button -->
        <input type="submit" name="action" value="addAddress" onclick="return addAddress();" />

        <input type="submit" value="Save">
    </form>

    <!-- JavaScript function to handle adding new address fields -->
    <script>
        function addAddress() {
            var addressCount = document.getElementsByName('addressCount')[0].value;
            var userId = document.getElementsByName('userId')[0].value;
            addressCount = parseInt(addressCount);

            var addressDiv = document.createElement('div');
            addressDiv.innerHTML =
                '<input type="hidden" name="addressId' + addressCount + '" value="-1">'
                + ' <input type="hidden" name="user_Id" value="'+userId+'">'
                + '<li>Address Line 1: <input type="text" name="AddressLine1' + addressCount + '"></li>'
                + '<li>Address Line 2: <input type="text" name="AddressLine2' + addressCount + '"></li>'
                + '<li>City: <input type="text" name="City' + addressCount + '"></li>'
                + '<li>State: <input type="text" name="State' + addressCount + '"></li>'
                + '<li>Country: <input type="text" name="Country' + addressCount + '"></li>'
                + '<li>PinCode: <input type="text" name="PinCode' + addressCount + '"></li>'
                + '<br/><br>';

            var form = document.querySelector('form');
            form.insertBefore(addressDiv, form.querySelector('input[type="submit"][value="Save"]'));
            addressCount++;
            document.getElementsByName('addressCount')[0].value = addressCount;
            return false; // Prevent form submission when "Add Address" is clicked
        }
    </script>
</body>
</html>
<br>
<form action="LogoutServlet" method="post">
    <button type="submit">Logout</button>
</form>
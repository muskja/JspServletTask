<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Address Details</title>
</head>
<body>
    <h1>Address Details</h1>
    <form action="UpdateAddressServlet" method="post">
        <input type="hidden" name="action" value="updateAddress">
        <label for="addressLine1">Address Line 1:</label>
        <input type="text" id="addressLine1" name="addressLine1" required><br><br>

        <label for="addressLine2">Address Line 2:</label>
        <input type="text" id="addressLine2" name="addressLine2" required><br><br>

        <label for="city">City:</label>
        <input type="text" id="city" name="city" required><br><br>

        <label for="state">State:</label>
        <input type="text" id="state" name="state" required><br><br>

        <label for="country">Country:</label>
        <input type="text" id="country" name="country" required><br><br>

        <label for="pinCode">Pin Code:</label>
        <input type="text" id="pinCode" name="pinCode" required><br><br>

        <input type="submit" value="Update">
    </form>
</body>
</html>

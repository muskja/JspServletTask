<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <script>
        function addAddressField() {
            var addressFields = document.getElementById("address-fields");
            var newAddressField = document.createElement("div");
            newAddressField.innerHTML = "AddressLine1: <input type='text' name='AddressLine1'><br>" +
                "AddressLine2: <input type='text' name='AddressLine2'><br>" +
                "City: <input type='text' name='City'><br>" +
                "State: <input type='text' name='State'><br>" +
                "Country: <input type='text' name='Country'><br>" +
                "PinCode: <input type='text' name='PinCode'><br>";
            addressFields.appendChild(newAddressField);
        }

        function deleteAddressField() {
            var addressFields = document.getElementById("address-fields");
            if (addressFields.children.length > 1) {
                addressFields.removeChild(addressFields.lastChild);
            }
        }
    </script>
</head>
<body>
    <h1>Registration Page</h1>
    <form action="RegisterServlet" method="post">
        <!-- Existing fields -->
        FirstName: <input type="text" name="FirstName"><br>
        LastName: <input type="text" name="LastName"><br>
        Email: <input type="email" name="Email"><br>
        Password: <input type="password" name="Password"><br>
        PhoneNumber: <input type="text" name="PhoneNumber"><br>
        Gender: <input type="text" name="Gender"><br>

        <!-- Address fields -->
        <div id="address-fields">
            AddressLine1: <input type="text" name="AddressLine1"><br>
            AddressLine2: <input type="text" name="AddressLine2"><br>
            City: <input type="text" name="City"><br>
            State: <input type="text" name="State"><br>
            Country: <input type="text" name="Country"><br>
            PinCode: <input type="text" name="PinCode"><br>
        </div>

        <!-- Button to add or delete address fields -->
        <button type="button" onclick="addAddressField()">Add Address</button>
        <button type="button" onclick="deleteAddressField()">Delete Address</button>

        <!-- Submit button -->
        <input type="submit" value="Submit">
    </form>
</body>
</html>




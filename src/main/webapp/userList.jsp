<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
</head>
<body>
    <h1>User List</h1>
    <table>
        <tr>
            <th>FirstName</th>
            <th>Email</th>
            <!-- Add more columns as per your user details -->
        </tr>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.FirstName}</td>
                <td>${user.Email}</td>
                <!-- Display more user details -->
            </tr>
        </c:forEach>
    </table>
</body>
</html>

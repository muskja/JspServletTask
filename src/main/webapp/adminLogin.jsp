<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
</head>
<body>
    <h1>Admin Login</h1>
    <form method="post" action="AdminServlet">
        <label for="Email">Email</label>
        <input type="text" id="Email" name="Email" required><br><br>

        <label for="Password">Password</label>
        <input type="Password" id="Password" name="Password" required><br><br>

        <input type="submit" value="Login">
    </form>
</body>
</html>

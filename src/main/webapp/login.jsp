<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
</head>
<body>
    <h1>User Login</h1>
    <form method="post" action="LoginServlet">
        <label for="Email">Email</label>
        <input type="text" id="Email" name="Email" required><br><br>

        <label for="Password">Password</label>
        <input type="Password" id="Password" name="Password" required><br><br>

        <input type="submit" value="Login">
        <br><br>
         <input type="submit" value="Forget password">
    </form>
</body>
</html>

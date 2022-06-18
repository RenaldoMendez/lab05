<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Form</title>
    </head>
    <body>
        <h1>Login</h1>
        <br>
        <form action="login" method="post">
            <label>Username:</label>
            <input type="text" name="user_name" value="${preloadusername}"><br>
            <label>Password:</label>
            <input type="text" name="pass_word" value="${preloapassword}"><br>
            <input type="submit" value="Log in">
            <p>${emptyFields}</p>
            <p>${invalidLogin}</p>
            <p>${loggedOutMessage}</p>
        </form>
    </body>
</html>

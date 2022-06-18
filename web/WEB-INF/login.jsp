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
            <input type="text" name="user_name" value="${userName}"><br>
            <label>Password:</label>
            <input type="text" name="pass_word" value="${passWord}">
            <input type="submit" value="Log in">
            <p>${errormessage}</p>
            <p>${loggedOutMessage}</p>
        </form>
    </body>
</html>

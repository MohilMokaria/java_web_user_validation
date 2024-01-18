<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Set the character encoding for the HTML page -->
    <meta charset="UTF-8">
    <!-- Define the viewport settings for responsive design -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Set the title of the HTML page -->
    <title>User Login Form</title>
    <!-- Link to the external stylesheet for styling -->
    <link rel="stylesheet" href="./index_styles.css"/>
</head>
<body>
    <!-- Login form with action pointing to LoginServlet and method set to post -->
    <form id="regform" action="LoginServlet" method="post">
        <!-- Form heading -->
        <h1>User Login Form</h1>
        
        <!-- Input field for email with label -->
        <label for="mail">Email ID : </label>
        <input type="email" id="mail" name="mail" required>

        <!-- Input field for password with label -->
        <label for="pass">New Password : </label>
        <input type="password" id="pass" name="pass" required>

        <!-- Login button centered in the form -->
        <center><button type="submit">Login</button></center>
        
        <!-- Link to the signup page -->
        <p>Don't have an account? <a href="./index.jsp">Sign-up</a></p>
    </form>
</body>
</html>

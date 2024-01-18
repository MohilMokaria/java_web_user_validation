<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Set the character encoding for the HTML page -->
    <meta charset="UTF-8">
    <!-- Define the viewport settings for responsive design -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Set the title of the HTML page -->
    <title>User Sign-up Form</title>
    <!-- Link to the external stylesheet for styling -->
    <link rel="stylesheet" href="./index_styles.css"/>
    
    <!-- JavaScript function for form validation -->
    <script>
        function validator(){
            // Regular expression for a valid email address
            var emailRegex = /^[^\s@]+@[^\s@]+\.[a-z]{2,}$/i;
            
            // Retrieve values from form fields
            var email = document.getElementById("mail").value;
            var pass1 = document.getElementById("pass1").value;
            var pass2 = document.getElementById("pass2").value;
            
            // Validate email using the regular expression
            if(!emailRegex.test(email)){
                alert("Invalid Email !");
                return false;
            }
                       
            // Validate that the passwords match
            if(pass1 !== pass2){
                alert("Password Doesn't Match! Try Again");
                return false;
            }
            
            // Validate password length
            if(pass1.length < 6 || pass1.length > 12 ){
                alert("Password must be 6 to 12 characters long !");
                return false;
            }
            
            // Submit the form if all validations pass
            document.getElementById("regform").submit();
            return true;
        }
    </script>
    
</head>
<body>
    <!-- Signup form with action pointing to SignupServlet and method set to post -->
    <form id="regform" action="SignupServlet" method="post" onsubmit="return validator()">
        <!-- Form heading -->
        <h1>User Sign-up Form</h1>
        
        <!-- Input field for email with label -->
        <label for="mail">Email ID : </label>
        <input type="email" id="mail" name="mail" required>

        <!-- Input field for password with label -->
        <label for="pass1">New Password : </label>
        <input type="password" id="pass1" name="pass1" required>
        
        <!-- Input field to confirm password with label -->
        <label for="pass2">Confirm Password : </label>
        <input type="password" id="pass2" name="pass2" required>

        <!-- Signup button centered in the form -->
        <center><button type="submit">Sign-up</button></center>
        
        <!-- Link to the login page -->
        <p>Already Signed-up? <a href="./login.jsp">Login</a></p>
    </form>
</body>
</html>

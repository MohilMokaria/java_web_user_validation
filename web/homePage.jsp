<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="jakarta.servlet.http.*" %>

<html>
    <head>
        <!-- Set the title and character encoding for the HTML page -->
        <title>Home Page</title>
        <meta charset="UTF-8">
        <!-- Define the viewport settings for responsive design -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>

        <!-- Check if the user is logged in by checking the session attribute "userEmail" -->
        <% if (session.getAttribute("userEmail") != null) { %>
            <!-- Display a welcome message with the user's email -->
            <h1>You are logged-in as, <%= session.getAttribute("userEmail") %>!</h1>

            <!-- Logout form with a button to submit the form -->
            <form action="LogoutServlet" method="get">
                <button type="submit">Logout</button>
            </form>
        <% } else { %>
            <!-- Display a message prompting the user to log in -->
            <p>Please log in to access the home page.</p>
            <!-- Link to the login page -->
            <a href="login.jsp">Login</a>
        <% } %>
    </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="jakarta.servlet.http.*" %>

<html>
    <head>
        <title>Home Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>

        <% if (session.getAttribute("userEmail") != null) { %>
            <h1>You are logged-in as, <%= session.getAttribute("userEmail") %>!</h1>

            <form action="LogoutServlet" method="get">
                <button type="submit">Logout</button>
            </form>
        <% } else { %>
            <p>Please log in to access the home page.</p>
            <a href="login.jsp">Login</a>
        <% } %>
    </body>
</html>

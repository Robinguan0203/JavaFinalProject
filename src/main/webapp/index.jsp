<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Food Waste Reduction Platform</title>
</head>
<body>
    <h1> Home - Food Waste Reduction Platform </h1>
    <c:if test="${not empty errorMessage}">
        <div style="color: red;">
            ${errorMessage}
        </div>
    </c:if>
    <form action="LoginServlet" method="post">
        Email:
        <INPUT TYPE="email" NAME="email" ><BR>
        Password:
        <INPUT TYPE="password" NAME="password" ><P>
        
        <button type="submit" value="Submit">Submit</button>
    </form>
    
</body>
</html>
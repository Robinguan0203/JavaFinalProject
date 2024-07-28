<%-- 
    Document   : add.jsp
    Created on : Jul 27, 2024, 11:00:56 AM
    Author     : robin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Food</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .container {
                width: 80%;
                margin: 0 auto;
            }
            .header, .form-section, .logout {
                margin-bottom: 20px;
            }
            .form-section form {
                margin-top: 20px;
            }
            .form-section input, .form-section button {
                display: block;
                margin: 10px 0;
                padding: 10px;
                width: 100%;
            }
            .form-section button {
                width: auto;
            }
            .message {
                color: green;
                font-weight: bold;
                margin-bottom: 20px;
            }
            .error {
                color: red;
                font-weight: bold;
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <h1>Welcome, ${retailer.firstName} ${retailer.lastName}</h1>
                <p>Email: ${retailer.email}</p>
                <p>Type: Retailer</p>
            </div>
            <div class="form-section">
                <h2>Add New Food</h2>
                <div class="message">
                    <%-- Display error message --%>
                    <%
                        String errorMessage = (String) request.getAttribute("errorMessage");
                        if (errorMessage != null) {
                            out.println("<p class='error'>" + errorMessage + "</p>");
                        }
                    %>
                </div>
                <form action="${pageContext.request.contextPath}/RetailerController" method="post">
                    <input type="hidden" name="action" value="storeNewFood" />
                    
                    <label for="name">Food Name:</label>
                    <input type="text" id="name" name="name" required />

                    <label for="expireDays">Expire Days:</label>
                    <input type="number" id="expireDays" name="expireDays" min="1" required />

                    <label for="unitPrice">Unit Price:</label>
                    <input type="number" id="unitPrice" name="unitPrice" step="0.01" min="0" required />

                    <label for="discount">Discount:</label>
                    <input type="number" id="discount" name="discount" step="0.01" min="0" max="1" required />

                    <button type="submit">Add</button>
                </form>
            </div>
            <div class="logout">
                <form action="logout" method="post">
                    <button type="submit">Logout</button>
                </form>
            </div>
        </div>
    </body>
</html>

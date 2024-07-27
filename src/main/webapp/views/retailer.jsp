<%-- 
    Document   : retailer.jsp
    Created on : Jul 27, 2024, 9:42:23 AM
    Author     : robin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Retailer Dashboard</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .container {
                width: 80%;
                margin: 0 auto;
            }
            .header, .actions, .logout {
                margin-bottom: 20px;
            }
            .actions button {
                display: block;
                width: 200px;
                margin: 10px 0;
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
                <h1>Welcome, ${user.firstName} ${user.lastName}</h1>
                <p>Email: ${user.email}</p>
                <p>Type: Retailer</p>
            </div>
            <div class="message">
                <%
                    String successMessage = request.getParameter("successMessage");
                    if (successMessage != null) {
                        out.println("<p class='message'>" + successMessage + "</p>");
                    }
                %>
            </div>
            <div class="actions">
                <h2>Actions</h2>
                <form action="retailerController" method="post">
                    <input type="hidden" name="action" value="addFood" />
                    <button type="submit">Add Food</button>
                </form>
                <form action="addIncomingFood" method="post">
                    <button type="submit">Add New Incoming Food</button>
                </form>
                <form action="setFoodExpireDays" method="post">
                    <button type="submit">Set Food Expire Days</button>
                </form>
                <form action="setInventoryExpireDate" method="post">
                    <button type="submit">Set Inventory Expire Date</button>
                </form>
                <form action="identifySurplus" method="post">
                    <button type="submit">Identify Surplus</button>
                </form>
                <form action="listSurplus" method="post">
                    <button type="submit">List Surplus</button>
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

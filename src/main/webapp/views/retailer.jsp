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
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                        out.println("<p class='error'>" + errorMessage + "</p>");
                    }
                %>
            </div>
            <div class="actions">
                <h2>Actions</h2>
                <form action="${pageContext.request.contextPath}/RetailerController" method="post">
                    <input type="hidden" name="action" value="addFood" />
                    <button type="submit">Add Food</button>
                </form>
                <form action="${pageContext.request.contextPath}/RetailerController" method="post">
                    <input type="hidden" name="action" value="addQuantities" />
                    <button type="submit">Add Quantities</button>
                </form>
                <form action="${pageContext.request.contextPath}/RetailerController" method="post">
                    <input type="hidden" name="action" value="changeFoodExpireDays" />
                    <button type="submit">Change Food Expire Days</button>
                </form>
                <form action="${pageContext.request.contextPath}/RetailerController" method="post">
                    <input type="hidden" name="action" value="updateInventoryExpireDate" />
                    <button type="submit">Update Inventory Expire Date</button>
                </form>
                <form action="${pageContext.request.contextPath}/RetailerController" method="post">
                    <input type="hidden" name="action" value="identifySurplus" />
                    <button type="submit">Identify Surplus</button>
                </form>
                <form action="${pageContext.request.contextPath}/RetailerController" method="post">
                    <input type="hidden" name="action" value="listSurplus" />
                    <button type="submit">List Surplus for Sale or Donation</button>
                </form>
                <form action="${pageContext.request.contextPath}/RetailerController" method="post">
                    <input type="hidden" name="action" value="viewInventory" />
                    <button type="submit">View Inventory</button>
                </form>
                <form action="${pageContext.request.contextPath}/RetailerController" method="post">
                    <input type="hidden" name="action" value="viewTransactions" />
                    <button type="submit">View Transaction</button>
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

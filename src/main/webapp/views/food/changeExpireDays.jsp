<%-- 
    Document   : changeFoodExpireDays
    Created on : Jul 27, 2024, 12:37:11 PM
    Author     : robin
--%>

<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="com.fwrp.models.Food" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Food Expire Days</title>
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
            .form-section input, .form-section select, .form-section button {
                display: block;
                margin: 10px 0;
                padding: 10px;
                width: 100%;
            }
            .form-section button {
                width: auto;
            }
            .message {
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
                <h2>Add Quantities to Food</h2>
                <div class="message">
                    <%
                        String errorMessage = (String) request.getAttribute("errorMessage");
                        if (errorMessage != null) {
                            out.println("<p class='message'>" + errorMessage + "</p>");
                        }
                    %>
                </div>
                <form action="RetailerController" method="post">
                    <input type="hidden" name="action" value="storeExpireDays" />
                    
                    <label for="food">Select Food:</label>
                    <select id="food" name="foodId" required>
                        <option value="">Please Select</option>
                        <%
                            ArrayList<Food> foods = (ArrayList<Food>) request.getAttribute("foods");
                            if (foods != null) {
                                for (Food food : foods) {
                                    out.println("<option value='" + food.getId() + "'>" + food.getName() + "</option>");
                                }
                            }
                        %>
                    </select>
                    
                    <label for="expireDays">New Expire Days</label>
                    <input type="number" id="expireDays" name="expireDays" min="1" required />

                    <button type="submit">Submit</button>
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

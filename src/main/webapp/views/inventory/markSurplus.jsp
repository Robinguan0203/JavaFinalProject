<%-- 
    Document   : add.jsp
    Created on : Jul 27, 2024, 11:00:56 AM
    Author     : robin
--%>

<%@page import="com.fwrp.models.ExpireInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Inventory Expire Date</title>
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
            .form-section table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            .form-section table, .form-section th, .form-section td {
                border: 1px solid black;
                padding: 10px;
                text-align: left;
            }
            .form-section th, .form-section td {
                width: 20%;
            }
            .form-section input[type="date"] {
                width: 90%;
                padding: 5px;
                box-sizing: border-box;
            }
            .form-section button {
                margin-top: 20px;
                padding: 10px 20px;
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
                <h2>Mark if surplus</h2>
                <div class="message">
                    <c:if test="${not empty errorMessage}">
                        <p class="message">${errorMessage}</p>
                    </c:if>
                </div>
                <form action="${pageContext.request.contextPath}/RetailerController" method="post">
                    <input type="hidden" name="action" value="storeUpdateIsSurplus" />
                    <% 
                        ArrayList<ExpireInfo> expireInfos= (ArrayList<ExpireInfo>) request.getAttribute("expireInfos");
                        if (expireInfos == null || expireInfos.isEmpty()) { 
                    %>
                        <p>No Food Expire Date Information Found</p>
                    <% 
                        } else { 
                    %>
                        <table>
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Food Name</th>
                                    <th>Quantity</th>
                                    <th>Expire Date</th>
                                    <th>Surplus</th>
                                    <th>New Expire Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%! int index = 0; %>
                                <% 
                                for (ExpireInfo info : expireInfos) { 
                                %>
                                    <tr>
                                        <td><%= index + 1 %></td>
                                        <td><%= info.getFood().getName() %></td>
                                        <td><%= info.getQuantity() %></td>
                                        <td><%= info.getExpireDate() %></td>
                                        <td><%= info.isIsSurplus() %></td>
                                        <td>
                                            <select name="newIsSurplus_<%= info.getId() %>">
                                                <option value="">Please Select</option>
                                                <option value="0">False</option>
                                                <option value="1">True</option>
                                            </select>
                                        </td>
                                    </tr>
                                <% 
                                    index++; 
                                } 
                                %>
                            </tbody>
                        </table>
                    <% 
                        } 
                    %>
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

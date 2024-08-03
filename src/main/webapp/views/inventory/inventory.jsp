<%-- 
    Document   : add.jsp
    Created on : Jul 27, 2024, 11:00:56 AM
    Author     : robin
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.fwrp.models.Food"%>
<%@page import="com.fwrp.models.ExpireInfo"%>
<%@ include file="../header.jsp" %>
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
                table-layout: fixed; /* Ensure fixed layout */
            }
            .form-section table, .form-section th, .form-section td {
                border: 1px solid black;
                padding: 10px;
                text-align: center;
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
        <%@ include file="../nav.jsp" %>
        <%@ include file="../messageBar.jsp" %>
        <div class="container">
            <center><h2>View Food Inventory</h2></center>
            <div class="form-section">    
                <% 
                    HashMap<Food, Integer[]> foodInventoryMap= (HashMap<Food, Integer[]>) request.getAttribute("foodInventoryMap");
                    if (foodInventoryMap == null || foodInventoryMap.isEmpty()) { 
                %>
                    <p>No Inventory Data Found</p>
                <% 
                    } else { 
                %>
                    <table>
                        <colgroup>
                            <col style="width: 5%;">
                            <col style="width: 20%;">
                            <col style="width: 15%;">
                            <col style="width: 15%;">
                            <col style="width: 15%;">
                            <col style="width: 15%;">
                            <col style="width: 15%;">
                        </colgroup>
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Food Name</th>
                                <th>Total Surplus Quantity</th>
                                <th>Inventory Normal</th>
                                <th>Listed For Discount</th>
                                <th>Listed For Donation</th>
                                <th>To be Listed</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%! int index = 0; %>
                            <% 
                            for (Map.Entry<Food, Integer[]> entry : foodInventoryMap.entrySet()) {
                                Food food = entry.getKey();
                                Integer[] qty = entry.getValue();
                            %>
                                <tr>
                                    <td><%= index + 1 %></td>
                                    <td><%= food.getName() %></td>
                                    <td><%= qty[0] %></td>
                                    <td><%= qty[1] %></td>
                                    <td><%= qty[2] %></td>
                                    <td><%= qty[3] %></td>
                                    <td><%= (qty[0] - qty[2] - qty[3]) %></td>
                                </tr>
                            <% 
                                index++; 
                            } 
                            %>
                        </tbody>
                    </table>
                    <p>${count} items.</p>
                <% 
                    } 
                %>                    
            </div>
        </div>
        <%@ include file="../footer.jsp" %>
    </body>
</html>
<style>
    body {
        font-family: Arial, sans-serif;
    }
    .container {
        width: 80%;
        margin: 0 auto;
        margin-top: 100px; /* ???????????? */
    }
    .header, .form-section, .logout {
        margin-bottom: 20px;
    }
    .form-section table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
        table-layout: fixed; /* Ensure fixed layout */
    }
    .form-section table, .form-section th, .form-section td {
        border: 1px solid black;
        padding: 10px;
        text-align: center;
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

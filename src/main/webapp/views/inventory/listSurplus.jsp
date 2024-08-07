<%-- 
    Document   : add.jsp
    Created on : Jul 27, 2024, 11:00:56 AM
    Author     : robin
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.TreeMap"%>
<%@page import="com.fwrp.models.Food"%>
<%@page import="com.fwrp.models.ExpireInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="../header.jsp" %>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .container {
                width: 80%;
                margin: 0 auto;
                padding-top: 80px;
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
        <%@ include file="../nav.jsp" %>
        <%@ include file="../messageBar.jsp" %>
        <div class="container">
            <center><h2>List Surplus Food</h2></center>
            <div class="form-section">
                <h4>List Surplus Food for Sale or Donation</h4>                             
                <form action="${pageContext.request.contextPath}/RetailerController" method="post">
                    <input type="hidden" name="action" value="storeListSurplus" />                    
                    <% 
                        HashMap<Food, Integer[]> foodExpireQtyMap = (HashMap<Food, Integer[]>) request.getAttribute("foodExpireQtyMap");
                        request.setAttribute("foodExpireQtyMap", foodExpireQtyMap);
                        if (foodExpireQtyMap == null || foodExpireQtyMap.isEmpty()) { 
                    %>
                        <p>No Food Expire Data Information Found</p>
                    <% 
                        } else { 
                    %>
                        <table>
                            <colgroup>
                                <col style="width: 4%;">
                                <col style="width: 11%;">
                                <col style="width: 11%;">
                                <col style="width: 11%;">
                                <col style="width: 11%;">
                                <col style="width: 11%;">
                                <col style="width: 11%;">
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
                                    <th>Quantity to Sale</th>
                                    <th>Quantity to Donate</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%! int index = 0; %>
                                <% 
                                for (Map.Entry<Food, Integer[]> entry : foodExpireQtyMap.entrySet()) {
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
                                        <td>
                                            <input type="number" min="0" name="qtyToDiscount_<%= food.getId() %>" >
                                        </td>
                                        <td>
                                            <input type="number" min="0" name="qtyToDonate_<%= food.getId() %>">
                                            <input type="hidden" name="foodId" value="<%= food.getId() %>">
                                            <input type="hidden" name="foodName" value="<%= food.getName() %>">
                                            <input type="hidden" name="foodExpireDays" value="<%= food.getExpireDays()%>">
                                            <input type="hidden" name="foodUnitprice" value="<%= food.getUnitPrice()%>">
                                            <input type="hidden" name="foodDiscount" value="<%= food.getDiscount()%>">
                                            <input type="hidden" name="totalSurplusQty" value="<%= qty[0] %>">
                                            <input type="hidden" name="inventoryNormal" value="<%= qty[1] %>">
                                            <input type="hidden" name="listedForDiscount" value="<%= qty[2] %>">
                                            <input type="hidden" name="listedForDonation" value="<%= qty[3] %>">
                                        </td>
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
                    <button type="submit">Submit</button>
                </form>
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
    .form-section th {
        background-color: #E0F7FA; /* ????? */
        color: #00796B; /* ?????? */
    }
    .form-section input[type="date"] {
        width: 90%;
        padding: 5px;
        box-sizing: border-box;
    }
    .form-section button {
        margin-top: 20px;
        padding: 10px 20px;
        background-color: #E0F7FA; /* ???????footer????? */
        border: 1px solid #B2EBF2; /* ??????????? */
        border-radius: 4px;
        color: #00796B; /* ???? */
        font-size: 16px;
        cursor: pointer;
        display: block;
        margin: 20px auto; /* ???? */
        width: 20%; /* ???? */
    }
    .form-section button:hover {
        background-color: #B2EBF2; /* ?????????? */
    }

</style>

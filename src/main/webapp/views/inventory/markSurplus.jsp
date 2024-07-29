<%-- 
    Document   : add.jsp
    Created on : Jul 27, 2024, 11:00:56 AM
    Author     : robin
--%>

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
        <%@ include file="../nav.jsp" %>
        <%@ include file="../messageBar.jsp" %>
        <div class="container">
            <center><h2>Identify Surplus Food</h2></center>
            <div class="form-section">
                <h4>Mark if surplus</h4>                
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
                                    <th>Is Surplus</th>
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
                                                <option value="0"
                                                <% if(!info.isIsSurplus()){ %>
                                                    selected 
                                                <% } %>
                                                >False</option>
                                                <option value="1"
                                                <% if(info.isIsSurplus()){ %>
                                                    selected 
                                                <% } %>
                                                >True</option>
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

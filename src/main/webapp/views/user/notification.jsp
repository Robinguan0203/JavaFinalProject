<%-- 
    Document   : add.jsp
    Created on : Jul 27, 2024, 11:00:56 AM
    Author     : robin
--%>

<%@page import="com.fwrp.constants.NotificationMethodConstant"%>
<%@page import="com.fwrp.models.Notification"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.fwrp.models.User"%>
<%@page import="com.fwrp.models.Consumer"%>
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
            <center><h2>View Notifications</h2></center>
            <div class="form-section">    
                <% 
                    ArrayList<Notification> notifications = (ArrayList<Notification>) request.getAttribute("notifications");
                    if (notifications == null || notifications.isEmpty()) { 
                %>
                    <p>No Notification Found</p>
                <% 
                    } else { 
                %>
                    <table>
                        <colgroup>
                            <col style="width: 5%;">
                            <col style="width: 15%;">
                            <col style="width: 15%;">
                            <col style="width: 45%;">
                        </colgroup>
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Method</th>
                                <th>Date</th>
                                <th>Message</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% int index = 0; %>
                            <% 
                                for (Notification notification : notifications) {
                                %>
                                    <tr>
                                        <td><%= index + 1 %></td>
                                        <td>
                                            <% if(notification.getMethod() == NotificationMethodConstant.EMAIL) { %>
                                            Email
                                            <% }else if(notification.getMethod() == NotificationMethodConstant.PHONE) { %>
                                            Phone
                                            <% }else if(notification.getMethod() == NotificationMethodConstant.SYSTEM) { %>
                                            System
                                            <%}%>
                                        </td>
                                        <td><%= notification.getDate() %></td>
                                        <td><%= notification.getNotification()%></td>
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
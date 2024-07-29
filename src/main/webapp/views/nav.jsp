<%-- 
    Document   : nav.jsp
    Created on : Jul 29, 2024, 11:27:35 AM
    Author     : robin
--%>

<%@page import="com.fwrp.models.User"%>
<%@page import="com.fwrp.constants.UserTypeConstant"%>
<header class="header">
    <nav class="navbar">
        <ul class="nav-list">
            <li class="nav-item platform-name">FWRP - Food Waste Reduction Platform</li>
            <%  
                User user = (User) session.getAttribute("user");
                if (user != null) { 
            %>
            <li class="nav-item home-link">
                <a href="<%= request.getContextPath() %>/UserController?action=<% 
                        switch(user.getType()){
                            case UserTypeConstant.RETAILER: out.print("viewRetailer"); break;
                            case UserTypeConstant.CHARITY: out.print("viewCharity"); break;
                            case UserTypeConstant.CONSUMER: out.print("viewConsumer"); break;
                            default: out.print("index"); // Fallback to a default action
                        }
                    %>" class="home-button">Home</a>
            </li>
            <% } %>
            <li class="nav-item user-name">
                <% 
                    Integer phoneNotificationCount = (Integer) session.getAttribute("phoneNotificationCount");
                    Integer emailNotificationCount = (Integer) session.getAttribute("emailNotificationCount");
                    //out.print(phoneNotificationCount);
                    //out.print(emailNotificationCount);
                    if (phoneNotificationCount == null) {
                        phoneNotificationCount = 0;
                    }
                    if (emailNotificationCount == null) {
                        emailNotificationCount = 0;
                    }
                    
                    if (user != null) {
                        out.print(user.getFirstName() + " " + user.getLastName());
                    } else {
                        out.print("Please login");
                    }                    
                %>
            </li>
            <%
                if(user != null) {
            %>
             
            <li class="nav-item user-type">You are 
                <% 
                    if (user != null) {
                        switch(user.getType()){
                            case UserTypeConstant.RETAILER: %>
                                Retailer
                                <%break;
                            case UserTypeConstant.CHARITY:%>
                                Charity
                                <%break;
                            case UserTypeConstant.CONSUMER:%>
                                Consumer
                                <%break;
                            default:%>
                                Unknown
                        <%}
                    }
                %>
            </li>
            <%}%>
             <li class="nav-item notification-icons">                
                <% if(phoneNotificationCount > 0){%>                    
                <form action="${pageContext.request.contextPath}/UserController" method="post" class="notification-icon">
                    <input type="hidden" name="action" value="viewPhoneNotification" />
                    <button type="submit" class="notification-button">
                        <i class="fas fa-phone"></i>
                        <% if (phoneNotificationCount > 0) { %>
                            <span class="notification-count"><%= phoneNotificationCount %></span>
                        <% } %>
                    </button>
                </form>
                <%}%>
                <% if(emailNotificationCount > 0){%>
                <form action="${pageContext.request.contextPath}/UserController" method="post" class="notification-icon">
                    <input type="hidden" name="action" value="viewEmailPhoneNotification" />
                    <button type="submit" class="notification-button">
                        <i class="fas fa-envelope"></i>
                        <% if (emailNotificationCount > 0) { %>
                            <span class="notification-count"><%= emailNotificationCount %></span>
                        <% } %>
                    </button>
                </form>
                <%}%>
            </li>
            <li class="nav-item logout">
                <% if (user != null) { %>
                    <form action="${pageContext.request.contextPath}/UserController" method="post" style="display: inline;">
                        <input type="hidden" name="action" value="logout" />
                        <button type="submit" class="logout-button">Logout</button>
                    </form>
                <% } %>
            </li>
        </ul>
    </nav>
</header>

<style>
    .header {
        margin-bottom: 20px;
        width: 100%;
    }
    .navbar {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        background-color: #ADD8E6;
        padding: 10px 20px;
        border-radius: 0 0 5px 5px; /* Optional: Rounded bottom corners */
        z-index: 1000; /* Ensure navbar is above other content */
    }

    .nav-list {
        display: flex;
        justify-content: space-between;
        align-items: center;
        list-style: none;
        padding: 0;
        margin: 0;
        width: 100%; /* ??100% */
    }

    .nav-item {
        margin: 0 10px;
        display: flex;
        align-items: center; /* ???? */
    }

    .platform-name {
        flex: 1;
        text-align: left;
        padding-left: 20px; /* ??? */
    }

    .user-name,
    .user-type {
        text-align: center;
        flex-shrink: 0; /* ??????? */
    }
    
    .notification-icons {
        display: flex;
        align-items: center;
    }

    .notification-icon {
        position: relative;
        margin-left: 15px;
        font-size: 18px;
        color: black;
        text-decoration: none;
    }
    .notification-button {
        background: none;
        border: none;
        cursor: pointer;
        font-size: inherit;
        padding: 0;
        position: relative;
        display: inline-flex;
        align-items: center;
        outline: none; /* ??????????? */
    }
    .notification-icon .notification-count {
        position: absolute;
        top: -5px;
        right: -10px;
        background-color: red;
        color: white;
        border-radius: 50%;
        padding: 2px 6px;
        font-size: 12px;
    }

    .logout {
        text-align: right;
        padding-right: 20px; /* ??? */
    }

    .logout-button {
        background: none;
        border: none;
        color: blue;
        cursor: pointer;
        font-size: 16px;
        padding: 0;
        margin: 0;
    }

    .logout-button:hover {
        text-decoration: underline;
    }
</style>
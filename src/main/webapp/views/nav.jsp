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
            <li class="nav-item user-name">
                <% 
                    User user = (User) session.getAttribute("user");
                    if (user != null) {
                        out.print(user.getFirstName() + " " + user.getLastName());
                    } else {
                        out.print("Please login");
                    }
                %>
            </li>
            <li class="nav-item user-type">
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
            <li class="nav-item logout">
                <% if (user != null) { %>
                    <form action="UserController" method="post" style="display: inline;">
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
        background-color: #ADD8E6; /* ??? */
        padding: 10px 20px;
        border-radius: 5px;
        width: 100%; /* ??100% */
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
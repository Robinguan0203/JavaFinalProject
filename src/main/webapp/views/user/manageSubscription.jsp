<html>
<head>
    <title>Donation Inventory</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="${pageContext.request.contextPath}/static/css/fwrp.css" rel="stylesheet" type="text/css"/>
</head>
<%@page import="com.fwrp.models.User"%>
<%@page import="com.fwrp.dataaccess.dto.SubscriptionDTO"%>
<%@page import="java.util.List"%>
<%@ include file="../header.jsp" %>
<%@ include file="../nav.jsp" %>
<%@ include file="../messageBar.jsp" %>
<body>
<div class="container">
    <div class="header">
        <%
            User thisUser = (User) session.getAttribute("user");
            String type; // ???
            if (thisUser != null) {
                switch(thisUser.getType()){
                    case UserTypeConstant.RETAILER:
                        type = "Retailer";
                        break;
                    case UserTypeConstant.CHARITY:
                        type = "Charity";
                        break;
                    case UserTypeConstant.CONSUMER:
                        type = "Consumer";
                        break;
                    default:
                        type = "Unknown";
                }
        %>
        <div>
            <center><h4>Hello <%= thisUser.getFirstName() %> <%= thisUser.getLastName() %>, you are a <%= type %>, please select your action</h4>
                <% } else { %>
                <p>User not found. Please login.</p>
                <% } %>
            </center>
        </div>
    </div>
    <div class="container mx-auto px-12 py-6 flex flex-col space-y-4">
        <%
            List<SubscriptionDTO> subscriptionDTOList= (List<SubscriptionDTO>) request.getAttribute("subscriptionList");

            if (subscriptionDTOList == null || subscriptionDTOList.isEmpty()) {
        %>
        <p>No Subscriptions Found</p>
        <%
        } else {
        %>
        <table class="p-4 table-auto border-collapse border border-slate-400">
            <caption class="caption-top text-2xl mb-4">
                User Subscription
            </caption>
            <thead>
            <tr>
                <th class="border border-slate-300 p-2">Id</th>
<%--                <th class="border border-slate-300 p-2">User Id</th>--%>
                <th class="border border-slate-300 p-2">Notify Method</th>
                <th class="border border-slate-300 p-2">Delete</th>
            </tr>
            </thead>
            <tbody>
            <%! int index = 0; %>
            <%
                for (SubscriptionDTO item : subscriptionDTOList) {
            %>
            <tr>
                <td class="border border-slate-300 p-2">#<%= item.getId() %></td>
<%--                <td class="border border-slate-300 p-2"><%= item.getUserId() %></td>--%>
                <td class="border border-slate-300 p-2"><%= com.fwrp.controllers.ConsumerController.MethodType.getDescriptionByCode(item.getMethod()) %></td>
                <td class="border border-slate-300 p-2">
                    <form action="${pageContext.request.contextPath}/ConsumerController" method="post">
                        <input type="hidden" name="action" value="deleteSubscription" />
                        <input type="hidden" class="input" id="id" name="id" value="<%= item.getId() %>" required />
                        <button class="bg-red-100 hover:bg-red-300 p-4 m-2 rounded-md" type="submit">Delete</button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <%
            }
        %>
        <div class="flex items-center justify-center">
            <form action="${pageContext.request.contextPath}/ConsumerController" method="post">
                <input type="hidden" name="action" value="showAddSubscription" />
                <button class="bg-green-100 hover:bg-green-300 p-4 rounded-md justify-items-end" type="submit">Add Subscription</button>
            </form>
        </div>
    </div>

</div>
<%@ include file="../footer.jsp" %>
<style>

    .actions {
        display: flex;
        flex-direction: column;
        gap: 10px; /* ???? */
    }

    .actions form {
        margin: 0;
        width: 250px; /* ?????????? */
        margin: 0 auto; /* ???? */
    }

    .actions button {
        background-color: #E0F7FA; /* ?footer???????? */
        border: 1px solid #B2EBF2; /* ??????????? */
        border-radius: 4px;
        color: #00796B; /* ???? */
        font-size: 16px;
        padding: 10px 15px; /* ????? */
        cursor: pointer;
        text-align: center;
        width: 100%; /* ????????? */
    }

    .actions button:hover {
        background-color: #B2EBF2; /* ?????????? */
    }
</style>
</body>
</html>

<%-- 
    Document   : retailer.jsp
    Created on : Jul 27, 2024, 11:27:35 AM
    Author     : Robin Guan
    Purpose    : Retailer homepage
--%>

<%@page import="com.fwrp.models.User"%>
<%@ include file="./header.jsp" %>
<body>
    <%@ include file="./nav.jsp" %>
    <%@ include file="./messageBar.jsp" %>
    <div class="container">
        <div class="header">
            <% 
                User thisUser = (User) session.getAttribute("user");
                String type = "Unknown"; // ???
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
        <div class="actions">
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
    </div>
    <%@ include file="./footer.jsp" %>
</body>
</html>
<style>
    .container {
        padding: 20px;
    }

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
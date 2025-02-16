<%-- 
    Document   : charity.jsp
    Purpose:  home page for Charity role with functions: Donation List, Transaction and Subscription
    Author     : Siqian Liu, Ke Yan
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
        <div class="actions" style="margin-bottom:5px">
            <form action="${pageContext.request.contextPath}/CharityController" method="post">
                <input type="hidden" name="action" value="checkInventory" />
                <button type="submit">Donation List</button>
            </form>
        </div>
        <div class="actions" style="margin-bottom:5px">
            <form action="${pageContext.request.contextPath}/CharityController" method="post">
                <input type="hidden" name="action" value="checkTransaction" />
                <button type="submit">Transaction</button>
            </form>
        </div>
        <div class="actions">
            <form action="${pageContext.request.contextPath}/UserController" method="post">
                <input type="hidden" name="action" value="manageSubscription" />
                <button type="submit">Subscription</button>
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
        gap: 10px;
    }

    .actions form {
        margin: 0;
        width: 250px;
        margin: 0 auto;
    }

    .actions button {
        background-color: #E0F7FA;
        border: 1px solid #B2EBF2;
        border-radius: 4px;
        color: #00796B;
        font-size: 16px;
        padding: 10px 15px;
        cursor: pointer;
        text-align: center;
        width: 100%;
    }

    .actions button:hover {
        background-color: #B2EBF2;
    }
</style>
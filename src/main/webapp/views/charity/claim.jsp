<%-- 
    Document   : claim
    Created on : Jul 30, 2024, 3:00:37 AM
    Author     : Ke Yan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
<%@ include file="../header.jsp" %>
<body>
    <%@ include file="../nav.jsp" %>
    <%@ include file="../messageBar.jsp" %>
    <div class="container">
        <div class="header">
            <center><h2>Create Claim</h2></center>
        </div>
        <div class="form-section">                            
            <form action="${pageContext.request.contextPath}/CharityController" method="post">
                <% 
                    String[] claimData= (String[]) request.getAttribute("claimData");
                    String foodIdValue = "";
                    String quantityValue = "";                   
                    if(claimData != null && claimData.length > 0){
                        foodIdValue = claimData[0];
                        quantityValue = claimData[1];                        
                    }                        
                %>
                <input class="input" type="hidden" name="action" value="storeNewFood" />
                
                <label for="name">Food ID:</label>
                <input class="input" type="number" id="foodId" name="foodId" value="<%= foodIdValue %>" required />

                <label for="expireDays">Quantity:</label>
                <input class="input" type="number" id="quantity" name="quantity" value="<%= quantityValue %>" min="1" required />
                
                <button type="submit">Create</button>
            </form>
        </div>
    </div>
    <%@ include file="../footer.jsp" %>
</body>
</html>
<style>
    .container {
        padding: 20px;
    }

    .header {
        margin-bottom: 20px;
    }

    .form-section {
        display: flex;
        flex-direction: column;
        gap: 10px; 
    }
    
    .form-section form {
        margin: 0;
        width: 250px; /* ?????????? */
        margin: 0 auto; /* ???? */
    }

    h2, h4 {
        color: #00796B; /* ??????????? */
    }

    label {
        display: block;
        margin: 10px 0 5px; /* ?????? */
        font-weight: bold;
    }

    .form-section input, .form-section button {
        display: block;
        margin: 10px 0;
        padding: 10px;
        width: 100%;
    }
    .input {
        width: 50%; /* ???????????????? */
        padding: 10px;
        margin-bottom: 15px; /* ??????? */
        border: 1px solid #B2EBF2; /* ???? */
        border-radius: 4px; /* ???? */
    }

    button {
        background-color: #E0F7FA; /* ???????footer????? */
        border: 1px solid #B2EBF2; /* ??????????? */
        border-radius: 4px;
        color: #00796B; /* ???? */
        font-size: 16px;
        padding: 10px 15px; /* ????? */
        cursor: pointer;
        text-align: center;
        width: 100%; /* ????????? */
        margin-top: 10px; /* ?????? */
    }

    button:hover {
        background-color: #B2EBF2; /* ?????????? */
    }

</style>
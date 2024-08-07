<%-- 
    Document   : add.jsp
    Created on : Jul 27, 2024, 11:00:56 AM
    Author     : robin
--%>


<%@ include file="../header.jsp" %>
<body>
    <%@ include file="../nav.jsp" %>
    <%@ include file="../messageBar.jsp" %>
    <div class="container">
        <div class="header">
            <center><h2>Add New Food Item</h2></center>
        </div>
        <div class="form-section">                            
            <form action="${pageContext.request.contextPath}/RetailerController" method="post">
                <% 
                    String[] foodData= (String[]) request.getAttribute("foodData");
                    String nameValue = "";
                    String expireDaysValue = "";
                    String unitpriceValue = "";
                    String discountValue = "";
                    if(foodData != null && foodData.length > 0){
                        nameValue = foodData[0];
                        expireDaysValue = foodData[1];
                        unitpriceValue = foodData[2];
                        discountValue = foodData[3];
                    }                        
                %>
                <input class="input" type="hidden" name="action" value="storeNewFood" />
                
                <label for="name">Food Name:</label>
                <input class="input" type="text" id="name" name="name" value="<%= nameValue %>" required />

                <label for="expireDays">Expire Days:</label>
                <input class="input" type="number" id="expireDays" name="expireDays" value="<%= expireDaysValue %>" min="1" required />

                <label for="unitPrice">Unit Price:</label>
                <input class="input" type="number" id="unitPrice" name="unitPrice" step="0.01" value="<%= unitpriceValue %>" min="0" required />

                <label for="discount">Discount:</label>
                <input class="input" type="number" id="discount" name="discount" step="0.01" value="<%= discountValue %>" min="0" max="1" required />

                <button type="submit">Add</button>
            </form>
        </div>
    </div>
    <%@ include file="../footer.jsp" %>
</body>
</html>
<style>
    .container {
        padding: 20px;
        padding-top: 80px;
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

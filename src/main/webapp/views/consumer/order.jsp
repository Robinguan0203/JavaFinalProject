<%-- 
    Document   : order
    Created on : Aug 4, 2024, 6:16:37 AM
    Author     : Ke Yan
--%>

<html>
<%@ include file="../header.jsp" %>
<body>
    <%@ include file="../nav.jsp" %>
    <style>
        .message-bar{
            position: fixed;
            z-index: 9999;
            margin-top: 20px;
        }
    </style>
    <%@ include file="../messageBar.jsp" %>
    <div class="container">
        <div class="header">
            <center><h2>Create Order</h2></center>
        </div>
        <div class="form-section">                            

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
    .message-bar{
        position: fixed;
        z-index: 9999;
        margin-top: 20px;
    }
</style>

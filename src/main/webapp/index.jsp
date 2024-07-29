<%@ include file="./views/header.jsp" %>
<body>
    <%@ include file="./views/nav.jsp" %>
    <%@ include file="./views/messageBar.jsp" %>
    <center><h1> Home - Food Waste Reduction Platform </h1></center>
    
    <div class="form-container">
        <form class="form-body" action="${pageContext.request.contextPath}/UserController" method="post">
            <input type="hidden" name="action" value="login" />
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required />
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required />
            </div>
            <button type="submit">Login</button>
        </form>
    </div>

    <div class="register-container">
        <p>Don't have an account?</p>
        <form action="${pageContext.request.contextPath}/UserController" method="post">
            <input type="hidden" name="action" value="openRegisterPage" />
            <button type="submit">Register</button>
        </form>
    </div>
    <%@ include file="./views/footer.jsp" %>
</body>
</html>
<style>
    body {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh; /* Ensure body takes full height of viewport */
        margin: 0; /* Remove default margin */
        height: 90vh; /* Full viewport height */
    }

    h1 {
        margin-top: 20px;
    }

    .form-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        margin-top: 20px;
        width: 100%;
    }
    
    .form-body {
        display: flex;
        flex-direction: column;
        align-items: center; /* Center-align the form items */
        width: 30%; /* Set a width for the form */
    }

    .form-group {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        margin-bottom: 15px;
        width:100%;
    }

    label {
        margin-bottom: 5px;
    }

    input {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
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
        width: 125px; /* ????????? */
        margin-top: 10px; /* ?????? */
    }

    button:hover {
        background-color: #B2EBF2; /* ?????????? */
    }

    .register-container {
        display: flex;
        align-items: center;
        justify-content: center;
        margin-top: 20px;
    }

    .register-container p {
        margin-right: 10px;
    }

    .register-container form {
        display: inline;
    }
</style>
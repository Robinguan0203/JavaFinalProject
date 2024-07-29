<%-- 
    Document   : register
    Created on : Jul 29, 2024, 12:35:33 PM
    Author     : robin
--%>

<%@ include file="../header.jsp" %>
<script type="text/javascript">
    function validateForm() {
        var password = document.forms["registerForm"]["password"].value;
        var rePassword = document.forms["registerForm"]["repassword"].value;
        if (password != rePassword) {
            alert("Passwords do not match.");
            return false;
        }
        return true;
    }

    function toggleOrganizationField() {
        var userType = document.getElementById("type").value;
        var organizationField = document.getElementById("organizationField");
        if (userType == 3) { // Consumer
            organizationField.style.display = "none";
            document.getElementById("organization").value = ""; // Clear the field value
        } else {
            organizationField.style.display = "block";
        }
    }
</script>
</head>
<body>
    <%@ include file="../nav.jsp" %>
    <%@ include file="../messageBar.jsp" %>
    <center><h1>Registration - Food Waste Reduction Platform</h1></center>
    <form name="registerForm" action="${pageContext.request.contextPath}/UserController" method="post" onsubmit="return validateForm();">
        <% 
            String[] userData= (String[]) request.getAttribute("userData");
            String firstname = "";
            String lastname = "";
            String phone = "";
            String email = "";
            String type = "";
            String organization = "";
            
            if(userData != null && userData.length > 0){
                firstname = userData[0];
                lastname = userData[1];
                phone = userData[2];
                email = userData[3];
                type = userData[4];
                organization = userData[5];
            }                        
        %>
        <input type="hidden" name="action" value="register" />

        <label for="firstname">First Name:</label>
        <input class="input" type="text" id="firstname" name="firstname" value="<%= firstname %>" required><br>

        <label for="lastname">Last Name:</label>
        <input class="input" type="text" id="lastname" name="lastname" value="<%= lastname %>" required><br>

        <label for="phone">Phone:</label>
        <input class="input" type="text" id="phone" name="phone" value="<%= phone %>"required><br>

        <label for="email">Email:</label>
        <input class="input" type="email" id="email" name="email" value="<%= email %>" required><br>

        <label for="password">Password:</label>
        <input class="input" type="password" id="password" name="password" required><br>

        <label for="repassword">Re-enter Password:</label>
        <input class="input" type="password" id="repassword" name="repassword" required><br>

        <label for="type">Type:</label>
        <select class="input" id="type" name="type" onchange="toggleOrganizationField();" required>
            <option value="">Select Type</option>
            <option value="1" <% if ("1".equals(type)) { %> selected <% } %>>Retailer</option>
            <option value="2" <% if ("2".equals(type)) { %> selected <% } %>>Charity</option>
            <option value="3" <% if ("3".equals(type)) { %> selected <% } %>>Consumer</option>
        </select><br>

        <div id="organizationField" style="display:<%= "2".equals(type) ? "block" : "none" %>;">
            <label for="organization">Organization Name:</label>
            <input class="input" type="text" id="organization" name="organization" value="<%= organization %>"><br>
        </div>

        <button type="submit">Register</button>
    </form>
    <%@ include file="../footer.jsp" %>
</body>
</html>
<%@ include file="../footer.jsp" %>
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

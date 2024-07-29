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
    <center><h1>Register - Food Waste Reduction Platform</h1></center>
    <form name="registerForm" action="${pageContext.request.contextPath}/UserController" method="post" onsubmit="return validateForm();">
        <input type="hidden" name="action" value="register" />

        <label for="firstname">First Name:</label>
        <input type="text" id="firstname" name="firstname" required><br>

        <label for="lastname">Last Name:</label>
        <input type="text" id="lastname" name="lastname" required><br>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <label for="repassword">Re-enter Password:</label>
        <input type="password" id="repassword" name="repassword" required><br>

        <label for="type">Type:</label>
        <select id="type" name="type" onchange="toggleOrganizationField();" required>
            <option value="">Select Type</option>
            <option value="1">Retailer</option>
            <option value="2">Charity</option>
            <option value="3">Consumer</option>
        </select><br>

        <div id="organizationField" style="display:none;">
            <label for="organization">Organization Name:</label>
            <input type="text" id="organization" name="organization"><br>
        </div>

        <button type="submit">Register</button>
    </form>
    <%@ include file="../footer.jsp" %>
</body>
</html>
<%@ include file="../footer.jsp" %>

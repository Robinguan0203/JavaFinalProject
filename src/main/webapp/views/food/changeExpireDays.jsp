<%-- 
    Document   : changeFoodExpireDays
    Created on : Jul 27, 2024, 12:37:11 PM
    Author     : robin
--%>

<%@page import="java.util.ArrayList"%>
<%@ include file="../header.jsp" %>
<%@ page import="com.fwrp.models.Food" %>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            width: 80%;
            margin: 0 auto;
        }
        .header, .form-section, .logout {
            margin-bottom: 20px;
        }
        .form-section form {
            margin-top: 20px;
        }
        .form-section input, .form-section select, .form-section button {
            display: block;
            margin: 10px 0;
            padding: 10px;
            width: 100%;
        }
        .form-section button {
            width: auto;
        }
        .message {
            color: red;
            font-weight: bold;
            margin-bottom: 20px;
        }
    </style>
</head>
    <body>
        <%@ include file="../nav.jsp" %>
        <%@ include file="../messageBar.jsp" %>
        <div class="container">
            <center><h2>Change Food Expire Days</h2></center>
            <div class="form-section">            
                <form action="RetailerController" method="post">
                    <input type="hidden" name="action" value="storeExpireDays" />
                    
                    <label for="food">Select Food:</label>
                    <select class="input" id="food" name="foodId" required>
                        <option value="">Please Select</option>
                        <%
                            ArrayList<Food> foods = (ArrayList<Food>) request.getAttribute("foods");
                            if (foods != null) {
                                for (Food food : foods) {
                                    out.println("<option value='" + food.getId() + "'>" + food.getName() + "</option>");
                                }
                            }
                        %>
                    </select>
                    
                    <label for="expireDays">New Expire Days</label>
                    <input class="input" type="number" id="expireDays" name="expireDays" min="1" step="1" required />

                    <button type="submit">Submit</button>
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

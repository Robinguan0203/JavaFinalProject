<%-- 
    Document   : add.jsp
    Created on : Jul 27, 2024, 11:00:56 AM
    Author     : robin
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.fwrp.models.RetailerTransaction"%>
<%@page import="com.fwrp.constants.TransactionTypeConstant"%>
<%@page import="com.fwrp.models.Transaction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.fwrp.models.Retailer"%>
<%@page import="com.fwrp.models.Food"%>
<%@page import="com.fwrp.models.ExpireInfo"%>
<%@ include file="../header.jsp" %>
        
    </head>
    <body>
        <%@ include file="../nav.jsp" %>
        <%@ include file="../messageBar.jsp" %>
        <div class="container">
            <center><h2>View Food Transactions</h2></center>
            <div class="form-section">    
                <% 
                    ArrayList<Transaction> transactions= (ArrayList<Transaction>) request.getAttribute("transactions");
                    if (transactions == null || transactions.isEmpty()) { 
                %>
                    <p>No Food Transactions Found</p>
                <% 
                    } else { 
                %>
                    <table>
                        <colgroup>
                            <col style="width: 5%;">
                            <col style="width: 10%;">
                            <col style="width: 15%;">
                            <col style="width: 15%;">
                            <col style="width: 10%;">
                            <col style="width: 10%;">
                            <col style="width: 10%;">
                            <col style="width: 25%;">
                        </colgroup>
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Date</th>
                                <th>Food Name</th>
                                <th>Operation Type</th>
                                <th>Normal</th>
                                <th>For Discount</th>
                                <th>For Donation</th>
                                <th>Operated By</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%! int index = 0; %>
                            <% 
                            SimpleDateFormat dateStringFormat = new SimpleDateFormat("yyyy-MM-dd");
                            for (Transaction transaction: transactions) {   
                                Date date = transaction.getDate();
                                String dateString = dateStringFormat.format(date);
                                String foodName = transaction.getFood().getName();
                                String type;
                                String userName = transaction.getUser().getFirstName()+" "+transaction.getUser().getLastName()+ " / "+transaction.getUser().getOrganization();
                                int qtyNormal = 0;
                                int qtyDiscount = 0;
                                int qtyDonation = 0;
                                switch(transaction.getType()){
                                case TransactionTypeConstant.CLAIM:
                                    type = "CLAIM";
                                    com.fwrp.models.ClaimTransaction thisTransaction2 = (com.fwrp.models.ClaimTransaction) transaction;
                                    qtyNormal = thisTransaction2.getQtyNormal();
                                    qtyDiscount = thisTransaction2.getQtyDiscount();
                                    qtyDonation = thisTransaction2.getQtyDonation();
                                    break;
                                case TransactionTypeConstant.ORDER:
                                    type = "ORDER";
                                    break;
                                case TransactionTypeConstant.INVENTORY_CHANGE:
                                    type = "Retailer Operation";
                                    RetailerTransaction thisTransaction = (RetailerTransaction) transaction;
                                    qtyNormal = thisTransaction.getQtyNormal();
                                    qtyDiscount = thisTransaction.getQtyDiscount();
                                    qtyDonation = thisTransaction.getQtyDonation();
                                    break;
                                default:
                                    type = "Unknow";
                                    out.print("Wrong Transaction Types!");                                    
                                }
                            %>
                                <tr>
                                    <td><%= index + 1 %></td>
                                    <td><%= dateString %></td>
                                    <td><%= foodName %></td>
                                    <td><%= type %></td>
                                    <td><%= qtyNormal %></td>
                                    <td><%= qtyDiscount %></td>
                                    <td><%= qtyDonation %></td>
                                    <td><%= userName %></td>
                                </tr>
                            <% 
                                index++; 
                            } 
                            %>
                        </tbody>
                    </table>
                    <p>${count} items.</p>
                <% 
                    } 
                %>                    
            </div>
        </div>
        <%@ include file="../footer.jsp" %>
    </body>
</html>
<style>
    body {
        font-family: Arial, sans-serif;
    }
    .container {
        width: 80%;
        margin: 0 auto;
        margin-top: 100px; /* ???????????? */
    }
    .header, .form-section, .logout {
        margin-bottom: 20px;
    }
    .form-section table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
        table-layout: fixed; /* Ensure fixed layout */
    }
    .form-section table, .form-section th, .form-section td {
        border: 1px solid black;
        padding: 10px;
        text-align: center;
    }
    .form-section button {
        margin-top: 20px;
        padding: 10px 20px;
    }
    .message {
        color: red;
        font-weight: bold;
        margin-bottom: 20px;
    }
</style>

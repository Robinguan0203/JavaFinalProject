<%-- 
    Document   : discountInventory
    Created on : Aug 4, 2024, 6:16:53 AM
    Author     : Ke Yan
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../header.jsp" %>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Discount Inventory</title>--%>
<%--&lt;%&ndash;    <meta content="width=device-width, initial-scale=1.0" name="viewport">&ndash;%&gt;--%>
<%--    <link href="${pageContext.request.contextPath}/static/css/fwrp.css" rel="stylesheet" type="text/css"/>--%>
<%--</head>--%>
<link href="${pageContext.request.contextPath}/static/css/fwrp.css" rel="stylesheet" type="text/css"/>
<body>
<%@ include file="../nav.jsp" %>
<%@ include file="../messageBar.jsp" %>
<div class="container mx-auto px-12 py-6">
    <table class="w-full p-4 table-auto border-collapse border border-slate-400">
        <caption class="caption-top text-2xl mb-4">
            Discount Inventory
        </caption>
        <thead>
        <tr>
            <th class="border border-slate-300 p-2">Inventory ID</th>
            <th class="border border-slate-300 p-2">Food Name</th>
            <th class="border border-slate-300 p-2">Inventory Discount Quantity</th>
            <th class="border border-slate-300 p-2">Inventory Donation Quantity</th>
            <th class="border border-slate-300 p-2">Inventory Normal Quantity</th>
            <th class="border border-slate-300 p-2">Operate</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="i" items="${inventoryList}">
            <tr>
                <td class="border border-slate-300 p-2">#${i.getId()}</td>
                <td class="border border-slate-300 p-2">${i.getFood().getName()}</td>
                <td class="border border-slate-300 p-2">${i.getQtyDiscount()}</td>
                <td class="border border-slate-300 p-2">${i.getQtyDonation()}</td>
                <td class="border border-slate-300 p-2">${i.getQtyNormal()}</td>
                <td class="border border-slate-300 p-2">
                    <form action="${pageContext.request.contextPath}/ConsumerController" method="post">
                        <input type="hidden" name="action" value="showOrder" />
                        <input type="hidden" name="id" value="${i.getId()}" />
                        <input type="hidden" name="foodId" value="${i.getFood().getId()}" />
                        <input type="hidden" name="foodName" value="${i.getFood().getName()}" />
                        <input type="hidden" name="qtyDiscount" value="${i.getQtyDiscount()}" />
                        <button class="bg-green-100 hover:bg-green-300 p-4 m-2 rounded-md" type="submit">Order</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="container mx-auto px-12 py-6 mx-12">
    <table class="w-full p-4 table-auto border-collapse border border-slate-400">
        <caption class="caption-top text-2xl mb-4">
            Order Information
        </caption>
        <thead>
        <tr>
            <th class="border border-slate-300 p-2">Id</th>
            <th class="border border-slate-300 p-2">Food Name</th>
            <th class="border border-slate-300 p-2">Order Date</th>
            <th class="border border-slate-300 p-2">Order quantity</th>
<%--            <th class="border border-slate-300 p-2">Operate</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="i" items="${claimList}">
            <tr>
                <td class="border border-slate-300 p-2">#${i.getId()}</td>
                <td class="border border-slate-300 p-2">${i.getFood().getName()}</td>
                <td class="border border-slate-300 p-2">${i.getDate()}</td>
                <td class="border border-slate-300 p-2">${i.getQtyDiscount()}</td>
<%--                <td class="border border-slate-300 p-2">--%>
<%--                    <form action="${pageContext.request.contextPath}/ConsumerController" method="post">--%>
<%--                        <input type="hidden" name="action" value="deleteOrder" />--%>
<%--                        <input type="hidden" name="id" value="${i.getId()}" />--%>
<%--                        <button class="bg-red-100 hover:bg-red-300 p-4 m-2 rounded-md" type="submit">Delete</button>--%>
<%--                    </form>--%>
<%--                </td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>

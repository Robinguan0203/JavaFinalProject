<%--
  Created by IntelliJ IDEA.
  User: Siqian
  Date: 2024/7/28
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Donation Inventory</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link href="${pageContext.request.contextPath}/static/css/fwrp.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container mx-auto px-12 py-6">
    <table class="w-full p-4 table-auto border-collapse border border-slate-400">
        <caption class="caption-top text-2xl mb-4">
            Donation Inventory
        </caption>
        <thead>
        <tr>
            <th class="border border-slate-300 p-2">Inventory ID</th>
            <th class="border border-slate-300 p-2">Food Name</th>
            <th class="border border-slate-300 p-2">Inventory Discount</th>
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
                    <form action="${pageContext.request.contextPath}/CharityController" method="post">
                        <input type="hidden" name="action" value="showClaim" />
                        <input type="hidden" name="id" value="${i.getId()}" />
                        <input type="hidden" name="foodId" value="${i.getFood().getId()}" />
                        <input type="hidden" name="foodName" value="${i.getFood().getName()}" />
                        <input type="hidden" name="qtyDonation" value="${i.getQtyDonation()}" />
                        <button class="bg-green-100 hover:bg-green-300 p-4 m-2 rounded-md" type="submit">Claim</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="container mx-auto px-12 py-6">
    <table class="w-full p-4 table-auto border-collapse border border-slate-400">
        <caption class="caption-top text-2xl mb-4">
            Claim Information
        </caption>
        <thead>
        <tr>
            <th class="border border-slate-300 p-2">Id</th>
            <th class="border border-slate-300 p-2">Food Name</th>
            <th class="border border-slate-300 p-2">Claim Date</th>
            <th class="border border-slate-300 p-2">Claim quantity</th>
            <th class="border border-slate-300 p-2">Operate</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="i" items="${claimList}">
            <tr>
                <td class="border border-slate-300 p-2">#${i.getId()}</td>
                <td class="border border-slate-300 p-2">${i.getFood().getName()}</td>
                <td class="border border-slate-300 p-2">${i.getDate()}</td>
                <td class="border border-slate-300 p-2">${i.getQtyDonation()}</td>
                <td class="border border-slate-300 p-2">
                    <form action="${pageContext.request.contextPath}/CharityController" method="post">
                        <input type="hidden" name="action" value="deleteClaim" />
                        <input type="hidden" name="id" value="${i.getId()}" />
                        <button class="bg-red-100 hover:bg-red-300 p-4 m-2 rounded-md" type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers;

import com.fwrp.dbService.InventoryDbService;
import com.fwrp.models.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ke Yan
 */
@WebServlet("/CharityController")
public class CharityController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); 
        
        if (session == null || session.getAttribute("user") == null) {
            request.setAttribute("errorMessage", "You must be logged in first.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return; 
        }
        
        Charity charity = this.getCharityFromSession(request);
        request.setAttribute("charity", charity);

        String action = request.getParameter("action");
        switch (action) {
            case "checkInventory":
                try {
                    checkInventory(request, response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "storeClaim":
                storeClaim(request,response);
                break;
            case "showClaim":
                showClaim(request,response);
            default:
                response.getWriter().println("Unknown action");
        }
    }
    
    private Charity getCharityFromSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Charity) session.getAttribute("user");
        }
        return null;
    }   

    private void checkInventory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        InventoryDbService dbService = new InventoryDbService();
        RequestDispatcher dispatcher;
        try {
            List<Inventory> inventoryList = dbService.getDonationInventories();
            request.setAttribute("inventoryList", inventoryList);
            dispatcher = request.getRequestDispatcher("/views/charity/donationInventory.jsp");
        }catch(ClassNotFoundException e){
            dispatcher = request.getRequestDispatcher("/views/charity.jsp");
        }catch(SQLException e){
            dispatcher = request.getRequestDispatcher("/views/charity.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void showClaim(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //int id=Integer.parseInt(req.getParameter("id"));
         req.setAttribute("id",req.getParameter("id"));
         req.setAttribute("foodName",req.getParameter("foodName"));
         req.setAttribute("qtyDonation",req.getParameter("qtyDonation"));
         RequestDispatcher dispatcher = req.getRequestDispatcher("/views/charity/claim.jsp");
         dispatcher.forward(req, resp);
         resp.getWriter().println("Create claim functionality");
    }

    private void storeClaim(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //int id=Integer.parseInt(req.getParameter("id"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/charity/donationInventory.jsp");
        dispatcher.forward(req, resp);
        resp.getWriter().println("Create claim functionality");
    }

    private String[] getClaimInputHistory(HttpServletRequest request) {
        String[] inputHistory = new String[4];
        inputHistory[0] = request.getParameter("name");
        inputHistory[1] = request.getParameter("expireDays");
        inputHistory[2] = request.getParameter("unitPrice");
        inputHistory[3] = request.getParameter("discount");

        return inputHistory;
    }

}



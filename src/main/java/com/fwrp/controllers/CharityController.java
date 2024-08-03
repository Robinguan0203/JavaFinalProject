/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers;

import com.fwrp.dataaccess.dto.SubscriptionDTO;
import com.fwrp.dbService.ClaimDbService;
import com.fwrp.dbService.FoodDbService;
import com.fwrp.dbService.InventoryDbService;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.NegativeInventoryException;
import com.fwrp.models.*;
import com.fwrp.services.CharityService;
import com.fwrp.services.ConsumerService;
import com.fwrp.services.RetailerService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                try {
                    storeClaim(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (NegativeInventoryException ex) {
                Logger.getLogger(CharityController.class.getName()).log(Level.SEVERE, null, ex);
            }
                break;

            case "showClaim":
                showClaim(request,response);
            case "checkTransaction":
                checkTransaction(request,response);
            case "deleteClaim":
                try {
                    deleteClaim(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
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
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            request.setAttribute("errorMessage", "You must be logged in first.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        Charity charity = this.getCharityFromSession(request);

        InventoryDbService dbService = new InventoryDbService();
        ClaimDbService claimService = new ClaimDbService();
        RequestDispatcher dispatcher;
        try {
            List<Inventory> inventoryList = dbService.getDonationInventories();
            request.setAttribute("inventoryList", inventoryList);

            List<Claim> claimList = claimService.getClaimByUserId(charity.getId());
            request.setAttribute("inventoryList", inventoryList);
            request.setAttribute("claimList", claimList);
            dispatcher = request.getRequestDispatcher("/views/charity/donationInventory.jsp");
        }catch(ClassNotFoundException | SQLException e){
            request.setAttribute("errorMessage", e.getMessage());
            dispatcher = request.getRequestDispatcher("/views/charity.jsp");
            dispatcher.forward(request, response);
            return;
            //dispatcher = request.getRequestDispatcher("/views/charity.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void showClaim(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //int id=Integer.parseInt(req.getParameter("id"));

         req.setAttribute("id",req.getParameter("id"));
         req.setAttribute("foodId",req.getParameter("foodId"));
         req.setAttribute("foodName",req.getParameter("foodName"));
         req.setAttribute("qtyDonation",req.getParameter("qtyDonation"));
         RequestDispatcher dispatcher = req.getRequestDispatcher("/views/charity/claim.jsp");
         dispatcher.forward(req, resp);
         resp.getWriter().println("Create claim functionality");
    }

    private void deleteClaim(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException, ServletException {
        int id=Integer.parseInt(req.getParameter("id"));

        CharityService charityService = new CharityService();
        charityService.deleteClaimById(id);

        resp.getWriter().println("Delete claim successfully");
        checkInventory(req,resp);
    }


    private void storeClaim(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException, ClassNotFoundException, NegativeInventoryException {
        //int id=Integer.parseInt(req.getParameter("id"));
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            req.setAttribute("errorMessage", "You must be logged in first.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        Charity charity = this.getCharityFromSession(req);
        int foodId=Integer.parseInt(req.getParameter("foodId"));

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date claimDate = Date.valueOf(formatter.format(localDateTime));


        int qtyDonation=Integer.parseInt(req.getParameter("qtyDonation"));

        int claimQty=Integer.parseInt(req.getParameter("claimQty"));

        CharityService charityService = new CharityService();
        //FoodDbService foodDbService = new FoodDbService();
        //Food food=foodDbService.getFoodById(foodId);
        try {
            charityService.storeNewClaim(foodId, claimQty, charity);                  

            checkInventory(req, resp);

        } catch (IOException |ClassNotFoundException |ServletException |DataInsertionFailedException e) {
            throw new RuntimeException(e);
        } 

        //dispatcher.forward(req, resp);
        resp.getWriter().println("Create claim successfully!");

    }

    public void checkTransaction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CharityService charityService = new CharityService();
        Charity charity = this.getCharityFromSession(request);

        try {
            ArrayList<Transaction> transactions = charityService.getTransactionsByUserId(charity.getId());
            int count = transactions.size();
            request.setAttribute("count", count);
            request.setAttribute("transactions", transactions);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/transactions.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/charity.jsp");
            dispatcher.forward(request, response);
            return;
        }
    }
    private Charity getCharityrFromSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Charity) session.getAttribute("user");
        }
        return null;
    }
    private void manageSubscription(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/manageSubscription.jsp");
        Charity charity=this.getCharityrFromSession(req);
        ConsumerService consumerService = new ConsumerService();
        try {
            List<SubscriptionDTO> subscriptionDTOList=consumerService.getAllMethodsByUserId(charity.getId());
            req.setAttribute("subscriptionList", subscriptionDTOList);
        } catch(ClassNotFoundException | SQLException ee){
            req.setAttribute("errorMessage", ee.getMessage());
            dispatcher.forward(req, resp);
        }
        dispatcher.forward(req, resp);
        resp.getWriter().println("Manage Subscription");
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



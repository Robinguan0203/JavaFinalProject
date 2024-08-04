package com.fwrp.controllers;

import com.fwrp.dataaccess.dto.SubscriptionDTO;
import com.fwrp.dbService.OrderDbService;
import com.fwrp.dbService.FoodDbService;
import com.fwrp.dbService.InventoryDbService;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.NegativeInventoryException;
import com.fwrp.models.*;
import com.fwrp.services.CharityService;
import com.fwrp.services.ConsumerService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
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

@WebServlet("/ConsumerController")
public class ConsumerController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); 
        
        if (session == null || session.getAttribute("user") == null) {
            request.setAttribute("errorMessage", "You must be logged in first.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return; 
        }
        
        Consumer consumer = this.getConsumerFromSession(request);
        request.setAttribute("consumer", consumer);

        String action = request.getParameter("action");
        switch (action) {
            case "checkInventory":
                try {
                    checkInventory(request, response);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "storeOrder":
                try {
                    storeOrder(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (NegativeInventoryException ex) {
                Logger.getLogger(ConsumerController.class.getName()).log(Level.SEVERE, null, ex);
            }
                break;

            case "showOrder":
                showOrder(request,response);
            case "checkTransaction":
                checkTransaction(request,response);
            case "deleteOrder":
                try {
                    deleteOrder(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            default:
                response.getWriter().println("Unknown action");
        }
    }
    
    private Consumer getConsumerFromSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Consumer) session.getAttribute("user");
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

        Consumer consumer = this.getConsumerFromSession(request);

        InventoryDbService dbService = new InventoryDbService();
        OrderDbService orderService = new OrderDbService();
        RequestDispatcher dispatcher;
        try {
            List<Inventory> inventoryList = dbService.getDiscountInventories();
            request.setAttribute("inventoryList", inventoryList);

            List<Order> orderList = orderService.getOrderByUserId(consumer.getId());
            request.setAttribute("inventoryList", inventoryList);
            request.setAttribute("orderList", orderList);
            dispatcher = request.getRequestDispatcher("/views/order/orderInventory.jsp");
        }catch(ClassNotFoundException | SQLException e){
            request.setAttribute("errorMessage", e.getMessage());
            dispatcher = request.getRequestDispatcher("/views/consumer.jsp");
            dispatcher.forward(request, response);
            return;
            //dispatcher = request.getRequestDispatcher("/views/consumer.jsp");
        }
        dispatcher.forward(request, response);
    }

    private void showOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //int id=Integer.parseInt(req.getParameter("id"));

         req.setAttribute("id",req.getParameter("id"));
         req.setAttribute("foodId",req.getParameter("foodId"));
         req.setAttribute("foodName",req.getParameter("foodName"));
         req.setAttribute("qtyDiscount",req.getParameter("qtyDiscount"));
         RequestDispatcher dispatcher = req.getRequestDispatcher("/views/consumer/order.jsp");
         dispatcher.forward(req, resp);
         resp.getWriter().println("Create order functionality");
    }

    private void deleteOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException, ServletException {
        int id=Integer.parseInt(req.getParameter("id"));

        ConsumerService consumerService = new ConsumerService();
        consumerService.deleteOrderById(id);

        resp.getWriter().println("Delete order successfully");
        checkInventory(req,resp);
    }


    private void storeOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException, ClassNotFoundException, NegativeInventoryException {
        //int id=Integer.parseInt(req.getParameter("id"));
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            req.setAttribute("errorMessage", "You must be logged in first.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        Consumer consumer = this.getConsumerFromSession(req);
        int foodId=Integer.parseInt(req.getParameter("foodId"));

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date orderDate = Date.valueOf(formatter.format(localDateTime));


        int qtyDiscount=Integer.parseInt(req.getParameter("qtyDiscount"));

        int orderQty=Integer.parseInt(req.getParameter("orderQty"));

        ConsumerService consumerService = new ConsumerService();
        //FoodDbService foodDbService = new FoodDbService();
        //Food food=foodDbService.getFoodById(foodId);
        try {
            if(orderQty>qtyDiscount){
                req.setAttribute("errorMessage", "Order quantity should less than remaining discount quantity.");
                showOrder(req,resp);
                //req.getRequestDispatcher("/views/consumer/order.jsp").forward(req, resp);
            }else if(orderQty<1){
                req.setAttribute("errorMessage", "Order quantity should greater than zero.");
                showOrder(req,resp);
                //req.getRequestDispatcher("/views/consumer/order.jsp").forward(req, resp);
            }
            else{
                consumerService.storeNewOrder(foodId, orderQty, consumer);
                checkInventory(req, resp);
            }
        } catch (IOException |ClassNotFoundException |ServletException |DataInsertionFailedException e) {
            throw new RuntimeException(e);
        } 

        //dispatcher.forward(req, resp);
//        resp.getWriter().println("Create order successfully!");

    }

    public void checkTransaction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConsumerService consumerService = new ConsumerService();
        Consumer consumer = this.getConsumerFromSession(request);

        try {
            ArrayList<Transaction> transactions = consumerService.getTransactionsByUserId(consumer.getId());
            int count = transactions.size();
            request.setAttribute("count", count);
            request.setAttribute("transactions", transactions);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/transactions.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/consumer.jsp");
            dispatcher.forward(request, response);
            return;
        }
    }
    private Consumer getConsumerrFromSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Consumer) session.getAttribute("user");
        }
        return null;
    }


    private String[] getOrderInputHistory(HttpServletRequest request) {
        String[] inputHistory = new String[4];
        inputHistory[0] = request.getParameter("name");
        inputHistory[1] = request.getParameter("expireDays");
        inputHistory[2] = request.getParameter("unitPrice");
        inputHistory[3] = request.getParameter("discount");

        return inputHistory;
    }

}

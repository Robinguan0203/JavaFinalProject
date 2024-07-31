/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers;

import com.fwrp.dataaccess.dto.SubscriptionDTO;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.models.Consumer;
import com.fwrp.services.ConsumerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ke Yan
 */
@WebServlet("/ConsumerController")
public class ConsumerController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            req.setAttribute("errorMessage", "You must be logged in first.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        Consumer consumer = this.getConsumerFromSession(req);
        req.setAttribute("consumer", consumer);

        String action = req.getParameter("action");
        switch (action) {
            case "manageSubscription":
                manageSubscription(req,resp);
            case "showAddSubscription":
                showAddSubscription(req, resp);
                break;
            case "addSubscription":
                addSubscription(req, resp);
                break;
            case "deleteSubscription":
                deleteSubscription(req, resp);
                break;
            default:
                resp.getWriter().println("Unknown action");
        }
    }

    private Consumer getConsumerFromSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (Consumer) session.getAttribute("user");
        }
        return null;
    }

    private void manageSubscription(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/manageSubscription.jsp");
        Consumer consumer=this.getConsumerFromSession(req);
        ConsumerService consumerService = new ConsumerService();
        try {
            List<SubscriptionDTO> subscriptionDTOList=consumerService.getAllMethodsByUserId(consumer.getId());
            req.setAttribute("subscriptionList", subscriptionDTOList);
        } catch(ClassNotFoundException | SQLException ee){
            req.setAttribute("errorMessage", ee.getMessage());
            dispatcher.forward(req, resp);
        }
        dispatcher.forward(req, resp);
        resp.getWriter().println("Manage Subscription");
    }

    private void showAddSubscription(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/addSubscription.jsp");

        dispatcher.forward(req, resp);
        resp.getWriter().println("Show Add Subscription");
    }

    private void addSubscription(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int method = Integer.parseInt(req.getParameter("method"));

        Consumer consumer=this.getConsumerFromSession(req);
        ConsumerService consumerService = new ConsumerService();
        try {
            consumerService.addSubscription(consumer.getId(),method);
        } catch(DataAlreadyExistsException ee){
            req.setAttribute("errorMessage", "Subscription Already Exist.");
        } catch (DataInsertionFailedException ex) {
            req.setAttribute("errorMessage", ex.getMessage());
        } catch (Exception e){
            req.setAttribute("errorMessage", e.getMessage());
        }
        manageSubscription(req,resp);
    }
    public enum MethodType {
        EMAIL(1, "EMAIL"),
        PHONE(2, "PHONE"),
        SYSTEM(3, "SYSTEM");

        private final int code;
        private final String description;

        MethodType(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

        public static String getDescriptionByCode(int code) {
            for (MethodType type : values()) {
                if (type.getCode() == code) {
                    return type.getDescription();
                }
            }
            return "Unknown";
        }
    }

    private void deleteSubscription(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));

        ConsumerService consumerService = new ConsumerService();
        try {
            consumerService.deleteSubscription(id);
        } catch (Exception ex) {
            req.setAttribute("errorMessage", ex.getMessage());
        }
        manageSubscription(req,resp);
    }
}



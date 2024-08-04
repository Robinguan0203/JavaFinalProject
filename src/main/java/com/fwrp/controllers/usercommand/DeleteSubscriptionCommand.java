/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.usercommand;

import com.fwrp.dataaccess.dto.SubscriptionDTO;
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.models.User;
import com.fwrp.services.UserService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command to delete a subscription for a user.
 * 
 * This class implements the IUserCommand interface and handles the 
 * execution of deleting a subscription by retrieving the subscription ID,
 * deleting the subscription, and managing the subscription view.
 * 
 * 
 * Author: Robin Guan
 * Version: 1.0
 * Since: 17.0.8
 */
public class DeleteSubscriptionCommand implements IUserCommand{
	
	 /**
     * Executes the command to delete a subscription.
     * 
     * @param req  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param resp The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        UserService userService = new UserService();
        try {
            userService.deleteSubscription(id);
        } catch (Exception ex) {
            req.setAttribute("errorMessage", ex.getMessage());
        }
        manageSubscription(req,resp);
    }
    
	/**
     * Manages the subscription view.
     * 
     * @param req  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param resp The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    private void manageSubscription(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/user/manageSubscription.jsp");
        User user=this.getUserFromSession(req);
        UserService userService = new UserService();
        try {
            List<SubscriptionDTO> subscriptionDTOList=userService.getAllMethodsByUserId(user.getId());
            req.setAttribute("subscriptionList", subscriptionDTOList);
        } catch(ClassNotFoundException | SQLException ee){
            req.setAttribute("errorMessage", ee.getMessage());
            dispatcher.forward(req, resp);
        }
        dispatcher.forward(req, resp);
        resp.getWriter().println("Manage Subscription");
    }
    
	/**
     * Retrieves the user from the session.
     * 
     * @param request The HttpServletRequest object that contains the request the client made to the servlet.
     * @return The user retrieved from the session, or null if no user is found.
     */
    private User getUserFromSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (User) session.getAttribute("user");
        }
        return null;
    }
}

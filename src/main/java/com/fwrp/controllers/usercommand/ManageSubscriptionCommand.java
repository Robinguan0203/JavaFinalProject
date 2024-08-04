/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.usercommand;

import com.fwrp.dataaccess.dto.SubscriptionDTO;
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
 *
 * @author robin
 */
public class ManageSubscriptionCommand implements IUserCommand{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    
    private User getUserFromSession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (User) session.getAttribute("user");
        }
        return null;
    }
}
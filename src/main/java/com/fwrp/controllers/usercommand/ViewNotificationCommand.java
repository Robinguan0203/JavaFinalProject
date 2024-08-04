/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.usercommand;

import com.fwrp.constants.UserTypeConstant;
import com.fwrp.models.Notification;
import com.fwrp.models.User;
import com.fwrp.services.UserService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command to view notifications based on the specified method.
 * 
 * This class implements the IUserCommand interface and handles the 
 * execution of viewing notifications by forwarding the request to 
 * the appropriate JSP page based on the user type.
 * 
 * Author: Robin Guan
 * Version: 1.0
 * Since: 17.0.8
 */
public class ViewNotificationCommand implements IUserCommand{
	
	/**
     * The notification method (e.g., phone, email, system).
     */
    private int method;
    
	/**
     * Constructs a ViewNotificationCommand with the specified method.
     * 
     * @param method The notification method.
     */
    public ViewNotificationCommand(int method){
        this.method = method;
    }
	
	/**
     * Executes the command to view notifications.
     * 
     * @param request  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        UserService userService = new UserService();        
        HttpSession session = request.getSession(false);
        User user = null;
        int count = 0;

        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        if (user != null) {
            try {
                ArrayList<Notification> notifications = userService.getNotifications(user, method);
                count = notifications.size();
                request.setAttribute("count", count);
                request.setAttribute("notifications", notifications);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/notification.jsp");
                dispatcher.forward(request, response);

            } catch (SQLException | ClassNotFoundException ex) {
                int userType = user.getType(); // 假设 getType() 返回的是 int
                switch (userType) {
                    case UserTypeConstant.RETAILER:
                        {
                            request.setAttribute("errorMessage", ex.getMessage());
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/retailer.jsp");
                            dispatcher.forward(request, response);
                            break;
                        }
                    case UserTypeConstant.CONSUMER:
                        {
                            request.setAttribute("errorMessage", ex.getMessage());
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/consumer.jsp");
                            dispatcher.forward(request, response);
                            break;
                        }
                    case UserTypeConstant.CHARITY:
                        {
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/charity.jsp");
                            dispatcher.forward(request, response);
                            break;
                        }
                    default:
                        // 处理未知用户类型
                        request.setAttribute("errorMessage", "Unknown user type.");
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                        break;
                }
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
    
}
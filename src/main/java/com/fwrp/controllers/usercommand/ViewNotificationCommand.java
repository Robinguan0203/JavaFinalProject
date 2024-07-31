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
 *
 * @author robin
 */
public class ViewNotificationCommand implements IUserCommand{
    private int method;
    
    public ViewNotificationCommand(int method){
        this.method = method;
    }
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
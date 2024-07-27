/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers;

import com.fwrp.constants.FileLocationConstant;
import com.fwrp.constants.UserTypeConstant;
import com.fwrp.dataaccess.DataSource;
import static com.fwrp.dataaccess.DataSource.openPropsFile;
import com.fwrp.dbService.UserDbService;
import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.models.Charity;
import com.fwrp.models.Consumer;
import com.fwrp.models.Retailer;
import com.fwrp.models.User;
import com.fwrp.services.UserService;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ke Yan
 */
public class UserController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String path = request.getServletPath();


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            login(request, response);
        } catch (DataNotExistsException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
            //return info back to frontend;
            response.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>Login Successfully!</h1>");
            out.println("<h2><strong>Your UserID is : " + userId + "</strong></h2>");
            out.println("<p>Email: " + email + "</p>");
            out.println("<p>name: " + user.getFirstName() + "</p>");
            out.println("<p>Password: " + password + "</p>");
            out.println("<p>CreatedDate: " + createdDate + "</p>");
            out.println("<p>CreatedTime: " + createdTime + "</p>");
            out.println("<p>Welcome Aboard!</p>");
            out.println("<p><a href='login.jsp'>Log Out</a></p>");
            out.println("</body></html>");
            }
            */
   }

    private void login(HttpServletRequest request, HttpServletResponse response) throws DataNotExistsException, SQLException, ClassNotFoundException, ServletException, IOException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = null;
        
        UserService userService = new UserService();
        user = userService.getUserByCredentials(email, password);
        
        if(user == null){
            request.setAttribute("errorMessage", "These credentials do not match our records.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        } else{
            // 存储 User 对象到 HttpSession
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
            request.setAttribute("user", user);
            switch(user.getType()) {
                case UserTypeConstant.RETAILER:
                    request.getRequestDispatcher("/views/retailer.jsp").forward(request, response);
                    return;                   
                case UserTypeConstant.CONSUMER:
                    request.getRequestDispatcher("/views/consumer.jsp").forward(request, response);
                    return;
                case UserTypeConstant.CHARITY:
                    request.getRequestDispatcher("/views/charity.jsp").forward(request, response);
                    return;
                default:
                    // 处理未知用户类型
                    request.setAttribute("errorMessage", "Unknown user type.");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    return;
            }
        }
        
    }
    
}

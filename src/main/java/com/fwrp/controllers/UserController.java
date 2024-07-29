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
import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.models.Charity;
import com.fwrp.models.Consumer;
import com.fwrp.models.Retailer;
import com.fwrp.models.User;
import com.fwrp.services.UserService;
import com.fwrp.validator.UserValidator;
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
@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String path = request.getServletPath();

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "login":
                login(request, response);
                break;
            case "openRegisterPage":
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/register.jsp");
                dispatcher.forward(request, response);
                response.getWriter().println("Add Food functionality");
                break;
            case "register":
                register(request,response);
            case "logout":
                logout(request, response);
                break;
            default:
                response.getWriter().println("Unknown action");
        }  
   }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = null;
        
        UserService userService = new UserService();
        try {
            user = userService.getUserByCredentials(email, password);
        } catch (DataNotExistsException ex) {
            request.setAttribute("errorMessage", "These credentials do not match our records.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        if(user == null){
            request.setAttribute("errorMessage", "These credentials do not match our records.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        } else{
            // Store user object to HttpSession
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
    
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String type = request.getParameter("type");
        String organization = request.getParameter("organization");
        
        UserValidator validator = new UserValidator();                
        String[] userData = getRegistrationHistory(request);
    
         if (!validator.validate(firstname, lastname, phone, email, password, repassword, type, organization)) {
            request.setAttribute("userData", userData);
            request.setAttribute("errorMessage", validator.getErrorMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/register.jsp");
            dispatcher.forward(request, response);
            return; // Ensure no further code is executed
        }
        
        UserService userService = new UserService();
         try {
            userService.register(firstname, lastname, phone, email, password, type, organization);
            response.sendRedirect(request.getContextPath() + "/index.jsp?successMessage=Registered%20successfully,%20please%20log%20in.");
        } catch(DataAlreadyExistsException ee) {
            request.setAttribute("errorMessage", "User Already Exists.");
            request.setAttribute("userData", userData);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/register.jsp");
            dispatcher.forward(request, response);
        } catch (DataInsertionFailedException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.setAttribute("userData", userData);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/register.jsp");
            dispatcher.forward(request, response);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("userData", userData);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/register.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private String[] getRegistrationHistory(HttpServletRequest request) {
        String[] inputHistory = new String[8];
        inputHistory[0] = request.getParameter("firstname");
        inputHistory[1] = request.getParameter("lastname");
        inputHistory[2] = request.getParameter("phone");
        inputHistory[3] = request.getParameter("email");
        inputHistory[4] = request.getParameter("type");
        inputHistory[5] = request.getParameter("organization");
        return inputHistory;
    }
    
     private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        request.setAttribute("successMessage", "You have been successfully logged out.");
        response.sendRedirect(request.getContextPath() + "/index.jsp?errorMessage=You have been logged out.");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.usercommand;

import com.fwrp.exceptions.DataAlreadyExistsException;
import com.fwrp.exceptions.DataInsertionFailedException;
import com.fwrp.services.UserService;
import com.fwrp.validator.UserValidator;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author robin
 */
public class RegisterCommand implements IUserCommand{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    /**
     * Retrieves the registration history from the request parameters.
     * 
     * @param request the {@link HttpServletRequest} object that contains the request from the client
     * @return an array of strings containing registration input data
     */
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
    
}
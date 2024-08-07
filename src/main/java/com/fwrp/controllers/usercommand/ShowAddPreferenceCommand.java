/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.usercommand;

import com.fwrp.models.Food;
import com.fwrp.services.RetailerService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to show the add preference page.
 * 
 * This class implements the IUserCommand interface and handles the 
 * execution of displaying the add preference page by forwarding 
 * the request to the addPreference.jsp page.
 * 
 * @author Ke Yan
 * @version 2.0
 */
public class ShowAddPreferenceCommand implements IUserCommand {

    /**
     * Executes the command to show the add preference page.
     * 
     * @param req   The HttpServletRequest object that contains the request the client made to the servlet.
     * @param resp  The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RetailerService retailerService = new RetailerService();
        ArrayList<Food> foods = new ArrayList<>();
        try {
            foods = retailerService.getAllFoods();
        } catch (ClassNotFoundException | SQLException ex) {
            req.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/consumer.jsp");
            dispatcher.forward(req, resp);
        }

        req.setAttribute("foods", foods);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/consumer/addPreference.jsp");

        dispatcher.forward(req, resp);
    }
}

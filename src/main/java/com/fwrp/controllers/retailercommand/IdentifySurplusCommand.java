/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.retailercommand;

import com.fwrp.models.ExpireInfo;
import com.fwrp.services.RetailerService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class implements the command to identify surplus items in the retailer system.
 * <p>
 * It retrieves expiration information for items that are close to expiring from the retailer service and forwards the request to the mark surplus view.
 * If an error occurs, it sets an error message and forwards the request to the retailer view.
 * </p>
 * 
 * @author Robin Guan(041117292)
 * @version 1.0
 * @since 17.0.8
 */
public class IdentifySurplusCommand implements IRetailerCommand{

	/**
     * Executes the identify surplus command.
     * <p>
     * This method retrieves expiration information for items that are close to expiring from the retailer service and sets them as a request attribute.
     * It then forwards the request to the mark surplus JSP view. If an error occurs during the retrieval,
     * it sets an error message as a request attribute and forwards the request to the retailer JSP view.
     * </p>
     * 
     * @param request  the HttpServletRequest object that contains the request the client made to the servlet
     * @param response the HttpServletResponse object that contains the response the servlet returns to the client
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RetailerService retailerService = new RetailerService();
        ArrayList<ExpireInfo> expireInfos = new ArrayList<>();
        try {
            expireInfos = retailerService.getExpireInfoClosedToExpireItems();
            
            request.setAttribute("expireInfos", expireInfos);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/markSurplus.jsp");
            dispatcher.forward(request, response);
            return;
        } catch (ClassNotFoundException | SQLException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/retailer.jsp");
            dispatcher.forward(request, response);
            return;
        }       
    }
}
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
 *
 * @author robin
 */
public class UpdateInventoryExpireDateCommand  implements IRetailerCommand{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RetailerService retailerService = new RetailerService();
        ArrayList<ExpireInfo> expireInfos = new ArrayList<>();
        try {
            expireInfos = retailerService.getAllExpireInfoItems();
        } catch (ClassNotFoundException | SQLException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/retailer.jsp");
            dispatcher.forward(request, response);
        } 
        
        request.setAttribute("expireInfos", expireInfos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/updateExpireDate.jsp");
        dispatcher.forward(request, response);
    }
}

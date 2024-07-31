/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers.retailercommand;

import com.fwrp.exceptions.DataNotExistsException;
import com.fwrp.models.ExpireInfo;
import com.fwrp.services.RetailerService;
import com.fwrp.validator.IsSurplusValidator;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author robin
 */
public class StoreUpdateIsSurplusCommand implements IRetailerCommand{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        RetailerService retailerService = new RetailerService();
         
        // 获取之前存储的 expireInfos
        ArrayList<ExpireInfo> expireInfos = (ArrayList<ExpireInfo>) request.getAttribute("expireInfos");                         
         
         // 处理 'action' 参数，确定请求的操作
         String action = request.getParameter("action");
         if ("storeUpdateIsSurplus".equals(action)) {
            // 遍历参数映射
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                String paramName = entry.getKey();
                String[] paramValues = entry.getValue();
                boolean newIsSurplus;

                // 检查参数名称是否以 'newExpireDate_' 开头
                if (paramName.startsWith("newIsSurplus_")) {
                    String idStr = paramName.substring("newIsSurplus_".length());    
                    int id = Integer.parseInt(idStr);
                    String newIsSurplusStr  = paramValues[0]; 
                    
                    IsSurplusValidator validator = new IsSurplusValidator();
                    if (!validator.validate(newIsSurplusStr)) {
                        request.setAttribute("expireInfos", expireInfos); // Set expireInfos
                        request.setAttribute("errorMessage", validator.getErrorMessage());
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/markSurplus.jsp");
                        dispatcher.forward(request, response);
                        return;
                    }
                    
                    if(newIsSurplusStr.equalsIgnoreCase("0")){
                        newIsSurplus = false;
                    }else{
                        newIsSurplus = true;
                    }

                    try {
                        retailerService.updateIsSurplusOfExpireInfo(id, newIsSurplus);  
                    } catch (SQLException | ClassNotFoundException ex) {
                        request.setAttribute("expireInfos", expireInfos); // Set expireInfos
                        request.setAttribute("errorMessage", ex.getMessage());
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/markSurplus.jsp");
                        dispatcher.forward(request, response);
                        return; 
                    } catch (DataNotExistsException ex) {
                        request.setAttribute("expireInfos", expireInfos); // Set expireInfos
                        request.setAttribute("errorMessage", ex.getMessage());
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inventory/markSurplus.jsp");
                        dispatcher.forward(request, response);
                        return; 
                    } 
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/views/retailer.jsp?successMessage=Food%20expire%20dates%20updated%20successfully.");
    }
}

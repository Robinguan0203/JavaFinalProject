/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers;

import com.fwrp.models.Inventory;
import com.fwrp.services.CharityService;
import com.fwrp.utils.ThymeleafUtil;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ke Yan
 */
@WebServlet("/CharityController")
public class CharityController extends HttpServlet {
    CharityService service;
    @Override
    public void init() throws ServletException {
        service=new CharityService();
        ThymeleafUtil.initialize(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req, resp, req.getServletContext());
//        Context context=new Context();
        List<Inventory> inventoryList=null;
        try {
            inventoryList=service.getDonationInventories();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        context.setVariable("inventories", inventoryList);
        ThymeleafUtil.process("/charity/donationInventory.html",context,resp.getWriter());
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.controllers;

import com.fwrp.models.Inventory;
import com.fwrp.services.CharityService;

import javax.servlet.RequestDispatcher;
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
@WebServlet("/Charity")
public class CharityController extends HttpServlet {
    CharityService service;
    @Override
    public void init() {
        service=new CharityService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Inventory> inventoryList;
        try {
            inventoryList=service.getDonationInventories();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("inventoryList", inventoryList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/charity/donationInventory.jsp");
        dispatcher.forward(req,resp);
    }
}
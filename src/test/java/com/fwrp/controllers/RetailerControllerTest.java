/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.fwrp.controllers;

import com.fwrp.models.Retailer;
import com.fwrp.services.RetailerService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author robin
 */
public class RetailerControllerTest extends Mockito {
    
    private RetailerController retailerController;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private PrintWriter writer;
    
    public RetailerControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        retailerController = new RetailerController();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);
        writer = mock(PrintWriter.class);

        when(request.getSession(false)).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(response.getWriter()).thenReturn(writer);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testDoPostWithNoSession() throws ServletException, IOException {
        when(request.getSession(false)).thenReturn(null);

        retailerController.doPost(request, response);

        verify(request).setAttribute("errorMessage", "You must be logged in first.");
        verify(request).getRequestDispatcher("/index.jsp");
        verify(dispatcher).forward(request, response);
    }
    
    @Test
    public void testDoPostWithNoUserInSession() throws ServletException, IOException {
        when(session.getAttribute("user")).thenReturn(null);

        retailerController.doPost(request, response);

        verify(request).setAttribute("errorMessage", "You must be logged in first.");
        verify(request).getRequestDispatcher("/index.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPostWithAddFoodAction() throws ServletException, IOException {
        Retailer mockRetailer = new Retailer();
        when(session.getAttribute("user")).thenReturn(mockRetailer); // Mock user object
        when(request.getParameter("action")).thenReturn("addFood");

        retailerController.doPost(request, response);
        verify(request).setAttribute(eq("retailer"), any(Retailer.class));
        verify(request).setAttribute(eq("retailer"), any());
       
    }
    
     @Test
    public void testDoPostWithUnknownAction() throws ServletException, IOException {
        Retailer mockRetailer = new Retailer();
        when(session.getAttribute("user")).thenReturn(mockRetailer);
        when(request.getParameter("action")).thenReturn("unknownAction");

        retailerController.doPost(request, response);

        verify(writer).println("Unknown action");
    }
    
    
}

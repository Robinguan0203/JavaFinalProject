/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fwrp.controllers.usercommand;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for user commands.
 * 
 * This interface defines a contract for executing user-related commands
 * with HTTP request and response objects.
 * 
 * Author: Robin Guan
 * Version: 1.0
 * Since: 17.0.8
 */
public interface IUserCommand {
	
	/**
     * Executes a user command.
     * 
     * @param request  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

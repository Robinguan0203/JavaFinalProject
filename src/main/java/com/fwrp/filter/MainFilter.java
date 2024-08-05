package com.fwrp.filter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * MainFilter is a servlet filter that sets the Content-Type header based on the request URI.
 * 
 * This filter applies to all incoming requests and sets the Content-Type header to "text/css" for
 * CSS files and "text/html" for all other files.
 * 
 */
@WebFilter("/*")
public class MainFilter extends HttpFilter {
    
    /**
     * Filters incoming requests and sets the Content-Type header based on the request URI.
     * 
     * @param req The HttpServletRequest object that contains the request the client made to the servlet.
     * @param res The HttpServletResponse object that contains the response the servlet returns to the client.
     * @param chain The FilterChain for invoking the next filter or the resource.
     * @throws IOException if an I/O error occurs during the processing of the request.
     * @throws ServletException if the request could not be handled.
     */
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String uri = req.getRequestURI();
        if(uri.contains(".css"))
        {
            res.setHeader("Content-type","text/css;charset=UTF-8");
        }
        else {
            res.setHeader("Content-type", "text/html;charset=UTF-8");
        }
        chain.doFilter(req,res);
    }
}

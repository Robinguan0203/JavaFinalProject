package com.fwrp.filter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class MainFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String uri = req.getRequestURI();
        String url=req.getRequestURL().toString();
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

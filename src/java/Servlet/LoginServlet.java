/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import JavaBean.DBbean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yanglijia
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url="index.html";
        String id=request.getParameter("username");
        String pwd=request.getParameter("password");
        DBbean db=new DBbean();
        
        String sql="select * from customer where cname='"+id+"' AND password='"+pwd+"'";
        try {
            ResultSet rs=db.query(sql);
            if (rs.next())
            {
                request.getSession().setAttribute("username", id);
                RequestDispatcher dispatcher=request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
                
            }
            else{
                PrintWriter out=response.getWriter();
                out.print("The username or password is wrong.");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    

}

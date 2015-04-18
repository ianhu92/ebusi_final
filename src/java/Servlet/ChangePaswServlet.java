/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import JavaBean.DBbean;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ChangePaswServlet", urlPatterns = {"/ChangePaswServlet"})
public class ChangePaswServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url="";
        String username =request.getParameter("username");
        String newpassword=request.getParameter("new");
        DBbean db=new DBbean();
        String sql="update customer set password='"+newpassword+"' where cname='"+username+"'";
        try {
            db.update(sql);
            RequestDispatcher ds=request.getRequestDispatcher(url);
            ds.forward(request, response);
           
            
        } catch (Exception ex) {
            Logger.getLogger(ChangePaswServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

 

}

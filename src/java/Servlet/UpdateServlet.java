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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yanglijia
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        if (session != null) {
            
            String username = (String) session.getAttribute("username");
            String address = request.getParameter("address");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            DBbean db = new DBbean();
            if (!username.equals("")) {
                String sql = "update customer set addr='" + address + "'where cname='" + username + "'";
                try {
                    db.update(sql);
                    
                } catch (Exception ex) {
                    Logger.getLogger(UpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            if (!address.equals("")) {
                String sql = "update customer set firstname='" + firstname + "'where cname='" + username + "'";
                try {
                    db.update(sql);
                } catch (Exception ex) {
                    Logger.getLogger(UpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (!lastname.equals("")) {
                String sql = "update customer set lastname='" + lastname + "'where cname='" + username + "'";
                try {
                    db.update(sql);
                } catch (Exception ex) {
                    Logger.getLogger(UpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            if (!email.equals("")) {
                String sql = "update customer set Email='" + email + "'where cname='" + username + "'";
                try {
                    db.update(sql);
                } catch (Exception ex) {
                    Logger.getLogger(UpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            out.print("update successfully");
        } else {
            out.print("Invalid Session");
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

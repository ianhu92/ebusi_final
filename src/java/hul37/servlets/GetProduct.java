/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hul37.servlets;

import hul37.dbutil.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author violetliu
 */
@WebServlet(name = "GetProduct", urlPatterns = {"/GetProduct"})
public class GetProduct extends HttpServlet {

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
        try {
            String type=request.getParameter("category");
            String sql="Select product.pid,pname,price,img,stock from product,inventory where product.pid=inventory.pid and type=?";
            PrintWriter out=response.getWriter();
            Connection con = DBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, type);
            ResultSet rs=pstmt.executeQuery();
            JSONObject productlist=new JSONObject();
            while (rs.next())
            {
                JSONObject product=new JSONObject();
                product.append("productid", rs.getString(1));
                product.append("productname", rs.getString(2));
                product.append("price", rs.getString(3));
                product.append("inventory", rs.getString(5));
                product.append("img", rs.getString(4));
                productlist.put(rs.getString(1),product);
            }
            DBConnection.close(rs, pstmt, con);
            String productString=productlist.toString();
            out.write(productString);
        } catch (SQLException ex) {
            Logger.getLogger(GetProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(GetProduct.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hul37.servlets;

import hul37.beans.CartProductBean;
import hul37.dbutil.CartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author violetliu
 */
@WebServlet(name = "GetCartProduct", urlPatterns = {"/GetCartProduct"})
public class GetCartProduct extends HttpServlet {

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
        String msg = "Invalid Session";
        HttpSession session = request.getSession(false);
        if (session != null) {
            String cname = (String) session.getAttribute("username");
            CartDAO cartDAO = new CartDAO();
            CartProductBean[] results = cartDAO.getCartProduct(cname);
            JSONObject list = new JSONObject();
            try {
                for (CartProductBean cpb : results) {
                    JSONObject product = new JSONObject();
                    product.append("productid", cpb.getPid());
                    product.append("productname", cpb.getPname());
                    product.append("price", cpb.getPrice());
                    product.append("inventory", cpb.getStock());
                    product.append("quantity", cpb.getQuantity());
                    product.append("img", cpb.getImg());
                    list.append("product", product);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            msg = list.toString();
        }
        out.write(msg);
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

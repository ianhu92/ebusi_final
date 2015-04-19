/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hul37.servlets;

import hul37.beans.XOrderBean;
import hul37.dbutil.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author violetliu
 */
@WebServlet(name = "GetOrder", urlPatterns = {"/GetOrder"})
public class GetOrder extends HttpServlet {

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
        OrderDAO orderDAO = new OrderDAO();
        JSONObject rspJSON = new JSONObject();
        String msg = "Invalid Session";
        HttpSession session = request.getSession(false);
        if (session != null) {
            try{
            String cname = (String) session.getAttribute("username");
            XOrderBean[] list = orderDAO.getComplexOrder(cname);
            int old_ordernum = -1;
            int ordernum = 0;
            org.json.JSONObject oid = new org.json.JSONObject();
                for (int i = 0; i < list.length; i++) {
                    ordernum = list[i].getOrdernum();
                    if (old_ordernum != ordernum) {
                        oid = new org.json.JSONObject();
                    }
                    oid.put("shippingaddr", list[i].getShippingaddr());
                    oid.put("datetime", list[i].getDatetime());
                    old_ordernum = ordernum;
                    org.json.JSONObject pid = new org.json.JSONObject();
                    pid.put("pname", list[i].getPname());
                    pid.put("price", list[i].getPrice());
                    pid.put("img", list[i].getImg());
                    pid.put("quantity", list[i].getQuantity());
                    oid.append("product", pid);
                }
                rspJSON.append("order", oid);
                rspJSON.getJSONObject(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }

            out.write(rspJSON.toString());
        } else {
            out.print("Invalid session.");
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

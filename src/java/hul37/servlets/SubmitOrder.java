/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hul37.servlets;

import hul37.beans.CartBean;
import hul37.beans.OrderBean;
import hul37.dbutil.CartDAO;
import hul37.dbutil.GetIndex;
import hul37.dbutil.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author violetliu
 */
@WebServlet(name = "SubmitOrder", urlPatterns = {"/SubmitOrder"})
public class SubmitOrder extends HttpServlet {

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
        if(session != null) {
            String cname = (String) session.getAttribute("username");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String address = request.getParameter("address");
//            String state = request.getParameter("state");
//            String zip = request.getParameter("zip");
            String shippingaddr = firstname + " " + lastname + ", " + address;
            CartDAO cartDAO = new CartDAO();
            OrderDAO orderDAO = new OrderDAO();
            CartBean[] list = cartDAO.getCart(cname);
            GetIndex a = new GetIndex();
            int ordernum = a.getOrderNum() + 1;
            for(CartBean aPid : list) {
                int pid = aPid.getPid();
                int quantity = aPid.getQuantity();
                cartDAO.deleteProduct(aPid);
                OrderBean order = new OrderBean(cname, pid, quantity, shippingaddr, "online", "completed", ordernum);
                if(orderDAO.submitOrder(order, ordernum)>=0){
                    msg="Successfully placed order";
                }else{
                    msg="sql error.";
                    break;
                }
            }
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

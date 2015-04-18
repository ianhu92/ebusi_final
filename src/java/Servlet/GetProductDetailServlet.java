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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

/**
 *
 * @author yanglijia
 */
@WebServlet(name = "GetProductDetailServlet", urlPatterns = {"/GetProductDetailServlet"})
public class GetProductDetailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productid = request.getParameter("productid");
        String sql = "Select pid,pname,price,stock,img,des, from product where product.pid=inventory.pid  and pid='" + productid + "'";
        String url = "";
        DBbean db = new DBbean();
        try {
            ResultSet rs = db.query(sql);
            JSONObject productlist = new JSONObject();
            while (rs.next()) {
                JSONObject product = new JSONObject();
                product.append("productid", rs.getString(1));
                product.append("productname", rs.getString(2));
                product.append("price", rs.getString(3));
                product.append("inventory", rs.getString(4));
                product.append("img", rs.getString(5));
                product.append("description", rs.getString(6));
                productlist.append("product",product);
            }
            request.setAttribute("product", productlist);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(GetProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

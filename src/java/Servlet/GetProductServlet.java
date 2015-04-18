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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author yanglijia
 */
@WebServlet(name = "GetProductServlet", urlPatterns = {"/GetProductServlet"})
public class GetProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("category");
        
        if(type.indexOf("'")>=0){
            String[] temp=type.split("'");
            type=temp[0]+"''"+temp[1];
        }
        String sql = "Select product.pid,pname,price,img,stock from product,inventory where product.pid=inventory.pid  and type='" + type + "'";
        PrintWriter out = response.getWriter();
        DBbean db = new DBbean();
        try {
            ResultSet rs = db.query(sql);
            JSONObject productlist = new JSONObject();
            while (rs.next()) {
                JSONObject product = new JSONObject();
                String productid=rs.getString("pid");
                String productname=rs.getString("pname");
                String price=rs.getString("price");
                String img=rs.getString("img");
                String inventory=rs.getString("stock");
                product.append("productid", productid);
                product.append("productname", productname);
                product.append("price", price);
                product.append("img", img);
                product.append("inventory", inventory);
                productlist.append("product", product);
            }
            String productString = productlist.toString();
            out.print(productString);

        } catch (Exception ex) {
            Logger.getLogger(GetProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

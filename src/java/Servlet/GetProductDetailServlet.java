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
import org.json.JSONObject;

/**
 *
 * @author yanglijia
 */
@WebServlet(name = "GetProductDetailServlet", urlPatterns = {"/GetProductDetailServlet"})
public class GetProductDetailServlet extends HttpServlet {

  



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String productid =request.getParameter("productid");
        String sql="Select product.pid,product.pname,product.price,inventory.stock,product.img,product.des from product,inventory where product.pid='"+productid+"'";
       
        DBbean db=new DBbean();
        try {
            ResultSet rs=db.query(sql);
            if(rs.next())
            {
                JSONObject product=new JSONObject();
                product.append("productid", rs.getString(1));
                product.append("productname", rs.getString(2));
                product.append("price", rs.getString(3));
                product.append("inventory", rs.getString(4));
                product.append("img", rs.getString(5));
                product.append("description", rs.getString(6));
                String productString=product.toString();
                out.print(productString);
            }
            else{
                out.print("This prodct id does not exist!");
            }
          
            
            
        } catch (Exception ex) {
            Logger.getLogger(GetProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

    


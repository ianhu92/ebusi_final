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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Ian
 */
@WebServlet(name = "GetProductDetailServlet", urlPatterns = {"/GetProductDetailServlet"})
public class GetProductDetailServlet extends HttpServlet {

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
        String productid = request.getParameter("productid");
        String sql = "Select product.pid,product.pname,product.price,inventory.stock,product.img,product.des from product,inventory where product.pid='" + productid + "'";

        DBbean db = new DBbean();
        try {
            ResultSet rs = db.query(sql);
            if (rs.next()) {
                String productname = rs.getString(2);
                String price = rs.getString(3);
                String inventory = rs.getString(4);
                String img = rs.getString(5);
                String description = rs.getString(6);
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head lang=\"en\">");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Online Flower Shop</title>");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
                out.println("<link href=\"css/productDetail.css\" rel=\"stylesheet\" type=\"text/css\"/>");
                out.println("<link href=\"css/normalize.css\" rel=\"stylesheet\" type=\"text/css\"/>");
                out.println("<link href=\"css/header.css\" rel=\"stylesheet\" type=\"text/css\"/>");
                out.println("<script src=\"js/productDetail.js\"></script>");
                out.println("<script src=\"js/header.js\"></script>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div id=\"header\">");
                out.println("<div id=\"headerLine\">");
                out.println("<div id=\"logo\">");
                out.println("<a class=\"headerLink\" href=\"index.html\"><h2>Pittsburgh Flower Shop</h2></a>");
                out.println("</div>");
                out.println("</div>");
                out.println("<div id=\"accountLine\">");
                out.println("<ul id=\"account\">");
                out.println("<li class=\"accountLi\"><a id=\"accountName\" class=\"headerLink\" href=\"signin.html\">Sign In</a></li>");
                out.println("<li class=\"accountLi\"><a class=\"headerLink\" href=\"cart.html\">Cart&nbsp;<span id=\"cartNum\">0</span></a></li>");
                out.println("<li id=\"logoutLi\" class=\"accountLi\"><a class=\"headerLink\" onclick=\"logout()\">Log Out</a></li>");
                out.println("</ul>");
                out.println("</div>");
                out.println("<div id=\"nav\">");
                out.println("<ul id=\"navUl\">");
                out.println("<li class=\"navLi\"><a href=\"index.html\">Best Sellers!</a></li>");
                out.println("<li class=\"navLi\"><a href=\"category2.html\">Birthday</a></li>");
                out.println("<li class=\"navLi\"><a href=\"category3.html\">Christmas</a></li>");
                out.println("<li class=\"navLi\"><a href=\"category4.html\">Mother's Day</a></li>");
                out.println("<li class=\"navLi\"><a href=\"category5.html\">Valentine's Day</a></li>");
                out.println("</ul>");
                out.println("</div>");
                out.println("</div>");
                out.println("<div id=\"body\">");
                out.println("<div id=\"img\">");
                out.println("<img src=\"img/flower.jpg\"/>");
                out.println("</div>");
                out.println("<div id=\"description\">");
                out.println("<h2 id=\"productName\">aaa</h2>");
                out.println("<div id=\"price\">$2345</div>");
                out.println("<div id=\"descriptionText\">hiqwefposij</div>");
                out.println("<div id=\"btn\">");
                out.println("<a id=\"addToCart5\" class=\"addToCart\" onclick=\"addToCart(this)\">Add to Cart</a>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>This prodct id does not exist!</title>");
                out.println("<script type='text/javascript'>");
                out.println("window.onload=function(){setTimeout(function(){window.location=\"index.html\"},5000)}");
                out.println("</script> ");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>This prodct id does not exist!</h2>");
                out.println("<h2>The page will be redicted in 5 seconds.</h2>");
                out.println("</body>");
                out.println("</html>");
            }

        } catch (Exception ex) {
            Logger.getLogger(GetProductServlet.class.getName()).log(Level.SEVERE, null, ex);
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

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
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "ChangePaswServlet", urlPatterns = {"/ChangePaswServlet"})
public class ChangePaswServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        if(session != null) {
            String username = (String) session.getAttribute("username");
            String oldpassword = request.getParameter("oldpassword");
            String newpassword = request.getParameter("password");
            DBbean db = new DBbean();
            String search = "Select password from customer where cname='" + username + "' and password='" + oldpassword + "'";
            try {
                ResultSet rs = db.query(search);
                if (rs.next()) {
                    String sql = "update customer set password='" + newpassword + "' where cname='" + username + "'";
                    try {
                        db.update(sql);
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Update password successfully!</title>");
                        out.println("<script type='text/javascript'>");
                        out.println("window.onload=function(){setTimeout(function(){window.location=\"setting.html\"},5000)}");
                        out.println("</script> ");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h2>Update password successfully!</h2>");
                        out.println("<h2>The page will be redicted in 5 seconds.</h2>");
                        out.println("</body>");
                        out.println("</html>");
                    } catch (Exception ex) {
                        Logger.getLogger(ChangePaswServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Your old password may be wrong, try again!</title>");
                    out.println("<script type='text/javascript'>");
                    out.println("window.onload=function(){setTimeout(function(){window.location=\"setting.html\"},5000)}");
                    out.println("</script> ");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h2>Your old password may be wrong, try again!</h2>");
                    out.println("<h2>The page will be redicted in 5 seconds.</h2>");
                    out.println("</body>");
                    out.println("</html>");
                }
            } catch (Exception ex) {
                Logger.getLogger(ChangePaswServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            out.print("Invalid Session.");
        }
    }

}

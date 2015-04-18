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

/**
 *
 * @author yanglijia
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("street") + request.getParameter("city") + request.getParameter("state") + request.getParameter("zip");
        String phone = request.getParameter("phone");

        DBbean db = new DBbean();
        String sql1 = "Select * from customer where cname='" + username + "'";
        try {
            ResultSet rs = db.query(sql1);
            if (!rs.next()) {
                String sql = "Insert into customer values('" + username + "','" + password + "','" + email + "','" + firstname + "','" + lastname + "','" + address + "','" + phone + "')";
                try {
                    db.update(sql);
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Sign up succeed</title>");
                    out.println("<script type='text/javascript'>");
                    out.println("window.onload=function(){setTimeout(function(){window.location=\"signin.html\"},5000)}");
                    out.println("</script> ");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h2>Successfully signed up.</h2>");
                    out.println("<h2>The page will be redicted in 5 seconds.</h2>");
                    out.println("</body>");
                    out.println("</html>");
                } catch (Exception ex) {
                    Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Sign up error</title>");
                out.println("<script type='text/javascript'>");
                out.println("window.onload=function(){setTimeout(function(){window.location=\"signin.html\"},5000)}");
                out.println("</script> ");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>The username has been used already.</h2>");
                out.println("<h2>The page will be redicted in 5 seconds.</h2>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

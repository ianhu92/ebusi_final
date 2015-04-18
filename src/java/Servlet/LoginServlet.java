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

/**
 *
 * @author yanglijia
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "index.html";
        String id = request.getParameter("username");
        String pwd = request.getParameter("password");
        DBbean db = new DBbean();

        String sql = "select * from customer where cname='" + id + "' AND password='" + pwd + "'";
        try {
            ResultSet rs = db.query(sql);
            if (rs.next()) {
                request.getSession().setAttribute("username", id);
                PrintWriter out = response.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Login succeed</title>");
                out.println("<script type='text/javascript'>");
                out.println("window.onload=function(){setTimeout(function(){window.location=\"index.html\"},5000)}");
                out.println("</script> ");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>Successfully signed in.</h2>");
                out.println("<h2>The page will be redicted in 5 seconds.</h2>");
                out.println("</body>");
                out.println("</html>");

            } else {
                PrintWriter out = response.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Login error</title>");
                out.println("<script type='text/javascript'>");
                out.println("window.onload=function(){setTimeout(function(){window.location=\"signin.html\"},5000)}");
                out.println("</script> ");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>The username or password is wrong.</h2>");
                out.println("<h2>The page will be redicted in 5 seconds.</h2>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

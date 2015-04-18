/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hul37.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author violetliu
 */
public class DBConnection {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:8889/flower_store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(DBDRIVER);
            con = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.out.println("The Driver for MySql database cannot be found.");
        } catch (SQLException ex) {
            System.out.println("Cannot connect to the database, please check the database url.");
        }
        return con;
    }
    
    public static void close(ResultSet rs, PreparedStatement pstmt, Connection con){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Cannot close ResultSet normally.");
            }
        }
        
        if(pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException ex) {
                System.out.println("Cannot close PreparedStatement normally.");
            }
        }
        
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Cannot close Connection normally.");
            }
        }
    }    
}

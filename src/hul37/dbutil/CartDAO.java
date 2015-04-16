/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hul37.dbutil;

import hul37.beans.CartBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author violetliu
 */
public class CartDAO {
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private final String INSERT = "INSERT INTO flower_store.cart (cname, pid, quantity) VALUES (?, ?, ?);";
    private final String UPDATE = "UPDATE flower_store.cart SET quantity = ? WHERE cname = ? AND pid = ?";
    private final String GET_CART = "SELECT * FROM flower_store.cart WHERE cname = ?";
    private final String DELETE = "DELETE FROM flower_store.cart WHERE cname = ? AND pid = ?";

    public CartDAO() {
        con = DBConnection.getConnection();
    }
    
    public int insertProduct(CartBean cart) {
        try {
            pstmt = con.prepareStatement(INSERT);
            pstmt.setString(1, cart.getCname());
            pstmt.setInt(2, cart.getPid());
            pstmt.setInt(3, cart.getQuantity());
            return  pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int deleteProduct(CartBean cart) {
        try {
            pstmt = con.prepareStatement(DELETE);
            pstmt.setString(1, cart.getCname());
            pstmt.setInt(2, cart.getPid());
            return  pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int updateProduct(CartBean cart) {
        try {
            pstmt = con.prepareStatement(UPDATE);
            pstmt.setInt(1, cart.getQuantity());
            pstmt.setString(2, cart.getCname());
            pstmt.setInt(3, cart.getPid());
            return  pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public CartBean[] getCart(String name) {
        ArrayList<CartBean> al = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(GET_CART);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int pid = rs.getInt("pid");
                int quantity = rs.getInt("quantity");
                CartBean cart = new CartBean(name, pid, quantity);
                al.add(cart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }     
        CartBean[] cart = new CartBean[al.size()];
        al.toArray(cart);
        return cart;
    }

    public Connection getCon() {
        return con;
    }

    @Override
    protected void finalize() throws Throwable {
        DBConnection.close(rs, pstmt, con);
    }
    
}

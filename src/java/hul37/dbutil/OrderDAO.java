package hul37.dbutil;


import hul37.beans.OrderBean;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author violetliu
 */
public class OrderDAO {
    
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private final String SUBMIT_ORDER = "INSERT INTO flower_store.order (cname, pid, quantity, shippingaddr, card, status, datetime)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?);";
    private final String GET_ORDER = "SELECT * FROM `order` WHERE cname = ?";

    public OrderDAO() {
        con = DBConnection.getConnection();
    }
    
    public int submitOrder(OrderBean order) {
        try {
            pstmt = con.prepareStatement(SUBMIT_ORDER);
            pstmt.setString(1, order.getCname());
            pstmt.setInt(2, order.getPid());
            pstmt.setInt(3, order.getQuantity());
            pstmt.setString(4, order.getShippingaddr());
            pstmt.setString(5, order.getCard());
            pstmt.setString(6, order.getStatus());
            pstmt.setTimestamp(7, new Timestamp(order.getDatetime().getTime()));
            return  pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public OrderBean[] getOrder(String name) {
        ArrayList<OrderBean> al = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(GET_ORDER);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                String cname = rs.getString("cname");
                int pid = rs.getInt("pid");
                int quantity = rs.getInt("quantity");
                String shippingaddr = rs.getString("shippingaddr");
                String card = rs.getString("card");
                String status = rs.getString("status");
                Date datetime = rs.getDate("datetime");
                OrderBean order = new OrderBean(cname, pid, quantity, shippingaddr, card, status, datetime);
                al.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }     
        OrderBean[] orders = new OrderBean[al.size()];
        al.toArray(orders);
        return orders;
    }
    
    @Override
    protected void finalize() throws Throwable {
        DBConnection.close(rs, pstmt, con);
    }
    
    
}

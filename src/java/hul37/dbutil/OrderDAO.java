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
    private final String SUBMIT_ORDERNUM = "INSERT INTO flower_store.ordernum (ordernum, oid) VALUES (?, ?);";    
    private final String GET_ORDER = "SELECT distinct * FROM `order`, `ordernum`\n" +
"WHERE (order.oid = ordernum.oid) AND cname = ?";
    
    public OrderDAO() {
        con = DBConnection.getConnection();
    }
    
    public int submitOrder(OrderBean order, int ordernum) {
        int result = -1;
        try {
            pstmt = con.prepareStatement(SUBMIT_ORDER);
            pstmt.setString(1, order.getCname());
            pstmt.setInt(2, order.getPid());
            pstmt.setInt(3, order.getQuantity());
            pstmt.setString(4, order.getShippingaddr());
            pstmt.setString(5, order.getCard());
            pstmt.setString(6, order.getStatus());
            pstmt.setTimestamp(7, new Timestamp(order.getDatetime().getTime()));
            result = pstmt.executeUpdate();
            insertOrdernum(ordernum);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
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
                int ordernum = rs.getInt("ordernum");
                OrderBean order = new OrderBean(cname, pid, quantity, shippingaddr, card, status, datetime, ordernum);
                al.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }     
        OrderBean[] orders = new OrderBean[al.size()];
        al.toArray(orders);
        return orders;
    }
    
    private void insertOrdernum(int ordernum) {
        GetIndex index = new GetIndex();
        int oid = index.getOid();
        try {
            pstmt = con.prepareStatement(SUBMIT_ORDERNUM);
            pstmt.setInt(1, ordernum);
            pstmt.setInt(2, oid);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    @Override
    protected void finalize() throws Throwable {
        DBConnection.close(rs, pstmt, con);
    }

}

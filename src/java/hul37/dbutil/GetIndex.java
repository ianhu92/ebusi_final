/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hul37.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author violetliu
 */
public class GetIndex {
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;  
    private final String GET_OID = "SELECT max(oid) FROM flower_store.order";
    private final String GET_ORDERNUM = "SELECT max(ordernum) FROM flower_store.ordernum";
    public int oid;
    public int ordernum;

    public GetIndex() {
        con = DBConnection.getConnection();
    }
    
    public int getOid() {
        try {
            pstmt = con.prepareStatement(GET_OID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                oid = rs.getInt(1);
            }
            return oid;
        } catch (SQLException ex) {
            System.err.println("cannot initalize the oid and ordernum" + ex.getMessage());
        }
        return -1;
    }
    
    public int  getOrderNum() {
        try {
            pstmt = con.prepareStatement(GET_ORDERNUM);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println("cannot initalize the oid and ordernum" + ex.getMessage());
        }
        return -1;
    }
    
    @Override
    protected void finalize() throws Throwable {
        DBConnection.close(rs, pstmt, con);
    }    
}

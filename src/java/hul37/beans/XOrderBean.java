/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hul37.beans;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author violetliu
 */
public class XOrderBean implements Serializable {
    
    private int ordernum;
    private int oid;
    private String pname;
    private double price;
    private int quantity;
    private String img;
    private String shippingaddr;
    private Date datetime;

    public int getOrdernum() {
        return ordernum;
    }

    public int getOid() {
        return oid;
    }

    public String getPname() {
        return pname;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImg() {
        return img;
    }

    public String getShippingaddr() {
        return shippingaddr;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setShippingaddr(String shippingaddr) {
        this.shippingaddr = shippingaddr;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public XOrderBean() {
    }

    public XOrderBean(int ordernum, int oid, String pname, double price, int quantity, String img, String shippingaddr, Date datetime) {
        this.ordernum = ordernum;
        this.oid = oid;
        this.pname = pname;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
        this.shippingaddr = shippingaddr;
        this.datetime = datetime;
    }
    
    
}

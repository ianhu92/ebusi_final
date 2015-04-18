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
public class OrderBean implements Serializable {
    
    private int ordernum;
    private String cname;
    private int pid;
    private int quantity;
    private String shippingaddr;
    private String card;
    private String status;
    private Date datetime;

    public OrderBean() {
    }
    

    public OrderBean(String cname, int pid, int quantity, String shippingaddr, String card, String status, int ordernum) {
        this.cname = cname;
        this.pid = pid;
        this.quantity = quantity;
        this.shippingaddr = shippingaddr;
        this.card = card;
        this.status = status;
        this.datetime = new Date(System.currentTimeMillis());
        this.ordernum = ordernum;
    }

    public OrderBean(String cname, int pid, int quantity, String shippingaddr, String card, String status, Date datetime, int ordernum) {
        this.ordernum = ordernum;
        this.cname = cname;
        this.pid = pid;
        this.quantity = quantity;
        this.shippingaddr = shippingaddr;
        this.card = card;
        this.status = status;
        this.datetime = datetime;
    }

    public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }

    public String getCname() {
        return cname;
    }

    public int getPid() {
        return pid;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getShippingaddr() {
        return shippingaddr;
    }

    public String getCard() {
        return card;
    }

    public String getStatus() {
        return status;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setShippingaddr(String shippingaddr) {
        this.shippingaddr = shippingaddr;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    
}

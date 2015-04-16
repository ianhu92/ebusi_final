/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hul37.beans;

import java.io.Serializable;

/**
 *
 * @author violetliu
 */
public class CartBean implements Serializable{
    
    private String cname;
    private int pid;
    private int quantity;

    public CartBean() {
    }

    public CartBean(String cname, int pid, int quantity) {
        this.cname = cname;
        this.pid = pid;
        this.quantity = quantity;
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

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}

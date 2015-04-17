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
public class CartProductBean implements Serializable{
    
    private int pid;
    private String pname;
    private double price;
    private int stock;
    private int quantity;

    public int getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartProductBean() {
    }

    public CartProductBean(int pid, String pname, double price, int stock, int quantity) {
        this.pid = pid;
        this.pname = pname;
        this.price = price;
        this.stock = stock;
        this.quantity = quantity;
    }
    
}

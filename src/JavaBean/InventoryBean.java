/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBean;

/**
 *
 * @author yanglijia
 */
public class InventoryBean {

    String pid;
    int  stock;
    public InventoryBean(String pid, int stock) {
        this.pid = pid;
        this.stock = stock;
    }
    public String getPid() {
        return pid;
    }

    public int getStock() {
        return stock;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    
    
}

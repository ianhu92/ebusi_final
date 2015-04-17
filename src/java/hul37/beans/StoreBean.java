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
public class StoreBean implements Serializable{
    
    private String sname;
    private double lon;
    private double lat;
    private String saddr;
    private int phone;

    public StoreBean() {
    }

    public StoreBean(String sname, double lon, double lat) {
        this.sname = sname;
        this.lon = lon;
        this.lat = lat;
    }

    public StoreBean(String sname, double lon, double lat, String saddr, int phone) {
        this.sname = sname;
        this.lon = lon;
        this.lat = lat;
        this.saddr = saddr;
        this.phone = phone;
    }

    public String getSname() {
        return sname;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public String getSaddr() {
        return saddr;
    }

    public int getPhone() {
        return phone;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setSaddr(String saddr) {
        this.saddr = saddr;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    
}

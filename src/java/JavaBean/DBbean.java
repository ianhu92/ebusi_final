/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author yanglijia
 */
public class DBbean {
    
 private String DBDriver="com.mysql.jdbc.Driver";
    private String url="jdbc:mysql://localhost:8889/flower_store?zeroDateTimeBehavior=convertToNull";
    private String uid="root";
    private String pwd="root";
    public Connection conn=null;
    public ResultSet rs=null;
    
    public void setDBDriver(String DBDriver)
    {
       this.DBDriver=DBDriver; 
    }
    public String getDBDriver()
    {
        return DBDriver;
    }
    public void setUrl(String url)
    {
        this.url=url;
    }
    public String getUrl()
    {
        return url;
    }
    public void setUid(String uid)
    {
        this.uid=uid;
    }
    public String getUid()
    {
        return uid;
    }
    public void setPwd(String pwd)
    {
        this.pwd=pwd;
    }
    public String getPwd()
    {
        return pwd;
    }
    public ResultSet query(String mySql)throws Exception
    {
        try
        {
            Class.forName(DBDriver);
            conn=DriverManager.getConnection(url, uid,pwd);
            Statement stmt=conn.createStatement();
            rs=stmt.executeQuery(mySql);
            return rs;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void update(String mysql) throws Exception
    {
        try
        {
           Class.forName(DBDriver);
           conn=DriverManager.getConnection(url,uid,pwd);
           Statement stmt=conn.createStatement();
           stmt.executeUpdate(mysql);
           stmt.close();
           conn.close();
           
        }
        catch(SQLException ee)
        {
            System.out.println(ee.getMessage());
        }
    }
    
}

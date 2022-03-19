/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myclasscount.model.dao;

/**
 *
 * @author CUINIC4
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexaoo {
    private static Connection con=null;
//    private static String username="root";
//    private static String password="";
//    private static String ip="localhost";
//    private static String bdName="myclasscount";
    
//    public static Connection getConnection(){
//        Connection connection=null;
//        try{
//            connection=DriverManager.getConnection("jdbc:mysql://"+ip+"/"+bdName,username,password);
//        }catch(SQLException e){
//            throw new RuntimeException(e);
//        }
//        return connection;
//    }
    
    public static Connection getConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            String url=System.getProperty("user.dir");
            //if (con == null) {
                con=DriverManager.getConnection("jdbc:sqlite:"+url+"\\src\\com\\myclasscount\\model\\dao\\myclasses.db");
            //} else {
                //con.close();
                //con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\CUINIC4\\OneDrive\\Documents\\NetBeansProjects\\com.myclasscount\\src\\com\\myclasscount\\model\\dao\\myclasses.db");
            //}
        }catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return con;
    }
    
    public static void close(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexaoo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
}

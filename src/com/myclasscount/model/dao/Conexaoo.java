
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

import static com.myclasscount.model.Licensa.criarDir;
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
            final String caminho=System.getProperty("user.home");
            //System.out.println("Caminnho: "+caminho);
            //if (con == null) {
            //System.out.println("User dir: "+url);
            criarDir(caminho+"\\myclasscount");
            con=DriverManager.getConnection("jdbc:sqlite:"+caminho+"\\myclasscount\\myclasses.db");
            //} else {
                //con.close();
                //con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\CUINIC4\\OneDrive\\Documents\\NetBeansProjects\\com.myclasscount\\dist\\myclasses.db");
            //}
        }catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException("Erro con: "+e);
        }catch(RuntimeException e){
            
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

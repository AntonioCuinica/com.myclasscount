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

public class conexao {
    private static String username="root";
    private static String password="";
    private static String ip="localhost";
    private static String bdName="myclasscount";
    
    public static Connection getConnection(){
        Connection connection=null;
        try{
            connection=DriverManager.getConnection("jdbc:mysql://"+ip+"/"+bdName,username,password);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return connection;
    }
}


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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexaoo {
    private static Connection con=null;
    
    public static Connection getConnection(){
        try{
            final String userDir=System.getProperty("user.dir");
            final String userHome=System.getProperty("user.home");
            criarDir(userHome+"\\myclasscount");
            System.out.println("User dir: "+userDir);
            System.out.println("Home dir: "+System.getProperty("user.home"));
            File file=new File(userHome+"\\myclasscount\\myclasses.db");
            try {
                System.out.println("Existe ?: "+file.exists());
                if(!file.exists()){
                    Files.copy(Paths.get(userDir+"\\myclasscount\\myclasses.db")
                        ,Paths.get(userHome+"\\myclasscount\\myclasses.db"));
                }
            } catch (IOException ex) {
                System.out.println("Erro, Conexao, base de dados nao movida");
            }
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:"+userHome+"\\myclasscount\\myclasses.db");
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

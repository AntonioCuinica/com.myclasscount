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


import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class conexao {
    private static boolean connected=false;
    private static Connection connection;
    private static String username="root";
    private static String password="";
    private static String ip="localhost";
    private static String bdName="myclasscount";
    
    public static Connection getConnection(){
        if(!connected){
            try{
                connection=DriverManager.getConnection("jdbc:mysql://"+ip+"/"+bdName,username,password);
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
            connected=true;
            return connection;
        }
        return connection;
    }
    
    public static void closeConnection() throws SQLException{
        if(connected){
            connection.close();
        }
    }
    
    public static void main(String[] args)throws Exception {
         PreparedStatement stmt=getConnection().prepareStatement("select * from aluno;");
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                com.myclasscount.model.Aluno aluno=new com.myclasscount.model.Aluno();
                aluno.setId((Integer.parseInt(rs.getString("id"))));
                aluno.setNome(rs.getString("nome"));
                aluno.setApelido(rs.getString("apelido"));
                aluno.setEmail(rs.getString("email"));
                aluno.setMorada(rs.getString("morada"));
                //aluno.setNascimento(rs.getDate("nascimento"));
                System.out.println("============================");
                System.out.println(aluno);
                System.out.println("============================");
            }
            rs.close();
            stmt.close();
            closeConnection();
    }
    
}

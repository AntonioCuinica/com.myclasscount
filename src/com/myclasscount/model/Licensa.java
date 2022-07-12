/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author CUINIC4
 */
public class Licensa implements Serializable {
    private static String dir;
    
    public Licensa(){
        dir=System.getProperty("user.home");
    }
    
    public  void guardarObjecto(Object codigo){
        try {
            //Cria o ficheiro Falunos.txt para armazenar os objectos 
            FileOutputStream ficheiro1 = new FileOutputStream(dir+"\\myclasscount\\licensa.txt"); 
            ObjectOutputStream obj = new ObjectOutputStream(ficheiro1);

            //Gravando obj objectos no ficheiro 
            obj.writeObject(codigo); 

            //fechando os ficheiros abertos 
            ficheiro1.flush(); 
            ficheiro1.close();   
            obj.flush(); 
            obj.close();   
            System.out.println("Objeto gravado com sucesso!");
        }catch(Exception e) { e.printStackTrace( ); }  
    }
    
    public static  void criarDir(String uri){
        try {
            //Cria o ficheiro Falunos.txt para armazenar os objectos 
            File dir=new File(uri);
            dir.mkdirs();
            //if(dir.mkdirs()){
            //   System.out.println("Diretorio criado com sucesso");
            //}else{
            //   System.out.println("Erro ao criar diretorio");
            //}
        }catch(Exception e) { 
            e.printStackTrace(); 
        }  
    }
    
    
    public  Object retornarObjecto(){
        Object obj=null;
        try{
            FileInputStream ficheiro2=new FileInputStream(dir+"\\myclasscount\\licensa.txt"); 
            ObjectInputStream entrada2=new ObjectInputStream(ficheiro2);
            obj=entrada2.readObject();
            ficheiro2.close();
            entrada2.close();
        }catch(Exception e){
            System.out.println("Error , backup failed");
            return null;
        }
        return obj;
    }
    
    
}

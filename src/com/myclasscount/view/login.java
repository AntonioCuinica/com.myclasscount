/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author CUINIC4
 */
public class login extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private Panel title;
    
    public login(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=myProcedures.barName("Login",this);
        container.add(title);
        this.addComponentToMainPane(myProcedures.mainPane(this,this,container));
        this.setVisible(true);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        container.remove(container.getComponentCount()-1);
        container.remove(container.getComponentCount()-1);
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(7,1));
        pan1.invisible(true, true);
        
        JTextField txtF[]={new JTextField()};
        JPasswordField pswF=new JPasswordField();
        mybutton entrar=new mybutton("Entrar",160,25,false);
        entrar.addMouseListener(new Clique());
        JLabel labels[]={new JLabel("Bem vindo ao MyClassCount",SwingConstants.CENTER),new JLabel("Usuario"),
            new JLabel("Senha")};
        for(JLabel lb:labels)lb.setForeground(Color.white);
        
        
        pan1.add(labels[0]); 
        pan1.add(labels[1]);
        pan1.add(txtF[0]);
        pan1.add(labels[2]);
        pan1.add(pswF);
        pan1.add(new JLabel());
        pan1.add(entrar);
        
        mainPane.add(pan1);
        
        
        new Thread(
            new Runnable(){
                public void run(){
                    while(true){
                        try{
                            Thread.sleep(50);
                        }catch(InterruptedException e){
                            System.out.println("Erro: "+e.getMessage());
                        }
                        int x=50,y=50;
                        x=container.getWidth()/2-title.getWidth()/2;
                        title.setLocation(x,y);
                        pan1.setSize((int)(mainPane.getWidth()*0.60),(int)(mainPane.getHeight()*0.75));
                        x=mainPane.getWidth()/2-pan1.getWidth()/2;
                        y=mainPane.getHeight()/2-pan1.getHeight()/2;
                        pan1.setLocation(x,y);
                        entrar.setPosition((int)(x*1.40),(int)(y*0.5));
                        mainPane.revalidate();
                    }
                }
            }
        ).start();
    }
    
    private class Clique extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"mainFrame");
        }
    }
}

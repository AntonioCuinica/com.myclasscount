/*
 * To change this license header, choose License Headers in Project Propertie
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author CUINIC4
 */
public class CriarSenha extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private MyButton btns[];
    private Panel title;
    public CriarSenha(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProcedures.barName("Criar Senha",this);
        container.add(title);
        this.addComponentToMainPane(new MyProcedures().mainPane("adicionarProfessor","login",container));
        this.setVisible(true);
    }
    
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(10,1));
        pan1.invisible(true, true);
       
        JTextField txtF[]={new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField()};
        JLabel labels[]={new JLabel("Username"),new JLabel("Criar Senha"),new JLabel("Confirmar Senha"),
                         new JLabel("Pergunta de Recuperaçao"),new JLabel("Resposta")};
        for(JLabel lb:labels)lb.setForeground(Color.white);
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(txtF[1]);
        pan1.add(labels[2]); pan1.add(txtF[2]);
        pan1.add(labels[3]); pan1.add(txtF[3]);
        pan1.add(labels[4]); pan1.add(txtF[4]);
        
        mainPane.add(pan1);
        //mainPane.add(pan2);
        
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
                        pan1.setSize((int)(mainPane.getWidth()*0.75),mainPane.getHeight()-50);
                        x=(mainPane.getWidth()/2)-(pan1.getWidth()/2);
                        y=(mainPane.getHeight()/2)-(pan1.getHeight()/2);
                        pan1.setLocation(x,y);
                        mainPane.revalidate();
                    }
                }
            }
        ).start();
    }
            
}

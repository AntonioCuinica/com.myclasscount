/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author CUINIC4
 */
public class CriarCategoria extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private MyButton btns[];
    
    public CriarCategoria(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        Panel title=MyProcedures.barName("Criar Categoria",this);
        container.add(title);
        this.addComponentToMainPane(new MyProcedures().mainPane("","",container));
        this.setVisible(true);
    }
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(10,1));
        pan1.invisible(true, true);
     
        String  tipoEnsino[]={"Primario","Secondario","Tecnico","Universitario"};
        String classe[]={"1a Classe","2a Classe","3a Classe","4a Classe","5a Classe","6a Classe","7a Classe",
                         "8a Classe","9a Classe","10a Classe","11a Classe","12a Classe","Superior"};
        JComboBox combbx[]={new JComboBox(tipoEnsino),new JComboBox(classe)};
        JTextField txtF[]={new JTextField(),new JTextField()};
        JTextArea desc=new JTextArea();
        desc.setLineWrap(true);
        JLabel labels[]={new JLabel("Nome"),new JLabel("Tipo de ensino"),new JLabel("Classe"),
                         new JLabel("Preço"),new JLabel("Descriçao")};
        for(JLabel lb:labels)lb.setForeground(Color.white);
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(combbx[0]);
        pan1.add(labels[2]); pan1.add(combbx[1]);
        pan1.add(labels[3]); pan1.add(txtF[1]);
        pan1.add(labels[4]);pan1.add(desc);
        
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
                        pan1.setSize((int)(mainPane.getWidth()*0.75),mainPane.getHeight()-50);
                        int x=(mainPane.getWidth()/2)-(pan1.getWidth()/2);
                        int y=(mainPane.getHeight()/2)-(pan1.getHeight()/2);
                        pan1.setLocation(x,y);
                        mainPane.revalidate();
                    }
                }
            }
        ).start();
    }
   
    
    
}

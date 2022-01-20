/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author CUINIC4
 */
public class MyProceduress {
    private MyButtonn btns[];

    public MyButtonn[] getBtns() {
        return btns;
    }
  
    public static Panel barName(String title,Container panel){
        Panel pane=new Panel(Color.black,false);
        pane.setLayout(null);
        pane.invisible(true,true);
        JLabel label=new JLabel(title);
        label.setForeground(Color.white);
        JPanel pan=new JPanel();
        pan.setBackground(new Color(30,80,172));
        pane.add(label);
        pane.add(pan);
        
     
        new Thread(
            new Runnable(){
                public void run(){
                    while(true){
                        try{
                            Thread.sleep(50);
                        }catch(InterruptedException e){
                            System.out.println("Erro: "+e.getMessage());
                        }
                        int x=100,y=50;
                        label.setBounds(0,0,120,20);
                        pan.setBounds(0,20,panel.getWidth()-x,10);
                        pane.setSize(panel.getWidth()-100,35);
                        panel.repaint();
                    }
                }
            }
        ).start();
        
        return pane;
    }
    
    public static Panel barName(String title){
        Panel pane=new Panel(Color.black,false);
        pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
        pane.invisible(true,true);
        JLabel label=new JLabel(title);
        label.setForeground(Color.white);
        JPanel pan=new JPanel();
        pan.setBackground(new Color(205,190,216).brighter());
        pane.add(label);
        pane.add(pan);
        
        return pane;
    }
    
   
    public  Panel mainPane(String antes, String depois, Container container){
        /**main panel*/
        Panel pane = new Panel(new Color(82,79,250),false);
        pane.setBorderColor(Color.white);
        pane.setLayout(null);
       
        /** btns(voltar e proximo) buttons*/
        btns=new MyButtonn[]{new MyButtonn("Voltar",false),new MyButtonn("Proximo",false)};
        btns[0].addActionListener(new Clique(antes));
        btns[0].setActionCommand("Voltar");
        btns[1].addActionListener(new Clique(depois));
        btns[1].setActionCommand("Proximo");
        btns[0].setVisible(false);
        btns[1].setVisible(false);
        container.add(pane);
        container.add(btns[0]);
        container.add(btns[1]);
        
        new Thread(
            new Runnable(){
                public void run(){
                    
                    while(true){
                        try{
                            Thread.sleep(50);
                        }catch(InterruptedException e){
                            System.out.println("Erro: "+e.getMessage());
                        }
                        int x=0,y=0;
                        
                        /** pane location */
                        Component cmp=container.getComponent(0);
                        y=cmp.getY()+cmp.getHeight()+20;
                        pane.setSize((int)(cmp.getWidth()*0.75),(container.getHeight()-(y+100)));
                        x=(cmp.getWidth()/2+cmp.getX())-(pane.getWidth()/2);
                        pane.setLocation(x,y);  
                        
                        /** btns Location */
                        getBtns()[1].setSize(85,25);
                        x=pane.getWidth()+pane.getX()-getBtns()[1].getWidth();
                        y=pane.getHeight()+pane.getY()+15;
                        getBtns()[1].setLocation(x,y);
                        x-=getBtns()[1].getWidth()+15;
                        getBtns()[0].setBounds(x,y,getBtns()[1].getWidth(),getBtns()[1].getHeight());
                       
                        container.repaint();
                    }
                }
            }
        ).start();
        
        return pane;
    }

    public static JPanel info(String n,String v){
        JPanel panel=new JPanel(new GridLayout(1,2));
        panel.setOpaque(false);
        JLabel name=new JLabel(n);
        name.setForeground(Color.white);
        name.setFont(new Font("Arial",Font.BOLD,18));
        JLabel value=new JLabel(v);
        value.setForeground(Color.white);
        value.setFont(new Font("Arial",Font.PLAIN,18));
        value.setToolTipText(v);
        panel.add(name);
        panel.add(value);
        return panel;
    }
    
    public static void updateVerAlunos(){
        Ver_Alunos v=(Ver_Alunos)Myclasscount.getContainer().getComponent(2);
        v.updateComponents();
    }
    
    public static void updateVerProfessores(){
        Ver_Professores v=(Ver_Professores)Myclasscount.getContainer().getComponent(3);
        v.updateComponents();
    }
    
    
    private class Clique implements ActionListener{
        private String dir; 
        
        public Clique(String dir){
            this.dir=dir;
        }
        
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("Voltar")){
                System.out.println("Voltar");
                System.out.println("dir :"+dir);
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),dir);
            } else if(e.getActionCommand().equals("Proximo")){
                System.out.println("Proximo");
                System.out.println("dir :"+dir);
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),dir);
            }      
        }
    }
    
    public static int idade(String ano){
        Calendar cal=Calendar.getInstance();
        return cal.get(Calendar.YEAR)-(Integer.parseInt(ano));
    }
}

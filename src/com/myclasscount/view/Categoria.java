/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author CUINIC4
 */
public class Categoria extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private mybutton btns[];
    private Panel title;
     
    public Categoria(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=myProcedures.barName("Categorias",this);
        container.add(title);
        this.addCategoria();
        this.setVisible(true);
    }
    
    public void addCategoria(){
        mybutton bts[]=new mybutton[6];
        Panel pan=new Panel(Color.white,true);
        GridLayout gLyt=new GridLayout(1,3,15,0);
        BoxLayout bLyt=new BoxLayout(pan,BoxLayout.Y_AXIS);
        pan.invisible(true,false);
        pan.setLayout(bLyt);
        pan.setBorder(new EmptyBorder(15,15,0,15));
        
        for(int i=0;i<bts.length;i++){
            bts[i]=new mybutton("Escola "+(i+1),15,16,false);
            bts[i].addActionListener(new clique(bts[i]));
            bts[i].addMouseListener(new clique(bts[i]));
        }
        
        Panel pan1=null;
        int count=0;
        for(int i=0;i<bts.length;i++){
            count++;
            if(i==0){
                pan1=new Panel(backColor.darker(),false);
                pan1.setLayout(gLyt);
                pan1.invisible(true,true);
                pan1.add(bts[i]);
            }else if(i%3==0){
                pan1=new Panel(backColor.darker(),false);
                pan1.setLayout(gLyt);
                pan1.invisible(true,true);
                pan1.add(bts[i]);
            }else{
                pan1.add(bts[i]);
            }
            if(count==3 || i==(bts.length-1)){
                if(count!=3 && i==(bts.length-1)){
                    for(int j=0;j<=count;j++){
                        if(pan1.getComponentCount()<3)pan1.add(new JLabel());
                    }
                }
                pan.add(pan1);
                pan.add(Box.createRigidArea(new Dimension(0,15)));
                count=0;
            }
        }
        
        
        mybutton voltar=new mybutton("Voltar",15,20,false);
        voltar.setSize(60,30);
        voltar.addMouseListener(new clique(voltar));
        JScrollPane src=new JScrollPane(pan);
        src.getViewport().setBackground(backColor.darker());
        container.add(src);
        container.add(voltar);
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
                        src.setBounds(0,title.getY()+y,container.getWidth(),(int)(container.getHeight()*0.75));
                        y=((container.getHeight()-(src.getHeight()+src.getY())))-(voltar.getHeight()/2);
                        x=title.getWidth()+title.getX()-voltar.getWidth();
                        voltar.setLocation(x,container.getHeight()-y);
                        container.revalidate();
                        
                    }
                }
            }
        ).start();
    }
   
    private class clique extends MouseAdapter implements ActionListener {
            mybutton btn=null;
            public clique(mybutton btn){
                this.btn=btn;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                //myDialog dialog=new myDialog(container,true);
                 myDialog dialog=new myDialog(Myclasscount.getFrame(),true);
                
            }
            
            public void mouseClicked(MouseEvent e){
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"mainFrame");
            }
            
    }
    
    /*public static void main(String[] args) {
        new Categoria();
    }*/
}

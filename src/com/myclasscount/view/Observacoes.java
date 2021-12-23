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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author CUINIC4
 */
public class Observacoes extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private Panel title;
    public Observacoes(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Observaçoes",this);
        container.add(title);
        this.addComponentToMainPane(new MyProceduress().mainPane("mainFrame","",container));
        this.setVisible(true);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(null);
        pan1.invisible(true, true);
        Panel pan2=new Panel(Color.black,false);
        pan2.setLayout(null);
        pan2.invisible(true, true);
        
       
        JTextField txtF[]={new JTextField()};
        JTextArea note=new JTextArea();
        note.setLineWrap(true);
        MyButtonn saveNote=new MyButtonn("Gravar",15,20,false);
        JLabel labels[]={new JLabel("Titulo"),new JLabel("Nota")};
        for(JLabel lb:labels)lb.setForeground(Color.white);
        
        MyButtonn student[]=new MyButtonn[20];
        Panel stud=new Panel(new Color(209,178,255),true);
        for(int i=0;i<student.length;i++){
            student[i]=new MyButtonn("Estudante "+(i+1),7,18,false);
            stud.add(student[i]);
        }
        stud.setLayout(new GridLayout(student.length,1,10,5));
        
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(note);
        pan1.add(saveNote);
        
        pan2.add(MyProceduress.barName("Escolher Aluno"));
        pan2.add(new JScrollPane(stud));
        
        mainPane.add(pan1);
        mainPane.add(pan2);
        
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
                        y=40;
                        pan1.setBounds(50,25,mainPane.getWidth()/2-75,mainPane.getHeight()-50);
                        pan2.setBounds(mainPane.getWidth()/2+25,25,mainPane.getWidth()/2-75,mainPane.getHeight()-50);
                        labels[0].setBounds(0,0,pan1.getWidth(),(int)(pan1.getHeight()*0.1));
                        txtF[0].setBounds(0,labels[0].getY()+y,pan1.getWidth(),(int)(pan1.getHeight()*0.1));
                        labels[1].setBounds(0,txtF[0].getY()+y,pan1.getWidth(),(int)(pan1.getHeight()*0.1));
                        note.setBounds(0,labels[1].getY()+y,pan1.getWidth(),(int)(pan1.getHeight()*0.5));
                        saveNote.setBounds(0,note.getHeight()+note.getY()+10,pan1.getWidth(),(int)(pan1.getHeight()*0.1));
                        saveNote.setPosition(saveNote.getWidth()/2-14,saveNote.getHeight()/2+2);
                        pan2.getComponent(0).setBounds(0,0,pan2.getWidth(),25);
                        pan2.getComponent(1).setBounds(0,30,pan2.getWidth(),pan2.getHeight()-30);
                        mainPane.revalidate();
                    }
                }
            }
        ).start();
    }
   
   
}

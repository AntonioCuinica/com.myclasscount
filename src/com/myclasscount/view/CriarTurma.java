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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author CUINIC4
 */
public class CriarTurma extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private Panel title;
    public CriarTurma(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=myProcedures.barName("Criar Turma",this);
        container.add(title);
        this.addComponentToMainPane(new myProcedures().mainPane("mainFrame","verTurmas",container));
        this.setVisible(true);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(11,1));
        pan1.invisible(true, true);
        Panel pan2=new Panel(Color.black,false);
        pan2.setLayout(null);
        pan2.invisible(true, true);
        
        String  turno[]={"Manha","Tarde","Noite"};
        String professor[]={"Domingos","Sergio","Teresa","Quinho","Queila","Serena","Samuel"};
        String classe[]={"1a Classe","2a Classe","3a Classe","4a Classe","5a Classe","6a Classe","7a Classe",
                         "8a Classe","9a Classe","10a Classe","11a Classe","12a Classe","Superior"};
        JComboBox combbx[]={new JComboBox(turno),new JComboBox(professor),new JComboBox(classe)};
        JTextField txtF[]={new JTextField()};
        mybutton defHorario=new mybutton("Definir Horario",165,20,false);
        defHorario.addActionListener(new Clique());
        JLabel labels[]={new JLabel("Nome da Turma"),new JLabel("Turno"),new JLabel("Nome do Professor"),
                         new JLabel("Classe")};
        for(JLabel lb:labels)lb.setForeground(Color.white);
        
        mybutton student[]=new mybutton[20];
        Panel stud=new Panel(new Color(209,178,255),true);
        for(int i=0;i<student.length;i++){
            student[i]=new mybutton("Estudante "+(i+1),7,18,false);
            stud.add(student[i]);
        }
        stud.setLayout(new GridLayout(student.length,1,10,5));
        
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(combbx[0]);
        pan1.add(labels[2]); pan1.add(combbx[1]);
        pan1.add(labels[3]); pan1.add(combbx[2]);
        pan1.add(new JLabel());pan1.add(defHorario);
        
        pan2.add(myProcedures.barName("Adicionar Alunos"));
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
                        pan1.setBounds(50,25,mainPane.getWidth()/2-75,mainPane.getHeight()-50);
                        pan2.setBounds(mainPane.getWidth()/2+25,25,mainPane.getWidth()/2-75,mainPane.getHeight()-50);
                        defHorario.setPosition(defHorario.getWidth()/2-35,defHorario.getHeight()/2+4);
                        pan2.getComponent(0).setBounds(0,0,pan2.getWidth(),25);
                        pan2.getComponent(1).setBounds(0,30,pan2.getWidth(),pan2.getHeight()-30);
                        pan2.revalidate();
                        mainPane.revalidate();
                    }
                }
            }
        ).start();
    }
    private class Clique implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"crirHorario");
        }
    }
    
   
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author CUINIC4
 */

public class Inscrever extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private MyButtonn btns[];
    private Panel title;
    public Inscrever(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Inscriçao",this);
        container.add(title);
        this.addComponentToMainPane(new MyProceduress().mainPane("mainFrame","verAlunos",container));
        this.setVisible(true);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(11,1));
        pan1.invisible(true, true);
     
        String nome[]={"António","Jessica","Eduardo","Daniel"};
        String  Disciplina[]={"Matemática","Física","Quimica","Inglês"};
        String classe[]={"1a Classe","2a Classe","3a Classe","4a Classe","5a Classe","6a Classe","7a Classe",
                         "8a Classe","9a Classe","10a Classe","11a Classe","12a Classe","Superior"};
        JComboBox combbx[]={new JComboBox(nome),new JComboBox(Disciplina),new JComboBox(classe)};
        JRadioButton yes=new JRadioButton("Sim");
        yes.setOpaque(false);
        yes.setForeground(Color.white);
        JRadioButton no=new JRadioButton("Nao");
        no.setOpaque(false);
        no.setForeground(Color.white);
        ButtonGroup btnG=new ButtonGroup();
        btnG.add(yes);btnG.add(no);
        Panel tax=new Panel(Color.black,false);
        tax.setLayout(new GridLayout(1,2));
        tax.invisible(true,true);
        tax.add(yes);tax.add(no);
        JLabel labels[]={new JLabel("Nome"),new JLabel("Disciplina"),new JLabel("Classe"),new JLabel("Data"),
                  new JLabel("Taxa"),new JLabel("Mês"),new JLabel("Dia"),new JLabel("Ano")};
        for(int i=0;i<labels.length;i++){labels[i].setForeground(Color.white);}
        String day[]={"Janeiro", "Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
        String month[]=new String[31];
        for(int i=0;i<month.length;i++){
            month[i]=""+(i+1);
        }
        String year[]=new String[30];
        for(int i=0;i<year.length;i++){
            year[i]=""+(i+1990);
        }
        JComboBox data[]={new JComboBox(day),new JComboBox(month),new JComboBox(year)};
        JPanel dataN=new JPanel(new GridLayout(1,3,20,1));
        dataN.setOpaque(false);
        dataN.add(labels[5]);
        dataN.add(labels[6]);
        dataN.add(labels[7]);
        JPanel dataN1=new JPanel(new GridLayout(1,3,20,1));
        dataN1.setOpaque(false);
        dataN1.add(data[0]);
        dataN1.add(data[1]);
        dataN1.add(data[2]);
       
        pan1.add(labels[0]); pan1.add(combbx[0]);
        pan1.add(labels[1]); pan1.add(combbx[1]);
        pan1.add(labels[2]); pan1.add(combbx[2]);

        pan1.add(labels[3]); pan1.add(dataN);pan1.add(dataN1);
        pan1.add(labels[4]);pan1.add(tax);
        
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
                        mainPane.revalidate();
                        
                    }
                }
            }
        ).start();
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author CUINIC4
 */
public class AdicionarProfessor extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private mybutton btns[];
    private Panel title;
    
   
    public AdicionarProfessor(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=myProcedures.barName("Cadastrar Professor",this);
        container.add(title);
        this.addComponentToMainPane(new myProcedures().mainPane("mainFrame","criarSenha",container));
        this.setVisible(true);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(14,1));
        pan1.invisible(true, true);
        Panel pan2=new Panel(Color.black,false);
        pan2.setLayout(null);
        pan2.invisible(true, true);
        
        JTextField txtF[]={new JTextField(),new JTextField(),new JTextField(),
                           new JTextField(),new JTextField(),new JTextField(),
                           new JTextField()};
        JTextArea andress=new JTextArea();
        andress.setLineWrap(true);
        JScrollPane morada=new JScrollPane(andress);
        
        String nivel[]={"Primario","Secondario","Tecnico","Universitario"};
        String  disciplina[]={"Matematica","Quimica","Português","Inglês","português","Fisica","Ciências Naturais"};
        JComboBox combbx=new JComboBox(nivel);
        JList discList=new JList(disciplina);
        JScrollPane disc=new JScrollPane(discList);
        JRadioButton male=new JRadioButton("Masculino");
        male.setOpaque(false);
        male.setForeground(Color.white);
        JRadioButton female=new JRadioButton("Feminino");
        female.setOpaque(false);
        female.setForeground(Color.white);
        ButtonGroup btnG=new ButtonGroup();
        btnG.add(male);btnG.add(female);
        Panel sexo=new Panel(Color.black,false);
        sexo.setLayout(new GridLayout(1,2));
        sexo.invisible(true,true);
        sexo.add(male);sexo.add(female);
        JLabel labels[]={new JLabel("Nome"),new JLabel("Apelido"),new JLabel("Nr BI"),new JLabel("Nascimento"),
                  new JLabel("Sexo"),new JLabel("Nivel Academico"),new JLabel("Salario"),new JLabel("Morada"),new JLabel("NUIT"),
                  new JLabel("Telefone"),new JLabel("Email"),new JLabel("Disciplina"),new JLabel("Mês"),new JLabel("Dia"),new JLabel("Ano")};
        for(int i=0;i<labels.length;i++){labels[i].setForeground(Color.white);}
        String mes[]={"Janeiro", "Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
        String dia[]=new String[31];
        for(int i=0;i<dia.length;i++){
            dia[i]=""+(i+1);
        }
        String ano[]=new String[30];
        for(int i=0;i<ano.length;i++){
            ano[i]=""+(i+1990);
        }
        
        JComboBox data[]={new JComboBox(mes),new JComboBox(dia),new JComboBox(ano)};
        JPanel dataN=new JPanel(new GridLayout(2,3,20,1));
        dataN.setOpaque(false);
        dataN.add(labels[12]);
        dataN.add(labels[13]);
        dataN.add(labels[14]);
        dataN.add(data[0]);
        dataN.add(data[1]);
        dataN.add(data[2]);
        
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(txtF[1]);
        pan1.add(labels[2]); pan1.add(txtF[2]);
        pan1.add(labels[3]); pan1.add(dataN);
        pan1.add(labels[4]); pan1.add(sexo);
        pan1.add(labels[5]); pan1.add(combbx);
        pan1.add(labels[6]);pan1.add(txtF[3]);
        
        pan2.add(labels[7]); pan2.add(morada);
        pan2.add(labels[8]); pan2.add(txtF[4]);
        pan2.add(labels[9]); pan2.add(txtF[5]);
        pan2.add(labels[10]); pan2.add(txtF[6]);
        pan2.add(labels[11]); pan2.add(disc);
        
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
                        labels[11].setBounds(labels[5].getBounds());
                        disc.setBounds(0,combbx.getY(),combbx.getWidth(),3*combbx.getHeight());
                        labels[10].setBounds(labels[4].getBounds());
                        txtF[6].setBounds(sexo.getBounds());
                        labels[9].setBounds(labels[3].getBounds());
                        txtF[5].setBounds(pan1.getComponent(7).getBounds());
                        labels[8].setBounds(labels[2].getBounds());
                        txtF[4].setBounds(txtF[2].getBounds());
                        labels[7].setBounds(labels[0].getBounds());
                        morada.setBounds(0,txtF[0].getY(),txtF[0].getWidth(),3*txtF[0].getHeight());
                        mainPane.revalidate();
                    }
                }
            }
        ).start();
    }
   
}

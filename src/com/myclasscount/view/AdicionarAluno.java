/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author CUINIC4
 */
public class AdicionarAluno extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private mybutton btns[];
    private Panel title;
    
    public AdicionarAluno(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        this.title=myProcedures.barName("Cadastrar Alunos",this);
        container.add(title);
        this.addComponentToMainPane(new myProcedures().mainPane("mainFrame","inscricao",container));
        this.setVisible(true);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(13,1));
        pan1.invisible(true, true);
        Panel pan2=new Panel(Color.black,false);
        pan2.setLayout(null);
        pan2.invisible(true, true);
        
        JTextField txtF[]={new JTextField(),new JTextField(),new JTextField(),
                           new JTextField(),new JTextField(),new JTextField()};
        
        JTextArea andress=new JTextArea();
        andress.setLineWrap(true);
        JScrollPane morada=new JScrollPane(andress);
        
        String nivel[]={"Primario","Secondario","Tecnico","Universitario"};
        String  categoria[]={"Normal","Domiciliar","Misto"};
        String classe[]={"1a Classe","2a Classe","3a Classe","4a Classe","5a Classe","6a Classe","7a Classe",
                         "8a Classe","9a Classe","10a Classe","11a Classe","12a Classe","Superior"};
        JComboBox combbx[]={new JComboBox(nivel),new JComboBox(categoria),new JComboBox(classe)};
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
                  new JLabel("Sexo"),new JLabel("Nivel Academico"),new JLabel("Morada"),new JLabel("Telefone"),
                  new JLabel("Email"),new JLabel("Categoria"),new JLabel("Classe"),new JLabel("Mês"),new JLabel("Dia"),new JLabel("Ano")};
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
        JPanel dataN=new JPanel(new GridLayout(1,3,20,1));
        dataN.setOpaque(false);
        dataN.add(labels[11]);
        dataN.add(labels[12]);
        dataN.add(labels[13]);
        JPanel dataN1=new JPanel(new GridLayout(1,3,20,1));
        dataN1.setOpaque(false);
        dataN1.add(data[0]);
        dataN1.add(data[1]);
        dataN1.add(data[2]);
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(txtF[1]);
        pan1.add(labels[2]); pan1.add(txtF[2]);
        pan1.add(labels[3]); pan1.add(dataN);pan1.add(dataN1);
        pan1.add(labels[4]); pan1.add(sexo);
        pan1.add(labels[5]); pan1.add(combbx[0]);
        
        pan2.add(labels[6]); pan2.add(morada);
        pan2.add(labels[7]); pan2.add(txtF[3]);
        pan2.add(labels[8]); pan2.add(txtF[4]);
        pan2.add(labels[9]); pan2.add(combbx[1]);
        pan2.add(labels[10]);pan2.add(combbx[2]);
        
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
                        labels[10].setBounds(labels[5].getBounds());
                        combbx[2].setBounds(combbx[0].getBounds());
                        labels[9].setBounds(labels[4].getBounds());
                        combbx[1].setBounds(sexo.getBounds());
                        labels[8].setBounds(labels[3].getBounds());
                        txtF[4].setBounds(pan1.getComponent(7).getBounds());
                        labels[7].setBounds(labels[2].getBounds());
                        txtF[3].setBounds(txtF[2].getBounds());
                        labels[6].setBounds(labels[0].getBounds());
                        morada.setBounds(0,txtF[0].getY(),txtF[0].getWidth(),3*txtF[0].getHeight());
                      
                        mainPane.revalidate();
                    }
                }
            }
        ).start();
    }
   
}

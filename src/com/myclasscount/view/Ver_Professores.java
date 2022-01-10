/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author CUINIC4
 */
public class Ver_Professores extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private MyButtonn btns[];
    private Panel title;
    private Table tabela;
    
    public Ver_Professores(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Professores",this);
        container.add(title);
        this.addTable();
        this.setVisible(true);
    }
    
    public void addTable(){
        String colunas[]={"Nome","BI","Idade","Sexo","Nível","Telefone","Salario"};
        MyButtonn voltar=new MyButtonn("Voltar",false);
        voltar.setSize(85,25);
        voltar.addMouseListener(new Clique());
        tabela=new Table(colunas);
        String dados[][]=new String[30][colunas.length];
         for(int i=0;i<dados.length;i++){
            for(int j=0;j<dados[0].length;j++){
                dados[i][j]=""+i*j;
            }
        }
        tabela.setTableData(dados);
        JScrollPane src=new JScrollPane(tabela);
        src.setBackground(Color.blue);
        src.getViewport().setBackground(Color.white);
        container.add(src);
        container.add(voltar);
        verProfessores(Myclasscount.getFrame());
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
    public void verProfessores(JFrame frame){
        tabela.addMouseListener(
            new MouseAdapter(){
                public void mouseClicked(MouseEvent evento){
                    if(evento.getClickCount()==2){
                        int linha=tabela.getSelectedRow();
                        String txt=tabela.getValueAt(linha,0).toString()+" | "+tabela.getValueAt(linha,1).toString()+
                             " | "+tabela.getValueAt(linha,2).toString()+" | "+tabela.getValueAt(linha,3).toString();
                        MyDialogg dialog=new MyDialogg(frame,true);
                    }
                }
            }
        );
    }
    private class Clique extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"mainFrame");
        }
    }
    
}

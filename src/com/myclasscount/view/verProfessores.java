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
public class verProfessores extends JFrame {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private mybutton btns[];
    private Panel title;
    private Table tabela;
    private JFrame anterior;
    
    public verProfessores(JFrame anterior){
        this.anterior=anterior;
        this.setSize(anterior.getSize());
        this.setMinimumSize(new Dimension(860,600));
        this.setLayout(null);
        this.setLocationRelativeTo(anterior);
        container=this.getContentPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setBackground(backColor.darker());
        title=myProcedures.barName("Cadastrar Professores",this);
        container.add(title);
        this.addTable();
        this.setVisible(true);
    }
    
    public void addTable(){
        String colunas[]={"Nome","BI","Idade","Sexo","Nível","Telefone","Salario"};
        mybutton voltar=new mybutton("Voltar",15,20,false);
        voltar.setSize(60,30);
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
        verProfessores(this);
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
                        myDialog dialog=new myDialog(frame,true);
                    }
                }
            }
        );
    }
    private class Clique extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
           anterior.setVisible(true);
           anterior.setBounds(getBounds());
           dispose();
        }
    }
    
    /*public static void main(String[] args) {
        new verProfessores();
    }*/
}

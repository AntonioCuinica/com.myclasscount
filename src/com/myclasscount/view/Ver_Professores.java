/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.Professor_ctrl;
import com.myclasscount.model.Professor;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
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
    private ArrayList<Professor> professores;
    private Professor professor;
    private int linhaSelecionada;
    
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
        professores=Professor_ctrl.getProfessores();
        String dados[][]=new String[professores.size()][colunas.length];
         for(int i=0;i<dados.length;i++){
            dados[i][0]=professores.get(i).getNome()+" "+professores.get(i).getApelido();
            dados[i][1]=professores.get(i).getBI();
            Calendar cal=Calendar.getInstance();
            int idade=cal.get(Calendar.YEAR)-(Integer.parseInt(professores.get(i).getNascimento().split("-")[0]));
            dados[i][2]=String.valueOf(idade);
            dados[i][3]=professores.get(i).getSexo();
            dados[i][4]=professores.get(i).getNivel();
            dados[i][5]=professores.get(i).getTelefone();
            dados[i][6]=String.valueOf(professores.get(i).getSalario());
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
                        linhaSelecionada=tabela.getSelectedRow();
                        String txt=tabela.getValueAt(linhaSelecionada,0).toString()+" | "+tabela.getValueAt(linhaSelecionada,1).toString()+
                             " | "+tabela.getValueAt(linhaSelecionada,2).toString()+" | "+tabela.getValueAt(linhaSelecionada,3).toString();
                        MyDialogg dialog=new MyDialogg(frame,true);
                    }
                }
            }
        );
    }
    
    public  void updateComponents(){
        removeAll();
        professores=Professor_ctrl.getProfessores();
        for(Professor a:professores){
            if(a.getBI().equals(tabela.getValueAt(linhaSelecionada,2).toString())){
                professor=a;
            }
        }
        container.add(title);
        addTable();
    }
    
    private class Clique extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"mainFrame");
        }
    }
    
}

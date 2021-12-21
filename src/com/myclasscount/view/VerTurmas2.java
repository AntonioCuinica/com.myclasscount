/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author CUINIC4
 */
public class VerTurmas2 extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private MyButton btns[];
    private Panel title;
    private Table tabela;
    
    public VerTurmas2(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProcedures.barName("Turma A",this);
        container.add(title);
        this.addTable();
        this.setVisible(true);
    }
    
    public void addTable(){
        Panel mainPane=new Panel(Color.white,false);
        mainPane.setLayout(new BorderLayout());
        
        JPanel pan=new JPanel(new GridLayout());
        mainPane.add(pan,BorderLayout.NORTH);
        
        String turma[][]={{"Classe","10a"},{"Turno","Manha"},{"Professor","Ribeiro"}};
        JPanel pan1=new JPanel(new GridLayout(3,1));
   
        for(int i=0;i<turma.length;i++){
            Panel pan2=new Panel(new Color(19,46,98).darker(),true);
            pan2.invisible(true,true);
            pan2.setLayout(new GridLayout(1,2));
            for(int j=0;j<turma[0].length;j++){
                JLabel lbl=new JLabel(turma[i][j]);
                lbl.setForeground(Color.white);
                pan2.add(lbl);
            }
            pan1.add(pan2);
        }
        pan.add(pan1);
        
        String colunas1[]={"","S","T","Q","Q","S","S","D"};
        tabela=new Table(colunas1);
        String dados1[][]=new String[2][colunas1.length];
        for(int i=0;i<dados1.length;i++){
            for(int j=0;j<dados1[0].length;j++){
                dados1[i][j]=""+i*j;
            }
        }
        dados1[0][0]="Inicio";
        dados1[1][0]="Fim";
        tabela.setTableData(dados1);
        tabela.setBackground(Color.black);
        JScrollPane src1=new JScrollPane(tabela);
        src1.setPreferredSize(new Dimension(200,100));
        src1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        pan.add(src1);
        
        String colunas[]={"Aluno","Apelido","BI","Idade","Classe","Sexo","Nível","Pagamento"};
        MyButton voltar=new MyButton("Voltar",15,20,false);
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
        
        
        mainPane.add(src,BorderLayout.CENTER);
        container.add(mainPane);
        container.add(voltar);
        verAlunos(Myclasscount.getFrame());
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
                        mainPane.setBounds(0,title.getY()+y,container.getWidth(),(int)(container.getHeight()*0.75));
                        y=((container.getHeight()-(mainPane.getHeight()+mainPane.getY())))-(voltar.getHeight()/2);
                        x=title.getWidth()+title.getX()-voltar.getWidth();
                        voltar.setLocation(x,container.getHeight()-y);
                        container.revalidate();
                    }
                }
            }
        ).start();
    }
    public void verAlunos(JFrame frame){
        tabela.addMouseListener(
            new MouseAdapter(){
                public void mouseClicked(MouseEvent evento){
                    if(evento.getClickCount()==2){
                        int linha=tabela.getSelectedRow();
                        String txt=tabela.getValueAt(linha,0).toString()+" | "+tabela.getValueAt(linha,1).toString()+
                             " | "+tabela.getValueAt(linha,2).toString()+" | "+tabela.getValueAt(linha,3).toString();
                        MyDialog dialog=new MyDialog(frame,true);
                    }
                }
            }
        );
    }
    
    private class Clique extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verTurmas");
        }
    }
    
}

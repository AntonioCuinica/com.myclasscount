/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myclasscount.view;

import com.myclasscount.control.Disciplina_ctrl;
import com.myclasscount.model.Disciplina;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author CUINIC4
 */

public class Ver_Disciplinas extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private MyButtonn btns[];
    private Panel title;
    private Table tabela;
    
    public Ver_Disciplinas(){
        setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Lista de Disciplinas",this);
        container.add(title);
        addTable();
        setVisible(true);
    }
    
    public void addTable(){
        String colunas[]={"Nome","Carga Horaria","Secçao",""," "};
        MyButtonn voltar=new MyButtonn("Voltar",false);
        voltar.setSize(85,25);
        voltar.addMouseListener(new Clique());
        voltar.setVisible(false);
        tabela=new Table(colunas);
        ArrayList<Disciplina> disciplinas=Disciplina_ctrl.getDisciplinas();
        Object dados[][]=new Object[disciplinas.size()][colunas.length];
        
        for(int i=0;i<dados.length;i++){
            dados[i][0]=disciplinas.get(i).getNome();
            dados[i][1]=disciplinas.get(i).getCarga_horaria();
            dados[i][2]=disciplinas.get(i).getSeccao();
        }
        
        tabela.setTableData(dados);
        tabela.setButton("","Modificar",Color.green.darker());
        tabela.setButton(" ","Remover",Color.red);
        JScrollPane src=new JScrollPane(tabela);
        src.setBackground(Color.blue);
        src.getViewport().setBackground(Color.white);
        container.add(src);
        container.add(voltar);
        verDisciplinas(Myclasscount.getFrame());
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
                        voltar.setVisible(true);
                        container.revalidate();
                    }
                }
            }
        ).start();
    }
    
    public void updateComponents(){
        removeAll();
        container.add(title);
        addTable();
    }
    
    
    public void verDisciplinas(JFrame frame){
        tabela.addMouseListener(
            new MouseAdapter(){
                public void mouseClicked(MouseEvent e){
                    int linha=tabela.getSelectedRow();
                    Disciplina disciplina=Disciplina_ctrl.getDisciplina(tabela.getValueAt(linha,0).toString());
                    if(tabela.getSelectedColumn()==3){
                        ModificarDisciplina mDisc=new ModificarDisciplina(frame,disciplina,true);
                        mDisc.setVisible(true);
                    }else if(tabela.getSelectedColumn()==4){
                        MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),2,"Deseja remover ?",true);
                        if(dialog.getSimTeste()){
                            if(Disciplina_ctrl.delDisciplina(disciplina)){
                                updateComponents();
                            }else {
                                MyDialogg dialog1=new MyDialogg(Myclasscount.getFrame(),1,"Erro, disciplina em uso",true);
                            }
                        }
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

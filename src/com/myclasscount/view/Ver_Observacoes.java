/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myclasscount.view;

import com.myclasscount.control.Aluno_ctrl;
import com.myclasscount.control.Observacao_ctrl;
import com.myclasscount.model.Aluno;
import com.myclasscount.model.Observacao;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author CUINIC4
 */
public class Ver_Observacoes extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private MyButtonn btns[];
    private Panel title;
    private Table tabela;
    private ArrayList<Observacao> observacoes;
    private String voltar="mainFrame";
    
    public void setVoltar(String voltar){
        this.voltar=voltar;
    }
     
    public Ver_Observacoes(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Observações",this);
        container.add(title);
        this.addTable();
        this.setVisible(true);
    }
    
    public void addTable(){
        String colunas[]={"Nr","Nome do Aluno","Titulo","Nota","Data",""};
        MyButtonn voltar=new MyButtonn("Voltar",false);
        voltar.setSize(85,25);
        voltar.addMouseListener(new Clique());
        voltar.setVisible(false);
        
        tabela=new Table(colunas);
        tabela.setDefaultRenderer(Object.class,new MyCell());
        observacoes=Observacao_ctrl.getObservacao();
        ArrayList<Aluno> alunos=Aluno_ctrl.getAlunos();
        Object dados[][]=new Object[observacoes.size()][colunas.length];
        for(int i=0;i<dados.length;i++){
            dados[i][0]=String.valueOf((i+1));
            int aluno_id=observacoes.get(i).getAluno_id();
            for(Aluno aluno:alunos){
                if(aluno.getId()==aluno_id){
                    dados[i][1]=aluno.getNome()+" "+aluno.getApelido();
                }
            }
            dados[i][2]=observacoes.get(i).getTitulo();
            dados[i][3]=new JTextArea(observacoes.get(i).getNota());
            dados[i][4]=observacoes.get(i).getDataObservacao();
        }
     
        tabela.setTableData(dados);
        tabela.setButton("","Eliminar",Color.red);
        tabela.setRowHeight(100);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(250);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
        JScrollPane src=new JScrollPane(tabela);
        src.setBackground(Color.blue);
        src.getViewport().setBackground(Color.white);
        container.add(src);
        container.add(voltar);
        removerObservacao(Myclasscount.getFrame());
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
        add(title);
        addTable();
    }
    
    public void removerObservacao(JFrame frame){
        tabela.addMouseListener(
            new MouseAdapter(){
                public void mouseClicked(MouseEvent evento){
                    if(evento.getClickCount()==1){
                        int linha=tabela.getSelectedRow();
                        if(tabela.getSelectedColumn()==5){
                            MyDialogg dialog=new MyDialogg(frame,2,"Deseja remover observação ?", true);
                            if(dialog.getSimTeste()){
                                Observacao observ=observacoes.get(linha);
                                Observacao_ctrl.delObservacao(observ.getId());
                                updateComponents();
                            }
                        }
                    }
                }
            }
        );
    }

    
    private  class MyCell extends DefaultTableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JTextArea txtA=null;
            setHorizontalAlignment(SwingConstants.CENTER);
            table.getColumnModel().getColumn(column).setCellRenderer(this);
            if(value instanceof JTextArea){
                txtA=(JTextArea)value;
                txtA.setLineWrap(true);
                txtA.setRows(20);
                txtA.setBackground(Color.white);
                txtA.setEditable(true);
                return txtA;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
    
    private class Clique extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            Myclasscount.getCardLayout().show(Myclasscount.getContainer(),voltar);
        }
    }
    
}

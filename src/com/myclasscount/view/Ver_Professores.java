/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.Disciplina_ctrl;
import com.myclasscount.control.Professor_ctrl;
import com.myclasscount.model.Disciplina;
import com.myclasscount.model.Professor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
    private MyDialogg ver_dialog;
    private Table tabInscricao;
    
    public Ver_Professores(){
        setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Professores",this);
        container.add(title);
        addTable();
        setVisible(true);
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
            dados[i][2]=String.valueOf(MyProceduress.idade(professores.get(i).getNascimento().split("-")[0]));
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
                       for(Professor a:professores){
                            if(a.getBI().equals(tabela.getValueAt(linhaSelecionada,1).toString())){
                                professor=a;
                            }
                        }
                        ver_dialog(frame);
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
    
    public void updateVer_dialog(JFrame frame){
        updateComponents();
        ver_dialog.removeAll();
        ver_dialog.dispose();
        ver_dialog(frame);
    }
    
    public void ver_dialog(JFrame frame){
        ver_dialog=new MyDialogg(frame,true);
        ver_dialog.setLayout(new GridLayout(1,2));
        ver_dialog.setSize(750,600);
        ver_dialog.setLocationRelativeTo(frame);
        
        JPanel lado1=new JPanel(new BorderLayout());
        lado1.setBorder(new EmptyBorder(15,15,15,15));
        lado1.setOpaque(false);
                        
        JPanel pan1=new JPanel(new GridLayout(12,1));
        pan1.setOpaque(false);
        pan1.add(MyProceduress.info("Nome",": "+professor.getNome()));
        pan1.add(MyProceduress.info("Apelido",": "+professor.getApelido()));
        pan1.add(MyProceduress.info("BI",": "+professor.getBI()));
        try{
            System.out.println("Nasc1: "+professor.getNascimento().split("-")[0]);
            String dt=professor.getNascimento().substring(0,4);
            System.out.println("Nascimento: "+professor.getNascimento());
            System.out.println("Idade : "+dt);
            pan1.add(MyProceduress.info("Idade",": "+MyProceduress.idade(dt)));
            System.out.println("Idade : "+dt);
        }catch(NumberFormatException e){
            System.out.println("Erro, data: "+e.getMessage());
        }
        pan1.add(MyProceduress.info("Sexo",": "+professor.getSexo()));
        pan1.add(MyProceduress.info("Nível",": "+professor.getNivel()));
        pan1.add(MyProceduress.info("Morada",": "+professor.getMorada()));
        pan1.add(MyProceduress.info("Telefone",": "+professor.getTelefone()));
        pan1.add(MyProceduress.info("Email",": "+professor.getEmail()));
        pan1.add(MyProceduress.info("NUIT",": "+professor.getNUIT()));
        pan1.add(MyProceduress.info("Salario",": "+professor.getSalario()));
                        
        JPanel pan2=new JPanel(new FlowLayout());
        pan2.setOpaque(false);
        MyButtonn modificar=new MyButtonn("Modificar",false);
        modificar.addMouseListener(new Clique());
        MyButtonn remover=new MyButtonn("Remover",false);
        remover.addMouseListener(new Clique());
        pan2.add(modificar);
        pan2.add(remover);
                        
        JPanel lado2=new JPanel(new BorderLayout());
        lado2.setBorder(new EmptyBorder(15,15,15,15));
        lado2.setOpaque(false);
                        
        tabInscricao=new Table(new String[]{"Disciplina","Carga horaria","Secçao"});
        ArrayList<Disciplina> disciplinas=Disciplina_ctrl.getDisciplinas(professor.getId());
        String dados[][]=new String[disciplinas.size()][3];
        for(int i=0;i<dados.length;i++){
            dados[i][0]=disciplinas.get(i).getNome();
            dados[i][1]=disciplinas.get(i).getCarga_horaria();
            dados[i][2]=disciplinas.get(i).getSeccao();
        }
                        
        tabInscricao.setTableData(dados);
        JScrollPane src=new JScrollPane(tabInscricao);
        src.getViewport().setBackground(new Color(82,79,250).darker().darker());
                       
        JPanel panBtn=new JPanel(new FlowLayout());
        panBtn.setOpaque(false);
        MyButtonn removerI=new MyButtonn("Eliminar",false);
        removerI.addMouseListener(new Clique());
        panBtn.add(removerI);
                        
        JPanel titulo=new JPanel();
        titulo.setOpaque(false);
        JLabel texto=new JLabel("Inscricoes",SwingConstants.CENTER);
        texto.setForeground(Color.white);
        texto.setFont(new Font("Arial",Font.BOLD,18));
        titulo.add(texto);
                        
        lado2.add(titulo,BorderLayout.NORTH);
        lado2.add(src,BorderLayout.CENTER);
        lado2.add(panBtn,BorderLayout.SOUTH);

        lado1.add(pan1,BorderLayout.CENTER);
        lado1.add(pan2,BorderLayout.SOUTH);
                        
        ver_dialog.add(lado1);
        ver_dialog.add(lado2);
        ver_dialog.setVisible(true);
    }
    
    
   
    
    private class Clique extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            if(e.getComponent().toString().contains("Voltar")){
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"mainFrame");
            }else if(e.getComponent().toString().contains("Modificar")){
                ModificarrProfessor modP=new ModificarrProfessor(Myclasscount.getFrame(),professor,true);
                modP.setVisible(true);
            }else if(e.getComponent().toString().contains("Remover")){
                MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),2,"Deseja remover professor?",true);
                if(dialog.getSimTeste()){
                    dialog.dispose();
                    if(Professor_ctrl.delProfessor(professor.getId())){
                        updateComponents();
                        ver_dialog.dispose();
                        MyDialogg resp1=new MyDialogg(Myclasscount.getFrame(),1,"Removido com sucesso",true);
                    }else{
                        MyDialogg resp2=new MyDialogg(Myclasscount.getFrame(),1,"Ocorreu um erro !!",true);
                    }
                }
            }else if(e.getComponent().toString().contains("Eliminar")){
                MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),2,"Deseja cancelar inscriçao",true);
                if(dialog.getSimTeste()){
                    dialog.dispose();
                    int linha=tabInscricao.getSelectedRow();
                    if(linha>-1){
                        String nome=tabInscricao.getValueAt(linha,0).toString();
                        Disciplina disc=Disciplina_ctrl.getDisciplina(nome);
                        if(Professor_ctrl.delInscricaoProfDisc(professor.getId(),disc.getId())){
                            MyDialogg resp2=new MyDialogg(Myclasscount.getFrame(),1,"Eliminada com sucesso",true);
                            updateComponents();
                            updateVer_dialog(Myclasscount.getFrame());
                        }else {
                            MyDialogg resp2=new MyDialogg(Myclasscount.getFrame(),1,"Ocorreu um erro !!",true);
                        }
                    }else {
                        MyDialogg resp2=new MyDialogg(Myclasscount.getFrame(),1,"Escolha uma disciplina",true);
                    }
                }
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.Aluno_ctrl;
import com.myclasscount.control.categoria_ctrl;
import com.myclasscount.model.Aluno;
import com.myclasscount.model.Categoria;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author CUINIC4
 */
public class Ver_Alunos extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private MyButtonn btns[];
    private Panel title;
    private Table tabela;
    private ArrayList<Aluno> alunos;
    private Aluno aluno=null;
    private MyDialogg ver_dialog;
    private Table tabInscricao;
    private  int linhaSelecionada;
    
    public JDialog getVer_dialog(){
        return ver_dialog;
    }
    public Ver_Alunos(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Ver Alunos",this);
        container.add(title);
        this.addTable();
        this.setVisible(true);
    }
    
    public void addTable(){
        String colunas[]={"Nome","Apelido","BI","Idade","Classe","Sexo","Nível","Pagamento"};
        MyButtonn voltar=new MyButtonn("Voltar",false);
        voltar.setSize(85,25);
        voltar.addMouseListener(new Clique());
        tabela=new Table(colunas);
        alunos=Aluno_ctrl.getAlunos();
        
        /**Preenchimento dos dados da tabela ver aluno*/
        String dados[][]=new String[alunos.size()][colunas.length];
        for(int i=0;i<dados.length;i++){
            dados[i][0]=alunos.get(i).getNome();
            dados[i][1]=alunos.get(i).getApelido();
            dados[i][2]=alunos.get(i).getBI();
            //Calendar cal=Calendar.getInstance();
            //int idade=cal.get(Calendar.YEAR)-(Integer.parseInt(alunos.get(i).getNascimento().split("-")[0]));
            dados[i][3]=String.valueOf(MyProceduress.idade(alunos.get(i).getNascimento().split("-")[0]));
            Categoria cat=categoria_ctrl.getCategoria(alunos.get(i).getCategoria_id());
            dados[i][4]=cat.getClasse();
            dados[i][5]=alunos.get(i).getSexo();
            dados[i][6]=alunos.get(i).getNivel();
            dados[i][7]=Aluno_ctrl.pagamento(alunos.get(i).getId())[2];
        }
        
        tabela.setTableData(dados);
        JScrollPane src=new JScrollPane(tabela);
        src.setBackground(Color.blue);
        src.getViewport().setBackground(Color.white);
        container.add(src);
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
    
    
    public void verAlunos(JFrame frame){
        tabela.addMouseListener(
            new MouseAdapter(){
                public void mouseClicked(MouseEvent evento){
                    if(evento.getClickCount()==2){
                        linhaSelecionada=tabela.getSelectedRow();
                        for(Aluno a:alunos){
                            if(a.getBI().equals(tabela.getValueAt(linhaSelecionada,2).toString())){
                                aluno=a;
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
        alunos=Aluno_ctrl.getAlunos();
        for(Aluno a:alunos){
            if(a.getBI().equals(tabela.getValueAt(linhaSelecionada,2).toString())){
                aluno=a;
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
                        
        JPanel pan1=new JPanel(new GridLayout(10,1));
        pan1.setOpaque(false);
        pan1.add(MyProceduress.info("Nome",": "+aluno.getNome()));
        pan1.add(MyProceduress.info("Apelido",": "+aluno.getApelido()));
        pan1.add(MyProceduress.info("BI",": "+aluno.getBI()));
        pan1.add(MyProceduress.info("Idade",": "+MyProceduress.idade(aluno.getNascimento().split("-")[0])));
        pan1.add(MyProceduress.info("Sexo",": "+aluno.getSexo()));
        pan1.add(MyProceduress.info("Nível",": "+aluno.getNivel()));
        pan1.add(MyProceduress.info("Morada",": "+aluno.getMorada()));
        pan1.add(MyProceduress.info("Telefone",": "+aluno.getTelefone()));
        pan1.add(MyProceduress.info("Email",": "+aluno.getEmail()));
        pan1.add(MyProceduress.info("Pagamento",": "+Aluno_ctrl.pagamento(aluno.getId())[2]));
                        
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
                        
        tabInscricao=new Table(new String[]{"Categoria","Disciplina","Preco"});
        ArrayList<String[]> inscricao=Aluno_ctrl.inscricao(aluno.getId());
        String dados[][]=new String[inscricao.size()][3];
        for(int i=0;i<dados.length;i++){
            dados[i][0]=inscricao.get(i)[2];
            dados[i][1]=inscricao.get(i)[3];
            dados[i][2]=inscricao.get(i)[4];
        }
                        
        tabInscricao.setTableData(dados);
        JScrollPane src=new JScrollPane(tabInscricao);
        src.getViewport().setBackground(new Color(82,79,250).darker().darker());
                       
        JPanel panBtn=new JPanel(new FlowLayout());
        panBtn.setOpaque(false);
        MyButtonn inscrever=new MyButtonn("Inscrever",false);
        inscrever.addMouseListener(new Clique());
        MyButtonn removerI=new MyButtonn("Eliminar",false);
        removerI.addMouseListener(new Clique());
        panBtn.add(inscrever);
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
            }
            else if(e.getComponent().toString().contains("Modificar")){
                ModificarEstudante modE=new ModificarEstudante(Myclasscount.getFrame(),aluno,true);
                modE.setVisible(true);
            }else if(e.getComponent().toString().contains("Remover")){
                MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),2,"Deseja remover estudante?",true);
                if(dialog.getSimTeste()){
                    dialog.dispose();
                    if(Aluno_ctrl.delAluno(aluno.getId())){
                        updateComponents();
                        ver_dialog.dispose();
                        MyDialogg resp1=new MyDialogg(Myclasscount.getFrame(),1,"Removido com sucesso",true);
                    }else{
                        MyDialogg resp2=new MyDialogg(Myclasscount.getFrame(),1,"Ocorreu um erro !!",true);
                    }
                }
            }else if(e.getComponent().toString().contains("Inscrever")){
                Inscrever inscrever=new Inscrever(Myclasscount.getFrame(),aluno,true);
                inscrever.setVisible(true);
            }else if(e.getComponent().toString().contains("Eliminar")){
                MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),2,"Deseja cancelar inscriçao",true);
                if(dialog.getSimTeste()){
                    dialog.dispose();
                    int linha=tabInscricao.getSelectedRow();
                    if(linha>-1){
                        String nome=tabInscricao.getValueAt(linha,1).toString();
                        if(Aluno_ctrl.delAlunoInscricao(aluno.getId(), nome)){
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.Aluno_ctrl;
import com.myclasscount.control.CtrlGeral;
import com.myclasscount.control.Mensalidade_ctrl;
import com.myclasscount.model.Aluno;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author CUINIC4
 */
public class MyProceduress {
    private MyButtonn btns[];

    public MyButtonn[] getBtns() {
        return btns;
    }
  
    public static Panel barName(String title,Container panel){
        Panel pane=new Panel(Color.black,false);
        pane.setLayout(null);
        pane.invisible(true,true);
        JLabel label=new JLabel(title);
        label.setForeground(Color.white);
        JPanel pan=new JPanel();
        pan.setBackground(new Color(30,80,172));
        pane.add(label);
        pane.add(pan);
        
     
        new Thread(
            new Runnable(){
                public void run(){
                    while(true){
                        try{
                            Thread.sleep(50);
                        }catch(InterruptedException e){
                            System.out.println("Erro: "+e.getMessage());
                        }
                        int x=100,y=50;
                        label.setBounds(0,0,120,20);
                        pan.setBounds(0,20,panel.getWidth()-x,10);
                        pane.setSize(panel.getWidth()-100,35);
                        panel.repaint();
                    }
                }
            }
        ).start();
        
        return pane;
    }
    
    public static Panel barName(String title){
        Panel pane=new Panel(Color.black,false);
        pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
        pane.invisible(true,true);
        JLabel label=new JLabel(title);
        label.setForeground(Color.white);
        JPanel pan=new JPanel();
        pan.setBackground(new Color(205,190,216).brighter());
        pane.add(label);
        pane.add(pan);
        
        return pane;
    }
    
   
    public  Panel mainPane(String antes, String depois, Container container){
        /**main panel*/
        Panel pane = new Panel(new Color(82,79,250),false);
        pane.setBorderColor(Color.white);
        pane.setLayout(null);
       
        /** btns(voltar e proximo) buttons*/
        btns=new MyButtonn[]{new MyButtonn("Voltar",false),new MyButtonn("Proximo",false)};
        btns[0].addActionListener(new Clique(antes));
        btns[0].setActionCommand("Voltar");
        btns[1].addActionListener(new Clique(depois));
        btns[1].setActionCommand("Proximo");
        btns[0].setVisible(false);
        btns[1].setVisible(false);
        container.add(pane);
        container.add(btns[0]);
        container.add(btns[1]);
        
        new Thread(
            new Runnable(){
                public void run(){
                    
                    while(true){
                        try{
                            Thread.sleep(50);
                        }catch(InterruptedException e){
                            System.out.println("Erro: "+e.getMessage());
                        }
                        int x=0,y=0;
                        
                        /** pane location */
                        Component cmp=container.getComponent(0);
                        y=cmp.getY()+cmp.getHeight()+20;
                        pane.setSize((int)(cmp.getWidth()*0.75),(container.getHeight()-(y+100)));
                        x=(cmp.getWidth()/2+cmp.getX())-(pane.getWidth()/2);
                        pane.setLocation(x,y);  
                        
                        /** btns Location */
                        getBtns()[1].setSize(85,25);
                        x=pane.getWidth()+pane.getX()-getBtns()[1].getWidth();
                        y=pane.getHeight()+pane.getY()+15;
                        getBtns()[1].setLocation(x,y);
                        x-=getBtns()[1].getWidth()+15;
                        getBtns()[0].setBounds(x,y,getBtns()[1].getWidth(),getBtns()[1].getHeight());
                       
                        container.repaint();
                    }
                }
            }
        ).start();
        
        return pane;
    }

    public static JPanel info(String n,String v){
        JPanel panel=new JPanel(new GridLayout(1,2));
        panel.setOpaque(false);
        JLabel name=new JLabel(n);
        name.setForeground(Color.white);
        name.setFont(new Font("Arial",Font.BOLD,18));
        JLabel value=new JLabel(v);
        value.setForeground(Color.white);
        value.setFont(new Font("Arial",Font.PLAIN,18));
        value.setToolTipText(v);
        panel.add(name);
        panel.add(value);
        return panel;
    }
    
    public static JPanel info(String n,String v,Color f,int size){
        JPanel panel=new JPanel(new GridLayout(1,2));
        panel.setOpaque(false);
        JLabel name=new JLabel(n);
        name.setForeground(f);
        name.setFont(new Font("Arial",Font.BOLD,size));
        JLabel value=new JLabel(v);
        value.setForeground(f);
        value.setFont(new Font("Arial",Font.PLAIN,size));
        value.setToolTipText(v);
        panel.add(name);
        panel.add(value);
        return panel;
    }
    
    public static MyDialogg ver_dialog(Aluno aluno,JFrame frame){
        MyDialogg ver_dialog=new MyDialogg(frame,true);
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
                        
        JPanel lado2=new JPanel(new BorderLayout());
        lado2.setBorder(new EmptyBorder(15,15,15,15));
        lado2.setOpaque(false);
                        
        Table tabInscricao=new Table(new String[]{"Categoria","Disciplina","Preco"});
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
                       
        JPanel titulo=new JPanel();
        titulo.setOpaque(false);
        JLabel texto=new JLabel("Inscricoes",SwingConstants.CENTER);
        texto.setForeground(Color.white);
        texto.setFont(new Font("Arial",Font.BOLD,18));
        titulo.add(texto);
                        
        lado2.add(titulo,BorderLayout.NORTH);
        lado2.add(src,BorderLayout.CENTER);

        lado1.add(pan1,BorderLayout.CENTER);
                        
        ver_dialog.add(lado1);
        ver_dialog.add(lado2);
        ver_dialog.setVisible(true);
        
        return ver_dialog;
    }
    
    public static void updateVerAlunos(){
        Ver_Alunos v=(Ver_Alunos)CtrlGeral.getTela("verAlunos");
        v.updateComponents();
    }
    
    public static void updateVerProfessores(){
        Ver_Professores v=(Ver_Professores)CtrlGeral.getTela("verProfessores");
        v.updateComponents();
    }
    
    public static void updateVerTurmas(){
        Ver_Turmas v=(Ver_Turmas)CtrlGeral.getTela("verTurmas");
        v.updateComponents();
    }
    
    public static void updateMainFrame(){
        MainFramee v=(MainFramee)CtrlGeral.getTela("mainFrame");
        v.updateComponents();
    }
    
    public static void updateVerObservacoes(){
        Ver_Observacoes verO=(Ver_Observacoes)CtrlGeral.getTela("verObservacoes");
        verO.updateComponents();
    }
    
    public static void updateCategoria(){
        Ver_Categoria v=(Ver_Categoria)CtrlGeral.getTela("categoria");
        v.updateComponents();
    }
    
    public static void updateAdicionarAluno(){
        AdicionarAluno add=(AdicionarAluno)CtrlGeral.getTela("addStudent");
        add.updateComponents();
    }
    
    public static void updateAdicionarProfessor(){
        AdicionarProfessor add=(AdicionarProfessor)CtrlGeral.getTela("adicionarProfessor");
        add.updateComponents();
    }
    
    public static void updateCriarTurma(){
        CriarTurma criar=(CriarTurma)CtrlGeral.getTela("criarTurma");
        criar.updateComponents();
    }
    
    public static void updateVerMensalidade(){
        Ver_Mensalidade verM=(Ver_Mensalidade)CtrlGeral.getTela("verMensalidade");
        verM.updateComponents();
    }
    
    
    private class Clique implements ActionListener{
        private String dir; 
        
        public Clique(String dir){
            this.dir=dir;
        }
        
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("Voltar")){
                System.out.println("Voltar");
                System.out.println("dir :"+dir);
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),dir);
            } else if(e.getActionCommand().equals("Proximo")){
                System.out.println("Proximo");
                System.out.println("dir :"+dir);
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),dir);
            }      
        }
    }
    
    public static int idade(String ano){
        Calendar cal=Calendar.getInstance();
        return cal.get(Calendar.YEAR)-(Integer.parseInt(ano));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.Aluno_ctrl;
import com.myclasscount.control.CtrlGeral;
import com.myclasscount.model.Aluno;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
        JLabel texto=new JLabel("inscrições",SwingConstants.CENTER);
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
    
    public static void ActualizarLicensa(JFrame frame){
        MyDialogg actualizar=new MyDialogg(frame,true);
        Color color=Color.red;
        Font font=new Font("Arial",Font.BOLD,22);
        actualizar.setUndecorated(true);
        actualizar.setLayout(new BorderLayout());
        actualizar.setSize(550,200);
        actualizar.setLocationRelativeTo(frame);
        
        JPanel pan1=new JPanel(new GridLayout());
        pan1.setBackground(color);
        pan1.setBorder(new EmptyBorder(30,30,0,30));
        
        JLabel lb1=new JLabel("Acesso bloqueado, a sua Licensa Expirou!",SwingConstants.CENTER);
        lb1.setFont(font);
        lb1.setForeground(Color.white);
        pan1.add(lb1);
        
        JPanel pan2=new JPanel(new GridLayout(2,1));
        pan2.setBackground(color);
        pan2.setBorder(new EmptyBorder(30,30,30,30));
        JLabel lb2=new JLabel("Insira um novo codigo",SwingConstants.CENTER);
        lb2.setFont(font);
        lb2.setForeground(Color.white);
        
        JPanel pan3=new JPanel(new BorderLayout(10,0));
        pan3.setBackground(color);
        JTextField codigo=new JTextField();
        JButton btn=new JButton("Gravar");
        btn.addActionListener(
            (ActionEvent e)->{
                String array[]=CtrlGeral.decryptar(codigo.getText());
                if(array==null || array.length!=6){
                    MyDialogg dialog=new MyDialogg(frame,1,"Erro, codigo invalido", true);
                    codigo.setText("");
                }else{
                    String userName=System.getProperty("user.name");
                    String osHome=System.getProperty("user.home");
                    boolean teste=osHome.toLowerCase().equals(array[5]);
                    teste=teste && userName.toLowerCase().equals(array[3]);
                    try {
                        Date dataFinal=new SimpleDateFormat("ddMMyyyy").parse(array[2]);
                        teste=teste && dataFinal.after(Calendar.getInstance().getTime());
                    } catch (ParseException ex) {
                        teste=false;
                    }
                    if(teste){
                        CtrlGeral.gravarLicenca(codigo.getText());
                        MyDialogg dialog=new MyDialogg(frame,1,"Codigo gravado com sucesso!", true);
                        actualizar.dispose();
                    }else{
                        MyDialogg dialog=new MyDialogg(frame,1,"Erro, codigo invalido", true);
                        codigo.setText("");
                    }
                }
            }
        );
        btn.setBackground(Color.white);
        pan3.add(codigo,BorderLayout.CENTER);
        pan3.add(btn,BorderLayout.EAST);
        
        pan2.add(lb2);
        pan2.add(pan3);
        
        JButton sair=new JButton("Sair");
        sair.setBackground(Color.white);
        sair.addActionListener(
            (ActionEvent e)->{
                System.exit(0);
            }
        );
        
        actualizar.add(pan1,BorderLayout.NORTH);
        actualizar.add(pan2,BorderLayout.CENTER);
        actualizar.add(sair,BorderLayout.SOUTH);
        actualizar.setVisible(true);
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
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),dir);
            } else if(e.getActionCommand().equals("Proximo")){
                System.out.println("Proximo");
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),dir);
            }      
        }
    }
    
    public static int idade(String ano){
        Calendar cal=Calendar.getInstance();
        return cal.get(Calendar.YEAR)-(Integer.parseInt(ano));
    }
    
}

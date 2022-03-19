/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.Aluno_ctrl;
import com.myclasscount.control.CtrlGeral;
import com.myclasscount.control.categoria_ctrl;
import com.myclasscount.model.Aluno;
import com.myclasscount.model.Categoria;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author CUINIC4
 */
public class AdicionarAluno extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private MyButtonn btns[];
    private Panel title;
    private MyProceduress myProc;
    private JTextField txtF[];
    private JTextArea andress;
    private JComboBox combbx[];
    private JRadioButton male,female;
    private JDateChooser data;
    
    public AdicionarAluno(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        this.title=MyProceduress.barName("Cadastrar Alunos",this);
        container.add(title);
        myProc=new MyProceduress();
        addComponentToMainPane(myProc.mainPane("mainFrame","verAlunos",container));
        setVisible(true);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(13,1));
        pan1.invisible(true, true);
        Panel pan2=new Panel(Color.black,false);
        pan2.setLayout(null);
        pan2.invisible(true, true);
        
         
        myProc.getBtns()[1].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[1].addMouseListener(new Clique());
        myProc.getBtns()[0].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[0].addMouseListener(new Clique());
     
        txtF=new JTextField[]{new JTextField(),new JTextField(),new JTextField(),
                           new JTextField(),new JTextField(),new JTextField()};
        
        andress=new JTextArea();
        andress.setLineWrap(true);
        JScrollPane morada=new JScrollPane(andress);
        
        String nivel[]={"Primario","Secondario","Tecnico","Universitario"};
        ArrayList<Categoria> categorias=categoria_ctrl.getCategorias();
        combbx=new JComboBox[]{new JComboBox(nivel),new JComboBox()};
        for(int i=0;i<categorias.size();i++){
            if(nivel[0].equals(categorias.get(i).getTipoEnsino())){
                combbx[1].addItem(categorias.get(i).getNome());
            }
        }
        combbx[0].addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    combbx[1].removeAllItems();
                    ArrayList<Categoria> categorias=categoria_ctrl.getCategorias();
                    for(int i=0;i<categorias.size();i++){
                        if(combbx[0].getSelectedItem().equals(categorias.get(i).getTipoEnsino())){
                            combbx[1].addItem(categorias.get(i).getNome());
                        }
                    }
                }
            }
        );
        
        male = new JRadioButton("Masculino");
        male.setOpaque(false);
        male.setForeground(Color.white);
        female=new JRadioButton("Feminino");
        female.setOpaque(false);
        female.setForeground(Color.white);
        ButtonGroup btnG=new ButtonGroup();
        btnG.add(male);btnG.add(female);
        Panel sexo=new Panel(Color.black,false);
        sexo.setLayout(new GridLayout(1,2));
        sexo.invisible(true,true);
        sexo.add(male);sexo.add(female);
        
        JLabel labels[]={new JLabel("Nome"),new JLabel("Apelido"),new JLabel("Nr BI"),new JLabel("Nascimento"),
                  new JLabel("Sexo"),new JLabel("Nivel Academico"),new JLabel("Morada"),new JLabel("Telefone"),
                  new JLabel("Email"),new JLabel("Categoria"),new JLabel("Mês"),new JLabel("Dia"),new JLabel("Ano")};
        for(int i=0;i<labels.length;i++){labels[i].setForeground(Color.white);}
        
        data=new JDateChooser();
        Calendar cal=Calendar.getInstance();
        data.setDate(cal.getTime());
        data.setLocale(Locale.forLanguageTag("pt-br"));
        data.setDateFormatString("dd/MM/yyyy");
        
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(txtF[1]);
        pan1.add(labels[2]); pan1.add(txtF[2]);
        pan1.add(labels[3]); pan1.add(data);
        pan1.add(labels[4]); pan1.add(sexo);
        pan1.add(labels[5]); pan1.add(combbx[0]);
        
        pan2.add(labels[6]); pan2.add(morada);
        pan2.add(labels[7]); pan2.add(txtF[3]);
        pan2.add(labels[8]); pan2.add(txtF[4]);
        pan2.add(labels[9]); pan2.add(combbx[1]);
        
        mainPane.add(pan1);
        mainPane.add(pan2);
        
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
                        pan1.setBounds(50,25,mainPane.getWidth()/2-75,mainPane.getHeight()-50);
                        pan2.setBounds(mainPane.getWidth()/2+25,25,mainPane.getWidth()/2-75,mainPane.getHeight()-50);

                        labels[9].setBounds(labels[5].getBounds());
                        combbx[1].setBounds(combbx[0].getBounds());
                        
                        labels[8].setBounds(labels[4].getBounds());
                        labels[8].setLocation(labels[8].getX(),(labels[8].getY()));
                        txtF[4].setBounds(sexo.getBounds());
                        txtF[4].setLocation(txtF[4].getX(),(txtF[4].getY()));
                        
                        labels[7].setBounds(labels[3].getBounds());
                        txtF[3].setBounds(pan1.getComponent(7).getBounds());
                        
                        
                        labels[6].setBounds(labels[0].getBounds());
                        morada.setBounds(0,txtF[0].getY(),txtF[0].getWidth(),5*txtF[0].getHeight());
                      
                        myProc.getBtns()[0].setVisible(true);
                        myProc.getBtns()[1].setVisible(true);
                        mainPane.revalidate();
                    }
                }
            }
        ).start();
    }
    
    public void updateComponents(){
        removeAll();
        container.add(title);
        addComponentToMainPane(myProc.mainPane("mainFrame","verAlunos",container));
    }
    
    
    private class Clique extends MouseAdapter{
        
        public void mouseClicked(MouseEvent e){
            if(e.getComponent().toString().contains("Proximo")){
                Aluno aluno=new Aluno();
                aluno.setNome(txtF[0].getText());
                aluno.setApelido(txtF[1].getText());
                aluno.setBI(txtF[2].getText());
                
                try{
                    aluno.setNascimento(new SimpleDateFormat("yyyy-MM-dd").format(data.getDate()));
                }catch(NullPointerException n){
                    System.out.println("Erro, data nula"+n.getMessage());
                    Calendar cal=Calendar.getInstance();
                    aluno.setNascimento(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
                }
                
                if(male.isSelected()){
                    aluno.setSexo("M");
                }else{
                    aluno.setSexo("F");
                }
                
                aluno.setNivel((String) combbx[0].getSelectedItem());
                aluno.setMorada(andress.getText());
                aluno.setTelefone(txtF[3].getText());
                aluno.setEmail(txtF[4].getText());
                String catID=String.valueOf(combbx[1].getSelectedItem());
                
                if(!catID.equals("null")){
                    aluno.setCategoria_id(categoria_ctrl.getCategoria(catID).getId());
                }
                
                
                if(!Aluno_ctrl.setAluno(aluno)){
                    if(Aluno_ctrl.getErro().contains("nome")){
                        txtF[0].setText("");
                        txtF[0].grabFocus();
                    }else if(Aluno_ctrl.getErro().contains("apelido")){
                        txtF[1].setText("");
                        txtF[1].grabFocus();
                    }else if(Aluno_ctrl.getErro().contains("BI")){
                        txtF[2].setText("");
                        if(Aluno_ctrl.getErro().contains("aleatorio")){
                            Random r=new Random();
                            Calendar cal=Calendar.getInstance();
                            String bi=new SimpleDateFormat("ddMMyyyy").format(cal.getTime());
                            bi+=(r.nextInt(1000)+1000)+""+aluno.getNome().charAt(0);
                            txtF[2].setText(bi);
                            aluno.setBI(bi);
                        }
                        txtF[2].grabFocus();
                    }else if(Aluno_ctrl.getErro().contains("telefone")){
                        txtF[3].setText("");
                        txtF[3].grabFocus();
                    }
                }else if(catID.equals("null")){
                    MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Erro, categoria nao definida", true);
                }else{
                    
                    MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Cadastrado com sucesso !!", true);
                    
                    for(JTextField txtF:txtF){  txtF.setText(""); }
                    
                    for(JComboBox combbx:combbx){ combbx.setSelectedIndex(0); }
                    
                    andress.setText("");
                    
                    Ver_Alunos v=(Ver_Alunos)CtrlGeral.getTela("verAlunos");
                    v.updateComponents();
                }
            
            }
        }
        
    }   
}

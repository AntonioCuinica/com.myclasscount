/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.CtrlGeral;
import com.myclasscount.control.Disciplina_ctrl;
import com.myclasscount.control.Professor_ctrl;
import com.myclasscount.model.Disciplina;
import com.myclasscount.model.Professor;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author CUINIC4
 */
public class AdicionarProfessor extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private MyButtonn btns[];
    private Panel title;
    private MyProceduress myProc;
    private JTextField txtF[];
    private JTextArea andress;
    private JComboBox combbx;
    private JCheckBox checks[];
    private JRadioButton male;
    private JRadioButton female;
    private JDateChooser data;
    
    public AdicionarProfessor(){
        setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Cadastrar Professor",this);
        container.add(title);
        myProc=new MyProceduress();
        addComponentToMainPane(myProc.mainPane("mainFrame","criarSenha",container));
        setVisible(true);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(15,1));
        pan1.invisible(true, true);
        
        Panel pan2=new Panel(Color.black,false);
        pan2.setLayout(new BoxLayout(pan2,BoxLayout.Y_AXIS));
        pan2.invisible(true, true);
        
        Panel pMorada=new Panel(Color.black,false);
        pMorada.setLayout(new BorderLayout());
        pMorada.invisible(true, true);
        
        Panel pTxtF=new Panel(Color.black,false);
        pTxtF.setLayout(new GridLayout(6,1));
        pTxtF.invisible(true, true);
        
        Panel pDisc=new Panel(Color.black,false);
        pDisc.setLayout(new BorderLayout());
        pDisc.invisible(true, true);
        
        myProc.getBtns()[1].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[1].addMouseListener(new Clique());
        myProc.getBtns()[0].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[0].addMouseListener(new Clique());
     
        txtF=new JTextField[]{new JTextField(),new JTextField(),new JTextField(),
                           new JTextField(),new JTextField(),new JTextField(),
                           new JTextField()};
        andress=new JTextArea();
        andress.setLineWrap(true);
        JScrollPane morada=new JScrollPane(andress);
        
        String nivel[]={"Primario","Secondario","Tecnico","Universitario"};
        combbx=new JComboBox(nivel);
        
        ArrayList<Disciplina> disciplinas=Disciplina_ctrl.getDisciplinas();
        checks=new JCheckBox[disciplinas.size()];
        JPanel checkDiscs=new JPanel();
        checkDiscs.setLayout(new BoxLayout(checkDiscs,BoxLayout.Y_AXIS));
        for(int i=0;i<checks.length;i++){
            checks[i]=new JCheckBox(disciplinas.get(i).getNome());
            checkDiscs.add(checks[i]);
        }
        JScrollPane disc=new JScrollPane(checkDiscs);
        
        male=new JRadioButton("Masculino");
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
                  new JLabel("Sexo"),new JLabel("Nivel Academico"),new JLabel("Salario"),new JLabel("Morada"),new JLabel("NUIT"),
                  new JLabel("Telefone"),new JLabel("Email"),new JLabel("Disciplina"),new JLabel("Mês"),new JLabel("Dia"),new JLabel("Ano")};
        for(int i=0;i<labels.length;i++){labels[i].setForeground(Color.white);}
        
        data=new JDateChooser();
        Calendar cal=new GregorianCalendar();
        data.setDate(cal.getTime());
        data.setLocale(Locale.forLanguageTag("pt-br"));
        data.setDateFormatString("dd/MM/yyyy");
        
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(txtF[1]);
        pan1.add(labels[2]); pan1.add(txtF[2]);
        pan1.add(labels[3]); pan1.add(data);
        pan1.add(labels[4]); pan1.add(sexo);
        pan1.add(labels[5]); pan1.add(combbx);
        pan1.add(labels[6]);pan1.add(txtF[3]);
        
        
        pMorada.add(labels[7],BorderLayout.NORTH); pMorada.add(morada,BorderLayout.CENTER);
        
        pan2.add(pMorada);
        
        pTxtF.add(labels[8]); pTxtF.add(txtF[4]);
        pTxtF.add(labels[9]); pTxtF.add(txtF[5]);
        pTxtF.add(labels[10]); pTxtF.add(txtF[6]);
        
        pan2.add(pTxtF);
        
        pDisc.add(labels[11],BorderLayout.NORTH); pDisc.add(disc,BorderLayout.CENTER);
        
        pan2.add(pDisc);
        
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
                        pan2.setBounds(mainPane.getWidth()/2+25,25,pan1.getWidth(),pan1.getHeight());
//                        labels[11].setBounds(labels[5].getBounds());
//                        disc.setBounds(0,combbx.getY(),combbx.getWidth(),3*combbx.getHeight());
//                        labels[10].setBounds(labels[4].getBounds());
//                        txtF[6].setBounds(sexo.getBounds());
//                        labels[9].setBounds(labels[3].getBounds());
//                        txtF[5].setBounds(pan1.getComponent(7).getBounds());
//                        labels[8].setBounds(labels[2].getBounds());
//                        txtF[4].setBounds(txtF[2].getBounds());
//                        labels[7].setBounds(labels[0].getBounds());
//                        morada.setBounds(0,txtF[0].getY(),txtF[0].getWidth(),3*txtF[0].getHeight());
                        
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
        addComponentToMainPane(myProc.mainPane("mainFrame","criarSenha",container));
    }
    
    private class Clique extends MouseAdapter{
        
        public void mouseClicked(MouseEvent e){
            if(e.getComponent().toString().contains("Proximo")){
                Professor professor=new Professor();
                professor.setNome(txtF[0].getText());
                professor.setApelido(txtF[1].getText());
                professor.setBI(txtF[2].getText());
                try{
                    professor.setNascimento(new SimpleDateFormat("yyyy-MM-dd").format(data.getDate()));
                }catch(NullPointerException n){
                    System.out.println("Erro, data nula"+n.getMessage());
                    Calendar cal=Calendar.getInstance();
                    professor.setNascimento(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
                }
                if(male.isSelected()){
                    professor.setSexo("Masculino");
                }else{
                    professor.setSexo("Femenino");
                }
                
                professor.setNivel((String) combbx.getSelectedItem());
                
                String salario=txtF[3].getText();
                
                if(!Professor_ctrl.validarNumero(salario,"salario").equals("valido")){
                    professor.setSalario(-1);
                }else{
                    professor.setSalario(Double.parseDouble(salario));
                }
                
                professor.setNUIT(txtF[4].getText());
                professor.setMorada(andress.getText());
                professor.setTelefone(txtF[5].getText());
                professor.setEmail(txtF[6].getText());
                
                
                if(!Professor_ctrl.setProfessor(professor)){
                    if(Professor_ctrl.getErro().contains("nome")){
                        txtF[0].setText("");
                        txtF[0].grabFocus();
                    }else if(Professor_ctrl.getErro().contains("apelido")){
                        txtF[1].setText("");
                        txtF[1].grabFocus();
                    }else if(Professor_ctrl.getErro().contains("BI")){
                        txtF[2].setText("");
                        if(Professor_ctrl.getErro().contains("aleatorio")){
                            Random r=new Random();
                            Calendar cal=Calendar.getInstance();
                            String bi=new SimpleDateFormat("ddMMyyyy").format(cal.getTime());
                            bi+=(r.nextInt(1000)+1000)+""+professor.getNome().charAt(0);
                            txtF[2].setText(bi);
                            professor.setBI(bi);
                        }
                    }else if(Professor_ctrl.getErro().contains("salario")){
                        txtF[3].setText("");
                        txtF[3].grabFocus();
                    }else if(Professor_ctrl.getErro().contains("NUIT")){
                        txtF[4].setText("");
                        if(Professor_ctrl.getErro().contains("aleatorio")){
                            Random r=new Random();
                            Calendar cal=Calendar.getInstance();
                            String nuit=new SimpleDateFormat("ddMMyyyy").format(cal.getTime());
                            nuit+=(r.nextInt(1000)+1000)+""+nuit.charAt(0);
                            txtF[4].setText(nuit);
                            professor.setNUIT(nuit);
                        }
                    }else if(Professor_ctrl.getErro().contains("telefone")){
                        txtF[5].setText("");
                        txtF[5].grabFocus();
                    }
                }else{
                    
                    for(JCheckBox ch:checks){
                        if(ch.isSelected()){
                            Disciplina disciplina=Disciplina_ctrl.getDisciplina(ch.getText());
                            Professor_ctrl.inscricaoProfessorDisc(professor,disciplina.getId());
                        }
                    }
                    
                    for(JTextField txtF:txtF){  txtF.setText(""); }
                    
                    combbx.setSelectedIndex(0);
                    
                    andress.setText("");
                    
                    Ver_Professores v=(Ver_Professores)CtrlGeral.getTela("verProfessores");
                    v.updateComponents();
                    
                    CriarSenha cS=(CriarSenha)CtrlGeral.getTela("criarSenha");
                    cS.setProf_BI(professor.getBI());
                    
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"criarSenha");
                    
                }
            
            }
        }
    }
}

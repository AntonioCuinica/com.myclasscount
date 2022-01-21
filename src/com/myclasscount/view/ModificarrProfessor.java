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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author CUINIC4
 */
public class ModificarrProfessor extends JDialog {
    private Color backColor=new Color(0,24,242);
    private Panel back;
    private MyButtonn btns[];
    private Panel title;
    private MyProceduress myProc;
    private JTextField txtF[];
    private JTextArea andress;
    private JComboBox combbx;
    private JList discList;
    JCheckBox checks[];
    private JComboBox data[];
    private JRadioButton male;
    private JRadioButton female;
    private Professor professor;
    
    public ModificarrProfessor(JFrame frame,Professor professor,boolean modal){
        super(frame,modal);
        setSize(700,550);
        setUndecorated(true);
        setOpacity(0.92f);
        setMinimumSize(new Dimension(700,500));
        setLocationRelativeTo(frame);
        this.professor=professor;
        back=new Panel(backColor.darker(),true,true);
        back.setLayout(null);
        back.setBorderColor(Color.WHITE);
        title=MyProceduress.barName("Modificar Professor",back);
        back.add(title);
        title.setVisible(false);
        myProc=new MyProceduress();
        addComponentToMainPane(myProc.mainPane("verProfessores","verProfessores", back));
        setContentPane(back);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(15,1));
        pan1.invisible(true, true);
        Panel pan2=new Panel(Color.black,false);
        pan2.setLayout(null);
        pan2.invisible(true, true);
        
        myProc.getBtns()[1].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[1].addMouseListener(new Clique());
        myProc.getBtns()[0].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[0].addMouseListener(new Clique());
     
        txtF=new JTextField[]{new JTextField(professor.getNome()),new JTextField(professor.getApelido()),new JTextField(professor.getBI()),
                           new JTextField(""+professor.getSalario()),new JTextField(professor.getNUIT()),new JTextField(professor.getTelefone()),
                           new JTextField(professor.getEmail())};
        andress=new JTextArea(professor.getMorada());
        andress.setLineWrap(true);
        JScrollPane morada=new JScrollPane(andress);
        
        String nivel[]={"Primario","Secondario","Tecnico","Universitario"};
        combbx=new JComboBox(nivel);
        
        ArrayList<Disciplina> disciplinas=Disciplina_ctrl.getDisciplinas();
        ArrayList<Disciplina> discIns=Disciplina_ctrl.getDisciplinas(professor.getId());
        checks=new JCheckBox[disciplinas.size()];
        JPanel checkDiscs=new JPanel();
        checkDiscs.setLayout(new BoxLayout(checkDiscs,BoxLayout.Y_AXIS));
        for(int i=0;i<checks.length;i++){
            checks[i]=new JCheckBox(disciplinas.get(i).getNome());
            checkDiscs.add(checks[i]);
            for(int j=0;j<discIns.size();j++){
                if(disciplinas.get(i).getNome().equals(discIns.get(j).getNome())){
                    checks[i].setSelected(true);
                    checks[i].setEnabled(false);
                }
            }
        }
        
        JScrollPane disc=new JScrollPane(checkDiscs);
        male=new JRadioButton("Masculino");
        male.setOpaque(false);
        male.setForeground(Color.white);
        female=new JRadioButton("Feminino");
        female.setOpaque(false);
        female.setForeground(Color.white);
        if(professor.getSexo().contains("F")){
            female.setSelected(true);
        }else{
            male.setSelected(true);
        }
        
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
        
        String mes[]={"Janeiro", "Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
        String dia[]=new String[31];
        
        for(int i=0;i<dia.length;i++){
            dia[i]=""+(i+1);
        }
        
        String ano[]=new String[30];
        for(int i=0;i<ano.length;i++){
            ano[i]=""+(i+1990);
        }
        
        data=new JComboBox[]{new JComboBox(mes),new JComboBox(dia),new JComboBox(ano)};
        String dt[]=null;
        if(professor.getNascimento().contains("-")){
            dt=professor.getNascimento().split("-");
        }else if(professor.getNascimento().contains("/")){
            dt=professor.getNascimento().split("/");
        }
        
        System.out.println("modifcar nascimento prof: "+professor.getNascimento());
        data[0].setSelectedItem(dt[1]);
        data[1].setSelectedItem(dt[2]);
        data[2].setSelectedItem(dt[0]);
        JPanel dataN=new JPanel(new GridLayout(1,3,5,1));
        dataN.setOpaque(false);
        dataN.add(labels[12]);
        dataN.add(labels[13]);
        dataN.add(labels[14]);
        JPanel dataN1=new JPanel(new GridLayout(1,3,5,1));
        dataN1.setOpaque(false);
        dataN1.add(data[0]);
        dataN1.add(data[1]);
        dataN1.add(data[2]);
        
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(txtF[1]);
        pan1.add(labels[2]); pan1.add(txtF[2]);
        pan1.add(labels[3]); pan1.add(dataN);pan1.add(dataN1);
        pan1.add(labels[4]); pan1.add(sexo);
        pan1.add(labels[5]); pan1.add(combbx);
        pan1.add(labels[6]);pan1.add(txtF[3]);
        
        pan2.add(labels[7]); pan2.add(morada);
        pan2.add(labels[8]); pan2.add(txtF[4]);
        pan2.add(labels[9]); pan2.add(txtF[5]);
        pan2.add(labels[10]); pan2.add(txtF[6]);
        pan2.add(labels[11]); pan2.add(disc);
        
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
                        x=getWidth()/2-title.getWidth()/2;
                        title.setLocation(x,y);
                        pan1.setBounds(50,25,mainPane.getWidth()/2-75,mainPane.getHeight()-50);
                        pan2.setBounds(mainPane.getWidth()/2+25,25,mainPane.getWidth()/2-75,mainPane.getHeight()-50);
                        labels[11].setBounds(labels[5].getBounds());
                        disc.setBounds(0,combbx.getY(),combbx.getWidth(),3*combbx.getHeight());
                        labels[10].setBounds(labels[4].getBounds());
                        txtF[6].setBounds(sexo.getBounds());
                        labels[9].setBounds(labels[3].getBounds());
                        txtF[5].setBounds(pan1.getComponent(7).getBounds());
                        labels[8].setBounds(labels[2].getBounds());
                        txtF[4].setBounds(txtF[2].getBounds());
                        labels[7].setBounds(labels[0].getBounds());
                        morada.setBounds(0,txtF[0].getY(),txtF[0].getWidth(),3*txtF[0].getHeight());
                        
                        myProc.getBtns()[0].setVisible(true);
                        myProc.getBtns()[1].setVisible(true);
                        mainPane.revalidate();
                    }
                }
            }
        ).start();
    }
    
    private class Clique extends MouseAdapter{
        
        public void mouseClicked(MouseEvent e){
            if(e.getComponent().toString().contains("Proximo")){
                professor.setNome(txtF[0].getText());
                professor.setApelido(txtF[1].getText());
                professor.setBI(txtF[2].getText());
                professor.setNascimento((data[2].getSelectedItem()+"/"+(data[0].getSelectedIndex()+1)+"/"+data[1].getSelectedItem()));

                if(male.isSelected()){
                    professor.setSexo("M");
                }else{
                    professor.setSexo("F");
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
                
                
                if(!Professor_ctrl.updateProfessor(professor)){
                    if(Professor_ctrl.getErro().contains("nome")){
                        txtF[0].setText("");
                        txtF[0].grabFocus();
                    }else if(Professor_ctrl.getErro().contains("apelido")){
                        txtF[1].setText("");
                        txtF[1].grabFocus();
                    }else if(Professor_ctrl.getErro().contains("BI")){
                        txtF[2].setText("");
                        txtF[2].grabFocus();
                    }else if(Professor_ctrl.getErro().contains("salario")){
                        txtF[3].setText("");
                        txtF[3].grabFocus();
                    }else if(Professor_ctrl.getErro().contains("NUIT")){
                        txtF[4].setText("");
                        txtF[4].grabFocus();
                    }else if(Professor_ctrl.getErro().contains("telefone")){
                        txtF[5].setText("");
                        txtF[5].grabFocus();
                    }
                }else{
                    MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Modificado com sucesso !!", true);
                    
                    for(JCheckBox ch:checks){
                        if(ch.isSelected()){
                            Disciplina disciplina=Disciplina_ctrl.getDisciplina(ch.getText());
                            Professor_ctrl.inscricaoProfessorDisc(professor,disciplina.getId());
                        }
                    }
                    
                    for(JTextField txtF:txtF){  txtF.setText(""); }
                    
                    combbx.setSelectedIndex(0);
                    
                    andress.setText("");
                    
                    Ver_Professores v=(Ver_Professores)Myclasscount.getContainer().getComponent(3);
                    v.updateVer_dialog(Myclasscount.getFrame());
                    
                    dispose();
                }
            
            }else if(e.getComponent().toString().contains("Voltar")){
                dispose();
            }
        }
    }
}

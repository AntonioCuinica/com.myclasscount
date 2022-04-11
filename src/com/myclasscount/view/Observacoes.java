/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.CtrlGeral;
import com.myclasscount.control.Observacao_ctrl;
import com.myclasscount.model.Aluno;
import com.myclasscount.model.Observacao;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author CUINIC4
 */
public class Observacoes extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private Panel title;
    private MyProceduress myProc;
    private JTextField txtF=new JTextField();
    private JTextArea note=new JTextArea();
    private Aluno aluno;
    
    public void setAluno(Aluno aluno){
        this.aluno=aluno;
    }
    
    public Observacoes(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Observaçoes",this);
        container.add(title);
        myProc=new MyProceduress();
        this.addComponentToMainPane(myProc.mainPane("verTurmas2","", container));
        this.setVisible(true);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(null);
        pan1.invisible(true, true);

        myProc.getBtns()[1].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[1].addMouseListener(new Clique());
        myProc.getBtns()[0].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[0].addMouseListener(new Clique());
       
        txtF=new JTextField();
        note=new JTextArea();
        note.setLineWrap(true);
        MyButtonn gravar=new MyButtonn("Gravar",false);
        gravar.addMouseListener(new Clique());
        JLabel labels[]={new JLabel("Titulo"),new JLabel("Nota")};
        
        for(JLabel lb:labels)lb.setForeground(Color.white);
        
        pan1.add(labels[0]); pan1.add(txtF);
        pan1.add(labels[1]); pan1.add(note);
        pan1.add(gravar);
        
        mainPane.add(pan1);
        
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
                        pan1.setSize((int)(mainPane.getWidth()*0.75),mainPane.getHeight()-50);
                        x=(mainPane.getWidth()/2)-(pan1.getWidth()/2);
                        y=(mainPane.getHeight()/2)-(pan1.getHeight()/2);
                        pan1.setLocation(x,y);
                        y=40;
                        labels[0].setBounds(0,0,pan1.getWidth(),(int)(pan1.getHeight()*0.1));
                        txtF.setBounds(0,labels[0].getY()+y,pan1.getWidth(),(int)(pan1.getHeight()*0.1));
                        labels[1].setBounds(0,txtF.getY()+y,pan1.getWidth(),(int)(pan1.getHeight()*0.1));
                        note.setBounds(0,labels[1].getY()+y,pan1.getWidth(),(int)(pan1.getHeight()*0.5));
                        gravar.setBounds(0,note.getHeight()+note.getY()+10,pan1.getWidth(),(int)(pan1.getHeight()*0.1));
                        
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
            if(e.getComponent().toString().contains("Gravar")){
                if(txtF.getText().isEmpty()){
                    MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Erro, titulo vazio",true);
                    txtF.grabFocus();
                }else if(note.getText().isEmpty()){
                    MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Erro, nota vazia",true);
                    note.grabFocus();
                }else{
                    Observacao observ=new Observacao();
                    if(CtrlGeral.getProfessor_logado()!=null){
                        observ.setProfessor_id(CtrlGeral.getProfessor_logado().getId());
                    }
                    if(aluno!=null){
                        observ.setAluno_id(aluno.getId());
                    }
                    observ.setTitulo(txtF.getText());
                    observ.setNota(note.getText());
                    Observacao_ctrl.setObservacao(observ);
                    MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Observaçao gravada !!",true);
                    MyProceduress.updateVerObservacoes();
                    txtF.setText("");
                    note.setText("");
                }
            }else if(e.getComponent().toString().contains("Proximo")){
                if(txtF.getText().isEmpty() && note.getText().isEmpty()){
                    Ver_Observacoes v=(Ver_Observacoes)CtrlGeral.getTela("verObservacoes");
                    v.setVoltar("observacoes");
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verObservacoes");
                }else{
                    MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),2,"Nota nao gravada, continuar ?",true);
                    if(dialog.getSimTeste()){
                        Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verObservacoes");
                    }
                }
            }else if(e.getComponent().toString().contains("Voltar")){
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"mainFrame");
            } 
        }   
    }
   
}

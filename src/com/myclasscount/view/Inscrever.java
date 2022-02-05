/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.Aluno_ctrl;
import com.myclasscount.control.CtrlGeral;
import com.myclasscount.model.Aluno;
import com.myclasscount.model.Disciplina;
import com.myclasscount.model.dao.Disciplina_dao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author CUINIC4
 */

public class Inscrever extends JDialog {
    
    private Color backColor=new Color(0,24,242);
    private MyButtonn btns[];
    private Panel title;
    private MyProceduress myProc;
    private Panel back;
    private Aluno aluno;
    private  JComboBox disciplina;
    
    public Inscrever(JFrame frame,Aluno aluno, boolean modal){
        super(frame,modal);
        setSize(400,350);
        setUndecorated(true);
        setOpacity(0.92f);
        setMinimumSize(new Dimension(400,350));
        setLocationRelativeTo(frame);
        this.aluno=aluno;
        back=new Panel(backColor.darker(),true,true);
        back.setLayout(null);
        back.setBorderColor(Color.WHITE);
        title=MyProceduress.barName("Inscricao",back);
        back.add(title);
        title.setVisible(false);
        myProc=new MyProceduress();
        addComponentToMainPane(myProc.mainPane("verAlunos","verAlunos",back));
        setContentPane(back);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new FlowLayout());
        pan1.invisible(true, true);
        
        myProc.getBtns()[1].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[1].addMouseListener(new Clique());
        myProc.getBtns()[0].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[0].addMouseListener(new Clique());
        
        ArrayList<Disciplina> disc=Disciplina_dao.getDisciplinas();
        String  disciplinas[]=new String[disc.size()];
        for(int i=0;i<disc.size();i++){
            disciplinas[i]=disc.get(i).getNome();
        }
        
        
        disciplina=new JComboBox(disciplinas);
        JLabel discText=new JLabel("Disciplina",SwingConstants.CENTER);
        discText.setForeground(Color.white);
        
        JPanel pan=new JPanel(new GridLayout(2,1,30,10));
        pan.setOpaque(false);
        pan.add(discText);
        pan.add(disciplina);
       
        pan1.add(pan);

        
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
                        x=getWidth()/2-title.getWidth()/2;
                        title.setLocation(x,y);
                        pan1.setSize((int)(mainPane.getWidth()*0.60),(int)(mainPane.getHeight()*0.75));
                        x=mainPane.getWidth()/2-pan1.getWidth()/2;
                        y=mainPane.getHeight()/2-pan1.getHeight()/2;
                        pan1.setLocation(x,y);
                        
                        myProc.getBtns()[0].setVisible(true);
                        myProc.getBtns()[1].setVisible(true);
                        mainPane.revalidate();
                    }
                }
            }
        ).start();
    }
    
    private  class Clique extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getComponent().toString().contains("Proximo")){
                Aluno_ctrl.setInscricao(aluno.getId(),String.valueOf(disciplina.getSelectedItem()));
                MyDialogg resp2=new MyDialogg(Myclasscount.getFrame(),1,"Inscrito com sucesso",true);
                disciplina.setSelectedIndex(0);
                
                Ver_Alunos v=(Ver_Alunos)CtrlGeral.getTela("verAlunos");
                v.updateComponents();
                v.updateVer_dialog(Myclasscount.getFrame());
                
                dispose();
            }else if(e.getComponent().toString().contains("Voltar")){
                dispose();
            }
        }
    }
}


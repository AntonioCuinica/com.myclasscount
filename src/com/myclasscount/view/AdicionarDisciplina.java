/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.CtrlGeral;
import com.myclasscount.control.Disciplina_ctrl;
import com.myclasscount.model.Disciplina;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

/**
 *
 * @author CUINIC4
 */
public class AdicionarDisciplina extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private MyButtonn btns[];
    private MyProceduress myProc;
    private JTextField txtF;
    private JComboBox combbx;
    private JSpinner carga;
    Panel title;
    
    public AdicionarDisciplina(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Registar Disciplina",this);
        container.add(title);
        myProc=new MyProceduress();
        this.addComponentToMainPane(myProc.mainPane("mainFrame","verDisciplinas",container));
        this.setVisible(true);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(10,1));
        pan1.invisible(true, true);
        
        myProc.getBtns()[1].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[1].addMouseListener(new Clique());
        myProc.getBtns()[0].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[0].addMouseListener(new Clique());
     
        String seccao[]={"Ciências","Letras","Tecnica","Superior"};
        combbx=new JComboBox(seccao);
        txtF=new JTextField();
        carga=new JSpinner(new SpinnerListModel(new Integer[]{50,55,60,65,70,75,80,85,90,95,100}));
        JLabel labels[]={new JLabel("Nome das disciplina"),new JLabel("Carga Horaria"),new JLabel("Seccao")};
        for(JLabel lb:labels)lb.setForeground(Color.white);
        pan1.add(labels[0]); pan1.add(txtF);
        pan1.add(labels[1]); pan1.add(carga);
        pan1.add(labels[2]); pan1.add(combbx);
        
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
               Disciplina disc=new Disciplina();
               disc.setNome(txtF.getText());
               disc.setCarga_horaria(String.valueOf(carga.getValue()));
               disc.setSeccao(String.valueOf(combbx.getSelectedItem()));
               if(Disciplina_ctrl.setDisciplina(disc)){
                   txtF.setText("");
                   txtF.grabFocus();
                   Ver_Disciplinas v=(Ver_Disciplinas)CtrlGeral.getTela("verDisciplinas");
                   v.updateComponents();
               }
            }else if(e.getComponent().toString().contains("Voltar")){
                
            }
        }
    }
    
}

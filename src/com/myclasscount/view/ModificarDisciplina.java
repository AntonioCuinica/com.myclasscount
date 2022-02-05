/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.CtrlGeral;
import com.myclasscount.control.Disciplina_ctrl;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.myclasscount.model.Disciplina;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
/**
 *
 * @author CUINIC4
 */
public class ModificarDisciplina extends JDialog {
    private Color backColor=new Color(0,24,242);
    private Panel back;
    private MyButtonn btns[];
    private Panel title;
    private MyProceduress myProc;
    private JComboBox combbx;
    private JTextField txtF;
    private JSpinner carga;
    private Disciplina disciplina;
    
    public ModificarDisciplina(JFrame frame,Disciplina disciplina,boolean modal){
        super(frame,modal);
        this.disciplina=disciplina;
        setSize(500,500);
        setUndecorated(true);
        setOpacity(0.92f);
        setMinimumSize(new Dimension(600,500));
        setLocationRelativeTo(frame);
        back=new Panel(backColor.darker(),true,true);
        back.setLayout(null);
        back.setBorderColor(Color.WHITE);
        title=MyProceduress.barName("Modificar Disciplina",back);
        back.add(title);
        title.setVisible(false);
        myProc=new MyProceduress();
        addComponentToMainPane(myProc.mainPane("verDisciplinas","verDisciplinas", back));
        setContentPane(back);
    }
   
    public void addComponentToMainPane(Container mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(10,1));
        pan1.invisible(true, true);
     
        myProc.getBtns()[1].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[1].addMouseListener(new Clique());
        myProc.getBtns()[0].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[0].addMouseListener(new Clique());
        
        String  categoria[]={"Domiciliar","Normal","Online","Mista"};
        String seccao[]={"Ciências","Letras","Tecnica","Superior"};
        combbx=new JComboBox(seccao);
        combbx.setSelectedItem(disciplina.getSeccao());
        txtF=new JTextField();
        txtF.setText(disciplina.getNome());
        Integer list[]=new Integer[]{0,50,55,60,65,70,75,80,85,90,95,100};
        list[0]=Integer.parseInt(disciplina.getCarga_horaria());
        carga = new JSpinner(new SpinnerListModel(list));
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
                        x=(getWidth()-title.getWidth())/2;
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
                com.myclasscount.model.Disciplina disc=new com.myclasscount.model.Disciplina();
                disc.setId(disciplina.getId());
                disc.setNome(txtF.getText());
                disc.setCarga_horaria(String.valueOf(carga.getValue()));
                disc.setSeccao(String.valueOf(combbx.getSelectedItem()));
                if(Disciplina_ctrl.updateDisciplina(disc)){
                   Ver_Disciplinas v=(Ver_Disciplinas)CtrlGeral.getTela("verDisciplinas");
                   v.updateComponents();
                   txtF.setText("");
                   Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verDisciplinas");
                   dispose();
                }
            }else if(e.getComponent().toString().contains("Voltar")){
                dispose();
            }
        }
    }
}

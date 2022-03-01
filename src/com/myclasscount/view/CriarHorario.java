/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.model.Horario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

/**
 *
 * @author CUINIC4
 */
public class CriarHorario extends JDialog {
    private Color backColor=new Color(0,24,242);
    private Panel title;
    private MyProceduress myProc;
    private Panel back;
    private MyButtonn btns[];
    private JComboBox combbx[];
    private JSpinner txtF[];
    private String  turno[];
    private Horario horario;
    private String antes,depois;
    
    public void setHorario(Horario horario){
        this.horario=horario;
    }
    
    public Horario getHorario(){
        return horario;
    }
    
    public CriarHorario(JFrame frame,String antes,String depois, boolean modal){
        super(frame,modal);
        setSize(600,400);
        setUndecorated(true);
        setOpacity(0.92f);
        setMinimumSize(new Dimension(700,500));
        setLocationRelativeTo(frame);
        this.antes=antes;
        this.depois=depois;
        back=new Panel(backColor.darker(),true,true);
        back.setLayout(null);
        back.setBorderColor(Color.WHITE);
        title=MyProceduress.barName("Criar Horario",back);
        back.add(title);
        title.setVisible(false);
        myProc=new MyProceduress();
        addComponentToMainPane(myProc.mainPane(antes,depois, back));
        setContentPane(back);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(10,1));
        pan1.invisible(true, true);
        
        myProc.getBtns()[1].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[1].addMouseListener(new Clique());
        myProc.getBtns()[0].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[0].addMouseListener(new Clique());
        
        turno=new String[]{"Manha","Tarde","Noite"};
        String diaSemana[]={"Domingo","Segunda","Terça","Quarta","Quinta","Sexta","Sabado"};
        combbx=new JComboBox[]{new JComboBox(turno),new JComboBox(diaSemana)};
        Integer hora[]=new Integer[24];
        for(int i=0;i<hora.length;i++){
            hora[i]=i;
        }
        txtF=new JSpinner[]{new JSpinner(new SpinnerListModel(hora)),new JSpinner(new SpinnerListModel(hora))};
        txtF[0].getModel().setValue(8);
        txtF[1].getModel().setValue(12);
        JLabel labels[]={new JLabel("Hora Inicio"),new JLabel("Hora Fim"),new JLabel("Turno"),
                         new JLabel("Dia de Semana")};
        for(JLabel lb:labels)lb.setForeground(Color.white);
        pan1.add(labels[2]); pan1.add(combbx[0]);
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(txtF[1]);
        pan1.add(labels[3]); pan1.add(combbx[1]);
        
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
    private class Clique extends MouseAdapter {
        
        public void mouseClicked(MouseEvent e){
            if(e.getComponent().toString().contains("Proximo")){
                horario=new Horario();
                horario.setTurno(String.valueOf(combbx[0].getSelectedItem()));
                horario.setHora_inicio(String.valueOf(txtF[0].getValue()+":00:00"));
                horario.setHora_fim(String.valueOf(txtF[1].getValue())+":00:00");
                horario.setDia_semana(String.valueOf(combbx[1].getSelectedItem()));
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),depois);
                dispose();
            }else if(e.getComponent().toString().contains("Voltar")){
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),antes);
                dispose();
            } 
        }   

    }
    
}

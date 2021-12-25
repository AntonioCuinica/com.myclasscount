/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author CUINIC4
 */
public  class MyDialogg extends JDialog {
    private Color color=new Color(82,79,250);
    private boolean simTeste=false;
    
    public boolean getSimTeste(){
        return simTeste;
    }
    
    public MyDialogg(JFrame container,boolean modal){
        super(container,modal);
        setSize(450,500);
        setLocationRelativeTo(container);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    public MyDialogg(JFrame container,int type,String text,boolean modal){
        super(container,modal);
        setSize(350,200);
        setLocationRelativeTo(container);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        Panel back=new Panel(color.darker(),true,true);
        back.setBorderColor(Color.BLUE);
        setContentPane(back);
        if(type==1){
            confirmDialog(text);
        }else if(type==2){
            confirmDialog(text,"Sim","Nao");
        }
        setVisible(true);
    }
    
    public void confirmDialog(String text){
        setLayout(new GridLayout(2,1));
        JLabel label=new JLabel(text,SwingConstants.CENTER);
        label.setForeground(Color.white);
        label.setFont(new Font("Arial",Font.BOLD,22));
        add(label);
        JPanel pane=new JPanel(new FlowLayout());
        pane.setBackground(null);
        JButton ok=new JButton("OK");
        ok.setBackground(new Color(82,79,180));
        ok.setForeground(Color.white);
        ok.addActionListener((ActionEvent e) -> {
            dispose();
        });
        pane.add(ok);
        add(pane);
    }
    
    public void confirmDialog(String text,String btn1,String btn2){
        setLayout(new GridLayout(2,1));
        JLabel label=new JLabel(text,SwingConstants.CENTER);
        label.setForeground(Color.white);
        label.setFont(new Font("Arial",Font.BOLD,22));
        add(label);
        JPanel pane=new JPanel(new FlowLayout(1,20,0));
        pane.setBackground(null);
        JButton sim=new JButton(btn1);
        sim.setBackground(new Color(82,79,180));
        sim.setForeground(Color.white);
        sim.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    simTeste=true;
                    dispose();
                }
            }
        );
        JButton nao=new JButton(btn2);
        nao.setBackground(new Color(82,79,180));
        nao.setForeground(Color.white);
        nao.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    simTeste=false;
                    dispose();
                }
            }
        );
        pane.add(sim);
        pane.add(nao);
        add(pane);
    }
   
    
}

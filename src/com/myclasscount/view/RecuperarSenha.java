
/*
 * To change this license header, choose License Headers in Project Propertie
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myclasscount.view;

import com.myclasscount.model.Acesso;
import com.myclasscount.model.dao.Acesso_dao;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author CUINIC4
 */

public class RecuperarSenha extends JPanel {
    
    private Color backColor=new Color(0,24,242);
    private Container container;
    private mybutton btns[];
    private Panel title;
    private myProcedures myProc;
    private JTextField txtF[];
    private JPasswordField pswF[];
    private JLabel labels[];
    private Acesso acesso=null;
    
    public RecuperarSenha(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=myProcedures.barName("Recuperar Senha",this);
        container.add(title);
        myProc=new myProcedures();
        this.addComponentToMainPane(myProc.mainPane("login","login",container));
        this.setVisible(true);
    }
    
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(10,1));
        pan1.invisible(true, true);
       
        //System.out.println("Size: "+myProc.getBtns()[1].getMouseListeners()[1]);
        myProc.getBtns()[1].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[1].addMouseListener(new Clique());
        
        txtF=new JTextField[]{new JTextField(),new JTextField()};
        txtF[0].addKeyListener(new Clique());
        pswF=new JPasswordField[]{new JPasswordField(),new JPasswordField()};
        
        labels=new JLabel[]{new JLabel("Username"),new JLabel("Nova Senha"),new JLabel("Confirmar Senha"),
                         new JLabel("Qual é o seu nome")};
        
        for(JLabel lb:labels)lb.setForeground(Color.white);
        
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(pswF[0]);
        pan1.add(labels[2]); pan1.add(pswF[1]);
        pan1.add(labels[3]); pan1.add(txtF[1]);
        
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
                        mainPane.revalidate();
                    }
                }
            }
        ).start();
    }
    
    private class Clique extends MouseAdapter implements KeyListener{
        public void mouseClicked(MouseEvent e){
            String nPsw=new String(pswF[0].getPassword());
            String cPsw=new String(pswF[1].getPassword());
            if(!(acesso==null)){
                if(nPsw.equals(cPsw)){
                    if(acesso.getResposta().equals(txtF[1].getText())){
                        Acesso_dao.mudarSenha(nPsw,acesso.getId());
                        Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"login");
                    }else{
                        txtF[1].setText("");
                        myDialog dialog=new myDialog(Myclasscount.getFrame(),400,250,true);
                    }
                }else{
                    pswF[1].setText("");
                    myDialog dialog=new myDialog(Myclasscount.getFrame(),400,250,true);
                }
            }
        }

        @Override
        public void keyTyped(KeyEvent e){}

        @Override
        public void keyPressed(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("Username: "+txtF[0].getText());
            acesso=Acesso_dao.getAcesso(txtF[0].getText());
            if(!(acesso==null)){
                labels[3].setText(acesso.getPergunta());
            }
        }
    }
}

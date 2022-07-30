/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.Acesso_ctrl;
import com.myclasscount.control.CtrlGeral;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import com.myclasscount.control.Login_ctrl;
import com.myclasscount.control.Professor_ctrl;
import com.myclasscount.model.Acesso;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author CUINIC4
 */

public class Loginn extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private Panel title;
    private JTextField txtF;
    private JPasswordField pswF;
    private JLabel labels[];
    private int countFalhas=0;
    private JButton esqueceu;
    private JButton sair;
    
    public Loginn(){
        setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Login",this);
        container.add(title);
        addComponentToMainPane(new MyProceduress().mainPane("mainFrame","mainFrame", container));
        setVisible(true);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        //remove os botoes voltar e proximo do mainPane
        container.getComponent(container.getComponentCount()-1).setVisible(false);
        container.getComponent(container.getComponentCount()-2).setVisible(false);
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(8,1));
        pan1.invisible(true, true);
        
        txtF=new JTextField();
        txtF.addKeyListener(new ChangeFocus());
        pswF=new JPasswordField();
        pswF.addKeyListener(new ChangeFocus());
        MyButtonn entrar=new MyButtonn("Entrar",false);
        entrar.addMouseListener(new Clique());
        labels=new JLabel[]{new JLabel("Sistema de Gestão de turmas particulares e mensalidades",SwingConstants.CENTER),new JLabel("Usuario"),
            new JLabel("Senha")};
        for(JLabel lb:labels)lb.setForeground(Color.white);
        labels[0].setFont(new Font("Arial",Font.BOLD,13));
        
        esqueceu=new JButton("Acesso invalido !!");
        esqueceu.setVisible(false);
        esqueceu.setContentAreaFilled(false);
        esqueceu.setBorderPainted(false);
        esqueceu.setForeground(Color.white);
        esqueceu.addMouseListener(new Clique());
        
        sair=new JButton();
        sair.setLayout(new GridLayout());
        JLabel sairN=new JLabel("Sair",SwingConstants.RIGHT);
        sairN.setForeground(Color.white);
        sair.add(sairN);
        sair.setContentAreaFilled(false);
        sair.setBorderPainted(false);
        sair.addMouseListener(new Clique());
        
        pan1.add(labels[0]); 
        pan1.add(labels[1]);
        pan1.add(txtF);
        pan1.add(labels[2]);
        pan1.add(pswF);
        pan1.add(esqueceu);
        pan1.add(entrar);
        pan1.add(sair);
        
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
                        pan1.setSize((int)(mainPane.getWidth()*0.60),(int)(mainPane.getHeight()*0.75));
                        x=mainPane.getWidth()/2-pan1.getWidth()/2;
                        y=mainPane.getHeight()/2-pan1.getHeight()/2;
                        pan1.setLocation(x,y);
                        mainPane.revalidate();
                    }
                }
            }
        ).start();
    }
    
    private class Clique extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            if(e.getComponent().toString().contains("Entrar")){
                entrar(e);
            }else if(e.getComponent().toString().contains("Esqueceu a senha ?")){
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"recuperarSenha");
                countFalhas=0;
                esqueceu.setVisible(false);
            }else if(e.getSource().equals(sair)){
                MyDialogg sair=new MyDialogg(Myclasscount.getFrame(),2,"Deseja sair ?", true);
                if(sair.getSimTeste()){
                    System.exit(0);
                }
            }
        }
    }
    
    public void entrar(InputEvent e){
        String psw=new String(pswF.getPassword());
        if(Login_ctrl.login_ctrl(txtF.getText(),psw)){
            CtrlGeral.carregando();
            countFalhas=0;
            
            Acesso acesso=Acesso_ctrl.getAcesso(txtF.getText());
            CtrlGeral.setProfessor_logado(Professor_ctrl.getProfessor(acesso.getProfessor_id()));
            CtrlGeral.setNivel_acesso(acesso.getNivel_acesso());
           
            CtrlGeral.inicializarTelas();
            
            MyProceduress.updateMainFrame();
            Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"mainFrame");
           
        }else{
            esqueceu.setText("Acesso invalido !!");
            esqueceu.setVisible(true);
            txtF.grabFocus();
        }
        txtF.setText("");
        pswF.setText("");
        if(countFalhas++>=2){
            esqueceu.setText("Esqueceu a senha ?");
            esqueceu.setVisible(true);
        }
    }
    
    private class ChangeFocus extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getSource().equals(txtF)){
                if(e.getKeyText(e.getKeyCode()).equals("Enter") || e.getKeyText(e.getKeyCode()).equals("Down") ){
                    txtF.transferFocus();
                }else{
                    esqueceu.setVisible(false);
                }
            }else if(e.getSource().equals(pswF)){
                if(e.getKeyText(e.getKeyCode()).equals("Backspace") && String.valueOf(pswF.getPassword()).isEmpty()){
                    pswF.transferFocusBackward();
                }else if(e.getKeyText(e.getKeyCode()).equals("Up") ){
                    pswF.transferFocusBackward();
                }else if(e.getKeyText(e.getKeyCode()).equals("Enter") ){
                    entrar(e);
                }
            }
        }
    }
}

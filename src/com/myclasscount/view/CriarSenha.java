/*
 * To change this license header, choose License Headers in Project Propertie
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.Acesso_ctrl;
import com.myclasscount.model.Acesso;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author CUINIC4
 */
public class CriarSenha extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private MyButtonn btns[];
    private Panel title;
    private MyProceduress myProc;
    private JTextField txtF[];
    private JLabel labels[];
    private Acesso acesso=null;
    private String prof_BI="";
    
    public  void setProf_BI(String prof_BI){
        this.prof_BI=prof_BI;
    }
    
    public String getProf_BI(){
        return prof_BI;
    }
    
    public CriarSenha(){
        setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Criar Senha",this);
        container.add(title);
        myProc=new MyProceduress();
        addComponentToMainPane(myProc.mainPane("adicionarProfessor","verProfessores",container));
        setVisible(true);
    }
    
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(10,1));
        pan1.invisible(true, true);
        
        myProc.getBtns()[1].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[1].addMouseListener(new Clique());
        
        txtF=new JTextField[]{new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField()};
        labels=new JLabel[]{new JLabel("Username"),new JLabel("Criar Senha"),new JLabel("Confirmar Senha"),
                         new JLabel("Pergunta de Recuperaçao"),new JLabel("Resposta")};
        for(JLabel lb:labels)lb.setForeground(Color.white);
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(txtF[1]);
        pan1.add(labels[2]); pan1.add(txtF[2]);
        pan1.add(labels[3]); pan1.add(txtF[3]);
        pan1.add(labels[4]); pan1.add(txtF[4]);
        
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
    
    
    private class Clique extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            acesso=new Acesso();
            acesso.setUsername(txtF[0].getText());
            String nPsw=txtF[1].getText();
            String cPsw=txtF[2].getText();
            if(nPsw.equals(cPsw)){
                acesso.setCodigo(cPsw);
            }else{
                MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Senha incorreta !!",true);
                txtF[1].getText();
                txtF[1].grabFocus();
                txtF[2].getText();
                txtF[2].grabFocus();
            }
            acesso.setPergunta(txtF[3].getText());
            acesso.setResposta(txtF[4].getText());
            
            String resultado=Acesso_ctrl.setAcesso(acesso,prof_BI);
            if(resultado.equals("valido")){
                MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Cadastrado com sucesso !!", true);
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verProfessores");
                
                for(JTextField txt:txtF){
                    txt.setText("");
                }
                
            }else {
                if(resultado.contains("1")){
                    txtF[0].setText("");
                    txtF[0].grabFocus();
                }else if(resultado.contains("2")){
                    txtF[1].setText("");txtF[1].grabFocus();
                    txtF[2].setText("");txtF[2].grabFocus();
                }else if(resultado.contains("3")){
                    txtF[3].setText("");
                    txtF[3].grabFocus();
                }else if(resultado.contains("4")){
                    txtF[4].setText("");
                    txtF[4].grabFocus();
                }
            }
        }
    }
}

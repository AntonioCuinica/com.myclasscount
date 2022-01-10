/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.categoria_ctrl;
import com.myclasscount.model.validar.ValidarCategoria;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.InputMismatchException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author CUINIC4
 */
public class CriarCategoria extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private MyButtonn btns[];
    private Panel title;
    private MyProceduress myProc;
    private JComboBox combbx[];
    private JTextField txtF[];
    private JTextArea desc;
    
    
    public CriarCategoria(){
        setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Criar Categoria",this);
        container.add(title);
        myProc=new MyProceduress();
        addComponentToMainPane(myProc.mainPane("categoria","categoria",container));
        setVisible(true);
    }
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(10,1));
        pan1.invisible(true, true);
     
        myProc.getBtns()[1].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[1].addMouseListener(new Clique());
        
        String  tipoEnsino[]={"Primario","Secondario","Tecnico","Universitario"};
        String classe[]={"1a Classe","2a Classe","3a Classe","4a Classe","5a Classe","6a Classe","7a Classe",
                         "8a Classe","9a Classe","10a Classe","11a Classe","12a Classe","Superior"};
        combbx=new JComboBox[]{new JComboBox(tipoEnsino),new JComboBox(classe)};
        txtF=new JTextField[]{new JTextField(),new JTextField()};
        desc=new JTextArea();
        desc.setLineWrap(true);
        
        JLabel labels[]={new JLabel("Nome da Categoria"),new JLabel("Tipo de ensino"),new JLabel("Classe"),
                         new JLabel("Preço"),new JLabel("Descriçao")};
        for(JLabel lb:labels)lb.setForeground(Color.white);
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(combbx[0]);
        pan1.add(labels[2]); pan1.add(combbx[1]);
        pan1.add(labels[3]); pan1.add(txtF[1]);
        pan1.add(labels[4]);pan1.add(new JScrollPane(desc));
        
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

    private  class Clique extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            com.myclasscount.model.Categoria cat=new com.myclasscount.model.Categoria();
            cat.setNome(txtF[0].getText());
            cat.setTipoEnsino((String)combbx[0].getSelectedItem());
            cat.setClasse((String)combbx[1].getSelectedItem());
            try{
                cat.setPreco(Double.parseDouble(txtF[1].getText()));
                System.out.println("Preco: "+cat.getPreco());
            }catch(NumberFormatException g){
                cat.setPreco(-2);
                if(g.getMessage().equals("empty String")){
                    cat.setPreco(-1);
                }
                System.out.println(g.getMessage());
            }
            cat.setDescricao(desc.getText());
            if(categoria_ctrl.inserirCategoria(cat)){
                Ver_Categoria categoria=(Ver_Categoria)Myclasscount.getContainer().getComponent(4);
                categoria.updateComponents();
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"categoria");
                txtF[0].setText(""); combbx[0].setSelectedIndex(0);
                txtF[1].setText(""); combbx[1].setSelectedIndex(0);
                desc.setText("");
            }else if(categoria_ctrl.getErro().contains("preco")){
                txtF[1].setText("");
                txtF[1].grabFocus();
            }else {
                txtF[0].setText("");
                txtF[0].grabFocus();
            }
        }

    }
   
}

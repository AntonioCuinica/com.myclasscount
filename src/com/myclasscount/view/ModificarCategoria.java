/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.CtrlGeral;
import com.myclasscount.control.categoria_ctrl;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.myclasscount.model.Categoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author CUINIC4
 */
public class ModificarCategoria extends JDialog {
    private Color backColor=new Color(0,24,242);
    private Panel back;
    private MyButtonn btns[];
    private Panel title;
    private MyProceduress myProc;
    private JComboBox combbx[];
    private JTextField txtF[];
    private JTextArea desc;
    private Categoria categoria;
    
    public ModificarCategoria(JFrame frame,Categoria categoria,boolean modal){
        super(frame,modal);
        this.categoria=categoria;
        setSize(500,500);
        setUndecorated(true);
        setOpacity(0.92f);
        setMinimumSize(new Dimension(600,500));
        setLocationRelativeTo(frame);
        back=new Panel(backColor.darker(),true,true);
        back.setLayout(null);
        back.setBorderColor(Color.WHITE);
        title=MyProceduress.barName("Modificar Categoria",back);
        back.add(title);
        title.setVisible(false);
        myProc=new MyProceduress();
        addComponentToMainPane(myProc.mainPane("categoria","categoria", back));
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
        
        String  tipoEnsino[]={"Primario","Secundario","Tecnico","Universitario"};
        String classe[];
        if(categoria.getTipoEnsino().equals("Primario")){
            classe=new String[]{"1a Classe","2a Classe","3a Classe","4a Classe","5a Classe","6a Classe","7a Classe"};
        }else if(categoria.getTipoEnsino().equals("Secundario")){
            classe=new String[]{"8a Classe","9a Classe","10a Classe","11a Classe","12a Classe"};
        }else{
            classe=new String[]{"Superior"};
        }
        combbx=new JComboBox[]{new JComboBox(tipoEnsino),new JComboBox(classe)};
        combbx[0].setSelectedItem(categoria.getTipoEnsino());
        combbx[1].setSelectedItem(categoria.getClasse());
        combbx[0].addActionListener(
           new ActionListener(){
               public void actionPerformed(ActionEvent e){
                   String classe[];
                   combbx[1].removeAllItems();
                   if(combbx[0].getSelectedItem().equals("Primario")){
                        classe=new String[]{"1a Classe","2a Classe","3a Classe","4a Classe","5a Classe","6a Classe","7a Classe"};
                   }else if(combbx[0].getSelectedItem().equals("Secundario")){
                        classe=new String[]{"8a Classe","9a Classe","10a Classe","11a Classe","12a Classe"};
                   }else{
                        classe=new String[]{"Superior"};
                   }
                   for(String item:classe){
                       combbx[1].addItem(item);
                   }
               }
           }
        );
        
        txtF=new JTextField[]{new JTextField(),new JTextField()};
        txtF[0].setText(categoria.getNome());
        txtF[1].setText(String.valueOf(categoria.getPreco()));
        desc=new JTextArea();
        desc.setText(categoria.getDescricao());
        desc.setLineWrap(true);
        
        JLabel labels[]={new JLabel("Nome da Categoria"),new JLabel("Tipo de ensino"),new JLabel("Classe"),
                         new JLabel("Preço"),new JLabel("Descrição")};
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
                        x=back.getWidth()/2-title.getWidth()/2;
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
                com.myclasscount.model.Categoria cat=new com.myclasscount.model.Categoria();
                cat.setId(categoria.getId());
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
                if(categoria_ctrl.updateCategoria(cat)){
                    
                    Ver_Categoria categoria=(Ver_Categoria)CtrlGeral.getTela("categoria");
                    categoria.updateComponents();
                    
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"categoria");
                    txtF[0].setText(""); combbx[0].setSelectedIndex(0);
                    txtF[1].setText(""); combbx[1].setSelectedIndex(0);
                    desc.setText("");
                    dispose();
                }else if(categoria_ctrl.getErro().contains("preco")){
                    txtF[1].setText("");
                    txtF[1].grabFocus();
                }else {
                    txtF[0].setText("");
                    txtF[0].grabFocus();
                }
            }else if(e.getComponent().toString().contains("Voltar")){
                dispose();
            }
        }
    }
}

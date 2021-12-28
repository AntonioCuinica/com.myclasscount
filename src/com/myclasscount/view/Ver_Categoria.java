/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.categoria_ctrl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author CUINIC4
 */

public class Ver_Categoria extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private MyButtonn btns[];
    private Panel title;
    private com.myclasscount.model.Categoria catActual=null;
    private MyDialogg ver_dialog;
    
    public Ver_Categoria(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Categorias",this);
        container.add(title);
        addCategoria();
        setVisible(true);
    }
    
    public void addCategoria(){
        MyButtonn bts[]=new MyButtonn[categoria_ctrl.getCategorias().size()];
        Panel pan=new Panel(Color.white,true);
        GridLayout gLyt=new GridLayout(1,3,15,0);
        BoxLayout bLyt=new BoxLayout(pan,BoxLayout.Y_AXIS);
        pan.invisible(true,false);
        pan.setLayout(bLyt);
        pan.setBorder(new EmptyBorder(15,15,0,15));
        
        for(int i=0;i<bts.length;i++){
            com.myclasscount.model.Categoria cat=categoria_ctrl.getCategorias().get(i);
            bts[i]=new MyButtonn(cat.getNome(),false);
            bts[i].addActionListener(new Clique(bts[i]));
            bts[i].setActionCommand(cat.getNome());
            bts[i].addMouseListener(new Clique(bts[i]));
        }
        
        Panel pan1=null;
        int count=0;
        for(int i=0;i<bts.length;i++){
            count++;
            if(i==0){
                pan1=new Panel(backColor.darker(),false);
                pan1.setLayout(gLyt);
                pan1.invisible(true,true);
                pan1.add(bts[i]);
            }else if(i%3==0){
                pan1=new Panel(backColor.darker(),false);
                pan1.setLayout(gLyt);
                pan1.invisible(true,true);
                pan1.add(bts[i]);
            }else{
                pan1.add(bts[i]);
            }
            if(count==3 || i==(bts.length-1)){
                if(count!=3 && i==(bts.length-1)){
                    for(int j=0;j<=count;j++){
                        if(pan1.getComponentCount()<3)pan1.add(new JLabel());
                    }
                }
                pan.add(pan1);
                pan.add(Box.createRigidArea(new Dimension(0,15)));
                count=0;
            }
        }
        
        
        MyButtonn voltar=new MyButtonn("Voltar",false);
        voltar.setSize(85,25);
        voltar.addMouseListener(new Clique(voltar));
        MyButtonn criar=new MyButtonn("Criar",false);
        criar.setSize(85,25);
        criar.addMouseListener(new Clique(criar));
        JScrollPane src=new JScrollPane(pan);
        src.getViewport().setBackground(backColor.darker());
        container.add(src);
        container.add(voltar);
        container.add(criar);
        
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
                        src.setBounds(0,title.getY()+y,container.getWidth(),(int)(container.getHeight()*0.75));
                        y=((container.getHeight()-(src.getHeight()+src.getY())))-(voltar.getHeight()/2);
                        x=title.getWidth()+title.getX()-voltar.getWidth();
                        voltar.setLocation(x-criar.getWidth(),container.getHeight()-y);
                        criar.setLocation(x+10,container.getHeight()-y);
                        container.revalidate();
                    }
                }
            }
        ).start();
    }
    
    public void updateComponents(){
        removeAll();
        title=MyProceduress.barName("Categorias",this);
        container.add(title);
        addCategoria();
    }
    
    public JPanel info(String n,String v){
        JPanel panel=new JPanel(new GridLayout(1,2));
        panel.setOpaque(false);
        JLabel name=new JLabel(n);
        name.setForeground(Color.white);
        name.setFont(new Font("Arial",Font.BOLD,18));
        JLabel value=new JLabel(v);
        value.setForeground(Color.white);
        value.setFont(new Font("Arial",Font.PLAIN,18));
        value.setToolTipText(v);
        panel.add(name);
        panel.add(value);
        return panel;
    }
    
    public void verCategoria(com.myclasscount.model.Categoria cat){
        catActual=cat;
        ver_dialog=new MyDialogg(Myclasscount.getFrame(),true);
        ver_dialog.setLayout(new BorderLayout());
        
        JPanel pan1=new JPanel(new GridLayout(5,1));
        pan1.setOpaque(false);
        pan1.setBorder(new EmptyBorder(15,15,15,15));
        pan1.add(info("Nome",": "+cat.getNome()));
        pan1.add(info("Tipo de Ensino",": "+cat.getTipoEnsino()));
        pan1.add(info("Classe",": "+cat.getClasse()));
        pan1.add(info("Preco",": "+String.valueOf(cat.getPreco())));
        pan1.add(info("Descricao",": "+cat.getDescricao()));
        
        JPanel pan2=new JPanel(new FlowLayout());
        pan2.setOpaque(false);
        MyButtonn modificar=new MyButtonn("Modificar",false);
        modificar.addMouseListener(new Clique(modificar));
        MyButtonn remover=new MyButtonn("Remover",false);
        remover.addMouseListener(new Clique(remover));
        pan2.add(modificar);
        pan2.add(remover);
        
        ver_dialog.add(pan1,BorderLayout.CENTER);
        ver_dialog.add(pan2,BorderLayout.SOUTH);
        ver_dialog.setVisible(true);
    }
    
    private class Clique extends MouseAdapter implements ActionListener {
            MyButtonn btn=null;
            
            public Clique(MyButtonn btn){
                this.btn=btn;
            }
            
            @Override
            public void actionPerformed(ActionEvent e) {
                com.myclasscount.model.Categoria cat=categoria_ctrl.getCategoria(e.getActionCommand());
                verCategoria(cat);
            }
            
            @Override
            public void mouseClicked(MouseEvent e){
                if(btn.getText().equals("Voltar")){
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"mainFrame");
                }else if(btn.getText().equals("Criar")){
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"CriarCat");
                }else if(btn.getText().equals("Remover")){
                    MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),2,"Deseja remover ?",true);
                    if(dialog.getSimTeste()){
                        if(categoria_ctrl.deletarCategoria(catActual)){
                            updateComponents();
                            ver_dialog.dispose();
                        }else {
                            MyDialogg dialog1=new MyDialogg(Myclasscount.getFrame(),1,"Erro, categoria em uso",true);
                        }
                    }
                }else if(btn.getText().equals("Modificar")){
                    ModificarCategoria modificar=new ModificarCategoria(Myclasscount.getFrame(),catActual,true);
                    ver_dialog.dispose();
                    modificar.setVisible(true);
                }
            }
            
    }
    
}

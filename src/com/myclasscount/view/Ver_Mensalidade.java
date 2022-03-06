/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myclasscount.view;

import com.myclasscount.control.Mensalidade_ctrl;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author CUINIC4
 */

public class Ver_Mensalidade extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private Panel title;
    private JTabbedPane mainPane;
    private ArrayList<Object[][]> mensAno;
    
    public Ver_Mensalidade(){
        setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Mensalidade",this);
        container.add(title);
        mensAno=new ArrayList();
        mensalidades();
        setVisible(true);
    }
    
    public void mensalidades(){
        mainPane=new JTabbedPane();
        ArrayList<com.myclasscount.model.Mensalidade> mensas=Mensalidade_ctrl.getMensalidades();
        
        for(com.myclasscount.model.Mensalidade m:mensas){
            adicionarMensalidade(m);
        }
        
        MyButtonn voltar=new MyButtonn("Voltar",false);
        voltar.setSize(85,25);
        voltar.addMouseListener(new Clique());
        voltar.setVisible(false);
        
        container.add(mainPane);
        container.add(voltar);
        
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
                        mainPane.setBounds(0,title.getY()+y,container.getWidth(),(int)(container.getHeight()*0.75));
                        y=((container.getHeight()-(mainPane.getHeight()+mainPane.getY())))-(voltar.getHeight()/2);
                        x=title.getWidth()+title.getX()-voltar.getWidth();
                        voltar.setLocation(x,container.getHeight()-y);
                        voltar.setVisible(true);
                        container.revalidate();
                    }
                }
            }
        ).start();
    }
    
    public void adicionarMensalidade(com.myclasscount.model.Mensalidade mensalidade){
        boolean teste=false;
        String ano=new SimpleDateFormat("yyyy").format(mensalidade.getDataPagamento());
        String mes=new SimpleDateFormat("MMMM",Locale.forLanguageTag("PT-BR")).format(mensalidade.getDataPagamento());
        String esteMes=new SimpleDateFormat("MMMM",Locale.forLanguageTag("PT-BR")).format(Calendar.getInstance().getTime());
        
        for(Object[][] m: mensAno){
            if(m[0][0].equals(ano)){
                JTabbedPane tab=(JTabbedPane)m[0][1];
                for(Component cmp:tab.getComponents()){
                    Mensalidade m1=(Mensalidade)cmp;
                    if(esteMes.equals(m1.getMes())) tab.setSelectedComponent(m1);
                    if(m1.getMes().equals(mes)){
                        teste=true;
                    }
                }
                if(!teste){
                    tab.addTab(mes,new Mensalidade(mes,ano));
                }
                teste=true;
            }
        }
        
        if(!teste){
            JTabbedPane tab=new JTabbedPane();
            Mensalidade mensa=new Mensalidade(mes,ano);
            tab.addTab(mes,mensa);
            if(esteMes.equals(mensa.getMes())) tab.setSelectedComponent(mensa);
            Object m[][]={{ano,tab}};
            mensAno.add(m);
        }
        
        mainPane.removeAll();
        for(Object[][] m:mensAno){
            mainPane.addTab((String)m[0][0],(JTabbedPane)m[0][1]);
            mainPane.setSelectedComponent((JTabbedPane)m[0][1]);
        }
    }
    
    public void updateComponents(){
        removeAll();
        container.add(title);
        String esteMes=new SimpleDateFormat("MMMM",Locale.forLanguageTag("PT-BR")).format(Calendar.getInstance().getTime());
        for(Object m[][]:mensAno){
            JTabbedPane tab=(JTabbedPane)m[0][1];
            tab.addMouseListener(new Clique(true,tab));
            for(Component cmp:tab.getComponents()){
                Mensalidade m1=(Mensalidade)cmp;
                if(m1.getMes().equals(esteMes)){
                    m1.updateComponents();
                    break;
                }
            }
        }
        mensalidades();
    }
    
    private class Clique extends MouseAdapter {
        private  boolean update=false;
        private JTabbedPane tab;
        
        public Clique(){}
        
        public Clique(boolean update,JTabbedPane tab){
            this.update=update;
            this.tab=tab;
        }
        
        @Override
        public void mouseClicked(MouseEvent e){
            if(e.getSource().toString().contains("Voltar")){
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"mainFrame");
            }else if(update){
                Mensalidade m=(Mensalidade)tab.getComponentAt(tab.getSelectedIndex());
                m.updateComponents();
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

/**
 *
 * @author CUINIC4
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.*;

public class mainFrame extends JPanel {
    private Color backColor=new Color(33,80,172);
    private Container container;
    
    public mainFrame(){        
        this.setLayout(null);
        container=this;
        container.setBackground(backColor);
        this.mainBar(); 
        this.mainPane();
        this.setVisible(true);
    }
    
    public void mainBar(){
        Panel pane= new Panel(new Color(0,24,242),false);
        pane.setLayout(null);
        
        mylabel logo=new mylabel("img/logo.png");
        JTextField search=new JTextField("Buscar estudante");
        mybutton btnSearch=new mybutton("Buscar",16,20,false);
        
        /**Adding components */
        pane.add(logo);
        pane.add(search);
        pane.add(btnSearch);
        container.add(pane);
  
        
        new Thread(
            new Runnable(){
                public void run(){
                    while(true){
                        try{
                            Thread.sleep(50);
                        }catch(InterruptedException e){
                            System.out.println("Erro: "+e.getMessage());
                        }
                        int x=0,y=0;
                        
                        /** pane location */
                        int width=getWidth()-60;
                        int height=getHeight()/7;
                        x=20;
                        pane.setBounds(x,20,width,height); 
                        
                        /** logo location*/
                        logo.setSize(new Dimension(300,50));
                        x=20;
                        y=(pane.getHeight()/2)-(logo.getHeight()/2);
                        logo.setLocation(x,y);
                        
                        
                        /** btnSearch location*/
                        btnSearch.setSize(new Dimension(70,30));
                        x=(pane.getWidth()-(btnSearch.getWidth()+25));
                        y=(pane.getHeight()/2)-(btnSearch.getHeight()/2);
                        btnSearch.setLocation(x,y);
                        
                        /** search location*/
                        search.setSize(new Dimension(300,40));
                        x=btnSearch.getX()-(search.getWidth()+30);
                        y=(pane.getHeight()/2)-(search.getHeight()/2);
                        search.setLocation(x,y);   
                    }
                }
            }
        ).start();
    }
    public void mainPane(){
        /**main panel*/
        Panel pane= new Panel(new Color(0,24,242),false);
        pane.setBorderColor(new Color(0,24,242));
        pane.setLayout(null);
        /**out(sair) button*/
        mybutton out=new mybutton("Sair",15,20,false);
        out.addActionListener(new clique(out,(byte)-1));
        out.setActionCommand("out");
        
        /**menu buttons names */
        String btnName[]={"Alunos","Professor","Categoria","Observações","Disciplina","Turma","Mensalidade"};
        JButton btn[]=new JButton[btnName.length];
        Panel pan=new Panel(new Color(104,97,138),true);
        pan.setBorderColor(new Color(0,24,242));
        pan.setLayout(new GridLayout(1,btnName.length,4,1));
        
        for(int i=0;i<btn.length;i++){
            btn[i]=new JButton(btnName[i]);
            btn[i].setBackground(new Color(104,97,138));
            btn[i].setForeground(Color.white);
            btn[i].addMouseListener(new clique(btn[i],(byte)(i+1)));
            pan.add(btn[i]);
        }
        
        /**cards buttons names*/
        String cardName[]={"Cadastrar professor","Cadastrar Alunos","Inserir Disciplina","Criar Turma"};
        mycard cards[]=new mycard[cardName.length];
        Panel pan2=new Panel(Color.lightGray,false);
        pan2.invisible(true,true);
        pan2.setLayout(new GridLayout(1,cards.length-1,18,1));
        for(int i=0;i<cards.length;i++){
            cards[i]=new mycard(cardName[i],"img/card"+(i+1)+".png");
            cards[i].addMouseListener(new clique(cards[i]));
            cards[i].addActionListener(new clique(cards[i]));
            cards[i].setActionCommand("card"+(i+1));
            pan2.add(cards[i]);
        }
                
        pane.add(pan);
        pane.add(pan2);
        pane.add(out);
        container.add(pane);
        
        new Thread(
            new Runnable(){
                public void run(){
                    while(true){
                        try{
                            Thread.sleep(50);
                        }catch(InterruptedException e){
                            System.out.println("Erro: "+e.getMessage());
                        }
                        int x=0,y=0;
                        
                        /** pane location */
                        Component cmp=container.getComponent(0);
                        int width=getWidth()-60;
                        int height=getHeight()-(cmp.getHeight()+100);
                        x=20;
                        y=cmp.getY()+cmp.getHeight()+20;
                        pane.setBounds(x,y,width,height);  
                        
                        /**pan location*/
                        pan.setBounds(0,0,pane.getWidth(),30);
                        pan.repaint();
                        
                        /**pan2 Location*/
                        pan2.setSize(850,280);
                        
                        //pan2.setSize(pane.getWidth()-(2*x),280);
                        x=(pane.getWidth()/2)-(pan2.getWidth()/2);
                        y=(pane.getHeight()/2)-(pan2.getHeight()/2);
                        pan2.setLocation(x,y);
                        for(int i=0;i<cards.length;i++){
                            cards[i].setSize(200,pan2.getHeight());
                        }
                        
                        /**out location*/
                        out.setSize(50,30);
                        x=pane.getWidth()-(out.getWidth()+20);
                        y=pane.getHeight()-(out.getHeight()+20);
                        out.setLocation(x,y);
 
                        revalidate();
                        repaint();
                    }
                }
            }
        ).start();
    }
    
    private class mycard extends JButton{
        String name="New Card";String URL;
        boolean needImage;
        private Color barColor=Color.DARK_GRAY;
        public mycard(String name,String URL){
            this.name=name;
            this.URL=URL;
            this.needImage=true;
            this.setOpaque(false);
        }
        
        public mycard(String name){
            this.name=name;
            this.needImage=false;
            this.setOpaque(false);
        }
        
        public void setBarColor(Color color){
            this.barColor=color;
            this.repaint();
        }
        public Color getBarColor(){
            return this.barColor;
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D gr=(Graphics2D)g;
            int height=this.getHeight()/8;
            if(this.needImage){
                URL url=getClass().getResource(this.URL);
                ImageIcon img=new ImageIcon(url);
                Image img1=img.getImage().getScaledInstance(this.getWidth(),this.getHeight()-height,Image.SCALE_DEFAULT);
                img1=new ImageIcon(img1).getImage();
                gr.drawImage(img1,0,0,this);
            }
            int y=this.getHeight()-height;
            gr.setColor(this.barColor);
            gr.fill(new Rectangle2D.Double(0,y,this.getWidth(),(int)height));
            gr.setColor(Color.white);
            int x=(this.getWidth()/2)-(this.name.length()/2);
            x=x/2;
            gr.drawString(this.name,x,y+20);
        }
        
        
    }
    
    private class clique extends MouseAdapter implements ActionListener {
            private mycard btn=null;
            private JButton btn2=null;
            private Color color;
            private byte type=0;
            public clique(mycard btn){
                this.btn=btn;
                color=btn.getBarColor();
                type=0;
            }
            
            public clique(JButton btn,byte type){
                this.btn2=btn;
                this.type=type;
            }
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("card1")){
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"adicionarProfessor");
                }else if(e.getActionCommand().equals("card2")){
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"addStudent");
                }
                else if(e.getActionCommand().equals("card3")){
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"registSubject");
                }
                else if(e.getActionCommand().equals("card4")){
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"crirTurma");
                }else if(e.getActionCommand().equals("out")){
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"login");
                }
            }
            
            public void mouseEntered(MouseEvent e){
                if(type==0){
                    btn.setBarColor(color.brighter());
                }
            }
            public void mouseExited(MouseEvent e){
                if(type==0){
                    btn.setBarColor(color.darker());
                }
            }
            
            public void mouseClicked(MouseEvent e){
                
                if(type==1){
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verAlunos");
                }
                else if(type==2){
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verProfessores");
                }
                else if(type==3){
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"categoria");
                }
                else if(type==4){
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verObservacoes");
                }
                else if(type==5){
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verDisciplinas");
                }
                else if(type==6){
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verTurmas");
                }
                
            }

            
    }
 
}

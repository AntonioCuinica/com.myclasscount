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
import javax.swing.border.EmptyBorder;

public final class MainFramee extends JPanel {
    private Color backColor=new Color(33,80,172);
    private Container container;
    
    public MainFramee(){        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(backColor);
        setBorder(new EmptyBorder(30,30,30,30));
        container=this;
        mainBar(); 
        add(Box.createRigidArea(new Dimension(0,30)));
        mainPane();
        setVisible(true);
    }
    
    public void mainBar(){
        
        Panel pane= new Panel(new Color(0,24,242),false);
        pane.setLayout(new BoxLayout(pane,BoxLayout.X_AXIS));
        pane.setPreferredSize(new Dimension(getWidth()-60,100));
        pane.setBorder(new EmptyBorder(30,30,30,30));
        
        MyLabell logo=new MyLabell("img/logo.png");
        JPanel lg=new JPanel(new GridLayout());
        lg.setPreferredSize(new Dimension(300,50));
        lg.setOpaque(false);
        lg.add(logo);
        JTextField search=new JTextField();
        search.setPreferredSize(new Dimension(100,50));
        search.add(new JLabel());
        MyButtonn btnSearch=new MyButtonn("Buscar",false);
        btnSearch.setPreferredSize(new Dimension(100,50));
        
        /**Adding components */
        pane.add(lg);
        pane.add(Box.createRigidArea(new Dimension(100,0)));
        pane.add(search);
        pane.add(Box.createRigidArea(new Dimension(30,0)));
        pane.add(btnSearch);
        
        container.add(pane);
  
    }
    
    public void mainPane(){
        /**main panel*/
        Panel pane= new Panel(new Color(0,24,242),false);
        pane.setBorderColor(new Color(0,24,242));
        pane.setLayout(new BorderLayout());
        pane.setPreferredSize(new Dimension(getWidth()-60,600));
      
        
        /**out(sair) button*/
        MyButtonn out=new MyButtonn("Logout",false);
        out.addActionListener(new clique(out,(byte)-1));
        out.setActionCommand("out");
        JPanel pan3=new JPanel(new FlowLayout(2));
        pan3.setBorder(new EmptyBorder(0,20,20,20));
        pan3.setOpaque(false);
        pan3.add(out);
        
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
        pan2.setBorder(new EmptyBorder(20,20,20,20));
        
        for(int i=0;i<cards.length;i++){
            cards[i]=new mycard(cardName[i],"img/card"+(i+1)+".png");
            cards[i].addMouseListener(new clique(cards[i]));
            cards[i].addActionListener(new clique(cards[i]));
            cards[i].setActionCommand("card"+(i+1));
            pan2.add(cards[i]);
        }
                
        pane.add(pan,BorderLayout.NORTH);
        pane.add(pan2,BorderLayout.CENTER);
        pane.add(pan3,BorderLayout.SOUTH);
        container.add(pane);
        
    }
    
    private class mycard extends JButton{
        String name="New Card";String URL;
        boolean needImage;
        private Color barColor=Color.DARK_GRAY;
        
        public mycard(String name,String URL){
            this.name=name;
            this.URL=URL;
            needImage=true;
            setOpaque(false);
        }
        
        public mycard(String name){
            this.name=name;
            needImage=false;
            setOpaque(false);
        }
        
        public void setBarColor(Color color){
            barColor=color;
            repaint();
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
                    MyProceduress.updateVerProfessores();
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"adicionarProfessor");
                }else if(e.getActionCommand().equals("card2")){
                    MyProceduress.updateVerAlunos();
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"addStudent");
                }
                else if(e.getActionCommand().equals("card3")){
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"registSubject");
                }
                else if(e.getActionCommand().equals("card4")){
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"crirTurma");
                }else if(e.getActionCommand().equals("out")){
                    MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),2,"Deseja fazer logout ?",true);
                    if(dialog.getSimTeste())Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"login");
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
                    MyProceduress.updateVerAlunos();
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verAlunos");
                }
                else if(type==2){
                    MyProceduress.updateVerProfessores();
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

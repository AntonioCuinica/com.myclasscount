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

import com.myclasscount.control.CtrlGeral;
import com.myclasscount.model.Professor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public final class MainFramee extends JPanel {
    private Color backColor=new Color(33,80,172);
    
    public MainFramee(){        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(backColor);
        setBorder(new EmptyBorder(30,30,30,30));
        mainBar(); 
        add(Box.createRigidArea(new Dimension(0,30)));
        mainPane();
        setVisible(true);
    }
    
    public void mainBar(){
        Professor professor=CtrlGeral.getProfessor_logado();
        Panel pane=new Panel(new Color(0,24,242),false);
        pane.setLayout(new BoxLayout(pane,BoxLayout.X_AXIS));
        pane.setPreferredSize(new Dimension(getWidth()-60,100));
        pane.setBorder(new EmptyBorder(30,30,30,30));
        
        MyLabell logo=new MyLabell("img/logo.png");
        JPanel lg=new JPanel(new GridLayout());
        lg.setPreferredSize(new Dimension(400,50));
        lg.setMaximumSize(new Dimension(400,50));
        lg.setOpaque(false);
        lg.add(logo);
        
        JLabel btnSearch=new JLabel();
        if(professor!=null){
            btnSearch.setText(professor.getNome()+" "+professor.getApelido());
        }
        btnSearch.setFont(new Font("Arial",Font.BOLD,25));
        btnSearch.setForeground(Color.white);
        
        MyLabell usuario=new MyLabell("img/user.png");
        JPanel user=new JPanel(new GridLayout());
        user.setPreferredSize(new Dimension(40,50));
        user.setMaximumSize(new Dimension(40,50));
        user.setOpaque(false);
        user.add(usuario);
        
        /**Adding components */
        pane.add(lg);
        JPanel separador=new JPanel(new GridLayout());
        separador.setPreferredSize(new Dimension(100,50));
        separador.setOpaque(false);
        pane.add(separador);
        pane.add(btnSearch);
        pane.add(user);
        
        add(pane);
  
    }
    
    public void mainPane(){
        /**main panel*/
        Panel pane=new Panel(new Color(0,24,242),false);
        pane.setBorderColor(new Color(0,24,242));
        pane.setLayout(new BorderLayout());
        pane.setPreferredSize(new Dimension(getWidth()-60,600));
      
        
        /**out(sair) button*/
        MyButtonn sair=new MyButtonn("Logout",false);
        sair.addActionListener(new Clique(sair,(byte)-1));
        sair.setActionCommand("out");
        JPanel pan3=new JPanel(new FlowLayout(2));
        pan3.setBorder(new EmptyBorder(0,20,20,20));
        pan3.setOpaque(false);
        pan3.add(sair);
        
        /**menu buttons names */
        String btnName[]={"Alunos","Professor","Categoria","Observações","Disciplina","Turma","Mensalidade"};
        JButton btn[]=new JButton[btnName.length];
        Panel pan=new Panel(new Color(104,97,138),true);
        pan.setBorderColor(new Color(0,24,242));
        pan.setLayout(new GridLayout(1,btn.length,4,0));
        
        for(int i=0;i<btn.length;i++){
            btn[i]=new JButton(btnName[i]);
            btn[i].setBackground(new Color(104,97,138));
            btn[i].setForeground(Color.white);
            btn[i].addMouseListener(new Clique(btn[i],(byte)(i+1)));
            pan.add(btn[i]);
             if(CtrlGeral.getNivel_acesso().equals("normal")){
                if(btnName[i].equals("Professor") || btnName[i].equals("Mensalidade")){
                    pan.remove(btn[i]);
                }
            }
        }
        
        /**cards buttons names*/
        String cardName[]={"Cadastrar professor","Cadastrar Alunos","Inserir Disciplina","Criar Turma"};
        Mycard cards[]=new Mycard[cardName.length];
        Panel pan2=new Panel(Color.lightGray,false);
        pan2.invisible(true,true);
        pan2.setLayout(new GridLayout(1,cards.length,18,0));
        pan2.setBorder(new EmptyBorder(20,20,20,20));
        
        for(int i=0;i<cards.length;i++){
            cards[i]=new Mycard(cardName[i],"img/card"+(i+1)+".jpg");
            cards[i].addMouseListener(new Clique(cards[i]));
            cards[i].addActionListener(new Clique(cards[i]));
            cards[i].setActionCommand("card"+(i+1));
            pan2.add(cards[i]);
            if(CtrlGeral.getNivel_acesso().equals("normal")){
                if(cardName[i].equals("Cadastrar professor")){
                    pan2.remove(cards[i]);
                }
            }
        }
                
        pane.add(pan,BorderLayout.NORTH);
        pane.add(pan2,BorderLayout.CENTER);
        pane.add(pan3,BorderLayout.SOUTH);
        
        add(pane);
        
    }
    
    private class Mycard extends JButton{
        String name="New Card";
        String URL;
        boolean needImage;
        private Color barColor=Color.DARK_GRAY;
        
        public Mycard(String name,String URL){
            super();
            this.name=name;
            this.URL=URL;
            needImage=true;
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
    
    public void updateComponents(){
        removeAll();
        mainBar(); 
        add(Box.createRigidArea(new Dimension(0,30)));
        mainPane();
    }
    
    private class Clique extends MouseAdapter implements ActionListener {
            private Mycard btn=null;
            private JButton btn2=null;
            private Color color;
            private byte type=0;
            
            public Clique(Mycard btn){
                this.btn=btn;
                color=btn.getBarColor();
                type=0;
            }
            
            public Clique(JButton btn,byte type){
                this.btn2=btn;
                this.type=type;
            }
            
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()) {
                    case "card1":
                        if(CtrlGeral.getNivel_acesso().equals("admin")){
                            MyProceduress.updateAdicionarProfessor();
                            Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"adicionarProfessor");
                        }   
                        break;
                    case "card2":
                        MyProceduress.updateAdicionarAluno();
                        Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"addStudent");
                        break;
                    case "card3":
                        Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"registSubject");
                        break;
                    case "card4":
                        MyProceduress.updateCriarTurma();
                        Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"criarTurma");
                        break;
                    case "out":
                        MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),2,"Deseja fazer logout ?",true);
                        if(dialog.getSimTeste()){
                            CtrlGeral.setProfessor_logado(null);
                            CtrlGeral.setNivel_acesso("normal");
                            Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"login");
                        }   
                        break;
                    default:
                        break;
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
            
            @Override
            public void mouseClicked(MouseEvent e){
                
                switch (type) {
                    case 1 -> {
                        MyProceduress.updateVerAlunos();
                        Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verAlunos");
                    }
                    case 2 -> {
                        if(CtrlGeral.getNivel_acesso().equals("admin")){
                            MyProceduress.updateVerProfessores();
                            Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verProfessores");
                        }
                    }
                    case 3 -> Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"categoria");
                    case 4 -> {
                        Ver_Observacoes v=(Ver_Observacoes)CtrlGeral.getTela("verObservacoes");
                        v.setVoltar("mainFrame");
                        MyProceduress.updateVerObservacoes();
                        Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verObservacoes");
                    }
                    case 5 -> Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verDisciplinas");
                    case 6 -> {
                        MyProceduress.updateVerTurmas();
                        Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verTurmas");
                    }
                    case 7 -> {
                        if(CtrlGeral.getNivel_acesso().equals("admin")){
                            MyProceduress.updateVerMensalidade();
                            Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verMensalidade");
                        }
                    }
                    default -> {
                    }
                }
                
            }

    }
 
}

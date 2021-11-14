/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author CUINIC4
 */
public class myProcedures {
    public static Panel barName(String title,JFrame frame){
        Panel pane=new Panel(Color.black,false);
        pane.setLayout(null);
        pane.invisible(true,true);
        JLabel label=new JLabel(title);
        label.setForeground(Color.white);
        JPanel pan=new JPanel();
        pan.setBackground(new Color(30,80,172));
        pane.add(label);
        pane.add(pan);
        
     
        new Thread(
            new Runnable(){
                public void run(){
                    while(true){
                        try{
                            Thread.sleep(50);
                        }catch(InterruptedException e){
                            System.out.println("Erro: "+e.getMessage());
                        }
                        int x=100,y=50;
                        label.setBounds(0,0,120,20);
                        pan.setBounds(0,20,frame.getWidth()-x,10);
                        pane.setSize(frame.getWidth()-100,35);
                        frame.repaint();
                    }
                }
            }
        ).start();
        
        return pane;
    }
    
    public static Panel barName(String title){
        Panel pane=new Panel(Color.black,false);
        pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
        pane.invisible(true,true);
        JLabel label=new JLabel(title);
        label.setForeground(Color.white);
        JPanel pan=new JPanel();
        pan.setBackground(new Color(205,190,216).brighter());
        pane.add(label);
        pane.add(pan);
        
        return pane;
    }
    
    public static Panel mainPane(JFrame antes,JFrame frame,Container container){
        /**main panel*/
        Panel pane = new Panel(new Color(82,79,250),false);
        pane.setBorderColor(Color.white);
        pane.setLayout(null);
       
        /** btns(voltar e proximo) buttons*/
        mybutton btns[]=new mybutton[]{new mybutton("Voltar",15,20,false),new mybutton("Proximo",7,20,false)};
        btns[0].addActionListener(new clique(antes,frame));
        btns[0].setActionCommand("Voltar");
        container.add(pane);
        container.add(btns[0]);
        container.add(btns[1]);
        
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
                        y=cmp.getY()+cmp.getHeight()+20;
                        pane.setSize((int)(cmp.getWidth()*0.75),(frame.getHeight()-(y+100)));
                        x=(cmp.getWidth()/2+cmp.getX())-(pane.getWidth()/2);
                        pane.setLocation(x,y);  
                        
                        /** btns Location */
                        btns[1].setSize(60,30);
                        x=pane.getWidth()+pane.getX()-btns[1].getWidth();
                        y=pane.getHeight()+pane.getY()+12;
                        btns[1].setLocation(x,y);
                        x-=btns[1].getWidth()+15;
                        btns[0].setBounds(x,y,btns[1].getWidth(),btns[1].getHeight());
                       
                        frame.repaint();
                    }
                }
            }
        ).start();
        return pane;
    }
    
    private static class clique implements ActionListener{
        private JFrame antes;
        private JFrame depois;
        
        public clique(JFrame antes,JFrame depois){
            this.antes=antes;
            this.depois=depois;
        }
        
        
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("Voltar")){
                antes.setVisible(true);
                antes.setBounds(depois.getBounds());
                depois.dispose();
            }
        }
    }
}

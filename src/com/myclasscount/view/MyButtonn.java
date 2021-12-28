/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/** *
 * @author CUINIC4
 */
public class MyButtonn extends JButton{
    private Color color=new Color(82,79,180).darker();
    private Color brColor=Color.white;
    private boolean opaque;
    private String name;
    
    public void setColor(Color color){
        this.color=color;
        repaint();
    }
    public Color getColor(){
        return this.color;
    } 

    public Color getBrColor() {
        return brColor;
    }

    public void setBrColor(Color brColor) {
        this.brColor = brColor;
        repaint();
    }
    
        
    public MyButtonn(String name, boolean opaque){
        super(name);
        setLayout(new GridLayout());
        this.name=name;
        this.opaque=opaque;
        this.setContentAreaFilled(opaque);
        this.setSize(55,22);
        this.addMouseListener(new clique(this));
        JLabel lab=new JLabel(name,SwingConstants.CENTER);
        lab.setForeground(Color.white);
        add(lab);
    }
    
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gr=(Graphics2D)g;
        gr.setColor(color);
        if(getModel().isArmed()){
            gr.setColor(color.darker());
        }else{
            gr.setColor(color);
        }
        if(!this.opaque){
            gr.fill(new RoundRectangle2D.Double(0,0,this.getWidth()-1,this.getHeight()-1,20,20));
        }
    }
    
    public void paintBorder(Graphics g){
        Graphics2D gr=(Graphics2D)g;
        gr.setColor(getBrColor());
        gr.setStroke(new BasicStroke(1.0f));
        gr.draw(new RoundRectangle2D.Double(1,1,this.getWidth()-2,this.getHeight()-2,20,20));
    }
    
    private class clique extends MouseAdapter {
        private MyButtonn btn=null;
        private Color color;
        private Color brColor;
        public clique(MyButtonn btn){
        
            this.btn=btn;
            color=new Color(82,79,180);
            brColor=getBrColor();
        }
        public void mouseEntered(MouseEvent e){
            btn.setColor(color.brighter());
            btn.setBrColor(color.brighter());
        }
        public void mouseExited(MouseEvent e){
            btn.setColor(color.darker());
            btn.setBrColor(brColor);
        }
            
    }
}

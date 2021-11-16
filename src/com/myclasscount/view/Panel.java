/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author CUINIC4
 */
public class Panel extends JPanel {
        private Color color=Color.blue;
        private Color borderColor=Color.white;
        private boolean opaque=true;
        private ImageIcon img;
        private boolean invisible=false;
        private boolean borderInvisible=false;
        
        public void setColor(Color color){
            this.color=color;
            repaint();
        }
        
        public void setBorderColor(Color color){
            this.borderColor=color;
            repaint();
        }
        
        public void invisible(boolean invisible,boolean borderInvisible){
            this.invisible=invisible;
            this.borderInvisible=borderInvisible;
            repaint();
        }
        
        public Panel(Color color,boolean opaque){
            this.setOpaque(opaque);
            if(color!=null)this.color=color;
            this.opaque=opaque;
            this.setBackground(this.color);
        }
        
        public Panel(URL url,boolean opaque){
            this.setOpaque(opaque);
            this.opaque=opaque;
            if(!url.equals(null)){
                this.img=new ImageIcon(url);
            }
            else{
                this.setBackground(this.color);
            }
        }
        
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D gr=(Graphics2D)g;
            gr.setPaint(new GradientPaint(290,160,this.color,40,100,this.color.darker(),true));
            if(!this.borderInvisible){
                if(this.img!=null){
                    Image img=this.img.getImage().getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_DEFAULT);
                    img=new ImageIcon(img).getImage();
                    gr.drawImage(img,0,0,this);
                }
                else{
                    gr.fill(new RoundRectangle2D.Double(0,0,this.getWidth()-1,this.getHeight()-1,50,50));
                }
            }
        }
             
        public void paintBorder(Graphics g){
            super.paintComponent(g);
            Graphics2D gr=(Graphics2D)g;
            if(!this.invisible){
                if(!this.opaque){
                    gr.setColor(this.borderColor);
                    gr.draw(new RoundRectangle2D.Double(0,0,this.getWidth()-1,this.getHeight()-1,50,50));
                }else{
                    gr.setColor(this.borderColor);
                    gr.draw(new Rectangle2D.Double(0,0,this.getWidth()-1,this.getHeight()-1));
                }
            }
        }
}

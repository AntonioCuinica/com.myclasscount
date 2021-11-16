/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author CUINIC4
 */
public class myDialog extends JDialog {
    private Color color=Color.blue;
    public myDialog(JFrame container,boolean modal){
        super(container,modal);
        this.setSize(450,500);
        this.setLocationRelativeTo(container);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(null);
        this.setVisible(true);
    }
   
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D gr=(Graphics2D)g;
        gr.setPaint(new GradientPaint(290,160,this.color,40,100,this.color.darker(),true));
        gr.fill(new Rectangle2D.Double(0,0,this.getWidth(),this.getHeight()));
    }
    
}

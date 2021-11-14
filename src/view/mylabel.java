/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author CUINIC4
 */
public class mylabel extends JLabel {
    private String url;
        
    public mylabel(String url){
        this.url=url;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gr=(Graphics2D)g;
        URL url=getClass().getResource(this.url);
        ImageIcon img=new ImageIcon(url);
        Image img1=img.getImage().getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_DEFAULT);
        img1=new ImageIcon(img1).getImage();
        gr.drawImage(img1,0,0,this);  
    }
}

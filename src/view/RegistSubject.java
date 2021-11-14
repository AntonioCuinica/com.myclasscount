/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author CUINIC4
 */
public class RegistSubject extends JFrame {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private mybutton btns[];
    
    public RegistSubject(JFrame anterior){
        this.setSize(anterior.getSize());
        this.setMinimumSize(new Dimension(860,600));
        this.setLayout(null);
        this.setLocationRelativeTo(anterior);
        container=this.getContentPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setBackground(backColor.darker());
        Panel title=myProcedures.barName("Registar Disciplina",this);
        container.add(title);
        this.addComponentToMainPane(myProcedures.mainPane(anterior,this,container));
        this.setVisible(true);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(10,1));
        pan1.invisible(true, true);
        
     
        String  categoria[]={"Domiciliar","Normal","Online","Mista"};
        String seccao[]={"Ciências","Letras","Tecnica","Superior"};
        JComboBox combbx[]={new JComboBox(categoria),new JComboBox(seccao)};
        JTextField txtF[]={new JTextField()};
        JLabel labels[]={new JLabel("Nome das disciplina"),new JLabel("Carga Horaria"),new JLabel("Seccao")};
        for(JLabel lb:labels)lb.setForeground(Color.white);
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(combbx[0]);
        pan1.add(labels[2]); pan1.add(combbx[1]);
        
        mainPane.add(pan1);
        
        new Thread(
            new Runnable(){
                public void run(){
                    while(true){
                        try{
                            Thread.sleep(50);
                        }catch(InterruptedException e){
                            System.out.println("Erro: "+e.getMessage());
                        }
                        pan1.setSize((int)(mainPane.getWidth()*0.75),mainPane.getHeight()-50);
                        int x=(mainPane.getWidth()/2)-(pan1.getWidth()/2);
                        int y=(mainPane.getHeight()/2)-(pan1.getHeight()/2);
                        pan1.setLocation(x,y);
                        mainPane.revalidate();
                    }
                }
            }
        ).start();
    }
   
    
    
}

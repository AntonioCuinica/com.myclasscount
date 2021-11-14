/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
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
public class CriarHorario extends JFrame {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private mybutton btns[];
    
    public CriarHorario(JFrame anterior){
        this.setSize(anterior.getSize());
        this.setMinimumSize(new Dimension(860,600));
        this.setLayout(null);
        this.setLocationRelativeTo(anterior);
        container=this.getContentPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setBackground(backColor.darker());
        Panel title=myProcedures.barName("Criar Horario",this);
        container.add(title);
        this.addComponentToMainPane(myProcedures.mainPane(anterior,this,container));
        this.setVisible(true);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(10,1));
        pan1.invisible(true, true);
        
        String  turno[]={"Manha","Tarde","Noite"};
        String diaSemana[]={"Domingo","Segunda","Terça","Quarta","Quinta","Sexta","Sabado"};
        String classe[]={"1a Classe","2a Classe","3a Classe","4a Classe","5a Classe","6a Classe","7a Classe",
                         "8a Classe","9a Classe","10a Classe","11a Classe","12a Classe","Superior"};
        JComboBox combbx[]={new JComboBox(turno),new JComboBox(diaSemana),new JComboBox(classe)};
        JTextField txtF[]={new JTextField(),new JTextField()};
        JLabel labels[]={new JLabel("Hora Inicio"),new JLabel("Hora Fim"),new JLabel("Turno"),
                         new JLabel("Dia de Semana"),new JLabel("Classe")};
        for(JLabel lb:labels)lb.setForeground(Color.white);
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(txtF[1]);
        pan1.add(labels[2]); pan1.add(combbx[0]);
        pan1.add(labels[3]); pan1.add(combbx[1]);
        pan1.add(labels[4]); pan1.add(combbx[2]);
        
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

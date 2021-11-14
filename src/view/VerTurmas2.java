/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author CUINIC4
 */
public class VerTurmas2 extends JFrame {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private mybutton btns[];
    private Panel title;
    private Table tabela;
    private JFrame anterior;
    
    public VerTurmas2(JFrame anterior){
        this.anterior=anterior;
        this.setSize(anterior.getSize());
        this.setMinimumSize(new Dimension(860,600));
        this.setLayout(null);
        this.setLocationRelativeTo(anterior);
        container=this.getContentPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setBackground(backColor.darker());
        title=myProcedures.barName("Turma A",this);
        container.add(title);
        this.addTable();
        this.setVisible(true);
    }
    
    public void addTable(){
        Panel mainPane=new Panel(Color.white,false);
        mainPane.setLayout(new BorderLayout());
        
        JPanel pan=new JPanel(new GridLayout());
        mainPane.add(pan,BorderLayout.NORTH);
        
        String turma[][]={{"Classe","10a"},{"Turno","Manha"},{"Professor","Ribeiro"}};
        JPanel pan1=new JPanel(new GridLayout(3,1));
   
        for(int i=0;i<turma.length;i++){
            Panel pan2=new Panel(new Color(19,46,98).darker(),true);
            pan2.invisible(true,true);
            pan2.setLayout(new GridLayout(1,2));
            for(int j=0;j<turma[0].length;j++){
                JLabel lbl=new JLabel(turma[i][j]);
                lbl.setForeground(Color.white);
                pan2.add(lbl);
            }
            pan1.add(pan2);
        }
        pan.add(pan1);
        
        String colunas1[]={"","S","T","Q","Q","S","S","D"};
        tabela=new Table(colunas1);
        String dados1[][]=new String[2][colunas1.length];
        for(int i=0;i<dados1.length;i++){
            for(int j=0;j<dados1[0].length;j++){
                dados1[i][j]=""+i*j;
            }
        }
        dados1[0][0]="Inicio";
        dados1[1][0]="Fim";
        tabela.setTableData(dados1);
        tabela.setBackground(Color.black);
        JScrollPane src1=new JScrollPane(tabela);
        src1.setPreferredSize(new Dimension(200,100));
        src1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        pan.add(src1);
        
        String colunas[]={"Aluno","Apelido","BI","Idade","Classe","Sexo","Nível","Pagamento"};
        mybutton voltar=new mybutton("Voltar",15,20,false);
        voltar.setSize(60,30);
        voltar.addMouseListener(new Clique());
        tabela=new Table(colunas);
        String dados[][]=new String[30][colunas.length];
         for(int i=0;i<dados.length;i++){
            for(int j=0;j<dados[0].length;j++){
                dados[i][j]=""+i*j;
            }
        }
        tabela.setTableData(dados);
        JScrollPane src=new JScrollPane(tabela);
        src.setBackground(Color.blue);
        src.getViewport().setBackground(Color.white);
        
        
        mainPane.add(src,BorderLayout.CENTER);
        container.add(mainPane);
        container.add(voltar);
        verAlunos(this);
        new Thread(
            new Runnable(){
                public void run(){
                    while(true){
                        try{
                            Thread.sleep(50);
                        }catch(InterruptedException e){
                            System.out.println("Erro: "+e.getMessage());
                        }
                        int x=50,y=50;
                        x=container.getWidth()/2-title.getWidth()/2;
                        title.setLocation(x,y);
                        mainPane.setBounds(0,title.getY()+y,container.getWidth(),(int)(container.getHeight()*0.75));
                        y=((container.getHeight()-(mainPane.getHeight()+mainPane.getY())))-(voltar.getHeight()/2);
                        x=title.getWidth()+title.getX()-voltar.getWidth();
                        voltar.setLocation(x,container.getHeight()-y);
                        container.revalidate();
                    }
                }
            }
        ).start();
    }
    public void verAlunos(JFrame frame){
        tabela.addMouseListener(
            new MouseAdapter(){
                public void mouseClicked(MouseEvent evento){
                    if(evento.getClickCount()==2){
                        int linha=tabela.getSelectedRow();
                        String txt=tabela.getValueAt(linha,0).toString()+" | "+tabela.getValueAt(linha,1).toString()+
                             " | "+tabela.getValueAt(linha,2).toString()+" | "+tabela.getValueAt(linha,3).toString();
                        myDialog dialog=new myDialog(frame,true);
                    }
                }
            }
        );
    }
    
    private class Clique extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
           anterior.setVisible(true);
           anterior.setBounds(getBounds());
           dispose();
        }
    }
    
    /*public static void main(String[] args) {
        new VerTurmas2(new JFrame());
    }*/
}

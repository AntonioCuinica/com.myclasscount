/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.Aluno_ctrl;
import com.myclasscount.model.Aluno;
import com.myclasscount.model.Turma;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author CUINIC4
 */
public class Ver_Turmas2 extends JPanel {
    private final Color backColor=new Color(0,24,242);
    private Container container;
    private MyButtonn btns[];
    private Panel title;
    private Table tabela;
    
    public Ver_Turmas2(){
        setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Turma A",this);
        container.add(title);
        setVisible(true);
    }
    
    public void addTable(Turma turma){
        Panel mainPane=new Panel(Color.white,false);
        mainPane.setLayout(new BorderLayout());
        
        JPanel pan=new JPanel(new GridLayout());
        pan.setBackground(backColor.darker());
        pan.setBorder(new EmptyBorder(15,0,15,0));
        mainPane.add(pan,BorderLayout.NORTH);
        
        String turmaTurno[][]={{"Classe",turma.getClasse()},{"Alunos",""+turma.getAlunos().size()},
                               {"Professor",turma.getProfessor().getNome()+" "+turma.getProfessor().getApelido()}};
        JPanel pan1=new JPanel(new GridLayout(3,1));
        pan1.setBorder(new EmptyBorder(0,15,0,15));
        pan1.setBackground(new Color(19,46,98).darker());
        
        for(int i=0;i<turmaTurno.length;i++){
            Panel pan2=new Panel(new Color(19,46,98).darker(),true);
            pan2.setBorder(new EmptyBorder(0,15,0,15));
            pan2.invisible(true,true);
            pan2.setLayout(new GridLayout(1,2));
            for(int j=0;j<turmaTurno[0].length;j++){
                JLabel lbl=new JLabel(turmaTurno[i][j]);
                lbl.setForeground(Color.white);
                pan2.add(lbl);
            }
            pan1.add(pan2);
        }
        pan.add(pan1);
        
        String colunas1[]={"","S","T","Q","Q","S","S","D"};
        tabela=new Table(colunas1);
        String dados1[][]=new String[2][colunas1.length];
        for(int i=0; i<turma.getHorarios().size() && i<dados1.length;i++){
            switch(turma.getHorarios().get(i).getDia_semana()){
                 case "Segunda":
                    dados1[0][1]=turma.getHorarios().get(i).getHora_inicio();
                    dados1[1][1]=turma.getHorarios().get(i).getHora_fim();
                break;
                case "Terça":
                    dados1[0][2]=turma.getHorarios().get(i).getHora_inicio();
                    dados1[1][2]=turma.getHorarios().get(i).getHora_fim();
                break;
                case "Quarta":
                    dados1[0][3]=turma.getHorarios().get(i).getHora_inicio();
                    dados1[1][3]=turma.getHorarios().get(i).getHora_fim();
                break;
                case "Quinta":
                    dados1[0][4]=turma.getHorarios().get(i).getHora_inicio();
                    dados1[1][4]=turma.getHorarios().get(i).getHora_fim();
                break;
                case "Sexta":
                    dados1[0][5]=turma.getHorarios().get(i).getHora_inicio();
                    dados1[1][5]=turma.getHorarios().get(i).getHora_fim();
                break;
                case "Sabado":
                    dados1[0][6]=turma.getHorarios().get(i).getHora_inicio();
                    dados1[1][6]=turma.getHorarios().get(i).getHora_fim();
                break;
                case "Domingo":
                    dados1[0][7]=turma.getHorarios().get(i).getHora_inicio();
                    dados1[1][7]=turma.getHorarios().get(i).getHora_fim();
                break;
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
        MyButtonn voltar=new MyButtonn("Voltar",false);
        voltar.setSize(85,25);
        voltar.addMouseListener(new Clique());
        voltar.setVisible(false);
        MyButtonn modificar=new MyButtonn("Modificar",false);
        modificar.setSize(90,25);
        modificar.addMouseListener(new Clique());
        modificar.setVisible(false);
        
        tabela=new Table(colunas);
        ArrayList<Aluno> alunos=turma.getAlunos();
        String dados[][]=new String[alunos.size()][colunas.length];
        for(int i=0;i<dados.length;i++){
            dados[i][0]=alunos.get(i).getNome();
            dados[i][1]=alunos.get(i).getApelido();
            dados[i][2]=alunos.get(i).getBI();
            dados[i][3]=String.valueOf(MyProceduress.idade(alunos.get(i).getNascimento().split("-")[0]));
            dados[i][4]=alunos.get(i).getNivel();
            dados[i][5]=alunos.get(i).getSexo();
            dados[i][6]=alunos.get(i).getNivel();
            dados[i][7]=Aluno_ctrl.pagamento(alunos.get(i).getId())[2];
            
        }
        tabela.setTableData(dados);
        JScrollPane src=new JScrollPane(tabela);
        src.setBackground(Color.blue);
        src.getViewport().setBackground(Color.white);
        
        
        mainPane.add(src,BorderLayout.CENTER);
        container.add(mainPane);
        container.add(modificar);
        container.add(voltar);
        verAlunos(Myclasscount.getFrame());
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
                        y=((container.getHeight()-(mainPane.getHeight()+mainPane.getY())))-(modificar.getHeight()/2);
                        x=title.getWidth()+title.getX()-modificar.getWidth();
                        modificar.setLocation(x,container.getHeight()-y);
                        x=modificar.getX()-modificar.getWidth();
                        voltar.setLocation(x-10,modificar.getY());
                        voltar.setVisible(true);
                        modificar.setVisible(true);
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
                        MyDialogg dialog=new MyDialogg(frame,true);
                    }
                }
            }
        );
    }

    void updateComponents(Turma turma) {
        removeAll();
        container.add(title);
        addTable(turma);
    }
    
    private class Clique extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verTurmas");
        }
    }
    
}

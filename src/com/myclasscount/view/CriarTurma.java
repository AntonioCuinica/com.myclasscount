/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.Aluno_ctrl;
import com.myclasscount.control.Professor_ctrl;
import com.myclasscount.control.categoria_ctrl;
import com.myclasscount.model.Aluno;
import com.myclasscount.model.Categoria;
import com.myclasscount.model.Professor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author CUINIC4
 */

public class CriarTurma extends JPanel {
    private Color backColor=new Color(0,24,242);
    private Container container;
    private MyProceduress myProc;
    private Panel title;
    private ArrayList<Aluno> alunos;
    private ArrayList<Professor> professores;
    Panel pan1;
    Panel pan2;
    MyButtonn student[];
    JCheckBox checks[];
    JComboBox combbx[];
    JTextField txtF;
    MyButtonn defHorario;
    
    
    public CriarTurma(){
        this.setLayout(null);
        container=this;
        container.setBackground(backColor.darker());
        title=MyProceduress.barName("Criar Turma",this);
        container.add(title);
        myProc=new MyProceduress();
        addComponentToMainPane(myProc.mainPane("mainFrame","verTurmas", container));
        this.setVisible(true);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(11,1));
        pan1.invisible(true, true);
        pan2=new Panel(Color.black,false);
        pan2.setLayout(null);
        pan2.invisible(true, true);
        
        myProc.getBtns()[1].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[1].addMouseListener(new Clique());
        myProc.getBtns()[0].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[0].addMouseListener(new Clique());
     
        
        String  turno[]={"Manha","Tarde","Noite"};
        
        professores=Professor_ctrl.getProfessores();
        String professor[]=new String[professores.size()];
        for(int i=0;i<professor.length;i++){
            Professor prof=professores.get(i);
            professor[i]=prof.getNome()+" "+prof.getApelido();
        }
        String classe[]={"1a Classe","2a Classe","3a Classe","4a Classe","5a Classe","6a Classe","7a Classe",
                         "8a Classe","9a Classe","10a Classe","11a Classe","12a Classe","Superior"};
        combbx=new JComboBox[]{new JComboBox(turno),new JComboBox(professor),new JComboBox(classe)};
        combbx[2].addActionListener(new Clique());
        txtF=new JTextField();
        defHorario=new MyButtonn("Definir Horario",false);
        defHorario.addActionListener(new Clique());
        JLabel labels[]={new JLabel("Nome da Turma"),new JLabel("Turno"),new JLabel("Nome do Professor"),
                         new JLabel("Classe")};
        for(JLabel lb:labels)lb.setForeground(Color.white);
        
        
        pan1.add(labels[0]); pan1.add(txtF);
        pan1.add(labels[1]); pan1.add(combbx[0]);
        pan1.add(labels[2]); pan1.add(combbx[1]);
        pan1.add(labels[3]); pan1.add(combbx[2]);
        pan1.add(new JLabel());pan1.add(defHorario);
        
        pan2.add(MyProceduress.barName("Adicionar Alunos"));
        pan2.add(new JScrollPane(listaEstudante(classe[0])));
        
        mainPane.add(pan1);
        mainPane.add(pan2);
        
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
                        pan1.setBounds(50,25,mainPane.getWidth()/2-75,mainPane.getHeight()-50);
                        pan2.setBounds(mainPane.getWidth()/2+25,25,mainPane.getWidth()/2-75,mainPane.getHeight()-50);
                        try{
                            pan2.getComponent(0).setBounds(0,0,pan2.getWidth(),25);
                            pan2.getComponent(1).setBounds(0,30,pan2.getWidth(),pan2.getHeight()-30);
                        }catch(IndexOutOfBoundsException e){
                            System.out.println("Erro, indice invalido: "+e.getMessage());
                        }
                        pan2.revalidate();
                        
                        myProc.getBtns()[0].setVisible(true);
                        myProc.getBtns()[1].setVisible(true);
                        
                        mainPane.revalidate();
                    }
                }
            }
        ).start();
    }
    
    public Panel listaEstudante(String classe){
        alunos=Aluno_ctrl.getAlunos();
        student=new MyButtonn[alunos.size()];
        checks=new JCheckBox[student.length];
        Panel stud=new Panel(new Color(209,178,255),true);
        stud.setLayout(new BorderLayout());
        for(int i=0;i<student.length;i++){
            Aluno aluno=alunos.get(i);
            Categoria cat=categoria_ctrl.getCategoria(aluno.getCategoria_id());
            if(cat.getClasse().equals(classe)){
                student[i]=new MyButtonn(aluno.getNome()+" "+aluno.getApelido(),false);
                student[i].setLayout(new BorderLayout());
                student[i].setText("Estudante");
                student[i].addMouseListener(new Clique());
                checks[i]=new JCheckBox(aluno.getNome()+" "+aluno.getApelido());
                checks[i].setForeground(Color.white);
                checks[i].getUI().installUI(student[i]);
                checks[i].setFocusable(false);
                checks[i].setOpaque(false);
                student[i].add(checks[i],BorderLayout.WEST);
                stud.add(student[i],BorderLayout.CENTER);
            }
        }
        stud.setLayout(new GridLayout(student.length,1,10,5));
        return stud;
    }
    
    private class Clique extends MouseAdapter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
           if(e.getSource().equals(defHorario)){
                CriarHorario cTur=new CriarHorario(Myclasscount.getFrame(),true);
                cTur.setVisible(true);
           }
           else if(e.getSource().equals(combbx[2])){
               pan2.remove(1);
               pan2.add(listaEstudante(String.valueOf(combbx[2].getSelectedItem())));
           }
        }
        
        public void mouseClicked(MouseEvent e){
            if(e.getComponent().toString().contains("Proximo")){
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verTurmas");
            }else if(e.getComponent().toString().contains("Voltar")){
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"mainFrame");
            }else if(e.getComponent().toString().contains("Estudante")){
                MyButtonn btn=(MyButtonn)e.getComponent();
                JCheckBox ch=(JCheckBox)btn.getComponent(1);
                ch.setSelected(ch.isSelected()?false:true);
            }    
        }   
    }
    
   
    
}

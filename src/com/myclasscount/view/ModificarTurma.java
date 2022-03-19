/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.Aluno_ctrl;
import com.myclasscount.control.CtrlGeral;
import com.myclasscount.control.Professor_ctrl;
import com.myclasscount.control.Turma_ctrl;
import com.myclasscount.control.categoria_ctrl;
import com.myclasscount.model.Aluno;
import com.myclasscount.model.Categoria;
import com.myclasscount.model.Horario;
import com.myclasscount.model.Professor;
import com.myclasscount.model.Turma;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author CUINIC4
 */

public class ModificarTurma extends JDialog {
    private Color backColor=new Color(0,24,242);
    private MyProceduress myProc;
    private Panel title;
    private ArrayList<Aluno> alunos;
    private ArrayList<Professor> professores;
    private Turma turma;
    private Panel pan1;
    private Panel pan2;
    private MyButtonn student[];
    private JCheckBox checks[];
    private JComboBox combbx[];
    private JTextField txtF;
    private MyButtonn defHorario;
    private CriarHorario cTur;
    private Panel back;
    
    public ModificarTurma(JFrame frame,Turma turma ,boolean modal){
        super(frame,modal);
        setSize(700,550);
        setUndecorated(true);
        setOpacity(0.92f);
        setMinimumSize(new Dimension(700,500));
        setLocationRelativeTo(frame);
        this.turma=turma;
        back=new Panel(backColor.darker(),true,true);
        back.setLayout(null);
        back.setBorderColor(Color.WHITE);
        title=MyProceduress.barName("Modificar Turma",back);
        back.add(title);
        title.setVisible(false);
        myProc=new MyProceduress();
        addComponentToMainPane(myProc.mainPane("verTurma2","verTurmas2", back));
        setContentPane(back);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(9,1));
        pan1.invisible(true, true);
        
        pan2=new Panel(Color.black,false);
        pan2.setLayout(new BoxLayout(pan2,BoxLayout.Y_AXIS));
        pan2.invisible(true, true);
        
        myProc.getBtns()[1].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[1].addMouseListener(new Clique());
        myProc.getBtns()[0].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[0].addMouseListener(new Clique());
     
        professores=Professor_ctrl.getProfessores();
        String professor[]=new String[professores.size()];
        for(int i=0;i<professor.length;i++){
            Professor prof=professores.get(i);
            professor[i]=prof.getNome()+" "+prof.getApelido();
        }
        String classe[]={"1a Classe","2a Classe","3a Classe","4a Classe","5a Classe","6a Classe","7a Classe",
                         "8a Classe","9a Classe","10a Classe","11a Classe","12a Classe","Superior"};
        combbx=new JComboBox[]{new JComboBox(professor),new JComboBox(classe)};
        combbx[0].setSelectedItem(turma.getProfessor().getNome()+" "+turma.getProfessor().getApelido());
        combbx[1].setEnabled(false);
        combbx[1].setSelectedItem(turma.getClasse());
        
        combbx[1].addActionListener(new Clique());
        txtF=new JTextField();
        txtF.setText(turma.getNome());
        defHorario=new MyButtonn("Definir Horario",false);
        defHorario.addActionListener(new Clique());
        JLabel labels[]={new JLabel("Nome da Turma"),new JLabel("Nome do Professor"),
                         new JLabel("Classe")};
        for(JLabel lb:labels)lb.setForeground(Color.white);
        
        
        pan1.add(labels[0]); pan1.add(txtF);
        pan1.add(labels[1]); pan1.add(combbx[0]);
        pan1.add(labels[2]); pan1.add(combbx[1]);
        pan1.add(new JLabel());pan1.add(defHorario);
        
        pan2.add(MyProceduress.barName("Adicionar Alunos"));
        pan2.add(Box.createRigidArea(new Dimension(0,15)));
        pan2.add(listaEstudante(String.valueOf(combbx[1].getSelectedItem())));
        
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
                        x=getWidth()/2-title.getWidth()/2;
                        title.setLocation(x,y);
                        pan1.setBounds(50,25,mainPane.getWidth()/2-75,mainPane.getHeight()-50);
                        pan2.setBounds(mainPane.getWidth()/2+25,25,mainPane.getWidth()/2-75,mainPane.getHeight()-50);
                        
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
        Clique clique=new Clique();
        Panel stud=new Panel(new Color(209,178,255),true);
        stud.setLayout(new GridLayout(student.length,1));
        for(int i=0;i<student.length;i++){
            Aluno aluno=alunos.get(i);
            Categoria cat=categoria_ctrl.getCategoria(aluno.getCategoria_id());
            if(cat.getClasse().equals(classe)){
                student[i]=new MyButtonn(aluno.getNome()+" "+aluno.getApelido(),false);
                student[i].setLayout(new BorderLayout());
                student[i].setText("Estudante");
                student[i].addMouseListener(clique);
                checks[i]=new JCheckBox(aluno.getNome()+" "+aluno.getApelido());
                checks[i].setForeground(Color.white);
                checks[i].getUI().installUI(student[i]);
                checks[i].setFocusable(false);
                checks[i].setOpaque(false);
                for(Aluno a:turma.getAlunos()){
                    if(a.getId()==aluno.getId()){
                        checks[i].setSelected(true);
                        checks[i].setEnabled(false);
                        student[i].removeMouseListener(clique);
                    }
                }
                student[i].add(checks[i],BorderLayout.WEST);
                stud.add(student[i]);
            }
        }
        return stud;
    }
    
    private class Clique extends MouseAdapter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
           if(e.getSource().equals(defHorario)){
                cTur=new CriarHorario(Myclasscount.getFrame(),"verTurmas2","verTurmas2",true);
                cTur.setVisible(true);
           }
           else if(e.getSource().equals(combbx[1])){
               pan2.remove(2);
               pan2.add(listaEstudante(String.valueOf(combbx[1].getSelectedItem())));
           }
        }
        
        public void mouseClicked(MouseEvent e){
            if(e.getComponent().toString().contains("Proximo")){
                if(txtF.getText().equals("")){
                    MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Erro, nome vazio",true);
                    txtF.grabFocus();
                }
                else if(Turma_ctrl.getTurma(txtF.getText())!=null && !txtF.getText().equals(turma.getNome())){
                    MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Erro, nome existente",true);
                    txtF.setText("");
                    txtF.grabFocus();
                }
                else{
                    turma.setNome(txtF.getText());
                    turma.setClasse(String.valueOf(combbx[1].getSelectedItem()));
                    for(Horario h:turma.getHorarios()){
                        if(cTur!=null && cTur.getHorario()!=null){
                            if(cTur.getHorario().getDia_semana().equals(h.getDia_semana())){
                                cTur.getHorario().setId(h.getId());
                            }
                        }
                    }
                    
                    if(cTur!=null){
                        turma.setHorario(cTur.getHorario());
                    }
                    
                    professores.forEach(
                        prof -> {
                            String nome=String.valueOf(combbx[0].getSelectedItem());
                            if (nome.equals(prof.getNome()+" "+prof.getApelido())) {
                                turma.setProfessor(prof);
                            }
                        }
                    );
                    
                    ArrayList<Aluno> alns=new ArrayList();
                    for(JCheckBox ch:checks){
                        if(ch!=null){
                            if(ch.isSelected() && ch.isEnabled()){
                                String nome=String.valueOf(ch.getText());
                                for(Aluno aluno:alunos){
                                    if(nome.equals(aluno.getNome()+" "+aluno.getApelido())){
                                        alns.add(aluno);
                                    }
                                }
                                turma.setAlunos(alns);
                            }
                        }
                    }
                    
                    Turma_ctrl.updateTurma(turma);
                    
                    MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Turma Modificada com sucesso",true);
                    Ver_Turmas v=(Ver_Turmas)CtrlGeral.getTela("verTurmas");
                    v.updateComponents();
                    Ver_Turmas2 v2=(Ver_Turmas2)CtrlGeral.getTela("verTurmas2");
                    v2.updateComponents(Turma_ctrl.getTurma(turma.getId()));
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verTurmas2");
                    dispose();
                }
            }else if(e.getComponent().toString().contains("Voltar")){
                Ver_Turmas2 v=(Ver_Turmas2)CtrlGeral.getTela("verTurmas2");
                v.updateComponents(turma);
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verTurmas2");
                dispose();
            }else if(e.getComponent().toString().contains("Estudante")){
                MyButtonn btn=(MyButtonn)e.getComponent();
                JCheckBox ch=(JCheckBox)btn.getComponent(1);
                ch.setSelected(!ch.isSelected());
            }    
        }   
    }
    
}

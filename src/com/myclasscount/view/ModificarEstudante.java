/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.Aluno_ctrl;
import com.myclasscount.control.categoria_ctrl;
import com.myclasscount.model.Aluno;
import com.myclasscount.model.Categoria;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author CUINIC4
 */
public class ModificarEstudante extends JDialog {
    private Color backColor=new Color(0,24,242);
    private MyButtonn btns[];
    private Panel title;
    private MyProceduress myProc;
    private JTextField txtF[];
    private JTextArea andress;
    private JComboBox combbx[];
    private JRadioButton male,female;
    private JComboBox data[];
    private Panel back;
    private Aluno aluno;
    
    public ModificarEstudante(JFrame frame,Aluno aluno,boolean modal){
        super(frame,modal);
        this.aluno=aluno;
        setSize(700,500);
        setUndecorated(true);
        setOpacity(0.92f);
        setMinimumSize(new Dimension(700,500));
        setLocationRelativeTo(frame);
        back=new Panel(backColor.darker(),true,true);
        back.setLayout(null);
        back.setBorderColor(Color.WHITE);
        title=MyProceduress.barName("Modificar Estudante",back);
        back.add(title);
        title.setVisible(false);
        myProc=new MyProceduress();
        addComponentToMainPane(myProc.mainPane("verAlunos","verAlunos", back));
        setContentPane(back);
    }
    
   
    public void addComponentToMainPane(Panel mainPane){
        Panel pan1=new Panel(Color.black,false);
        pan1.setLayout(new GridLayout(13,1));
        pan1.invisible(true, true);
        Panel pan2=new Panel(Color.black,false);
        pan2.setLayout(null);
        pan2.invisible(true, true);
        
         
        myProc.getBtns()[1].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[1].addMouseListener(new Clique());
        myProc.getBtns()[0].removeMouseListener(myProc.getBtns()[1].getMouseListeners()[0]);
        myProc.getBtns()[0].addMouseListener(new Clique());
     
        txtF=new JTextField[]{new JTextField(aluno.getNome()),new JTextField(aluno.getApelido()),new JTextField(aluno.getBI()),
                           new JTextField(aluno.getTelefone()),new JTextField(aluno.getEmail()),new JTextField()};
        
        andress=new JTextArea(aluno.getMorada());
        andress.setLineWrap(true);
        JScrollPane morada=new JScrollPane(andress);
        
        String nivel[]={"Primario","Secondario","Técnico","Universitario"};
        ArrayList<Categoria> categorias=categoria_ctrl.getCategorias();
        String  categoria[]=new String[categorias.size()];
        
        for(int i=0;i<categoria.length;i++){
            categoria[i]=categorias.get(i).getNome();
        }
        
        combbx=new JComboBox[]{new JComboBox(nivel),new JComboBox(categoria)};
        combbx[0].setSelectedItem(aluno.getNivel());
        combbx[1].setSelectedItem(categoria_ctrl.getCategoria(aluno.getCategoria_id()).getNome());
        male = new JRadioButton("Masculino");
        male.setOpaque(false);
        male.setForeground(Color.white);
        female=new JRadioButton("Feminino");
        female.setOpaque(false);
        female.setForeground(Color.white);
        if(aluno.getSexo().contains("F")){
            female.setSelected(true);
        }else{
            male.setSelected(true);
        }
        ButtonGroup btnG=new ButtonGroup();
        btnG.add(male);btnG.add(female);
        Panel sexo=new Panel(Color.black,false);
        sexo.setLayout(new GridLayout(1,2));
        sexo.invisible(true,true);
        sexo.add(male);sexo.add(female);
        
        JLabel labels[]={new JLabel("Nome"),new JLabel("Apelido"),new JLabel("Nr BI"),new JLabel("Nascimento"),
                  new JLabel("Sexo"),new JLabel("Nivel Academico"),new JLabel("Morada"),new JLabel("Telefone"),
                  new JLabel("Email"),new JLabel("Categoria"),new JLabel("Mês"),new JLabel("Dia"),new JLabel("Ano")};
        for(int i=0;i<labels.length;i++){labels[i].setForeground(Color.white);}
        
        String mes[]={"Janeiro", "Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
        
        String dia[]=new String[31];
        for(int i=0;i<dia.length;i++){
            dia[i]=""+(i+1);
        }
        
        String ano[]=new String[30];
        for(int i=0;i<ano.length;i++){
            ano[i]=""+(i+1990);
        }
        
        data=new JComboBox[]{new JComboBox(mes),new JComboBox(dia),new JComboBox(ano)};
        String dt[]=aluno.getNascimento().split("-");
        data[0].setSelectedItem(dt[1]);
        data[1].setSelectedItem(dt[2]);
        data[2].setSelectedItem(dt[0]);
        
        JPanel dataN=new JPanel(new GridLayout(1,3,5,1));
        dataN.setOpaque(false);
        dataN.add(labels[10]);
        dataN.add(labels[11]);
        dataN.add(labels[12]);
        JPanel dataN1=new JPanel(new GridLayout(1,3,5,1));
        dataN1.setOpaque(false);
        dataN1.add(data[0]);
        dataN1.add(data[1]);
        dataN1.add(data[2]);
        
        pan1.add(labels[0]); pan1.add(txtF[0]);
        pan1.add(labels[1]); pan1.add(txtF[1]);
        pan1.add(labels[2]); pan1.add(txtF[2]);
        pan1.add(labels[3]); pan1.add(dataN);pan1.add(dataN1);
        pan1.add(labels[4]); pan1.add(sexo);
        pan1.add(labels[5]); pan1.add(combbx[0]);
        
        pan2.add(labels[6]); pan2.add(morada);
        pan2.add(labels[7]); pan2.add(txtF[3]);
        pan2.add(labels[8]); pan2.add(txtF[4]);
        pan2.add(labels[9]); pan2.add(combbx[1]);
        
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

                        labels[9].setBounds(labels[5].getBounds());
                        combbx[1].setBounds(combbx[0].getBounds());
                        
                        labels[8].setBounds(labels[4].getBounds());
                        labels[8].setLocation(labels[8].getX(),(labels[8].getY()-(int)(labels[8].getY()*0.05)));
                        txtF[4].setBounds(sexo.getBounds());
                        txtF[4].setLocation(txtF[4].getX(),(txtF[4].getY()-(int)(txtF[4].getY()*0.05)));
                        
                        labels[7].setBounds(labels[3].getBounds());
                        txtF[3].setBounds(pan1.getComponent(7).getBounds());
                        
                        
                        labels[6].setBounds(labels[0].getBounds());
                        morada.setBounds(0,txtF[0].getY(),txtF[0].getWidth(),5*txtF[0].getHeight());
                      
                        mainPane.revalidate();
                    }
                }
            }
        ).start();
    }
    
    private class Clique extends MouseAdapter{
        
        public void mouseClicked(MouseEvent e){
            if(e.getComponent().toString().contains("Proximo")){
                Aluno aluno=new Aluno();
                aluno.setNome(txtF[0].getText());
                aluno.setId(ModificarEstudante.this.aluno.getId());
                aluno.setApelido(txtF[1].getText());
                aluno.setBI(txtF[2].getText());
                aluno.setNascimento((data[2].getSelectedItem()+"/"+(data[0].getSelectedIndex()+1)+"/"+data[1].getSelectedItem()));
                if(male.isSelected()){
                    aluno.setSexo("M");
                }else{
                    aluno.setSexo("F");
                }
                
                aluno.setNivel((String) combbx[0].getSelectedItem());
                aluno.setMorada(andress.getText());
                aluno.setTelefone(txtF[3].getText());
                aluno.setEmail(txtF[4].getText());
                aluno.setCategoria_id(categoria_ctrl.getCategoria(""+combbx[1].getSelectedItem()).getId());
                
                if(!Aluno_ctrl.updateAluno(aluno)){
                    if(Aluno_ctrl.getErro().contains("nome")){
                        txtF[0].setText("");
                        txtF[0].grabFocus();
                    }else if(Aluno_ctrl.getErro().contains("apelido")){
                        txtF[1].setText("");
                        txtF[1].grabFocus();
                    }else if(Aluno_ctrl.getErro().contains("BI")){
                        txtF[2].setText("");
                        txtF[2].grabFocus();
                    }else if(Aluno_ctrl.getErro().contains("telefone")){
                        txtF[3].setText("");
                        txtF[3].grabFocus();
                    }
                }else{
                    
                    MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Modificado com sucesso !!", true);
                    
                    for(JTextField txtF:txtF){  txtF.setText(""); }
                    
                    for(JComboBox combbx:combbx){ combbx.setSelectedIndex(0); }
                    
                    andress.setText("");
                    
                    Ver_Alunos v=(Ver_Alunos)Myclasscount.getContainer().getComponent(2);
                    v.updateComponents();
                    v.updateVer_dialog(Myclasscount.getFrame());
                    
                    dispose();
                }
            
            }else if(e.getComponent().toString().contains("Voltar")){
                dispose();
            }
        }
        
    }  
}

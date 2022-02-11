/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.CtrlGeral;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author CUINIC4
 */
public class Myclasscount extends JFrame {
    private static CardLayout layout;
    private static Container container;
    private static JFrame frame;
    
    public static CardLayout getCardLayout(){
        return layout;
    }
    
    public static Container getContainer(){
        return container;
    }
    
    public static JFrame getFrame(){
        return frame;
    }
    
    public Myclasscount(){
        setSize(940,650);
        setMinimumSize(new Dimension(940,650));
        layout=new CardLayout();
        setLayout(layout);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addViews();
        container=getContentPane();
        frame=this;
        setVisible(true);
    }
    
    public void addViews(){
        Loginn login=new Loginn();
        add(login,"login");
        CtrlGeral.setTela("login",login);
        
        MainFramee mainF=new MainFramee();
        add(mainF,"mainFrame");
        CtrlGeral.setTela("mainFrame",mainF);
        
        Ver_Alunos verA=new Ver_Alunos();
        add(verA,"verAlunos");
        CtrlGeral.setTela("verAlunos",verA);
        
        Ver_Professores verP=new Ver_Professores();
        add(verP,"verProfessores");
        CtrlGeral.setTela("verProfessores",verP);
        
        Ver_Categoria cat=new Ver_Categoria();
        add(cat,"categoria");
        CtrlGeral.setTela("categoria",cat);
        
        Ver_Turmas verT=new Ver_Turmas();
        add(verT,"verTurmas");
        CtrlGeral.setTela("verTurmas",verT);
        
        Ver_Turmas2 verT2=new Ver_Turmas2();
        add(verT2,"verTurmas2");
        CtrlGeral.setTela("verTurmas2",verT2);
        
        Ver_Observacoes verO=new Ver_Observacoes();
        add(verO,"verObservacoes");
        CtrlGeral.setTela("verObservacoes",verO);
        
        Ver_Disciplinas verD=new Ver_Disciplinas();
        add(verD,"verDisciplinas");
        CtrlGeral.setTela("verDisciplinas",verD);
        
        AdicionarProfessor addP=new AdicionarProfessor();
        add(addP,"adicionarProfessor");
        CtrlGeral.setTela("adicionarProfessor",addP);
        
        AdicionarAluno addE=new AdicionarAluno();
        add(addE,"addStudent");
        CtrlGeral.setTela("addStudent",addE);
        
        AdicionarDisciplina addD=new AdicionarDisciplina();
        add(addD,"registSubject");
        CtrlGeral.setTela("registSubject",addD);
        
        CriarTurma criarT=new CriarTurma();
        add(criarT,"criarTurma");
        CtrlGeral.setTela("criarTurma",criarT);
        
        RecuperarSenha recupS=new RecuperarSenha();
        add(recupS,"recuperarSenha");
        CtrlGeral.setTela("recuperarSenha",recupS);
        
        CriarSenha criarS=new CriarSenha();
        add(criarS,"criarSenha");
        CtrlGeral.setTela("criarSenha",criarS);
        
        CriarCategoria criarC=new CriarCategoria();
        add(criarC,"CriarCat");
        CtrlGeral.setTela("CriarCat",criarC);
        
        Observacoes observ=new Observacoes();
        add(observ,"observacoes");
        CtrlGeral.setTela("observacoes",observ);
        
        resizing();
    }
    
    
    public void resizing(){
        new Thread(
            new Runnable(){
                public void run(){
                    while(true){
                        try{
                            Thread.sleep(50);
                        }catch(InterruptedException e){
                            System.out.println("Erro: "+e.getMessage());
                        }
                        for(Component cmp:getComponents()){
                            cmp.setSize(getSize());
                        }
                        revalidate();
                    }
                }
            }
        ).start();
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Myclasscount::new);
    }
}

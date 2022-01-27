/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

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
        
        MainFramee mainF=new MainFramee();
        JPanel panel=new JPanel(new GridLayout());
        panel.add(mainF);
        add(panel,"mainFrame");
        
        Ver_Alunos verA=new Ver_Alunos();
        add(verA,"verAlunos");
        
        Ver_Professores verP=new Ver_Professores();
        add(verP,"verProfessores");
        
        Ver_Categoria cat=new Ver_Categoria();
        add(cat,"categoria");
        
        Ver_Turmas verT=new Ver_Turmas();
        add(verT,"verTurmas");
        
        Ver_Turmas2 verT2=new Ver_Turmas2();
        add(verT2,"verTurmas2");
        
        Ver_Observacoes verO=new Ver_Observacoes();
        add(verO,"verObservacoes");
        
        Ver_Disciplinas verD=new Ver_Disciplinas();
        add(verD,"verDisciplinas");
        
        AdicionarProfessor addP=new AdicionarProfessor();
        add(addP,"adicionarProfessor");
        
        AdicionarAluno addE=new AdicionarAluno();
        add(addE,"addStudent");
        
        AdicionarDisciplina addD=new AdicionarDisciplina();
        add(addD,"registSubject");
        
        CriarTurma criarT=new CriarTurma();
        add(criarT,"criarTurma");
        
        RecuperarSenha recupS=new RecuperarSenha();
        add(recupS,"recuperarSenha");
        
        CriarSenha criarS=new CriarSenha();
        add(criarS,"criarSenha");
        
        CriarCategoria criarC=new CriarCategoria();
        add(criarC,"CriarCat");
        
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

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
import javax.swing.JFrame;
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
        this.setSize(940,650);
        this.setMinimumSize(new Dimension(940,650));
        layout=new CardLayout();
        this.setLayout(layout);
        this.setLocationRelativeTo(null);
        container=this.getContentPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addViews();
        frame=this;
        this.setVisible(true);
    }
    
    public void addViews(){
        Loginn login=new Loginn();
        container.add(login,"login");
        
        MainFramee mainF=new MainFramee();
        container.add(mainF,"mainFrame");
        
        Ver_Alunos verA=new Ver_Alunos();
        container.add(verA,"verAlunos");
        
        Ver_Professores verP=new Ver_Professores();
        container.add(verP,"verProfessores");
        
        Ver_Categoria cat=new Ver_Categoria();
        container.add(cat,"categoria");
        
        Ver_Turmas verT=new Ver_Turmas();
        container.add(verT,"verTurmas");
        
        Ver_Turmas2 verT2=new Ver_Turmas2();
        container.add(verT2,"verTurmas2");
        
        Ver_Observacoes verO=new Ver_Observacoes();
        container.add(verO,"verObservacoes");
        
        Ver_Disciplinas verD=new Ver_Disciplinas();
        container.add(verD,"verDisciplinas");
        
        AdicionarProfessor addP=new AdicionarProfessor();
        container.add(addP,"adicionarProfessor");
        
        AdicionarAluno addE=new AdicionarAluno();
        container.add(addE,"addStudent");
        
        AdicionarDisciplina addD=new AdicionarDisciplina();
        container.add(addD,"registSubject");
        
        CriarTurma criarT=new CriarTurma();
        container.add(criarT,"crirTurma");
        
        CriarHorario criarH=new CriarHorario();
        container.add(criarH,"crirHorario");
        
        Inscrever inscricao=new Inscrever();
        container.add(inscricao,"inscricao");
        
        RecuperarSenha recupS=new RecuperarSenha();
        container.add(recupS,"recuperarSenha");
        
        CriarSenha criarS=new CriarSenha();
        container.add(criarS,"criarSenha");
        
        CriarCategoria criarC=new CriarCategoria();
        container.add(criarC,"CriarCat");
        
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
                        for(Component cmp:container.getComponents()){
                            cmp.setSize(container.getSize());
                        }
                        container.revalidate();
                    }
                }
            }
        ).start();
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            new Runnable(){
                public void run(){
                    new Myclasscount();
                }
            }
        );
    }
}

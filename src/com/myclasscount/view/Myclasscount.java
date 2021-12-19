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
        this.setMinimumSize(new Dimension(860,600));
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
        login login=new login();
        container.add(login,"login");
        
        mainFrame mainF=new mainFrame();
        container.add(mainF,"mainFrame");
        
        verAlunos verA=new verAlunos();
        container.add(verA,"verAlunos");
        
        verProfessores verP=new verProfessores();
        container.add(verP,"verProfessores");
        
        Categoria cat=new Categoria();
        container.add(cat,"categoria");
        
        VerTurmas verT=new VerTurmas();
        container.add(verT,"verTurmas");
        
        VerTurmas2 verT2=new VerTurmas2();
        container.add(verT2,"verTurmas2");
        
        verObservacoes verO=new verObservacoes();
        container.add(verO,"verObservacoes");
        
        verDisciplinas verD=new verDisciplinas();
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
        new Myclasscount();
    }
}

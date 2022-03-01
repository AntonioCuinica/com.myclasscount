/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.view;

import com.myclasscount.control.Aluno_ctrl;
import com.myclasscount.control.CtrlGeral;
import com.myclasscount.control.Turma_ctrl;
import com.myclasscount.model.Aluno;
import com.myclasscount.model.Horario;
import com.myclasscount.model.Observacao;
import com.myclasscount.model.Turma;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private Turma turma;
    private Aluno aluno;
    
    public Ver_Turmas2(){
        setLayout(null);
        container=this;
        setBackground(backColor.darker());
        title=MyProceduress.barName("Turma",this);
        add(title);
        setVisible(true);
    }
    
    public void addTable(Turma turma){
        this.turma=turma;
        Panel mainPane=new Panel(Color.white,false);
        mainPane.setLayout(new BorderLayout());
        
        JPanel pan=new JPanel(new GridLayout());
        pan.setBackground(backColor.darker());
        pan.setBorder(new EmptyBorder(10,0,10,0));
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
        dados1[0][0]="Inicio";
        dados1[1][0]="Fim";
        for(int i=0; i<turma.getHorarios().size() && i<dados1[0].length;i++){
            switch(turma.getHorarios().get(i).getDia_semana()){
                case "Segunda" -> {
                     dados1[0][1]=turma.getHorarios().get(i).getHora_inicio();
                     dados1[1][1]=turma.getHorarios().get(i).getHora_fim();
                }
                case "Terça" -> {
                    dados1[0][2]=turma.getHorarios().get(i).getHora_inicio();
                    dados1[1][2]=turma.getHorarios().get(i).getHora_fim();
                }
                case "Quarta" -> {
                    dados1[0][3]=turma.getHorarios().get(i).getHora_inicio();
                    dados1[1][3]=turma.getHorarios().get(i).getHora_fim();
                }
                case "Quinta" -> {
                    dados1[0][4]=turma.getHorarios().get(i).getHora_inicio();
                    dados1[1][4]=turma.getHorarios().get(i).getHora_fim();
                }
                case "Sexta" -> {
                    dados1[0][5]=turma.getHorarios().get(i).getHora_inicio();
                    dados1[1][5]=turma.getHorarios().get(i).getHora_fim();
                }
                case "Sabado" -> {
                    dados1[0][6]=turma.getHorarios().get(i).getHora_inicio();
                    dados1[1][6]=turma.getHorarios().get(i).getHora_fim();
                }
                case "Domingo" -> {
                    dados1[0][7]=turma.getHorarios().get(i).getHora_inicio();
                    dados1[1][7]=turma.getHorarios().get(i).getHora_fim();
                }
            } 
            
        }
        tabela.setTableData(dados1);
        tabela.setBackground(Color.black);
        removeHorario(tabela,Myclasscount.getFrame());
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
        MyButtonn eliminar=new MyButtonn("Eliminar",false);
        eliminar.setSize(90,25);
        eliminar.addMouseListener(new Clique());
        eliminar.setVisible(false);
        
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
        container.add(eliminar);
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
                        y=((container.getHeight()-(mainPane.getHeight()+mainPane.getY())))-(eliminar.getHeight()/2);
                        x=title.getWidth()+title.getX()-eliminar.getWidth();
                        eliminar.setLocation(x,container.getHeight()-y);
                        x=eliminar.getX()-eliminar.getWidth();
                        modificar.setLocation(x-10,eliminar.getY());
                        x=modificar.getX()-modificar.getWidth();
                        voltar.setLocation(x-10,modificar.getY());
                        
                        voltar.setVisible(true);
                        modificar.setVisible(true);
                        eliminar.setVisible(true);
                        container.revalidate();
                    }
                }
            }
        ).start();
    }
    
    public void removeHorario(Table horarios,JFrame frame){
        horarios.addMouseListener(
            new MouseAdapter(){
                public void mouseClicked(MouseEvent evento){
                    if(evento.getClickCount()==2 && horarios.getSelectedColumn()!=0){
                        MyDialogg rm=new MyDialogg(frame,2,"Remover horario?", true);
                        if(rm.getSimTeste()){
                            int coluna=horarios.getSelectedColumn();
                            String dia_semana=new String();
                            switch(coluna){
                                case 1 ->{
                                    dia_semana="Segunda";
                                }
                                case 2 ->{
                                    dia_semana="Terça";
                                }
                                case 3 ->{
                                    dia_semana="Quarta";
                                }
                                case 4 ->{
                                    dia_semana="Quinta";
                                }
                                case 5 ->{
                                    dia_semana="Sexta";
                                }
                                case 6 ->{
                                    dia_semana="Sabado";
                                }
                                case 7 ->{
                                    dia_semana="Domingo";
                                }
                            }
                            Turma_ctrl.delHorario(turma, dia_semana);
                            for(Horario hor:turma.getHorarios()){
                                if(dia_semana.equals(hor.getDia_semana())){
                                    turma.getHorarios().remove(hor);
                                    break;
                                }
                            }
                            updateComponents(turma);
                        }
                        
                    }
                }
            }
        );
    }
    
    public void verAlunos(JFrame frame){
        tabela.addMouseListener(
            new MouseAdapter(){
                public void mouseClicked(MouseEvent evento){
                    if(evento.getClickCount()==2){
                        int linha=tabela.getSelectedRow();
                        String aluno_bi=tabela.getValueAt(linha,2).toString();
                        MyDialogg dialog=new MyDialogg(frame,true);
                        dialog.setLayout(new BorderLayout());
                        dialog.setSize(400,300);
                        dialog.setLocationRelativeTo(frame);
                        MyButtonn observacao=new MyButtonn("Adicionar Observacao",false);
                        observacao.addActionListener(
                            (ActionEvent e)->{
                                Observacoes observ=(Observacoes)CtrlGeral.getTela("observacoes");
                                turma.getAlunos().forEach(
                                        aln->{
                                            if(aluno_bi.equals(aln.getBI())){
                                                observ.setAluno(aln);
                                            }
                                        }
                                    );
                                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"observacoes");
                                dialog.dispose();
                            }
                        );
                        MyButtonn remover=new MyButtonn("Remover estudante",false);
                        remover.addActionListener(
                            (ActionEvent e) -> {
                                MyDialogg rm=new MyDialogg(frame,2,"Remover estudante ?", true);
                               
                                if(rm.getSimTeste()){
                                    turma.getAlunos().forEach(
                                        aln->{
                                            if(aluno_bi.equals(aln.getBI())){
                                                Turma_ctrl.delAluno_turma(turma,aln.getId());
                                                aluno=aln;
                                            }
                                        }
                                    );
                                    turma.getAlunos().remove(aluno);
                                    updateComponents(turma);
                                    dialog.dispose();
                                }
                            }
                        );
                        JPanel btns=new JPanel();
                        btns.setBorder(new EmptyBorder(30,30,30,30));
                        btns.setLayout(new BoxLayout(btns,BoxLayout.Y_AXIS));
                        btns.setOpaque(false);
                        btns.add(observacao);
                        btns.add(Box.createRigidArea(new Dimension(0,30)));
                        btns.add(remover);
                        dialog.add(btns,BorderLayout.CENTER);
                        dialog.setVisible(true);
                    }
                }
            }
        );
    }

    void updateComponents(Turma turma) {
        removeAll();
        title=MyProceduress.barName(turma.getNome(),this);
        container.add(title);
        addTable(turma);
    }
    
    private class Clique extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            if(e.getComponent().toString().contains("Voltar")){
                Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verTurmas");
            }else if(e.getComponent().toString().contains("Modificar")){
                ModificarTurma modT=new ModificarTurma(Myclasscount.getFrame(),turma,true);
                modT.setVisible(true);
            }else if(e.getComponent().toString().contains("Eliminar")){
                MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),2,"Deseja remover, "+turma.getNome()+" ?", true);
                if(dialog.getSimTeste()){
                    Turma_ctrl.removerTurma(turma);
                    MyProceduress.updateVerTurmas();
                    Myclasscount.getCardLayout().show(Myclasscount.getContainer(),"verTurmas");
                }
            }
        }
    }
    
}

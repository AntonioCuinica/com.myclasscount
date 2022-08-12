/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myclasscount.view;

import com.myclasscount.control.Aluno_ctrl;
import com.myclasscount.control.CtrlGeral;
import com.myclasscount.control.Mensalidade_ctrl;
import com.myclasscount.model.Aluno;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author CUINIC4
 */

public class Mensalidade extends JPanel {
    private Color backColor=new Color(0,24,242);
    private int linhaCentro=0;
    boolean btnPagar_visivel=true;
    private double tPago=0,tDever=0;
    private String mes;
    private String ano;
    private String dia;
    private ArrayList<JTextField> txtF;
    private ArrayList<Aluno> alunos;
    
    public String getMes(){
        return mes;
    }
    
    public Mensalidade(String dia, String mes, String ano){
        this.dia=dia;
        this.mes=mes;
        this.ano=ano;
        setLayout(null);
        setBackground(backColor.darker());
        setVisible(true);
    }
    
    
    
    public void addCategoria(String mes, String ano){
        txtF=new ArrayList();
        
        ArrayList<com.myclasscount.model.Mensalidade> mensa;
        mensa=getMensalidade(mes,ano);
        
        alunos=Aluno_ctrl.getAlunos();
        
        JPanel mainPane=new JPanel();
        mainPane.setLayout(new BorderLayout());
        
        JPanel descPanel=descricao(dia,mes,ano,mensa);
        
        Panel mPanel=new Panel(Color.white,true);
        GridLayout gLyt=new GridLayout(1,3,15,0);
        BoxLayout bLyt=new BoxLayout(mPanel,BoxLayout.Y_AXIS);
        mPanel.invisible(true,false);
        mPanel.setLayout(bLyt);
        mPanel.setBorder(new EmptyBorder(15,15,0,15));
        
        Panel[] bts=new Panel[mensa.size()];
        
        for(int i=0;i<bts.length;i++){
            bts[i]=new Panel(Color.black,true);
            bts[i].setBorder(new EmptyBorder(2,2,2,2));
            bts[i].setLayout(new BorderLayout());
            bts[i].setMinimumSize(new Dimension(250,350));
            bts[i].setPreferredSize(new Dimension(250,250));
            
            Aluno aluno=null;
            for(Aluno a:alunos){
                if(a.getId()==mensa.get(i).getAluno_id()){
                    aluno=a;
                    break;
                }
            }
            
            if(mensa.get(i).getEstado().equals("aberta")){
                if(Calendar.getInstance().getTime().after(mensa.get(i).getDataPagamento())){
                    mensa.get(i).setEstado("divida");
                    Mensalidade_ctrl.updateMensalidade(mensa.get(i));
                }
            }
            
            JPanel status=status(mensa.get(i).getEstado());
            
            JPanel centro=centro(aluno,mensa.get(i));
            centro.setBorder(new EmptyBorder(2,2,2,2));
            
            JButton btn=new JButton("Ver Aluno");
            btn.addMouseListener(new Clique(aluno,mensa.get(i)));
            btn.setBackground(Color.white);
            btn.setForeground(Color.blue);
            
            
            bts[i].add(status,BorderLayout.NORTH);
            bts[i].add(centro,BorderLayout.CENTER);
            bts[i].add(btn,BorderLayout.SOUTH);
        }
        
        Panel pan1=null;
        int count=0;
        for(int i=0;i<bts.length;i++){
            count++;
            if(i==0){
                pan1=new Panel(backColor.darker(),false);
                pan1.setLayout(gLyt);
                pan1.invisible(true,true);
                pan1.add(bts[i]);
            }else if(i%3==0){
                pan1=new Panel(backColor.darker(),false);
                pan1.setLayout(gLyt);
                pan1.invisible(true,true);
                pan1.add(bts[i]);
            }else{
                pan1.add(bts[i]);
            }
            if(count==3 || i==(bts.length-1)){
                if(count!=3 && i==(bts.length-1)){
                    for(int j=0;j<=count;j++){
                        if(pan1.getComponentCount()<3)pan1.add(new JLabel());
                    }
                }
                mPanel.add(pan1);
                mPanel.add(Box.createRigidArea(new Dimension(0,15)));
                count=0;
            }
        }
        
        JScrollPane src=new JScrollPane(mPanel);
        src.getViewport().setBackground(backColor.darker());
        
        mainPane.add(descPanel,BorderLayout.NORTH);
        mainPane.add(src,BorderLayout.CENTER);
        
        add(mainPane);
        
        new Thread(
            new Runnable(){
                public void run(){
                    while(true){
                        try{
                            Thread.sleep(50);
                        }catch(InterruptedException e){
                            System.out.println("Erro: "+e.getMessage());
                        }
                        mainPane.setBounds(0,0,getWidth(),getHeight());
                        revalidate();
                    }
                }
            }
        ).start();
    }
    
    public ArrayList<com.myclasscount.model.Mensalidade> getMensalidade(String mes,String ano){
        ArrayList<com.myclasscount.model.Mensalidade> mensa;
        mensa=Mensalidade_ctrl.getMensalidades(ano);
        for(int i=0;i<mensa.size();i++){
            String mes1=new SimpleDateFormat("MMMM",Locale.forLanguageTag("PT-BR")).format(mensa.get(i).getDataPagamento());
            if(!mes1.equals(mes)){
                mensa.remove(i);
                i--;
            } 
        }
        return mensa;
    }
    
    
    public JPanel descricao(String dia,String mes,String ano,ArrayList<com.myclasscount.model.Mensalidade> mensa){
        JPanel desc=new JPanel(new GridLayout(1,2));
        desc.setBackground(Color.black);
        desc.setBorder(new EmptyBorder(1,1,1,1));
        
        JPanel esc=new JPanel();
        esc.setBackground(backColor.darker().darker());
        esc.add(MyProceduress.info(mes+" "+ano,"",Color.white,25));
        
        JPanel dir=new JPanel(new GridLayout(2,1));
        dir.setBackground(backColor.darker().darker());
        double totP=0,totD=0;
        for(com.myclasscount.model.Mensalidade m:mensa){
            totP+=m.getValor();
            String p=Aluno_ctrl.pagamento(m.getAluno_id())[2];
            if(p!=null){
                double pag=Double.parseDouble(p);
                totD+=(pag-m.getValor());
            } 
        }
        dir.add(MyProceduress.info("Total pago",": "+totP, Color.white,14));
        dir.add(MyProceduress.info("Total em dívida",": "+totD, Color.white,14));
        //dir.add(MyProceduress.info("Numero de alunos",": "+mensa.size(), Color.white,14));
        
        desc.add(esc); desc.add(dir);
        return desc;
    }
    
    public JPanel status(String txt){
        JPanel panel=new JPanel(new BorderLayout());
        btnPagar_visivel=true;
        linhaCentro=7;
        
        if(txt.equals("aberta")){
            panel.setBackground(Color.BLUE);
        }else if(txt.equals("divida")){
            panel.setBackground(Color.red);
        }else if(txt.equals("paga")){
            panel.setBackground(Color.green.darker());
            btnPagar_visivel=false;
            linhaCentro=6;
        }
        
        JButton imprimir=new JButton();
        imprimir.setLayout(new GridLayout());
        imprimir.setVisible(false);
        imprimir.addActionListener(
            (ActionEvent)->{
            
            }
        );
        MyLabell icon=new MyLabell("img/print.png");
        JPanel user=new JPanel(new GridLayout());
        user.setPreferredSize(new Dimension(20,20));
        user.setMaximumSize(new Dimension(20,20));
        user.setOpaque(false);
        user.add(icon);
        imprimir.add(user);
        imprimir.setContentAreaFilled(false);
        JLabel texto=new JLabel(txt,SwingConstants.CENTER);
        texto.setForeground(Color.white);
        texto.setFont(new Font("Arial",Font.BOLD,16));
        panel.add(texto,BorderLayout.CENTER);
        panel.add(imprimir,BorderLayout.EAST);
        return panel;
    }
    
    public JPanel centro(Aluno aluno,com.myclasscount.model.Mensalidade mensa){
        JPanel panel=new JPanel(new GridLayout(linhaCentro,1));
        panel.setBackground(Color.white);
        System.out.println("aluno: "+mensa.getAluno_id());
        panel.add(MyProceduress.info("Nome",": "+aluno.getNome()+" "+aluno.getApelido(),Color.black,14));
        panel.add(MyProceduress.info("Telefone",": "+aluno.getTelefone(),Color.black,14));
        
        double pag=0;
        if(Aluno_ctrl.pagamento(aluno.getId())[2]!=null){
            pag=Double.parseDouble(Aluno_ctrl.pagamento(aluno.getId())[2]);
            if(pag<(mensa.getDivida()+mensa.getValor())){
                pag=mensa.getDivida()+mensa.getValor();
            }
        }
        tPago=mensa.getValor();
        tDever=pag-mensa.getValor();
        
        if(pag!=0){
            mensa.setDivida(tDever);
        }else{
            tDever=mensa.getDivida();
        }
        
        if(mensa.getEstado().equals("paga")){
           pag=tPago;
           tDever=0.0;
        }
        panel.add(MyProceduress.info("Total a pagar",": "+pag,Color.black,14));
        panel.add(MyProceduress.info("Valor pago",": "+tPago,Color.black,14));
        panel.add(MyProceduress.info("Valor a dever",": "+tDever,Color.black,14));
        panel.add(MyProceduress.info("Prazo",": "+new SimpleDateFormat("dd/MM/yyyy").format(mensa.getDataPagamento()),Color.black,14));
        
        if(btnPagar_visivel){
            JPanel pan=new JPanel(new GridLayout(1,2));
            pan.setVisible(btnPagar_visivel);
            pan.setOpaque(false);
            JTextField t=new JTextField();
            t.setName(aluno.getBI());
            txtF.add(t);

            MyButtonn btn=new MyButtonn("pagar",false);
            btn.addMouseListener(new Clique(aluno,mensa));
            pan.add(t); pan.add(btn);

            panel.add(pan);
        }
        
        return panel;
    }
    
    public void updateComponents(){
        removeAll();
        addCategoria(mes,ano);
    }
    
    private class Clique extends MouseAdapter {
        private Aluno aluno;
        private com.myclasscount.model.Mensalidade mensalidade;
        
        public Clique(Aluno aluno,com.myclasscount.model.Mensalidade mensalidade){
            this.aluno=aluno;
            this.mensalidade=mensalidade;
        }
        
        @Override
        public void mouseClicked(MouseEvent e){
            if(e.getSource().toString().contains("Ver Aluno")){
                MyDialogg ver_aluno=MyProceduress.ver_dialog(aluno,Myclasscount.getFrame());
            }else if(e.getSource().toString().contains("pagar")){
                JTextField v=null;
                for(JTextField t:txtF){
                    if(t.getName().equals(aluno.getBI())){
                        v=t;
                        break;
                    }
                }
                String valido=CtrlGeral.validarNumero(v.getText(),"Valor");
                if(valido.equals("valido")){
                    double valor=Double.parseDouble(v.getText());
                    double pag=0;
                    if(Aluno_ctrl.pagamento(aluno.getId())[2]!=null){
                        pag=Double.parseDouble(Aluno_ctrl.pagamento(aluno.getId())[2]);
                    }
                    if((valor+mensalidade.getValor())<=pag || (valor>0 && valor<=mensalidade.getDivida())){
                        mensalidade.setValor(mensalidade.getValor()+valor);
                        mensalidade.mudarEstado(pag);
                        if((valor>0 && valor<=mensalidade.getDivida())){
                            mensalidade.setDivida(mensalidade.getDivida()-valor);
                            if(mensalidade.getDivida()==0){
                                mensalidade.setEstado("paga");
                            }
                        }else{
                            mensalidade.setDivida(pag-mensalidade.getValor());
                        }
                        Mensalidade_ctrl.updateMensalidade(mensalidade);
                        MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Pagamento feito com sucesso",true);
                        updateComponents();
                    }else{
                        MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,"Erro, valor maior que pagamento",true);
                        v.setText("");
                        v.grabFocus();
                    }
                }else{
                    MyDialogg dialog=new MyDialogg(Myclasscount.getFrame(),1,valido,true);
                    v.setText("");
                    v.grabFocus();
                }
            }
        }
    }
}

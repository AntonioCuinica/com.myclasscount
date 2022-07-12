/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.dao;

import com.myclasscount.model.Mensalidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CUINIC4
 */

public class Mensalidade_dao {
    public static Mensalidade getMensalidade(int id){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM mensalidade WHERE id=? order by dataP;";
        Mensalidade mensalidade=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                mensalidade=new Mensalidade();
                mensalidade.setId(rs.getInt("id"));
                mensalidade.setEstado(rs.getString("estado"));
                Date data;
                try {
                    data = new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("dataP"));
                    mensalidade.setDataPagamento(data);
                } catch (ParseException ex) {
                    Logger.getLogger(Mensalidade_dao.class.getName()).log(Level.SEVERE, null, ex);
                }
                mensalidade.setValor(rs.getDouble("valor"));
                mensalidade.setAluno_id(rs.getInt("aluno_id"));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getMensalidade id: "+e.getMessage());
        }
        return mensalidade;
    }
    
    public static ArrayList<Mensalidade> getMensalidades(){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM mensalidade order by dataP;";
        ArrayList<Mensalidade> mensalidades=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Mensalidade mensalidade=new Mensalidade();
                mensalidade.setId(rs.getInt("id"));
                mensalidade.setEstado(rs.getString("estado"));
                Date data;
                try {
                    data = new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("dataP"));
                    mensalidade.setDataPagamento(data);
                } catch (ParseException ex) {
                    Logger.getLogger(Mensalidade_dao.class.getName()).log(Level.SEVERE, null, ex);
                }
                mensalidade.setValor(rs.getDouble("valor"));
                mensalidade.setAluno_id(rs.getInt("aluno_id"));
                mensalidades.add(mensalidade);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getMensalidades: "+e.getMessage());
        }
        return mensalidades;
    }
    
    public static ArrayList<Mensalidade> getMensalidadesDiferentes(){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM mensalidade group by dataP order by dataP;";
        ArrayList<Mensalidade> mensalidades=new ArrayList();
        int anoSize=(Integer.parseInt(new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime())))-2022;
        if(anoSize<=0){
            anoSize=1;
        }else{
            anoSize++;
        }
        int m[][]=new int[anoSize][12];
        Mensalidade mensa[][]=new Mensalidade[anoSize][12];
        int mes=0;int ano=0;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Mensalidade mensalidade=new Mensalidade();
                mensalidade.setId(rs.getInt("id"));
                mensalidade.setEstado(rs.getString("estado"));
                Date data;
                try {
                    data = new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("dataP"));
                    mes=Integer.parseInt(new SimpleDateFormat("MM").format(data));
                    ano=Integer.parseInt(new SimpleDateFormat("yyyy").format(data))-2022;
                    if(ano>=anoSize)ano=anoSize-1;
                    mensalidade.setDataPagamento(data);
                } catch (ParseException ex) {
                    Logger.getLogger(Mensalidade_dao.class.getName()).log(Level.SEVERE, null, ex);
                }
                mensalidade.setValor(rs.getDouble("valor"));
                mensalidade.setAluno_id(rs.getInt("aluno_id"));
                if(m[ano][mes-1]==0){
                    m[ano][mes-1]=mes;
                    mensa[ano][mes-1]=mensalidade;
                }
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getMensalidadesDiferentes: "+e.getMessage());
        }
        
        for(int i=0;i<mensa.length;i++){
            for(int j=0;j<mensa[0].length;j++){
                if(mensa[i][j]!=null)mensalidades.add(mensa[i][j]);
            }
        }
                
        return mensalidades;
    }
    
    public static ArrayList<Mensalidade> getMensalidades(int aluno_id){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM mensalidade WHERE aluno_id=?;";
        ArrayList<Mensalidade> mensalidades=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,aluno_id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Mensalidade mensalidade=new Mensalidade();
                mensalidade.setId(rs.getInt("id"));
                mensalidade.setEstado(rs.getString("estado"));
                Date data;
                try {
                    data = new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("dataP"));
                    mensalidade.setDataPagamento(data);
                } catch (ParseException ex) {
                    Logger.getLogger(Mensalidade_dao.class.getName()).log(Level.SEVERE, null, ex);
                }
                mensalidade.setValor(rs.getDouble("valor"));
                mensalidade.setAluno_id(rs.getInt("aluno_id"));
                mensalidades.add(mensalidade);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getMensalidades aluno_id: "+e.getMessage());
        }
        return mensalidades;
    }
    
    public static ArrayList<Mensalidade> getMensalidades(String ano){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM mensalidade order by dataP;";
        ArrayList<Mensalidade> mensalidades=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Date data=null;
                try {
                    data = new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("dataP"));
                } catch (ParseException ex) {
                    Logger.getLogger(Mensalidade_dao.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(data!=null){
                    String a=new SimpleDateFormat("yyyy").format(data);
                    if(a.equals(ano)){
                        Mensalidade mensalidade=new Mensalidade();
                        mensalidade.setId(rs.getInt("id"));
                        mensalidade.setEstado(rs.getString("estado"));
                        Date dt;
                        try {
                            dt = new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("dataP"));
                            mensalidade.setDataPagamento(dt);
                        } catch (ParseException ex) {
                            Logger.getLogger(Mensalidade_dao.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        mensalidade.setValor(rs.getDouble("valor"));
                        mensalidade.setAluno_id(rs.getInt("aluno_id"));
                        mensalidades.add(mensalidade);
                    }
                }
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getMensalidade ano: "+e.getMessage());
        }
        return mensalidades;
    }
    
    public static void updateMensalidade(Mensalidade mensalidade){
        Connection con=Conexaoo.getConnection();
        String select="UPDATE mensalidade SET estado=?,valor=? WHERE id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,mensalidade.getEstado());
            stmt.setDouble(2,mensalidade.getValor());
            stmt.setInt(3,mensalidade.getId());
            stmt.execute();
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro updateMensalidade: "+e.getMessage());
        }
    }
    
    public static void setMensalidade(int aluno_id, Date dataInicial){
        Connection con=Conexaoo.getConnection();
        //String select="CALL inserir_mensalidade(?,?);";
        String select="insert into mensalidade(estado,aluno_id,valor,dataP) values('aberta',?,'0',?);";
        
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,aluno_id);
            Calendar g=Calendar.getInstance();
            g.setTime(dataInicial);
            g.add(Calendar.DAY_OF_MONTH,31);
            String dataFinal=new SimpleDateFormat("dd/MM/yyyy").format(g.getTime());
            stmt.setString(2,dataFinal);
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro setMensalidade aluno_id e dataI: "+e.getMessage());
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model.dao;

import com.myclasscount.model.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author CUINIC4
 */

public class Aluno_dao {
    
    public static Aluno getAluno(final String BI){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM aluno WHERE BI=? order by categoria_id ,nome ";
        Aluno aluno=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,BI);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                aluno=new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setApelido(rs.getString("apelido"));
                aluno.setBI(rs.getString("BI"));
                aluno.setNascimento(rs.getString("nascimento"));
                aluno.setSexo(rs.getString("sexo"));
                aluno.setNivel(rs.getString("nivel"));
                aluno.setMorada(rs.getString("morada"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setEmail(rs.getString("email"));
                aluno.setCategoria_id(rs.getInt("categoria_id"));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getAluno BI: "+e.getMessage());
        }
        return aluno;
    }
    
    public static Aluno getAlunoID(int aluno_id){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM aluno WHERE id=? order by categoria_id ,nome ";
        Aluno aluno=null;
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,aluno_id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                aluno=new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setApelido(rs.getString("apelido"));
                aluno.setBI(rs.getString("BI"));
                aluno.setNascimento(rs.getString("nascimento"));
                aluno.setSexo(rs.getString("sexo"));
                aluno.setNivel(rs.getString("nivel"));
                aluno.setMorada(rs.getString("morada"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setEmail(rs.getString("email"));
                aluno.setCategoria_id(rs.getInt("categoria_id"));
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getAluno ID: "+e.getMessage());
        }
        return aluno;
    }
    
    public static ArrayList<Aluno> getAlunos(){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM aluno order by categoria_id ,nome";
        ArrayList<Aluno> alunos=new ArrayList();
        try{
            try (PreparedStatement stmt = con.prepareStatement(select)) {
                ResultSet rs=stmt.executeQuery();
                while(rs.next()){
                    Aluno aluno=new Aluno();
                    aluno.setId(rs.getInt("id"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setApelido(rs.getString("apelido"));
                    aluno.setBI(rs.getString("BI"));
                    aluno.setNascimento(rs.getString("nascimento"));
                    aluno.setSexo(rs.getString("sexo"));
                    aluno.setNivel(rs.getString("nivel"));
                    aluno.setMorada(rs.getString("morada"));
                    aluno.setTelefone(rs.getString("telefone"));
                    aluno.setEmail(rs.getString("email"));
                    aluno.setCategoria_id(rs.getInt("categoria_id"));
                    alunos.add(aluno);
                }
            }
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getAlunos: "+e.getMessage());
        }
        return alunos;
    }
    
    public static ArrayList<Aluno> getAlunosInscritos(){
        Connection con=Conexaoo.getConnection();
        String select="SELECT a.* FROM aluno a join pagamento p on a.id=p.id order by a.categoria_id ,a.nome";
        ArrayList<Aluno> alunos=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Aluno aluno=new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setApelido(rs.getString("apelido"));
                aluno.setBI(rs.getString("BI"));
                aluno.setNascimento(rs.getString("nascimento"));
                aluno.setSexo(rs.getString("sexo"));
                aluno.setNivel(rs.getString("nivel"));
                aluno.setMorada(rs.getString("morada"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setEmail(rs.getString("email"));
                aluno.setCategoria_id(rs.getInt("categoria_id"));
                alunos.add(aluno);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro getAlunosInscritos: "+e.getMessage());
        }
        return alunos;
    }
    
    public static void updateAluno(Aluno aluno){
        Connection con=Conexaoo.getConnection();
        String select="UPDATE aluno SET nome=?,apelido=?,BI=?,nascimento=?,sexo=?,nivel=?,morada=?,telefone=?,email=?,categoria_id=? WHERE id=?";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,aluno.getNome());
            stmt.setString(2,aluno.getApelido());
            stmt.setString(3,aluno.getBI());
            stmt.setString(4,aluno.getNascimento());
            stmt.setString(5,aluno.getSexo());
            stmt.setString(6,aluno.getNivel());
            stmt.setString(7,aluno.getMorada());
            stmt.setString(8,aluno.getTelefone());
            stmt.setString(9,aluno.getEmail());
            stmt.setInt(10,aluno.getCategoria_id());
            stmt.setInt(11,aluno.getId());
            stmt.execute();
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro updateAluno: "+e.getMessage());
        }
    }
    
    public static void setAluno(Aluno aluno){
        Connection con=Conexaoo.getConnection();
        String select="insert into aluno (nome,apelido,BI,nascimento,sexo,nivel,morada,telefone,email,categoria_id) "
                + "values (?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setString(1,aluno.getNome());
            stmt.setString(2,aluno.getApelido());
            stmt.setString(3,aluno.getBI());
            stmt.setString(4,aluno.getNascimento());
            stmt.setString(5,aluno.getSexo());
            stmt.setString(6,aluno.getNivel());
            stmt.setString(7,aluno.getMorada());
            stmt.setString(8,aluno.getTelefone());
            stmt.setString(9,aluno.getEmail());
            stmt.setInt(10,aluno.getCategoria_id());
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro setAluno: "+e.getMessage());
        }
    }
    
    public static boolean delAluno(int aluno_id){
        Connection con=Conexaoo.getConnection();
        String delete1="delete from observacao where aluno_id=?";
        String delete2="DELETE FROM aluno WHERE id=?;";
        String delete3="DELETE FROM mensalidade WHERE aluno_id=?;";
        String delete4="DELETE FROM aluno_turma WHERE aluno_id=?;";
        try{
            PreparedStatement stmt=con.prepareStatement(delete1);
            stmt.setInt(1,aluno_id);
            stmt.execute();
            
            stmt=con.prepareStatement(delete2);
            stmt.setInt(1,aluno_id);
            stmt.execute();
            
            stmt=con.prepareStatement(delete3);
            stmt.setInt(1,aluno_id);
            stmt.execute();
            
            stmt=con.prepareStatement(delete4);
            stmt.setInt(1,aluno_id);
            stmt.execute();
            
            
            stmt.close();
            con.close();
            
            return true;
        }catch(SQLException e){
            System.out.println("Erro delAluno: "+e.getMessage());
            return false;
        }
    }
    
    public static String[] pagamento(int aluno_id){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM pagamento WHERE id=?";
        String pag[]=new String[3];
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,aluno_id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                pag[0]=rs.getString("id");
                pag[1]=rs.getString("aluno");
                pag[2]=rs.getString("pagamento");
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro pagamentoAluno: "+e.getMessage());
        }
        return pag;
    }
    
    public static ArrayList<String[]> inscricoes(int aluno_id){
        Connection con=Conexaoo.getConnection();
        String select="SELECT * FROM inscricoes WHERE id=?";
        ArrayList inscricoes=new ArrayList();
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,aluno_id);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                String pag[]=new String[5];
                pag[0]=rs.getString("id");
                pag[1]=rs.getString("aluno");
                pag[2]=rs.getString("categoria");
                pag[3]=rs.getString("disciplina");
                pag[4]=rs.getString("preco");
                inscricoes.add(pag);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro inscricoesAluno: "+e.getMessage());
        }
        return inscricoes;
    }
    
    public static void setInscricao(int aluno_id,int disc_id){
        Connection con=Conexaoo.getConnection();
        String select="insert into inscricao(dataI,aluno_id,disciplina_id,taxa_inscricao) values(?,?,?,?)";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            Date data=Calendar.getInstance().getTime();
            String dt=new SimpleDateFormat("yyyy-MM-dd").format(data);
            stmt.setString(1,dt);
            stmt.setInt(2,aluno_id);
            stmt.setInt(3,disc_id);
            stmt.setDouble(4,0.0);
            stmt.execute(); 
            
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Erro setInscricaoAluno: "+e.getMessage());
        }
    }
    
    public static boolean delAlunoInscricao(int aluno_id, int disc_id){
        Connection con=Conexaoo.getConnection();
        String select="DELETE FROM inscricao WHERE aluno_id=? and disciplina_id=?";
        try{
            PreparedStatement stmt=con.prepareStatement(select);
            stmt.setInt(1,aluno_id);
            stmt.setInt(2,disc_id);
            stmt.execute(); 
            
            stmt.close();
            con.close();
            
            return true;
        }catch(SQLException e){
            System.out.println("Erro delAlunoInscricao: "+e.getMessage());
            return false;
        }
    }
   
}

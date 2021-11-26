/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model;

import java.util.Date;

/**
 *
 * @author CUINIC4
 */
public class Aluno {
    private int id;
    private int categoria_id;
    private String nome;
    private String apelido;
    private long BI;
    private Date nascimento;
    private String sexo;
    private String nivel;
    private String morada;
    private String telefone;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public long getBI() {
        return BI;
    }

    public void setBI(long BI) {
        this.BI = BI;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aluno\nid=").append(id);
        sb.append("\ncategoria_id=").append(categoria_id);
        sb.append("\nnome=").append(nome);
        sb.append("\napelido=").append(apelido);
        sb.append("\nBI=").append(BI);
        sb.append("\nnascimento=").append(nascimento);
        sb.append("\nsexo=").append(sexo);
        sb.append("\nnivel=").append(nivel);
        sb.append("\nmorada=").append(morada);
        sb.append("\ntelefone=").append(telefone);
        sb.append("\nemail=").append(email);
        return sb.toString();
    }
    
   /* 
    public static void main(String[] args) throws ParseException {
        Aluno a=new Aluno();
        a.setNome("Alves");
        a.setApelido("Cuinica");
        a.setNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("22/01/2022"));
        a.setEmail("älves@gmail.com");
        System.out.println(a);
    }
    */
}
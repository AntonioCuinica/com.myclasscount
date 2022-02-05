/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasscount.model;
/**
 *
 * @author CUINIC4
 */
public class Professor {
    private int id;
    private String nome;
    private String apelido;
    private String BI;
    private String nascimento;
    private String sexo;
    private String nivel;
    private String morada;
    private String telefone;
    private String email;
    private String NUIT;
    private double salario=0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getBI() {
        return BI;
    }

    public void setBI(String BI) {
        this.BI = BI;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
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

    public String getNUIT() {
        return NUIT;
    }

    public void setNUIT(String NUIT) {
        this.NUIT = NUIT;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PROFESSOR\nid=").append(id);
        sb.append("\nnome=").append(nome);
        sb.append("\napelido=").append(apelido);
        sb.append("\nBI=").append(BI);
        sb.append("\nnascimento=").append(nascimento);
        sb.append("\nsexo=").append(sexo);
        sb.append("\nnivel=").append(nivel);
        sb.append("\nmorada=").append(morada);
        sb.append("\ntelefone=").append(telefone);
        sb.append("\nemail=").append(email);
        sb.append("\nNUIT=").append(NUIT);
        sb.append("\nsalario=").append(salario);
        return sb.toString();
    }
    
    /*
    public static void main(String[] args) throws ParseException {
        Professor a=new Professor();
        a.setNome("Alves");
        a.setApelido("Cuinica");
        a.setNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("22/01/2022"));
        a.setEmail("älves@gmail.com");
        System.out.println(a);
    }
    */
}
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
public class Acesso {
    private int id;
    private int professor_id;
    private String username;
    private String codigo;
    private String pergunta;
    private String resposta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfessor_id() {
        return professor_id;
    }

    public void setProfessor_id(int professor_id) {
        this.professor_id = professor_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Acesso\nid=").append(id);
        sb.append("\nprofessor_id=").append(professor_id);
        sb.append("\nusername=").append(username);
        sb.append("\ncodigo=").append(codigo);
        sb.append("\npergunta=").append(pergunta);
        sb.append("\nresposta=").append(resposta);
        return sb.toString();
    }
    
    
}

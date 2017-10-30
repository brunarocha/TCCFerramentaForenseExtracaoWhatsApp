/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;


/**
 *
 * @author BRUNA
 */
public class Usuario {
    
    private Long id;
    private String login;
    private String senha;
    private String nome;
    private String diretorioCasos;

    public Usuario() {
    }

    public Usuario(String login, String senha, String nome, String diretorioCasos) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.diretorioCasos = diretorioCasos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiretorioCasos() {
        return diretorioCasos;
    }

    public void setDiretorioCasos(String diretorioCasos) {
        this.diretorioCasos = diretorioCasos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
}
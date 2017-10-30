/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Bruna
 */
public class Configuracao {
    
    private Long id;
    private Usuario usuario;
    private String diretorioCaso;

    public Configuracao() {
    }

    public Configuracao(Long id, Usuario usuario, String diretorioCaso) {
        this.id = id;
        this.usuario = usuario;
        this.diretorioCaso = diretorioCaso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDiretorioCaso() {
        return diretorioCaso;
    }

    public void setDiretorioCaso(String diretorioCaso) {
        this.diretorioCaso = diretorioCaso;
    }
}
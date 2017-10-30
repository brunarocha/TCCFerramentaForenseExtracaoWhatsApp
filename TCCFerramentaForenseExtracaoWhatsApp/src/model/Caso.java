/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author BRUNA
 */
public class Caso {
    
    private Long id;
    private Usuario usuario;
    private String descricao;
    private String nomeInvestigado;
    private Calendar dataCriacao;
    private String diretorio;
    private List<Dispositivo> dispositivos;
    private List<Usuario> usuariosAcesso;
    
    public Caso() {
    }

    public Caso(String descricao, String nomeInvestigado) {
        this.descricao = descricao;
        this.nomeInvestigado = nomeInvestigado;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeInvestigado() {
        return nomeInvestigado;
    }

    public void setNomeInvestigado(String nomeInvestigado) {
        this.nomeInvestigado = nomeInvestigado;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public List<Usuario> getUsuariosAcesso() {
        return usuariosAcesso;
    }

    public void setUsuariosAcesso(List<Usuario> usuariosAcesso) {
        this.usuariosAcesso = usuariosAcesso;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
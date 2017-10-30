/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author BRUNA
 */
public class Dispositivo  {
    
    private Long id;
    private String nome;
    private String fabricante;
    private String idioma;
    private String modelo;
    private String numeroVersao;
    private String numeroModelo;
    private String versaoAndroid;
    private String versaoSistema;
    private String diretorio;
    private String tabelaContato;
    private String tabelaMensagem;
    private Caso caso;
    
    public Dispositivo() {
    }

    public Dispositivo(Long id) {
        this.id = id;
    }
        
    public Dispositivo(String nome, String fabricante, String idioma, String modelo, String numeroVersao, String numeroModelo, String versaoAndroid, String versaoSistema, String diretorio, String tabelaContato, String tabelaMensagem) {
        this.nome = nome;
        this.fabricante = fabricante;
        this.idioma = idioma;
        this.modelo = modelo;
        this.numeroVersao = numeroVersao;
        this.numeroModelo = numeroModelo;
        this.versaoAndroid = versaoAndroid;
        this.versaoSistema = versaoSistema;
        this.diretorio = diretorio;
        this.tabelaContato = tabelaContato;
        this.tabelaMensagem = tabelaMensagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroVersao() {
        return numeroVersao;
    }

    public void setNumeroVersao(String numeroVersao) {
        this.numeroVersao = numeroVersao;
    }

    public String getNumeroModelo() {
        return numeroModelo;
    }

    public void setNumeroModelo(String numeroModelo) {
        this.numeroModelo = numeroModelo;
    }

    public String getVersaoAndroid() {
        return versaoAndroid;
    }

    public void setVersaoAndroid(String versaoAndroid) {
        this.versaoAndroid = versaoAndroid;
    }

    public String getVersaoSistema() {
        return versaoSistema;
    }

    public void setVersaoSistema(String versaoSistema) {
        this.versaoSistema = versaoSistema;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public String getTabelaContato() {
        return tabelaContato;
    }

    public void setTabelaContato(String tabelaContato) {
        this.tabelaContato = tabelaContato;
    }

    public String getTabelaMensagem() {
        return tabelaMensagem;
    }

    public void setTabelaMensagem(String tabelaMensagem) {
        this.tabelaMensagem = tabelaMensagem;
    }
    
    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    @Override
    public String toString() {
        return nome;
    }
}
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
public class Relatorio {
    
    private Long id;
    private String nome;
    private int mensagensEnviadas;
    private int mensagensRecebidas;

    public Relatorio() {
    }

    public Relatorio(Long id, String nome, int mensagensEnviadas, int mensagensRecebidas) {
        this.id = id;
        this.nome = nome;
        this.mensagensEnviadas = mensagensEnviadas;
        this.mensagensRecebidas = mensagensRecebidas;
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

    public int getMensagensEnviadas() {
        return mensagensEnviadas;
    }

    public void setMensagensEnviadas(int mensagensEnviadas) {
        this.mensagensEnviadas = mensagensEnviadas;
    }

    public int getMensagensRecebidas() {
        return mensagensRecebidas;
    }

    public void setMensagensRecebidas(int mensagensRecebidas) {
        this.mensagensRecebidas = mensagensRecebidas;
    }   
}
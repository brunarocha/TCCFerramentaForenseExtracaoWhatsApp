/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import util.ConversorData;

/**
 *
 * @author BRUNA
 */
public class Consulta {
    
    private String palavraChave;
    private Date dataInicial;
    private Date dataFinal;
    private Time horaInicial;
    private Time horaFinal;
    private Contato contato;
    private String tipo;
    private List<Contato> listaContatos;

    public Consulta() {
    }

    public Consulta(String palavraChave, Date dataInicial, Date dataFinal, Time horaInicial, Time horaFinal, Contato contato, String tipo, List<Contato> listaContatos) {
        this.palavraChave = palavraChave;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.contato = contato;
        this.tipo = tipo;
        this.listaContatos = listaContatos;
    }

    public String getPalavraChave() {
        return palavraChave;
    }

    public void setPalavraChave(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Time getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Time horaInicial) {
        this.horaInicial = horaInicial;
    }

    public Time getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Time horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Contato> getListaContatos() {
        return listaContatos;
    }

    public void setListaContatos(List<Contato> listaContatos) {
        this.listaContatos = listaContatos;
    }

    public  void carregaObjetoConsulta(String cPalavra, Object cDataInicial, Object cDataFinal, Object cHoraInicial, 
                                       Object cHoraFinal, boolean cCheckContatos, boolean cCheckGrupos, Contato cContato){
        if(cPalavra.isEmpty()){ this.palavraChave = null; }
        else{ this.palavraChave = cPalavra; }
        
        if(cDataInicial == null){ this.dataInicial = null; }
        else{ this.dataInicial = ConversorData.getDateDeString((String)cDataInicial); }
        
        if(cDataFinal == null){ this.dataFinal = null; }
        else{ this.dataFinal = ConversorData.getDateDeString((String)cDataFinal); }
        
        if(cHoraInicial == null){ this.horaInicial = null; }
        else{ this.horaInicial = ConversorData.getTimeDeString((String)cHoraInicial); }
        
        if(cHoraFinal == null){ this.horaFinal = null; }
        else{ this.horaFinal = ConversorData.getTimeDeString((String)cHoraFinal); }
       
        if(cCheckContatos && cCheckGrupos){
            this.tipo = "T";
            this.contato = null;
        }
        else if(cCheckContatos){
            this.tipo = "C";
            this.contato = null;
        }
        else if(cCheckGrupos){
            this.tipo = "G";
            this.contato = null;
        }     
        else {
            this.contato = cContato;
        }
    }   
    
    
    public boolean verificaPreenchimentoCampos(){
        boolean verifica = true;
        
        if( palavraChave == null && dataInicial == null && dataFinal == null && horaInicial == null && horaFinal == null){
            verifica = false;
        }
        return verifica;
    }

    @Override
    public String toString() {
        return "Consulta{" + "palavraChave=" + palavraChave + ", dataInicial=" + dataInicial + ", dataFinal=" + dataFinal + ", horaInicial=" + horaInicial + ", horaFinal=" + horaFinal + ", contato=" + contato + ", tipo=" + tipo + ", listaContatos=" + listaContatos + '}';
    }
    
}
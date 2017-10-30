/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Contato;
import util.ConexaoJDBC;

/**
 *
 * @author BRUNA
 */
public class ContatoDAO {
    
    private final Connection connection;
    private final String tabelaContato;
    
    public ContatoDAO(String tabelaContato) {
        this.connection = ConexaoJDBC.getConexaoJDBC();
        this.tabelaContato = tabelaContato;
    }
    
    
    public boolean insereContato(Contato contato, Long dispositivoID){
        try {
            String sql = "INSERT INTO "+tabelaContato+" (CTT_ID, CTT_JID, CTT_STATUS, CTT_STATUS_DATE, CTT_STATUS_TIME, CTT_NUMBER, CTT_DISPLAY_NAME, CTT_WA_NAME, CTT_DPS_ID) " +
                         " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, contato.getId());
            preparedStatement.setString(2, contato.getJid());
            preparedStatement.setString(3, contato.getStatus());
            preparedStatement.setDate(4, contato.getStatusDate());
            preparedStatement.setTime(5, contato.getStatusTime());
            preparedStatement.setString(6, contato.getNumber());
            preparedStatement.setString(7, contato.getDisplayName());
            preparedStatement.setString(8, contato.getWaName());
            preparedStatement.setLong(9, dispositivoID);
            preparedStatement.execute();
            
            return true;
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao importar contato! "+ex);
            return false;
        }
    }
    
    
    public boolean criaTabelaContato(){
        String sql = "CALL SP_CRIA_TABELA_CONTATO (?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, tabelaContato);
            preparedStatement.execute();
            
            return true;
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar tabela "+tabelaContato+"! "+ex);
            return false;
        }
    }
    
    
    // consulta todos os contatos
    public List<Contato> pesquisaTodosContatos(){
        String sql = "CALL SP_PESQUISACONTATOS_TODOS (?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, tabelaContato);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Contato> contatos = new ArrayList<>();
            
            while(resultSet.next()){
                contatos.add(carregaObjetoContato(resultSet, false));
            }
            return contatos;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar todos contatos! "+ex.getMessage());
            return null;
        }
    }
    
    
    // consulta contatos da agenda
    public List<Contato> pesquisaContatosAgenda(){
        String sql = "CALL SP_PESQUISACONTATOS_AGENDA (?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, tabelaContato);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Contato> contatos = new ArrayList<>();
            
            while(resultSet.next()){
                contatos.add(carregaObjetoContato(resultSet, false));
            }
            return contatos;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar contatos da agenda! "+ex.getMessage());
            return null;
        }
    }
    
    
    // consulta contatos não salvos
    public List<Contato> pesquisaContatosNaoSalvos(){
        String sql = "CALL SP_PESQUISACONTATOS_NAOSALVOS (?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, tabelaContato);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Contato> contatos = new ArrayList<>();
            
            while(resultSet.next()){
                contatos.add(carregaObjetoContato(resultSet, false));
            }
            return contatos;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar contatos não salvos! "+ex.getMessage());
            return null;
        }
    }
    
    
    // consulta grupos
    public List<Contato> pesquisaGrupos(){
        String sql = "CALL SP_PESQUISAGRUPOS (?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, tabelaContato);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Contato> contatos = new ArrayList<>();
            
            while(resultSet.next()){
                contatos.add(carregaObjetoContatoGrupo(resultSet));
            }
            return contatos;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar grupos! "+ex.getMessage());
            return null;
        }
    }
    
    
    public Contato pesquisaContatoPorJID(String jid){
        String sql = "CALL SP_PESQUISA_CONTATO_JID (?, ?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, tabelaContato);
            preparedStatement.setString(2, jid);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            
            return carregaObjetoContato(resultSet, true);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    
    private Contato carregaObjetoContato(ResultSet resultado, boolean filtro ) throws SQLException{
        Contato contato = new Contato(resultado.getString("CTT_JID"), 
                                      resultado.getString("CTT_STATUS"),
                                      resultado.getString("CTT_NUMBER"),
                                      resultado.getString("CTT_DISPLAY_NAME"));
        contato.setId(resultado.getLong("CTT_ID"));
        contato.setStatusDate(resultado.getDate("CTT_STATUS_DATE"));
        contato.setStatusTime(resultado.getTime("CTT_STATUS_TIME"));
        
        if(filtro){
            contato.setWa_name(resultado.getString("CTT_WA_NAME"));
        }
        
        /*if(resultado.getDate("CTT_STATUS_DATE") == null){
            contato.setStatus_date(null);
            contato.setStatus_time(null);
        }
        else{
            contato.setStatus_date(resultado.getDate("CTT_STATUS_DATE"));
            contato.setStatus_time(resultado.getTime("CTT_STATUS_TIME"));
        }*/
        
        return contato;
    }
    
    
    
    private Contato carregaObjetoContatoGrupo(ResultSet resultado) throws SQLException{
        Contato contato = new Contato(resultado.getString("CTT_JID"),
                                      null,
                                      resultado.getString("CTT_NUMBER"),
                                      resultado.getString("CTT_DISPLAY_NAME"));
        contato.setId(resultado.getLong("CTT_ID"));
        
        return contato;
    }
}

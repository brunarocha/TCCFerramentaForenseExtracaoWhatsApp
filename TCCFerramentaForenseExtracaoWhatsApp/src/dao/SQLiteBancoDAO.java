/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Contato;
import model.Mensagem;
import util.ConexaoSQLite;
import util.ConversorData;

/**
 *
 * @author BRUNA
 */
public class SQLiteBancoDAO {
    private final Connection connection;
    private Statement statement;
    

    public SQLiteBancoDAO(String urlBanco) {
        this.connection = ConexaoSQLite.getConexaoSQLite(urlBanco);
        try {
            this.statement = connection.createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao criar statement! "+ex);
        }
    }
    
    
    
    // carrega lista de contatos do banco WA.DB
    public List<Contato> carregaTabelaContatoSQLite(){
        String sql = " SELECT _id, jid, status, status_timestamp, number, display_name, wa_name "
                    +" FROM wa_contacts "
                    +" WHERE is_whatsapp_user = 1 and jid != 'status@broadcast'; ";
        
        try {
            ResultSet resultado = statement.executeQuery(sql);
            List<Contato> contatos = new ArrayList<>();
            
            while(resultado.next()){
                contatos.add(carregaObjetoContato(resultado));
            }
            
            return contatos;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar tabela contato do banco WA.DB! "+ex);
            return null;
        }
    }
    
    
    // carrega lista de mensagens do banco MSGSTORE.DB
    public List<Mensagem> carregaTabelaMensagensSQLite(){
        String sql = " SELECT _id, key_remote_jid, key_from_me, data, timestamp, media_url, media_mime_type, "
                    +" media_size, media_name, media_caption, remote_resource "
                    +" FROM messages "
                    +" WHERE key_remote_jid != -1 AND key_remote_jid != 'status@broadcast' "
                    +" AND ( (data is not null and media_url is not null) "
                    +"       or (data is null and media_url is not null) " 
                    +"       or (data is not null and media_url is null) " 
                    +"     ) " 
                    +" ORDER BY timestamp ASC ";
                
        try {
            ResultSet resultado = statement.executeQuery(sql);
            
            List<Mensagem> mensagens = new ArrayList<>();
            
            while(resultado.next()){
                mensagens.add(carregaObjetoMensagem(resultado));
            }
            
            return mensagens;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar tabela mensagem do banco MSGSTORE.DB! "+ex);
            return null;
        }
    }
    
    
    
    private Contato carregaObjetoContato(ResultSet resultado) throws SQLException{
        Contato contato = new Contato(resultado.getString("jid"),
                                      resultado.getString("status"),
                                      resultado.getString("number"),
                                      resultado.getString("display_name"));
        
        contato.setId(resultado.getLong("_id"));
        contato.setWa_name(resultado.getString("wa_name"));
        
        if(resultado.getTimestamp("status_timestamp").getTime() == 0){
            contato.setStatusDate(null);
            contato.setStatusTime(null);
        }
        else{
            contato.setStatusDate(ConversorData.getDateDeTimestamp(resultado.getTimestamp("status_timestamp")));
            contato.setStatusTime(ConversorData.getTimeDeTimestamp(resultado.getTimestamp("status_timestamp")));
        }
        
        return contato;
    }
    
    
    private Mensagem carregaObjetoMensagem(ResultSet resultado) throws SQLException{
        Mensagem mensagem = new Mensagem(new Contato(resultado.getString("key_remote_jid")),
                                         resultado.getString("remote_resource"),
                                         resultado.getBoolean("key_from_me"),
                                         resultado.getString("data"), 
                                         null,
                                         null,
                                         resultado.getString("media_url"),
                                         resultado.getString("media_mime_type"),
                                         resultado.getInt("media_size"),
                                         resultado.getString("media_name"),
                                         resultado.getString("media_caption"));
        mensagem.setId(resultado.getLong("_id"));
        
        if(resultado.getTimestamp("timestamp").getTime() == 0){
            mensagem.setDate(null);
            mensagem.setTime(null);
        }
        else{
            mensagem.setDate(ConversorData.getDateDeTimestamp(resultado.getTimestamp("timestamp")));
            mensagem.setTime(ConversorData.getTimeDeTimestamp(resultado.getTimestamp("timestamp")));
        }
        
        return mensagem;
    }
    
}

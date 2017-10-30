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
import model.Consulta;
import model.Contato;
import model.Mensagem;
import model.RankingMensagem;
import util.ConexaoJDBC;

/**
 *
 * @author BRUNA
 */
public class MensagemDAO {
    
    private final Connection connection;
    private final String tabelaMensagem, tabelaContato;

    public MensagemDAO(String tabelaMensagem, String tabelaContato) {
        this.connection = ConexaoJDBC.getConexaoJDBC();
        this.tabelaMensagem = tabelaMensagem;
        this.tabelaContato = tabelaContato;
    }

    
    public boolean insereMensagem(Mensagem mensagem, Long dispositivoID){
        String sql = " INSERT INTO "+tabelaMensagem+" (MEN_ID, MEN_DPS_ID, MEN_KEY_REMOTE_JID, " +
                     " MEN_REMOTE_RESOURCE, MEN_KEY_FROM_ME, MEN_DATA, MEN_DATE, MEN_TIME, MEN_MEDIA_URL, " +
                     " MEN_MEDIA_MIME_TYPE, MEN_MEDIA_SIZE, MEN_MEDIA_NAME, MEN_MEDIA_CAPTION ) " +
                     " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ContatoDAO contatoDAO = new ContatoDAO(tabelaContato);
        Contato contato = contatoDAO.pesquisaContatoPorJID(mensagem.getKeyRemoteJid().getJid());
        
        try {           
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, mensagem.getId());
            preparedStatement.setLong(2, dispositivoID);
            preparedStatement.setLong(3, contato.getId());
            preparedStatement.setString(4, mensagem.getRemoteResource());
            preparedStatement.setBoolean(5, mensagem.isKeyFromMe());
            preparedStatement.setString(6, mensagem.getData());
            preparedStatement.setDate(7, mensagem.getDate());
            preparedStatement.setTime(8, mensagem.getTime());
            preparedStatement.setString(9, mensagem.getMediaUrl());
            preparedStatement.setString(10, mensagem.getMediaMimeType());
            preparedStatement.setInt(11, mensagem.getMediaSize());
            preparedStatement.setString(12, mensagem.getMediaName());
            preparedStatement.setString(13, mensagem.getMediaCaption());
            preparedStatement.execute();
            
            return true;
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao importar mensagem! "+ex);
            return false;
        }
    }
    
    
    public List<Mensagem> filtraConsultaContato(Consulta consulta){
        String sql = "CALL SP_FILTRA_CONSULTAS_CONTATO(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, tabelaMensagem);
            preparedStatement.setLong(2, consulta.getContato().getId());
            preparedStatement.setDate(3, consulta.getDataInicial());
            preparedStatement.setDate(4, consulta.getDataFinal());
            preparedStatement.setTime(5, consulta.getHoraInicial());
            preparedStatement.setTime(6, consulta.getHoraFinal());
            preparedStatement.setString(7, consulta.getPalavraChave());
        
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Mensagem> mensagens = new ArrayList<>();
            
            while(resultSet.next()){
                mensagens.add(carregaObjetoMensagem(resultSet));
            }
            return mensagens;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao realizar consulta por filtros de contato!" +ex.getMessage());
            return null;
        }
    }
    
    
    public List<Contato> filtraConsultaTodos(Consulta consulta){
        String sql = "CALL SP_FILTRA_CONSULTAS_TODOS(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, tabelaMensagem);
            preparedStatement.setString(2, tabelaContato);
            preparedStatement.setDate(3, consulta.getDataInicial());
            preparedStatement.setDate(4, consulta.getDataFinal());
            preparedStatement.setTime(5, consulta.getHoraInicial());
            preparedStatement.setTime(6, consulta.getHoraFinal());
            preparedStatement.setString(7, consulta.getPalavraChave());
            preparedStatement.setString(8, consulta.getTipo());
        
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Contato> contatos = new ArrayList<>();
            Contato contato;
            
            while(resultSet.next()){
                contato = new Contato(resultSet.getString("ctt_jid"),
                                      null,
                                      null,
                                      resultSet.getString("ctt_display_name"));
                contato.setId(resultSet.getLong("ctt_id"));
                contato.setWa_name(resultSet.getString("ctt_wa_name"));
        
                contatos.add(contato);
            }
            return contatos;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar consulta por filtros!" +ex.getMessage());
            return null;
        }
    }
    
    
    public List<Mensagem> pesquisaMensagensPorContato(Contato contato){
        String sql = "CALL SP_PESQUISAMENSAGENS_CONTATO(?, ?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, tabelaMensagem);
            preparedStatement.setLong(2, contato.getId());
        
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Mensagem> mensagens = new ArrayList<>();
                    
            while(resultSet.next()){
                mensagens.add(carregaObjetoMensagem(resultSet));
            }
            return mensagens;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar mensagens do contato : "+contato+"! "+ex);
            return null;
        }
    }
    
    
    public List<RankingMensagem> pesquisaRankingMensagens(Consulta consulta){
        String sql = "CALL SP_PESQUISA_RANKING_MENSAGEM(?, ?, ?, ?, ?) ";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, tabelaMensagem);
            preparedStatement.setString(2, tabelaContato);
            preparedStatement.setDate(3, consulta.getDataInicial());
            preparedStatement.setDate(4, consulta.getDataFinal());
            preparedStatement.setString(5, consulta.getTipo());
        
            ResultSet resultSet = preparedStatement.executeQuery();
            List<RankingMensagem> rankingMensagens = new ArrayList<>();
            
            RankingMensagem rankingMensagem;
            while(resultSet.next()){
                rankingMensagem = new RankingMensagem();
                
                rankingMensagem.setTotalMensagem(resultSet.getInt("COUNT(*)"));
                
                if(resultSet.getString("ctt_display_name") != null){
                    rankingMensagem.setNome(resultSet.getString("ctt_display_name"));
                }
                else if(resultSet.getString("ctt_wa_name") != null){
                    rankingMensagem.setNome(resultSet.getString("ctt_wa_name"));
                }
                else{
                    rankingMensagem.setNome(resultSet.getString("ctt_jid"));
                }
                
                rankingMensagens.add(rankingMensagem);
            }
            return rankingMensagens;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar ranking de mensagens!" +ex.getMessage());
            return null;
        }
    }
    
    
    private Mensagem carregaObjetoMensagem(ResultSet resultSet) throws SQLException{
        Mensagem mensagem = new Mensagem(new Contato("men_key_remote_jid"),
                                         resultSet.getString("men_remote_resource"),
                                         resultSet.getBoolean("men_key_from_me"),
                                         resultSet.getString("men_data"),
                                         resultSet.getDate("men_date"), 
                                         resultSet.getTime("men_time"), 
                                         resultSet.getString("men_media_url"),
                                         resultSet.getString("men_media_mime_type"),
                                         resultSet.getInt("men_media_size"),
                                         resultSet.getString("men_media_name"),
                                         resultSet.getString("men_media_caption"));
        mensagem.setId(resultSet.getLong("men_id"));
       
        return mensagem;
    }

}
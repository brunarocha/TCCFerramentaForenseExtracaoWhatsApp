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
import model.Caso;
import model.Dispositivo;
import util.ConexaoJDBC;

/**
 *
 * @author BRUNA
 */
public class DispositivoDAO {
    
    private final Connection connection;
    
    public DispositivoDAO() {
        this.connection = ConexaoJDBC.getConexaoJDBC();
    }
    
    
    public boolean inserir(Dispositivo dispositivo){
        String sql = "CALL SP_INSERE_DISPOSITIVO (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, dispositivo.getCaso().getId());
            preparedStatement.setString(2, dispositivo.getNome());
            preparedStatement.setString(3, dispositivo.getFabricante());
            preparedStatement.setString(4, dispositivo.getIdioma());
            preparedStatement.setString(5, dispositivo.getModelo());
            preparedStatement.setString(6, dispositivo.getNumeroVersao());
            preparedStatement.setString(7, dispositivo.getNumeroModelo());
            preparedStatement.setString(8, dispositivo.getVersaoAndroid());
            preparedStatement.setString(9, dispositivo.getVersaoSistema());
            preparedStatement.setString(10, dispositivo.getDiretorio());
            preparedStatement.setString(11, dispositivo.getTabelaContato());
            preparedStatement.setString(12, dispositivo.getTabelaMensagem());
            preparedStatement.execute();
            
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir novo dispositivo! "+ex.getMessage());
            return false;
        }
    }
    
    
    public boolean excluir(Dispositivo dispositivo){
        String sql = "CALL SP_EXCLUI_DISPOSITIVO (?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, dispositivo.getId());
            preparedStatement.execute();
            
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir dispositivo! "+ex.getMessage());
            return false;
        }
    }
    
    
    public Dispositivo pesquisaDispositivoPorNome(String nome, Caso caso){
        String sql = "CALL SP_PESQUISA_DISPOSITIVO_NOME (?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            
            return carregaObjetoDispositivo(resultSet, caso);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar dispositivo "+nome+"! "+ex.getMessage());
            return null;
        }
    }
    
    
    public List<Dispositivo> dispositivosPorCaso(Caso caso){
        String sql = "CALL SP_LISTA_DISPOSITIVOS_CASO (?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, caso.getId());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Dispositivo> dispositivos = new ArrayList<>();
            
            while(resultSet.next()){
                dispositivos.add(carregaObjetoDispositivo(resultSet, caso));
            }
            
            return dispositivos;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar dispositivos do caso: "+caso.getDescricao()+ "! "+ex.getMessage());
            return null;
        }
    }
    
    
    public int verificaImportacaoDispositivo(String tabelaDispositivo){
        String sql = "CALL SP_VERIFICA_IMPORTACAO_DISPOSITIVO (?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, tabelaDispositivo);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            
            return resultSet.getInt("COUNT(*)");
            
        } catch (SQLException ex) {
            return 0;
        }
    }
    
    
    private Dispositivo carregaObjetoDispositivo(ResultSet resultado, Caso caso) throws SQLException{
        Dispositivo dispositivo = new Dispositivo(resultado.getString("dps_nome"),
                                                  resultado.getString("dps_fabricante"),
                                                  resultado.getString("dps_idioma"),
                                                  resultado.getString("dps_modelo"),
                                                  resultado.getString("dps_numero_versao"),
                                                  resultado.getString("dps_numero_modelo"),
                                                  resultado.getString("dps_versao_android"),
                                                  resultado.getString("dps_versao_sistema"),
                                                  resultado.getString("dps_diretorio"),
                                                  resultado.getString("dps_tabela_contato"),
                                                  resultado.getString("dps_tabela_mensagem"));
        dispositivo.setId(resultado.getLong("dps_id"));
        dispositivo.setCaso(caso);
        
        return dispositivo;
    }
    
}
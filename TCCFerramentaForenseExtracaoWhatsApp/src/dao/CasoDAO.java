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
import model.TipoAcesso;
import model.Usuario;
import util.ConexaoJDBC;
import util.ConversorData;

/**
 *
 * @author BRUNA
 */
public class CasoDAO {
    
    private final Connection connection;
        
    public CasoDAO() {
        this.connection = ConexaoJDBC.getConexaoJDBC();
    }
        
    public boolean inserir(Caso caso){
        String sql = "CALL SP_INSERE_CASO (?, ?, ?) ";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, caso.getDescricao());
            preparedStatement.setString(2, caso.getNomeInvestigado());
            preparedStatement.setString(3, caso.getDiretorio());
            preparedStatement.execute();
            
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir novo caso! "+ex.getMessage());
            return false;
        }
    }
    
    
    public boolean excluir(Caso caso){
        String sql = "CALL SP_EXCLUI_CASO (?) ";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, caso.getId());
            preparedStatement.execute();
            
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir caso! "+ex.getMessage());
            return false;
        }
    }
    
        
    public boolean relacionaCasoUsuario(TipoAcesso tipoAcesso, Long idUsuario, Long idCaso){
        String sql = "CALL SP_INSERE_CASO_USUARIO (?, ?, ?) ";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, tipoAcesso.toString());
            preparedStatement.setLong(2, idUsuario);
            preparedStatement.setLong(3, idCaso);
            preparedStatement.execute();
            
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao relacionar caso com usuário! "+ex.getMessage());
            return false;
        }
    }
    
    
    public List<Caso> pesquisaListaCasos(Usuario usuario, TipoAcesso tipoAcesso){
        String sql = "CALL SP_LISTA_CASOS (?, ?) ";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, usuario.getId());
            preparedStatement.setString(2, tipoAcesso.toString());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Caso> casos = new ArrayList<>();
            
            while(resultSet.next()){
                casos.add(carregaObjetoCaso(resultSet, usuario));
            }
           
            return casos;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar casos do usuário: "+usuario.getNome()+"! "+ex.getMessage());
            return null;
        }
    }
    
    
    public List<Caso> pesquisaListaCasosPorNome(Usuario usuario, TipoAcesso tipoAcesso, String nomeCaso){
        String sql = "CALL SP_LISTA_CASOS_NOME (?, ?, ?) ";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, usuario.getId());
            preparedStatement.setString(2, tipoAcesso.toString());
            preparedStatement.setString(3, nomeCaso);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Caso> casos = new ArrayList<>();
            
            while(resultSet.next()){
                casos.add(carregaObjetoCaso(resultSet, usuario));
            }
           
            return casos;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar casos do usuário: "+usuario.getNome()+"! "+ex.getMessage());
            return null;
        }
    }
    
    
    public boolean verificaNomeCaso(String descricao){
        String sql = "CALL SP_VERIFICA_NOME_CASO(?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, descricao);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            
            return resultSet.getLong(1) > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao verificar nome do caso: "+descricao+"! "+ex.getMessage());
            return false;
        }
    }
    
    
    private Caso carregaObjetoCaso(ResultSet resultSet, Usuario usuario) throws SQLException{
        Caso caso = new Caso();
        caso.setId(resultSet.getLong("cas_id"));
        caso.setUsuario(usuario);
        caso.setDescricao(resultSet.getString("cas_descricao"));
        caso.setDataCriacao(ConversorData.getCalendarDeDate(resultSet.getDate("cas_data_criacao")));
        caso.setNomeInvestigado(resultSet.getString("cas_nome_investigado"));
        caso.setDiretorio(resultSet.getString("cas_diretorio"));
        
        return caso;
    }
    
}
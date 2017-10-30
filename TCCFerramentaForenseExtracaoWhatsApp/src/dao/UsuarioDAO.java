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
import model.Usuario;
import util.ConexaoJDBC;

/**
 *
 * @author BRUNA
 */
public class UsuarioDAO {
    private final Connection connection;
    
    public UsuarioDAO() {
        this.connection = ConexaoJDBC.getConexaoJDBC();
    }
    
    
    public boolean inserir(Usuario usuario){
        String sql = "CALL SP_INSERE_USUARIO (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getLogin());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getNome());
            preparedStatement.setString(4, usuario.getDiretorioCasos());
            preparedStatement.execute();
            
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir novo usuário! "+ex.getMessage());
            return false;
        }
    }
    
    
    public boolean alterar(Usuario usuario){
        String sql = "CALL SP_ALTERA_USUARIO (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, usuario.getId());
            preparedStatement.setString(2, usuario.getLogin());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setString(4, usuario.getNome());
            preparedStatement.execute();
            
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar dados do usuário! "+ex.getMessage());
            return false;
        }
    }
    
    
    // VALIDA LOGIN DO USUÁRIO
    public Usuario verificaUsuario(Usuario usuario){
        String consulta = "CALL SP_VERIFICA_USUARIO (?, ?) ";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(consulta);
            preparedStatement.setString(1, usuario.getLogin());
            preparedStatement.setString(2, usuario.getSenha());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            
            Usuario usuarioResultado = carregaObjetoUsuario(resultSet);
            
            return usuarioResultado;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Usuário e/ou senha incorretos! ");
            return null;
        }
    }
    
    
    // VALIDA LOGIN EXISTENTE
    public boolean verificaLoginExistente(String login){
        String consulta = "CALL SP_VERIFICA_LOGIN_EXISTENTE (?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(consulta);
            preparedStatement.setString(1, login);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            
            int resultado = resultSet.getInt("COUNT(*)");
            return resultado != 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao verificar login! ");
            return false;
        }
    }
    
    
    // LISTA USUÁRIOS
    public List<Usuario> pesquisaUsuarios(Long idUsuarioLogado){
        String sql = "CALL SP_LISTA_USUARIOS (?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, idUsuarioLogado);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            List<Usuario> usuarios = new ArrayList<>();
            
            while(resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getLong("usu_id"));
                usuario.setNome(resultSet.getString("usu_nome"));
                usuario.setLogin(resultSet.getString("usu_login"));
                usuarios.add(usuario);
            }
            
            return usuarios;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar usuários! ");
            return null;
        }
    }
    
    // lista usuarios com acesso a um caso, exceto o criador
    public List<Usuario> pesquisaUsuariosAcesso(Long idUsuarioLogado, Long idCaso){
        String sql = "CALL SP_LISTA_USUARIOS_ACESSO (?, ?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setLong(1, idUsuarioLogado);
            preparedStatement.setLong(2, idCaso);
             
            ResultSet resultSet = preparedStatement.executeQuery();
            
            List<Usuario> usuarios = new ArrayList<>();
            
            while(resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getLong("usu_id"));
                usuario.setNome(resultSet.getString("usu_nome"));
                usuarios.add(usuario);
            }
            
            return usuarios;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar usuários! "+ex);
            return null;
        }
    }
    
    
    private Usuario carregaObjetoUsuario(ResultSet resultSet) throws SQLException{
        Usuario usuarioResultado = new Usuario(resultSet.getString("usu_login"),
                                               resultSet.getString("usu_senha"),
                                               resultSet.getString("usu_nome"),
                                               resultSet.getString("usu_diretorio_casos"));
        usuarioResultado.setId(resultSet.getLong("usu_id"));
        
        return usuarioResultado;
    }
    
}
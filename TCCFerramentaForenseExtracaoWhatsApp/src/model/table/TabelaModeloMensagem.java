/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.table;

//import dao.ContatoDAO;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Dispositivo;
import model.Mensagem;
import util.ConversorData;

/**
 *
 * @author Bruna
 */
public class TabelaModeloMensagem  extends AbstractTableModel{
    private final List<Mensagem> mensagens;
    private final Dispositivo dispositivo;
    private final String colunas[] = {"Recebidas", "Enviadas"};

    public TabelaModeloMensagem(List<Mensagem> mensagens, Dispositivo dispositivo) {
        this.mensagens = mensagens;
        this.dispositivo = dispositivo;
    }
        
    @Override
    public int getRowCount() {
        return this.mensagens.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return colunas[columnIndex];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mensagem mensagem = this.mensagens.get(rowIndex);
        //ContatoDAO contatoDAO = new ContatoDAO(this.dispositivo.getTabelaContato());
        
        if(mensagem.isKeyFromMe()){
            if(mensagem.getRemoteResource() == null){
                switch(columnIndex){
                    case 0 : return ConversorData.getStringDeDate(mensagem.getDate())+" "+ConversorData.getStringDeTime(mensagem.getTime())+"\n";
                    case 1 : return mensagem.getData();
                }
            }
            else{
                switch(columnIndex){
                    case 0 : return ConversorData.getStringDeDate(mensagem.getDate())+" "+ConversorData.getStringDeTime(mensagem.getTime())+"\n"+
                                    mensagem.getRemoteResource(); //+"\n"+
                                    //contatoDAO.pesquisaContato(mensagem.getRemote_resource()).getWa_name();
                    case 1 : return mensagem.getData();
                }
            }
        }
        
        else{
            if(mensagem.getRemoteResource() == null){
                switch(columnIndex){
                    case 0 : return mensagem.getData();
                    case 1 : return ConversorData.getStringDeDate(mensagem.getDate())+" "+ConversorData.getStringDeTime(mensagem.getTime());
                }
            }
            else{
                switch(columnIndex){
                    case 0 : return mensagem.getData();
                    case 1 : return ConversorData.getStringDeDate(mensagem.getDate())+" "+ConversorData.getStringDeTime(mensagem.getTime())+"\n"+
                                    mensagem.getRemoteResource();//+"\n"+
                                    //contatoDAO.pesquisaContato(mensagem.getRemote_resource()).getWa_name();
                }
            }
        }
        
        return null;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeprodutoalternativo.factory;

import cadastrodeprodutoalternativo.modelo.Cliente;
import cadastrodeprodutoalternativo.modelo.Filiado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alunos
 */
public class Dao {
    protected Connection conexao;
    protected PreparedStatement stmt;
    protected Cliente cliente;
    protected Filiado filiado;
    protected String sql = "";
    protected ResultSet res;
    protected boolean debug = true;
    protected static final String DATABASE_NAME = "locadora";
    
    
    public Dao(){
        conexao =  Conexao.getConexao();
        openOrCreateDatabase();
    }
    
    private void openOrCreateDatabase(){
        try {
            stmt =  conexao.prepareStatement("CREATE DATABASE IF NOT EXISTS "+DATABASE_NAME); //se o banco não existir, criar 
            stmt.execute();//executar o query
            
            stmt = conexao.prepareStatement("USE "+DATABASE_NAME); //usar o bando "loja"
            stmt.execute();//executar o query
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeprodutoalternativo.factory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Alunos
 */
public class Conexao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/"; //diz qul o caminho usado para conexão do banco, com qual banco é utilizado(jdbc Mysql)
    private static final String USER = "root"; //diz qual é o USUÁRIO necessário para conecatar no banco
    private static final String PASS = ""; //diz qual é a SENHA para acessar o banco;
    private static Connection conexao = null; //?
    
    public static Connection getConexao(){
        if (conexao==null) {
            try {
                conexao = DriverManager.getConnection(URL, USER, PASS); //faz conexão usando o caminho com ip do banco, nome de usuário e senha
                System.out.println("Conectou...");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return conexao;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeprodutoalternativo.model.dao;

import cadastrodeprodutoalternativo.factory.Dao;
import cadastrodeprodutoalternativo.interfaces.DaoI;
import cadastrodeprodutoalternativo.modelo.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alunos
 */
public class ClienteDao extends Dao implements DaoI<Cliente> { //adicionar as funções dentro do banco de dados, com querys

    @Override
    public boolean salvar(Cliente obj) {
        sql = "INSERT INTO cliente (nome,dataNascimento,cpf) VALUES(?,?,?)"; // insere os dados recebidos pela interface no banco, nome, data nascimento e cpf
        try {
            stmt = conexao.prepareStatement(this.sql);
            stmt.setString(1, obj.getNome());
            stmt.setDate(2, obj.getDtNasc());
            stmt.setString(3, obj.getCpf());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean atualizar(Cliente obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE cliente SET nome=?, dataNascimento=?, cpf=? WHERE id=?"); //faz update de dados no banco
            
            stmt.setString(1, obj.getNome());
            stmt.setDate(2, obj.getDtNasc());
            stmt.setString(3, obj.getCpf());
            stmt.setInt(4, obj.getId());
            
            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean excluir(Cliente obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE cliente SET status = 0 WHERE id = ?");
            
//            stmt = conexao.prepareStatement("DELETE FROM cliente WHERE id = ?");

            stmt.setInt(1, obj.getId());
            return (stmt.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Cliente> listar() {
        try {
            this.sql = "SELECT id, nome, dataNascimento, cpf FROM cliente WHERE status = 1 ORDER BY id ASC";
            stmt = conexao.prepareStatement(sql);
            res = stmt.executeQuery();
            List<Cliente> lista = new ArrayList<>();
            while (res.next()) {
                cliente = new Cliente();
                cliente.setId(res.getInt("id")); 
                cliente.setNome(res.getString("nome")); 
                cliente.setDtNasc(res.getDate("dataNascimento"));
                cliente.setCpf(res.getString("cpf"));
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public Cliente lerPorId(int id) {
        try {
            stmt = conexao.prepareStatement("SELECT id, nome, dataNascimento, cpf FROM cliente"); //seleciona o cliente pelo ID
            stmt.setInt(1, id);//seleciona o id pelo Id passado por parametro
            res = stmt.executeQuery();//executa o comando
            if (res.next()) { //usa o IF pq nao existe mais de um cliente com o mesmo id(se fosse por nome, teria mais de um cliente com mesmo nome, usaria o WHILE pra criar loop)
                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setDtNasc(res.getDate("dtNasc"));
                cliente.setCpf(res.getString("cpf"));
            }
            return cliente;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Cliente> pesquisarPorNome(String nome) {
        try {
            stmt = conexao.prepareStatement("SELECT id, nome, dataNascimento, cpf FROM cliente WHERE nome LIKE?");//seleciona o cliente com o nome que bate com o passado por parametro
            stmt.setString(1, nome+"%");//procura pelo nome do cliente(o % no final, diz que pode ter mais alguma coisa depois, antes, somente o nome)
            res = stmt.executeQuery();
            List<Cliente> lista = new ArrayList<>();
            while (res.next()) {//cria o loop, enquanto tiver mais clientes com o mesmo nome, ele adiciona na lista;
                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setDtNasc(res.getDate("dataNascimento"));
                cliente.setCpf(res.getString("cpf"));
                lista.add(cliente);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Cliente> listarTodos(){
        List<Cliente> lista = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement("SELECT * FROM cliente");
            res = stmt.executeQuery();
            while (res.next()) {
                cliente.setId(res.getInt("id"));
                cliente.setNome(res.getString("nome"));
                cliente.setDtNasc(res.getDate("dataNascimento"));
                cliente.setCpf(res.getString("cpf"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar todos os clientes "+ e.getMessage());
        }
        return lista;
    }
    }

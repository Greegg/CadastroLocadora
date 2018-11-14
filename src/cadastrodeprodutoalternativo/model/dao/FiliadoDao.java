/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeprodutoalternativo.model.dao;

import cadastrodeprodutoalternativo.factory.Dao;
import cadastrodeprodutoalternativo.interfaces.DaoI;
import cadastrodeprodutoalternativo.modelo.Cliente;
import cadastrodeprodutoalternativo.modelo.Filiado;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alunos
 */
public class FiliadoDao extends Dao{
    
    
    public boolean salvar(Filiado obj) {
        
        sql = "INSERT INTO filiado (nome, telefone, cliente_id) VALUES(?,?,?)"; // insere os dados recebidos pela interface no banco, nome, data nascimento e cpf
        try {
            stmt = conexao.prepareStatement(this.sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getTelefone());
            stmt.setInt(3, obj.getCliente().getId());
            
            
            return stmt.executeUpdate() > 0;
            
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            return false;
        }
    }

    
    public boolean atualizar(Filiado obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE filiado SET nome=?, telefone=? WHERE id=?"); //faz update de dados no banco
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getTelefone());
            stmt.setInt(3, obj.getId());
            
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

//    @Override
    public boolean excluir(Filiado obj) {
        try {
            stmt = conexao.prepareStatement("DELETE FROM filiado WHERE id = ?");
            stmt.setInt(1, obj.getId());
            return (stmt.executeUpdate() > 0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    
    public List<Filiado> listar(int id) {
        try {
            this.sql = "SELECT id, nome, telefone FROM filiado where cliente_id = ? ORDER BY id ASC";
            stmt = conexao.prepareStatement(this.sql);
            stmt.setInt(1, id);

            res = stmt.executeQuery();
            
            List<Filiado> lista = new ArrayList<>();
            while (res.next()) {
                filiado = new Filiado();
                filiado.setId(res.getInt("id")); 
                filiado.setNome(res.getString("nome")); 
                filiado.setTelefone(res.getString("telefone"));
                lista.add(filiado);
            }
            
            
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

//    @Override
    public Filiado lerPorId(int id) {
        try {
            stmt = conexao.prepareStatement("SELECT id, telefone FROM filiado where id = ?");
            stmt.setInt(1, id);
            res = stmt.executeQuery();
            if (res.next()) {
                filiado.setId(res.getInt("id"));
                filiado.setNome(res.getString("nome"));
                filiado.setTelefone(res.getString("telefone"));
            }
            return filiado;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

//    @Override
    public List<Filiado> pesquisarPorNome(String nome) {
        try {
            stmt = conexao.prepareStatement("SELECT id, telefone FROM filiado WHERE nome LIKE?");
            stmt.setString(1, nome+"%");
            res = stmt.executeQuery();
            List<Filiado> lista = new ArrayList<>();
            while (res.next()) {
                filiado.setId(res.getInt("id"));
                filiado.setNome(res.getString("nome"));
                filiado.setTelefone(res.getString("telefone"));
                lista.add(filiado);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

//    @Override
    public List<Filiado> listarTodos() {
        List<Filiado> lista = new ArrayList<>();
        try {
            stmt = conexao.prepareStatement("SELECT * FROM filiado");
            res = stmt.executeQuery();
            while (res.next()) {
                filiado.setId(res.getInt("id"));
                filiado.setNome(res.getString("nome"));
                filiado.setTelefone(res.getString("telefone"));
                lista.add(filiado);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar todos os filiado "+ e.getMessage());
        }
        return lista;
    }
    
}

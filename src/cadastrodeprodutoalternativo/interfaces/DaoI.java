/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeprodutoalternativo.interfaces;

import java.util.List;

/**
 *
 * @author Alunos
 */
public interface DaoI<T> {
    
    public boolean salvar(T obj); //cria a base DAO
    public boolean atualizar(T obj); //cria a base DAO
    public boolean excluir (T obj); //cria a base DAO
    public List<T> listar(); //cria a base DAO
    public T lerPorId(int id); //cria a base DAO
    public List<T> pesquisarPorNome(String nome); //cria a base DAO
    public List<T> listarTodos();
    
}

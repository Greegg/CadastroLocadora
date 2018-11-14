/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeprodutoalternativo.modelo;

/**
 *
 * @author Alunos
 */
public class Filiado {
    
    private int id;
    private String nome;
    private String telefone;
    private Cliente cliente;
    

    public Filiado() {
    }

    public Filiado(int id, String nome, String telefone, Cliente cliente) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cliente=cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeprodutoalternativo.modelo;

import java.sql.Date;

/**
 *
 * @author Alunos
 */
public class Cliente {
    
    private int id;
    private String nome;
    private Date dtNasc;
    private String cpf;

    public Cliente() {
    }

    public Cliente(int id, String nome, Date dtNasc, String cpf) {
        this.id = id;
        this.nome = nome;
        this.dtNasc = dtNasc;
        this.cpf = cpf;
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

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
    

   

       
    
}

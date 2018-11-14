/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeprodutoalternativo.control;

import cadastrodeprodutoalternativo.model.dao.FiliadoDao;
import cadastrodeprodutoalternativo.modelo.Cliente;
import cadastrodeprodutoalternativo.modelo.Filiado;
import cadastrodeprodutoalternativo.uteis.Conversor;
import cadastrodeprodutoalternativo.uteis.Painel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alunos
 */
public class FiliadoControl {

    private JTextField nomeFiliado;
    private JTextField telefone;
    private JTable tbFiliado;

    private Filiado filiado = null;
    private List<Filiado> listaFiliado;
    private FiliadoDao filiadoDao;

    private Cliente cliente;

    private String msg;

    public FiliadoControl() {
    }

    public FiliadoControl(JTextField nomeFiliado, JTextField telefone, JTable tbFiliado) {

        this.nomeFiliado = nomeFiliado;
        this.telefone = telefone;
        this.tbFiliado = tbFiliado;

        filiadoDao = new FiliadoDao();
        listaFiliado = new ArrayList<>();
        cliente = new Cliente();
    }

    public void listarAction() {
        listaFiliado = filiadoDao.listar(cliente.getId());
        showItensTable();
    }

    private void showItensTable() {

        DefaultTableModel model;
        model = (DefaultTableModel) tbFiliado.getModel();
        model.setNumRows(0);

        for (Filiado filiados : listaFiliado) {
            model.addRow(new Object[]{
                filiados.getId(),
                filiados.getNome(),
                filiados.getTelefone()
            });
        }

    }

    public boolean ValidacaoCliente() {

        boolean ok = false;
        if (nomeFiliado.getText().isEmpty()) {
            ok = true;
            Painel.show("Nome é obrigatório");
        }
        if (telefone.getText().isEmpty()) {
            Painel.show("Telefone é obrigatório");
        }
        return ok;
    }

    public void salvarAction() {

        if (filiado == null) {
            if (ValidacaoCliente()) {
                Painel.show("Erro de validação");
            } else {
                cadastrarAction();
            }
        } else {
            alterarAction();
        }

        listarAction();
        nomeFiliado.setText("");
        nomeFiliado.requestFocus();
        telefone.setText("");
        telefone.requestFocus();
        filiado = null;

    }

    public void cadastrarAction() {

        filiado = new Filiado();
        filiado.setNome(nomeFiliado.getText());
        filiado.setTelefone(telefone.getText());
        filiado.setCliente(cliente);

        if (filiadoDao.salvar(filiado)) {

            Painel.show("Cadastrado com sucesso!");

            listarAction();
        } else {

            Painel.show("Erro ao cadastrar!");
        }
    }

    public void alterarAction() {

        filiado.setNome(nomeFiliado.getText());
        filiado.setTelefone(telefone.getText());

        boolean res = filiadoDao.atualizar(filiado);
        if (res) {
            Painel.show("Editado com sucesso");
            listarAction();
        } else {
            Painel.show("Erro ao editar");
        }

    }

    public void excluirAction() {
        filiado = getItemSelecionado();
        if (cliente == null) {
            Painel.show("Escolha um filiado");
        } else {
            boolean res = filiadoDao.excluir(filiado);
            if (res) {
                Painel.show("Cliente excluido");
                listarAction();
            } else {
                Painel.show("Erro ao tentar excluir");
            }
        }
    }

    public Filiado getItemSelecionado() {
        
        int linha = tbFiliado.getSelectedRow();
        if (linha >= 0) {
            return listaFiliado.get(linha);

        } else {
            return null;
        }
    }

    public void popularFormAction() {
        filiado = getItemSelecionado();

        if (filiado != null) {

            nomeFiliado.setText(filiado.getNome());
            telefone.setText(filiado.getTelefone());
            
        } else {
            Painel.show("Selecione um cliente");
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

    }
    
    public void acaobotaolimpa() {
        nomeFiliado.setText(null);
        nomeFiliado.requestFocus();
        telefone.setText(null);
        telefone.requestFocus();
        filiado = null;
        
    }

}

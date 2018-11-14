/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeprodutoalternativo.control;

import cadastrodeprodutoalternativo.interfaces.ControlI;
import cadastrodeprodutoalternativo.model.dao.ClienteDao;
import cadastrodeprodutoalternativo.model.dao.FiliadoDao;
import cadastrodeprodutoalternativo.modelo.Cliente;
import cadastrodeprodutoalternativo.modelo.Filiado;
import cadastrodeprodutoalternativo.uteis.Conversor;
import cadastrodeprodutoalternativo.uteis.Painel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alunos
 */
public class ClienteControl implements ControlI {

    private JTextField nomeCliente;
    private JFormattedTextField CPF;
    private JFormattedTextField dtNasc;
    private JTextField tfPesquisa;
    private JTable tbCliente;

    private FiliadoControl filiadoCtrl;
    private JTextField nomeFiliado;
    private JFormattedTextField telefone;
    private JTable tbFiliado;

    private Cliente cliente = null;
    private List<Cliente> listaCliente;
    private ClienteDao clienteDao;


    public ClienteControl() {
    }

    public ClienteControl(JTextField nomeCliente, JFormattedTextField dtNasc, JFormattedTextField CPF, JTable tbCliente, JTextField tfPesquisa,
            JTextField nomeFiliado, JTextField telefone, JTable tbFiliado) {
        this.nomeCliente = nomeCliente;
        this.CPF = CPF;
        this.dtNasc = dtNasc;
        this.tfPesquisa = tfPesquisa;
        this.tbCliente = tbCliente;

        filiadoCtrl = new FiliadoControl(nomeFiliado, telefone, tbFiliado);

        listaCliente = new ArrayList<>();
        clienteDao = new ClienteDao();
    }

    public void listarAction() {
        listaCliente = clienteDao.listar();
        showItensTable();
    }

    private void showItensTable() {
        DefaultTableModel model;
        model = (DefaultTableModel) tbCliente.getModel();
        model.setNumRows(0);
        for (Cliente cli : listaCliente) {

            model.addRow(new Object[]{
                cli.getId(),
                cli.getNome(),
                Conversor.dataBancoParaUsuario(cli.getDtNasc()),
                cli.getCpf()
            });
        }
    }

    public void salvarAction() {

        if (cliente == null) {
            if (ValidacaoCliente()) {
                Painel.show("Erro de validação");
            } else {
                cadastrarAction();
            }
        } else {
            alterarAction();
        }

        listarAction();
        nomeCliente.setText(null);
        nomeCliente.requestFocus();
        dtNasc.setText(null);
        CPF.setText(null);
        cliente = null;

    }

    public boolean ValidacaoCliente() {

        boolean ok = false;

        if (nomeCliente.getText().isEmpty()) {
            ok = true;
            Painel.show("Nome é obrigatório");
        }
        if (dtNasc.getText().isEmpty()) {
            Painel.show("Data de nascimento é obrigatória");
        }
        if (CPF.getText().isEmpty()) {
            Painel.show("CPF é obrigatório");
        }

        return ok;

    }

    @Override
    public void cadastrarAction() {

        cliente = new Cliente();
        cliente.setNome(nomeCliente.getText());
        cliente.setDtNasc(Conversor.dataUsuarioParaBanco(dtNasc.getText()));
        cliente.setCpf(CPF.getText());

        if (clienteDao.salvar(cliente)) {
            Painel.show("Cadastrado com sucesso!");
            listarAction();
        } else {
            Painel.show("Erro ao salvar!");
        }
    }

    @Override
    public void alterarAction() {

        cliente.setNome(nomeCliente.getText());
        cliente.setDtNasc(Conversor.dataUsuarioParaBanco(dtNasc.getText()));
        cliente.setCpf(CPF.getText());

        boolean res = clienteDao.atualizar(cliente);
        if (res) {
            Painel.show("Editado com sucesso");
            listarAction();
        } else {
            Painel.show("Erro ao editar");
        }

    }

    @Override
    public void excluirAction() {
        
        cliente = getItemSelecionado();
        if (cliente == null) {
            Painel.show("Escolha uma categoria");
        } else {
            boolean res = clienteDao.excluir(cliente);
            if (res) {
                Painel.show("Cliente excluido");
                listarAction();
            } else {
                Painel.show("Erro ao tentar excluir");
            }
        }
        cliente = null;
    }

    public Cliente getItemSelecionado() {

        int linha = tbCliente.getSelectedRow();
        if (linha >= 0) {
            return listaCliente.get(linha);

        } else {
            return null;
        }
    }

    @Override
    public void popularFormAction() {
        cliente = getItemSelecionado();

        if (cliente != null) {
            nomeCliente.setText(cliente.getNome());
            dtNasc.setText(Conversor.dataBancoParaUsuario(cliente.getDtNasc()));
            CPF.setText(cliente.getCpf());
        } else {
            Painel.show("Selecione um cliente");
        }
    }

    @Override
    public void pesquisarAction() {
        listaCliente = clienteDao.pesquisarPorNome(tfPesquisa.getText());

        DefaultTableModel model = (DefaultTableModel) tbCliente.getModel();
        model.setNumRows(0);
        for (Cliente c : listaCliente) {
            model.addRow(new Object[]{
                c.getId(),
                c.getNome(),
                c.getDtNasc(),
                c.getCpf()
            });

        }
    }

    public void acaobotaolimpa() {
        nomeCliente.setText("");
        dtNasc.setText(null);
        CPF.setText("");
    }

    public FiliadoControl getFiliadoCtrl() {
        return filiadoCtrl;
    }

    public void enviarClienteParaFiliado() {
        filiadoCtrl.setCliente(getItemSelecionado());
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entidade.TPaagamentoPagar;
import entidade.TabelaQPagamento;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class QuitarContasPagarController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtData;
    @FXML
    private JFXButton btnPesquisa;
    @FXML
    private JFXTextField txtFornecedor;
    @FXML
    private JFXTextField txtFuncionario;
    @FXML
    private TableView<TabelaQPagamento> tabProdutos;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colDescricao;
    @FXML
    private TableColumn colQtde;
    @FXML
    private TableColumn colUnimed;
    @FXML
    private TableColumn colPrecoUnit;
    @FXML
    private TableColumn colTotal;
    @FXML
    private JFXTextField txtAcrescimo;
    @FXML
    private JFXTextField txtDesconto;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private JFXComboBox<?> cbFormaPag;
    @FXML
    private JFXTextField txtValorPag;
    @FXML
    private JFXButton btnIncluir;
    @FXML
    private JFXButton btnExcluir;
    @FXML
    private TableView<TPaagamentoPagar> tabFormaPag;
    @FXML
    private TableColumn colFormaPag;
    @FXML
    private TableColumn colValor;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnFinalizar;
    @FXML
    private JFXTextField txtRestante;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MaskFieldUtil.monetaryField(txtTotal);
        MaskFieldUtil.monetaryField(txtAcrescimo);
        MaskFieldUtil.monetaryField(txtDesconto);
        MaskFieldUtil.monetaryField(txtRestante);
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        String str = fmt.format(new Date());
        txtData.setText(str);
    }    

    @FXML
    private void clkPesquisa(ActionEvent event) {
    }

    @FXML
    private void clkIncluir(ActionEvent event) {
    }

    @FXML
    private void clkExcluir(ActionEvent event) {
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
    }

    @FXML
    private void clkFinalizar(ActionEvent event) {
    }
    
}

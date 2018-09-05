/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class RealizarCompraController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtData;
    @FXML
    private JFXTextField txtFuncionario;
    @FXML
    private JFXTextField txtFornecedor;
    @FXML
    private JFXButton btnPesquisaFornecedor;
    @FXML
    private JFXButton btnPesquisaProduto;
    @FXML
    private JFXTextField txtProduto;
    @FXML
    private JFXButton btnInsereProduto;
    @FXML
    private JFXTextField txtQtde;
    @FXML
    private JFXButton btnExcluir;
    @FXML
    private JFXButton btnIncluir;
    @FXML
    private TableView<?> tabProdutos;
    @FXML
    private TableColumn<?, ?> colCodigo;
    @FXML
    private TableColumn<?, ?> colDescricao;
    @FXML
    private TableColumn<?, ?> colQtde;
    @FXML
    private TableColumn<?, ?> colUmedida;
    @FXML
    private TableColumn<?, ?> colValor;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private JFXButton btnNovo;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnGravar;
    @FXML
    private JFXButton btnFinalizar;
    @FXML
    private JFXButton btnSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clkPesquisaFornecedor(ActionEvent event) {
    }

    @FXML
    private void clkPesquisaProduto(ActionEvent event) {
    }

    @FXML
    private void clkInsereProduto(ActionEvent event) {
    }

    @FXML
    private void clkExcluir(ActionEvent event) {
    }

    @FXML
    private void clkIncluir(ActionEvent event) {
    }

    @FXML
    private void clkNovo(ActionEvent event) {
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
    }

    @FXML
    private void clkGravar(ActionEvent event) {
    }

    @FXML
    private void clkFinalizar(ActionEvent event) {
    }

    @FXML
    private void clkSair(ActionEvent event) {
    }
    
}

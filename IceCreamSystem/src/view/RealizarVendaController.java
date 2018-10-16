/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entidade.TabelaQPagamento;
import entidade.TipoVenda;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class RealizarVendaController implements Initializable {

    @FXML
    private JFXButton btnGravar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnExcluir;
    @FXML
    private JFXButton btnSair;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtComanda;
    @FXML
    private JFXTextField txtData;
    @FXML
    private JFXTextField txtCliente;
    @FXML
    private JFXTextField txtFuncionario;
    @FXML
    private JFXButton btnPesqCliente;
    @FXML
    private JFXButton btnNovoCli;
    @FXML
    private JFXComboBox<TipoVenda> cbTipoVenda;
    @FXML
    private JFXTextField txtDtEntrega;
    @FXML
    private JFXButton btnPesqProd;
    @FXML
    private JFXTextField txtProduto;
    @FXML
    private Label txtValorProduto;
    @FXML
    private JFXButton btnInsereProd;
    @FXML
    private JFXButton btnExcluiProduto;
    @FXML
    private TableView<TabelaQPagamento> tabProdutos;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colDescricao;
    @FXML
    private TableColumn colUnidade;
    @FXML
    private TableColumn colQtde;
    @FXML
    private TableColumn colPreco;
    @FXML
    private TableColumn colTotal;
    @FXML
    private Label valorTotal;
    @FXML
    private JFXButton btnFinalizar;
    @FXML
    private JFXTextField txtQtde;
    @FXML
    private JFXButton btnLocalizar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializa(false);
    }    


    @FXML
    private void clkGravar(ActionEvent event) {
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
        inicializa(true);
    }

    @FXML
    private void clkExcluir(ActionEvent event) {
    }

    @FXML
    private void clkSair(ActionEvent event) {
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clkPesqCli(ActionEvent event) {
    }

    @FXML
    private void clkNovoCli(ActionEvent event) {
    }

    @FXML
    private void clkPesqProd(ActionEvent event) {
    }

    @FXML
    private void clkInclui(ActionEvent event) {
    }

    @FXML
    private void clkExclui(ActionEvent event) {
    }

    @FXML
    private void clkFinalizar(ActionEvent event) {
    }
    
    private void inicializa(boolean estado){
        txtComanda.setDisable(estado);
        txtCliente.setDisable(estado);
        cbTipoVenda.setDisable(estado);
        txtDtEntrega.setDisable(estado);
        txtQtde.setDisable(estado);
        txtValorProduto.setText("0.00");
        valorTotal.setText("0.00");
        
    }

    @FXML
    private void clkLocalizar(ActionEvent event) {
    }
    
}

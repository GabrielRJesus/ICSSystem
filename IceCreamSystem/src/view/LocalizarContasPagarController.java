/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import control.ContaPagarControl;
import control.TipoDespesasControl;
import control.TipoPagamentoControl;
import entidade.ContasPagar;
import entidade.TipoDespesas;
import entidade.TipoPagamento;
import exception.ControlException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class LocalizarContasPagarController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtData;
    @FXML
    private JFXComboBox<TipoDespesas> cbDespesas;
    @FXML
    private JFXComboBox<TipoPagamento> cbPagamento;
    @FXML
    private JFXButton btnPesquisar;
    @FXML
    private TableView<ContasPagar> tabelaContasPagar;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn<TipoDespesas, String> colTipoDespesa;
    @FXML
    private TableColumn colDtVencimento;
    @FXML
    private TableColumn colValor;
    @FXML
    private JFXButton btnSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            carregaDespesas();
            carregaTpPagamento();
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
        tabelaContasPagar.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colTipoDespesa.setCellValueFactory(new PropertyValueFactory("tpd"));
        colDtVencimento.setCellValueFactory(new PropertyValueFactory("data"));
        colValor.setCellValueFactory(new PropertyValueFactory("valor"));
    }    

    @FXML
    private void clkPesquisar(ActionEvent event) throws ControlException, ParseException {
        carregaTabela();
    }

    @FXML
    private void selecionaContasPagar(MouseEvent event) throws IOException {
        if(tabelaContasPagar.getSelectionModel().getSelectedIndex()>=0){
            ContaPagarControl cc = new ContaPagarControl();
            cc.guardaSelecionado(tabelaContasPagar.getSelectionModel().getSelectedItem());
            Parent root = FXMLLoader.load(getClass().getResource("/view/LancarContasPagar.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Lançar Contas à Pagar");
            stage.setResizable(false);
            stage.showAndWait();
            stage.close();
        }
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LancarContasPagar.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Menu Principal");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }
    
    public void carregaDespesas() throws ControlException{
        TipoDespesasControl tdc = new TipoDespesasControl();
        List<TipoDespesas> lista = new ArrayList<>();
        lista = tdc.listaDespesas(0, "");
        ObservableList<TipoDespesas> colection = FXCollections.observableArrayList(lista);
        cbDespesas.getItems().addAll(colection);
    }
    
    public void carregaTpPagamento() throws ControlException{
        TipoPagamentoControl tpp = new TipoPagamentoControl();
        List<TipoPagamento> lista = new ArrayList<>();
        lista = tpp.listaTPagamento(0, "");
        ObservableList<TipoPagamento> colection = FXCollections.observableArrayList(lista);
        cbPagamento.getItems().addAll(colection);
    }
    
    public void carregaTabela() throws ControlException, ParseException{
        ContaPagarControl pc = new ContaPagarControl();
        List<ContasPagar> lista = new ArrayList<>();
        Integer codigo = 0;
        java.sql.Date data = null;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        if(txtData.getText()!=null && !txtData.getText().isEmpty()){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            data = new java.sql.Date(format.parse(txtData.getText()).getTime());
        }
        lista = pc.listaContas(codigo, data, cbDespesas.getValue(), cbPagamento.getValue());
        if(lista!=null){
            ObservableList<ContasPagar> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabelaContasPagar.setItems(modelo);
        }
    }
    
}

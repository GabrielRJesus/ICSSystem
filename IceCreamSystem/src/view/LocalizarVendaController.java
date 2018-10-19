/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import control.VendaControl;
import entidade.*;
import exception.ControlException;
import exception.EntidadeException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class LocalizarVendaController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtCliente;
    @FXML
    private JFXRadioButton rbAberta;
    @FXML
    private JFXRadioButton rbFechada;
    @FXML
    private JFXComboBox<TipoVenda> cbTipo;
    @FXML
    private JFXButton btnPesquisar;
    @FXML
    private TableView<Venda> tabVendas;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colComanda;
    @FXML
    private TableColumn colData;
    @FXML
    private TableColumn colStatus;
    @FXML
    private TableColumn<TipoVenda, String> colTipo;
    @FXML
    private TableColumn colValor;
    @FXML
    private JFXButton btnSair;
    
    private String status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tabVendas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colComanda.setCellValueFactory(new PropertyValueFactory("comanda"));
        colData.setCellValueFactory(new PropertyValueFactory("data"));
        colStatus.setCellValueFactory(new PropertyValueFactory("status"));
        colTipo.setCellValueFactory(new PropertyValueFactory("tpv"));
        colValor.setCellValueFactory(new PropertyValueFactory("total"));
    }    

    @FXML
    private void clkAberta(ActionEvent event) {
        rbFechada.setSelected(false);
        rbAberta.setSelected(true);
        status = "Aberta";
    }

    @FXML
    private void clkFechada(ActionEvent event) {
        rbFechada.setSelected(true);
        rbAberta.setSelected(false);
        status = "Fechada";
    }

    @FXML
    private void clkPesquisar(ActionEvent event) throws ControlException, EntidadeException {
        carregaTabela();        
    }

    @FXML
    private void selecionaVenda(MouseEvent event) {
        if(tabVendas.getSelectionModel().getSelectedIndex()>=0){
            VendaControl vc = new VendaControl();
            vc.guardaSelecionada(tabVendas.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void clkSair(ActionEvent event) {
        Stage stage = (Stage)btnSair.getScene().getWindow();
        stage.close();
    }
    
    public void carregaTabela() throws ControlException, EntidadeException{
        VendaControl vc = new VendaControl();
        List<Venda> lista = new ArrayList<>();
        Integer codigo = 0, tipo = null;
        if(cbTipo.getValue()!=null)
            tipo = cbTipo.getValue().getCodigo();
        lista = vc.listaVendas(codigo, txtCliente.getText(), status, tipo);
        if(lista!=null){
            ObservableList<Venda> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabVendas.setItems(modelo);
        }
    }
    
}

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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class LocalizaCompraController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtData;
    @FXML
    private TableView<?> tabCompras;
    @FXML
    private TableColumn<?, ?> colCodigo;
    @FXML
    private TableColumn<?, ?> colFornecedor;
    @FXML
    private TableColumn<?, ?> colData;
    @FXML
    private TableColumn<?, ?> colValor;
    @FXML
    private JFXButton btnPesquisar;
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
    private void selecionaCompra(MouseEvent event) {
    }

    @FXML
    private void clkPesquisar(ActionEvent event) {
    }

    @FXML
    private void clkSair(ActionEvent event) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class TelaPrincipalController implements Initializable {

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuGerenciar;
    @FXML
    private MenuItem gerenciarCliente;
    @FXML
    private MenuItem gerenciarFuncionario;
    @FXML
    private MenuItem gerenciarFornecedor;
    @FXML
    private MenuItem gerenciarProduto;
    @FXML
    private MenuItem gerenciarMarcas;
    @FXML
    private MenuItem gerenciarLotes;
    @FXML
    private MenuItem gerenciarTipos;
    @FXML
    private MenuItem gerenciarUnidades;
    @FXML
    private MenuItem gerenciarCategorias;
    @FXML
    private MenuItem gerenciarModalidades;
    @FXML
    private MenuItem gerenciarPagamentos;
    @FXML
    private Menu menuLocalizar;
    @FXML
    private Menu menuFuncoes;
    @FXML
    private Menu menuRelatorios;
    @FXML
    private Menu menuSair;
    @FXML
    private JFXButton btNovaComanda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gerCliente(ActionEvent event) {
    }

    @FXML
    private void gerFuncionario(ActionEvent event) {
    }

    @FXML
    private void gerFornecedor(ActionEvent event) {
    }

    @FXML
    private void sair(ActionEvent event) {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();
    }
    
}

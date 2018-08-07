/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class AbrirCaixaController implements Initializable {

    @FXML
    private JFXTextField txtFuncionario;
    @FXML
    private JFXTextField txtTrocoInicial;
    @FXML
    private JFXTextField txtData;
    @FXML
    private JFXComboBox<?> cbPeriodo;
    @FXML
    private JFXButton btnAbrir;
    @FXML
    private JFXButton btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clkAbrir(ActionEvent event) {
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
    }
    
}

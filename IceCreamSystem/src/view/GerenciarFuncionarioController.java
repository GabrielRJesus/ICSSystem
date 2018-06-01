/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
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
public class GerenciarFuncionarioController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtCpf;
    @FXML
    private JFXTextField txtRg;
    @FXML
    private JFXTextField txtTelefone;
    @FXML
    private JFXTextField txtCelular;
    @FXML
    private JFXDatePicker dpData;
    @FXML
    private JFXComboBox<?> cbSexo;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtEndereco;
    @FXML
    private JFXTextField txtNumero;
    @FXML
    private JFXTextField txtBairro;
    @FXML
    private JFXTextField txtCep;
    @FXML
    private JFXComboBox<?> cbCidade;
    @FXML
    private JFXComboBox<?> cbEstado;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btGravar;
    @FXML
    private JFXButton btLocalizar;
    @FXML
    private JFXButton btSair;
    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXPasswordField txtSenha;
    @FXML
    private JFXDatePicker dpDataAdm;
    @FXML
    private JFXDatePicker dpDataDem;
    @FXML
    private JFXTextField txtSalario;
    @FXML
    private JFXTextField txtCargo;
    @FXML
    private JFXButton btCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clkNovo(ActionEvent event) {
    }

    @FXML
    private void clkGravar(ActionEvent event) {
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
    }

    @FXML
    private void clkLocalizar(ActionEvent event) {
    }

    @FXML
    private void clkSair(ActionEvent event) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entidade.Cidade;
import entidade.Estado;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class GerenciarClienteController implements Initializable {

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
    private JFXComboBox<String> cbSexo;
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
    private JFXComboBox<Cidade> cbCidade;
    @FXML
    private JFXComboBox<Estado> cbEstado;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btGravar;
    @FXML
    private JFXButton btLocalizar;
    @FXML
    private JFXButton btSair;
    @FXML
    private JFXButton btCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializa(true);
    }    

    @FXML
    private void clkNovo(ActionEvent event) {
        inicializa(false);
    }

    @FXML
    private void clkGravar(ActionEvent event) {
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
        limpatela();
        inicializa(true);
    }

    @FXML
    private void clkLocalizar(ActionEvent event) {
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btSair.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Menu Principal");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }
    
    public void inicializa(boolean estado){
        txtBairro.setDisable(estado);
        txtCelular.setDisable(estado);
        txtCep.setDisable(estado);
        txtCodigo.setDisable(estado);
        txtCpf.setDisable(estado);
        txtEmail.setDisable(estado);
        txtEndereco.setDisable(estado);
        txtNome.setDisable(estado);
        txtNumero.setDisable(estado);
        txtRg.setDisable(estado);
        txtTelefone.setDisable(estado);
        dpData.setDisable(estado);
        cbCidade.setDisable(estado);
        cbEstado.setDisable(estado);
        cbSexo.setDisable(estado);
        btCancelar.setDisable(estado);
        btGravar.setDisable(estado);
        
    }
    
    public void limpatela(){
        txtBairro.setText("");
        txtCelular.setText("");
        txtCep.setText("");
        txtCodigo.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtEndereco.setText("");
        txtNome.setText("");
        txtNumero.setText("");
        txtRg.setText("");
        txtTelefone.setText("");
        dpData.setValue(LocalDate.now());
        cbCidade.setPromptText("Cidade");
        cbEstado.setPromptText("Estado");
        cbSexo.setPromptText("Sexo");
    }
    
}

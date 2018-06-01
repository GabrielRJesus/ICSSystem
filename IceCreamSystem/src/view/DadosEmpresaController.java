/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class DadosEmpresaController implements Initializable {

    @FXML
    private JFXTextField tvCodigo;
    @FXML
    private JFXTextField tvNome;
    @FXML
    private JFXTextField tvRazaoSocial;
    @FXML
    private JFXTextField tvCNPJ;
    @FXML
    private JFXTextField tvIe;
    @FXML
    private JFXTextField tvTelefone;
    @FXML
    private JFXDatePicker dpDtFundacao;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btGravar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btSair;


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
    private void clkSair(ActionEvent event) {
    }

    
}

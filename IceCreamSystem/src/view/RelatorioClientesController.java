/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.RelatorioControl;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class RelatorioClientesController implements Initializable {

    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtCPF;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btGerarRelatorio;
    @FXML
    private JFXButton btSair;
    
    private RelatorioControl rc = new RelatorioControl();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MaskFieldUtil.cepField(txtCPF);
    }    

    @FXML
    private void clkCancelar(ActionEvent event) {
        txtNome.setText("");
        txtCPF.setText("");
    }

    @FXML
    private void clkGerar(ActionEvent event) throws SQLException {
        rc.geraRelatorioClientes(txtNome.getText(), txtCPF.getText());
    }

    @FXML
    private void clkSair(ActionEvent event) {
        Stage stage = (Stage)btCancelar.getScene().getWindow();
        stage.close();
    }
    
}

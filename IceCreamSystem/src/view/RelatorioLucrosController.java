/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
public class RelatorioLucrosController implements Initializable {

    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btGerarRelatorio;
    @FXML
    private JFXButton btSair;
    @FXML
    private JFXCheckBox chbTodas;
    @FXML
    private JFXCheckBox chbQuitadas;
    @FXML
    private JFXCheckBox chbReceber;
    @FXML
    private JFXTextField txtDataI;
    @FXML
    private JFXTextField txtDataF;

    private RelatorioControl rc = new RelatorioControl();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MaskFieldUtil.dateField(txtDataI);
        MaskFieldUtil.dateField(txtDataF);
    }    

    @FXML
    private void clkCancelar(ActionEvent event) {
        chbQuitadas.setSelected(false);
        chbReceber.setSelected(false);
        chbTodas.setSelected(false);
        txtDataF.setText("");
        txtDataI.setText("");
        
    }

    @FXML
    private void clkGerar(ActionEvent event) throws SQLException {
        Boolean estado = null;
        if(chbTodas.isSelected())
            estado = null;
        if(chbQuitadas.isSelected())
            estado = true;
        if(chbReceber.isSelected())
            estado = false;
        
        rc.geraContasReceber(estado, txtDataI.getText(), txtDataF.getText());
    }

    @FXML
    private void clkSair(ActionEvent event) {
        Stage stage = (Stage)btCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clkTodas(ActionEvent event) {
        if(chbTodas.isSelected()){
            chbQuitadas.setSelected(false);
            chbReceber.setSelected(false);
        }
    }

    @FXML
    private void clkQuitadas(ActionEvent event) {
        if(chbQuitadas.isSelected()){
            chbTodas.setSelected(false);
            chbReceber.setSelected(false);
        }
    }

    @FXML
    private void clkReceber(ActionEvent event) {
        if(chbReceber.isSelected()){
            chbQuitadas.setSelected(false);
            chbTodas.setSelected(false);
        }
    }
    
}

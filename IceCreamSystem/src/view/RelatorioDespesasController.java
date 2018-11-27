/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
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
public class RelatorioDespesasController implements Initializable {

    @FXML
    private JFXTextField txtDataInicio;
    @FXML
    private JFXTextField txtDataFinal;
    @FXML
    private JFXButton btSair;
    @FXML
    private JFXButton btGerarRelatorio;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXRadioButton rbVencidas;
    
    private RelatorioControl rc = new RelatorioControl();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MaskFieldUtil.dateField(txtDataFinal);
        MaskFieldUtil.dateField(txtDataInicio);
    }    

    @FXML
    private void clkSair(ActionEvent event) {
        Stage stage = (Stage)btCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clkGerar(ActionEvent event) throws SQLException {
        boolean vencidas = false;
        if(rbVencidas.isSelected())
            vencidas = true;
        rc.geraRelatorioDespesas(txtDataInicio.getText(), txtDataFinal.getText(), vencidas);
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
        txtDataFinal.setText("");
        txtDataInicio.setText("");
        rbVencidas.setSelected(false);
    }
    
}

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
public class RelatorioVendasController implements Initializable {

    @FXML
    private JFXCheckBox chbDia;
    @FXML
    private JFXTextField txtDataI;
    @FXML
    private JFXTextField txtDataF;
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
        MaskFieldUtil.dateField(txtDataI);
        MaskFieldUtil.dateField(txtDataF);
    }    

    @FXML
    private void clkVendasDia(ActionEvent event) {
        if(chbDia.isSelected()){
            txtDataF.setDisable(true);
            txtDataI.setDisable(true);
        }else{
            txtDataF.setDisable(false);
            txtDataI.setDisable(false);
        }
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
        chbDia.setSelected(false);
        txtDataF.setText("");
        txtDataI.setText("");
    }

    @FXML
    private void clkGerar(ActionEvent event) throws SQLException {
        boolean teste = false;
        if(chbDia.isSelected())
            teste = true;
        rc.geraRelatorioVendas(txtDataI.getText(), txtDataF.getText(), teste);
    }

    @FXML
    private void clkSair(ActionEvent event) {
        Stage stage = (Stage)btCancelar.getScene().getWindow();
        stage.close();
    }
    
}

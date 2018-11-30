/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import control.RelatorioControl;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class RelatorioEntregasController implements Initializable {

    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btGerarRelatorio;
    @FXML
    private JFXButton btSair;
    @FXML
    private JFXCheckBox chbDia;
    @FXML
    private JFXCheckBox chbEfetuadas;
    @FXML
    private JFXCheckBox chbProximas;
    
    private RelatorioControl rc = new RelatorioControl();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clkCancelar(ActionEvent event) {
        chbEfetuadas.setSelected(false);
        chbProximas.setSelected(false);
        chbDia.setSelected(false);
    }

    @FXML
    private void clkGerar(ActionEvent event) throws SQLException {
        String rest = "";
        if(chbDia.isSelected())
            rest = "Dia";
        if(chbEfetuadas.isSelected())
            rest = "Efetuadas";
        if(chbProximas.isSelected())
            rest = "Proximas";
        
        rc.geraRelatorioEntregas(rest);
    }

    @FXML
    private void clkSair(ActionEvent event) {
        Stage stage = (Stage)btCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clkDia(ActionEvent event) {
        if(chbDia.isSelected()){
            chbEfetuadas.setSelected(false);
            chbProximas.setSelected(false);
        }
    }

    @FXML
    private void clkEfetuadas(ActionEvent event) {
        if(chbEfetuadas.isSelected()){
            chbDia.setSelected(false);
            chbProximas.setSelected(false);
        }
    }

    @FXML
    private void clkProximas(ActionEvent event) {
        if(chbProximas.isSelected()){
            chbDia.setSelected(false);
            chbEfetuadas.setSelected(false);
        }
    }
    
}

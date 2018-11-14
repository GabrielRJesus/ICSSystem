/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.CaixaControl;
import exception.ControlException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class LocValorCaixaController implements Initializable {

    @FXML
    private JFXTextField txtData;
    @FXML
    private JFXTextField txtValor;
    @FXML
    private JFXButton btnSair;

    private CaixaControl cc = new CaixaControl();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MaskFieldUtil.dateField(txtData);
        double pagar = 0.0, receber = 0.0;
        double total = 0.0;
        try {
            pagar = cc.valorPagar(cc.retornaCaixaAberto().getCodigo());
            receber = cc.valorReceber(cc.retornaCaixaAberto().getCodigo());
            total = cc.retornaCaixaAberto().getTroco() + receber - pagar;
            txtValor.setText(total+"");
            Date data = new Date();
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            String str = fmt.format(data);
            txtData.setText(str);
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void clkSair(ActionEvent event) {
        Stage stage = (Stage)btnSair.getScene().getWindow();
        stage.close();
    }
    
}

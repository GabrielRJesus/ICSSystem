/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import control.EmpresaControl;
import exception.ControlException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.MaskFieldUtil;

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
    private JFXButton btNovo;
    @FXML
    private JFXButton btGravar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btSair;
    @FXML
    private JFXTextField tvData;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tvCodigo.setDisable(true);
        MaskFieldUtil.cnpjField(tvCNPJ);
        MaskFieldUtil.foneField(tvTelefone);
        MaskFieldUtil.dateField(tvData);
        EmpresaControl ec = new EmpresaControl();
        try {
            if(ec.retornaEmpresa()!=null){
                tvCodigo.setText(ec.retornaEmpresa().getCodigo()+"");
                tvNome.setText(ec.retornaEmpresa().getNome());
                tvRazaoSocial.setText(ec.retornaEmpresa().getRazaoSocial());
                tvCNPJ.setText(ec.retornaEmpresa().getCnpj());
                tvIe.setText(ec.retornaEmpresa().getIe());
                tvTelefone.setText(ec.retornaEmpresa().getTelefone());
                SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
                String str = fmt.format(ec.retornaEmpresa().getDataFundação()); 
                tvData.setText(str);
            }else{
                inicializa(true);
            }
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void clkNovo(ActionEvent event) {
        inicializa(false);
    }

    @FXML
    private void clkGravar(ActionEvent event) throws ControlException, ParseException {
        EmpresaControl ec = new EmpresaControl();
        Integer codigo = 0;
        Integer qtde = 0, qtdr = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date data = new java.sql.Date(format.parse(tvData.getText()).getTime());
        if(tvCodigo.getText()!=null && !tvCodigo.getText().isEmpty())
            codigo = Integer.parseInt(tvCodigo.getText());
        int result = ec.gravaEmpresa(codigo, tvNome.getText(), tvRazaoSocial.getText(), tvCNPJ.getText(), tvIe.getText(), tvTelefone.getText(), data);
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Empresa gravada com sucesso!");
            alert.showAndWait();
            inicializa(true);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Erro na hora de gravar os dados!");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
        inicializa(false);
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
        Stage stage = (Stage) btSair.getScene().getWindow();
        stage.close();
    }
    
    public void inicializa(boolean estado){
        tvNome.setDisable(estado);
        tvRazaoSocial.setDisable(estado);
        tvCNPJ.setDisable(estado);
        tvIe.setDisable(estado);
        tvTelefone.setDisable(estado);
        tvData.setDisable(estado);
    }
    
}

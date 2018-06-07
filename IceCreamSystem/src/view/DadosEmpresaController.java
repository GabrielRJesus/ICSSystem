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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
        tvCodigo.setDisable(true);
        EmpresaControl ec = new EmpresaControl();
        try {
            if(ec.retornaEmpresa()!=null){
                tvCodigo.setText(ec.retornaEmpresa().getCodigo()+"");
                tvNome.setText(ec.retornaEmpresa().getNome());
                tvRazaoSocial.setText(ec.retornaEmpresa().getRazaoSocial());
                tvCNPJ.setText(ec.retornaEmpresa().getCnpj());
                tvIe.setText(ec.retornaEmpresa().getIe());
                tvTelefone.setText(ec.retornaEmpresa().getTelefone());
                LocalDate ld = new java.sql.Date( ec.retornaEmpresa().getDataFundação().getTime() ).toLocalDate();
                dpDtFundacao.setValue(ld);
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
    private void clkGravar(ActionEvent event) throws ControlException {
        EmpresaControl ec = new EmpresaControl();
        Integer codigo = 0;
        Integer qtde = 0, qtdr = 0;
        Date data = java.sql.Date.valueOf(dpDtFundacao.getValue());
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
        tvNome.setDisable(estado);
        tvRazaoSocial.setDisable(estado);
        tvCNPJ.setDisable(estado);
        tvIe.setDisable(estado);
        tvTelefone.setDisable(estado);
        dpDtFundacao.setDisable(estado);
    }
    
}

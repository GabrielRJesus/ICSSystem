/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import control.CaixaControl;
import entidade.Funcionario;
import exception.ControlException;
import exception.EntidadeException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class FecharCaixaController implements Initializable {

    @FXML
    private JFXTextField txtFuncionario;
    @FXML
    private JFXTextField txtTrocoInicial;
    @FXML
    private JFXTextField txtData;
    @FXML
    private JFXComboBox<String> cbPeriodo;
    @FXML
    private JFXButton btnFechar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXTextField txtValorCaixa;
    @FXML
    private JFXTextField txtMotivo;
    
    private CaixaControl cc = new CaixaControl();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregacb();
        txtFuncionario.setText(Funcionario.getFuncLogado().getNome());
        MaskFieldUtil.dateField(txtData);
        txtMotivo.setDisable(true);
        double pagar = 0.0, receber = 0.0;
        double total = 0.0;
        try {
            pagar = cc.valorPagar(cc.retornaCaixaAberto().getCodigo());
            receber = cc.valorReceber(cc.retornaCaixaAberto().getCodigo());
            total = cc.retornaCaixaAberto().getTroco() + receber - pagar;
            txtTrocoInicial.setText(total+"");
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
        
    }   

    @FXML
    private void clkFechar(ActionEvent event) throws IOException, ParseException, ControlException, EntidadeException {
        Double troco = 0.0;
        double inicial = 0.0;
        inicial = Double.parseDouble(txtTrocoInicial.getText());
        java.sql.Date data = null;
        if(txtData.getText()!=null && !txtData.getText().isEmpty()){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            data = new java.sql.Date(format.parse(txtData.getText()).getTime());
        }
        if(txtValorCaixa.getText()!=null && !txtValorCaixa.getText().isEmpty()){
            troco = Double.parseDouble(txtValorCaixa.getText());
            if(inicial > troco || troco > inicial){
                txtMotivo.setDisable(false);
                if(txtMotivo.getText()!=null && !txtMotivo.getText().isEmpty()){
                    int retorno = cc.fecharCaixa(Funcionario.getFuncLogado(), troco, data, cbPeriodo.getValue(),txtMotivo.getText());
                        if(retorno>0){
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Resposta do Servidor");
                            alert.setHeaderText(null);
                            alert.setContentText("Caixa Fechado com sucesso!");
                            alert.showAndWait();
                            clkCancelar(event);
                        }else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Resposta do Servidor");
                            alert.setHeaderText(null);
                            alert.setContentText("Erro na hora de fechar o caixa!");
                            alert.showAndWait();
                        }
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Resposta do Servidor");
                    alert.setHeaderText(null);
                    alert.setContentText("Digite o motivo da diferença!");
                    alert.showAndWait();
                }
            }else{
                int retorno = cc.fecharCaixa(Funcionario.getFuncLogado(), troco, data, cbPeriodo.getValue(),txtMotivo.getText());
                if(retorno>0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Resposta do Servidor");
                    alert.setHeaderText(null);
                    alert.setContentText("Caixa Fechado com sucesso!");
                    alert.showAndWait();
                    clkCancelar(event);
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Resposta do Servidor");
                    alert.setHeaderText(null);
                    alert.setContentText("Erro na hora de fechar o caixa!");
                    alert.showAndWait();
                }
            }
        }else{
            int retorno = cc.fecharCaixa(Funcionario.getFuncLogado(), inicial, data, cbPeriodo.getValue(),txtMotivo.getText());
            if(retorno>0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Resposta do Servidor");
                alert.setHeaderText(null);
                alert.setContentText("Caixa Fechado com sucesso!");
                alert.showAndWait();
                clkCancelar(event);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Resposta do Servidor");
                alert.setHeaderText(null);
                alert.setContentText("Erro na hora de fechar o caixa!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void clkCancelar(ActionEvent event) throws IOException {
        txtData.setText("");
        txtFuncionario.setText("");
        txtTrocoInicial.setText("");
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    public void carregacb(){
        List<String> lista = new ArrayList<>();
        lista.add("Manhã");
        lista.add("Tarde");
        lista.add("Noite");
        ObservableList<String> colection = FXCollections.observableArrayList(lista);
        cbPeriodo.getItems().addAll(colection);
    } 
    
    
}

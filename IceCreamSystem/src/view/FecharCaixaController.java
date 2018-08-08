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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregacb();
        txtFuncionario.setText(Funcionario.getFuncLogado().getNome());
        MaskFieldUtil.dateField(txtData);
    }    

    @FXML
    private void clkFechar(ActionEvent event) throws IOException, ParseException, ControlException, EntidadeException {
        CaixaControl cc = new CaixaControl();
        Double troco = 0.0;
        java.sql.Date data = null;
        if(txtData.getText()!=null && !txtData.getText().isEmpty()){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            data = new java.sql.Date(format.parse(txtData.getText()).getTime());
        }
        if(txtTrocoInicial.getText()!=null && !txtTrocoInicial.getText().isEmpty()){
            troco = Double.parseDouble(txtTrocoInicial.getText());
        }
        int retorno = cc.fecharCaixa(Funcionario.getFuncLogado(), troco, data, cbPeriodo.getValue());
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

    @FXML
    private void clkCancelar(ActionEvent event) throws IOException {
        txtData.setText("");
        txtFuncionario.setText("");
        txtTrocoInicial.setText("");
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Menu Principal");
        stage.setResizable(false);
        stage.showAndWait();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import control.ContaPagarControl;
import control.TipoDespesasControl;
import control.TipoPagamentoControl;
import entidade.ContasPagar;
import entidade.TipoDespesas;
import entidade.TipoPagamento;
import exception.ControlException;
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
import javafx.stage.Stage;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class LancarContasPagarController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXComboBox<TipoDespesas> cbDespesas;
    @FXML
    private JFXTextField txtData;
    @FXML
    private JFXTextField txtValor;
    @FXML
    private JFXButton btnNovo;
    @FXML
    private JFXButton btnGravar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnLocalizar;
    @FXML
    private JFXButton btnExcluir;
    @FXML
    private JFXButton btnSair;
    @FXML
    private JFXComboBox<TipoPagamento> cbFormaPagamento;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaskFieldUtil.dateField(txtData);
        try {
                carregaDespesas();
                carregaTpPagamento();
            } catch (ControlException ex) {
                System.out.println(ex.getMessage());
            }
        if(ContasPagar.getCpSelecionada()==null){
            inicializa(true);
        }else{
            btnNovo.setDisable(true);
            txtCodigo.setText(ContasPagar.getCpSelecionada().getCodigo()+"");
            txtData.setText(ContasPagar.getCpSelecionada().getData().toString());
            txtValor.setText(ContasPagar.getCpSelecionada().getValor()+"");
            cbDespesas.setValue(ContasPagar.getCpSelecionada().getTpd());
            cbFormaPagamento.setValue(ContasPagar.getCpSelecionada().getTpp());
        }
    }    

    @FXML
    private void clkNovo(ActionEvent event) {
        inicializa(false);
    }

    @FXML
    private void clkGravar(ActionEvent event) throws ParseException, ControlException {
        ContaPagarControl cpc = new ContaPagarControl();
        int codigo = 0;
        double valor = 0.0;
        java.sql.Date data = null;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        if(txtData.getText()!=null && !txtData.getText().isEmpty()){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            data = new java.sql.Date(format.parse(txtData.getText()).getTime());
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Digite a data do Vencimento!");
            alert.showAndWait();
        }
        if(txtValor.getText()!=null && !txtValor.getText().isEmpty())
            valor = Double.parseDouble(txtValor.getText());
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Digite o valor da conta!");
            alert.showAndWait();
        }
        if(cbDespesas.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Selecione um tipo de despesa!");
            alert.showAndWait();
        }
        if(cbFormaPagamento.getValue()==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Selecione a forma de pagamento!");
            alert.showAndWait();
        }
        
        int result = cpc.lancaContapagar(codigo, data, valor, cbDespesas.getValue(), cbFormaPagamento.getValue());
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Despesa Lançada com Sucesso!");
            alert.showAndWait();
            clkCancelar(event);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Erro na hora lançar despesa!");
            alert.showAndWait();
        }
            
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
        limpatela();
        inicializa(true);
        if(ContasPagar.getCpSelecionada()!=null)
            ContasPagar.setCpSelecionada(new ContasPagar());
    }

    @FXML
    private void clkLocalizar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarContasPagar.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Localizar Conta a Pagar");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void clkExcluir(ActionEvent event) {
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Menu Principal");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }
    
    public void inicializa(boolean estado){
        txtData.setDisable(estado);
        txtValor.setDisable(estado);
        btnGravar.setDisable(estado);
        btnCancelar.setDisable(estado);
        btnExcluir.setDisable(estado);
        cbDespesas.setDisable(estado);
        cbFormaPagamento.setDisable(estado);
    }
    
    public void limpatela(){
        txtData.setText("");
        txtValor.setText("");
        cbDespesas.setValue(new TipoDespesas());
        txtCodigo.setText("");
        cbFormaPagamento.setValue(new TipoPagamento());
    }
    
    public void carregaDespesas() throws ControlException{
        TipoDespesasControl tdc = new TipoDespesasControl();
        List<TipoDespesas> lista = new ArrayList<>();
        lista = tdc.listaDespesas(0, "");
        ObservableList<TipoDespesas> colection = FXCollections.observableArrayList(lista);
        cbDespesas.getItems().addAll(colection);
    }
    
    public void carregaTpPagamento() throws ControlException{
        TipoPagamentoControl tpp = new TipoPagamentoControl();
        List<TipoPagamento> lista = new ArrayList<>();
        lista = tpp.listaTPagamento(0, "");
        ObservableList<TipoPagamento> colection = FXCollections.observableArrayList(lista);
        cbFormaPagamento.getItems().addAll(colection);
    }
}

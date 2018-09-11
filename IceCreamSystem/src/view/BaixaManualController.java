/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.ProdutoControl;
import entidade.Funcionario;
import entidade.Produto;
import exception.ControlException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
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
public class BaixaManualController implements Initializable {

    @FXML
    private JFXTextField txtFuncionario;
    @FXML
    private JFXTextField txtProduto;
    @FXML
    private JFXButton btnLocalizaP;
    @FXML
    private JFXTextField txtMotivo;
    @FXML
    private JFXTextField txtQtde;
    @FXML
    private JFXTextField txtData;
    @FXML
    private JFXButton btnNovo;
    @FXML
    private JFXButton btnGravar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MaskFieldUtil.dateField(txtData);
        txtFuncionario.setText(Funcionario.getFuncLogado().getNome());
        if(Produto.getProdSelecionado()!=null)
            txtProduto.setText(Produto.getProdSelecionado().getDescricao());
        else{
            inicializa(true);
        }
    }    

    @FXML
    private void clkLocaliza(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocProdutoBaixa.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Localizar Produto");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void clkNovo(ActionEvent event) {
        btnNovo.setDisable(true);
        inicializa(false);
    }

    @FXML
    private void clkGravar(ActionEvent event) throws ParseException, ControlException, SQLException {
        ProdutoControl pc = new ProdutoControl();
        int qtde = 0;
        java.sql.Date data = null;
        if(txtData.getText()!=null && !txtData.getText().isEmpty()){
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            data = new java.sql.Date(format.parse(txtData.getText()).getTime());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Escolha uma data!");
            alert.showAndWait();
        }
        if(txtQtde.getText()!=null && !txtQtde.getText().isEmpty())
            qtde = Integer.parseInt(txtQtde.getText());
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Selecione a Quantidade!");
            alert.showAndWait();
        }  
        if(txtMotivo.getText()==null || txtMotivo.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Diga um motivo!");
            alert.showAndWait();
        }
        if(txtProduto.getText()==null || txtProduto.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Escolha um produto!");
            alert.showAndWait();
        }
        int result = pc.darBaixa(Funcionario.getFuncLogado(), Produto.getProdSelecionado(), txtMotivo.getText(), qtde, data);
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Baixa realizada com sucesso!");
            alert.showAndWait();
            limpatela();
            inicializa(true);
        }else{
            if(result == 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Resposta do Servidor");
                alert.setHeaderText(null);
                alert.setContentText("Baixa realizada com sucesso! -- Produto esgotado no estoque!");
                alert.showAndWait();
                limpatela();
                inicializa(true);
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Resposta do Servidor");
                alert.setHeaderText(null);
                alert.setContentText("Erro na hora de gravar os dados!");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
        limpatela();
        
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
    
    public void inicializa(boolean estado){
        txtMotivo.setDisable(estado);
        txtQtde.setDisable(estado);
        txtData.setDisable(estado);
        txtProduto.setDisable(estado);
        btnGravar.setDisable(estado);
        btnCancelar.setDisable(estado);
    }
    
    public void limpatela(){
        txtData.setText("");
        txtMotivo.setText("");
        txtProduto.setText("");
        Produto.setProdSelecionado(new Produto());
        txtQtde.setText("");
    }
    
}

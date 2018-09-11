/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.LoteProdutoControl;
import exception.ControlException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class GerenciarLoteProdutoController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtDescricao;
    @FXML
    private JFXTextField txtNumeroLote;
    @FXML
    private DatePicker dpDataVencimento;
    @FXML
    private JFXTextField txtQtde;
    @FXML
    private JFXTextField txtRemanescente;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btGravar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btSair;
    @FXML
    private JFXButton btLocalizar;
    @FXML
    private JFXButton btExcluir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoteProdutoControl tpc = new LoteProdutoControl();
        if(tpc.retornaSelecionado()!=null){
            LocalDate ld = new java.sql.Date( tpc.retornaSelecionado().getValidade().getTime() ).toLocalDate();
            txtCodigo.setText(tpc.retornaSelecionado().getCodigo()+"");
            txtDescricao.setText(tpc.retornaSelecionado().getDescricao());
            txtNumeroLote.setText(tpc.retornaSelecionado().getNumeroLote());
            dpDataVencimento.setValue(ld);
            txtQtde.setText(tpc.retornaSelecionado().getQtdeCompra()+"");
            txtRemanescente.setText(tpc.retornaSelecionado().getQtdRemanescente()+"");
            inicializa(false);
        }
        else{   
            inicializa(true);
        }
        
    }    

    @FXML
    private void clkNovo(ActionEvent event) {
        inicializa(false);
    }

    @FXML
    private void clkGravar(ActionEvent event) throws ControlException {
        LoteProdutoControl lpc = new LoteProdutoControl();
        Integer codigo = 0;
        Integer qtde = 0, qtdr = 0;
        Date data = java.sql.Date.valueOf(dpDataVencimento.getValue());
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        if(txtQtde.getText()!=null && !txtQtde.getText().isEmpty())
            qtde = Integer.parseInt(txtQtde.getText());
        if(txtRemanescente.getText()!=null && !txtRemanescente.getText().isEmpty())
            qtdr = Integer.parseInt(txtRemanescente.getText());
        int result = lpc.gravaLote(codigo, txtDescricao.getText(), txtNumeroLote.getText(), data, qtde, qtdr);
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Lote de Produto gravado com sucesso!");
            alert.showAndWait();
            limpatela();
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
        limpatela();
        inicializa(true);
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
        Stage stage = (Stage) btSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clkLocalizar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarLoteProdutos.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btSair.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Localizar Lotes de Produtos");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void clkExcluir(ActionEvent event) throws ControlException {
        LoteProdutoControl lpc = new LoteProdutoControl();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        int result = lpc.excluiLote(codigo);
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Lote excluido com sucesso!");
            alert.showAndWait();
            limpatela();
            inicializa(true);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Erro na hora de excluir os dados!");
            alert.showAndWait();
        }
    }
    
    public void inicializa(boolean estado){
        txtCodigo.setDisable(estado);
        txtDescricao.setDisable(estado);
        txtNumeroLote.setDisable(estado);
        dpDataVencimento.setDisable(estado);
        txtQtde.setDisable(estado);
        txtRemanescente.setDisable(estado);
        btGravar.setDisable(estado);
        btNovo.setDisable(!estado);
        btCancelar.setDisable(estado);
        btExcluir.setDisable(estado);
    }
    
    public void limpatela(){
        txtCodigo.setText("");
        txtDescricao.setText("");
        txtNumeroLote.setText("");
        dpDataVencimento.setValue(null);
        txtQtde.setText("");
        txtRemanescente.setText("");
    }
    
}

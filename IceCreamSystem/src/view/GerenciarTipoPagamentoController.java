 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.MarcaControl;
import control.TipoPagamentoControl;
import entidade.TipoPagamento;
import exception.ControlException;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class GerenciarTipoPagamentoController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtDescricao;
    @FXML
    private TableView<TipoPagamento> tableaTipoPag;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btGravar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btSair;
    @FXML
    private JFXButton btExcluir;
    @FXML
    private TableColumn colDescricao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializa(true);
        try {
            carregaTabela();
        } catch (ControlException ex) {
            System.out.println("Erro na hora de carregar a tabela!");
        }
        tableaTipoPag.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
    }    

    @FXML
    private void selecionaTipoPagamento(MouseEvent event) {
        if (tableaTipoPag.getSelectionModel().getSelectedIndex() >= 0)
        {
            txtCodigo.setText(tableaTipoPag.getSelectionModel().getSelectedItem().getCodigo().toString());
            txtDescricao.setText(tableaTipoPag.getSelectionModel().getSelectedItem().getDescricao());
        }
    }

    @FXML
    private void clkNovo(ActionEvent event) {
        inicializa(false);
    }

    @FXML
    private void clkGravar(ActionEvent event) throws ControlException {
        TipoPagamentoControl tpc = new TipoPagamentoControl();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        int result = tpc.gravaTPagamento(codigo, txtDescricao.getText());
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Tipo de pagamento gravado com sucesso!");
            alert.showAndWait();
            limpatela();
            inicializa(true);
            carregaTabela();
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
    private void clkExcluir(ActionEvent event) throws ControlException {
        TipoPagamentoControl tpc = new TipoPagamentoControl();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        int result = tpc.excluiTPagamento(codigo, txtDescricao.getText());
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Tipo de pagamento excluido com sucesso!");
            alert.showAndWait();
            limpatela();
            inicializa(true);
            carregaTabela();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Erro na hora de excluir os dados!");
            alert.showAndWait();
        }
    }
    
    public void inicializa(boolean estado){
        txtDescricao.setDisable(estado);
        btCancelar.setDisable(estado);
        btGravar.setDisable(estado);
        btExcluir.setDisable(estado);
        btNovo.setDisable(!estado);
        tableaTipoPag.setDisable(estado);
        txtCodigo.setDisable(estado);
    }
    
    public void limpatela(){
        txtDescricao.setText("");
        txtCodigo.setText("");
    }
    
    public void carregaTabela() throws ControlException{
        TipoPagamentoControl tpc = new TipoPagamentoControl();
        List<TipoPagamento> lista = new ArrayList<>();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        lista = tpc.listaTPagamento(codigo, txtDescricao.getText());
        if(lista!=null){
            ObservableList<TipoPagamento> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tableaTipoPag.setItems(modelo);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.TipoDespesasControl;
import entidade.TipoDespesas;
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
public class GerenciarTipoDespesasController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtDescricao;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colMarca;
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
    private TableView<TipoDespesas> tableaTipoDespesas;

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
        tableaTipoDespesas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colMarca.setCellValueFactory(new PropertyValueFactory("descricao"));
    }    

    @FXML
    private void selecionaTipoDespesas(MouseEvent event) {
        if (tableaTipoDespesas.getSelectionModel().getSelectedIndex() >= 0)
        {
            txtCodigo.setText(tableaTipoDespesas.getSelectionModel().getSelectedItem().getCodigo().toString());
            txtDescricao.setText(tableaTipoDespesas.getSelectionModel().getSelectedItem().getDescricao());
        }
    }

    @FXML
    private void clkNovo(ActionEvent event) {
        inicializa(false);
    }

    @FXML
    private void clkGravar(ActionEvent event) throws ControlException {
        TipoDespesasControl tpc = new TipoDespesasControl();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        int result = tpc.gravaTipoDespesas(codigo, txtDescricao.getText());
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Tipo de despesas gravada com sucesso!");
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
        TipoDespesasControl tpc = new TipoDespesasControl();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        int result = tpc.excluiTipoDespesas(codigo, txtDescricao.getText());
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Tipo de Despesa excluida com sucesso!");
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
        tableaTipoDespesas.setDisable(estado);
        txtCodigo.setDisable(estado);
    }
    
    public void limpatela(){
        txtDescricao.setText("");
        txtCodigo.setText("");
    }
    
    public void carregaTabela() throws ControlException{
        TipoDespesasControl tpc = new TipoDespesasControl();
        List<TipoDespesas> lista = new ArrayList<>();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        lista = tpc.listaDespesas(codigo, txtDescricao.getText());
        if(lista!=null){
            ObservableList<TipoDespesas> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tableaTipoDespesas.setItems(modelo);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.UnidadeMedidaControl;
import entidade.UnidadeMedida;
import exception.ControlException;
import java.io.IOException;
import java.net.URL;
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
public class GerenciarUnidadeMedidaController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtDescricao;
    @FXML
    private JFXTextField txtAbreviacao;
    @FXML
    private TableView<UnidadeMedida> tabelaUnidadeMedida;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colDescricao;
    @FXML
    private TableColumn colAbreviacao;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializa(true);
        try {
            carregaTabela();
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
        tabelaUnidadeMedida.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        colAbreviacao.setCellValueFactory(new PropertyValueFactory("abreviacao"));
    }    

    @FXML
    private void selecionarUnidadeMedida(MouseEvent event) {
        if (tabelaUnidadeMedida.getSelectionModel().getSelectedIndex() >= 0)
        {
            txtCodigo.setText(tabelaUnidadeMedida.getSelectionModel().getSelectedItem().getCodigo().toString());
            txtDescricao.setText(tabelaUnidadeMedida.getSelectionModel().getSelectedItem().getDescricao());
            txtAbreviacao.setText(tabelaUnidadeMedida.getSelectionModel().getSelectedItem().getAbreviacao());
        }
    }

    @FXML
    private void clkNovo(ActionEvent event) {
        inicializa(false);
    }

    @FXML
    private void clkGravar(ActionEvent event) throws ControlException {
        UnidadeMedidaControl umc = new UnidadeMedidaControl();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        int result = umc.gravaUM(codigo, txtDescricao.getText(), txtAbreviacao.getText());
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Unidade de Medida gravada com sucesso!");
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
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btSair.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Menu Principal");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void clkExcluir(ActionEvent event) throws ControlException {
        UnidadeMedidaControl umc = new UnidadeMedidaControl();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        int result = umc.excluiUM(codigo, txtDescricao.getText(), txtAbreviacao.getText());
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Marca excluida com sucesso!");
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
        txtAbreviacao.setDisable(estado);
        btCancelar.setDisable(estado);
        btGravar.setDisable(estado);
        btExcluir.setDisable(estado);
        btNovo.setDisable(!estado);
        tabelaUnidadeMedida.setDisable(estado);
        txtCodigo.setDisable(estado);
    }
    
    public void limpatela(){
        txtDescricao.setText("");
        txtAbreviacao.setText("");
        txtCodigo.setText("");
    }
    
    public void carregaTabela() throws ControlException{
        UnidadeMedidaControl umc = new UnidadeMedidaControl();
        List<UnidadeMedida> lista = new ArrayList<>();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        lista = umc.listaUM(codigo, txtDescricao.getText(), txtAbreviacao.getText());
        if(lista!=null){
            ObservableList<UnidadeMedida> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabelaUnidadeMedida.setItems(modelo);
        }
    }
}

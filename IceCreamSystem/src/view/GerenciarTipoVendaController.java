/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.MarcaControl;
import control.TipoVendaControl;
import entidade.Marca;
import entidade.TipoVenda;
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
public class GerenciarTipoVendaController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtDescricao;
    @FXML
    private TableView<TipoVenda> tabelaTipoVenda;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colTipoVenda;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btGravar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btExcluir;
    @FXML
    private JFXButton btSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializa(true);
        try {
            carregaTabela();
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
        tabelaTipoVenda.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colTipoVenda.setCellValueFactory(new PropertyValueFactory("descricao"));
    }    

    @FXML
    private void selecionarTipoVenda(MouseEvent event) {
        if (tabelaTipoVenda.getSelectionModel().getSelectedIndex() >= 0)
        {
            txtCodigo.setText(tabelaTipoVenda.getSelectionModel().getSelectedItem().getCodigo().toString());
            txtDescricao.setText(tabelaTipoVenda.getSelectionModel().getSelectedItem().getDescricao());
        }
    }

    @FXML
    private void clkNovo(ActionEvent event) {
        inicializa(false);
    }

    @FXML
    private void clkGravar(ActionEvent event) throws ControlException {
        TipoVendaControl tvc = new TipoVendaControl();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        int result = tvc.gravaTVenda(codigo, txtDescricao.getText());
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Tipo Venda gravado com sucesso!");
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
    private void clkExcluir(ActionEvent event) throws ControlException {
        TipoVendaControl tvc = new TipoVendaControl();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        int result = tvc.excluiTVenda(codigo, txtDescricao.getText());
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Tipo Venda excluido com sucesso!");
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
        txtDescricao.setDisable(estado);
        btCancelar.setDisable(estado);
        btGravar.setDisable(estado);
        btExcluir.setDisable(estado);
        btNovo.setDisable(!estado);
        tabelaTipoVenda.setDisable(estado);
        txtCodigo.setDisable(estado);
    }
    
    public void limpatela(){
        txtDescricao.setText("");
        txtCodigo.setText("");
    }
    
    public void carregaTabela() throws ControlException{
        TipoVendaControl tvc = new TipoVendaControl();
        List<TipoVenda> lista = new ArrayList<>();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        lista = tvc.listaTVenda(codigo, txtDescricao.getText());
        if(lista!=null){
            ObservableList<TipoVenda> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabelaTipoVenda.setItems(modelo);
        }
    }
    
}

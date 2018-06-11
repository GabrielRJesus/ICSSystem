/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.PessoaControl;
import entidade.Fornecedor;
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
public class LocalizarFornecedorController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtCnpj;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private TableView<Fornecedor> tabFornecedor;
    @FXML
    private TableColumn colNome;
    @FXML
    private TableColumn colCnpj;
    @FXML
    private TableColumn colIe;
    @FXML
    private TableColumn colInicio;
    @FXML
    private JFXButton btSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tabFornecedor.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colCnpj.setCellValueFactory(new PropertyValueFactory("cnpj"));
        colIe.setCellValueFactory(new PropertyValueFactory("ie"));
        colInicio.setCellValueFactory(new PropertyValueFactory("inicioAtiv"));
    }    

    @FXML
    private void clkPesquisar(ActionEvent event) throws ControlException {
        carregaTabela();
    }

    @FXML
    private void selecionaFornecedor(MouseEvent event) throws IOException {
        if (tabFornecedor.getSelectionModel().getSelectedIndex() >= 0){
            PessoaControl pc = new PessoaControl();
            pc.guardaBusca(tabFornecedor.getSelectionModel().getSelectedItem());
            Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarFornecedor.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) btSair.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Gerenciar Fornecedor");
            stage.setResizable(false);
            stage.showAndWait();
            stage.close();
        }
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarFornecedor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btSair.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Fornecedor");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }
    
    public void carregaTabela() throws ControlException{
        PessoaControl pc = new PessoaControl();
        List<Fornecedor> lista = new ArrayList<>();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        lista = pc.buscaFornecedor(codigo, txtNome.getText(), txtCnpj.getText());
        if(lista!=null){
            ObservableList<Fornecedor> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabFornecedor.setItems(modelo);
        }
    }
    
}

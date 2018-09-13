/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.PessoaControl;
import control.ProdutoControl;
import entidade.Fornecedor;
import entidade.Produto;
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
public class LocalzarProdutoController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtDescricao;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private TableView<Produto> tabProdutos;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colDescricao;
    @FXML
    private TableColumn colEstoque;
    @FXML
    private TableColumn colPreco;
    @FXML
    private JFXButton btSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tabProdutos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        colEstoque.setCellValueFactory(new PropertyValueFactory("qtdeEstoque"));
        colPreco.setCellValueFactory(new PropertyValueFactory("preco"));
    }    

    @FXML
    private void clkPesquisar(ActionEvent event) throws ControlException {
        carregaTabela();
    }

    @FXML
    private void selecionaProduto(MouseEvent event) throws IOException {
        if (tabProdutos.getSelectionModel().getSelectedIndex() >= 0){
            ProdutoControl pc = new ProdutoControl();
            pc.guardaSelecionado(tabProdutos.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) btSair.getScene().getWindow();
            stage.showAndWait();
        }
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
            Stage stage = (Stage) btSair.getScene().getWindow();
            stage.close();
    }
    
    public void carregaTabela() throws ControlException{
        ProdutoControl pc = new ProdutoControl();
        List<Produto> lista = new ArrayList<>();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        lista = pc.listaProdutos(codigo, txtDescricao.getText());
        if(lista!=null){
            ObservableList<Produto> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabProdutos.setItems(modelo);
        }
    }
    
}

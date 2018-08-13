/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.ProdutoControl;
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
public class LocProdutoBaixaController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtDescrição;
    @FXML
    private JFXButton btnLocalizar;
    @FXML
    private TableView<Produto> tabelaProdutos;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colDescricao;
    @FXML
    private TableColumn colEstoque;
    @FXML
    private JFXButton btnSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tabelaProdutos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        colEstoque.setCellValueFactory(new PropertyValueFactory("qtdeEstoque"));
    }    

    @FXML
    private void clkLocaliza(ActionEvent event) throws ControlException {
        carregaTabela();
    }

    @FXML
    private void clkSeleciona(MouseEvent event) throws IOException {
        if (tabelaProdutos.getSelectionModel().getSelectedIndex() >= 0){
            ProdutoControl pc = new ProdutoControl();
            pc.guardaSelecionado(tabelaProdutos.getSelectionModel().getSelectedItem());
            Parent root = FXMLLoader.load(getClass().getResource("/view/BaixaManual.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Baixa Manual");
            stage.setResizable(false);
            stage.showAndWait();
            stage.close();
        }
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/BaixaManual.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Baixa Manual");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }
    
    public void carregaTabela() throws ControlException{
        ProdutoControl pc = new ProdutoControl();
        List<Produto> lista = new ArrayList<>();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        lista = pc.listaProdutos(codigo, txtDescrição.getText());
        if(lista!=null){
            ObservableList<Produto> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabelaProdutos.setItems(modelo);
        }
    }
    
}

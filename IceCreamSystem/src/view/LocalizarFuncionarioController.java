/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.PessoaControl;
import entidade.Funcionario;
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
public class LocalizarFuncionarioController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtCPF;
    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXTextField txtCargo;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private TableView<Funcionario> tabFuncionario;
    @FXML
    private TableColumn colNome;
    @FXML
    private TableColumn colLogin;
    @FXML
    private TableColumn colCargo;
    @FXML
    private TableColumn colDtAdmissão;
    @FXML
    private JFXButton btSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tabFuncionario.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colLogin.setCellValueFactory(new PropertyValueFactory("login"));
        colCargo.setCellValueFactory(new PropertyValueFactory("cargo"));
        colDtAdmissão.setCellValueFactory(new PropertyValueFactory("dtAdmis"));
    }    

    @FXML
    private void clkPesquisar(ActionEvent event) throws ControlException {
        carregaTabela();
        
    }

    @FXML
    private void selecionaFuncionario(MouseEvent event) throws IOException {
        if (tabFuncionario.getSelectionModel().getSelectedIndex() >= 0){
            PessoaControl pc = new PessoaControl();
            pc.guardaBusca(tabFuncionario.getSelectionModel().getSelectedItem());
            Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarFuncionario.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) btSair.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Gerenciar Funcionario");
            stage.setResizable(false);
            stage.showAndWait();
            stage.close();
        }
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarFuncionario.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btSair.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Funcionario");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }
    
    public void carregaTabela() throws ControlException{
        PessoaControl pc = new PessoaControl();
        List<Funcionario> lista = new ArrayList<>();
        Integer codigo = 0;
        Integer nivel = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        if(txtCargo.getText()!=null && !txtCargo.getText().isEmpty())
            nivel = Integer.parseInt(txtCargo.getText());
        lista = pc.buscaFuncionario(codigo, txtNome.getText(), txtCPF.getText(), txtLogin.getText(), txtCargo.getText(), nivel);
        if(lista!=null){
            ObservableList<Funcionario> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabFuncionario.setItems(modelo);
        }
    }
}

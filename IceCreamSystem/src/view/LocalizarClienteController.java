/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.PessoaControl;
import entidade.Cliente;
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
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class LocalizarClienteController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtCpf;
    @FXML
    private TableView<Cliente> tabCliente;
    @FXML
    private TableColumn colNome;
    @FXML
    private TableColumn colCPF;
    @FXML
    private TableColumn colSexo;
    @FXML
    private TableColumn colTelefone;
    @FXML
    private JFXButton btPesquisar;
    @FXML
    private JFXButton btSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MaskFieldUtil.onlyDigitsValue(txtCodigo);
        MaskFieldUtil.cpfField(txtCpf);
        tabCliente.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colCPF.setCellValueFactory(new PropertyValueFactory("cpf"));
        colSexo.setCellValueFactory(new PropertyValueFactory("sexo"));
        colTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
    }    

    @FXML
    private void selectCliente(MouseEvent event) throws IOException {
        if (tabCliente.getSelectionModel().getSelectedIndex() >= 0){
            PessoaControl pc = new PessoaControl();
            pc.guardaBusca(tabCliente.getSelectionModel().getSelectedItem());
            Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarCliente.fxml"));
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
    private void clkPesquisar(ActionEvent event) throws ControlException {
        carregaTabela();
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
        Stage stage = (Stage) btSair.getScene().getWindow();
        stage.close();
    }
    
    public void carregaTabela() throws ControlException{
        PessoaControl pc = new PessoaControl();
        List<Cliente> lista = new ArrayList<>();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        lista = pc.buscaCliente(codigo, txtNome.getText(), txtCpf.getText());
        if(lista!=null){
            ObservableList<Cliente> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabCliente.setItems(modelo);
        }
    }
    
}

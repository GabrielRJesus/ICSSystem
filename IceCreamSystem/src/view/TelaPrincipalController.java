/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class TelaPrincipalController implements Initializable {

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuGerenciar;
    @FXML
    private MenuItem gerenciarCliente;
    @FXML
    private MenuItem gerenciarFuncionario;
    @FXML
    private MenuItem gerenciarFornecedor;
    @FXML
    private MenuItem gerenciarProduto;
    @FXML
    private MenuItem gerenciarMarcas;
    @FXML
    private MenuItem gerenciarLotes;
    @FXML
    private MenuItem gerenciarTipos;
    @FXML
    private MenuItem gerenciarUnidades;
    @FXML
    private MenuItem gerenciarCategorias;
    @FXML
    private MenuItem gerenciarModalidades;
    @FXML
    private MenuItem gerenciarPagamentos;
    @FXML
    private Menu menuLocalizar;
    @FXML
    private Menu menuFuncoes;
    @FXML
    private Menu menuRelatorios;
    @FXML
    private MenuItem menuSair;
    @FXML
    private JFXButton btNovaComanda;
    @FXML
    private MenuItem locCliente;
    @FXML
    private MenuItem locFuncionario;
    @FXML
    private MenuItem locFornecedor;
    @FXML
    private MenuItem locProduto;
    @FXML
    private MenuItem locLoteProduto;
    @FXML
    private MenuItem dadosEmpresa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gerCliente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarCliente.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Cliente");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void gerFuncionario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarFuncionario.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Funcionario");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void gerFornecedor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarFornecedor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Fornecedor");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void sair(ActionEvent event) {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void gerProduto(ActionEvent event) {
    }

    @FXML
    private void gerMarca(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarMarca.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Marcas");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void gerLote(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarLoteProduto.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Tipos de Despesas");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void gerTipoDespesas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarTipoDespesas.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Tipos de Despesas");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void gerUnidadeMedida(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarUnidadeMedida.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Unidade de Medida");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void gerTipoVenda(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarTipoVenda.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Tipo de Venda");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void gerTipoProduto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarTipoProduto.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Categoria do Produto");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void gerTipoPagamento(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarTipoPagamento.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Tipo de Pagamento");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void locCliente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarCliente.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Localizar Funcionário");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void locFuncionario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarFuncionario.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Localizar Funcionário");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void locFornecedor(ActionEvent event) {
    }

    @FXML
    private void locProduto(ActionEvent event) {
    }

    @FXML
    private void locLoteProduto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarLoteProdutos.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Localizar Lotes de Produto");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void dadosEmpresa(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/DadosEmpresa.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dados da Empresa");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }
    
}

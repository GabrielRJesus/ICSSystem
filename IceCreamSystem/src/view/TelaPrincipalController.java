/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import control.CaixaControl;
import control.PessoaControl;
import entidade.Caixa;
import exception.ControlException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private MenuItem dadosEmpresa;
    @FXML
    private MenuItem abrirCaixa;
    @FXML
    private MenuItem fecharCaixa;
    @FXML
    private MenuItem realizarCompra;
    @FXML
    private MenuItem realizarVenda;
    @FXML
    private MenuItem lancarContasPagar;
    @FXML
    private MenuItem quitarContasPagar;
    @FXML
    private MenuItem quitarContasReceber;
    @FXML
    private MenuItem baixaManual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PessoaControl pc = new PessoaControl();
        if(pc.retornaSelecionado()!=null){
            if(pc.retornaSelecionado().getCargo().equalsIgnoreCase("Funcionario")){
                gerenciarFuncionario.setVisible(false);
                gerenciarModalidades.setVisible(false);
                gerenciarPagamentos.setVisible(false);
                gerenciarFornecedor.setVisible(false);
                locFornecedor.setVisible(false);
                locFuncionario.setVisible(false);
            }
        }
    }    

    @FXML
    private void gerCliente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarCliente.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gerenciar Cliente");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void gerFuncionario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarFuncionario.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gerenciar Funcionario");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void gerFornecedor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarFornecedor.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gerenciar Fornecedor");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void sair(ActionEvent event) {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void gerProduto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarProduto.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Produto");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void gerMarca(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarMarca.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Marcas");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void gerTipoDespesas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarTipoDespesas.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Tipos de Despesas");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void gerUnidadeMedida(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarUnidadeMedida.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Unidade de Medida");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void gerTipoVenda(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarTipoVenda.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Tipo de Venda");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void gerTipoProduto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarTipoProduto.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Categoria do Produto");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void gerTipoPagamento(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarTipoPagamento.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Tipo de Pagamento");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void locCliente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarCliente.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Localizar Funcionário");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void locFuncionario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarFuncionario.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Localizar Funcionário");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void locFornecedor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarFornecedor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Localizar Fornecedor");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void locProduto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalzarProduto.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Localizar Produto");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void dadosEmpresa(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/DadosEmpresa.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Dados da Empresa");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void clkAbrirCaixa(ActionEvent event) throws IOException, ControlException {
        CaixaControl cc = new CaixaControl();
        if(cc.retornaCaixaAberto()==null){
            Parent root = FXMLLoader.load(getClass().getResource("/view/AbrirCaixa.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Abrir Caixa");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Ja existe um caixa aberto!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clkFecharCaixa(ActionEvent event) throws IOException, ControlException {
        CaixaControl cc = new CaixaControl();
        if(cc.retornaCaixaAberto()!=null){
            Parent root = FXMLLoader.load(getClass().getResource("/view/FecharCaixa.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Fechar Caixa");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Não existe nenhum caixa aberto!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clkRealizarCompra(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/RealizarCompra.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Realizar Compra");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setX(320);
        stage.setY(5);
        stage.showAndWait();
    }

    @FXML
    private void clkRealizarVenda(ActionEvent event) {
    }

    @FXML
    private void clkLancarContas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LancarContasPagar.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Lançar Contas à Pagar");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void clkQuitarContasPagar(ActionEvent event) {
    }

    @FXML
    private void clkQuitarContasReceber(ActionEvent event) {
    }

    @FXML
    private void clkBaixaManual(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/BaixaManual.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Baixa Manual Estoque");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    

    
}

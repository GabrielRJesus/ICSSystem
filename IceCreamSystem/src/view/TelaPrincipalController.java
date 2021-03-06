/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import control.CaixaControl;
import control.ContaPagarControl;
import control.PessoaControl;
import control.ProdutoControl;
import exception.ControlException;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import sql.Banco;

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
    @FXML
    private ImageView panePrincipal;
    @FXML
    private MenuItem mnValorCaixa;
    @FXML
    private JFXButton btnEstoque;
    @FXML
    private JFXButton btnContas;
    
    private ContaPagarControl cpc = new ContaPagarControl();
    private ProdutoControl pcc = new ProdutoControl();
    @FXML
    private MenuItem relroduto;
    @FXML
    private MenuItem relDespesas;
    @FXML
    private MenuItem relVendas;
    @FXML
    private MenuItem relEntregas;
    @FXML
    private MenuItem relFornecedor;
    @FXML
    private MenuItem relClientes;
    @FXML
    private MenuItem relLucros;
    @FXML
    private MenuItem relCompras;
    @FXML
    private MenuItem menuAjuda;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PessoaControl pc = new PessoaControl();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
        if(pc.retornaSelecionado()!=null){
            if(pc.retornaSelecionado().getCargo().equalsIgnoreCase("Funcionario")){
                gerenciarFuncionario.setVisible(false);
                gerenciarModalidades.setVisible(false);
                gerenciarPagamentos.setVisible(false);
                gerenciarFornecedor.setVisible(false);
                locFornecedor.setVisible(false);
                locFuncionario.setVisible(false);
                realizarCompra.setVisible(false);
                lancarContasPagar.setVisible(false);
                quitarContasPagar.setVisible(false);
                menuRelatorios.setVisible(false);
            }
        }
        try {
            if(cpc.listaVencendo().size()>0){
                btnContas.setVisible(true);
                btnContas.setStyle("-fx-background-color:Red");
            }
            if(pcc.listaFaltas().size()>0){
                btnEstoque.setVisible(true);
                btnEstoque.setStyle("-fx-background-color:Red");
            }
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
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
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void gerFuncionario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarFuncionario.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gerenciar Funcionario");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
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
        stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
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
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void gerMarca(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarMarca.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Marcas");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void gerTipoDespesas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarTipoDespesas.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Tipos de Despesas");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void gerUnidadeMedida(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarUnidadeMedida.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Unidade de Medida");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void gerTipoVenda(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarTipoVenda.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Tipo de Venda");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void gerTipoProduto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarTipoProduto.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Categoria do Produto");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void gerTipoPagamento(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarTipoPagamento.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gerenciar Tipo de Pagamento");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void locCliente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarCliente.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Localizar Funcionário");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void locFuncionario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarFuncionario.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Localizar Funcionário");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void locFornecedor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarFornecedor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Localizar Fornecedor");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void locProduto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalzarProduto.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Localizar Produto");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void dadosEmpresa(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/DadosEmpresa.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Dados da Empresa");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
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
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.showAndWait();
            btnContas.setVisible(false);
            btnEstoque.setVisible(false);
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
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.showAndWait();
            btnContas.setVisible(false);
            btnEstoque.setVisible(false);
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
        stage.setY(35);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void clkRealizarVenda(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/RealizarVenda.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Realizar Venda");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setX(320);
        stage.setY(40);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void clkLancarContas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LancarContasPagar.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Lançar Contas à Pagar");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void clkQuitarContasPagar(ActionEvent event) throws IOException, ControlException {
        CaixaControl cc = new CaixaControl();
        if(cc.retornaCaixaAberto()!=null){
            Parent root = FXMLLoader.load(getClass().getResource("/view/QuitarContasPagar.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Quitar Contas a Pagar");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.showAndWait();
            btnContas.setVisible(false);
            btnEstoque.setVisible(false);
        }else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Nenhum caixa Aberto!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clkQuitarContasReceber(ActionEvent event) throws ControlException, IOException {
        CaixaControl cc = new CaixaControl();
        if(cc.retornaCaixaAberto()!=null){
            Parent root = FXMLLoader.load(getClass().getResource("/view/QuitarContasReceber.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Quitar Contas a Receber");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.showAndWait();
            btnContas.setVisible(false);
            btnEstoque.setVisible(false);
        }else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Nenhum caixa Aberto!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clkBaixaManual(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/BaixaManual.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Baixa Manual Estoque");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void clkNovaVenda(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/RealizarVenda.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Realizar Venda");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setX(320);
        stage.setY(40);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void relProduto(ActionEvent event) throws SQLException, IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/RelatorioProdutos.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Relatorio Produtos");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    private void gerarRelatorio(String sql,String relat) throws SQLException{
    try
    { //sql para obter os dados para o relatorio
        Banco conSing = Banco.getInstancia();
       Connection con = conSing.getConexao();
        Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(sql);
      //implementação da interface JRDataSource para DataSource ResultSet
      JRResultSetDataSource jrRS;
      jrRS= new JRResultSetDataSource(rs);
      //chamando o relatório
      String jasperPrint =          
      JasperFillManager.fillReportToFile(relat,null, jrRS);
      JasperViewer viewer = new JasperViewer(jasperPrint, false, false);
      viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);//maximizado
      viewer.setTitle("Relatório de Alunos");//titulo do relatório
      viewer.setVisible(true);
    } catch (JRException erro)
    {  erro.printStackTrace(); }

   }

    @FXML
    private void clkValorCaixa(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocValorCaixa.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Valor no Caixa");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void clkEstoque(ActionEvent event) throws SQLException {
        String sql = "SELECT\n" +
        "     produto.`prod_codigo` AS produto_prod_codigo,\n" +
        "     produto.`prod_descricao` AS produto_prod_descricao,\n" +
        "     produto.`prod_qtdeEmbalagem` AS produto_prod_qtdeEmbalagem,\n" +
        "     produto.`prod_preco` AS produto_prod_preco,\n" +
        "     produto.`prod_estoque` AS produto_prod_estoque,\n" +
        "     unidade_medida.`um_sigla` AS unidade_medida_um_sigla\n" +
        "FROM\n" +
        "     `unidade_medida` unidade_medida INNER JOIN `produto` produto ON unidade_medida.`um_codigo` = produto.`um_codigo` where produto.prod_qtdemin > 0 and produto.prod_estoque < produto.prod_qtdemin";
        
        gerarRelatorio(sql, "Relatorios//Produtos.jasper");
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void clkContas(ActionEvent event) throws SQLException {
        String sql = "SELECT\n" +
        "     contas_pagar.`con_codigo` AS contas_pagar_con_codigo,\n" +
        "     contas_pagar.`con_data` AS contas_pagar_con_data,\n" +
        "     contas_pagar.`con_valor` AS contas_pagar_con_valor,\n" +
        "     contas_pagar.`com_codigo` AS contas_pagar_com_codigo,\n" +
        "     tipo_conta.`tpc_descricao` AS tipo_conta_tpc_descricao\n" +
        "FROM\n" +
        "     `tipo_conta` tipo_conta INNER JOIN `contas_pagar` contas_pagar ON tipo_conta.`tpc_codigo` = contas_pagar.`tpc_codigo`";
        gerarRelatorio(sql, "Relatorios//Despesas.jasper");
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void relDespesas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/RelatorioDespesas.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Relatorio Despesas");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void relVendas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/RelatorioVendas.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Relatorio Vendas");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void relEntregas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/RelatorioEntregas.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Relatorio Entregas");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void relFornecedor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/RelatorioFornecedor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Relatorio Fornecedor");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void relClientes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/RelatorioClientes.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Relatorio Clientes");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void relLucros(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/RelatorioLucros.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Relatorio Lucros");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void relCompras(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/RelatorioCompras.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Relatorio Compras");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        btnContas.setVisible(false);
        btnEstoque.setVisible(false);
    }

    @FXML
    private void clkAjuda(ActionEvent event) throws IOException {
        Desktop.getDesktop().open((new File("Ice Cream System.pdf")));
    }

    
}

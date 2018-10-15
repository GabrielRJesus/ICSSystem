/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import control.CompraControl;
import control.ContaPagarControl;
import control.TipoPagamentoControl;
import entidade.Compra;
import entidade.ContasPagar;
import entidade.Funcionario;
import entidade.TPaagamentoPagar;
import entidade.TabelaQPagamento;
import entidade.TipoDespesas;
import entidade.TipoPagamento;
import exception.ControlException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class QuitarContasPagarController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtData;
    @FXML
    private JFXButton btnPesquisa;
    @FXML
    private JFXTextField txtFornecedor;
    @FXML
    private JFXTextField txtFuncionario;
    @FXML
    private TableView<TabelaQPagamento> tabProdutos;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colDescricao;
    @FXML
    private TableColumn colQtde;
    @FXML
    private TableColumn colUnimed;
    @FXML
    private TableColumn colPrecoUnit;
    @FXML
    private TableColumn colTotal;
    @FXML
    private JFXTextField txtAcrescimo;
    @FXML
    private JFXTextField txtDesconto;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private JFXComboBox<TipoPagamento> cbFormaPag;
    @FXML
    private JFXTextField txtValorPag;
    @FXML
    private JFXButton btnIncluir;
    @FXML
    private JFXButton btnExcluir;
    @FXML
    private TableView<TPaagamentoPagar> tabFormaPag;
    @FXML
    private TableColumn<TipoDespesas, String> colFormaPag;
    @FXML
    private TableColumn colValor;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnFinalizar;
    @FXML
    private JFXTextField txtRestante;
    
    private List<TabelaQPagamento> lista = new ArrayList<>();
    private List<TPaagamentoPagar> listap = new ArrayList<>();
    private ContaPagarControl cpc = new ContaPagarControl();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //ContasPagar.setCpSelecionada(new ContasPagar());
        MaskFieldUtil.monetaryField(txtAcrescimo);
        MaskFieldUtil.monetaryField(txtDesconto);
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        String str = fmt.format(new Date());
        txtData.setText(str);
        txtFuncionario.setText(Funcionario.getFuncLogado().getNome());
        tabProdutos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        colQtde.setCellValueFactory(new PropertyValueFactory("qtde"));
        colUnimed.setCellValueFactory(new PropertyValueFactory("unimed"));
        colPrecoUnit.setCellValueFactory(new PropertyValueFactory("precounit"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        try {
            carregaPagamentos();
        } catch (ControlException ex) {
            Logger.getLogger(QuitarContasPagarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabFormaPag.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colValor.setCellValueFactory(new PropertyValueFactory("valor"));
        colFormaPag.setCellValueFactory(new PropertyValueFactory("tpg"));
        
        if(ContasPagar.cpSelecionada!=null && ContasPagar.cpSelecionada.getCodigo()!=0){
            txtCodigo.setText(ContasPagar.cpSelecionada.getCodigo()+"");
            txtTotal.setText(ContasPagar.cpSelecionada.getValor()+"");
            txtRestante.setText(ContasPagar.cpSelecionada.getValor()+"");
            if(ContasPagar.cpSelecionada.getCompra()!=null && ContasPagar.cpSelecionada.getCompra().getCodigo()!=0){
                try {
                    preencheFornecedor(ContasPagar.cpSelecionada.getCompra().getCodigo());
                } catch (ControlException ex) {
                    Logger.getLogger(QuitarContasPagarController.class.getName()).log(Level.SEVERE, null, ex);
                }
                carregaTabela(ContasPagar.cpSelecionada.getCompra());
            }
        }
        
    }    

    @FXML
    private void clkPesquisa(ActionEvent event) throws IOException, ControlException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarContasPagar.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Localizar Contas a Pagar");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setX(330);
        stage.setY(40);
        stage.setResizable(false);
        stage.showAndWait();
        txtCodigo.setText(ContasPagar.cpSelecionada.getCodigo()+"");
        txtTotal.setText(ContasPagar.cpSelecionada.getValor()+"");
        txtRestante.setText(ContasPagar.cpSelecionada.getValor()+"");
        if(ContasPagar.cpSelecionada.getCompra()!=null && ContasPagar.cpSelecionada.getCompra().getCodigo()!=0){
            preencheFornecedor(ContasPagar.cpSelecionada.getCompra().getCodigo());
            carregaTabela(ContasPagar.cpSelecionada.getCompra());
        }
    }
    
    private void preencheFornecedor(int codigo) throws ControlException{
        CompraControl cc = new CompraControl();
        Compra c = cc.selecionaCompra(codigo);
        txtFornecedor.setText(c.getForn().getNome());
    }

    @FXML
    private void clkIncluir(ActionEvent event) {
        TPaagamentoPagar tp = new TPaagamentoPagar();
        tp.setTpg(cbFormaPag.getValue());
        String texto = txtValorPag.getText().replace(",", ".");
        double valor = Double.parseDouble(texto);
        tp.setValor(valor);
        if(valor <= Double.parseDouble(txtRestante.getText())){
            listap.add(tp);
            carregaTPagamento();
            valores();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Valor maior que o restante!");
            alert.showAndWait();
        }
    }
    
    private void valores(){
        double tot = ContasPagar.cpSelecionada.getValor();
        for(int i =0; i<listap.size();i++){
            tot -= listap.get(i).getValor();
        }
        txtRestante.setText(tot+"");
        if(tot == 0)
            btnIncluir.setDisable(true);
        else
            btnIncluir.setDisable(false);
        if(tot == ContasPagar.cpSelecionada.getValor())
            btnExcluir.setDisable(true);
        else
            btnExcluir.setDisable(false);
    }
    
    private void carregaTPagamento(){
        if(listap!=null){
            ObservableList<TPaagamentoPagar> modelo;
            modelo = FXCollections.observableArrayList(listap);
            tabFormaPag.setItems(modelo);
        }
    }
    
    @FXML
    private void clkExcluir(ActionEvent event) {
        if(tabFormaPag.getSelectionModel().getSelectedItem()!=null){
            listap.remove(tabFormaPag.getSelectionModel().getSelectedItem());
            carregaTPagamento();
            valores();
        }
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clkFinalizar(ActionEvent event) throws ControlException, SQLException {
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty()){
            int result = cpc.quitarContaPagar(ContasPagar.cpSelecionada, listap);
            if(result>0){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Resposta do Servidor");
                alert.setHeaderText(null);
                alert.setContentText("Conta paga com sucesso!");
                alert.showAndWait();
                limpatela();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Resposta do Servidor");
                alert.setHeaderText(null);
                alert.setContentText("Erro na gravação!");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Selecione uma conta a pagar!");
            alert.showAndWait();
        }
    }
    
    private void carregaTabela(Compra c){
        for(int i = 0; i<c.getProdutosCompra().size(); i++){
            TabelaQPagamento tb = new TabelaQPagamento();
            tb.setCodigo(c.getProdutosCompra().get(i).getP().getCodigo());
            tb.setDescricao(c.getProdutosCompra().get(i).getP().getDescricao());
            tb.setQtde(c.getProdutosCompra().get(i).getQtd());
            tb.setUnimed(c.getProdutosCompra().get(i).getP().getQtdeEmbalagem()+" - "+c.getProdutosCompra().get(i).getP().getUnimed().getAbreviacao());
            tb.setPrecounit(c.getProdutosCompra().get(i).getValor());
            tb.setTotal(c.getProdutosCompra().get(i).getValor()*c.getProdutosCompra().get(i).getQtd());
            lista.add(tb);
        }
        if(lista!=null){
            ObservableList<TabelaQPagamento> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabProdutos.setItems(modelo);
        }
    }
    
    private void carregaPagamentos() throws ControlException{
        TipoPagamentoControl tpc = new TipoPagamentoControl();
        List<TipoPagamento> lista = new ArrayList<>();
        lista = tpc.listaTPagamento(0, "");
        ObservableList<TipoPagamento> colection = FXCollections.observableArrayList(lista);
        cbFormaPag.getItems().addAll(colection);
    }
    
    private void limpatela(){
        txtAcrescimo.setText("");
        txtCodigo.setText("");
        txtData.setText("");
        txtDesconto.setText("");
        txtFornecedor.setText("");
        txtFuncionario.setText("");
        txtRestante.setText("");
        txtTotal.setText("");
        txtValorPag.setText("");
        lista = new ArrayList<>();
        listap = new ArrayList<>();
        tabFormaPag.getItems().clear();
        tabProdutos.getItems().clear();
        ContasPagar.setCpSelecionada(null);
    }

    
}

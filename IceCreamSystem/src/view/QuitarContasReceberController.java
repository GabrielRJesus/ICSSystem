/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import control.ContasReceberControl;
import control.TipoPagamentoControl;
import entidade.*;
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

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class QuitarContasReceberController implements Initializable {

    @FXML
    private JFXTextField txtComanda;
    @FXML
    private JFXTextField txtData;
    @FXML
    private JFXButton btnPesquisa;
    @FXML
    private JFXTextField txtCliente;
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
    private TableView<TPagamentoReceber> tabFormaPag;
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
    @FXML
    private JFXTextField txtVenda;
    
    private List<TabelaQPagamento> lista = new ArrayList<>();
    private List<TPagamentoReceber> listap = new ArrayList<>();
    private ContasReceberControl crc = new ContasReceberControl();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tabProdutos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        colQtde.setCellValueFactory(new PropertyValueFactory("qtde"));
        colUnimed.setCellValueFactory(new PropertyValueFactory("unimed"));
        colPrecoUnit.setCellValueFactory(new PropertyValueFactory("precounit"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
        
        tabFormaPag.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colValor.setCellValueFactory(new PropertyValueFactory("valor"));
        colFormaPag.setCellValueFactory(new PropertyValueFactory("tpg"));
        
        if(Venda.getVenSelecionada()!=null)
            carregaVenda(Venda.getVenSelecionada());
        
        try {
            carregaPagamentos();
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
    }  
    
    public void carregaVenda(Venda v){
        if(v!=null){
            txtCliente.setText(v.getCliNome());
            txtComanda.setText(v.getComanda());
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            String str = fmt.format(new Date());
            txtData.setText(str);
            txtFuncionario.setText(v.getFunc().getNome());
            txtRestante.setText(v.getTotal()+"");
            txtTotal.setText(v.getTotal()+"");
            txtVenda.setText(v.getCodigo()+"");
            carregaTabela(v);
        }
    }
    
    private void carregaTabela(Venda v){
        List<ItensVenda> listait = v.getLista();
        for(int i = 0; i<listait.size(); i++){
            TabelaQPagamento tb = new TabelaQPagamento();
            tb.setCodigo(listait.get(i).getProd().getCodigo());
            tb.setDescricao(listait.get(i).getProd().getDescricao());
            tb.setQtde(listait.get(i).getQtde());
            tb.setUnimed(listait.get(i).getProd().getQtdeEmbalagem()+" - "+listait.get(i).getProd().getUnimed().getAbreviacao());
            tb.setPrecounit(listait.get(i).getValor());
            tb.setTotal(listait.get(i).getValor()*listait.get(i).getQtde());
            lista.add(tb);
        }
        if(lista!=null){
            ObservableList<TabelaQPagamento> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabProdutos.setItems(modelo);
        }
    }

    @FXML
    private void clkPesquisa(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarVenda.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Localizar Venda");
        stage.setResizable(false);
        stage.showAndWait();
        if(Venda.getVenSelecionada()!=null)
            carregaVenda(Venda.getVenSelecionada());
    }

    @FXML
    private void clkIncluir(ActionEvent event) {
        TPagamentoReceber tp = new TPagamentoReceber();
        tp.setTpg(cbFormaPag.getValue());
        String texto = txtValorPag.getText().replace(",", ".");
        double valor = Double.parseDouble(texto);
        tp.setValor(valor);
        if(valor <= Double.parseDouble(txtRestante.getText())){
            listap.add(tp);
            carregaTabPagamentos();
            valores();
            txtValorPag.setText("");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Valor maior que o restante!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clkExcluir(ActionEvent event) {
        if(tabFormaPag.getSelectionModel().getSelectedItem()!=null){
            listap.remove(tabFormaPag.getSelectionModel().getSelectedItem());
            carregaTabPagamentos();
            valores();
        }
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
        Venda.setVenSelecionada(new Venda());
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clkFinalizar(ActionEvent event) throws ControlException, SQLException {
        double restante = 0;
        if(txtRestante.getText()!=null)
            restante = Double.parseDouble(txtRestante.getText());
        if(restante!=0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("A conta não foi toda paga!");
            alert.showAndWait();
            limpatela();
            Venda.setVenSelecionada(new Venda());
        }else{
            int rest = crc.gravrConta(listap, Venda.getVenSelecionada().getTotal());
            if(rest>0){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Resposta do Servidor");
                alert.setHeaderText(null);
                alert.setContentText("Recebimento finalizado com sucesso!");
                alert.showAndWait();
                Stage stage = (Stage) btnCancelar.getScene().getWindow();
                stage.close();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Resposta do Servidor");
                alert.setHeaderText(null);
                alert.setContentText("Erro ao receber conta!");
                alert.showAndWait();
            }
        }
    }
    
    private void carregaPagamentos() throws ControlException{
        TipoPagamentoControl tpc = new TipoPagamentoControl();
        List<TipoPagamento> lista = new ArrayList<>();
        lista = tpc.listaTPagamento(0, "");
        ObservableList<TipoPagamento> colection = FXCollections.observableArrayList(lista);
        cbFormaPag.getItems().addAll(colection);
    }
    
    public void carregaTabPagamentos(){
        if(listap!=null){
            ObservableList<TPagamentoReceber> modelo;
            modelo = FXCollections.observableArrayList(listap);
            tabFormaPag.setItems(modelo);
        }
    }
    
    private void valores(){
        double tot = Venda.getVenSelecionada().getTotal();
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
    
    private void limpatela(){
        txtAcrescimo.setText("");
        txtVenda.setText("");
        txtComanda.setText("");
        txtData.setText("");
        txtDesconto.setText("");
        txtCliente.setText("");
        txtFuncionario.setText("");
        txtRestante.setText("");
        txtTotal.setText("");
        txtValorPag.setText("");
        lista = new ArrayList<>();
        listap = new ArrayList<>();
        tabFormaPag.getItems().clear();
        tabProdutos.getItems().clear();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import control.LoteProdutoControl;
import control.ProdutoControl;
import entidade.LoteProduto;
import entidade.Produto;
import exception.ControlException;
import exception.EntidadeException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
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
public class LocalizarLoteProdutosController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtDescricao;
    @FXML
    private JFXTextField txtNumeroLote;
    @FXML
    private DatePicker dpDataInicio;
    @FXML
    private JFXTextField txtQtde;
    @FXML
    private DatePicker dpDataFim;
    @FXML
    private JFXButton btnPesquisar;
    @FXML
    private TableView<LoteProduto> tabLote;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colDescricao;
    @FXML
    private TableColumn colNumero;
    @FXML
    private TableColumn colValidade;
    @FXML
    private TableColumn colEstTotal;
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
        tabLote.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        colNumero.setCellValueFactory(new PropertyValueFactory("numeroLote"));
        colValidade.setCellValueFactory(new PropertyValueFactory("validade"));
        colEstTotal.setCellValueFactory(new PropertyValueFactory("qtdeCompra"));
        colEstoque.setCellValueFactory(new PropertyValueFactory("qtdRemanescente"));
            
    }    

    @FXML
    private void retornaLote(MouseEvent event) throws IOException {
        LoteProdutoControl tpc = new LoteProdutoControl();
        int codigo, total, remanes;
        String lote, numero;
        Date data;
        if (tabLote.getSelectionModel().getSelectedIndex() >= 0)
        {
            codigo = tabLote.getSelectionModel().getSelectedItem().getCodigo();
            lote = tabLote.getSelectionModel().getSelectedItem().getDescricao();
            numero = tabLote.getSelectionModel().getSelectedItem().getNumeroLote();
            data = tabLote.getSelectionModel().getSelectedItem().getValidade();
            total = tabLote.getSelectionModel().getSelectedItem().getQtdeCompra();
            remanes = tabLote.getSelectionModel().getSelectedItem().getQtdRemanescente();
            tpc.guardaSelecionado(codigo,lote,numero,data,total,remanes);
            Stage stage = (Stage) btnPesquisar.getScene().getWindow();
            stage.close();
        }
        
    }
    
    @FXML
    private void clkPesquisar(ActionEvent event) throws ControlException {
        carregaTabela();
        
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    public void carregaTabela() throws ControlException{
        LoteProdutoControl lpc = new LoteProdutoControl();
        ProdutoControl pc = new ProdutoControl();
        List<LoteProduto> lista = new ArrayList<>();
        Integer codigo = 0;
        Integer qtde = 0;
        int codigop = 0;
        Date inicio = null,fim = null;
        if(dpDataInicio.getValue()!=null)
            inicio = java.sql.Date.valueOf(dpDataInicio.getValue());
        if(dpDataFim.getValue()!=null)
            fim = java.sql.Date.valueOf(dpDataFim.getValue());
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        if(txtQtde.getText()!=null && !txtQtde.getText().isEmpty())
            qtde = Integer.parseInt(txtQtde.getText());
        if(Produto.getProdSelecionado()!=null)
            codigop = Produto.getProdSelecionado().getCodigo();
        lista = lpc.listaLotes(codigo, txtDescricao.getText(), txtNumeroLote.getText(), inicio, fim, qtde, codigop);
        if(lista!=null){
            ObservableList<LoteProduto> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabLote.setItems(modelo);
        }
    }
    
}

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
import entidade.ItensCompra;
import entidade.Produto;
import exception.ControlException;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class RealizarCompraController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtData;
    @FXML
    private JFXTextField txtFornecedor;
    @FXML
    private JFXButton btnPesquisaFornecedor;
    @FXML
    private JFXButton btnPesquisaProduto;
    @FXML
    private JFXTextField txtProduto;
    @FXML
    private JFXButton btnInsereProduto;
    @FXML
    private JFXTextField txtQtde;
    @FXML
    private JFXButton btnExcluir;
    @FXML
    private JFXButton btnIncluir;
    @FXML
    private TableView<Produto> tabProdutos;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colDescricao;
    @FXML
    private TableColumn colUmedida;
    @FXML
    private TableColumn colValor;
    @FXML
    private JFXTextField txtTotal;
    @FXML
    private JFXButton btnNovo;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnGravar;
    @FXML
    private JFXButton btnFinalizar;
    @FXML
    private JFXButton btnSair;
    @FXML
    private TableView<ItensCompra> tabProdutosC;
    @FXML
    private TableColumn<Produto, String> colDescricaoC;
    @FXML
    private TableColumn colQtdeC;
    @FXML
    private TableColumn colValorC;
    @FXML
    private JFXTextField txtValor;
    
    private List<ItensCompra> lista;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lista = new ArrayList<>();
        try {
            // TODO
            carrgaLista();
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
        carregaFornecedor(new PessoaControl().retornaForBusca());
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        String str = fmt.format(new Date());
        txtData.setText(str);
        
        tabProdutos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        colUmedida.setCellValueFactory(new PropertyValueFactory("unimed"));
        colValor.setCellValueFactory(new PropertyValueFactory("precoBase"));
        
        tabProdutosC.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colDescricaoC.setCellValueFactory(new PropertyValueFactory("p"));
        colQtdeC.setCellValueFactory(new PropertyValueFactory("qtd"));
        colValorC.setCellValueFactory(new PropertyValueFactory("valor"));
    } 
    
    public void carregaFornecedor(Fornecedor f){
        if(f!=null)
            txtFornecedor.setText(f.getNome());
    }

    @FXML
    private void clkPesquisaFornecedor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarFornecedor.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Localizar Fornecedor");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setX(330);
        stage.setY(40);
        stage.setResizable(false);
        stage.showAndWait();
        txtFornecedor.setText(Fornecedor.getForSelecionado().getNome());
    }

    @FXML
    private void clkPesquisaProduto(ActionEvent event) throws ControlException {
        ProdutoControl pc = new ProdutoControl();
        List<Produto> listap = pc.listaProdutos();
        for(int i =0; i<listap.size(); i++){
            if(!txtProduto.getText().equalsIgnoreCase(listap.get(i).getDescricao()))
                listap.remove(listap.get(i));
        }
        carregaLista(listap);
    }

    @FXML
    private void clkInsereProduto(ActionEvent event) {
    }

    @FXML
    private void clkExcluir(ActionEvent event) throws ControlException {
        if(tabProdutosC.getSelectionModel().getSelectedItem()!=null){
            lista.remove(tabProdutosC.getSelectionModel().getSelectedItem());
            carrgaLista(lista);
            txtTotal.setText(somaValor(lista)+"");
        }
    }

    @FXML
    private void clkIncluir(ActionEvent event) throws ControlException {
        Produto p = new Produto();
        int qtd = 0;
        double valor = 0;
        if(tabProdutos.getSelectionModel().getSelectedItem()!=null){
            p = tabProdutos.getSelectionModel().getSelectedItem();
            if(txtQtde.getText()!=null && !txtQtde.getText().isEmpty())
                qtd = Integer.parseInt(txtQtde.getText());
            if(txtValor.getText()!=null && !txtValor.getText().isEmpty())
                valor = Double.parseDouble(txtValor.getText());
            ItensCompra ic = new ItensCompra();
            ic.setP(p);
            ic.setQtd(qtd);
            ic.setValor(valor);
            lista.add(ic);
            carrgaLista(lista);
            txtTotal.setText(somaValor(lista)+"");
        }
    }
    
    public double somaValor(List<ItensCompra> lista){
        double total = 0;
        for(int i = 0; i< lista.size(); i++){
            total += lista.get(i).getQtd()*lista.get(i).getValor();
        }
        return total;
    }

    @FXML
    private void clkNovo(ActionEvent event) {
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
    }

    @FXML
    private void clkGravar(ActionEvent event) {
    }

    @FXML
    private void clkFinalizar(ActionEvent event) {
    }

    @FXML
    private void clkSair(ActionEvent event) {
        Stage stage = (Stage)btnExcluir.getScene().getWindow();
        stage.close();
    }

    
    public void carrgaLista() throws ControlException{
        ProdutoControl pc = new ProdutoControl();
        List<Produto> lista = new ArrayList<>();
        lista = pc.listaProdutos();
        if(lista!=null){
            ObservableList<Produto> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabProdutos.setItems(modelo);
        }
    }
    
    public void carregaLista(List<Produto> lista) throws ControlException{
        if(lista!=null){
            ObservableList<Produto> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabProdutos.setItems(modelo);
        }
    }
    
    public void carrgaLista(List<ItensCompra> lista) throws ControlException{
        if(lista!=null){
            ObservableList<ItensCompra> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabProdutosC.setItems(modelo);
        }
    }
    
}

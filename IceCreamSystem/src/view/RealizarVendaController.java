/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import control.*;
import entidade.*;
import exception.ControlException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class RealizarVendaController implements Initializable {

    @FXML
    private JFXButton btnGravar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnExcluir;
    @FXML
    private JFXButton btnSair;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtComanda;
    @FXML
    private JFXTextField txtData;
    @FXML
    private JFXTextField txtCliente;
    @FXML
    private JFXTextField txtFuncionario;
    @FXML
    private JFXButton btnPesqCliente;
    @FXML
    private JFXButton btnNovoCli;
    @FXML
    private JFXComboBox<TipoVenda> cbTipoVenda;
    @FXML
    private JFXTextField txtDtEntrega;
    @FXML
    private JFXButton btnPesqProd;
    @FXML
    private JFXTextField txtProduto;
    @FXML
    private Label txtValorProduto;
    @FXML
    private JFXButton btnInsereProd;
    @FXML
    private JFXButton btnExcluiProduto;
    @FXML
    private TableView<TabelaQPagamento> tabProdutos;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colDescricao;
    @FXML
    private TableColumn colUnidade;
    @FXML
    private TableColumn colQtde;
    @FXML
    private TableColumn colPreco;
    @FXML
    private TableColumn colTotal;
    @FXML
    private Label valorTotal;
    @FXML
    private JFXButton btnFinalizar;
    @FXML
    private JFXTextField txtQtde;
    @FXML
    private JFXButton btnLocalizar;

    private List<TabelaQPagamento> listat = new ArrayList<>();
    private int codProd = 0, codigocli = 0;
    private VendaControl vc = new VendaControl();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtDtEntrega.setDisable(true);
        txtFuncionario.setText(Funcionario.getFuncLogado().getNome());
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        String str = fmt.format(new Date());
        txtData.setText(str);
        try {
            carregaCB();
        } catch (ControlException ex) {
            Logger.getLogger(RealizarVendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabProdutos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        colQtde.setCellValueFactory(new PropertyValueFactory("qtde"));
        colUnidade.setCellValueFactory(new PropertyValueFactory("unimed"));
        colPreco.setCellValueFactory(new PropertyValueFactory("precounit"));
        colTotal.setCellValueFactory(new PropertyValueFactory("total"));
    }    


    @FXML
    private void clkGravar(ActionEvent event) throws ParseException, ControlException{
        int codigo = 0;
        double valTotal = 0;
        java.sql.Date dataEntrega = null;
        valTotal = Double.parseDouble(valorTotal.getText());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date data = new java.sql.Date(format.parse(txtData.getText()).getTime());
        if(txtDtEntrega.getText()!=null && !txtDtEntrega.getText().isEmpty()){
            dataEntrega = new java.sql.Date(format.parse(txtDtEntrega.getText()).getTime());
        }
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty()){
            codigo = Integer.parseInt(txtCodigo.getText());
        }
        if(txtComanda.getText()!=null && !txtComanda.getText().isEmpty()){
            if(txtCliente.getText()!=null && !txtCliente.getText().isEmpty()){
                List<ItensVenda> listaFim = new ArrayList<>();
                listaFim = convertLista(listat);
                int rest = vc.gravarVenda(codigo, txtComanda.getText(), data, codigocli, txtCliente.getText(), txtFuncionario.getText(), cbTipoVenda.getValue().getDescricao(), dataEntrega, listaFim, valTotal);
                if(rest>0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Resposta do Servidor");
                    alert.setHeaderText(null);
                    alert.setContentText("Venda gravada com sucesso!");
                    alert.showAndWait();
                    limpaTela();
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Resposta do Servidor");
                    alert.setHeaderText(null);
                    alert.setContentText("Erro ao gravar Venda!");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Resposta do Servidor");
                alert.setHeaderText(null);
                alert.setContentText("Digite o nome do cliente!");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Digite o numero da comanda!");
            alert.showAndWait();
        }
        
    }
    
    public List<ItensVenda> convertLista(List<TabelaQPagamento> listat) throws ControlException{
        List<ItensVenda> lista = new ArrayList<>();
        for(int i = 0; i<listat.size(); i++){
            ItensVenda iv = new ItensVenda();
            Produto p = new Produto();
            ProdutoControl pc = new ProdutoControl();
            iv.setQtde(listat.get(i).getQtde());
            iv.setProd(pc.buscaProduto(listat.get(i).getCodigo()));
            iv.setValor(listat.get(i).getPrecounit());
            lista.add(iv);
        }
        return lista;
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
        limpaTela();
    }

    @FXML
    private void clkExcluir(ActionEvent event) throws ControlException {
        if(txtCodigo.getText()!=null && txtCodigo.getText()!=""){
            int codigo = Integer.parseInt(txtCodigo.getText());
            int result = vc.excluirVenda(codigo);
            if(result > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Resposta do Servidor");
                alert.setHeaderText(null);
                alert.setContentText("Venda excluida com sucesso!");
                alert.showAndWait();
                limpaTela();
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Resposta do Servidor");
                alert.setHeaderText(null);
                alert.setContentText("Erro ao excluir venda!");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Selecione a venda a ser excluida!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clkSair(ActionEvent event) {
        Stage stage = (Stage)btnCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clkPesqCli(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalzarCliente.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Localizar Cliente");
        stage.setResizable(false);
        stage.showAndWait();
        if(Cliente.getCliSelecionado()!=null){
            txtCliente.setText(Cliente.getCliSelecionado().getNome());
            codigocli = Cliente.getCliSelecionado().getCodigo();
        }
    }

    @FXML
    private void clkNovoCli(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/GerenciarCliente.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Gerenciar Cliente");
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void clkPesqProd(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalzarProduto.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Localizar Produto");
        stage.setResizable(false);
        stage.showAndWait();
        if(Produto.getProdSelecionado()!=null){
            txtProduto.setText(Produto.getProdSelecionado().getDescricao());
            codProd = Produto.getProdSelecionado().getCodigo();
            txtValorProduto.setText(Produto.getProdSelecionado().getPreco()+"");
        }
    }

    @FXML
    private void clkInclui(ActionEvent event) {
        if(txtQtde.getText()!=null && !txtQtde.getText().isEmpty()){
            TabelaQPagamento tb = new TabelaQPagamento();        
            tb.setCodigo(codProd);
            tb.setDescricao(txtProduto.getText());
            tb.setQtde(Integer.parseInt(txtQtde.getText()));
            tb.setUnimed(Produto.getProdSelecionado().getQtdeEmbalagem()+" "+Produto.getProdSelecionado().getUnimed().getAbreviacao());
            tb.setPrecounit(Produto.getProdSelecionado().getPreco());
            tb.setTotal(tb.getPrecounit()*tb.getQtde());
            int i = verificaLista(listat, tb);
            if(i>=0){
                listat.get(i).setQtde(listat.get(i).getQtde()+tb.getQtde());
                listat.get(i).setTotal(listat.get(i).getQtde()*listat.get(i).getPrecounit());
            }else{
                listat.add(tb);
            }
            carregaTabela(listat);
            valorTotal.setText(somaValor(listat)+"");
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Digite a quantidade!");
            alert.showAndWait();
        }
        
    }
    
    public int verificaLista(List<TabelaQPagamento> lista, TabelaQPagamento item){
        for(int i =0 ; i<lista.size(); i++){
            if(lista.get(i).getCodigo() == item.getCodigo())
                return i;
        }
        return -1;
    }

    @FXML
    private void clkExclui(ActionEvent event) {
        if(tabProdutos.getSelectionModel().getSelectedItem()!=null){
            listat.remove(tabProdutos.getSelectionModel().getSelectedItem());
            carregaTabela(listat);
            valorTotal.setText(somaValor(listat)+"");
        }
    }
    
    public double somaValor(List<TabelaQPagamento> lista){
        double total = 0;
        for(int i = 0; i< lista.size(); i++){
            total += lista.get(i).getQtde()*lista.get(i).getPrecounit();
        }
        return total;
    }

    @FXML
    private void clkFinalizar(ActionEvent event) {
    }
    
    private void limpaTela(){
        txtCodigo.setText("");
        txtData.setText("");
        txtFuncionario.setText("");
        txtProduto.setText("");
        txtComanda.setText("");
        txtCliente.setText("");
        cbTipoVenda.setValue(new TipoVenda());
        txtDtEntrega.setText("");
        txtQtde.setText("");
        txtValorProduto.setText("0.00");
        valorTotal.setText("0.00");
        codigocli = 0;
        codProd = 0;
        listat = new ArrayList<>();
        tabProdutos.getItems().clear();
    }

    @FXML
    private void clkLocalizar(ActionEvent event) {
    }
    
    public void carregaTabela(List<TabelaQPagamento> lista){
        if(lista!=null){
            ObservableList<TabelaQPagamento> modelo;
            modelo = FXCollections.observableArrayList(lista);
            tabProdutos.setItems(modelo);
        }
    }
    
    public void carregaCB() throws ControlException{
        TipoVenda tv = new TipoVenda();
        TipoVendaControl tvc = new TipoVendaControl();
        List<TipoVenda> lista = new ArrayList<>();
        lista = tvc.listaTVenda(0, "");
        ObservableList<TipoVenda> colection = FXCollections.observableArrayList(lista);
        cbTipoVenda.getItems().addAll(colection);
    }

    @FXML
    private void selEntrega(ActionEvent event) {
        if(cbTipoVenda.getSelectionModel().getSelectedItem()!=null){
            if(cbTipoVenda.getSelectionModel().getSelectedItem().getDescricao().equalsIgnoreCase("Entrega"))
                txtDtEntrega.setDisable(false);
            else{
                txtDtEntrega.setDisable(true);
                txtDtEntrega.setText("");
            }
        }
    }
    
}

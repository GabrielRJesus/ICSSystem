/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import control.LoteProdutoControl;
import control.ProdutoControl;
import exception.ControlException;
import exception.EntidadeException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class GerenciarProdutoController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtDescricao;
    @FXML
    private JFXComboBox<String> cbTipo;
    @FXML
    private JFXComboBox<String> cbMarca;
    @FXML
    private JFXComboBox<String> cbUm;
    @FXML
    private JFXTextField txtPrecoBase;
    @FXML
    private JFXTextField txtPreco;
    @FXML
    private JFXTextField txtMargem;
    @FXML
    private JFXTextField txtQtde;
    @FXML
    private JFXTextField txtQtdeMin;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btGravar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btSair;
    @FXML
    private JFXButton btExcluir;
    @FXML
    private JFXButton btLocalizar;
    @FXML
    private JFXTextField txtDescricaoLote;
    @FXML
    private JFXTextField txtNumeroLote;
    @FXML
    private JFXTextField txtValidadeLote;
    @FXML
    private JFXTextField txtQtdeLote;
    @FXML
    private JFXTextField txtQtdeReman;
    @FXML
    private JFXTextField txtCodigoLote;
    @FXML
    private JFXTextField txtqtdEmbalagem;
    @FXML
    private JFXButton btNovoLote;
    @FXML
    private JFXButton btGravarLote;
    @FXML
    private JFXButton btLocalizarProduto;
    @FXML
    private JFXButton btLocalizarLote;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MaskFieldUtil.dateField(txtValidadeLote);
        
        inicializaProduto(true);
        inicializaLote(true);
        try {
            carregacbCategoria();
            carregacbMarca();
            carregacbUm();
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
        } catch (EntidadeException ex) {
            System.out.println(ex.getMessage());
        }
        carregaProduto();
    }    
    
    public void carregaProduto(){
        ProdutoControl pc = new ProdutoControl();
        if(pc.retornaSelecionado()!=null){
            txtDescricao.setText(pc.retornaSelecionado().getDescricao());
            txtCodigo.setText(pc.retornaSelecionado().getCodigo()+"");
            txtPreco.setText(pc.retornaSelecionado().getPreco()+"");
            txtPrecoBase.setText(pc.retornaSelecionado().getPrecoBase()+"");
            txtMargem.setText(pc.retornaSelecionado().getMargemLucro()+"");
            txtQtde.setText(pc.retornaSelecionado().getQtdeEstoque()+"");
            txtQtdeMin.setText(pc.retornaSelecionado().getQtdeMin()+"");
            cbTipo.setValue(pc.retornaSelecionado().getCprod().toString());
            cbMarca.setValue(pc.retornaSelecionado().getMarca().toString());
            cbUm.setValue(pc.retornaSelecionado().getUnimed().toString());
            txtqtdEmbalagem.setText(pc.retornaSelecionado().getQtdeEmbalagem());
        }
    }
    
    public void carregaLote(){
        LoteProdutoControl lpc = new LoteProdutoControl();
        if(lpc.retornaSelecionado()!=null){
                txtCodigoLote.setText(lpc.retornaSelecionado().getCodigo().toString());
                txtDescricaoLote.setText(lpc.retornaSelecionado().getDescricao());
                txtNumeroLote.setText(lpc.retornaSelecionado().getNumeroLote());
                SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
                String str = fmt.format(lpc.retornaSelecionado().getValidade());
                txtValidadeLote.setText(str);
                txtQtdeLote.setText(lpc.retornaSelecionado().getQtdeCompra()+"");
                txtQtdeReman.setText(lpc.retornaSelecionado().getQtdRemanescente()+"");
            }
    }

    @FXML
    private void clkNovo(ActionEvent event) {
        inicializaProduto(false);
        inicializaLote(false);
    }

    @FXML
    private void clkGravar(ActionEvent event) throws ControlException, ParseException, SQLException {
        ProdutoControl pc = new ProdutoControl();
        Date data = null;
        int codigo =0, qtde = 0, qtdemin = 0,  qtdeLote = 0, qtdeRLote = 0;
        Integer codigoLote = 0;
        double preco =0.0, margem = 0.0, precobase = 0.0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        if(txtQtde.getText()!=null && !txtQtde.getText().isEmpty())
            qtde = Integer.parseInt(txtQtde.getText());
        if(txtQtdeMin.getText()!=null && !txtQtdeMin.getText().isEmpty())
            qtdemin = Integer.parseInt(txtQtdeMin.getText());
        if(txtPreco.getText()!=null && !txtPreco.getText().isEmpty())
            preco = Double.parseDouble(txtPreco.getText());
        if(txtPrecoBase.getText()!=null && !txtPrecoBase.getText().isEmpty())
            precobase = Double.parseDouble(txtPrecoBase.getText());
        if(txtMargem.getText()!=null && !txtMargem.getText().isEmpty())
            margem = Double.parseDouble(txtMargem.getText());
        if(txtCodigoLote.getText()!=null && !txtCodigoLote.getText().isEmpty())
            codigoLote = Integer.parseInt(txtCodigoLote.getText());
        if(txtCodigoLote.getText()==null || txtCodigoLote.getText().isEmpty())
            codigoLote = null;
        if(txtQtdeLote.getText()!=null && !txtQtdeLote.getText().isEmpty())
            qtdeLote = Integer.parseInt(txtQtdeLote.getText());
        if(txtQtdeReman.getText()!=null && !txtQtdeReman.getText().isEmpty())
            qtdeRLote = Integer.parseInt(txtQtdeReman.getText());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if(txtValidadeLote.getText()!=null)
            data = new java.sql.Date(format.parse(txtValidadeLote.getText()).getTime());
        int result = pc.gravarProdutoeLote(codigo, txtDescricao.getText(), cbTipo.getValue(),  cbMarca.getValue(), cbUm.getValue(), precobase, preco, margem, qtde, qtdemin, codigo, txtDescricaoLote.getText(), txtNumeroLote.getText(), data, qtdeLote, qtdeRLote, txtqtdEmbalagem.getText());
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Produto gravado com sucesso!");
            alert.showAndWait();
            limpatela();
            inicializaProduto(true);
            inicializaLote(true);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Erro na hora de gravar os dados!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
        limpatela();
        inicializaProduto(true);
        inicializaLote(true);
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
        Stage stage = (Stage) btSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clkExcluir(ActionEvent event) throws ControlException {
        ProdutoControl pc = new ProdutoControl();
        int codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        int result = pc.excluiProduto(codigo);
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Produto excluido com sucesso!");
            alert.showAndWait();
            limpatela();
            inicializaProduto(true);
            inicializaLote(true);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Erro na hora de excluir os dados!");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void clkLocalizar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalzarProduto.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Localizar Produto");
        stage.setResizable(false);
        stage.showAndWait();
        carregaProduto();
    }
    
    public void inicializaProduto(boolean estado){
        txtDescricao.setDisable(estado);
        btCancelar.setDisable(estado);
        btGravar.setDisable(estado);
        btExcluir.setDisable(estado);
        btNovo.setDisable(!estado);
        txtCodigo.setDisable(estado);
        txtPreco.setDisable(estado);
        txtPrecoBase.setDisable(estado);
        txtMargem.setDisable(estado);
        txtQtde.setDisable(estado);
        txtQtdeMin.setDisable(estado);
        txtqtdEmbalagem.setDisable(estado);
        cbTipo.setDisable(estado);
        cbMarca.setDisable(estado);
        cbUm.setDisable(estado);
    }
    
    public void inicializaLote(boolean estado){
        txtCodigoLote.setDisable(estado);
        txtDescricaoLote.setDisable(estado);
        txtNumeroLote.setDisable(estado);
        txtValidadeLote.setDisable(estado);
        txtQtdeLote.setDisable(estado);
        txtQtdeReman.setDisable(estado);
        btGravarLote.setDisable(estado);
        btLocalizarProduto.setDisable(estado);
    }
    
    public void limpatela(){
        txtDescricao.setText("");
        txtCodigo.setText("");
        txtPreco.setText("");
        txtPrecoBase.setText("");
        txtMargem.setText("");
        txtQtde.setText("");
        txtQtdeMin.setText("");
        txtCodigo.setText("");
        cbTipo.setValue("");
        cbMarca.setValue("");
        cbUm.setValue("");
        txtCodigoLote.setText("");
        txtDescricaoLote.setText("");
        txtNumeroLote.setText("");
        txtValidadeLote.setText("");
        txtQtdeLote.setText("");
        txtQtdeReman.setText("");
        txtqtdEmbalagem.setText("");
    }
    
    public void carregacbMarca() throws ControlException, EntidadeException{
        ProdutoControl pc = new ProdutoControl();
        List<String> lista = new ArrayList<>();
        lista = pc.buscaMarcas();
        ObservableList<String> colection = FXCollections.observableArrayList(lista);
        cbMarca.getItems().addAll(colection);
    }
    
    public void carregacbUm() throws ControlException, EntidadeException{
        ProdutoControl pc = new ProdutoControl();
        List<String> lista = new ArrayList<>();
        lista = pc.buscaUm();
        ObservableList<String> colection = FXCollections.observableArrayList(lista);
        cbUm.getItems().addAll(colection);
    }
    
    public void carregacbCategoria() throws ControlException, EntidadeException{
        ProdutoControl pc = new ProdutoControl();
        List<String> lista = new ArrayList<>();
        lista = pc.buscaCategorias();
        ObservableList<String> colection = FXCollections.observableArrayList(lista);
        cbTipo.getItems().addAll(colection);
    }

    @FXML
    private void clkNovoLote(ActionEvent event) {
        inicializaLote(false);
    }

    @FXML
    private void clkGravarLote(ActionEvent event) throws ParseException, ControlException {
        ProdutoControl pc = new ProdutoControl();
        int codigoLote = 0, qtdr = 0, qtde = 0;
        Date data = null;
        if(txtCodigoLote.getText()!=null && !txtCodigoLote.getText().isEmpty()){
            codigoLote = Integer.parseInt(txtCodigoLote.getText());
        }
        if(txtQtdeLote.getText()!=null && !txtQtdeLote.getText().isEmpty()){
            qtde = Integer.parseInt(txtQtdeLote.getText());
        }
        if(txtQtdeReman.getText()!=null && !txtQtdeReman.getText().isEmpty()){
            qtdr = Integer.parseInt(txtQtdeReman.getText());
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if(txtValidadeLote.getText()!=null)
            data = new java.sql.Date(format.parse(txtValidadeLote.getText()).getTime());
        int result = pc.gravaLote(codigoLote, txtDescricaoLote.getText(), txtNumeroLote.getText(), data, qtde, qtdr, pc.retornaSelecionado().getCodigo());
        if(result>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Lote gravado com sucesso!");
            alert.showAndWait();
            limpatela();
            inicializaProduto(true);
            inicializaLote(true);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Erro na hora de gravar os dados!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clkLocalizarProduto(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalzarProduto.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Localizar Produto");
        stage.setResizable(false);
        stage.showAndWait();
        carregaProduto();
    }

    @FXML
    private void clkLocalizarLote(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarLoteProdutos.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Localizar Lote");
        stage.setResizable(false);
        stage.showAndWait();
        carregaLote();
    }
    
    
}

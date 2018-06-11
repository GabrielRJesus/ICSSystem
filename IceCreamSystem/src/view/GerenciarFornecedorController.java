/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import control.PessoaControl;
import entidade.Cidade;
import entidade.Estado;
import exception.ControlException;
import exception.EntidadeException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class GerenciarFornecedorController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtRazao;
    @FXML
    private JFXTextField txtCnpj;
    @FXML
    private JFXTextField txtIe;
    @FXML
    private JFXTextField txtTelefone;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtEndereco;
    @FXML
    private JFXTextField txtNumero;
    @FXML
    private JFXTextField txtBairro;
    @FXML
    private JFXTextField txtCep;
    @FXML
    private JFXComboBox<String> cbEstado;
    @FXML
    private JFXComboBox<String> cbCidade;
    @FXML
    private JFXTextField txtNomeContato;
    @FXML
    private JFXComboBox<String> cbSituacao;
    @FXML
    private JFXTextField txtInicio;
    @FXML
    private JFXTextField txtFim;
    @FXML
    private JFXTextField txtObs;
    @FXML
    private JFXTextField txtRamo;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btGravar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btLocalizar;
    @FXML
    private JFXButton btSair;
    @FXML
    private JFXButton btExcluir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PessoaControl pc = new PessoaControl();
        MaskFieldUtil.cepField(txtCep);
        MaskFieldUtil.cnpjField(txtCnpj);
        MaskFieldUtil.dateField(txtInicio);
        MaskFieldUtil.dateField(txtFim);
        MaskFieldUtil.foneField(txtTelefone);
        
        inicializa(true);
        carregacb();
        try {
            carregacbEstado();
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
        
        if(pc.retornaForBusca()!=null){
            txtCodigo.setText(pc.retornaForBusca().getCodigo()+"");
            txtNome.setText(pc.retornaForBusca().getNome());
            txtRazao.setText(pc.retornaForBusca().getRazaosocial());
            txtCnpj.setText(pc.retornaForBusca().getCnpj());
            txtIe.setText(pc.retornaForBusca().getIe());
            txtTelefone.setText(pc.retornaForBusca().getTelefone());
            txtEmail.setText(pc.retornaForBusca().getEmail());
            txtEndereco.setText(pc.retornaForBusca().getLogradouro().getEndereco());
            txtNumero.setText(pc.retornaForBusca().getLogradouro().getNumero());
            txtBairro.setText(pc.retornaForBusca().getLogradouro().getBairro());
            txtCep.setText(pc.retornaForBusca().getLogradouro().getCep());
            cbEstado.setValue(pc.retornaForBusca().getLogradouro().getCidade().getEstado().toString());
            cbCidade.setValue(pc.retornaForBusca().getLogradouro().getCidade().toString());
            txtNomeContato.setText(pc.retornaForBusca().getResponsavel());
            if(pc.retornaForBusca().getStatus()==0)
                cbSituacao.setValue("Inativo");
            if(pc.retornaForBusca().getStatus()==1)
                cbSituacao.setValue("Ativo");
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            String str = fmt.format(pc.retornaForBusca().getInicioAtiv());
            txtInicio.setText(str);
            if(pc.retornaForBusca().getFimAtiv()!=null){
                str = fmt.format(pc.retornaForBusca().getFimAtiv());
                txtFim.setText(str);
            }else
                txtFim.setText("");
            txtObs.setText(pc.retornaForBusca().getObservacoes());
            txtRamo.setText(pc.retornaForBusca().getRamoAtiv());
        }
        
    }    

    @FXML
    private void selectEstado(ActionEvent event) throws ControlException {
        if(cbEstado.getSelectionModel().getSelectedItem()!=null)
            carregacbCidade(cbEstado.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    private void clkNovo(ActionEvent event) {
        inicializa(false);
    }

    @FXML
    private void clkGravar(ActionEvent event) throws ParseException, ControlException, SQLException, EntidadeException {
        Integer codigo,situ;
        java.sql.Date dataf =null,datai=null;
        if(txtCodigo.getText().toString()!=null && !txtCodigo.getText().toString().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText().toString());
        else
            codigo = null;
        if(cbSituacao.getValue().equalsIgnoreCase("Ativo"))
            situ = 1;
        else{
            if(cbSituacao.getValue().equalsIgnoreCase("Inativo"))
                situ = 0;
            else
                situ = 2;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if(txtInicio.getText()!=null)
            datai = new java.sql.Date(format.parse(txtInicio.getText()).getTime());
        if(txtFim.getText()!=null && !txtFim.getText().isEmpty())
            dataf = new java.sql.Date(format.parse(txtFim.getText()).getTime());
        PessoaControl pc = new PessoaControl();
        int rest = pc.gravarFornecedor(codigo, txtNome.getText().toString(), txtRazao.getText(), txtCnpj.getText(), txtIe.getText(), txtNomeContato.getText(),
                situ, datai, dataf, txtObs.getText(), txtRamo.getText(), txtTelefone.getText(), txtEmail.getText(),
                pc.montaLogradouro(txtEndereco.getText().toString(), txtNumero.getText().toString(), txtBairro.getText().toString(), txtCep.getText().toString(), cbCidade.getValue()));
        if(rest >0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Fornecedor gravado com sucesso!");
            alert.showAndWait();
            limpatela();
            inicializa(true);
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
        inicializa(true);
        limpatela();
    }

    @FXML
    private void clkLocalizar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarFornecedor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btSair.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Localizar Fornecedor");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btSair.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Menu Principal");
        stage.setResizable(false);
        stage.showAndWait();
        stage.close();
    }

    @FXML
    private void clkExcluir(ActionEvent event) throws ControlException {
        PessoaControl pc = new PessoaControl();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        if(codigo>0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Deseja mesmo excluir esse fornecedor?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                 int resp = pc.excluirFornecedor(codigo);
                 if(resp>0){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Resposta do Servidor");
                    alert.setHeaderText(null);
                    alert.setContentText("Fornecedor excluido com sucesso!");
                    alert.showAndWait();
                    limpatela();
                    inicializa(true);
                 }else{
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Resposta do Servidor");
                    alert.setHeaderText(null);
                    alert.setContentText("Erro na hora de excluir!");
                    alert.showAndWait();
                 }
            } else {
                inicializa(true);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Selecione um fornecedor para excluir!");
            alert.showAndWait();
        }
    }
    
    public void inicializa(boolean estado){
        txtBairro.setDisable(estado);
        txtIe.setDisable(estado);
        txtCep.setDisable(estado);
        txtCodigo.setDisable(estado);
        txtCnpj.setDisable(estado);
        txtEmail.setDisable(estado);
        txtEndereco.setDisable(estado);
        txtNome.setDisable(estado);
        txtNumero.setDisable(estado);
        txtInicio.setDisable(estado);
        txtTelefone.setDisable(estado);
        txtRamo.setDisable(estado);
        txtRazao.setDisable(estado);
        txtObs.setDisable(estado);
        txtNomeContato.setDisable(estado);
        txtFim.setDisable(estado);
        cbCidade.setDisable(estado);
        cbEstado.setDisable(estado);
        cbSituacao.setDisable(estado);
        btCancelar.setDisable(estado);
        btGravar.setDisable(estado);
        btExcluir.setDisable(estado);
        
    }
    
    public void limpatela(){
        txtBairro.setText("");
        txtCnpj.setText("");
        txtCep.setText("");
        txtCodigo.setText("");
        txtIe.setText("");
        txtEmail.setText("");
        txtEndereco.setText("");
        txtNome.setText("");
        txtNumero.setText("");
        txtNomeContato.setText("");
        txtTelefone.setText("");
        txtInicio.setText("");
        txtFim.setText("");
        txtRamo.setText("");
        txtRazao.setText("");
        txtObs.setText("");
        cbCidade.setValue("");
        cbEstado.setValue("");
        cbSituacao.setPromptText("Sexo");
    }
    
    public void carregacb(){
        List<String> lista = new ArrayList<>();
        lista.add("Ativo");
        lista.add("Inativo");
        ObservableList<String> colection = FXCollections.observableArrayList(lista);
        cbSituacao.getItems().addAll(colection);
    }
    
    public void carregacbCidade(String est) throws ControlException{
        cbCidade.getItems().clear();
        PessoaControl pc = new PessoaControl();
        List<String> lista = new ArrayList<>();
        lista = pc.buscaCidades(est);
        ObservableList<String> colection = FXCollections.observableArrayList(lista);
        cbCidade.getItems().addAll(colection);
    }
    
    public void carregacbEstado() throws ControlException{
        PessoaControl pc = new PessoaControl();
        List<String> lista = new ArrayList<>();
        lista = pc.buscaEstados();
        ObservableList<String> colection = FXCollections.observableArrayList(lista);
        cbEstado.getItems().addAll(colection);
    }
}

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
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class GerenciarClienteController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtCpf;
    @FXML
    private JFXTextField txtRg;
    @FXML
    private JFXTextField txtTelefone;
    @FXML
    private JFXTextField txtCelular;
    @FXML
    private JFXComboBox<String> cbSexo;
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
    private JFXComboBox<Cidade> cbCidade;
    @FXML
    private JFXComboBox<Estado> cbEstado;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btGravar;
    @FXML
    private JFXButton btLocalizar;
    @FXML
    private JFXButton btSair;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXTextField txtData;
    @FXML
    private JFXButton btExcluir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PessoaControl pc = new PessoaControl();
        inicializa(true);
        MaskFieldUtil.cpfField(txtCpf);
        MaskFieldUtil.dateField(txtData);
        MaskFieldUtil.foneField(txtCelular);
        MaskFieldUtil.foneField(txtTelefone);
        if(pc.retornaCliBusca()!=null){
            txtBairro.setText(pc.retornaCliBusca().getLogradouro().getBairro());
            txtCelular.setText(pc.retornaCliBusca().getCelular());
            txtCep.setText(pc.retornaCliBusca().getLogradouro().getCep());
            txtCodigo.setText(pc.retornaCliBusca().getCodigo()+"");
            txtCpf.setText(pc.retornaCliBusca().getCpf());
            txtEmail.setText(pc.retornaCliBusca().getEmail());
            txtEndereco.setText(pc.retornaCliBusca().getLogradouro().getEndereco());
            txtNome.setText(pc.retornaCliBusca().getNome());
            txtNumero.setText(pc.retornaCliBusca().getLogradouro().getNumero());
            txtRg.setText(pc.retornaCliBusca().getRg());
            txtTelefone.setText(pc.retornaCliBusca().getTelefone());
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            String str = fmt.format(pc.retornaCliBusca().getDtNasc());
            txtData.setText(str);
            cbCidade.setValue(pc.retornaCliBusca().getLogradouro().getCidade());
            cbEstado.setValue(pc.retornaCliBusca().getLogradouro().getCidade().getEstado());
            if(pc.retornaCliBusca().getSexo()=='M')
                cbSexo.setValue("Masculino");
            if(pc.retornaCliBusca().getSexo()=='F')
                cbSexo.setValue("Feminino");
        }
        try {
            carregacb();
            carregacbEstado();
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
        
    }    

    @FXML
    private void clkNovo(ActionEvent event) {
        inicializa(false);
        
    }

    @FXML
    private void clkGravar(ActionEvent event) throws ControlException, SQLException, ParseException {
        Integer codigo;
        if(txtCodigo.getText().toString()!=null && !txtCodigo.getText().toString().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText().toString());
        else
            codigo = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date data = new java.sql.Date(format.parse(txtData.getText()).getTime());
        PessoaControl pc = new PessoaControl();
        int rest = pc.gravarCliente(codigo, txtNome.getText().toString(), txtCpf.getText().toString(), txtRg.getText().toString(), txtTelefone.getText().toString(), 
                txtCelular.getText().toString(),data, cbSexo.getValue(), txtEmail.getText().toString(), 
                pc.montaLogradouro(txtEndereco.getText().toString(), txtNumero.getText().toString(), txtBairro.getText().toString(), txtCep.getText().toString(), cbCidade.getValue()));
        if(rest >0){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Cliente gravado com sucesso!");
            alert.showAndWait();
            limpatela();
            inicializa(true);
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Erro na hora de gravar os dados!");
            alert.showAndWait();
        }
            
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
        limpatela();
        inicializa(true);
    }

    @FXML
    private void clkLocalizar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarCliente.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btSair.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Menu Principal");
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
    
    public void inicializa(boolean estado){
        txtBairro.setDisable(estado);
        txtCelular.setDisable(estado);
        txtCep.setDisable(estado);
        txtCodigo.setDisable(estado);
        txtCpf.setDisable(estado);
        txtEmail.setDisable(estado);
        txtEndereco.setDisable(estado);
        txtNome.setDisable(estado);
        txtNumero.setDisable(estado);
        txtRg.setDisable(estado);
        txtTelefone.setDisable(estado);
        txtData.setDisable(estado);
        cbCidade.setDisable(estado);
        cbEstado.setDisable(estado);
        cbSexo.setDisable(estado);
        btCancelar.setDisable(estado);
        btGravar.setDisable(estado);
        
    }
    
    public void limpatela(){
        txtBairro.setText("");
        txtCelular.setText("");
        txtCep.setText("");
        txtCodigo.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtEndereco.setText("");
        txtNome.setText("");
        txtNumero.setText("");
        txtRg.setText("");
        txtTelefone.setText("");
        txtData.setText("");
        cbCidade.setPromptText("Cidade");
        cbEstado.setPromptText("Estado");
        cbSexo.setPromptText("Sexo");
    }
    
    public void carregacb(){
        List<String> lista = new ArrayList<>();
        lista.add("Masculino");
        lista.add("Feminino");
        ObservableList<String> colection = FXCollections.observableArrayList(lista);
        cbSexo.getItems().addAll(colection);
    }
    
    public void carregacbCidade(Estado est) throws ControlException{
        cbCidade.getItems().clear();
        PessoaControl pc = new PessoaControl();
        List<Cidade> lista = new ArrayList<>();
        lista = pc.buscaCidades(est);
        ObservableList<Cidade> colection = FXCollections.observableArrayList(lista);
        cbCidade.getItems().addAll(colection);
    }
    
    public void carregacbEstado() throws ControlException{
        PessoaControl pc = new PessoaControl();
        List<Estado> lista = new ArrayList<>();
        lista = pc.buscaEstados();
        ObservableList<Estado> colection = FXCollections.observableArrayList(lista);
        cbEstado.getItems().addAll(colection);
    }

    @FXML
    private void selectEstado(ActionEvent event) throws ControlException {
        if(cbEstado.getSelectionModel().getSelectedItem()!=null)
            carregacbCidade(cbEstado.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void clkExcluir(ActionEvent event) throws ControlException {
        PessoaControl pc = new PessoaControl();
        Integer codigo = 0;
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        if(codigo>0){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setContentText("Deseja mesmo excluir esse cliente?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                 int resp = pc.excluirCliente(codigo);
                 if(resp>0){
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Resposta do Servidor");
                    alert.setHeaderText(null);
                    alert.setContentText("Cliente excluido com sucesso!");
                    alert.showAndWait();
                    limpatela();
                    inicializa(true);
                 }else{
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Resposta do Servidor");
                    alert.setHeaderText(null);
                    alert.setContentText("Erro na hora de excluir!");
                    alert.showAndWait();
                 }
            } else {
                inicializa(true);
            }
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Selecione um cliente para excluir!");
            alert.showAndWait();
        }
    }
    
}

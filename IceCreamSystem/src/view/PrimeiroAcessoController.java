/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import control.PessoaControl;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class PrimeiroAcessoController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtCpf;
    @FXML
    private JFXTextField txtRg;
    @FXML
    private JFXTextField txtEndereco;
    @FXML
    private JFXTextField txtNumero;
    @FXML
    private JFXTextField txtBairro;
    @FXML
    private JFXTextField txtCep;
    @FXML
    private JFXComboBox<String> cbCidade;
    @FXML
    private JFXComboBox<String> cbEstado;
    @FXML
    private JFXTextField txtTelefone;
    @FXML
    private JFXTextField txtCelular;
    private JFXTextField dpData;
    @FXML
    private JFXComboBox<String> cbSexo;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXPasswordField txtSenha;
    private JFXTextField dpDataAdm;
    private JFXTextField dpDataDem;
    @FXML
    private JFXTextField txtSalario;
    @FXML
    private JFXTextField txtCargo;
    @FXML
    private JFXButton btGravar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btSair;
    @FXML
    private JFXTextField txtDataNascimento;
    @FXML
    private JFXTextField txtDataAdmis;
    @FXML
    private JFXTextField txtDataDemis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PessoaControl pc = new PessoaControl();
        MaskFieldUtil.cepField(txtCep);
        MaskFieldUtil.cpfField(txtCpf);
        MaskFieldUtil.dateField(txtDataNascimento);
        MaskFieldUtil.dateField(txtDataAdmis);
        MaskFieldUtil.dateField(txtDataDemis);
        MaskFieldUtil.foneField(txtTelefone);
        MaskFieldUtil.foneField(txtCelular);
        inicializa(false);
        carregacb();
        try {
            carregacbEstado();
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void selectEstado(ActionEvent event) throws ControlException {
        if(cbEstado.getSelectionModel().getSelectedItem()!=null)
            carregacbCidade(cbEstado.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void clkGravar(ActionEvent event) throws ControlException, SQLException, EntidadeException, IOException, ParseException {
        Integer codigo;
        Double salario;
        Date datadem = null, dataadm = null, data = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if(txtCodigo.getText()!=null && !txtCodigo.getText().isEmpty())
            codigo = Integer.parseInt(txtCodigo.getText());
        else
            codigo = null;
        if(txtSalario.getText()!=null && !txtSalario.getText().isEmpty())
            salario = Double.parseDouble(txtSalario.getText());
        else
            salario = 0.0;
        if(dpData.getText()!=null && !dpData.getText().isEmpty())
            data = new java.sql.Date(format.parse(dpData.getText()).getTime());
        if(dpDataAdm.getText()!=null && !dpDataAdm.getText().isEmpty())
            dataadm = new java.sql.Date(format.parse(dpDataAdm.getText()).getTime());
        if(dpDataDem.getText()!=null && !dpDataDem.getText().isEmpty())
            datadem = new java.sql.Date(format.parse(dpDataDem.getText()).getTime());
        PessoaControl pc = new PessoaControl();
        int rest = pc.gravarFuncionario(codigo, txtNome.getText().toString(), txtCpf.getText().toString(), txtRg.getText().toString(), txtTelefone.getText().toString(), 
                txtCelular.getText().toString(),data, cbSexo.getValue(), txtEmail.getText().toString(), 
                pc.montaLogradouro(txtEndereco.getText().toString(), txtNumero.getText().toString(), txtBairro.getText().toString(), txtCep.getText().toString(), cbCidade.getValue()),
                txtLogin.getText(), txtSenha.getText(), dataadm, datadem, salario, txtCargo.getText());
        if(rest >0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resposta do Servidor");
            alert.setHeaderText(null);
            alert.setContentText("Cliente gravado com sucesso!");
            alert.showAndWait();
            //Fazer Login
            inicializa(true);
            limpatela();
            btGravar.setDisable(true);
            
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
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaLogin.fxml"));  
        Scene scene = new Scene(root);
        Stage stage = (Stage) btSair.getScene().getWindow();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
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
        dpData.setDisable(estado);
        cbCidade.setDisable(estado);
        cbEstado.setDisable(estado);
        cbSexo.setDisable(estado);
        btCancelar.setDisable(estado);
        txtLogin.setDisable(estado);
        txtSenha.setDisable(estado);
        txtCargo.setDisable(estado);
        txtSalario.setDisable(estado);
        dpDataAdm.setDisable(estado);
        dpDataDem.setDisable(estado);
        
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
        dpData.setText("");
        cbCidade.setValue("");
        cbEstado.setValue("");
        cbSexo.setPromptText("Sexo");
        txtLogin.setText("");
        txtSenha.setText("");
        txtCargo.setText("");
        txtSalario.setText("");
        dpDataAdm.setText("");
        dpDataDem.setText("");
    }
    
    public void carregacb(){
        List<String> lista = new ArrayList<>();
        lista.add("Masculino");
        lista.add("Feminino");
        ObservableList<String> colection = FXCollections.observableArrayList(lista);
        cbSexo.getItems().addAll(colection);
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

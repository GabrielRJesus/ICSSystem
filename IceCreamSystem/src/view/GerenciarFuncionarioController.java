package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import control.PessoaControl;
import entidade.Cidade;
import entidade.Estado;
import entidade.Funcionario;
import exception.ControlException;
import exception.EntidadeException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.MaskFieldUtil;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class GerenciarFuncionarioController implements Initializable {

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
    private JFXComboBox<String> cbCidade;
    @FXML
    private JFXComboBox<String> cbEstado;
    @FXML
    private JFXButton btNovo;
    @FXML
    private JFXButton btGravar;
    @FXML
    private JFXButton btLocalizar;
    @FXML
    private JFXButton btSair;
    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXPasswordField txtSenha;
    @FXML
    private JFXTextField txtSalario;
    @FXML
    private JFXTextField txtCargo;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btExcluir;
    @FXML
    private JFXTextField txtDataN;
    @FXML
    private JFXTextField txtDataAdmissao;
    @FXML
    private JFXTextField txtDataDemissao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        MaskFieldUtil.cepField(txtCep);
        MaskFieldUtil.cpfField(txtCpf);
        MaskFieldUtil.dateField(txtDataN);
        MaskFieldUtil.dateField(txtDataAdmissao);
        MaskFieldUtil.dateField(txtDataDemissao);
        MaskFieldUtil.foneField(txtTelefone);
        MaskFieldUtil.foneField(txtCelular);
        inicializa(true);
        carregacb();
        try {
            carregacbEstado();
        } catch (ControlException ex) {
            System.out.println(ex.getMessage());
        }
        carregaFuncionario();
    }   
    
    public void carregaFuncionario(){
         SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
         
        PessoaControl pc = new PessoaControl();
        if(pc.retornaFuncBusca()!=null){
            LocalDate data = null, dataadm = null, datadem = null;
            if(pc.retornaFuncBusca().getDtNasc()!=null)
             data = new java.sql.Date( pc.retornaFuncBusca().getDtNasc().getTime() ).toLocalDate();
            if(pc.retornaFuncBusca().getDtAdmiss()!=null)
                dataadm = new java.sql.Date( pc.retornaFuncBusca().getDtAdmiss().getTime() ).toLocalDate();
            if(pc.retornaFuncBusca().getDtDemiss()!=null)
                datadem = new java.sql.Date( pc.retornaFuncBusca().getDtDemiss().getTime() ).toLocalDate();
            txtBairro.setText(pc.retornaFuncBusca().getLogradouro().getBairro());
            txtCelular.setText(pc.retornaFuncBusca().getCelular());
            txtCep.setText(pc.retornaFuncBusca().getLogradouro().getCep());
            txtCodigo.setText(pc.retornaFuncBusca().getCodigo()+"");
            txtCpf.setText(pc.retornaFuncBusca().getCpf());
            txtEmail.setText(pc.retornaFuncBusca().getEmail());
            txtEndereco.setText(pc.retornaFuncBusca().getLogradouro().getEndereco());
            txtNome.setText(pc.retornaFuncBusca().getNome());
            txtNumero.setText(pc.retornaFuncBusca().getLogradouro().getNumero());
            txtRg.setText(pc.retornaFuncBusca().getRg());
            txtTelefone.setText(pc.retornaFuncBusca().getTelefone());
            String str = fmt.format(data);
            txtDataN.setText(str);
            cbCidade.setValue(pc.retornaFuncBusca().getLogradouro().getCidade().toString());
            cbEstado.setValue(pc.retornaFuncBusca().getLogradouro().getCidade().getEstado().toString());
            if(pc.retornaFuncBusca().getSexo()=='M')
                cbSexo.setValue("Mascuino");
            if(pc.retornaFuncBusca().getSexo()=='F')
                cbSexo.setValue("Feminino");
            txtLogin.setText(pc.retornaFuncBusca().getLogin());
            txtSenha.setText(pc.retornaFuncBusca().getSenha());
            txtCargo.setText(pc.retornaFuncBusca().getCargo());
            txtSalario.setText(pc.retornaFuncBusca().getSalario()+"");
            String stra = fmt.format(dataadm);
            txtDataAdmissao.setText(stra);
            String strd = fmt.format(datadem);
            txtDataDemissao.setText(strd);
        }
    }

    @FXML
    private void clkNovo(ActionEvent event) throws ControlException {
        inicializa(false);
    }

    @FXML
    private void clkGravar(ActionEvent event) throws ControlException, SQLException, EntidadeException, ParseException {
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
        if(txtDataN.getText()!=null && !txtDataN.getText().isEmpty())
            data = new java.sql.Date(format.parse(txtDataN.getText()).getTime());
        if(txtDataAdmissao.getText()!=null && !txtDataAdmissao.getText().isEmpty())
            dataadm = new java.sql.Date(format.parse(txtDataAdmissao.getText()).getTime());;
        if(txtDataDemissao.getText()!=null && !txtDataDemissao.getText().isEmpty())
            datadem = new java.sql.Date(format.parse(txtDataDemissao.getText()).getTime());
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
        Parent root = FXMLLoader.load(getClass().getResource("/view/LocalizarFuncionario.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Localizar Funcionario");
        stage.setResizable(false);
        stage.showAndWait();
        carregaFuncionario();
    }

    @FXML
    private void clkSair(ActionEvent event) throws IOException {
        Stage stage = (Stage) btSair.getScene().getWindow();
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
        cbCidade.setDisable(estado);
        cbEstado.setDisable(estado);
        cbSexo.setDisable(estado);
        btCancelar.setDisable(estado);
        btGravar.setDisable(estado);
        txtLogin.setDisable(estado);
        txtSenha.setDisable(estado);
        txtCargo.setDisable(estado);
        txtSalario.setDisable(estado);
        txtDataN.setDisable(estado);
        txtDataAdmissao.setDisable(estado);
        txtDataDemissao.setDisable(estado);
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
        cbCidade.setValue("");
        cbEstado.setValue("");
        cbSexo.setPromptText("Sexo");
        txtLogin.setText("");
        txtSenha.setText("");
        txtCargo.setText("");
        txtSalario.setText("");
        txtDataN.setText((""));
        txtDataAdmissao.setText((""));
        txtDataDemissao.setText((""));
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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Deseja mesmo excluir esse funcionario?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                 int resp = pc.excluirFuncionario(codigo);
                 if(resp>0){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Resposta do Servidor");
                    alert.setHeaderText(null);
                    alert.setContentText("Funcionario excluido com sucesso!");
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
            alert.setContentText("Selecione um funcionario para excluir!");
            alert.showAndWait();
        }
    }
    
}

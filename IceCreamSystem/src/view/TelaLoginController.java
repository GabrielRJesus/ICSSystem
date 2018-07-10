package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import control.EmpresaControl;
import control.PessoaControl;
import exception.ControlException;
import exception.EntidadeException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class TelaLoginController implements Initializable {

    @FXML
    private JFXTextField tvLogin;
    @FXML
    private JFXPasswordField tvSenha;
    @FXML
    private JFXButton btEntrar;
    @FXML
    private JFXButton btSair;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clkEntrar(ActionEvent event) throws EntidadeException, ControlException, IOException {
        PessoaControl pc = new PessoaControl();
        EmpresaControl ec = new EmpresaControl();
        //fazer Login - Verifica login e senha
            if(pc.verificaUsuario(tvLogin.getText(), tvSenha.getText())!=null){
                pc.guardaSelecionado(pc.verificaUsuario(tvLogin.getText(), tvSenha.getText()));
                if(ec.retornaEmpresa()!=null){
                    Parent root = FXMLLoader.load(getClass().getResource("/view/TelaPrincipal.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) btSair.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Menu Principal");
                    stage.setX(300);
                    stage.setY(4);
                    stage.setResizable(false);
                    stage.showAndWait();
                    stage.close();
                }else{
                    Parent root = FXMLLoader.load(getClass().getResource("/view/DadosEmpresa.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) btSair.getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle("Dados da Empresa");
                    stage.setX(300);
                    stage.setY(4);
                    stage.setResizable(false);
                    stage.showAndWait();
                    stage.close();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Resposta do Servidor");
                alert.setHeaderText(null);
                alert.setContentText("Login ou senha incorretos!");
                alert.showAndWait();
                tvLogin.setText("");
                tvSenha.setText("");
            }
    }

    @FXML
    private void clkSair(ActionEvent event) {
        Stage stage = (Stage) btSair.getScene().getWindow();
        stage.close();
    }

    
}

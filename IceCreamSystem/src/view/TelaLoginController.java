package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


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
    private void clkEntrar(ActionEvent event) {
    }

    @FXML
    private void clkSair(ActionEvent event) {
    }

    
}

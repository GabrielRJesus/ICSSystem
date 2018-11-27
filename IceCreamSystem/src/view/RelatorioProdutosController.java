/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import control.ProdutoControl;
import control.RelatorioControl;
import exception.ControlException;
import exception.EntidadeException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class RelatorioProdutosController implements Initializable {

    @FXML
    private JFXTextField txtProduto;
    @FXML
    private JFXComboBox<String> cbMarca;
    @FXML
    private JFXRadioButton rbProdFalta;
    @FXML
    private JFXComboBox<String> cbTipo;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btGerarRelatorio;
    @FXML
    private JFXButton btSair;
    
    private RelatorioControl rc = new RelatorioControl();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            inicializa();
            // TODO
            carregacbCategoria();
            carregacbMarca();
        } catch (ControlException ex) {
            Logger.getLogger(RelatorioProdutosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EntidadeException ex) {
            Logger.getLogger(RelatorioProdutosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void clkCancelar(ActionEvent event) {
        inicializa();
    }

    @FXML
    private void clkGerar(ActionEvent event) throws SQLException {
        boolean estoque = false;
        if(rbProdFalta.isSelected())
            estoque = true;
        rc.geraRelatorioProduto(txtProduto.getText(), cbMarca.getValue(), cbTipo.getValue(), estoque);
        
    }

    @FXML
    private void clkSair(ActionEvent event) {
        Stage stage = (Stage)btCancelar.getScene().getWindow();
        stage.close();
    }
    
    public void carregacbMarca() throws ControlException, EntidadeException{
        ProdutoControl pc = new ProdutoControl();
        List<String> lista = new ArrayList<>();
        lista = pc.buscaMarcas();
        ObservableList<String> colection = FXCollections.observableArrayList(lista);
        cbMarca.getItems().addAll(colection);
    }
    
    public void carregacbCategoria() throws ControlException, EntidadeException{
        ProdutoControl pc = new ProdutoControl();
        List<String> lista = new ArrayList<>();
        lista = pc.buscaCategorias();
        ObservableList<String> colection = FXCollections.observableArrayList(lista);
        cbTipo.getItems().addAll(colection);
    }
    
    public void inicializa(){
        txtProduto.setText("");
        rbProdFalta.setSelected(false);
    }


    
    
}

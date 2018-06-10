

import control.EmpresaControl;
import exception.ControlException;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author gabri
 */
public class IceCreamSystem extends Application {
    
    @Override
    public void start(Stage stage) throws IOException, ControlException {
        Parent root = FXMLLoader.load(getClass().getResource("view/TelaLogin.fxml"));  
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        
        stage.show(); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

package frames;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLMenuController {
    public BorderPane borderpane;

    public void ui2(MouseEvent mouseEvent) {

        loadUI("Sell");
    }

    public void ui1(MouseEvent mouseEvent) {
        loadUI("Customers");
    }

    public void clear(MouseEvent mouseEvent) {
        Stage stage = (Stage) borderpane.getScene().getWindow();
        stage.close();
    }
    private void loadUI(String ui ){
        Parent root = null;
        try {
          root =  FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderpane.setCenter(root);

    }

    public void urunler(MouseEvent mouseEvent) {
        loadUI("urunlerUI");
    }
}

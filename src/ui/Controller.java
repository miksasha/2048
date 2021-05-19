package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;

public class Controller {
    @FXML
    private Button firstButton;
    @FXML
    private Image firstLock;
    @FXML
    private AnchorPane anchorPane;
    private Main m=new Main();

    public void onClickFirst(ActionEvent actionEvent) throws Exception {
        //спроба видалити замок, поки не виходить
     //   anchorPane.getChildren().remove((Collection<?>) firstLock);

        Stage myStage=new Stage();
        m.levels(myStage);
        myStage.show();
    }

    public void onClickSecond(ActionEvent actionEvent) {
    }

    public void onClickThree(ActionEvent actionEvent) {
    }

    public void onClickFour(ActionEvent actionEvent) {
    }

    public void onClickFive(ActionEvent actionEvent) {
    }

    public void onClickAsk(ActionEvent actionEvent) throws Exception {
        m.instruction(m.menu);
    }
}

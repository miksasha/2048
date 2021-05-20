package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;

public class Controller {
    @FXML
    public ImageView firstLock2;
    public ImageView firstLock3;
    public ImageView firstLock4;
    public ImageView firstLock5;
    @FXML
    private Button firstButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ComboBox handChoice;


    private Main m=new Main();
    private static Stage myStageInst;

    @FXML protected void actionBox(ActionEvent event ) {
        String output = (String) handChoice.getValue();
        if(output.equals("Шульга")){
m.rightHanded=false;
        }
    }

    public void onClickFirst(ActionEvent actionEvent) throws Exception {
        // видалення замочка
       // anchorPane.getChildren().remove( firstLock3);

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
        m.menu.hide();
        myStageInst=new Stage();
        m.instruction(myStageInst);
    }
    public void onClickAskButton(ActionEvent actionEvent) {
        m.menu.show();
        myStageInst.close();
    }
}

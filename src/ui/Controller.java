package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Controller {

    @FXML
    private AnchorPane anchorPane=new AnchorPane();
    @FXML
    private ComboBox handChoice;

    public static boolean level2Open =false;
    public static boolean level3Open =false;
    public static boolean level4Open =false;
    public static boolean level5Open =false;
    public static boolean level6Open =false;
    public static boolean level7Open =false;
    public static boolean level8Open =false;
    public static boolean level9Open =false;

    private static Stage myStageInst;
    private Main m=new Main();
    private Stage myStage=new Stage();

    @FXML protected void actionBox(ActionEvent event ) {
        String output = (String) handChoice.getValue();
        if(output.equals("Шульга")){
            m.rightHanded=false;
        }else{
            m.rightHanded=true;
        }
    }

    public void onClickFirst(ActionEvent actionEvent) throws Exception {

        m.pirate.setVisible(false);
        m.dialog.setVisible(false);
        m.text.setVisible(false);

        m.levels(myStage,1);
        myStage.show();

        m.mainMusic.stop();
        m.musicLaugh();
        m.mainMusic.play();
    }

    public void onClickSecond(ActionEvent actionEvent) throws Exception {
        if(level2Open){
            m.levels(myStage,2);
            myStage.show();

            m.mainMusic.stop();
            m.musicAllLaugh();
            m.mainMusic.play();
        }
    }

    public void onClickThree(ActionEvent actionEvent) throws Exception {
        if(level3Open){
            m.levels(myStage,3);
            myStage.show();

            m.mainMusic.stop();
            m.musicLaugh();
            m.mainMusic.play();
        }
    }

    public void onClickFour(ActionEvent actionEvent) throws Exception {
        if(level4Open){
            m.levels(myStage,4);
            myStage.show();

            m.mainMusic.stop();
            m.musicAllLaugh();
            m.mainMusic.play();
        }
    }

    public void onClickFive(ActionEvent actionEvent) throws Exception {
        if(level5Open){
            m.levels(myStage,5);
            myStage.show();

            m.mainMusic.stop();
            m.musicRRR();
            m.mainMusic.play();
        }
    }

    public void onClickSix(ActionEvent actionEvent) throws Exception {
        if(level6Open){
            m.levels(myStage,6);
            myStage.show();

            m.mainMusic.stop();
            m.musicRRR();
            m.mainMusic.play();
        }
    }

    public void onClickSeven(ActionEvent actionEvent) throws Exception {
        if(level7Open){
            m.levels(myStage,7);
            myStage.show();

            m.mainMusic.stop();
            m.musicRRR();
            m.mainMusic.play();
        }
    }
    public void onClickEight(ActionEvent actionEvent) throws Exception {
        if(level8Open){
            m.levels(myStage,8);
            myStage.show();

            m.mainMusic.stop();
            m.musicRRR();
            m.mainMusic.play();
        }
    }

    public void onClickNine(ActionEvent actionEvent) throws Exception {
        if(level9Open){
            m.levels(myStage,9);
            myStage.show();

            m.mainMusic.stop();
            m.musicRRR();
            m.mainMusic.play();
        }
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

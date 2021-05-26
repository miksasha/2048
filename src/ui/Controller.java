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
    private Button firstButton;
    @FXML
    private AnchorPane anchorPane=new AnchorPane();
    @FXML
    private ComboBox handChoice;

    public boolean level2Open =false;
    public boolean level3Open =false;
    public boolean level4Open =false;
    public boolean level5Open =false;
    public boolean level6Open =false;
    public boolean level7Open =false;
    public boolean level8Open =false;
    public boolean level9Open =false;

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

    }

    public void onClickSecond(ActionEvent actionEvent) throws Exception {
        if(level2Open){
            m.levels(myStage,2);
            myStage.show();
        }
    }

    public void onClickThree(ActionEvent actionEvent) {
        if(level3Open){
            //викликати наступний рівень

        }
    }

    public void onClickFour(ActionEvent actionEvent) {
        if(level4Open){
            //викликати наступний рівень

        }
    }

    public void onClickFive(ActionEvent actionEvent) {
        if(level5Open){
            //викликати наступний рівень

        }
    }

    public void onClickSix(ActionEvent actionEvent) {
        if(level6Open){
            //викликати наступний рівень

        }
    }

    public void onClickSeven(ActionEvent actionEvent) {
        if(level7Open){
            //викликати наступний рівень

        }
    }
    public void onClickEight(ActionEvent actionEvent) {
        if(level8Open){
            //викликати наступний рівень

        }
    }

    public void onClickNine(ActionEvent actionEvent) {
        if(level9Open){
            //викликати наступний рівень

        }
    }

//    public void isWin(int level, boolean win)  throws Exception {
//        if (level == 1 && win) {
//          //  anchorPane.getChildren().remove(lock2);
//            level2Open = true;
//        } else {
//            if (level == 2 && win) {
//            //    anchorPane.getChildren().remove(lock3);
//                level3Open = true;
//            } else {
//                if (level == 3 && win) {
//                    anchorPane.getChildren().remove(lock4);
//                    level4Open = true;
//                } else {
//                    if (level == 4 && win) {
//                        anchorPane.getChildren().remove(lock5);
//                        level5Open = true;
//                    } else {
//                        if (level == 5 && win) {
//                            anchorPane.getChildren().remove(lock6);
//                            level6Open = true;
//                        } else {
//                            if (level == 6 && win) {
//                                anchorPane.getChildren().remove(lock7);
//                                level7Open = true;
//                            } else {
//                                if (level == 7 && win) {
//                                    anchorPane.getChildren().remove(lock8);
//                                    level8Open = true;
//                                } else {
//                                    if (level == 8 && win) {
//                                        anchorPane.getChildren().remove(lock9);
//                                        level9Open = true;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

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

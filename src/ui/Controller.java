package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {
    @FXML
    public ImageView lock2;
    public ImageView lock3;
    public ImageView lock4;
    public ImageView lock5;
    public ImageView lock6;
    public ImageView lock7;
    @FXML
    private Button firstButton;
    @FXML
    private AnchorPane anchorPane=new AnchorPane();
    @FXML
    private ComboBox handChoice;


    private Main m=new Main();
    private static Stage myStageInst;
    private boolean level2Open =false;
    private boolean level3Open =false;
    private boolean level4Open =false;
    private boolean level5Open =false;
    private boolean level6Open =false;
    private boolean level7Open =false;

    @FXML protected void actionBox(ActionEvent event ) {
        String output = (String) handChoice.getValue();
        if(output.equals("Шульга")){
            m.rightHanded=false;
        }else{
            m.rightHanded=true;
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
        if(level2Open){
            //викликати наступний рівень

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

    public void isWin(int level, boolean win)  throws Exception{
        if(level==1 && win){
            anchorPane.getChildren().remove( lock2);
            level2Open =true;
        }else{
            if(level==2 && win){
                anchorPane.getChildren().remove( lock3);
                level3Open =true;
            }else{
                if(level==3 && win){
                    anchorPane.getChildren().remove( lock4);
                    level4Open =true;
                }else{
                    if(level==4 && win){
                        anchorPane.getChildren().remove( lock5);
                        level5Open =true;
                    }else{
                        if(level==5 && win){
                            anchorPane.getChildren().remove( lock6);
                            level6Open =true;
                        }else{
                            if(level==6 && win){
                                anchorPane.getChildren().remove( lock7);
                                level7Open =true;
                            }
                        }
                    }
                }
            }
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

package ui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;


public class Main extends Application {



    public int level=1;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage myStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
        myStage.setTitle("2048 GAME");
        myStage.setScene(new Scene(root, 500, 400));
        //levels(myStage);
        myStage.show();
    }

    public void levels(Stage myStage) throws Exception{
        myStage.setTitle("Chernova+Mykhailenko=2048");

        FlowPane rootNode = new FlowPane();
        rootNode.getStyleClass().add("bg-sea-style");
        myStage.setResizable(false);
        myStage.setOnCloseRequest(event -> Platform.exit());

        Logic logic = new Logic();
        Scene myScene = new Scene(rootNode, logic.getWidth(), logic.getHeight());
        myScene.getStylesheets().add(getClass().getResource("design.css").toExternalForm());

        myStage.setScene(myScene);

        myScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SHIFT) {
                    logic.resetGame();
                }
                if (keyEvent.getCode() == KeyCode.F2) {
                    logic.addCellTwo();
                }

                if (!logic.canMove() || (!logic.win && !logic.canMove())) {
                    logic.lose = true;
                }
                if (keyEvent.getCode() == KeyCode.F4) {
                    logic.addCellFour();
                }
                if (!logic.win && !logic.lose) {

                    if (keyEvent.getCode() == KeyCode.UP) {
                        logic.up();
                    }  if (keyEvent.getCode() == KeyCode.DOWN) {
                        logic.down();
                    }  if (keyEvent.getCode() == KeyCode.LEFT) {
                        logic.left();
                    }  if (keyEvent.getCode() == KeyCode.RIGHT) {
                        logic.right();
                    }

                }
                if (keyEvent.getCode() == KeyCode.F8) {
                    logic.addCellEight();
                }
                // logic.relocate(330, 390);
            }
        });

        rootNode.getChildren().add(logic);

        myStage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext gc = logic.getGraphicsContext2D();
//                Image image = new Image("/C:/Liza/Summer2021/Game2048/src/ui/img.png");
//                gc.drawImage(image,10,10,CELL_SIZE,CELL_SIZE);
                gc.setFill(Color.LIGHTBLUE);

                gc.fillRect(0, 0, logic.getWidth(), logic.getHeight());

                for(int y = 0; y < logic.amoungOfLines; y++) {
                    for(int x = 0; x < logic.amoungOfLines; x++){
                        Cell cell = logic.getAllcells()[x + y * logic.amoungOfLines];
                        int value = cell.number;
                        int xOffset = offsetCoors(x);
                        int yOffset = offsetCoors(y);

//                        Image flower = new Image(flowerURL);
//                        gc.setFill(new ImagePattern(flower, 0, 0, 1, 1, true));
                        gc.setFill(cell.getBackground());
                        gc.fillOval(xOffset, yOffset, logic.CELL_SIZE, logic.CELL_SIZE);
                        gc.setFill(cell.getForeground());

                       int size = value < 30 ? 32 : value < 80 ? 37 : 40;
                        gc.setFont(Font.font("Verdana", FontWeight.BOLD, size));
                        gc.setTextAlign(TextAlignment.CENTER);


                        String s = String.valueOf(value);

                        if (value != 0)
                            gc.fillText(s, xOffset + logic.CELL_SIZE / 2, yOffset + logic.CELL_SIZE / 2 - 2);
                        if(logic.win || logic.lose) {
                            gc.setFill(Color.rgb(255, 255, 255));
                            gc.fillRect(0, 0, logic.getWidth(), logic.getHeight());
                            gc.setFill(Color.rgb(78, 139, 202));
                            gc.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
                            if(logic.win){
                                gc.fillText("You win!", 95, 150);
                            }
                            if(logic.lose) {
                                gc.fillText("Game over!", 150, 130);
                                gc.fillText("You lose!", 160, 200);
                            }
                            if(logic.win || logic.lose) {
                                gc.setFont(Font.font("Verdana", FontWeight.LIGHT, 16));
                                gc.setFill(Color.rgb(128, 128, 128));
                                gc.fillText("Press Shift to play again", 110, 270);
                            }
                        }
                        gc.setFont(Font.font("Verdana", FontWeight.LIGHT, 18));
                        gc.fillText("Score: " + logic.score, 200, 350);
                    }
                }
            }
        }.start();
    }

    private static int offsetCoors(int arg) {
        return arg * (16 + 64) + 16;
    }

}

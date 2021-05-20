package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.awt.*;

import javafx.geometry.Insets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Main extends Application {

    public int level = 1;
    KTimer time;
    boolean rightHanded = true;

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage menu;
    public static Stage instruction;
    public static Parent root;

    @Override
    public void start(Stage myStage) throws Exception {

        root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
        myStage.setTitle("Chernova+Mykhailenko=2048");
        myStage.setScene(new Scene(root, 500, 475));
        myStage.setResizable(false);
        // levels(myStage);
        myStage.show();
        menu = myStage;
    }

    public void instruction(Stage myStage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("instruction_window.fxml"));
        myStage.setTitle("Chernova+Mykhailenko=2048");
        myStage.setScene(new Scene(root, 500, 475));
        myStage.setResizable(false);
        myStage.show();
    }

    public void levels(Stage myStage) throws Exception {
        time = new KTimer();
        time.startTimer(00);

        // myStage.setTitle("Chernova+Mykhailenko=2048");
        FlowPane rootNode = new FlowPane();

        myStage.setResizable(true);
        myStage.setOnCloseRequest(event -> Platform.exit());

        Logic logic = new Logic();


        Scene myScene = new Scene(rootNode, logic.getWidth(), logic.getHeight());
        myScene.getStylesheets().add(getClass().getResource("design.css").toExternalForm());

        myStage.setScene(myScene);

        myScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SHIFT) {

                    time.stopTimer();
                    logic.startNewGame();
                    time.startTimer(00);
                }
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if (logic.winning) {
                        level++;

                        logic.amountOfLines = level + 1;
                       // logic.CELL_SIZE=320/logic.amountOfLines;
                        logic.maxNumber = level * 10 + 10;
                        time.stopTimer();
                        logic.startNewGame();
                        if (logic.maxNumber >= 100) {
                            logic.maxNumber = 100;
                        }
                        time.startTimer(00);
                    } else {
                        JOptionPane.showMessageDialog(null, "You can't see next level? press SHIFT to play this one one more time");
                    }
                }
                if (keyEvent.getCode() == KeyCode.F1) {
                    logic.CellTen();
                }

                if (logic.checkIfStepIsNotAvalible() || (!logic.winning && logic.checkIfStepIsNotAvalible())) {
                    logic.fail = true;
                }
                if (keyEvent.getCode() == KeyCode.F2) {
                    logic.CellTwenty();
                }
                if (!logic.winning && !logic.fail) {
                    if (rightHanded) {
                        if (keyEvent.getCode() == KeyCode.UP) {
                            logic.up();
                        }
                        if (keyEvent.getCode() == KeyCode.DOWN) {
                            logic.down();
                        }
                        if (keyEvent.getCode() == KeyCode.LEFT) {
                            logic.left();
                        }
                        if (keyEvent.getCode() == KeyCode.RIGHT) {
                            logic.right();
                        }
                    } else {
                        if (keyEvent.getCode() == KeyCode.W) {
                            logic.up();
                        }
                        if (keyEvent.getCode() == KeyCode.S) {
                            logic.down();
                        }
                        if (keyEvent.getCode() == KeyCode.A) {
                            logic.left();
                        }
                        if (keyEvent.getCode() == KeyCode.D) {
                            logic.right();
                        }
                    }

                }
                if (keyEvent.getCode() == KeyCode.F3) {
                    logic.Celltherty();
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

                gc.setFill(Color.ROSYBROWN);

                gc.fillRect(0, 0, logic.getWidth(), logic.getHeight());

                for (int y = 0; y < logic.amountOfLines; y++) {
                    for (int x = 0; x < logic.amountOfLines; x++) {
                        Cell cell = logic.getAllcells()[x + y * logic.amountOfLines];
                        int value = cell.number;
                        int xOffset = offsetCoors(x);
                        int yOffset = offsetCoors(y);

                        gc.setFill(cell.getBackground());
                        gc.fillOval(xOffset, yOffset, logic.CELL_SIZE, logic.CELL_SIZE);
                        gc.setFill(cell.getForeground());

                        int size = value < 30 ? 32 : value < 80 ? 37 : 40;
                        gc.setFont(Font.font("Verdana", FontWeight.BOLD, size));
                        gc.setTextAlign(TextAlignment.CENTER);


                        String s = String.valueOf(value);

                        if (value != 0)
                            gc.fillText(s, xOffset + logic.CELL_SIZE / 2, yOffset + logic.CELL_SIZE / 2 - 2);
                        if (logic.winning || logic.fail) {
                            time.stopTimer();
                            gc.setFill(Color.ROSYBROWN);
                            gc.fillRect(0, 0, logic.getWidth(), logic.getHeight());
                            gc.setFill(Color.rgb(182, 43, 64));
                            gc.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                            if (logic.winning) {
                                gc.fillText("You win! With time " + time.getTime() / 1000 + " sec", 95, 150);
                            }
                            if (logic.fail) {
                                logic.lives--;
                                gc.fillText("Game over! With time " + time.getTime() / 1000 + " sec", 150, 130);
                                gc.fillText("You lost one life!", 160, 200);
                            }
                            if (logic.winning || logic.fail) {
                                gc.setFont(Font.font("Verdana", FontWeight.LIGHT, 16));
                                gc.setFill(Color.rgb(182, 43, 64));
                                gc.fillText("Press Shift to play again", 110, 270);
                            }
                        }
                        gc.setFont(Font.font("Verdana", FontWeight.LIGHT, 18));
                        gc.fillText("Score: " + logic.score, 200, 350);
                        gc.setFont(Font.font("Verdana", FontWeight.LIGHT, 18));
                        gc.fillText("Time: " + time.getTime() / 1000, 200, 370);
                        gc.setFont(Font.font("Verdana", FontWeight.LIGHT, 18));
                        gc.fillText("Lifes: " + logic.lives, 200, 390);
                    }
                }
            }
        }.start();
    }

    private static int offsetCoors(int arg) {
        return arg * (16 + 64) + 16;
    }
}

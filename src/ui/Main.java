package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import javafx.scene.image.Image;


public class Main extends Application {

    public int level = 5;
    KTimer time;
    boolean rightHanded = true;
    String fraze = "Ви перемогли\nодин рівень\n       Скарби вам не знайти!!!";

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage menu;
    public static Stage instruction;
    public static Parent root;
    public Logic logic;

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

        myStage.setResizable(false);

        myStage.setOnCloseRequest(event -> Platform.exit());
        logic = new Logic();


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
                        if (level >= 4) {
                            logic.setWidth((level - 3) * 80 + 400);
                            logic.setHeight((level - 3) * 80 + 500);
                            myStage.setWidth((level - 3) * 80 + 400);
                            myStage.setHeight((level - 3) * 80 + 500);
                        }


                        if (level < 6) {
                            logic.amountOfLines = level + 1;
                            logic.maxNumber = level * 10 + 10;
                        } else {
                            logic.ice = true;
                            logic.amountOfLines = 4;
                            logic.maxNumber = 100;
                        }
                        speak(level);
                        time.stopTimer();
                        logic.startNewGame();
                        if (logic.maxNumber >= 100) {
                            logic.maxNumber = 100;
                        }
                        time.startTimer(00);
                    } else {
                        JOptionPane.showMessageDialog(null, "You can't see next level, press SHIFT to play this one one more time");
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

                        gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 18));
                        gc.fillText("Бали: " + logic.score, 200, logic.getHeight() - 150);
                        gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 18));
                        gc.fillText("Час: " + time.getTime() / 1000, 200, logic.getHeight() - 130);
                        gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 18));
                        gc.fillText("Життя: " + logic.lives, 200, logic.getHeight() - 110);

                        String s = String.valueOf(value);

                        if (value != 0)
                            gc.fillText(s, xOffset + logic.CELL_SIZE / 2, yOffset + logic.CELL_SIZE / 2 - 2);
                        if (logic.winning || logic.fail) {
                            time.stopTimer();
                            gc.setFill(Color.ROSYBROWN);
                            gc.fillRect(0, 0, logic.getWidth(), logic.getHeight());
                            gc.setFill(Color.WHITE);
                            gc.setFont(Font.font("Elephant", FontWeight.BOLD, 15));
                            if (logic.winning) {
                                Image cup = new Image("img/win.png");
                                gc.drawImage(cup, 120, 15, 150, 150);
                                Image pirate = new Image("img/Pirat.png");
                                if (level < 4) {
                                    gc.drawImage(pirate, 10, logic.getHeight() - 160, 140, 150);
                                } else {
                                    gc.drawImage(pirate, 10, logic.getHeight() - 200, 140, 150);
                                }
                                Image words = new Image("img/Word.png");
                                if (level < 4) {
                                    gc.drawImage(words, logic.getWidth() - 240, logic.getHeight() - 160, 220, 160);
                                } else {
                                    gc.drawImage(words, logic.getWidth() -330, logic.getHeight() - 160, 220, 160);
                                }

                                gc.fillText("Ви перемогли!\n Час: " + time.getTime() / 1000 + " с", logic.getHeight() - 310, 180);
                                gc.fillText(fraze, logic.getWidth() - 130, logic.getHeight() - 100);
                                //  gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 18));
                                Button screenshot = new Button("Зберегти рекорд!");

                                gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 18));
                                gc.fillText("Бали: " + logic.score, 50, 20);
//                        gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 18));
//                        gc.fillText("Час: " + time.getTime() / 1000, 200, 370);
                                gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 18));
                                gc.fillText("Життя: " + logic.lives, logic.getWidth() - 170, 20);

                            }
                            if (logic.fail) {
                                logic.lives--;
                                Image image = new Image("img/skull.png");
                                gc.drawImage(image, 0, 0, 100, 100);
                                gc.fillText("Ви програли!\n Час: " + time.getTime() / 1000 + " с", 130, 150);
                                gc.fillText("Ви втратили одне життя!", 160, 200);
                            }
                            if (logic.winning || logic.fail) {
                                gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 16));
                                gc.setFill(Color.WHITE);
                                gc.fillText("Натисніть Shift, щоб почати знову ", 150, 270);
                                gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 16));
                                gc.setFill(Color.WHITE);
                                gc.fillText("Натисніть Enter, щоб продовжити ", 150, 290);
                            }
                        }
                    }
                }
            }
        }.start();
    }

    private void speak(int level) {

        switch (level) {
            case 2: {
                fraze = "Заробляйте монети\nтільки так\nВи знайдете те, що шукали";
                break;
            }
            case 3: {
                fraze = "3 рівень";
                break;
            }
            case 4: {
                fraze = "4 рівень";
                break;
            }
            case 5: {
                fraze = "Льодові кулі\nможуть вас зупинити";
                break;
            }
            case 6: {
                fraze = "Якщо у вас є\n500 монет\nви зможете пройти";
                break;
            }
        }
    }

    private static int offsetCoors(int arg) {
        return arg * (16 + 64) + 16;
    }
}

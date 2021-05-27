package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.media.AudioClip;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.stage.WindowEvent;

public class Main extends Application {

    public int level = 1;
    KTimer time;
    boolean rightHanded = true;
    String fraze = "Ви перемогли\nлише один рівень\n       Скарби вам не знайти!!!";
    String frazeHappy = "Ха-ха-ха!\n А ви ще\n сподівались\n на перемогу...!";
    public static Stage menu;
    public static Stage instruction;
    public static Parent root;
    public Logic logic = new Logic(level);

    private static Group rootGr;
    private static ImageView iz2;
    private static ImageView iz3;
    private static ImageView iz4;
    private static ImageView iz5;
    private static ImageView iz6;
    private static ImageView iz7;
    private static ImageView iz8;
    private static ImageView iz9;
    private static ImageView gold;

    public static ImageView pirate;
    public static ImageView pirate2;
    public static ImageView dialog;
    public static ImageView paper;
    public static ImageView paperWin;
    public static Label text;
    public static Label text2;

    public static AudioClip mainMusic;
    public static AudioClip smile;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage myStage) throws Exception {
        Image zamok = new Image("img/zamok.png");
        iz2 = new ImageView(zamok);
        iz2.setPreserveRatio(true);
        iz2.setX(237);
        iz2.setY(335);
        iz2.setFitHeight(31);
        iz2.setFitWidth(30);

        iz3 = new ImageView(zamok);
        iz3.setPreserveRatio(true);
        iz3.setX(85);
        iz3.setY(279);
        iz3.setFitHeight(31);
        iz3.setFitWidth(30);

        iz4 = new ImageView(zamok);
        iz4.setPreserveRatio(true);
        iz4.setX(266);
        iz4.setY(228);
        iz4.setFitHeight(31);
        iz4.setFitWidth(30);

        iz5 = new ImageView(zamok);
        iz5.setPreserveRatio(true);
        iz5.setX(451);
        iz5.setY(174);
        iz5.setFitHeight(31);
        iz5.setFitWidth(30);

        iz6 = new ImageView(zamok);
        iz6.setPreserveRatio(true);
        iz6.setX(248);
        iz6.setY(166);
        iz6.setFitHeight(31);
        iz6.setFitWidth(30);

        iz7 = new ImageView(zamok);
        iz7.setPreserveRatio(true);
        iz7.setX(85);
        iz7.setY(128);
        iz7.setFitHeight(31);
        iz7.setFitWidth(30);

        iz8 = new ImageView(zamok);
        iz8.setPreserveRatio(true);
        iz8.setX(266);
        iz8.setY(81);
        iz8.setFitHeight(31);
        iz8.setFitWidth(30);

        iz9 = new ImageView(zamok);
        iz9.setPreserveRatio(true);
        iz9.setX(360);
        iz9.setY(14);
        iz9.setFitHeight(31);
        iz9.setFitWidth(30);

        Image p = new Image("img/SmilePirate.png");
        pirate = new ImageView(p);
        pirate.setPreserveRatio(true);
        pirate.setX(0);
        pirate.setY(249);
        pirate.setFitHeight(178);
        pirate.setFitWidth(170);

        Image d = new Image("img/dialog.png");
        dialog = new ImageView(d);
        dialog.setPreserveRatio(true);
        dialog.setX(95);
        dialog.setY(164);
        dialog.setFitHeight(150);
        dialog.setFitWidth(200);

        Image pi = new Image("img/Pirat.png");
        pirate2 = new ImageView(pi);
        pirate2.setPreserveRatio(true);
        pirate2.setX(0);
        pirate2.setY(249);
        pirate2.setFitHeight(178);
        pirate2.setFitWidth(170);
        pirate2.setVisible(false);


        Image g = new Image("img/gold.png");
        gold = new ImageView(g);
        gold.setPreserveRatio(true);
        gold.setX(95);
        gold.setY(144);
        gold.setFitHeight(300);
        gold.setFitWidth(300);
        gold.setVisible(false);

        Image sms = new Image("img/paper.png");
        paper = new ImageView(sms);
        paper.setPreserveRatio(true);
        paper.setX(240);
        paper.setY(14);
        paper.setFitHeight(250);
        paper.setFitWidth(250);
        paper.setVisible(false);

        Image smsWin = new Image("img/paperWin.png");
        paperWin = new ImageView(smsWin);
        paperWin.setPreserveRatio(true);
        paperWin.setX(140);
        paperWin.setY(10);
        paperWin.setFitHeight(200);
        paperWin.setFitWidth(200);
        paperWin.setVisible(false);

        text = new Label("   Ми вкрали ваші скарби \nі сховали на острові! Вам ніколи\n             їх не знайти!\n                Ха-ха-ха!");
        text.setLayoutX(112);
        text.setLayoutY(182);

        text2 = new Label("   Ніііі! Як вам це вдалось??? \n       Думаєте це кінець?\n          Ви ще пошкодуєте!");
        text2.setLayoutX(112);
        text2.setLayoutY(182);
        text2.setVisible(false);

        root = FXMLLoader.load(getClass().getResource("main_window.fxml"));
        myStage.setTitle("Chernova+Mykhailenko=2048");
        rootGr = new Group(root, iz2, iz3, iz4, iz5, iz6, iz7, iz8, iz9, pirate, dialog, text, pirate2, text2, gold, paper,paperWin);
        myStage.setScene(new Scene(rootGr, 500, 475));
        myStage.setResizable(false);

        myStage.show();
        music();

        menu = myStage;
    }

    public void musicLaugh() {
        String bip = "src/music/laugh.wav";
        Media hit = new Media(Paths.get(bip).toUri().toString());
        AudioClip smile = new AudioClip(hit.getSource());
        smile.setVolume(10);
        smile.play();
    }

    public void music() {
        String bip = "src/music/mus.mp3";
        Media hit = new Media(Paths.get(bip).toUri().toString());
        mainMusic = new AudioClip(hit.getSource());
        mainMusic.setVolume(10);
        mainMusic.play();
    }

    public void musicAllLaugh() {
        String bip = "src/music/allL.wav";
        Media hit = new Media(Paths.get(bip).toUri().toString());
        AudioClip allLaugh = new AudioClip(hit.getSource());
        allLaugh.setVolume(10);
        allLaugh.play();
    }

    public void musicRRR() {
        String bip = "src/music/rrr.wav";
        Media hit = new Media(Paths.get(bip).toUri().toString());
        AudioClip rrr = new AudioClip(hit.getSource());
        rrr.setVolume(10);
        rrr.play();
    }

    public void musicWin() {
        String bip = "src/music/win.wav";
        Media hit = new Media(Paths.get(bip).toUri().toString());
        AudioClip allLaugh = new AudioClip(hit.getSource());
        allLaugh.setVolume(10);
        allLaugh.play();
    }

    public void instruction(Stage myStage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("instruction_window.fxml"));
        myStage.setTitle("Chernova+Mykhailenko=2048");
        myStage.setScene(new Scene(root, 500, 475));
        myStage.setResizable(false);

        myStage.show();
    }

    public static void savePicture(Canvas myStage, String path) {
        WritableImage image = myStage.snapshot(new SnapshotParameters(), null);
        File file = new File(path);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void levels(Stage myStage, int lev) throws Exception {
        time = new KTimer();
        time.startTimer(00);
        level = lev;

        myStage.setTitle("Chernova+Mykhailenko=2048");
        FlowPane rootNode = new FlowPane();

        myStage.setResizable(false);

        myStage.setOnCloseRequest(event -> Platform.exit());
        logic = new Logic(level);
        if (level >= 4) {
            logic.setWidth((logic.amountOfLines - 4) * 80 + 400);
            logic.setHeight((logic.amountOfLines - 4) * 80 + 500);
            myStage.setWidth((logic.amountOfLines - 4) * 80 + 400);
            myStage.setHeight((logic.amountOfLines - 4) * 80 + 500);
        }
        Scene myScene = new Scene(rootNode, logic.getWidth(), logic.getHeight());
        myScene.getStylesheets().add(getClass().getResource("design.css").toExternalForm());

        myStage.setScene(myScene);

        myScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SHIFT) {
                    if (logic.fail) {
                        logic.lives--;
                    }
                    if (logic.lives == 0) {
                        level = 1;
                        logic.score = 0;
                        logic.amountOfLines = level + 1;
                        logic.maxNumber = level * 10 + 10;
                    }
                    time.stopTimer();
                    logic.startNewGame();
                    time.startTimer(00);
                }
                if (keyEvent.getCode() == KeyCode.ENTER) {

                    if (logic.winning) {
                        level++;
                        if (level <= 6) {

                            logic.amountOfLines = level + 1;
                            logic.maxNumber = level * 10 + 10;
                        } else if (level == 7) {
                            logic.ice = true;
                            logic.amountOfLines = 6;
                            logic.maxNumber = 80;
                        } else if (level == 8) {
                            logic.ice = true;
                            logic.amountOfLines = 7;
                            logic.maxNumber = 90;
                        } else if(level==9){
                            logic.ice = false;
                            logic.amountOfLines = 7;
                            logic.maxNumber = 100;
                        }else{
                            myStage.close();
                        }

                        if (level >= 4) {
                            logic.setWidth((logic.amountOfLines - 4) * 80 + 400);
                            logic.setHeight((logic.amountOfLines - 4) * 80 + 500);
                            myStage.setWidth((logic.amountOfLines - 4) * 80 + 400);
                            myStage.setHeight((logic.amountOfLines - 4) * 80 + 500);
                        }


                        speak(level);

                        time.stopTimer();
                        logic.startNewGame();

                        time.startTimer(00);
                    } else {
                        speakHappy(level);
                        JOptionPane.showMessageDialog(null, "You can't see next level, press SHIFT to play this one one more time");
                    }
                }

                if (logic.checkIfStepIsNotAvalible() || (!logic.winning && logic.checkIfStepIsNotAvalible())) {
                    logic.fail = true;

                }
                Random random = new Random();

                if (keyEvent.getCode() == KeyCode.PRINTSCREEN) {
                    String res = "_";
                    File f;
                    File dir = new File("C:\\GameResults");
                    if (!dir.exists() || !dir.isDirectory()) {
                        try {

                            Path path = Paths.get("C:\\GameResults");

                            Files.createDirectories(path);


                        } catch (IOException e) {

                            System.err.println("Failed to create directory!" + e.getMessage());

                        }
                    }
                    do {
                        for (int i = 0; i < 5; i++) {
                            res += random.nextInt(10);
                        }
                        f = new File("C:\\GameResults\\" + level + "level" + res + ".png");

                    } while (f.exists());
                    savePicture(logic, "C:\\GameResults\\" + level + "level" + res + ".png");
                }
                if (level >= 1) {
                    if (keyEvent.getCode() == KeyCode.F1) {
                        logic.CellTen();
                    }
                    if (level >= 2) {
                        if (keyEvent.getCode() == KeyCode.F2) {
                            logic.CellTwenty();
                        }
                    }
                    if (level >= 3) {
                        if (keyEvent.getCode() == KeyCode.F3) {
                            logic.Celltherty();
                        }
                    }
                    if (level >= 4) {
                        if (keyEvent.getCode() == KeyCode.F4) {
                            logic.newNotRandomCellAdding(40);
                        }
                    }
                    if (level >= 5) {
                        if (keyEvent.getCode() == KeyCode.F5) {
                            logic.newNotRandomCellAdding(50);
                        }
                    }
                    if (level >= 6) {
                        if (keyEvent.getCode() == KeyCode.F6) {
                            logic.newNotRandomCellAdding(60);
                        }
                    }
                    if (level >= 7) {
                        if (keyEvent.getCode() == KeyCode.F7) {
                            logic.newNotRandomCellAdding(70);
                        }
                    }
                    if (level >= 8) {
                        if (keyEvent.getCode() == KeyCode.F8) {
                            logic.newNotRandomCellAdding(80);
                        }
                    }
                    if (level >= 9) {
                        if (keyEvent.getCode() == KeyCode.F9) {
                            logic.newNotRandomCellAdding(90);
                        }
                    }
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

                // logic.relocate(330, 390);
            }
        });

        rootNode.getChildren().add(logic);

        myStage.show();

        var animationTimer= new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext gc = logic.getGraphicsContext2D();

                gc.setFill(Color.ROSYBROWN);

                gc.fillRect(0, 0, logic.getWidth(), logic.getHeight());
                for (int y = 0; y < logic.amountOfLines; y++) {
                    for (int x = 0; x < logic.amountOfLines; x++) {
                        Cell cell = logic.getAllcells()[x + y * logic.amountOfLines];
                        int value = cell.number;
                        int xOffset = changeC(x);
                        int yOffset = changeC(y);

                        gc.setFill(cell.getBackground());
                        gc.fillOval(xOffset, yOffset, logic.CELL_SIZE, logic.CELL_SIZE);
                        gc.setFill(cell.getForeground());

                        int size = value < 30 ? 32 : value < 80 ? 37 : 40;
                        gc.setFont(Font.font("Verdana", FontWeight.BOLD, size));
                        gc.setTextAlign(TextAlignment.CENTER);

                        gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 18));
                        gc.fillText("Бали: " + logic.score, logic.getWidth() / 2, logic.getHeight() - 130);
                        gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 18));
                        gc.fillText("Час: " + time.getTime() / 1000, logic.getWidth() / 2, logic.getHeight() - 110);
                        gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 18));
                        gc.fillText("Життя: " + logic.lives, logic.getWidth() / 2, logic.getHeight() - 90);
                        gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 18));
                        gc.fillText("Рівень: " + level, logic.getWidth() - 65, logic.getHeight() - 45);
                        gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 18));
                        gc.fillText("Потрібне число: " + logic.maxNumber, 90, logic.getHeight() - 45);
                        String s = String.valueOf(value);

                        if (value != 0)
                            gc.fillText(s, xOffset + logic.CELL_SIZE / 2, yOffset + logic.CELL_SIZE / 2 - 2);

                    }
                }
                if (logic.winning || logic.fail) {
                    time.stopTimer();
                    gc.setFill(Color.ROSYBROWN);
                    gc.fillRect(0, 0, logic.getWidth(), logic.getHeight());
                    gc.setFill(Color.WHITE);
                    gc.setFont(Font.font("Elephant", FontWeight.BOLD, 15));
                    if (logic.winning) {
                        Controller controller = new Controller();
                        if (level == 1) {
                            iz2.setVisible(false);
                            controller.level2Open = true;
                        }
                        if (level == 2) {
                            iz3.setVisible(false);
                            controller.level3Open = true;
                        }
                        if (level == 3) {
                            iz4.setVisible(false);
                            controller.level4Open = true;
                        }
                        if (level == 4) {
                            iz5.setVisible(false);
                            controller.level5Open = true;
                        }
                        if (level == 5) {
                            iz6.setVisible(false);
                            controller.level6Open = true;
                        }
                        if (level == 6) {
                            iz7.setVisible(false);
                            controller.level7Open = true;
                        }
                        if (level == 7) {
                            iz8.setVisible(false);
                            controller.level8Open = true;
                        }
                        if (level == 8) {
                            iz9.setVisible(false);
                            controller.level9Open = true;
                        }
                        if (level == 9) {

                            paper.setVisible(true);
                            pirate2.setVisible(true);
                            dialog.setVisible(true);
                            text2.setVisible(true);
                            pirate2.setOnMouseMoved(e -> {
                                pirate2.setX(e.getX());
                                rootGr.getChildren().remove(text2);
                                rootGr.getChildren().remove(dialog);
                                if (pirate2.getX() > 498) {
                                    gold.setVisible(true);
                                    paperWin.setVisible(true);
                                    rootGr.getChildren().remove(paper);
                                    mainMusic.stop();
                                    musicWin();
                                }
                            });

                        }

                        Image cup = new Image("img/win.png");
                        gc.drawImage(cup, logic.getWidth() / 2 - 70, 15, 150, 150);
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
                            gc.drawImage(words, logic.getWidth() - 300, logic.getHeight() - 200, 220, 160);
                        }

                        gc.fillText("Ви перемогли!\n Час: " + time.getTime() / 1000 + " с", logic.getWidth() / 2, 180);
                        if (level < 4) {
                            gc.fillText(fraze, logic.getWidth() - 130, logic.getHeight() - 100);
                        } else {
                            gc.fillText(fraze, logic.getWidth() - 180, logic.getHeight() - 130);
                        }
                        gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 18));
                        gc.fillText("Бали: " + logic.score, 70, 20);
//                        gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 18));
//                        gc.fillText("Час: " + time.getTime() / 1000, 200, 370);
                        gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 18));
                        gc.fillText("Життя: " + logic.lives, logic.getWidth() - 70, 20);
                    }

                    if (logic.fail) {

                        Image image = new Image("img/skull.png");
                        gc.drawImage(image, logic.getWidth() / 2 - 70, 15, 150, 150);
                        gc.fillText("Ви програли!\n Час: " + time.getTime() / 1000 + " с", logic.getWidth() / 2, 180);
                        gc.fillText("Ви втратили одне життя!", logic.getWidth() / 2, 220);
                        Image smilePirate = new Image("img/SmilePirate.png");
                        gc.drawImage(smilePirate, 10, logic.getHeight() - 180, 140, 150);

                        Image words = new Image("img/Word.png");
                        if (level < 4) {
                            gc.drawImage(words, logic.getWidth() - 240, logic.getHeight() - 160, 220, 160);
                        } else {
                            gc.drawImage(words, logic.getWidth() - 300, logic.getHeight() - 200, 220, 160);
                        }
                        if (level < 4) {
                            gc.fillText(frazeHappy, logic.getWidth() - 130, logic.getHeight() - 100);
                        } else {
                            gc.fillText(frazeHappy, logic.getWidth() - 180, logic.getHeight() - 130);
                        }
                    }
                    if (logic.winning || logic.fail) {
                        if (logic.winning) {
                            gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 16));
                            gc.setFill(Color.WHITE);
                            gc.fillText("Натисніть prt scr, щоб зберегти рекорд!", logic.getWidth() / 2, 270);
                        }
                        gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 16));
                        gc.setFill(Color.WHITE);
                        gc.fillText("Натисніть Shift, щоб почати знову ", logic.getWidth() / 2, 290);
                        if (logic.winning) {
                            gc.setFont(Font.font("Elephant", FontWeight.LIGHT, 16));
                            gc.setFill(Color.WHITE);
                            gc.fillText("Натисніть Enter, щоб продовжити ", logic.getWidth() / 2, 310);
                        }
                    }
                }
            }
        };
        animationTimer.start();

        myStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                animationTimer.stop();
                //метод що залишаэ лише карту
// get a handle to the stage
                Stage stage = (Stage)myScene.getWindow();
                // do what you have to do
                stage.close();

            }
        });
    }

    private void speak(int level) {
        switch (level) {
            case 2: {
                fraze = "\"Йо-хо!\n  І пляшка рому....\"\n  Ой, ви ще живі?\n  Це не на довго...";
                break;
            }
            case 3: {
                fraze = " Ніхто не може\n знайти скарби, \n  що заховали пірати!";
                break;
            }
            case 4: {
                fraze = "Ви дійсно хочете\n пограбувати капітана?\n Він не знає жалощі...";
                break;
            }
            case 5: {
                fraze = "Плавати у \n піратських водах\n погана ідея!";
                break;
            }
            case 6: {
                fraze = "Попереду льодові\nкулі, вам\nїх не пройти!";
                break;
            }
            case 7: {
                fraze = "В цих водах\nмешкає кракен, \nце вас не лякає?";
                break;
            }
            case 8: {
                fraze = "На острові\n зі скарбом вас\n чекає купа\n  пасток!";
                break;
            }
        }
    }

    private void speakHappy(int level) {
        switch (level) {
            case 2: {
                fraze = "\"Йо-хо-xo!\n  І пляшка рому....\"\n Тікайте!";
                break;
            }
            case 3: {
                fraze = " Ніхто не може\n перемогти, \n  злих піратів!";
                break;
            }
            case 4: {
                fraze = "Ми гроза\n цих морів\n тікайте поки живі!";
                break;
            }
            case 5: {
                fraze = "Тікайте на сушу \n там вам і місце!";
                break;
            }
            case 6: {
                fraze = "Якщо ви це\n не можете пройти\n то, що буде далі...\n Ха-ха-ха";
                break;
            }
            case 7: {
                fraze = "Тільки не плачте\n море не поважає\n слабаків!";
                break;
            }
            case 8: {
                fraze = "Тікайте поки можете!\n Ха-ха-ха!";
                break;
            }
        }
    }

    private static int changeC(int arg) {
        return arg * (16 + 64) + 16;
    }
}

package ui;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Cell {
    int number;

    public Cell() {
        this.number = 0;
    }

    public Cell(int number) {
        this.number = number;
    }

    public boolean isEmpty() {
        return number == 0;
    }

    public Color getBackground() {
        switch (number) {
            case 10:		return Color.rgb(0,1,2);
            case 20:		return Color.GREEN;
            case 30:		return Color.GREEN;
            case 40:		return Color.GREEN;
            case 50:		return Color.GREEN;
            case 60:		return Color.GREEN;
            case 70:		return Color.GREEN;
            case 80:		return Color.GREEN;
            case 90:		return Color.GREEN;
            case 100:		return Color.GREEN;
        }
        return Color.rgb(205, 193, 180, 1.0); //0xcdc1b4
    }

    public Color getForeground() {
        Color foreground;
        if(number < 40) {
            foreground = Color.WHITE; //0x776e65
        } else {
            foreground = Color.rgb(249, 246, 242, 1.0);    //0xf9f6f2
        }
        return foreground;
    }
}
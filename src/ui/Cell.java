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
            case 10:		return Color.rgb(238,163,163);
            case 20:		return Color.rgb(241,94,94);
            case 30:		return Color.rgb(241,39,39);
            case 40:		return Color.rgb(212,13,13);
            case 50:		return Color.rgb(130,8,8);
            case 60:		return Color.rgb(219,92,33);
            case 70:		return Color.rgb(156,53,5);
            case 80:		return Color.rgb(88,5,5);
            case 90:		return Color.rgb(57,2,2);
            case 100:		return Color.rgb(14,1,1);
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
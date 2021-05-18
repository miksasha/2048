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
            case 10:		return Color.rgb(238, 228, 218, 1.0); //238 228 218 1.0      0xeee4da
            case 20: 	return Color.rgb(237, 224, 200, 1.0); //237, 224, 200, 1.0   0xede0c8
            case 30: 	return Color.rgb(242, 177, 121, 1.0); //242, 177, 121, 1.0   0xf2b179
            case 40: 	return Color.rgb(245, 149, 99, 1.0); //245, 149, 99, 1.0     0xf59563
            case 50: 	return Color.rgb(246, 124, 95, 1.0); //246, 124, 95, 1.0     0xf67c5f
            case 60:	return Color.rgb(246, 94, 59, 1.0 ); //246, 94, 59, 1.0      0xf65e3b
            case 70:	return Color.rgb(237, 207, 114, 1.0); //237, 207, 114, 1.0   0xedcf72
            case 80: 	return Color.rgb(237, 204, 97, 1.0); //237, 204, 97, 1.0     0xedcc61
            case 90: 	return Color.rgb(237, 200, 80, 1.0); //237, 200, 80, 1.0     0xedc850
            case 100: 	return Color.rgb(237, 197, 63, 1.0); //237, 197, 63, 1.0     0xedc53f

        }
        return Color.rgb(205, 193, 180, 1.0); //0xcdc1b4
    }

    public Color getForeground() {
        Color foreground;
        if(number < 40) {
            foreground = Color.rgb(119, 110, 101, 1.0); //0x776e65
        } else {
            foreground = Color.rgb(249, 246, 242, 1.0);    //0xf9f6f2
        }
        return foreground;
    }
}
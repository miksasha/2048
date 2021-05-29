package ui;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Cell {
    int number;
boolean frozen = false;
    public Cell() {
        this.number = 0;
    }

    public Cell(int number) {
        this.number = number;
        frozen=false;
    }
    public Cell(int number, boolean frozen) {
        this.number = number;
        this.frozen=frozen;
    }
    /**
     * check if cell is empty
     */
    public boolean isEmpty() {
        return number == 0;
    }
    /**
     * paint cell depends on its number
     */
    public Color getBackground() {
        if(frozen==true){
            switch (number) {
            case 10:		return Color.valueOf("54ADE8FF");
            case 20:		return Color.valueOf("5471F8FF");
            case 30:		return Color.valueOf("1A4DFAFF");
            case 40:		return Color.valueOf("0531C4FF");
            case 50:		return Color.valueOf("0D0DD5FF");
            case 60:		return Color.valueOf("0E0484FF");
            case 70:		return Color.valueOf("3C03F8FF");
            case 80:		return Color.valueOf("714AF3FF");
            case 90:		return Color.valueOf("9C6CE8FF");
            case 100:		return Color.valueOf("BFAFEAFF");
            case 110:		return Color.valueOf("BFAFEAFF");}
        }
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
            case 110:		return Color.rgb(14,1,1);
        }
        return Color.rgb(205, 193, 180, 1.0); //0xcdc1b4
    }
    /**
     * paint foreground
     */
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
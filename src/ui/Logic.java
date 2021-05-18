package ui;
import java.util.*;
import java.util.List;

public class Logic extends javafx.scene.canvas.Canvas {
    public static final int CELL_SIZE = 80;
    private Cell[] Allcells;
    boolean win = false;
    boolean lose = false;
    int score = 0;
    boolean reverse= false;
int amoungOfLines =4;
    public Cell[] getAllcells() {
        return Allcells;
    }

    public Logic() {
        super(400, 390);
        setFocused(true);
        resetGame();
    }

    public Logic(double width, double height) {
        super(width, height);
        setFocused(true);
        resetGame();
    }


    void resetGame() {
        score = 0;
        win = false;
        lose = false;
        Allcells = new Cell[amoungOfLines * amoungOfLines];
        for (int cell = 0; cell < Allcells.length; cell++) {
            Allcells[cell] = new Cell();
        }
        addCell();
        addCell();
    }

    public void addCell() {
        List<Cell> list = availableSpace();
        if(!availableSpace().isEmpty()) {
            int index = (int) (Math.random() * list.size()) % list.size();
            Cell emptyCell = list.get(index);
            emptyCell.number = Math.random() < 0.9 ? 10 : 20;
        }

    }
    public void addCellTwo() {
        List<Cell> list = availableSpace();
        if(!availableSpace().isEmpty()) {
            int index = (int) (Math.random() * list.size()) % list.size();
            Cell emptyCell = list.get(index);
            emptyCell.number = 10;
        }

    }
    public void addCellFour() {
        List<Cell> list = availableSpace();
        if(!availableSpace().isEmpty()) {
            int index = (int) (Math.random() * list.size()) % list.size();
            Cell emptyCell = list.get(index);
            emptyCell.number = 20;
        }

    }
    public void addCellEight() {
        List<Cell> list = availableSpace();
        if(!availableSpace().isEmpty()) {
            int index = (int) (Math.random() * list.size()) % list.size();
            Cell emptyCell = list.get(index);
            emptyCell.number = 30;
        }

    }
    private List<Cell> availableSpace() {
        List<Cell> list = new ArrayList<>(16);
        for(Cell c : Allcells)
            if(c.isEmpty())
                list.add(c);
        return list;
    }

    private boolean isFull() {
        return availableSpace().size() == 0;
    }

    private Cell cellAt(int x, int y) {
        return Allcells[x + y * amoungOfLines];
    }

    protected boolean canMove() {
        if(!isFull()) return true;
        for(int x = 0; x < amoungOfLines; x++) {
            for (int y = 0; y < amoungOfLines; y++) {
                Cell cell = cellAt(x, y);
                if ((x < 3 && cell.number == cellAt(x + 1, y).number) ||
                        (y < 3) && cell.number == cellAt(x, y + 1).number) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean compare(Cell[] line1, Cell[] line2) {
        if(line1 == line2) {
            return true;
        }
        if (line1.length != line2.length) {
            return false;
        }

        for(int i = 0; i < line1.length; i++) {
            if(line1[i].number != line2[i].number) {
                return false;
            }
        }
        return true;
    }

    private Cell[] rotate(int angle) {
        Cell[] tiles = new Cell[amoungOfLines * amoungOfLines];
        int offsetX = amoungOfLines-1;
        int offsetY = amoungOfLines-1;
        if(angle == 90) {
            offsetY = 0;
        } else if(angle == 270) {
            offsetX = 0;
        }

        double rad = Math.toRadians(angle);
        int cos = (int) Math.cos(rad);
        int sin = (int) Math.sin(rad);
        for(int x = 0; x < amoungOfLines; x++) {
            for(int y = 0; y < amoungOfLines; y++) {
                int newX = (x*cos) - (y*sin) + offsetX;
                int newY = (x*sin) + (y*cos) + offsetY;
                tiles[(newX) + (newY) * amoungOfLines] = cellAt(x, y);
            }
        }
        return tiles;
    }

    private Cell[] moveLine(Cell[] oldLine) {
        LinkedList<Cell> list = new LinkedList<Cell>();
        for(int i = 0; i < amoungOfLines; i++) {
            if(!oldLine[i].isEmpty()){
                list.addLast(oldLine[i]);
            }
        }

        if(list.size() == 0) {
            return oldLine;
        } else {
            Cell[] newLine = new Cell[amoungOfLines];
            while (list.size() != amoungOfLines) {
                list.add(new Cell());
            }
            for(int j = 0; j < amoungOfLines; j++) {
                newLine[j] = list.removeFirst();
            }
            return newLine;
        }
    }

    private Cell[] mergeLine(Cell[] oldLine) {
        LinkedList<Cell> list = new LinkedList<Cell>();
        for(int i = 0; i < amoungOfLines && !oldLine[i].isEmpty(); i++) {
            int num = oldLine[i].number;
            if (i < amoungOfLines-1 && oldLine[i].number == oldLine[i+1].number) {
                num += 10;
                score += num;
                if ( num == 100) {
                    win = true;
                }
                i++;
            }
            list.add(new Cell(num));
        }

        if(list.size() == 0) {
            return oldLine;
        } else {
            while (list.size() != amoungOfLines) {
                list.add(new Cell());
            }
            return list.toArray(new Cell[amoungOfLines]);
        }
    }

    private Cell[] getLine(int index) {
        Cell[] result = new Cell[amoungOfLines];
        for(int i = 0; i < amoungOfLines; i++) {
            result[i] = cellAt(i, index);
        }
        return result;
    }

    private void setLine(int index, Cell[] re) {
        System.arraycopy(re, 0, Allcells, index * amoungOfLines, amoungOfLines);
    }

    public void left() {
        if(reverse==true){
            boolean needAddCell = false;
        }
        boolean needAddCell = false;
        for(int i = 0; i < amoungOfLines; i++) {
            Cell[] line = getLine(i);
            Cell[] merged = mergeLine(moveLine(line));
            setLine(i, merged);
            if( !needAddCell && !compare(line, merged)) {
                needAddCell = true;
            }
        }
        if(needAddCell) {
            addCell();
        }
    }

    public void right() {
        Allcells = rotate(180);
        left();
        Allcells = rotate(180);
    }

    public void up() {
        Allcells = rotate(270);
        left();
        Allcells = rotate(90);
    }

    public void down() {
        Allcells = rotate(90);
        left();
        Allcells = rotate(270);
    }
}
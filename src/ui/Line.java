package ui;

public class Line {
    public Cell[] cellsInLine;
public int number;
    private Cell[] getLine(int index) {
        Cell[] result = new Cell[4];
        for(int i = 0; i < 4; i++) {
            result[i] = cellAt(i);
        }
        return result;
    }
    private Cell cellAt(int x) {
        return cellsInLine[x];
    }
}

package ui;

import java.util.*;
import java.util.List;

public class Logic extends javafx.scene.canvas.Canvas {
    public static  int CELL_SIZE = 80;
    private Cell[] allcells;
    boolean winning = false;
    boolean fail = false;
    int score = 0;
    int lives=3;
    //boolean reverse = false;
    int amountOfLines = 2;
    int maxNumber = 20;

    public Cell[] getAllcells() {
        return allcells;
    }

    public Logic() {
        super(400, 500);
        setFocused(true);
        startNewGame();
    }



    void startNewGame() {
      //  score = 0;
        allcells = new Cell[amountOfLines * amountOfLines];
        for (int cell = 0; cell < allcells.length; cell++) {
            allcells[cell] = new Cell();
        }
        winning = false;
        fail = false;
        newCellAdding();
        newCellAdding();
    }

    public void newCellAdding() {
        List<Cell> list = freeCells();
        if (!freeCells().isEmpty()) {
            int index = (int) (Math.random() * list.size());
            Cell empty = list.get(index);
            empty.number = Math.random() * 10 < 9 ? 10 : 20;
        }

    }

    public void CellTen() {
        List<Cell> list = freeCells();
        if (!freeCells().isEmpty()) {
            int index = (int) (Math.random() * list.size()) % list.size();
            Cell emptyCell = list.get(index);
            emptyCell.number = 10;
        }

    }

    public void CellTwenty() {
        List<Cell> list = freeCells();
        if (!freeCells().isEmpty()) {
            int index = (int) (Math.random() * list.size()) % list.size();
            Cell emptyCell = list.get(index);
            emptyCell.number = 20;
        }

    }

    public void Celltherty() {
        List<Cell> list = freeCells();
        if (!freeCells().isEmpty()) {
            int index = (int) (Math.random() * list.size()) % list.size();
            Cell emptyCell = list.get(index);
            emptyCell.number = 30;
        }

    }

    private List<Cell> freeCells() {
        List<Cell> list = new ArrayList<>(amountOfLines * amountOfLines);
        for (Cell c : allcells)
            if (c.isEmpty())
                list.add(c);
        return list;
    }

    private boolean isFull() {
        return freeCells().size() == 0;
    }

    private Cell cellAt(int x, int y) {
        //todo fix bag
        System.out.println(x);
        System.out.println(y);
        System.out.println(amountOfLines-1);
        System.out.println(x + y * amountOfLines);
        return allcells[x + y * amountOfLines];
    }

     boolean checkIfStepIsNotAvalible() {
        if (!isFull()) return false;
        for (int x = 0; x < amountOfLines; x++) {
            for (int y = 0; y < amountOfLines; y++) {
                Cell cell = cellAt(x, y);
                if ((x < 2 && cell.number == cellAt(x + 1, y).number) ||
                        (y < 2) && cell.number == cellAt(x, y + 1).number) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean compare(Cell[] line1, Cell[] line2) {
        if (line1 == line2) {
            return true;
        }
        if (line1.length != line2.length) {
            return false;
        }

        for (int i = 0; i < line1.length; i++) {
            if (line1[i].number != line2[i].number) {
                return false;
            }
        }
        return true;
    }

    private Cell[] turnleft() {
        Cell[][] tiles = makeMatrix();
        Cell[][] result = new Cell[amountOfLines][amountOfLines];
        for (int i = 0; i < amountOfLines; i++) {
            for (int j = 0; j < amountOfLines; j++) {
                result[i][j] = tiles[amountOfLines - 1 - j][i];
            }
        }
        return undoMatrix(result);

    }

    private Cell[] undoMatrix(Cell[][] result) {
        Cell[] res = new Cell[amountOfLines * amountOfLines];
        for (int i = 0; i < amountOfLines; i++) {
            System.arraycopy(result[i], 0, res, i * amountOfLines, amountOfLines);
        }
        return res;
    }

    private Cell[][] makeMatrix() {
        Cell[][] res = new Cell[amountOfLines][amountOfLines];
        for (int i = 0; i < res.length; i++) {
            if (amountOfLines >= 0) System.arraycopy(allcells, i * amountOfLines, res[i], 0, amountOfLines);

        }
        return res;
    }


    private Cell[] moveLine(Cell[] oldLine) {
        LinkedList<Cell> list = new LinkedList<>();
        for (int i = 0; i < amountOfLines; i++) {
            if (!oldLine[i].isEmpty()) {
                list.addLast(oldLine[i]);
            }
        }

        if (list.size() == 0) {
            return oldLine;
        } else {
            Cell[] newLine = new Cell[amountOfLines];
            while (list.size() != amountOfLines) {
                list.add(new Cell());
            }
            for (int j = 0; j < amountOfLines; j++) {
                newLine[j] = list.removeFirst();
            }
            return newLine;
        }
    }

    private Cell[] mergeLine(Cell[] oldLine) {
        LinkedList<Cell> list = new LinkedList<>();
        for (int i = 0; i < amountOfLines && !oldLine[i].isEmpty(); i++) {
            int num = oldLine[i].number;
            if (i < amountOfLines - 1 && oldLine[i].number == oldLine[i + 1].number) {
                num += 10;
                score += num;
                if (num == maxNumber) {
                    winning = true;

                }
                i++;
            }
            list.add(new Cell(num));
        }

        if (list.size() == 0) {
            return oldLine;
        } else {
            while (list.size() != amountOfLines) {
                list.add(new Cell());
            }
            return list.toArray(new Cell[amountOfLines]);
        }
    }

    private Cell[] getLine(int index) {
        Cell[] result = new Cell[amountOfLines];
        for (int i = 0; i < amountOfLines; i++) {
            result[i] = cellAt(i, index);
        }
        return result;
    }

    private void setLine(int index, Cell[] re) {
        System.arraycopy(re, 0, allcells, index * amountOfLines, amountOfLines);
    }

    public void left() {
        //TODO reverse method
//        if (reverse) {
//            boolean addMoreCells = false;
//        }
        boolean addMoreCells = false;
        for (int i = 0; i < amountOfLines; i++) {
            Cell[] line = getLine(i);
            Cell[] merged = mergeLine(moveLine(line));
            setLine(i, merged);
            if (!addMoreCells && !compare(line, merged)) {
                addMoreCells = true;
            }
        }
        if (addMoreCells) {
            newCellAdding();
        }
    }

    public void right() {
        allcells = turnleft();
        allcells = turnleft();
        left();
        allcells = turnleft();
        allcells = turnleft();
    }

    public void up() {
        for (int i = 0; i < 3; i++) {
            allcells = turnleft();
        }
        left();
        allcells = turnleft();
    }

    public void down() {
        allcells = turnleft();
        left();
        for (int i = 0; i < 3; i++) {
            allcells = turnleft();
        }
    }
}
package ui;

import java.util.*;
import java.util.List;

public class Logic extends javafx.scene.canvas.Canvas {
    public static int CELL_SIZE = 80;
    private Cell[] allcells;
    boolean winning = false;
    boolean fail = false;
    int score = 0;
    int lives = 3;
    //boolean reverse = false;
    int amountOfLines = 2;
    int maxNumber = 20;
    boolean ice = false;

    public Cell[] getAllcells() {
        return allcells;
    }

    public Logic(int level) {
        super(400, 500);
         amountOfLines = level+1;
         maxNumber = level*10+10;
        setFocused(true);
        makeNewGame();


    }

    /**
     * begin new game
     */
    void makeNewGame() {
        allcells = new Cell[amountOfLines * amountOfLines];
        for (int cell = 0; cell < allcells.length; cell++) {
            allcells[cell] = new Cell();
        }
        winning = false;
        fail = false;

        newCellAddingDependOnIce();
        newCellAdding();
    }
    /**
     * add cell with ice from the logic
     */
    private void newCellAddingDependOnIce() {

        List<Cell> list = freeCells();
        if (!freeCells().isEmpty()) {
            int index = (int) (Math.random() * list.size());
            Cell empty = list.get(index);
            empty.number = Math.random() * 10 < 9 ? 10 : 20;
            empty.frozen = ice;}
    }
    /**
     * make new cell with ice=false
     */
    public void newCellAdding() {

        List<Cell> list = freeCells();
        if (!freeCells().isEmpty()) {
            int index = (int) (Math.random() * list.size());
            Cell empty = list.get(index);
            empty.number = Math.random() * 10 < 9 ? 10 : 20;

        }

    }
    /**
     * make cell with given number
     */
    public void newNotRandomCellAdding(int number) {

        List<Cell> list = freeCells();
        if (!freeCells().isEmpty()) {
            int index = (int) (Math.random() * list.size());
            Cell empty = list.get(index);
            empty.number = number;

        }

    }
    /**
     * make cell with number =10
     */
    public void CellTen() {
        List<Cell> list = freeCells();
        if (!freeCells().isEmpty()) {
            int index = (int) (Math.random() * list.size()) % list.size();
            Cell emptyCell = list.get(index);
            emptyCell.number = 10;
            emptyCell.frozen = ice;
        }

    }
    /**
     * make cell with number =20
     */
    public void CellTwenty() {
        List<Cell> list = freeCells();
        if (!freeCells().isEmpty()) {
            int index = (int) (Math.random() * list.size()) % list.size();
            Cell emptyCell = list.get(index);
            emptyCell.number = 20;
            emptyCell.frozen = ice;
        }

    }
    /**
     * make cell with number =30
     */
    public void Celltherty() {
        List<Cell> list = freeCells();
        if (!freeCells().isEmpty()) {
            int index = (int) (Math.random() * list.size()) % list.size();
            Cell emptyCell = list.get(index);
            emptyCell.number = 30;
            emptyCell.frozen = ice;
        }

    }
    /**
     * return all empty cells
     */
    private List<Cell> freeCells() {
        List<Cell> list = new ArrayList<>(amountOfLines * amountOfLines);
        for (Cell c : allcells)
            if (c.isEmpty())
                list.add(c);
        return list;
    }
    /**
     * check if field is full
     */
    private boolean isFull() {
        return freeCells().size() == 0;
    }
    /**
     * return cell at x, y
     */
    private Cell cellAt(int x, int y) {
        return allcells[x + y * amountOfLines];
    }
    /**
     * check if there are no available steps
     */
    boolean checkIfStepIsNotAvalible() {
        if (!isFull()) return false;
        for (int x = 0; x < amountOfLines; x++) {
            for (int y = 0; y < amountOfLines; y++) {
                Cell cell = cellAt(x, y);
                if ((x < 2 && cell.number == cellAt(x + 1, y).number && cell.frozen == cellAt(x + 1, y).frozen) ||
                        (y < 2) && cell.number == cellAt(x, y + 1).number && cell.frozen == cellAt(x, y + 1).frozen) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * compare given lines
     */
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
    /**
     * turn matrix left on 90 degrees
     */
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
    /**
     * make array[] from array[][]
     */
    private Cell[] undoMatrix(Cell[][] result) {
        Cell[] res = new Cell[amountOfLines * amountOfLines];
        for (int i = 0; i < amountOfLines; i++) {
            System.arraycopy(result[i], 0, res, i * amountOfLines, amountOfLines);
        }
        return res;
    }
    /**
     * make array[][] from array[]
     */
    private Cell[][] makeMatrix() {
        Cell[][] res = new Cell[amountOfLines][amountOfLines];
        for (int i = 0; i < res.length; i++) {
            if (amountOfLines >= 0) System.arraycopy(allcells, i * amountOfLines, res[i], 0, amountOfLines);

        }
        return res;
    }

    /**
     * move given line to the left
     */
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
    /**
     * add lines together(merge)
     */
    private Cell[] addLinesTogetherLogic(Cell[] oldLine) {

        LinkedList<Cell> list = new LinkedList<>();
        for (int i = 0; i < amountOfLines && !oldLine[i].isEmpty(); i++) {
            int num = oldLine[i].number;
            boolean iced=oldLine[i].frozen;
            if (i < amountOfLines - 1 && oldLine[i].number == oldLine[i + 1].number && oldLine[i].frozen == oldLine[i + 1].frozen) {
                num += 10;
                score += num;

                if (num >= maxNumber) {
                    winning = true;

                }
                i++;
            }
            if (num<40) {
                list.add(new Cell(num, iced));
            } else {
                list.add(new Cell(num));
            }
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
    /**
     * get line from array of cells
     */
    private Cell[] getLine(int index) {
        Cell[] result = new Cell[amountOfLines];
        for (int i = 0; i < amountOfLines; i++) {
            result[i] = cellAt(i, index);
        }
        return result;
    }
    /**
     * make new line
     */
    private void setLine(int index, Cell[] re) {
        System.arraycopy(re, 0, allcells, index * amountOfLines, amountOfLines);
    }
    /**
     * merge lines to the left
     */
    public void mergeLogicleft() {

        boolean addMoreCells = false;
        for (int i = 0; i < amountOfLines; i++) {
            Cell[] line = getLine(i);
            Cell[] merged = addLinesTogetherLogic(moveLine(line));
            setLine(i, merged);
            if (!addMoreCells && !compare(line, merged)) {
                addMoreCells = true;
            }
        }
        Random rand = new Random();
        if (addMoreCells) {
            int i= rand.nextInt(10);
            if(i%2==0){
            newCellAdding();}else{
                newCellAddingDependOnIce();
            }
        }
    }
    /**
     * merge lines to the right
     */
    public void mergeLogicRight() {
        allcells = turnleft();
        allcells = turnleft();
        mergeLogicleft();
        allcells = turnleft();
        allcells = turnleft();
    }
    /**
     * merge lines  up
     */
    public void mergeLogicUp() {
        for (int i = 0; i < 3; i++) {
            allcells = turnleft();
        }
        mergeLogicleft();
        allcells = turnleft();
    }
    /**
     * merge lines down
     */
    public void mergeLogicDown() {
        allcells = turnleft();
        mergeLogicleft();
        for (int i = 0; i < 3; i++) {
            allcells = turnleft();
        }
    }
}
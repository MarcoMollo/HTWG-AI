import java.util.*;

/**
 * Klasse Board für 8-Puzzle-Problem
 *
 * @author Ihr Name
 */
public class Board {

    /**
     * Problmegröße
     */
    public static final int N = 8;

    /**
     * Board als Feld.
     * Gefüllt mit einer Permutation von 0,1,2, ..., 8.
     * 0 bedeutet leeres Feld.
     */
    protected Integer[] board = new Integer[N + 1];

    /**
     * Generiert ein zufälliges Board.
     */
    public Board() {
        Integer[] intArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> intList = Arrays.asList(intArray);
        Collections.shuffle(intList);
        board = intList.toArray(intArray);
    }

    /**
     * Generiert ein Board und initialisiert es mit board.
     *
     * @param board Feld gefüllt mit einer Permutation von 0,1,2, ..., 8.
     */
    public Board(Integer[] board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "Puzzle{" + "board=" + Arrays.toString(board) + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Board other = (Board) obj;
        return Arrays.equals(this.board, other.board);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Arrays.hashCode(this.board);
        return hash;
    }

    public int calculate_parity() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            if (i == board.length - 1) {
                break;
            }
            int left = board[i];
            if (left == 0) {
                continue;
            }
            for (int j = i + 1; j < board.length; j++) {
                int right = board[j];
                if (right == 0) {
                    continue;
                }
                if (left > right) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Paritätsprüfung.
     *
     * @return Parität.
     */
    public boolean parity() {
        return true;
    }

    /**
     * Heurstik h1. (siehe Aufgabenstellung)
     *
     * @return Heuristikwert.
     */
    public int h1() {
        int count = 0;
        // Index i ist die korrekte Position
        for (int i = 0; i < board.length; i++) {
            if (!Objects.equals(board[i], i) && board[i] != 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Heurstik h2. (siehe Aufgabenstellung)
     *
     * @return Heuristikwert.
     */
    public int h2() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            if (!Objects.equals(board[i], i) && board[i] != 0) {
                // Idee wäre Position in der Matrix von falsche und richtige Zahl finden.
                // Man kann dann die Positionen subtrahieren und auf die Distanz kommen.
                // Bsp.: Index 5 hat die Position 1,2 (Reihe, Spalte)  (5 % 3 = 1 Rest 2)
                // 0, 1, 2
                // 3, 4, 5
                // 6, 7, 8
                int wrong_index = i;
                int correct_index = board[i];

                int row1 = wrong_index / 3;
                int column1 = wrong_index % 3;

                int row2 = correct_index / 3;
                int column2 = correct_index % 3;

                count += Math.abs(row1 - row2) + Math.abs(column1 - column2);
            }
        }
        return count;
    }

    /**
     * Liefert eine Liste der möglichen Aktion als Liste von Folge-Boards zurück.
     *
     * @return Folge-Boards.
     */
    public List<Board> possibleActions() {
        List<Board> boardList = new LinkedList<>();
        // ...
        return boardList;
    }


    /**
     * Prüft, ob das Board ein Zielzustand ist.
     *
     * @return true, falls Board Ziestzustand (d.h. 0,1,2,3,4,5,6,7,8)
     */
    public boolean isSolved() {
        return true;
    }


    public static void main(String[] args) {
        Board b = new Board(new Integer[]{7, 2, 4, 5, 0, 6, 8, 3, 1});        // abc aus Aufgabenblatt
        Board goal = new Board(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8});

        System.out.println(b);
        System.out.println(b.parity());
        System.out.println(b.calculate_parity());
        System.out.println(b.h1());
        System.out.println(b.h2());

        for (Board child : b.possibleActions())
            System.out.println(child);

        System.out.println(goal.isSolved());
    }
}
	

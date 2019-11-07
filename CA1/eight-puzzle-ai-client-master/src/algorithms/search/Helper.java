package algorithms.search;

import java.util.LinkedList;

public class Helper {
    static LinkedList<State> generatedStates = new LinkedList<>();

    /***
     * Checks if s has already been generated and
     * if it hasn't it's added to generatedStates list
     */
    public static State addToGeneratedStates(State s) {
        int x = Helper.generatedStates.indexOf(s);// return -1 if not in list
        if (x != -1) // adj[0] has already been generated.
            s = Helper.generatedStates.get(x);
        else // adj[0] has never been generated before
            Helper.generatedStates.add(s);
        return s;
    }

    public static State goalMaker(int MAP_SIZE) {
        String[][] goalState = new String[MAP_SIZE][MAP_SIZE];
        int k = 1;
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                goalState[i][j] = Integer.toString(k);
                k++;
            }
        }
        goalState[MAP_SIZE - 1][MAP_SIZE - 1] = " ";
        return new State(goalState, null, null, 1);
    }

    public static int[] getCell(String[][] grid, String cell) {
        int[] k = new int[2];
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid.length; j++)
                if (grid[i][j].equals(cell)) {
                    k[0] = i;
                    k[1] = j;
                    return k;
                }
        return k;
    }

    public static int[] getCorrectPlace(String[][] grid, String cell) {
        switch (cell) {
            case "1":
                return new int[]{0, 0};
            case "2":
                return new int[]{0, 1};
            case "3":
                return new int[]{0, 2};
            case "4":
                return new int[]{1, 0};
            case "5":
                return new int[]{1, 1};
            case "6":
                return new int[]{1, 2};
            case "7":
                return new int[]{2, 0};
            case "8":
                return new int[]{2, 1};
            default:
                return new int[]{2, 2};
        }
    }

    public static String[][] moveUp(String[][] grid, int[] emptyIndx) {
        String[][] temp = grid;
        if (emptyIndx[0] != 0) {
            temp[emptyIndx[0]][emptyIndx[1]] = temp[emptyIndx[0] - 1][emptyIndx[1]];
            temp[emptyIndx[0] - 1][emptyIndx[1]] = " ";
        }
        return temp;
    }

    public static String[][] moveDown(String[][] grid, int[] emptyIndx) {
        String[][] temp = grid;
        if (emptyIndx[0] != 2) {
            temp[emptyIndx[0]][emptyIndx[1]] = temp[emptyIndx[0] + 1][emptyIndx[1]];
            temp[emptyIndx[0] + 1][emptyIndx[1]] = " ";
        }
        return temp;

    }

    public static String[][] moveLeft(String[][] grid, int[] emptyIndx) {
        String[][] temp = grid;
        if (emptyIndx[1] != 0) {
            temp[emptyIndx[0]][emptyIndx[1]] = temp[emptyIndx[0]][emptyIndx[1] - 1];
            temp[emptyIndx[0]][emptyIndx[1] - 1] = " ";
        }
        return temp;
    }

    public static String[][] moveRight(String[][] grid, int[] emptyIndx) {
        String[][] temp = grid;
        if (emptyIndx[1] != 2) {
            temp[emptyIndx[0]][emptyIndx[1]] = temp[emptyIndx[0]][emptyIndx[1] + 1];
            temp[emptyIndx[0]][emptyIndx[1] + 1] = " ";
        }
        return temp;
    }

    public static void arraycopy(String[][] src, String[][] dest) {
        for (int i = 0; i < src.length; i++)
            for (int j = 0; j < src.length; j++)
                dest[i][j] = src[i][j];
    }
}

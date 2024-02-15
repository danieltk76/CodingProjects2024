package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeGame {
    public static final int HEIGHT = 19;
    public static final int WIDTH = 39;
    private static final int COL = 1;
    private static final int ROW = 0;
    private Scanner playerInput;
    private boolean[][] blocked;
    private boolean[][] visited;
    private int[] player;
    private int[] goal;
    private int[] start;

    public MazeGame(String mazeFile) throws FileNotFoundException {

        this(mazeFile, new Scanner(System.in));

    }

    public MazeGame(String mazeFile, Scanner playerInput) throws FileNotFoundException {
        this.playerInput = playerInput;

        loadMaze(mazeFile);

    }

    public void playGame() {

        do {
            prompt();
        } while (!makeMove(playerInput.nextLine()));

        if (playerAtGoal()) {
            System.out.println("You Won!");

        } else {
            System.out.println("Goodbye!");
        }
    }

    public void printMaze() {

        System.out.print("*");
        for (int k = 0; k < WIDTH; k++) {
            System.out.print("-");
        }
        System.out.println("*");

        for (int i = 0; i < HEIGHT; i++) {
            System.out.print("|");
            for (int j = 0; j < WIDTH; j++) {
                if (player[0] == i && player[1] == j) {
                    System.out.print("@");
                } else if (start[0] == i && start[1] == j) {
                    System.out.print("S");
                } else if (goal[0] == i && goal[1] == j) {
                    System.out.print("G");
                } else if (visited[i][j]) {
                    System.out.print(".");
                } else if (blocked[i][j]) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("|");
        }

        System.out.print("*");
        for (int k = 0; k < WIDTH; k++) {
            System.out.print("-");
        }
        System.out.println("*");
    }

    public int getPlayerRow() {
        return this.player[ROW];
    }

    public int getPlayerCol() {
        return this.player[COL];
    }

    public int getGoalRow() {
        return this.goal[ROW];
    }

    public int getGoalCol() {
        return this.goal[COL];
    }

    public int getStartRow() {
        return this.start[ROW];

    }

    public int getStartCol() {
        return this.start[COL];
    }

    public boolean[][] getBlocked() {
        return copyTwoDimBoolArray(blocked);
    }

    public boolean[][] getVisited() {
        return copyTwoDimBoolArray(visited);

    }

    public Scanner getPlayerInput() {
        return this.playerInput;
    }

    public void setPlayerRow(int row) {
        if (row >= 0 && row < HEIGHT) {
            this.player[ROW] = row;
        }

    }

    public void setPlayerCol(int col) {

        if (col >= 0 && col < WIDTH) {
            this.player[COL] = col;

        }

    }

    public void setGoalRow(int row) {
        if (row >= 0 && row < HEIGHT) {
            this.goal[ROW] = row;
        }

    }

    public void setGoalCol(int col) {
        if (col >= 0 && col < WIDTH) {
            this.goal[COL] = col;

        }
    }

    public void setStartRow(int row) {
        if (row >= 0 && row < HEIGHT) {
            this.start[ROW] = row;
        }

    }

    public void setStartCol(int col) {

        if (col >= 0 && col < WIDTH) {
            this.start[COL] = col;

        }
    }

    public void setBlocked(boolean[][] newBlocked) {
        this.blocked = copyTwoDimBoolArray(newBlocked);

    }

    public void setVisited(boolean[][] newVisited) {
        this.visited = copyTwoDimBoolArray(newVisited);

    }

    public void setPlayerInput(Scanner playerInput) {
        this.playerInput = playerInput;

    }

    private boolean[][] copyTwoDimBoolArray(boolean[][] arrayToCopy) {
        int width2 = arrayToCopy.length;
        int height2 = arrayToCopy[0].length;
        boolean[][] newcopArray = new boolean[width2][height2];
        for (int i = 0; i < width2; i++) {
            for (int j = 0; j < height2; j++) {
                newcopArray[i][j] = arrayToCopy[i][j];

            }
        }

        return newcopArray;

    }

    private void prompt() {
        printMaze();
        System.out.print("Enter your move (up, down, left, right, or q to quit): ");

    }

    private boolean playerAtGoal() {

        return (getPlayerRow() == getGoalRow() && getPlayerCol() == getGoalCol());

    }

    private boolean valid(int row, int col) {
        if (row < 0 || row >= HEIGHT) {
            return false;

        } else if (col < 0 || col >= WIDTH) {
            return false;

        }
        if (blocked[row][col] == true) {
            return false;

        } else {
            return true;
        }

    }

    private void visit(int row, int col) {
        visited[row][col] = true;

    }

    private void loadMaze(String mazeFile) throws FileNotFoundException {
        blocked = new boolean[HEIGHT][WIDTH];
        visited = new boolean[HEIGHT][WIDTH];
        player = new int[2];
        goal = new int[2];
        start = new int[2];

        File fileIn = new File(mazeFile);
        Scanner files = new Scanner(fileIn);

        for (int i = 0; i < HEIGHT; i++) {

            for (int j = 0; j < WIDTH; j++) {

                String line = files.next();
                if (line.equals("1")) {
                    blocked[i][j] = true;
                } else if (line.equals("0")) {
                    blocked[i][j] = false;

                } else if (line.equals("S")) {
                    setStartRow(i);
                    setStartCol(j);
                    setPlayerRow(i);
                    setPlayerCol(j);

                } else if (line.equals("G")) {
                    setGoalRow(i);
                    setGoalCol(j);

                }
            }

        }
        files.close();

    }

    private boolean makeMove(String move) {

        char direction = move.toLowerCase().charAt(0);

        int newRow = player[ROW];
        int newCol = player[COL];

        switch (direction) {
            case 'q':
                return true;

            case 'l':
                newCol--;
                break;
            case 'r':
                newCol++;
                break;
            case 'u':
                newRow--;
                break;
            case 'd':
                newRow++;
                break;
            default:
                System.out.println("Invalid move. Try again.");
                return false;

        }

        if (valid(newRow, newCol)) {
            player[ROW] = newRow;
            player[COL] = newCol;
            visit(newRow, newCol);

            return playerAtGoal();
        } else {
            System.out.println("Move not allowed. Try again. ");
            return false;
        }

    }

}

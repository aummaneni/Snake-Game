import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Snake Game - A game where the user controls a snake and tries to make
 * it as big as possible while also making sure not to kill the snake
 *
 * @author Atul Ummaneni
 * @since 12 May 2022
 */
public class SnakeGame {
    private Prompt Prompt; // Prompt instance
    private FileUtils FileUtils; // FileUtils instance
    private Snake snake; // the snake in the game
    private SnakeBoard board; // the game board
    private Coordinate target; // the target for the snake
    private int score; // the score of the game

    /** constructor */
    public SnakeGame() {
        Prompt = new Prompt();
        FileUtils = new FileUtils();
        score = 0;
        board = new SnakeBoard(20, 25);
        int row = (int) (Math.random() * (15));
        int col = (int) (Math.random() * 25);
        snake = new Snake(new Coordinate(row, col));
        target = null;

    }

    /** Main method */
    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();
        game.printIntroduction();
        game.run();

    }

    /** Print the game introduction */
    public void printIntroduction() {
        System.out.println("  _________              __            ________");
        System.out.println(" /   _____/ ____ _____  |  | __ ____  /  _____/_____    _____   ____");
        System.out.println(" \\_____  \\ /    \\\\__  \\ |  |/ // __ \\/   \\  ___\\__  \\  /     \\_/ __ \\");
        System.out.println(" /        \\   |  \\/ __ \\|    <\\  ___/\\    \\_\\  \\/ __ \\|  Y Y  \\  ___/");
        System.out.println("/_______  /___|  (____  /__|_ \\\\___  >\\______  (____  /__|_|  /\\___  >");
        System.out.println("        \\/     \\/     \\/     \\/    \\/        \\/     \\/      \\/     \\/");
        System.out.println("\nWelcome to SnakeGame!");
        System.out.println("\nA snake @****** moves around a board " +
                "eating targets \"+\".");
        System.out.println("Each time the snake eats the target it grows " +
                "another * longer.");
        System.out.println("The objective is to grow the longest it can " +
                "without moving into");
        System.out.println("itself or the wall.");
        System.out.println("\n");
    }

    /** Print help menu */
    public void helpMenu() {
        System.out.println("\nCommands:\n" +
                "  w - move north\n" +
                "  s - move south\n" +
                "  d - move east\n" +
                "  a - move west\n" +
                "  h - help\n" +
                "  f - save game to file\n" +
                "  r - restore game from file\n" +
                "  q - quit");
        Prompt.getString("Press enter to continue");
    }

    /** runs game */
    public void run() {
        generateTarget(); // generates random target
        boolean gameOver = false; // makes sure game hasn't ended
        String input; // user input controller

        while (!gameOver) {
            System.out.println();
            board.printBoard(snake, target); // puts snake & target on board
            input = Prompt.getString("Score: " + score + " (w - North, s - South, d - East, a - West, h - Help)");
            while (!(input.equals("w") || input.equals("a") || input.equals("s") || input.equals("d")
                    || input.equals("h") || input.equals("f") || input.equals("q") || input.equals("r"))) {
                input = Prompt.getString("Score: " + score + " (w - North, s - South, d - East, a - West, h - Help)");
            }
            if (input.equals("w"))
                gameOver = move("w");
            else if (input.equals("a"))
                gameOver = move("a");
            else if (input.equals("s"))
                gameOver = move("s");
            else if (input.equals("d"))
                gameOver = move("d");
            else if (input.equals("h")) {
                helpMenu();
            } else if (input.equals("f")) {
                input = Prompt.getString("\nSave game? (y or n)");
                if (input.equals("y")) {
                    saveGame();
                    System.out.println("Saving game to file snakeGameSave.txt\n");
                    System.out.println("Game is over");
                    System.out.println("Score = " + score);
                    System.out.println("\nThanks for playing SnakeGame!!");
                    gameOver = true;
                }
            } else if (input.equals("r")) {
                restoreGame();
            } else if (input.equals("q")) {
                String s = Prompt.getString("\nDo you really want to quit? (y or n)");
                if (s.equals("y")) {
                    System.out.println("Game is over");
                    System.out.println("Score = " + score);
                    System.out.println("\nThanks for playing SnakeGame!!");
                    gameOver = true;
                    System.out.println();
                }
            }
            if (snake.size() >= 495)
                gameOver = true; // game over if only 5 spaces left
        }
    }

    /**
     * Moves snake in direction specified; if it can't,
     * returns true; 
     * 
     * @param dir direction specified by user to move snake
     * @return false if successful, true if not. 
     */
    public boolean move(String dir) {
        int col = snake.get(0).getValue().getColumn();
        int row = snake.get(0).getValue().getRow();
        if (dir.equals("w"))
            row--;
        else if (dir.equals("s"))
            row++;
        else if (dir.equals("a"))
            col--;
        else
            col++;

        Coordinate loc = new Coordinate(row, col);
        if (loc.getRow() < 0 || loc.getRow() > 20 || loc.getColumn() < 0 || loc.getColumn() > 25)
            return true;
        else if (snake.contains(loc))
            return true;
        if (loc.equals(target)) {
            snake.add(0, loc);
            generateTarget();
            score++;
        } else {
            snake.add(0, loc);
            snake.remove(snake.size() - 1);
        }

        return false;
    }

    /**
     * generates a target that isn't on the snake itself
     */
    public void generateTarget() {
        int row = (int) (Math.random() * 19) + 1;
        int col = (int) (Math.random() * 24) + 1;
        if (!snake.contains(new Coordinate(row, col)))
            target = new Coordinate(row, col);
        else
            generateTarget();
    }

    /**
     * Saves game
     */
    public void saveGame() {
        ListNode<Coordinate> parts = snake.get(0); 
        PrintWriter saver = FileUtils.openToWrite("snakeGameSave.txt"); // used to write to the file
        saver.println("Score " + score);
        saver.println("Target " + target.getRow() + " " + target.getColumn());
        saver.println("Snake " + snake.size());
        while (parts != null) // traverses through the snake, and stores each part's coordinate in the file
        {
            Coordinate c = parts.getValue();
            saver.println(c.getRow() + " " + c.getColumn());
            parts = parts.getNext();
        }
        System.out.println();
        saver.close();
    }

    /**
     * restores previous game
     */
    public void restoreGame() {
        Scanner sc = FileUtils.openToRead("snakeGameSave.txt"); // used to read through the file
        System.out.println();
        System.out.println("Restoring from file snakeGameSave.txt");
        sc.next();
        score = sc.nextInt();
        sc.next();
        target = new Coordinate(sc.nextInt(), sc.nextInt());
        sc.next();
        int length = sc.nextInt();
        snake.clear();
        while (sc.hasNextInt()) {
            int row = sc.nextInt();
            int col = sc.nextInt();
            System.out.println(row + " " + col);
            snake.add(new Coordinate(row, col));
        }
        sc.close();
    }
}
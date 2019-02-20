package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static int seed;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40);
        game.startGame();
    }

    public MemoryGame(int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        String randNumber = generateRandomString(3);
    }

    public String generateRandomString(int n) {
        rand = new Random(seed);
        String s = "";
        for (int i = 0; i < n; i++) {
            int number = rand.nextInt(CHARACTERS.length);
            s += CHARACTERS[number];
        }

        return s;
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.red);
        StdDraw.text(this.width / 2, this.height / 2, s);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for (int i = 0; i < letters.length(); i++) {
            try{
                drawFrame(String.valueOf(letters.charAt(i)));
                Thread.sleep(1000);
                drawFrame("");
                Thread.sleep(500);
            } catch(InterruptedException e) {
                System.out.println(e);
            }

        }
    }

    public String solicitNCharsInput(int n) {
        String inputString = "";
        drawFrame(inputString);
        while (inputString.length() < n) {
            if (StdDraw.hasNextKeyTyped()) {
                inputString += StdDraw.nextKeyTyped();
                drawFrame(inputString);
            } else {
                continue;
            }
        }
        StdDraw.pause(500);
        return inputString;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        int round;
        //TODO: Establish Game loop
        for (round = 1; ; round++) {
            drawFrame("Round : " + round);
            StdDraw.pause(1000);

            drawFrame("");
            StdDraw.pause(500);

            String s = generateRandomString(round);
            flashSequence(s);
            String input = solicitNCharsInput(round);

            if (!s.equals(input)) {
                drawFrame("Game Over! You made it to round: " + round);
                break;
            }
        }
    }

    public static void setSeed(int seed) {
        seed = seed;
    }

}

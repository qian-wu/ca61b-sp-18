package byog.Core;

import byog.Core.Core.generator.ShapeGenerator;
import byog.Core.Core.shape.Rectangle;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.HexWorld;
import byog.lab5.Position;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {

        int seed = 0;

        if (input.charAt(0) != 'N') {
            return null;
        }

        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == 'S') {
                break;
            }

            if (isNumeric(c)) {
                seed = 10 * seed + Integer.parseInt(String.valueOf(c));
            }
        }

        //initial world
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        initWorld(finalWorldFrame);

        //get start position
        Position start = ShapeGenerator.getRandomPosition(WIDTH, HEIGHT);

        //draw shape
        Rectangle r = ShapeGenerator.getRectangle(finalWorldFrame, start, ShapeGenerator.getRandomOrient());
        r.draw();

        finalWorldFrame[start.x][start.y] = Tileset.LOCKED_DOOR;

        return finalWorldFrame;
    }

    private boolean isNumeric(char c) {
        try{
            int i = Integer.parseInt(String.valueOf(c));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void initWorld(TETile[][] tiles) {
        for (int x = 0; x < this.WIDTH; x += 1) {
            for (int y = 0; y < this.HEIGHT; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }
}

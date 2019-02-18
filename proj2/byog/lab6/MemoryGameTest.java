package byog.lab6;

import org.junit.Test;

import static org.junit.Assert.*;

public class MemoryGameTest {
    private static MemoryGame game = new MemoryGame(40, 30);

    @Test
    public void generateRandomString() {
        game.setSeed(43);
        String s = game.generateRandomString(5);
        assertTrue(new String("anhyx").equals(s));
    }

    @Test
    public void drawFrame() {
        game.setSeed(43);
        String s = game.generateRandomString(5);
        game.drawFrame("Hello World !");
    }

    @Test
    public void flashSequence() {

    }

    @Test
    public void solicitNCharsInput() {
    }

    @Test
    public void startGame() {
    }

    public static void main(String[] args) {
        game.setSeed(43);
        String s = game.generateRandomString(5);
        game.flashSequence(s);
    }

}
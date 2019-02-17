package byog.Core;

import org.junit.Test;

public class TestGame {
    Game game = new Game();

    @Test
    public void testPlayWithInputString() {
        game.playWithInputString("N123S");
    }
}

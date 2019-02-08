import edu.princeton.cs.algs4.StdAudio;
import synthesizer.GuitarString;

public class GuitarHero {
    private static final double CONCERT = 440.0;
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    private static GuitarString[] initial(int size) {
        GuitarString[] guitarStrings = new GuitarString[size];
        for (int i = 0; i < guitarStrings.length; i++) {
            guitarStrings[i] = new GuitarString(CONCERT * Math.pow(2, (i - 24) / 12));
        }
        return guitarStrings;
    }

    public static void main(String[] args) {
        GuitarString[] strings = initial(KEYBOARD.length());

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();

                for (int i = 0; i < KEYBOARD.length(); i++) {
                    if (key == KEYBOARD.charAt(i)) {
                        strings[i].pluck();
                    }
                }
            }

            double sample = 0;
            for (int i = 0; i < strings.length; i++) {
                sample += strings[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < strings.length; i++) {
                strings[i].tic();
            }
        }
    }
}

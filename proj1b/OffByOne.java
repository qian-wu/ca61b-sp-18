public class OffByOne implements CharacterComparator {

    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        return diff == 1;
    };

}

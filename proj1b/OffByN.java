public class OffByN implements CharacterComparator {

    private int offSet;

    public OffByN(int n) {
        offSet = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        return  diff == offSet;
    }
}

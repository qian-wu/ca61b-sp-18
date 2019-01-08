public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque d = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

//    public static void main(String[] args) {
//        String s = "abcd";
//        System.out.println(s.length());
//    }
}

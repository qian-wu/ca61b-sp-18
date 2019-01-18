public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque d = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        Palindrome p = new Palindrome();
        Deque<Character> d = p.wordToDeque(word);
        return isPalindromeRecur(d);
    }

    private boolean isPalindromeRecur(Deque<Character> d) {
        if (d.size() <= 1) {
            return true;
        }

        if (d.removeFirst().equals(d.removeLast())) {
            return isPalindromeRecur(d);
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Palindrome p = new Palindrome();
        Deque<Character> d = p.wordToDeque(word);
        return isPalindromeRecur(d, cc);
    }

    private boolean isPalindromeRecur(Deque<Character> d, CharacterComparator cc) {
        if (d.size() <= 1) {
            return true;
        }

        if (cc.equalChars(d.removeFirst(), d.removeLast())) {
            return isPalindromeRecur(d, cc);
        } else {
            return false;
        }
    }
}

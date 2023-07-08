import java.util.ArrayList;
import java.util.HashSet;

public class WordChain {
    final int SOLVED_LETTERS_COUNT = 12;
    ArrayList<String> wordChain = new ArrayList<>();
    HashSet<Character> letters = new HashSet<>();
    char lastChar;

    public WordChain() {
    }
    public WordChain(String word) {
        this.add(word);
    }

    public boolean add(String newWord) {
        if (wordChain.size() == 0) {
            this.addWord(newWord);
            return true;
        }

        char firstCharOfNewWord = newWord.charAt(0);


        if (firstCharOfNewWord == lastChar) {
            this.addWord(newWord);
            return true;
        }
        return false;
    }

    private void addWord(String newWord) {
        wordChain.add(newWord);
        lastChar = newWord.charAt(newWord.length() - 1);
        for (char letter: newWord.toCharArray()) {
            letters.add(letter);
        }
    }
    public boolean solved() {
        return letters.size() == SOLVED_LETTERS_COUNT;
    }
}

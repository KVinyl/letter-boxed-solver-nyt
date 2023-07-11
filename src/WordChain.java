import java.util.ArrayList;
import java.util.HashSet;

public class WordChain {
    final int SOLVED_LETTERS_COUNT = 12;
    ArrayList<String> wordChainList = new ArrayList<>();
    HashSet<Character> letters = new HashSet<>();
    char lastChar;

    public WordChain() {
    }
    public WordChain(String word) {
        this.add(word);
    }
    public WordChain(WordChain wordChain) {
        this.wordChainList = new ArrayList<>(wordChain.wordChainList);
        this.letters = new HashSet<Character>(wordChain.letters);
        this.lastChar = wordChain.lastChar;
    }

    public boolean add(String newWord) {
        if (wordChainList.size() == 0) {
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
        wordChainList.add(newWord);
        lastChar = newWord.charAt(newWord.length() - 1);
        for (char letter: newWord.toCharArray()) {
            letters.add(letter);
        }
    }

    public boolean isSolved() {
        return letters.size() == SOLVED_LETTERS_COUNT;
    }

    public String[] toArray() {
        return wordChainList.toArray(new String[wordChainList.size()]);
    }

    public String toString() {
        return String.join(", ", wordChainList);
    }

    public int length() {
        return wordChainList.size();
    }
}

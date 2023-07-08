import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;

public class LetterBoxedSolver {
    String[] sides;
    String[] words;

    Dictionary<Character, HashSet<String>> wordDictionary = new Hashtable<>();
    public LetterBoxedSolver(String[] sides, String[] words) {
        this.sides = sides;
        this.words = words;

        initializeWordDictionary();
        loadWordDictionary();
    }

    private void initializeWordDictionary() {
        for (String side: sides) {
            for (char letter: side.toCharArray()) {
                wordDictionary.put(letter, new HashSet<>());
            }
        }
    }

    private void loadWordDictionary() {
        for (String word: words) {
            char firstLetter = word.charAt(0);
            wordDictionary.get(firstLetter).add(word);
        }
    }
}

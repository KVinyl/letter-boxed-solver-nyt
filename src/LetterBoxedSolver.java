import java.util.*;

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

    public String[] solution() {
        Queue<WordChain> wordChainQueue = initializeWordChainQueue();

        WordChain chain;
        while (true) {
            chain = wordChainQueue.remove();

            if (chain.isSolved()) {
                return chain.toArray();
            }

            for (String nextWord: wordDictionary.get(chain.lastChar)) {
                WordChain nextWordChain = new WordChain(chain);
                nextWordChain.add(nextWord);

                wordChainQueue.add(nextWordChain);
            }
        }
    }

    private Queue<WordChain> initializeWordChainQueue() {
        List<WordChain> wordChainList = new ArrayList<>();
        
        for (String word: words) {
            wordChainList.add(new WordChain(word));
        }

        Collections.shuffle(wordChainList);

        return new LinkedList<>(wordChainList);
    }
}

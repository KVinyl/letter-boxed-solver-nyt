import org.json.JSONArray;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GameData gameData = new GameData();

        String[] words = convertToStringArray(gameData.dictionary);
        String[] sides = convertToStringArray(gameData.sides);

        LetterBoxedSolver solver = new LetterBoxedSolver(sides, words);
        String[] solution = solver.solution();

        System.out.println(String.join(", ", solution));
    }

    private static String[] convertToStringArray(JSONArray arr) {
        if (arr == null) {
            return null;
        }

        ArrayList<String> strList = new ArrayList<>();
        for (Object obj: arr) {
            strList.add(obj.toString());
        }
        return strList.toArray(new String[strList.size()]);
    }
}
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class GameData {
    private String url = "https://www.nytimes.com/puzzles/letter-boxed";

    JSONArray sides;
    JSONArray dictionary;

    public GameData() {
        JSONObject gameData = extractGameData();

        if (gameData != null) {
            sides = gameData.getJSONArray("sides");
            dictionary = gameData.getJSONArray("dictionary");
        }
    }
    
    private JSONObject extractGameData() {
        try {
            Document doc = Jsoup.connect(url).get();

            Element gameDataElement = doc.select("script[type]").first();
            String gameDataString = gameDataElement.toString();

            int beginIndex  = gameDataString.indexOf('{');
            int endIndex = gameDataString.lastIndexOf('}') + 1;
            String gameDataJsonString = gameDataString.substring(beginIndex, endIndex);

            return new JSONObject(gameDataJsonString);

        } catch (IOException ex) {
            return null;
        }
    }
}
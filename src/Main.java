import org.json.JSONArray;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Main {
    private static WebDriver driver = new ChromeDriver();
    private static JavascriptExecutor js = (JavascriptExecutor) driver;

    public static void main(String[] args) {
        String[] solution = solveGame();

        openGame();
        playGame(solution);
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

    private static void openGame() {
        String url = "https://www.nytimes.com/puzzles/letter-boxed";

        driver.manage().window().maximize();
        driver.get(url);

        WebElement playButton = driver.findElement(By.className("pz-moment__button"));
        WebElement letterBoxedContainer = driver.findElement(By.id("letter-boxed-container"));

        js.executeScript("arguments[0].scrollIntoView();", letterBoxedContainer);

        delay(500);
        playButton.click();
    }

    private static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String[] solveGame() {
        GameData gameData = new GameData();

        String[] words = convertToStringArray(gameData.dictionary);
        String[] sides = convertToStringArray(gameData.sides);

        for (int i = 0; i < sides.length; i++ ) {
            int n = i + 1;
            System.out.println("side " + n + ": " + sides[i]);
        }

        LetterBoxedSolver solver = new LetterBoxedSolver(sides, words);
        String[] solution = solver.solution();

        System.out.println();
        System.out.println("Solution:");
        System.out.println(String.join(", ", solution));

        return solution;
    }

    private static void playGame(String[] solution) {
        WebElement body = driver.findElement(By.tagName("body"));

        delay(1000);

        for (String word : solution) {
            for (char ch : word.toCharArray()) {
                String letter = String.valueOf(ch);
                body.sendKeys(letter);
                delay(300);
            }

            delay(300);
            body.sendKeys(Keys.ENTER);
            delay(500 * word.length());
        }
    }
}
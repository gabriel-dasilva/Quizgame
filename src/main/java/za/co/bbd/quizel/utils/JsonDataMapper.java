package za.co.bbd.quizel.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import za.co.bbd.quizel.Genre;
import za.co.bbd.quizel.QuizQuestion;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonDataMapper {
    private JsonDataMapper() {
    }

    public static List<Genre> getAllData() {
        // TODO: Break method into single responsibility methods
        URL filePath = JsonDataMapper.class.getClassLoader().getResource("data.json");

        JSONParser parser = new JSONParser();
        JSONObject gameData;
        try {
            assert filePath != null;
            try (FileReader reader = new FileReader(filePath.getPath())
            ) {
                gameData = (JSONObject) parser.parse(reader);
            }
        } catch (ParseException ex) {
            // TODO:  add logging
            return new ArrayList<Genre>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Gson gson = new Gson();
        Type conversionType = new TypeToken<List<QuizQuestion>>(){}.getType();

        // TODO: Get map entries and use them to generate genres in reusable method
        List<QuizQuestion> harryPotterQuestions = gson
                .fromJson(gameData.get("Harry Potter").toString(), conversionType);
        List<QuizQuestion> onePieceQuestions = gson
                .fromJson(gameData.get("One Piece Quiz").toString(), conversionType);
        List<QuizQuestion> playStationQuestions = gson
                .fromJson(gameData.get("PlayStation").toString(), conversionType);
        List<QuizQuestion> sportsQuestions = gson
                .fromJson(gameData.get("Sports").toString(), conversionType);
        List<QuizQuestion> scienceQuestions = gson
                .fromJson(gameData.get("Science").toString(), conversionType);
        List<QuizQuestion> mathQuestions = gson
                .fromJson(gameData.get("Math").toString(), conversionType);

        List<Genre> genres = new ArrayList<Genre>();
        genres.add(new Genre("Harry Potter", harryPotterQuestions));
        genres.add(new Genre("One Piece Quiz", onePieceQuestions));
        genres.add(new Genre("Playstation", playStationQuestions));
        genres.add(new Genre("Sports", sportsQuestions));
        genres.add(new Genre("Science", scienceQuestions));
        genres.add(new Genre("Math", mathQuestions));

        return genres;
    }
}

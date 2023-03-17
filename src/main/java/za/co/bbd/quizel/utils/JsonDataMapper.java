package za.co.bbd.quizel.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import za.co.bbd.quizel.Genre;
import za.co.bbd.quizel.QuizQuestion;

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
            gameData = (JSONObject) parser.parse(filePath.getPath());
        } catch (ParseException ex) {
            // TODO:  add logging
            return new ArrayList<Genre>();
        }

        Gson gson = new Gson();
        Type conversionType = new TypeToken<List<QuizQuestion>>(){}.getType();

        // TODO: Get map entries and use them to generate genres in reusable method
        List<QuizQuestion> harryPotterQuestions = gson
                .fromJson((String) gameData.get("Harry Potter"), conversionType);
        List<QuizQuestion> onePieceQuestions = gson
                .fromJson((String) gameData.get("One Piece Quiz"), conversionType);
        List<QuizQuestion> playStationQuestions = gson
                .fromJson((String) gameData.get("PlayStation"), conversionType);
        List<QuizQuestion> sportsQuestions = gson
                .fromJson((String) gameData.get("Sports"), conversionType);
        List<QuizQuestion> scienceQuestions = gson
                .fromJson((String) gameData.get("Science"), conversionType);
        List<QuizQuestion> mathQuestions = gson
                .fromJson((String) gameData.get("Math"), conversionType);

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

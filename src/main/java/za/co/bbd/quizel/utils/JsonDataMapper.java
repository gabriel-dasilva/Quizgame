package za.co.bbd.quizel.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.bbd.quizel.models.Genre;
import za.co.bbd.quizel.models.QuizQuestion;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonDataMapper {
    private static final Logger log = LoggerFactory.getLogger(JsonDataMapper.class);

    private JsonDataMapper() {
    }

    public static List<Genre> getAllData() {
        URL filePath = JsonDataMapper.class.getClassLoader().getResource("data.json");

        JSONParser parser = new JSONParser();
        JSONObject gameData;
        try {
            assert filePath != null;
            try (FileReader reader = new FileReader(filePath.getPath())
            ) {
                gameData = (JSONObject) parser.parse(reader);
            }
        } catch (ParseException exception) {
            log.error("Failed to parse Json", exception);
            return new ArrayList<Genre>();
        } catch (IOException exception) {
            log.error("Failed to read file", exception);
            throw new RuntimeException(exception);
        }

        Gson gson = new Gson();
        List<Genre> genres = new ArrayList<Genre>();
        Set<Map.Entry<String, JSONArray>> entries = gameData.entrySet();

        Type conversionType = new TypeToken<List<QuizQuestion>>(){}.getType();
        for(Map.Entry<String, JSONArray> entry : entries) {
            String jsonString = entry.getValue().toString();
            List<QuizQuestion> questions = gson.fromJson(jsonString, conversionType);

            log.debug("Adding \"{}\" genre with {} questions", entry.getKey(), questions.size());
            genres.add(new Genre(entry.getKey(), questions));
        }

        return genres;
    }
}

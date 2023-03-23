package za.co.bbd.quizel.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.bbd.quizel.models.Genre;
import za.co.bbd.quizel.models.QuizQuestion;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonDataMapper {
    private static final Logger log = LoggerFactory.getLogger(JsonDataMapper.class);

    private JsonDataMapper() {
    }

    public static List<Genre> getAllData() {
        JSONParser parser = new JSONParser();
        JSONObject gameData;
        try {
            try (InputStream stream = JsonDataMapper.class.getClassLoader()
                    .getResourceAsStream("data.json")
            ) {
                assert stream != null;
                gameData = (JSONObject) parser.parse(IOUtils.toString(stream, StandardCharsets.UTF_8));
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

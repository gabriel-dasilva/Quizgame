package za.co.bbd.quizel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class Genre {
    public final String GenreDescription;

    public List<QuizQuestion> GenreQuestions;
}


package za.co.bbd.quizel.models;

import java.util.List;

public record Genre(String GenreDescription, List<QuizQuestion> GenreQuestions) {
}


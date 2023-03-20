package za.co.bbd.quizel.models;

import za.co.bbd.quizel.models.QuizQuestion;

import java.util.List;

public record Genre(String GenreDescription, List<QuizQuestion> GenreQuestions) {
}


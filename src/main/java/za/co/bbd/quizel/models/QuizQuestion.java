package za.co.bbd.quizel.models;

import java.util.List;

public record QuizQuestion(String question, String answer, List<String> options) {
}

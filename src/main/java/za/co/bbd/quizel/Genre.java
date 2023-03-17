package za.co.bbd.quizel;

public class Genre {
    public String GenreDescription;
    public QuizQuestion[] GenreQuestions;
    public Genre(String genreDesc){
        GenreDescription = genreDesc;
        GenreQuestions = loadGenreQuestions(genreDesc);
    }

    // Returns an array of questions depending on what the Genre Description is
    private QuizQuestion[] loadGenreQuestions(String genreDesc){
        QuizQuestion[] genreQuestions = {};

        // ToDo: Add functionality to read the questions into the genreQuestions using the genreDescription

        return genreQuestions;
    }
    public String getGenreDescription() {
        return GenreDescription;
    }

    public void setGenreDescription(String genreDescription) {
        GenreDescription = genreDescription;
    }

    public QuizQuestion[] getGenreQuestions() {
        return GenreQuestions;
    }

    public void setGenreQuestions(QuizQuestion[] genreQuestions) {
        GenreQuestions = genreQuestions;
    }
}

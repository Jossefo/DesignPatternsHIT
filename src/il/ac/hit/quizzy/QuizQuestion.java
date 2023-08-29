package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestion implements IQuizQuestion {
    private String title;
    private String question;
    private List<IQuizAnswer> answers = new ArrayList<>();

    // Protected constructor
    protected QuizQuestion() {
        // Initialize any default values or perform setup here
    }

    public String getTitle() {
        return title;
    }

    public String getQuestion() {
        return question;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    // Nested Builder class
    public static class Builder implements IQuizQuestionBuilder {
        private QuizQuestion questionToBuild;

        public Builder() {
            questionToBuild = new QuizQuestion();
        }

        @Override
        public IQuizQuestionBuilder setTitle(String text) {
            questionToBuild.title = text;
            return this;
        }

        @Override
        public IQuizQuestionBuilder setQuestion(String text) {
            questionToBuild.question = text;
            return this;
        }

        @Override
        public IQuizQuestionBuilder addAnswer(String text, boolean correct) {
            IQuizAnswer answer = new QuizAnswer(text, correct);
            questionToBuild.answers.add(answer);
            return this;
        }

        @Override
        public IQuizQuestion create() {
            return questionToBuild;
        }
    }
}

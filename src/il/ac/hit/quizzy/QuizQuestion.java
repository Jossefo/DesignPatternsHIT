package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestion implements IQuizQuestion {
    private String title;
    private String question;
    private List<IQuizAnswer> answers = new ArrayList<>();


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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Title: ").append(title).append("\n");
        builder.append("Question: ").append(question).append("\n");
        builder.append("Answers:\n");
        for (IQuizAnswer answer : answers) {
            builder.append(answer.getAnswer()).append(" (Correct: ").append(answer.isCorrect()).append(")\n");
        }
        return builder.toString();
    }

    @Override
    public List<IQuizAnswer> getAnswers() {
        return answers;
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

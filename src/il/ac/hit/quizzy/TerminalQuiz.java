package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

public class TerminalQuiz implements IQuiz {
    private String name;
    private List<IQuizQuestion> questions = new ArrayList<>();

    @Override
    public void start() {
        // Implement the quiz logic for the terminal interface
    }

    @Override
    public void setName(String text) {
        this.name = text;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addQuestion(IQuizQuestion question) {
        questions.add(question);
    }

    @Override
    public List<IQuizQuestion> getQuestions() {
        return questions;
    }
    @Override
    public IQuiz clone() throws CloneNotSupportedException {
        TerminalQuiz clonedQuiz = (TerminalQuiz) super.clone();
        // Выполните клонирование для всех неизменяемых полей, если такие есть
        clonedQuiz.questions = new ArrayList<>(this.questions);
        return clonedQuiz;
    }
}
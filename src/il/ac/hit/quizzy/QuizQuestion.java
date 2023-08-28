package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestion implements IQuizQuestion {
    private String question;
    private List<IQuizAnswer> answers = new ArrayList<>();

    public QuizQuestion(String question) {
        this.question = question;
    }

    @Override
    public String askQuestion() {
        return question;
    }

    @Override
    public List<IQuizAnswer> getAnswers() {
        return answers;
    }

    public void addAnswer(IQuizAnswer answer) {
        answers.add(answer);
    }
}

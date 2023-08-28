package il.ac.hit.quizzy;

import java.util.List;

public interface IQuizQuestion {
    String askQuestion();
    List<IQuizAnswer> getAnswers();
}

//============

public interface IQuizAnswer {
    String getAnswer();
    boolean isCorrect();
}

public class QuizAnswer implements IQuizAnswer {
    private String answer;
    private boolean correct;

    public QuizAnswer(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean isCorrect() {
        return correct;
    }
}
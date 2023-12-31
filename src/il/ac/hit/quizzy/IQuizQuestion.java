package il.ac.hit.quizzy;

import java.util.List;

public interface IQuizQuestion {

    List<IQuizAnswer> getAnswers();

    CharSequence getTitle();

    String getQuestion();

    boolean isAnswerCorrect(int i);
}
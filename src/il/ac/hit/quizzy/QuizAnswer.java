package il.ac.hit.quizzy;

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

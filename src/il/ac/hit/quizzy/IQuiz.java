package il.ac.hit.quizzy;

public interface IQuiz {
    void start();
    void setName(String text);
    String getName();
    void addQuestion(IQuizQuestion question);

}

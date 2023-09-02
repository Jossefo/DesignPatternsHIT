package il.ac.hit.quizzy;

public abstract class UIQuiz implements IQuiz, Cloneable {
    protected QuizType quizType;

    @Override
    public Object clone(){
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}

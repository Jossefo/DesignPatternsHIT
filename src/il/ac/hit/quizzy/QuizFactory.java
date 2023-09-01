package il.ac.hit.quizzy;
public class QuizFactory {
    private IQuiz terminalQuizPrototype;
    private IQuiz guiQuizPrototype;

    public QuizFactory() {
        // Initialize the prototypes for TerminalQuiz and GUIQuiz
        terminalQuizPrototype = new TerminalQuiz();
        guiQuizPrototype = new GUIQuiz();
    }

    public IQuiz createQuiz(QuizType type) {
        if (type == QuizType.TERMINAL) {
            return cloneQuiz(terminalQuizPrototype);
        } else if (type == QuizType.GUI) {
            return cloneQuiz(guiQuizPrototype);
        } else {
            throw new IllegalArgumentException("Invalid QuizType");
        }
    }

    private IQuiz cloneQuiz(IQuiz quizPrototype) {
        // Create a new instance of the specified quiz type using the clone method
        try {
            return (IQuiz) quizPrototype.clone();
        } catch (CloneNotSupportedException e) {

            e.printStackTrace();
            return null;
        }
    }
}
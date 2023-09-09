package il.ac.hit.quizzy;
public class QuizFactory {
    private IQuiz terminalQuizPrototype;
    private IQuiz guiQuizPrototype;

    public QuizFactory() {
        // Initialize prototypes for Terminal/GUI
        guiQuizPrototype = new GUIQuiz();
        terminalQuizPrototype = new TerminalQuiz();

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
        // Create a new instance
        try {
            return (IQuiz) quizPrototype.clone();
        } catch (CloneNotSupportedException e) {

            e.printStackTrace();
            return null;
        }
    }
}
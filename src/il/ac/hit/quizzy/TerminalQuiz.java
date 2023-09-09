package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TerminalQuiz extends UIQuiz implements IQuiz {
    private String name;
    private List<IQuizQuestion> questions = new ArrayList<>();
    private int score = 0;

    @Override
    public List<IQuizQuestion> getQuestions() {
        return questions;
    }

    public TerminalQuiz() {
        this.quizType = QuizType.TERMINAL;
    }

    @Override
    public void start() {
        System.out.println("Start - Quiz Terminal");
        play();
        endTerminal();
    }

    private void endTerminal() {
        System.out.println("Quiz - Quit , Score --> " + getScore());
    }


    @Override
    public void setName(String name) {
        if (name.isEmpty()) {
            System.out.println("Quiz name is empty - setting to Default Name");
            this.name = "Default Name";
        } else {
            this.name = name;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addQuestion(IQuizQuestion question) {
        questions.add(question);
    }

    private void play() {
        System.out.println("Playing Quiz :" + getName());
        for (IQuizQuestion question : questions) {
            boolean valid_choose = false;
            int user_answer = -1;
            int answer_range = question.getAnswers().size();
            System.out.println("Question :" + getQuestions());
            System.out.println("Answers : ");
            printAnswers(question);

            Scanner scanner;
            scanner = new Scanner(System.in);

            while (!valid_choose) {
                System.out.println("Your answer (1-" + answer_range + ") --> ");
                try {
                    user_answer = scanner.nextInt();
                    scanner.nextLine();

                    if (question.isAnswerCorrect(user_answer-1)) {
                        valid_choose = true;
                    } else {
                        System.out.println("Answer out of range");
                    }
                } catch (Exception e) {
                    System.out.println("Enter new answer.");
                    scanner.nextLine();
                }
            }

            if (question.isAnswerCorrect(user_answer - 1)) {
                System.out.println("Correct answer");
                setScore(getScore() + 1);
            } else {
                System.out.println("Try again : ");
            }
        }
    }


    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        if (score > 0) {
            this.score = score;
        }
    }

    public void setQuestions(List<IQuizQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "TerminalQuiz{" + "name='" + name + '\'' + ", questions=" + questions + ", score=" + score + '}';
    }

    private void printAnswers(IQuizQuestion question) {
        int answerIndex = 1;
        for (IQuizAnswer answer : question.getAnswers()) {
            System.out.println(answerIndex + ". " + answer.getAnswer());
            answerIndex++;
        }

    }
    @Override
    public IQuiz clone() {
        TerminalQuiz clonedQuiz = (TerminalQuiz) super.clone();
        clonedQuiz.questions = new ArrayList<>(this.questions);
        return clonedQuiz;
    }

    @Override
    public QuizType getType() {
        return this.quizType;
    }
}
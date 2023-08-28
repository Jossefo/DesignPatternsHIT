package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

public class Quiz implements IQuiz {
    private String name;
    private List<IQuizQuestion> questions;

    public Quiz(String name){
        this.name = name;
        this.questions = new ArrayList<>();
    }

    @Override
    public void start(){
        System.out.println("Welcome to IQuiz");
        int total_questions = questions.size();
        int correct_answers = 0;

        // NEED TO IMPLEMENT QUESTIONS ITERATION

        double score = (double) correct_answers / total_questions * 100;

        System.out.println("Score :" + score + " .");
    }

    @Override
    public void setName(String text){
        this.name = text;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void addQuestion(IQuizQestion question){
        questions.add(question);
    }

}

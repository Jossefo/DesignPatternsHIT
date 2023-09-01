package il.ac.hit.quizzy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleCSVQuizFilesDAO implements IQuizFilesDAO{

    private static SimpleCSVQuizFilesDAO instance;

    private SimpleCSVQuizFilesDAO(){
        //Private constructor --> Singleton
    }

    public static synchronized SimpleCSVQuizFilesDAO getInstance(){
        if (instance == null) {
            instance = new SimpleCSVQuizFilesDAO();
        }
        return instance;
    }

    @Override
    public void saveQuizToFile(IQuiz quiz , String fileName) throws QuizException{
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))){
            writer.println(quiz.getName()); // save quiz name

            for (IQuizQuestion question : quiz.getQuestions()){
                writer.println(question.toString()); // save questions
            }
        } catch (IOException e) {
            throw new QuizException("Saving quiz to file - Failed !!",e);
        }
    }

    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException{
        List<IQuizQuestion> questions = new ArrayList<>();
        String quizName = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            quizName = reader.readLine();

            String line;
            while ((line = reader.readLine()) != null){
                // Parse question details and create IQuizQuestion instances
                // Add the instances to the questions list
            }

        } catch (IOException e){
            throw new QuizException("Loading quiz - Failed !!");
        }
        IQuiz loadedQuiz = new Quiz(quizName);
        for (IQuizQuestion question : questions){
            loadedQuiz.addQuestion(question);
        }
        return loadedQuiz;
    }


}

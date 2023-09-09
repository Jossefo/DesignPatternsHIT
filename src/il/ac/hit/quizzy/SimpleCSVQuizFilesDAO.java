package il.ac.hit.quizzy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class SimpleCSVQuizFilesDAO implements IQuizFilesDAO {

    private static SimpleCSVQuizFilesDAO instance;

    private SimpleCSVQuizFilesDAO() {
        // Singleton private constructor
    }

    public static SimpleCSVQuizFilesDAO getInstance() {
        if (instance == null) {
            instance = new SimpleCSVQuizFilesDAO();
        }
        return instance;
    }

    @Override
    public void saveQuizToFile(IQuiz quiz, String fileName) throws QuizException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Write quiz name
            writer.write(quiz.getName());
            writer.newLine();
            if (quiz.getType() == QuizType.GUI) {
                writer.write("GUI");
            } else {
                writer.write("TERMINAL");
            }
            writer.newLine();

            // Write questions and answers
            for (IQuizQuestion question : quiz.getQuestions()) {
                writer.write(questionToString(question));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new QuizException("Error while saving quiz to file: " + e.getMessage(), e);
        }
    }

    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String quizName = reader.readLine();
            String quizTypeStr = reader.readLine();
            QuizType quizType = QuizType.TERMINAL;

            if (quizName == null || quizName.isEmpty()) {
                throw new QuizException("Invalid quiz file format");
            }

            QuizFactory factory = new QuizFactory();
            if (Objects.equals(quizTypeStr, "GUI")) {
                quizType = QuizType.GUI;
            } else {
                quizType = QuizType.TERMINAL;
            }
            IQuiz quiz = factory.createQuiz(quizType);
            //IQuiz quiz = new GUIQuiz();
            quiz.setName(quizName);

            String line;
            while ((line = reader.readLine()) != null) {
                IQuizQuestion question = stringToQuestion(line);
                quiz.addQuestion(question);
            }

            return quiz;
        } catch (IOException e) {
            throw new QuizException("Error while loading quiz from file: " + e.getMessage(), e);
        }
    }




    private String questionToString(IQuizQuestion question) {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(question.getTitle());
        joiner.add(question.getQuestion());

        for (IQuizAnswer answer : question.getAnswers()) {
            joiner.add(answer.getAnswer());
            joiner.add(Boolean.toString(answer.isCorrect()));
        }

        return joiner.toString();
    }



    private IQuizQuestion stringToQuestion(String csv) {
        String[] parts = csv.split(",");


        if (parts.length < 3 || (parts.length) % 2 != 0) {
            throw new IllegalArgumentException("Invalid CSV maks format");
        }

        IQuizQuestionBuilder builder = new QuizQuestion.Builder(); // Instantiate the appropriate builder
        builder.setTitle(parts[0]);
        builder.setQuestion(parts[1]);

        for (int i = 2; i < parts.length; i += 2) {
            builder.addAnswer(parts[i], Boolean.parseBoolean(parts[i + 1]));
        }

        return builder.create();
    }

}
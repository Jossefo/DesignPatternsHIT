package il.ac.hit.quizzy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
            if (quizName == null || quizName.isEmpty()) {
                throw new QuizException("Invalid quiz file format");
            }

            IQuiz quiz = new GUIQuiz(); // Instantiate the appropriate quiz type (e.g., GUIQuiz or TerminalQuiz)
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

//    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
//        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
//            String quizName = reader.readLine();
//            if (quizName == null || quizName.isEmpty()) {
//                throw new QuizException("Invalid quiz file format");
//            }
//
//            IQuiz quiz = new TerminalQuiz(); // You can create the appropriate quiz type here
//            quiz.setName(quizName);
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                quiz.addQuestion(stringToQuestion(line));
//            }
//
//            return quiz;
//        } catch (IOException e) {
//            throw new QuizException("Error while loading quiz from file: " + e.getMessage(), e);
//        }
//    }

    // Helper method to convert a question to a CSV string
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

    // Helper method to convert a CSV string to a question
//    private IQuizQuestion stringToQuestion(String csv) {
//        String[] parts = csv.split(",");
//        if (parts.length < 3 || (parts.length - 1) % 2 != 0) {
//            throw new IllegalArgumentException("Invalid CSV format");
//        }
//
//        IQuizQuestionBuilder builder = new QuizQuestion.Builder()
//                .setTitle(parts[0])
//                .setQuestion(parts[1]);
//
//        for (int i = 2; i < parts.length; i += 2) {
//            builder.addAnswer(parts[i], Boolean.parseBoolean(parts[i + 1]));
//        }
//
//        return builder.create();
//    }

    private IQuizQuestion stringToQuestion(String csv) {
        String[] parts = csv.split(",");
        for (String part:parts){
            System.out.println(part.toString());
        }

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
//    private IQuizQuestion stringToQuestion(String csv) {
//        String[] parts = csv.split(",");
//        if (parts.length < 3 || (parts.length - 1) % 2 != 0) {
//            throw new IllegalArgumentException("Invalid CSV format");
//        }
//
//        // Extract the title and question from the parts array
//        String title = parts[0];
//        String question = parts[1];
//
//        // Create a builder for the quiz question
//        IQuizQuestionBuilder builder = new QuizQuestion.Builder()
//                .setTitle(title)
//                .setQuestion(question);
//
//        // Parse the answers and correctness values
//        for (int i = 2; i < parts.length; i += 2) {
//            String answerText = parts[i];
//            boolean isCorrect = Boolean.parseBoolean(parts[i + 1]);
//            builder.addAnswer(answerText, isCorrect);
//        }
//
//        // Create the quiz question
//        return builder.create();
//    }
}
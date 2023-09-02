package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GUIQuiz extends UIQuiz implements IQuiz {
    private String name;
    private List<IQuizQuestion> questions = new ArrayList<>();
    private int score = 0;
    private int curr_question_idx = 0 ;
    private JRadioButton select_buttom = null;
    private JFrame frame ;
    private JLabel question_label;
    private JPanel answer_panel;
    private JButton check_buttom;
    private JLabel score_label;

    //Methods :
public GUIQuiz(){
    this.quizType = QuizType.GUI;
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    question_label = new JLabel("Question :");
    check_buttom = new JButton("Check m answer");
    score_label = new JLabel("Score --> " + getScore());
    answer_panel = new JPanel();
}

@Override
public void start(){
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            initComponent();
            play();
        }
    });
}

@Override
public void setName(String name){
    if (name.isEmpty()) {
        this.name = "Default Name";
        frame.setTitle(this.name);
    } else {
        this.name = name;
        frame.setTitle(this.name);
    }
}

@Override
public String getName(){
    return this.name;
}

@Override
public  void addQuestion(IQuizQuestion question){
    questions.add(question);
}


public List<IQuizQuestion> getQuestions(){
    return questions;
}


public int getScore(){
    return score;
}

public void setScore(int score){
    if (score > 0) {
        this.score = score;
    }
}

private void play(){
    showNextQuestion();
}

private void initComponent(){
    frame.setLayout(new BorderLayout());
    frame.add(question_label, BorderLayout.NORTH);
    frame.add(check_buttom, BorderLayout.SOUTH);
    frame.add(score_label, BorderLayout.CENTER);
    frame.add(score_label , BorderLayout.EAST);
    frame.setSize(1000,1000);

    answer_panel.setLayout(new GridLayout((questions.get(curr_question_idx).getAnswers().size()) , 1));

    check_buttom.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            checkAnswer();
            showNextQuestion();
        }
    });

    Border pad = BorderFactory.createEmptyBorder(10,10,500,100);
    score_label.setBorder(pad);
    score_label.setFont(new Font("MONOSPACED",Font.BOLD , 50));
    question_label.setFont(new Font("MONOSPACED" , Font.BOLD , 35));
    frame.setVisible(true);
}


private void showNextQuestion() {
    if (curr_question_idx < questions.size()){
        score_label.setText("Score --> " + getScore());
        IQuizQuestion question = questions.get(curr_question_idx);
        question_label.setText(question.getQuestion());
        answer_panel.removeAll();
        answer_panel.setLayout(new GridLayout(question.getAnswers().size() , 1));

        renderAnswers(question);

        frame.revalidate();
        curr_question_idx++;
    } else {
        showResults();
    }
}

private void renderAnswers(IQuizQuestion curr_question){
    ButtonGroup button_group = new ButtonGroup();

    for (IQuizAnswer answer : curr_question.getAnswers()) {
        JRadioButton radioButton = new JRadioButton(answer.getAnswer());
        button_group.add(radioButton);

        radioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                select_buttom = radioButton;
            }
        });
    }
}

public void checkAnswer(){
    if (select_buttom != null) {
        IQuizQuestion question = questions.get(curr_question_idx - 1);
        component[] components = answer_panel.getComponents();

        for (int i=0 ; i < components.length ; i++){
            if (question.isAnswerCorrect(i)){
                setScore(getScore());
                score_label.setText("Score --> " + getScore());
            }
            break;
        }
    }
    select_buttom = null;
}

private void showResults() {
    JOptionPane.showMessageDialog(frame , "Quiz Done , Score --> " + getScore() + " --- " );
    frame.dispose();
}

}
import javax.swing.*;

public class QuizzyGUI extends JFrame{
    private JPanel mainPanel;
    private JTextField questionTextField1;

    public QuizzyGUI(String title){
        super();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new QuizzyGUI("My Quizzy");
        frame.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HighScoreInputFrame extends JFrame {

    private JTextField scoreInput;
    private JButton submitButton;
    public HighScoreInputFrame(){
        setTitle("High Score Input");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 150);
        setVisible(true);
        setBackground(GameData.lightBackgroundColor);
        setLayout(new GridLayout(4, 1));

        add(new JLabel("Congratulation! You finished the game with " + GameData.point + " points!"));
        add(new JLabel("Please enter your name down here!"));
        scoreInput = new JTextField();
        add(scoreInput);
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonActionListener());
        add(submitButton);
    }

    private class SubmitButtonActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            // check if both inputs are valid integers
           String input = scoreInput.getText();

           if( input.length()>100){
               JOptionPane.showMessageDialog(HighScoreInputFrame.this,
                       "Name must be less than 100 character", "Error", JOptionPane.ERROR_MESSAGE);
           } else if(input.length() == 0){
               JOptionPane.showMessageDialog(HighScoreInputFrame.this,
                       "Empty Input", "Error", JOptionPane.ERROR_MESSAGE);
           } else {
               HighScore newScore = new HighScore(input, GameData.point);
               List<HighScore> tempList = HighScoreManagement.readObjects();


               if(tempList == null || tempList.isEmpty()){
                   tempList = new ArrayList<>();
                   tempList.add(newScore);
               } else {
                   boolean added = false;
                   for(int i =0; i < tempList.size(); i++){
                       if(newScore.getScore() > tempList.get(i).getScore()){
                           tempList.add(i,newScore);
                           added = true;
                           break;
                       }
                   }
                   if(added == false){
                       tempList.add(newScore);
                   }
               }
               HighScoreManagement.writeObjects(tempList);
               JButton button = (JButton)e.getSource();
               Window window = SwingUtilities.windowForComponent(button);
               IntroMenu menu = new IntroMenu();
               window.dispose();

           }

        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameMenuCreation extends JFrame {
   private JTextField heightInput, widthInput;
   private JButton playButton;

   public GameMenuCreation() {
      setTitle("Starting Game");
      setLocationRelativeTo(null);
      setVisible(true);
      setSize(400, 150);
      setLayout(new GridLayout(3, 2));


      JLabel jLabelWidth = new JLabel("  Width:");
      add(jLabelWidth);
      widthInput = new JTextField();

      add(widthInput);

      add(new JLabel("  Height:"));
      heightInput = new JTextField();
      add(heightInput);

      playButton = new JButton("Play");
      playButton.setBackground(GameData.lightBackgroundColor);
      playButton.setFont(new Font("Arial", Font.BOLD, 12));
      playButton.setForeground(Color.white);
      add(playButton);

      JButton backButton = new JButton("Back to Menu");
      backButton.setFont(new Font("Arial", Font.BOLD, 12));
      backButton.setForeground(Color.white);
      backButton.setBackground(GameData.backgroundColor);
      backButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            IntroMenu introMenu = new IntroMenu();
            JButton button = (JButton)e.getSource();
            Window window = SwingUtilities.windowForComponent(button);
            window.dispose();
         }
      });
      add(backButton);

      playButton.addActionListener(new SubmitButtonActionListener());

      setTitle("Starting Game");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLocationRelativeTo(null);
      setVisible(true);
      setSize(400, 150);
      setLayout(new GridLayout(3, 2));

   }



   private class SubmitButtonActionListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         // check if both inputs are valid integers
         try {
            int height = Integer.parseInt(heightInput.getText());
            int width = Integer.parseInt(widthInput.getText());
            if ((height < 10 || height > 100) || (width < 10 || width > 100)) {
               JOptionPane.showMessageDialog(GameMenuCreation.this,
                       "Input must be between 10 and 100", "Error", JOptionPane.ERROR_MESSAGE);

            } else {
               GameData.inputRow = height;
               GameData.inputCol = width;
               DefaultSpriteData.initArrays();
               DefaultSpriteData.initSprites();
               GameBoard gameBoard = new GameBoard();
               dispose();
            }



            // do something with the inputs here
         } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(GameMenuCreation.this,
                    "Input must be a valid integer", "Error", JOptionPane.ERROR_MESSAGE);
         }

      }
   }

}
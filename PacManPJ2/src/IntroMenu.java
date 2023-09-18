import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IntroMenu extends JFrame {
    public IntroMenu(){

        Panel buttonPanel = new Panel();


        // Create the buttons
        JButton startGame = new JButton("Start Game");
        JButton highScore = new JButton("High Score");
        JButton  exit = new JButton("Exit");
        startGame.setBackground(GameData.lightBackgroundColor);
        highScore.setBackground(GameData.lightBackgroundColor);
        exit.setBackground(GameData.lightBackgroundColor);
        startGame.setForeground(Color.white);
        highScore.setForeground(Color.white);
        exit.setForeground(Color.white);
        startGame.setFont(new Font("Arial", Font.BOLD, 12));
        highScore.setFont(new Font("Arial", Font.BOLD, 12));
        exit.setFont(new Font("Arial", Font.BOLD, 12));
        startGame.addActionListener(new PlayButtonActionListener());
        highScore.addActionListener(new HighScoreButtonActionListener());
        exit.addActionListener(new ExitButtonActionListener());


        // Create a buttonPanel to hold the buttons
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0)); // Adjust spacing between buttons
        buttonPanel.add(startGame);
        buttonPanel.add(highScore);
        buttonPanel.add(exit);
        buttonPanel.setSize(400,400);
        buttonPanel.setBackground(GameData.backgroundColor);

        // Set the layout manager for the frame


        Panel picturePanel = new Panel();
        picturePanel.setBackground(GameData.backgroundColor);
        try{
            BufferedImage myPicture = ImageIO.read(new File("./src/imgData/pacman/right/tile006.png"));
            Image resizedPic = myPicture.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel picLabel = new JLabel(new ImageIcon(resizedPic));

            picturePanel.add(picLabel);
        }
        catch (IOException e){
            e.printStackTrace();
        }


        this.setTitle("PacMan - s28786");
        this.add(picturePanel,BorderLayout.PAGE_START);
        this.add(buttonPanel,BorderLayout.CENTER);
        this.pack();
        setLayout(new BorderLayout());

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setFocusable(true);
    }

    public static void main(String[] args) {
        IntroMenu introMenu = new IntroMenu();
    }

    private class PlayButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // check if both inputs are valid integers
            GameMenuCreation gameMenuCreation = new GameMenuCreation();

            dispose();

        }
    }


    private class HighScoreButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            HighScoreFrame newFrame = new HighScoreFrame();

            dispose();

        }
    }
    private class ExitButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {


            dispose();

        }
    }
}

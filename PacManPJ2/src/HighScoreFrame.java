import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class HighScoreFrame  extends JFrame {
    public HighScoreFrame(){


         DefaultListModel<HighScore> listModel = new DefaultListModel<>();
        List<HighScore> tempList = HighScoreManagement.readObjects();
        for (int i =0; i < tempList.size(); i++) {
            listModel.addElement(tempList.get(i));
        }

        // Create the JList
        JList<HighScore> list = new JList<>(listModel);
        list.setCellRenderer(new HighScoreListCellRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellHeight(80);

        JScrollPane scrollPane = new JScrollPane(list);

        this.setLayout(new GridLayout(2,1));



        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(78, 164, 246));
        backButton.setForeground(Color.white);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IntroMenu introMenu = new IntroMenu();
                JButton button = (JButton)e.getSource();
                Window window = SwingUtilities.windowForComponent(button);
                window.dispose();
            }
        });
        Panel panel = new Panel(new BorderLayout());
        panel.add(backButton, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBackground (new Color(0, 43, 86));
        // Add the panel to the frame

        setPreferredSize(new Dimension(300, 600));
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("High Scores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private static class HighScoreListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {

            Component renderedCell = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            HighScore highScore = (HighScore) value;
            String text = " Player: " + highScore.getName() + " - Points: " + highScore.getScore();
            setText(text);
            setFont(new Font("Arial", Font.PLAIN, 16));
            setForeground(Color.WHITE);
            if(index%2 == 0){
                renderedCell.setBackground(GameData.backgroundColor);
            } else {
                renderedCell.setBackground(GameData.lightBackgroundColor);
            }
            return renderedCell;

        }
    }

    public static void main(String[] args) {

    }
}




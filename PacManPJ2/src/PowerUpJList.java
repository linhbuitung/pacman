import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PowerUpJList extends JPanel {
    public static int width = 100;
    public PowerUpJList(){
        width = GameData.getRowNum()*PlayGroundGrid.currCellSize*3/7;
        this.setLayout(new GridBagLayout());

        JList<String> list = new JList<>(new String[] { " Speed Boost!", " Invincible!", " Eat Ghosts!", " Double Points", " Decrease Ghost Speed!" }){
            @Override
            public ListCellRenderer<? super String> getCellRenderer() {
                return new DefaultListCellRenderer() {
                    @Override
                    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                        //keep the color
                        renderer.setEnabled(true);
                        if(PacManMovement.powerUpsList[index] >0){
                            renderer.setForeground(Color.WHITE);
                        } else{
                            renderer.setForeground(Color.DARK_GRAY);
                        }

                        return renderer;
                    }
                };
            }
        };
        list.setBorder(BorderFactory.createEmptyBorder());
        list.setFocusable(false);
        list.setEnabled(false);
        list.setFont(new Font("Arial", Font.PLAIN, 12));
        list.setSize(new Dimension(width, GameData.getRowNum()*PlayGroundGrid.currCellSize));
        list.setFixedCellHeight(40);
        list.setBackground(GameData.backgroundColor);

        JScrollPane scrollPane = new JScrollPane(list);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(scrollPane, gbc);



        this.setPreferredSize(new Dimension(width, GameData.getRowNum()*PlayGroundGrid.currCellSize));
        this.setSize(new Dimension(GameData.getRowNum()*PlayGroundGrid.currCellSize*3/7, GameData.getRowNum()*PlayGroundGrid.currCellSize));
        this.setMinimumSize(this.getPreferredSize());
        this.setVisible(true);
    }


}

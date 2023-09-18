import javax.swing.*;
import java.awt.*;

public class LiveDataPanel extends JPanel {
    public static int minHeight = 100;

    public LiveDataPanel(){
        minHeight = GameData.getRowNum()*PlayGroundGrid.currCellSize*3/20;
        this.setPreferredSize(new Dimension(GameData.getColNum()*PlayGroundGrid.currCellSize + PowerUpJList.width, minHeight));
        this.setSize(new Dimension(GameData.getColNum()*PlayGroundGrid.currCellSize + PowerUpJList.width, minHeight));
        this.setMinimumSize(getPreferredSize());
        this.setBackground(GameData.backgroundColor);
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawScore(g2d);
    }

    private void drawScore(Graphics2D g) {
        int size = Math.min(this.getHeight()/5, this.getWidth()/5);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.setColor(new Color(255, 253, 148));
        String pointText = " Points: " +GameData.point;
        g.drawString(pointText, size,this.getHeight()/2);

        String levelText = "Level: " + GameData.level;
        g.drawString(levelText, this.getWidth()/2,this.getHeight()/2);


        for (int i = 0; i < PacMan.lives; i++) {
            g.drawImage(DefaultSpriteData.heartIcon, this.getWidth() - this.getWidth()/20 - size  - i*( size+5),
                    (this.getHeight()-  size)/2  , size,  size, this);
        }


    }

}

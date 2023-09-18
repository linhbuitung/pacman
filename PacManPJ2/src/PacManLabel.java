import javax.swing.*;
import java.awt.*;

public class PacManLabel extends JLabel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = PlayGroundGrid.table.getColumnModel().getColumn(0).getWidth();
        g.drawImage(DefaultSpriteData.currPacManAnim[PacMan.animationCycle], 0, 0, cellWidth, PlayGroundGrid.table.getRowHeight((int) PacMan.posY / GameData.CURRENT_CELL_SIZE), null);
    }
}

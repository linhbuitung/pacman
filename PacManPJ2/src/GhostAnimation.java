import javax.swing.*;
import java.awt.*;

public class GhostAnimation extends JLabel {
    private int ghostIndex;
    public GhostAnimation(int ghostIndex){
        this.ghostIndex = ghostIndex;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = PlayGroundGrid.table.getColumnModel().getColumn(0).getWidth();
            g.drawImage(Ghost.ghostList.get(ghostIndex).getImgAnimSprite()[Ghost.ghostList.get(ghostIndex).getAnimationCycle()],
                    0 , 0, cellWidth, PlayGroundGrid.table.getRowHeight((int) PacMan.posY / GameData.CURRENT_CELL_SIZE), this);

    }
}

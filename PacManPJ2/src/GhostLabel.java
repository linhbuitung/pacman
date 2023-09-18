import javax.swing.*;
import java.awt.*;

public class GhostLabel extends JLabel {
    private int i;
    public GhostLabel(int i){
        this. i = i;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = PlayGroundGrid.table.getColumnModel().getColumn(0).getWidth();
        g.drawImage(Ghost.ghostList.get(i).getImgAnimSprite()[Ghost.ghostList.get(i).getAnimationCycle()], 0, 0, cellWidth, PlayGroundGrid.table.getRowHeight((int) Ghost.ghostList.get(i).getPosY()/ GameData.CURRENT_CELL_SIZE), null);
    }
}

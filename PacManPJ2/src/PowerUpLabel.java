import javax.swing.*;
import java.awt.*;

public class PowerUpLabel extends JLabel {
    private int i;
    public PowerUpLabel(int i){
        this. i = i;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = PlayGroundGrid.table.getColumnModel().getColumn(0).getWidth();
        int cellHeight= PlayGroundGrid.table.getRowHeight((int) PowerUps.powerUps.get(i).getPosY() / GameData.CURRENT_CELL_SIZE);
        g.drawImage(PowerUps.powerUps.get(i).getPowerUpImg(),  cellWidth/10, 0, cellWidth - cellHeight/5, cellHeight - cellHeight/5 , null);
    }
}

import javax.swing.*;
import java.awt.*;

public class GameLayerPanel extends JLayeredPane {
    public GameLayerPanel(PlayGroundGrid playGroundGrid, GameBoard gameBoard, CharactersAnimationDrawnPanel charactersAnimationDrawnPanel){
        this.add(playGroundGrid, 2);
        this.add(charactersAnimationDrawnPanel, 0);
        this.setBackground(new Color(255, 255, 255, 80));
        charactersAnimationDrawnPanel.setPreferredSize(playGroundGrid.getSize());
        charactersAnimationDrawnPanel.setSize(playGroundGrid.getSize());

        this.setPreferredSize(playGroundGrid.getSize());
        this.setSize(playGroundGrid.getSize());
        this.setMinimumSize(playGroundGrid.getSize());

        //this.setBounds((gameBoard.getWidth()-this.getWidth())/2,5,
        //        GameData.getTableSizeCol() + 40, GameData.getTableSizeRow() + 40);

    }




}

import javax.swing.*;
import java.awt.*;

public class CharactersAnimationDrawnPanel extends JPanel {

    public CharactersAnimationDrawnPanel(){
        this.setBackground(new Color(0,0,0,0));
        this.setPreferredSize(new Dimension(GameData.getTableSizeCol() , GameData.getTableSizeRow() ));
        this.setSize(new Dimension(GameData.getTableSizeCol(), GameData.getTableSizeRow() ));
    };

    public void UpdateSizing(){
        this.setPreferredSize(new Dimension(GameData.getTableSizeCol() , GameData.getTableSizeRow() ));
        this.setSize(new Dimension(GameData.getTableSizeCol(), GameData.getTableSizeRow() ));
        this.setMinimumSize(this.getPreferredSize());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        drawGhosts(g2d);
        drawPowerUps(g2d);
    }

    public static void setPacmanAnim() {
        if (PacMan.dirX == -1) {
            DefaultSpriteData.currPacManAnim = DefaultSpriteData.pacmanLeft;
        } else if (PacMan.dirX == 1) {
            DefaultSpriteData.currPacManAnim = DefaultSpriteData.pacmanRight;
        } else if (PacMan.dirY == -1) {
            DefaultSpriteData.currPacManAnim = DefaultSpriteData.pacmanUp;
        } else if (PacMan.dirY == 1){
            DefaultSpriteData.currPacManAnim = DefaultSpriteData.pacmanDown;
        }

    }

    public void drawGhosts(Graphics2D g2d) {
        for(int i =0; i < Ghost.ghostList.size(); i++){

            g2d.drawImage(Ghost.ghostList.get(i).getImgAnimSprite()[Ghost.ghostList.get(i).getAnimationCycle()], Ghost.ghostList.get(i).getPosX() , Ghost.ghostList.get(i).getPosY(),
                    GameData.CURRENT_CELL_SIZE, GameData.CURRENT_CELL_SIZE, this);
        }


    }

    public void drawPowerUps(Graphics2D g2d){

        if(PowerUps.powerUps != null){
            for(int i =0; i < PowerUps.powerUps.size(); i++){

                g2d.drawImage(PowerUps.powerUps.get(i).getPowerUpImg(),
                        PowerUps.powerUps.get(i).getPosX() + GameData.CURRENT_CELL_SIZE/10 ,
                        PowerUps.powerUps.get(i).getPosY() + GameData.CURRENT_CELL_SIZE/10,
                        GameData.CURRENT_CELL_SIZE - GameData.CURRENT_CELL_SIZE/5,
                        GameData.CURRENT_CELL_SIZE - GameData.CURRENT_CELL_SIZE/5, this);
            }
        }
    }


}

import javax.swing.*;
import java.awt.*;

public class PacManMovement extends Thread{
    private GameBoard gameBoard;
    public int waitCycleAnim;
    private int defaultPacManCycle;
    public static int pacManCycle;
    public static int[] powerUpsList;
    public PacManMovement( GameBoard gameBoard){
        setUpPowerUpList();
        defaultPacManCycle = (int)200/GameData.CURRENT_CELL_SIZE;
        pacManCycle = defaultPacManCycle;
        waitCycleAnim = 0;
        this.gameBoard = gameBoard;
    }

    public void setUpPowerUpList(){
        if(powerUpsList == null){
            powerUpsList = new int[5];
        }
        for(int i = 0; i < powerUpsList.length; i++){
            powerUpsList[i] = 0;
        }
    }

     void pacManPowerUpManager(){
         if(GameData.inGame){
             synchronized (Locks.powerUpListLock){
                 if(PowerUps.powerUps != null){
                     for(int i = 0; i < PowerUps.powerUps.size(); i++){
                         if(PacMan.posX < PowerUps.powerUps.get(i).getPosX()+GameData.CURRENT_CELL_SIZE &&
                                 PacMan.posX+GameData.CURRENT_CELL_SIZE > PowerUps.powerUps.get(i).getPosX() &&
                                 PacMan.posY < PowerUps.powerUps.get(i).getPosY()+GameData.CURRENT_CELL_SIZE &&
                                 PacMan.posY+GameData.CURRENT_CELL_SIZE > PowerUps.powerUps.get(i).getPosY()){
                             powerUpsList[PowerUps.getPowerUp(i)] = 1;
                         }

                     }
                 }

             }
             for(int i = 0; i < powerUpsList.length; i++){
                 if(powerUpsList[i] > 0){
                     powerUpsList[i] += pacManCycle;
                 }
                 if(powerUpsList[i] > 10000){
                     powerUpsList[i] = 0;
                 }
             }
             if(powerUpsList[0] != 0 && pacManCycle == defaultPacManCycle){
                 pacManCycle = defaultPacManCycle/2;
             } else {
                 pacManCycle = defaultPacManCycle;
             }
             if(powerUpsList[1] != 0 ){
                 PacMan.collidable = false;
             } else {
                 PacMan.collidable = true;
             }
             synchronized (Locks.ghostSpeedLock){
                 if(powerUpsList[4] != 0 && GhostMovement.ghostCycle == GhostMovement.defaultGhostCycle){
                     GhostMovement.ghostCycle = GhostMovement.defaultGhostCycle*2;
                 } else {
                     GhostMovement.ghostCycle = GhostMovement.defaultGhostCycle;
                 }
             }
             if(powerUpsList[3] != 0 ){
                 GameData.pointGain = GameData.defaultPointGain*2;
             } else {
                 GameData.pointGain = GameData.defaultPointGain;
             }

             synchronized (Locks.eatGhostLock){
                 if(powerUpsList[2] != 0 ){
                     PacMan.eatGhost = true;
                 } else {
                     PacMan.eatGhost = false;
                 }
             }
         }



    }

    public void movePacman(){

            synchronized (Locks.pacmanPosLock){
                int posX;
                int posY;
                if(waitCycleAnim == 15){
                    PacMan.updateCycle();
                    waitCycleAnim = 0;
                } else{
                    waitCycleAnim++;
                }

                if (PacMan.posX % GameData.CURRENT_CELL_SIZE == 0 && PacMan.posY % GameData.CURRENT_CELL_SIZE == 0) {

                    posX = (int) PacMan.posX / GameData.CURRENT_CELL_SIZE;
                    posY = (int) PacMan.posY / GameData.CURRENT_CELL_SIZE;


                    if (GameTableModel.getDataAt(posX,posY) == 1) {
                        synchronized (Locks.pointLock){
                            GameData.point += GameData.pointGain;
                            GameTableModel.setValue(2,posY, posX);
                        }


                        if (GameTableModel.levelFinish()){
                            GameData.resetDataForNextLLevel();
                            PacMan.resetPosPacMan();
                            Ghost.setRandomPosAll();
                        }
                        //score++;
                    }

                    PacMan.dirX = 0;
                    PacMan.dirY = 0;
                    if (GameInput.getFirstKeyX() != 0 || GameInput.getFirstKeyY() != 0) {
                        if (!((GameInput.getFirstKeyX() == -1 && GameInput.getFirstKeyY() == 0 && (PacMan.posX <= 0|| GameTableModel.isWall(4,posX,posY))  )
                                || (GameInput.getFirstKeyX() == 1 && GameInput.getFirstKeyY() == 0 && (PacMan.posX + GameData.CURRENT_CELL_SIZE >= GameData.getTableSizeCol()|| GameTableModel.isWall(3,posX,posY)))
                                || (GameInput.getFirstKeyX() == 0 && GameInput.getFirstKeyY() == -1 && (PacMan.posY <= 0|| GameTableModel.isWall(2,posX,posY))   )
                                || (GameInput.getFirstKeyX() == 0 && GameInput.getFirstKeyY() == 1 && (PacMan.posY + GameData.CURRENT_CELL_SIZE >= GameData.getTableSizeRow() || GameTableModel.isWall(1,posX,posY)) ))) {
                            PacMan.dirX = GameInput.getFirstKeyX();
                            PacMan.dirY = GameInput.getFirstKeyY();

                        }


                    }


                    // Check for standstill

                }

                PacMan.posX += PacMan.speed * PacMan.dirX;
                PacMan.posY +=  PacMan.speed * PacMan.dirY;

            }

        //System.out.println(GameInput.pressList.get(0));
    }

    public void run(){
        while (!isInterrupted()){

            try {
                SwingUtilities.invokeLater(() -> {

                    pacManPowerUpManager();
                    movePacman();
                    PacMan.updateAnimPacman();
                });
                this.gameBoard.getComponent(0).repaint();
                if(!GameData.inGame){
                    gameBoard.dispose();
                    this.interrupt();

                    IntroMenu menu = new IntroMenu();
                    return;
                }
                Thread.sleep(pacManCycle);

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}


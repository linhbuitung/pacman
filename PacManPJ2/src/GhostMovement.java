import javax.swing.*;

public class GhostMovement extends Thread{
    private GameBoard gameBoard;
    public static int defaultGhostCycle;
    public static int ghostCycle;

    public GhostMovement(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        defaultGhostCycle = (int)250/GameData.CURRENT_CELL_SIZE;
        ghostCycle = defaultGhostCycle;
    }
    public void moveGhost() {
        synchronized (Locks.ghostPosLock){
            for (int i = 0; i < Ghost.ghostList.size(); i++) {
                int posX;
                int posY;
                if (Ghost.ghostList.get(i).getWaitCycleAnim() == 15) {
                    Ghost.ghostList.get(i).updateCycle();
                    Ghost.ghostList.get(i).setWaitCycleAnim(0);
                } else {
                    Ghost.ghostList.get(i).updateWaitCycleAnim();
                }

                if (Ghost.ghostList.get(i).getPosX() % GameData.CURRENT_CELL_SIZE == 0 && Ghost.ghostList.get(i).getPosY() % GameData.CURRENT_CELL_SIZE == 0) {

                    posX = (int) Ghost.ghostList.get(i).getPosX() / GameData.CURRENT_CELL_SIZE;
                    posY = (int) Ghost.ghostList.get(i).getPosY() / GameData.CURRENT_CELL_SIZE;
                    setRandomDirForGhostPos(i);

                    while (((Ghost.ghostList.get(i).getDirX() == -1 && Ghost.ghostList.get(i).getDirY() == 0 && (Ghost.ghostList.get(i).getPosX() <= 0 || GameTableModel.isWall(4, posX, posY)))
                            || (Ghost.ghostList.get(i).getDirX() == 1 && Ghost.ghostList.get(i).getDirY() == 0 && (Ghost.ghostList.get(i).getPosX() + GameData.CURRENT_CELL_SIZE >= GameData.getTableSizeCol() || GameTableModel.isWall(3, posX, posY)))
                            || (Ghost.ghostList.get(i).getDirX() == 0 && Ghost.ghostList.get(i).getDirY() == -1 && (Ghost.ghostList.get(i).getPosY() <= 0 || GameTableModel.isWall(2, posX, posY)))
                            || (Ghost.ghostList.get(i).getDirX() == 0 && Ghost.ghostList.get(i).getDirY() == 1 && (Ghost.ghostList.get(i).getPosY() + GameData.CURRENT_CELL_SIZE >= GameData.getTableSizeRow() || GameTableModel.isWall(1, posX, posY))))) {
                        setRandomDirForGhostPos(i);
                    }


                    // Check for standstill

                }
                Ghost.ghostList.get(i).setPosX(Ghost.ghostList.get(i).getPosX() + Ghost.ghostList.get(i).getSpeed() * Ghost.ghostList.get(i).getDirX());
                Ghost.ghostList.get(i).setPosY(Ghost.ghostList.get(i).getPosY() + Ghost.ghostList.get(i).getSpeed() * Ghost.ghostList.get(i).getDirY());
                if(checkPlayerCollision(i)){
                    PacMan.resetPosPacMan();
                    Ghost.setRandomPosAll();
                    PacMan.lives --;
                    if(PacMan.lives == 0){
                        GameData.inGame = false;
                        gameBoard.dispose();
                        HighScoreInputFrame frame = new HighScoreInputFrame();

                    }
                }
            }
        }

        }
    public boolean checkPlayerCollision(int i){

        synchronized (Locks.pacmanPosLock){
            synchronized (Locks.eatGhostLock){
                if (PacMan.eatGhost == true){
                    if(PacMan.posX < Ghost.ghostList.get(i).getPosX()+GameData.CURRENT_CELL_SIZE &&
                            PacMan.posX+GameData.CURRENT_CELL_SIZE > Ghost.ghostList.get(i).getPosX() &&
                            PacMan.posY < Ghost.ghostList.get(i).getPosY()+GameData.CURRENT_CELL_SIZE &&
                            PacMan.posY+GameData.CURRENT_CELL_SIZE > Ghost.ghostList.get(i).getPosY()){
                        Ghost.ghostList.get(i).setRandomPos();
                        synchronized (Locks.pointLock){
                            GameData.point += 2000;
                        }
                        return false;
                    }
                }
                synchronized (Locks.pacmanCollidableLock){

                    if(!PacMan.collidable){

                        return false;
                    }
                }

                if(PacMan.posX < Ghost.ghostList.get(i).getPosX()+GameData.CURRENT_CELL_SIZE &&
                        PacMan.posX+GameData.CURRENT_CELL_SIZE > Ghost.ghostList.get(i).getPosX() &&
                        PacMan.posY < Ghost.ghostList.get(i).getPosY()+GameData.CURRENT_CELL_SIZE &&
                        PacMan.posY+GameData.CURRENT_CELL_SIZE > Ghost.ghostList.get(i).getPosY()){

                    return true;
                }
            }

            return false;
        }

    }

    public void setRandomDirForGhostPos(int i){
        int rand = (int) ((Math.random() * (12-1 )) + 1);
        switch (rand){
            case 1:{
                Ghost.ghostList.get(i).setDirX(1);
                Ghost.ghostList.get(i).setDirY(0);
                break;
            }
            case 2:{
                Ghost.ghostList.get(i).setDirX(-1);
                Ghost.ghostList.get(i).setDirY(0);
                break;
            }
            case 3:{
                Ghost.ghostList.get(i).setDirX(0);
                Ghost.ghostList.get(i).setDirY(1);
                break;
            }
            case 4:{
                Ghost.ghostList.get(i).setDirX(0);
                Ghost.ghostList.get(i).setDirY(-1);
                break;
            }
            case 6:{
                Ghost.ghostList.get(i).setDirX(0);
                Ghost.ghostList.get(i).setDirY(0);
                break;
            }

            default:{

                break;
            }
        }
    }


    public void run(){
        while (!isInterrupted()){

            try {
                SwingUtilities.invokeLater(() -> {
                    synchronized (Locks.ghostSpeedLock){
                        moveGhost();
                    }
                });
                this.gameBoard.getComponent(0).repaint();
                if(!GameData.inGame){
                    this.interrupt();
                    return;
                }
                Thread.sleep(ghostCycle);

            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}

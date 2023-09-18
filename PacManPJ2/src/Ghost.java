import java.awt.*;
import java.util.ArrayList;

public class Ghost {
    public static ArrayList<Ghost> ghostList;
    private   int animationCycle;
    private int waitCycleAnim;
    private Image[] imgAnimSprite;
    private int posX;
    private int posY;
    private int speed;
    private boolean eaten;

    private int dirX;
    private int dirY;
    private int defaultSpeed =1;
    public Ghost(){
        setAnimationCycle(0);
        setWaitCycleAnim(0);
        setRandomAnim();
        setRandomPos();
        setSpeed(defaultSpeed);
        addGhost();
        eaten = false;

    }
    public static void clearGhostList(){
       if(ghostList != null){
           ghostList.clear();
       }
    }

    public static Ghost getRandomGhost(){
        if(ghostList!=null || !ghostList.isEmpty()){
            int rand = (int) ((Math.random() * (ghostList.size()-0 )) + 0);
            return ghostList.get(rand);
        } else{
            return null;
        }
    }

    public static boolean isGhostAt(int row, int col){
        for(int i =0; i < Ghost.ghostList.size(); i++){
            if(Ghost.ghostList.get(i).getPosX()/GameData.CURRENT_CELL_SIZE == col &&
            Ghost.ghostList.get(i).getPosY()/GameData.CURRENT_CELL_SIZE == row){
                return true;
            }
        }
        return false;
    }

    public void setRandomPos(){
        int rand = (int) ((Math.random() * (5-1 )) + 1);
        switch (rand){
            case 1:{
                setPosX(0);
                setPosY(0);
                return;
            }
            case 2:{
                setPosX((int) GameData.inputCol * GameData.CURRENT_CELL_SIZE - GameData.CURRENT_CELL_SIZE);
                setPosY(0);
                return;
            }
            case 3:{
                setPosX(0);
                setPosY((int) GameData.inputRow * GameData.CURRENT_CELL_SIZE - GameData.CURRENT_CELL_SIZE);
                return;
            }
            case 4:{
                setPosX((int) GameData.inputCol * GameData.CURRENT_CELL_SIZE - GameData.CURRENT_CELL_SIZE);
                setPosY((int) GameData.inputRow * GameData.CURRENT_CELL_SIZE - GameData.CURRENT_CELL_SIZE);
                return;
            }

            default:{
                return;
            }
        }
    }

    public static void setRandomPosAll(){
        synchronized (Locks.ghostPosLock){
            for(int i =0; i < ghostList.size(); i++){
                ghostList.get(i).setRandomPos();
            }
        }
    }

    public void setRandomAnim(){
        int rand = (int) ((Math.random() * (6-1 )) + 1);
        switch (rand){
            case 1:{
                this.imgAnimSprite = DefaultSpriteData.ghost1;
                return;
            }
            case 2:{
                this.imgAnimSprite = DefaultSpriteData.ghost2;
                return;
            }
            case 3:{
                this.imgAnimSprite = DefaultSpriteData.ghost3;
                return;
            }
            case 4:{
                this.imgAnimSprite = DefaultSpriteData.ghost4;
                return;
            }
            case 5:{
                this.imgAnimSprite = DefaultSpriteData.ghost5;
                return;
            }
            default:{
                return;
            }
        }
    }

    public void addGhost(){
        if(ghostList==null) {
            ghostList = new ArrayList<Ghost>();
        }
        ghostList.add(this);
    }

    public  void updateCycle(){
        if(animationCycle >= 7){
            animationCycle = 0;
        } else {
            animationCycle++;
        }
    }
    public void updateWaitCycleAnim(){
        this.waitCycleAnim++;
    }
    public int getWaitCycleAnim() {
        return waitCycleAnim;
    }

    public void setWaitCycleAnim(int waitCycleAnim) {
        this.waitCycleAnim = waitCycleAnim;
    }

    public int getAnimationCycle() {
        return animationCycle;
    }

    public void setAnimationCycle(int animationCycle) {
        this.animationCycle = animationCycle;
    }

    public Image[] getImgAnimSprite() {
        return imgAnimSprite;
    }

    public void setImgAnimSprite(Image[] imgAnimSprite) {
        this.imgAnimSprite = imgAnimSprite;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirX() {
        return dirX;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }

    public int getDefaultSpeed() {
        return defaultSpeed;
    }

    public void setDefaultSpeed(int defaultSpeed) {
        this.defaultSpeed = defaultSpeed;
    }
}

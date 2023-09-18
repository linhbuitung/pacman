public class PacMan {

    public  static  int animationCycle;
    public  static int posX;
    public static int posY;
    public static int speed;
    public static int lives;
    public static boolean collidable;
    public static boolean eatGhost;
    public static int dirX;
    public static int dirY;
    public static int defaultSpeed =1;

    public static void InitPacman(){
        posX = (int) GameData.inputCol /2 * GameData.CURRENT_CELL_SIZE;
        posY =(int) GameData.inputRow /2* GameData.CURRENT_CELL_SIZE;
        lives = 3;
        animationCycle = 0;
        speed =defaultSpeed;
        collidable= true;
        eatGhost = false;
//        initPos();
//        initSpeed();

    }

    private void initPos(){
        setPosX(0);
        setPosY(0);
    }

    private void initSpeed(){
        setSpeed(1);
    }

    public static void updateCycle(){
        if(animationCycle >= 7){
            animationCycle = 0;
        } else {
            animationCycle++;
        }
    }

    public static void resetPosPacMan(){
        synchronized (Locks.pacmanPosLock){
            posX = (int) GameData.inputCol /2 * GameData.CURRENT_CELL_SIZE;
            posY =(int) GameData.inputRow /2* GameData.CURRENT_CELL_SIZE;
        }
    }

    public static void updateAnimPacman() {


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
}

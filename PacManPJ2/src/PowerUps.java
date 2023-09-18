
import java.awt.*;
import java.util.ArrayList;

public abstract class PowerUps {
    public static ArrayList<PowerUps> powerUps;
    private String name;
    private String description;
    private Image powerUpImg;
    private int posX;
    private int posY;
    public abstract  void runPowerUps();
    public abstract void setImg();

    public void addPowerUp(){
        if(powerUps == null){
            powerUps = new ArrayList<PowerUps>();

        }
        powerUps.add(this);
    };

    public static boolean isPowerUpsAt(int row, int col){
        if(PowerUps.powerUps == null){
            return false;
        }
        for(int i =0; i < PowerUps.powerUps.size(); i++){
            if(PowerUps.powerUps.get(i).getPosX()/GameData.CURRENT_CELL_SIZE == col &&
                    PowerUps.powerUps.get(i).getPosY()/GameData.CURRENT_CELL_SIZE == row){
                return true;
            }
        }
        return false;
    }

    public static void resetPowerUps(){
        if(powerUps != null){
            powerUps.clear();
        }
    }
    public static int getPowerUp(int i){
        int temp;
        if(powerUps.get(i).getClass().equals(Apple.class) ){
            temp = 0;
        } else if(powerUps.get(i).getClass().equals(Banana.class) ){
            temp = 1;
        }
        else if(powerUps.get(i).getClass().equals(Grapes.class) ){
            temp = 2;
        }
        else if(powerUps.get(i).getClass().equals(Lemon.class) ){
            temp = 3;
        }
        else {
            temp = 4;
        }
        powerUps.remove(i);
        return temp;

    }
    public static void removePowerUp(int i){
        powerUps.remove(i);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getPowerUpImg() {
        return powerUpImg;
    }

    public void setPowerUpImg(Image powerUpImg) {
        this.powerUpImg = powerUpImg;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}



public class Banana extends PowerUps{
    public Banana(int posX,int posY){
        setName("Banana");
        setDescription("Pacman Invincible");
        setImg();
        setPosX(posX);
        setPosY(posY);
        addPowerUp();
    }
    @Override
    public void runPowerUps() {

    }
    @Override
    public void setImg() {
        setPowerUpImg(DefaultSpriteData.powerUpsImg[4]);
    }
}

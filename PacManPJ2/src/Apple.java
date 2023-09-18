

public class Apple extends PowerUps{

    public Apple(int posX, int posY){
        setName("Apple");
        setDescription("Double Pacman Speed");
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
        setPowerUpImg(DefaultSpriteData.powerUpsImg[0]);
    }
}

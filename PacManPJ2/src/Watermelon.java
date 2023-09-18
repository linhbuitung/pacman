

public class Watermelon extends PowerUps{
    public Watermelon(int posX,int posY){
        setName("Watermelon");
        setDescription("Decrease Ghost Speed");
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
        setPowerUpImg(DefaultSpriteData.powerUpsImg[1]);
    }
}



public class Lemon extends PowerUps{
    public  Lemon(int posX, int posY){
        setName("Lemon");
        setDescription("Double Points");
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
        setPowerUpImg(DefaultSpriteData.powerUpsImg[2]);
    }
}

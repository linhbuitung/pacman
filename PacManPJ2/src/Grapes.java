
public class Grapes extends PowerUps{
    public Grapes(int posX,int posY){
        setName("Grapes");
        setDescription("Eat Ghosts");
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
        setPowerUpImg(DefaultSpriteData.powerUpsImg[3]);
    }
}

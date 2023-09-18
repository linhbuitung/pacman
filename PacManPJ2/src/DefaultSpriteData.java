import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class DefaultSpriteData {

    //8 anim every character
    public static Image[] pacmanRight;
    public static Image[] pacmanLeft;
    public static Image[] pacmanUp;
    public static Image[] pacmanDown;
    public static Image[] currPacManAnim;

    public static Image[] powerUpsImg;

    public static Image[] ghost1;
    public static Image[] ghost2;
    public static Image[] ghost3;
    public static Image[] ghost4;
    public static Image[] ghost5;
    public static String mainGhostLink = "./src/imgData/ghosts/ghost-";
    public static String mainPacManLink = "./src/imgData/pacman/";
    public static String mainPowerUpLink = "./src/imgData/powerups/tile";

    public static Image coinIcon;
    public static Image heartIcon;
    public static void initArrays(){
        pacmanRight = new Image[8];
        pacmanLeft = new Image[8];
        pacmanUp = new Image[8];
        pacmanDown = new Image[8];
        ghost1= new Image[8];
        ghost2= new Image[8];
        ghost3= new Image[8];
        ghost4= new Image[8];
        ghost5= new Image[8];
        powerUpsImg = new Image[5];


    }

    public static Image getImg (String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    public static void initSprites(){

        //get ghost sprite
        for(int i = 0; i < 8; i ++){
            String link = mainGhostLink + "1/tile00" + i +".png";
            if(getImg(link) != null){
                Image tempImg = getImg(link);
                ghost1[i] = tempImg;
            }
        }
        for(int i = 0; i < 8; i ++){
            String link = mainGhostLink + "2/tile00" + i +".png";
            if(getImg(link) != null){
                Image tempImg = getImg(link);
                ghost2[i] = tempImg;
            }

        }
        for(int i = 0; i < 8; i ++){
            String link = mainGhostLink + "3/tile00" + i +".png";
            if(getImg(link) != null){
                Image tempImg = getImg(link);
                ghost3[i] = tempImg;
            }

        }
        for(int i = 0; i < 8; i ++){
            String link = mainGhostLink + "4/tile00" + i +".png";
            if(getImg(link) != null){
                Image tempImg = getImg(link);
                ghost4[i] = tempImg;
            }

        }
        for(int i = 0; i < 8; i ++){
            String link = mainGhostLink + "5/tile00" + i +".png";
            if(getImg(link) != null){
                Image tempImg = getImg(link);
                ghost5[i] = tempImg;
            }

        }

        //get PacmanRight
        for(int i = 0; i < 8; i ++){
            String link = mainPacManLink  + "/right/tile00" + i +".png";
            if(getImg(link) != null){
                Image tempImg = getImg(link);
                pacmanRight[i] = tempImg;
            }

        }
        for(int i = 0; i < 8; i ++){
            String link = mainPacManLink + "/left/tile00" + i +".png";
            if(getImg(link) != null){
                Image tempImg = getImg(link);
                pacmanLeft[i] = tempImg;
            }

        }
        for(int i = 0; i < 8; i ++){
            String link = mainPacManLink + "/up/tile00" + i +".png";
            if(getImg(link) != null){
                Image tempImg = getImg(link);
                pacmanUp[i] = tempImg;
            }

        }
        for(int i = 0; i < 8; i ++){
            String link = mainPacManLink + "/down/tile00" + i +".png";
            if(getImg(link) != null){
                Image tempImg = getImg(link);
                pacmanDown[i] = tempImg;
            }
        }

        for(int i = 0; i < 5; i ++){
            String link = mainPowerUpLink  + (i+1) +".png";
            if(getImg(link) != null){
                Image tempImg = getImg(link);
                powerUpsImg[i] = tempImg;
            }

        }

        currPacManAnim = pacmanRight;
        coinIcon = getImg("./src/imgData/coin/tile000.png");
        heartIcon = getImg("./src/imgData/heart/heart.png");
    }

    public static void main(String[] args) {
//        DefaultSpriteData.initArrays();
//        DefaultSpriteData.initSprites();
//
//        JFrame frame = new JFrame();
//
//        JLabel label = new JLabel(); //JLabel Creation
//        label.setIcon(new ImageIcon(pacman[0]));
//        frame.add(label);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);

    }
}

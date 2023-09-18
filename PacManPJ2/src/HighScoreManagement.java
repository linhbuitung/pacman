import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class HighScoreManagement {
    public static void writeObjects(List<HighScore> list) {
        try (FileOutputStream fileOut = new FileOutputStream(HighScore.highScoreTxt);
             ObjectOutputStream scoreOut = new ObjectOutputStream(fileOut)){
            scoreOut.writeObject(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<HighScore> readObjects() {
        List<HighScore> highScoreList = new ArrayList<>();
        File file = new File(HighScore.highScoreTxt);
        try (FileInputStream fileIn = new FileInputStream(HighScore.highScoreTxt);
             ObjectInputStream getScoreInput = new ObjectInputStream(fileIn)) {

            if (file.length() == 0) {
                return null;
            }

            highScoreList = (List<HighScore>) getScoreInput.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return highScoreList;
}


}

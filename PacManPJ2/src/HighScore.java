import java.io.Serializable;

public class HighScore implements Serializable {
    public static String highScoreTxt = "./src/scoreData/highscores.txt";
    private String name;
    private int score;

    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "HighScore{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}

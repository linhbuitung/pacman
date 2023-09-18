import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameData {

    public  static Color backgroundColor = new Color( 0, 21, 84);;
    public  static Color lightBackgroundColor = new Color(0, 168, 158);
    public  static boolean inGame;
    public static boolean die;

    public  static  int ghostNum = 5;

    public static int point;
    public static  int defaultPointGain = 100;
    public static  int pointGain;
    public static  int level;

    public static  int DEFAULT_CELL_SIZE = 30;
    public static  int CURRENT_CELL_SIZE;
    public static int inputRow = 10;
    public static int inputCol = 10;
    public static Object[][] GAME_GRID;
    public static String gridFileLink = "./src/gridData/gridTemplate.txt";

    public static int getTableSizeRow(){
      return GAME_GRID.length * CURRENT_CELL_SIZE;
    };
    public static int getTableSizeCol(){
        return GAME_GRID[0].length * CURRENT_CELL_SIZE;
    };


    public static void initIngameData(){
        setCellSize();
        MazeGenerator mazeGenerator = new MazeGenerator();
        mazeGenerator.createMazeUsingRecursion();
        point = 0;
        level = 1;
        pointGain = defaultPointGain;
        inGame = true;
        die = false;
        //CURRENT_CELL_SIZE = DEFAULT_CELL_SIZE;
        getDefaultGrid();
    }

    public static  void setCellSize(){
        CURRENT_CELL_SIZE = DEFAULT_CELL_SIZE;
    }


    public static void resetDataForNextLLevel(){
        MazeGenerator mazeGenerator = new MazeGenerator();
        mazeGenerator.createMazeUsingRecursion();
        getDefaultGrid();
        GameTableModel.setNewTable(GAME_GRID);
            level++;

    }
    public static void getDefaultGrid(){
        List<String> lines = new ArrayList<>();

        // Read file into list of lines
        try (BufferedReader br = new BufferedReader(new FileReader(gridFileLink))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numRows = lines.size();
        int numCols = lines.get(0).length();

        // Initialize 2D array
        Object[][] arr = new Object[numRows][numCols];

        // Fill 2D array with file contents
        for (int i = 0; i < numRows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < numCols; j++) {
                arr[i][j] = (int) (line.charAt(j) - '0');
            }
        }
        GAME_GRID = arr;

    }

    public static int getRowNum(){
        return GAME_GRID.length;
    }

    public static int getColNum(){
        return GAME_GRID[0].length;
    }
}

import jdk.jshell.spi.SPIResolutionException;

import java.io.*;
import java.util.*;

public class MazeGenerator {
    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator();
        mazeGenerator.createMazeUsingRecursion();
    }

    public static int[][]maze;
    public static int[][] tempMaze;
    public void createMazeUsingRecursion(){
        maze = new int[GameData.inputRow][GameData.inputCol];

        generateFullMaze();

        //cleanMaze();

        clearOutsideRowCol();
        try {
            writeMaze();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void printMaze(int[][] maze){
        for(int i =0; i < maze.length; i++){
            String line = "";
            for(int j =0; j < maze[i].length; j++){
                line += maze[i][j];
            }
            System.out.println(line);
        }
    }

    public void replaceElement(int x, int y){
        for(int i =0; i < maze.length; i++){

            for(int j =0; j < maze[i].length; j++){
                if(maze[i][j] == x){
                    maze[i][j] = y;
                }
            }

        }
    }

    public void clearOutsideRowCol(){
        for(int i =0; i < maze.length; i++){
            maze[i][0] =1;
            maze[i][maze[0].length-1] = 1;

        }
        for(int j =0; j < maze[0].length; j++){
            maze[0][j] =1;
            maze[maze.length-1][j] = 1;
        }
        if(maze.length%2 == 0) {
            for(int i =0; i < maze[0].length; i++){
                maze[maze.length/2][i] =1;
                maze[(maze.length/2)-1][i] = 1;

            }
        } else{
            for(int i =0; i < maze[0].length; i++){
                maze[maze.length/2][i] =1;

            }
        }
        if(maze[0].length%2 == 0) {
            for(int i =0; i < maze.length; i++){
                maze[i][maze[0].length/2] =1;
                maze[i][(maze[0].length/2)-1] = 1;

            }
        }
        else{
            for(int i =0; i < maze.length; i++){
                maze[i][maze[0].length/2] =1;
            }
        }
    }

    public boolean checkPercentage(){
        int numWall = 0;
        for(int i =0; i < maze.length; i++){

            for(int j =0; j < maze[i].length; j++){
                if(maze[i][j] == 0){
                    numWall++;
                }
            }

        }
        if((int)(numWall/(maze.length*maze[0].length))*100>40){
            return false;
        }
        return true;
    }

    public static void writeMaze() throws IOException {
        File fout = new File(GameData.gridFileLink);
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < maze.length; i++) {
            String line = "";
            for(int j =0; j < maze[i].length; j++){
                line += maze[i][j];
            }
            bw.write(line);
            bw.newLine();
        }

        bw.close();
    }

    public void cleanMaze(){
        int x = 0;
        int y = 0;
        Queue<Integer> cleanX = new LinkedList<>();
        Queue<Integer> cleanY= new LinkedList<>();
        cleanX.add(x);
        cleanY.add(y);
        while (!cleanX.isEmpty() && !cleanY.isEmpty()){
            x=cleanX.poll();
            y=cleanY.poll();
            if(getCellVal(x+1,y) == 1){
                setCellVal(x+1,y,-5);
                cleanX.add(x+1);
                cleanY.add(y);
            }
            if(getCellVal(x-1,y) == 1){
                setCellVal(x-1,y,-5);
                cleanX.add(x-1);
                cleanY.add(y);
            }
            if(getCellVal(x,y+1) == 1){
                setCellVal(x,y+1,-5);
                cleanX.add(x);
                cleanY.add(y+1);
            }
            if(getCellVal(x,y-1) == 1){
                setCellVal(x,y-1,-5);
                cleanX.add(x);
                cleanY.add(y-1);
            }
        }
        for (int i = 0; i < maze.length; i++) {
            for(int j =0; j < maze[i].length; j++){
                if(maze[i][j]==1){
                    maze[i][j] = 0;
                }
                else if(maze[i][j]==-5){
                    maze[i][j]=1;
                }
            }
        }
    }



    //RECURSION STACK OVERFLOW WHEN > 80 SIZE
//    public void randomMazeCreation(int x, int y){
//        Queue<Integer> freeNeighborX = new LinkedList<>();
//        Queue<Integer> freeNeighborY = new LinkedList<>();
//        freeNeighborX.add(x);
//        freeNeighborY.add(y);
//        while (!freeNeighborX.isEmpty() && !freeNeighborY.isEmpty()) {
//            x = freeNeighborX.poll();
//            y = freeNeighborY.poll();
//            int valIndex;
//            if(getCellVal(x+1,y) == 3){
//                 valIndex = (int) ((Math.random() * (4-0 )) + 0);
//                if(valIndex!= 0){
//                    setCellVal(x+1,y,1);
//                    freeNeighborX.add(x+1);
//                    freeNeighborY.add(y);
//                } else{
//                    setCellVal(x+1,y,0);
//                }
//
//
//            }
//            if(getCellVal(x-1,y) == 3){
//                 valIndex = (int) ((Math.random() * (4-0 )) + 0);
//                if(valIndex!= 0){
//                    setCellVal(x-1,y,1);
//                    freeNeighborX.add(x-1);
//                    freeNeighborY.add(y);
//                } else{
//                    setCellVal(x-1,y,0);
//                }
//
//            }
//            if(getCellVal(x,y+1) == 3){
//                 valIndex = (int) ((Math.random() * (4-0 )) + 0);
//                if(valIndex!= 0){
//                    setCellVal(x,y+1,1);
//                    freeNeighborX.add(x);
//                    freeNeighborY.add(y+1);
//                } else{
//                    setCellVal(x,y+1,0);
//                }
//            }
//            if(getCellVal(x,y-1) == 3){
//                 valIndex = (int) ((Math.random() * (4-0 )) + 0);
//                if(valIndex!= 0){
//                    setCellVal(x,y-1,1);
//                    freeNeighborX.add(x);
//                    freeNeighborY.add(y-1);
//                } else{
//                    setCellVal(x,y-1,0);
//                }
//            }
//        }
//
//
//    }
//
    public void generateFullMaze(){
        generateOneFourthMaze();
        int[][] generatedTopLeftMaze = tempMaze;
        for(int i = 0; i < generatedTopLeftMaze.length; i++){
            for(int j = 0; j < generatedTopLeftMaze[0].length; j++ ){
                maze[i][j] = generatedTopLeftMaze[i][j];
            }
        }
        for(int i = 0; i < generatedTopLeftMaze.length; i++){
            for(int j = 0; j < generatedTopLeftMaze[0].length; j++ ){
                maze[maze.length-1-i][maze[0].length-1-j] = generatedTopLeftMaze[i][j];
            }
        }

        for(int i = 0; i < generatedTopLeftMaze.length; i++){
            for(int j = 0; j < generatedTopLeftMaze[0].length; j++ ){
                maze[maze.length-1-i][j] = generatedTopLeftMaze[i][j];
            }
        }
        for(int i = 0; i < generatedTopLeftMaze.length; i++){
            for(int j = 0; j < generatedTopLeftMaze[0].length; j++ ){
                maze[i][maze[0].length-1-j] = generatedTopLeftMaze[i][j];
            }
        }
    }
    public void generateOneFourthMaze() {
        int width = (GameData.inputRow/2);
        int height = (GameData.inputCol/2);
        tempMaze = new int[width][height];
        // Initialize maze with wall cells
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tempMaze[i][j] = 0;
            }
        }

        Random random = new Random();
        int startX = width/2;
        int startY = height/2;

        // Generate maze recursively
        generateMazeRecursive(startX, startY,width,height);

    }

    private void generateMazeRecursive(int x, int y,int width, int height) {

        tempMaze[x][y] =1;
        List<int[]> neighbors = getUnvisitedNeighbors(x, y,width,height);
        Collections.shuffle(neighbors);

        for (int[] neighbor : neighbors) {
            int nx = neighbor[0];
            int ny = neighbor[1];



            if (tempMaze[nx][ny] == 0) {
                // Remove wall cell between current cell and neighbor
                int dx = x + (nx - x) / 2;
                int dy = y + (ny - y) / 2;
                tempMaze[dx][dy] = 1;

                // Recursively visit the neighbor
                generateMazeRecursive(nx, ny, width,  height);
            } else{
                int rand =(int) (Math.random()*(6-1)+1);
                int dx = x + (nx - x) / 2;
                int dy = y + (ny - y) / 2;
                if(rand == 1 && !(tempMaze[dx][dy] ==1)){

                    tempMaze[dx][dy] = 1;
                }
            }
        }

    }

    private List<int[]> getUnvisitedNeighbors(int x, int y, int width, int height) {
        List<int[]> neighbors = new ArrayList<>();

        // Check top neighbor
        if (y - 2 >= 0 && tempMaze[x][y - 2] == 0) {
            neighbors.add(new int[]{x, y - 2});
        }
        // Check right neighbor
        if (x + 2 < width && tempMaze[x + 2][y] == 0) {
            neighbors.add(new int[]{x + 2, y});
        }
        // Check bottom neighbor
        if (y + 2 < height && tempMaze[x][y + 2] == 0) {
            neighbors.add(new int[]{x, y + 2});
        }
        // Check left neighbor
        if (x - 2 >= 0 && tempMaze[x - 2][y] == 0 ) {
            neighbors.add(new int[]{x - 2, y});
        }

        return neighbors;
    }


    public int getCellVal(int i, int j){
        if(i < 0 || i >= maze.length || j < 0 || j >= maze[0].length){
            return Integer.MIN_VALUE;
        } else{
            return maze[i][j];
        }
    }
    public void setCellVal(int i, int j, int val){
        maze[i][j] = val;
    }


    public int[][] fillMaze(int x, int[][]maze){
        for (int i = 0; i < maze.length; i++){
            for(int j =0; j  < maze[i].length; j++){
                maze[i][j] = x;
            }
        }
        return maze;
    }

}
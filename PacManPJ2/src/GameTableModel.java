import javax.swing.table.AbstractTableModel;

public class GameTableModel extends AbstractTableModel {

    //size = width = height
    private static Object[][] data;

    public GameTableModel(Object[][] data) {
        this.data = data;
//        for(int i =0; i < 10; i++){
//            String a = "";
//            for(int j =0; j < 10; j++){
//                a += (int) data[i][j];
//            }
//            System.out.println(a);
//        }
        // initialize gridData with default values (0 for empty space, 1 for Pac-Man, etc.)
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        if (data.length == 0) {
            return 0;
        }
        return data[0].length;
    }
    // implement the getValueAt(int row, int col) method
    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public static void setNewTable(Object[][] newData){
        data = newData;
    }


    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void setValueAt(int valueToChange, int row, int col) {
        data[row][col] = valueToChange;
        //Notifies all listeners that the value of the cell at [row, column] has been updated.
        fireTableCellUpdated(row, col);
    }

    public void setValueEveryCell( int[][] valueTable) {
        for(int i =0; i < data.length; i++){
            for(int j = 0; j < data[0].length; j++){
                data[i][j] = valueTable[i][j];
                fireTableCellUpdated(i, j);
            }
        }

    }

    public static int getDataAt(int x, int y){
            if(x >= 0 && x < data[0].length && y >= 0 && y < data.length){

                return (int) data[y][x];
            }else {
                return Integer.MIN_VALUE;
            }
    }

    public static boolean isWall(int i, int posX, int posY){

        switch (i){
            case 1:{

                if(getDataAt(posX,posY+1) == 0 || getDataAt(posX,posY+1) == Integer.MIN_VALUE){

                    return true;
                }
                return  false;
            }
            case 2:{
                if(getDataAt(posX,posY-1) == 0 || getDataAt(posX,posY-1) == Integer.MIN_VALUE){

                    return true;
                }
                return  false;
            }
            case 3:{
                if(getDataAt(posX+1,posY) == 0 || getDataAt(posX+1,posY) == Integer.MIN_VALUE){

                    return true;
                }
                return  false;
            }
            case 4:{
                if(getDataAt(posX-1,posY) == 0 || getDataAt(posX-1,posY) == Integer.MIN_VALUE){


                    return true;
                }
                return  false;
            }
            default:{
                return false;
            }
        }
    }
    public static void setValue(int valueToChange, int row, int col){
        data[row][col] = valueToChange;
    }

    public static boolean levelFinish(){
        boolean finish = true;
        for(int i =0; i < data.length; i++){
            for(int j = 0; j < data[0].length; j++){
                if((int)data[i][j] == 1){
                    finish = false;
                }

            }
        }
        return finish;
    }
}

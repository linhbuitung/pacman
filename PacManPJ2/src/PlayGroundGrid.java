import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PlayGroundGrid extends JPanel {
    public static JTable table;
    public static GameTableModel model;
    public static int currCellSize;
    public PlayGroundGrid(){

        model = new GameTableModel(GameData.GAME_GRID);

        // Create the table
        table = new JTable(model);
        table.setShowGrid(false);
        // Set the cell render to display centered text
        GameBoardCellRenderer renderer = new GameBoardCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, renderer);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Set the cell dimensions

        updateSize(-1);



        this.setLayout(new BorderLayout());

        this.setBackground(GameData.lightBackgroundColor);
        this.add(table);

    }

    public void updateSize(int size){
        if(size == -1){
            currCellSize =10;
        } else {
            currCellSize = size;
        }

//        this.setMinimumSize(table.getSize());
        table.setRowHeight(currCellSize);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(currCellSize);
            table.getColumnModel().getColumn(i).setWidth(currCellSize);
            table.getColumnModel().getColumn(i).setMinWidth(0);

        }
//        table.setPreferredScrollableViewportSize(
//                new Dimension(GameData.getTableSizeCol(), GameData.getTableSizeRow()));

        // Set the preferred size of the table and scroll pane


        //table.setFillsViewportHeight(true);
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);


        table.setPreferredSize(new Dimension(currCellSize*GameData.getColNum() , currCellSize*GameData.getRowNum() ));
        table.setSize(new Dimension(currCellSize*GameData.getColNum() , currCellSize*GameData.getRowNum() ));
        table.setMinimumSize(this.getPreferredSize());
        this.setSize(table.getSize());


    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
    @Override
    public int getHeight() {
        return (int)getPreferredSize().getHeight();
    }
    @Override
    public int getWidth() {
        return (int)getPreferredSize().getWidth();
    }




}

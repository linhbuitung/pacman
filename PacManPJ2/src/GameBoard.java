import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

public class GameBoard extends JFrame {

    private Dimension dimension;

    public GameBoard(){



        GameData.initIngameData();
        this.addKeyListener(new GameInput());
        PacMan.InitPacman();


        PlayGroundGrid playGroundGrid = new PlayGroundGrid();

        //this.setMaximumSize();
//        this.rootPane.setSize(this.getSize());
//        this.rootPane.setPreferredSize(this.getPreferredSize());



        PowerUps.resetPowerUps();
        PacManMovement pacManMovement = new PacManMovement( this);
        pacManMovement.start();
        Ghost.clearGhostList();
        for(int i =0; i < GameData.ghostNum; i++){
            Ghost ghost = new Ghost();
        }
        GhostMovement ghostMovement = new GhostMovement(this);
        ghostMovement.start();
        PowerUpsManager powerUpsManager = new PowerUpsManager();
        powerUpsManager.start();

        LiveDataPanel liveDataPanel = new LiveDataPanel();
        PowerUpJList powerUpJList = new PowerUpJList();


        dimension = this.getSize();



        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.7;
        gbc.weighty = 0.7;
        //gbc.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(playGroundGrid, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0.7;
        gbc.fill = GridBagConstraints.BOTH;
        //gbc.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(powerUpJList, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;
        gbc.fill = GridBagConstraints.BOTH;
        //gbc.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(liveDataPanel, gbc);



        this.setPreferredSize(new Dimension(playGroundGrid.getWidth() + powerUpJList.getWidth()+10,
                playGroundGrid.getHeight()+liveDataPanel.getHeight()+40));
//        this.setSize(new Dimension(GameData.getTableSizeCol() + powerUpJList.getWidth(),
//                GameData.getTableSizeRow()+liveDataPanel.getHeight()+38));

        //setResizable(false);


        this.pack();

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(0);
        this.setVisible(true);
        this.setFocusable(true);
        this.setMinimumSize(new Dimension(700,500));

       // setResizeElements(this);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                Dimension newSize = e.getComponent().getSize();
                int newWidth = newSize.width;
                int newHeight = newSize.height;



                int width = newSize.width*80/100;
                int height = newSize.height*80/100;
                int cellSize = Math.min(width / GameData.inputCol, height / GameData.inputRow);
                playGroundGrid.updateSize(cellSize);

                int panel2Width = newWidth - playGroundGrid.getWidth();
                int panel2Height = newHeight*9/10;
                powerUpJList.setSize(panel2Width, panel2Height);

                int panel3Width = newWidth;
                int panel3Height = newHeight / 10; // 1/10th of the new height
                //LiveDataPanel.minHeight = newHeight/10;
                liveDataPanel.setSize(panel3Width, panel3Height);
            }
        });
    }

    public void setResizeElements(JFrame frame){
        if(frame != null){
//            frame.addComponentListener(new ComponentAdapter() {
//                public void componentResized(ComponentEvent e) {
//
//                    int width =  frame.getWidth()*100/80;
//                    int height = frame.getHeight()*100/80;
//                    int cellSize = Math.min(width / GameData.inputCol, height / GameData.inputRow);
//                    table.setRowHeight(cellSize);
//                    for (int i = 0; i < table.getColumnCount(); i++) {
//                        table.getColumnModel().getColumn(i).setPreferredWidth(cellSize);
//                    }
//                    int x = pacMan.xPos * cellSize;
//                    int y = pacMan.yPos * cellSize;
//                    int pacSize = cellSize - 4;
//                    pacMan.setBounds(x + 2, y + 2, pacSize, pacSize);
//
//                }
//            })
        }
        ;

        // Add a WindowStateListener to catch the windowStateChanged event
//        this.addWindowStateListener(new WindowStateListener() {
//            public void windowStateChanged(WindowEvent e) {
//                if (e.getNewState() == JFrame.NORMAL) {
//                    System.out.println("Resizing finished");
//                }
//            }
//        });
    }


    public static void main(String[] args) {




    }
}

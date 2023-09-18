import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;


class GameBoardCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        JLabel cell = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        //Hide number value
        cell.setText("");
        ImageIcon icon = new ImageIcon(DefaultSpriteData.coinIcon);
        Image scaledImg = icon.getImage().getScaledInstance(PlayGroundGrid.currCellSize /2, PlayGroundGrid.currCellSize /2, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        //Change each cell
        synchronized (Locks.pacmanPosLock){
            synchronized (Locks.ghostPosLock) {
                synchronized (Locks.powerUpListLock) {
                    if ((int) PacMan.posX / GameData.CURRENT_CELL_SIZE == col && (int) PacMan.posY / GameData.CURRENT_CELL_SIZE == row) {
                        return new PacManLabel();
                    } else if (Ghost.isGhostAt(row, col)) {
                        for (int i = 0; i < Ghost.ghostList.size(); i++) {
                            if ((int) Ghost.ghostList.get(i).getPosX() / GameData.CURRENT_CELL_SIZE == col && (int) Ghost.ghostList.get(i).getPosY() / GameData.CURRENT_CELL_SIZE == row) {
                                return new GhostLabel(i);
                            }
                        }
                    } else if(PowerUps.isPowerUpsAt(row, col)){
                        for (int i = 0; i < PowerUps.powerUps.size(); i++) {
                            if ((int) PowerUps.powerUps.get(i).getPosX() / GameData.CURRENT_CELL_SIZE == col && (int) PowerUps.powerUps.get(i).getPosY() / GameData.CURRENT_CELL_SIZE == row) {
                                return new PowerUpLabel(i);
                            }
                        }
                    }
                    else if ((int) table.getValueAt(row, col) == 0) {
                        cell.setBackground(GameData.backgroundColor);
                        cell.setIcon(null);
                    } else if ((int) table.getValueAt(row, col) == 2) {
                        cell.setBackground(Color.white);
                        cell.setIcon(null);
                    } else if ((int) table.getValueAt(row, col) == 1) {
                        cell.setBackground(Color.white);
                        cell.setIcon(scaledIcon);
                    }
                }
            }
        }



        return cell;
    }
}


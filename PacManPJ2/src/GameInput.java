import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GameInput extends KeyAdapter {
    public  static int inputX = 0;
    public static ArrayList<Integer> pressList;
    public  static  int inputY = 0;
    private boolean ctrl = false;
    private boolean shift = false;
    private boolean q = false;
    public GameInput(){
        pressList = new ArrayList<Integer>();
    }
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            ctrl = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shift = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            q = true;
        }
        if (ctrl && shift && q) {
            GameData.inGame = false;

        }

        int key = e.getKeyCode();

        if (GameData.inGame) {
            if (key == KeyEvent.VK_A) {
                if(!pressList.contains(key)){
                    pressList.add(0, key);
                }
            } else if (key == KeyEvent.VK_D) {
                if(!pressList.contains(key)){
                    pressList.add(0, key);
                }
            } else if (key == KeyEvent.VK_W) {
                if(!pressList.contains(key)){
                    pressList.add(0, key);
                }
            } else if (key == KeyEvent.VK_S) {
                if(!pressList.contains(key)){
                    pressList.add(0, key);
                }
            } else if (key == KeyEvent.VK_ESCAPE ) {
//                inGame = false;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {

            pressList.removeAll(List.of(Integer.valueOf(key)));

        } else if (key == KeyEvent.VK_D) {

            pressList.removeAll(List.of(Integer.valueOf(key)));
        } else if (key == KeyEvent.VK_W) {
            pressList.removeAll(List.of(Integer.valueOf(key)));
        } else if (key == KeyEvent.VK_S) {
            pressList.removeAll(List.of(Integer.valueOf(key)));
        }

        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            ctrl = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shift = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            q = false;
        }
    }

    public static int getFirstKeyX(){
        if(pressList.isEmpty()){
            return 0;
        } else if (pressList.get(0) == KeyEvent.VK_A) {
            return -1;

        } else if (pressList.get(0) == KeyEvent.VK_D) {
            return 1;
        } else if (pressList.get(0) == KeyEvent.VK_W) {
            return 0;
        } else if (pressList.get(0) == KeyEvent.VK_S) {
            return 0;
        }
        return 0;
    }
    public static int getFirstKeyY(){
        if(pressList.isEmpty()){
            return 0;
        } else if (pressList.get(0) == KeyEvent.VK_A) {
            return 0;

        } else if (pressList.get(0) == KeyEvent.VK_D) {
            return 0;
        } else if (pressList.get(0) == KeyEvent.VK_W) {
            return -1;
        } else if (pressList.get(0) == KeyEvent.VK_S) {
            return 1;
        }
        return 0;
    }
}

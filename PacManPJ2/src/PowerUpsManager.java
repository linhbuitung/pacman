public class PowerUpsManager extends Thread{


    @Override
    public void run() {
        int counter = 2;
        while (!isInterrupted()){
            try {
                if(!GameData.inGame){
                    this.interrupt();
                    return;
                }
                synchronized (Locks.powerUpListLock){
                    if(PowerUps.powerUps != null && !PowerUps.powerUps.isEmpty()){
                        if (counter == 4 && PowerUps.powerUps.size() == 3){
                            PowerUps.powerUps.remove(0);
                        }
                    }
                }
                if(counter == 5){
                    counter = 0;
                    int upgradeChance = (int) (Math.random() * (4-1)+1);
                    if(upgradeChance == 1){
                        int rand = (int) ((Math.random() * (6-1 )) + 1);
                        switch (rand){
                            case 1:{
                                Ghost temp = Ghost.getRandomGhost();
                                Apple apple = new Apple((temp.getPosX()/GameData.CURRENT_CELL_SIZE)*GameData.CURRENT_CELL_SIZE, (temp.getPosY()/GameData.CURRENT_CELL_SIZE)*GameData.CURRENT_CELL_SIZE);
                                break;
                            }
                            case 2:{
                                Ghost temp = Ghost.getRandomGhost();
                                Lemon lemon = new Lemon((temp.getPosX()/GameData.CURRENT_CELL_SIZE)*GameData.CURRENT_CELL_SIZE, (temp.getPosY()/GameData.CURRENT_CELL_SIZE)*GameData.CURRENT_CELL_SIZE);
                                break;
                            }
                            case 3:{
                                Ghost temp = Ghost.getRandomGhost();
                                Watermelon watermelon = new Watermelon((temp.getPosX()/GameData.CURRENT_CELL_SIZE)*GameData.CURRENT_CELL_SIZE, (temp.getPosY()/GameData.CURRENT_CELL_SIZE)*GameData.CURRENT_CELL_SIZE);
                                break;
                            }
                            case 4:{
                                Ghost temp = Ghost.getRandomGhost();
                                Grapes grapes = new Grapes((temp.getPosX()/GameData.CURRENT_CELL_SIZE)*GameData.CURRENT_CELL_SIZE, (temp.getPosY()/GameData.CURRENT_CELL_SIZE)*GameData.CURRENT_CELL_SIZE);
                                break;
                            }
                            case 5:{
                                Ghost temp = Ghost.getRandomGhost();
                                Banana banana = new Banana((temp.getPosX()/GameData.CURRENT_CELL_SIZE)*GameData.CURRENT_CELL_SIZE, (temp.getPosY()/GameData.CURRENT_CELL_SIZE)*GameData.CURRENT_CELL_SIZE);
                                break;
                            }
                            default:{
                                break;
                            }
                        }
                    }


                }
                counter++;
                Thread.sleep(1000);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

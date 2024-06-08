package Menu.MenuElements;

import Elements.Node;
import Level.Level;

import java.awt.*;

public class LevelBar extends Node {
    private int maxNumber;
    private int currentNumber;
    private int levelNum;
    public LevelBar(int x, int y, int width, int height){
        super(x, y, width, height);
        image = getImage("/elements/levelbar.png");
    }

    public void setLevel(int maxNumber, int currentNumber){
        this.maxNumber = maxNumber;
        this.currentNumber = currentNumber;
    }

    public void increaseLevel(){
        if(maxNumber-1 != currentNumber)
            this.currentNumber++;
        else {
            Level.nextLevel();
            levelNum = Level.levelState;
            maxNumber = Level.getTimesBeforeNextLevel();
            currentNumber = 0;
        }
    }

    public void setLevelNum(int number){
        this.levelNum = number;
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

    @Override
    public void draw(Graphics2D g2d) {
        drawLevel(g2d);
        super.draw(g2d);
        drawLevelName(g2d);
    }

    private void drawLevel(Graphics2D g2d){
        g2d.setPaint(Color.yellow);
        g2d.fillRect(getX()+5, getY()+5, ((getWidth()-15)/maxNumber)*currentNumber, getHeight()-15);
    }

    private void drawLevelName(Graphics2D g2d){
        g2d.setPaint(Color.black);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 20));
        g2d.drawString("LEVEL " + levelNum, getX()+20, getY()+getHeight()/2+8);
    }
}

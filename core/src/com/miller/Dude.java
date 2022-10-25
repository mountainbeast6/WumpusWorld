package com.miller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Dude {
    private Location loc;
    private WumpusWorld myWorld;
    private Texture texture;
    private boolean hasGold = false;
    private int totalSteps = 0;
    private boolean killedWumpus = false;

    public Dude(Location loc, WumpusWorld myWorld) {
        this.loc = loc;
        this.myWorld = myWorld;
        texture = new Texture("guy.png");
        myWorld.makeVisible(loc);
    }

    public void randomAISolution() {
        int choice = (int)(1 + Math.random() * 4);
        if(choice == 1)
            moveDown();
        else if(choice == 2)
            moveRight();
        else if(choice == 3)
            moveLeft();
        else
            moveUp();
    }

    //this method makes ONE step
    public void step() {
        randomAISolution();
    }


    public boolean killedWumpus() {
        return killedWumpus;
    }

    public void moveRight() {
        if(loc.getCol()+1 < myWorld.getNumCols()) {
            loc.setCol(loc.getCol() + 1);
            myWorld.makeVisible(loc);
            totalSteps++;
        }
    }

    public void moveLeft() {
        if(loc.getCol()-1 >= 0) {
            loc.setCol(loc.getCol() - 1);
            myWorld.makeVisible(loc);
            totalSteps++;
        }
    }

    public void moveUp() {
        if(loc.getRow() - 1 >= 0) {
            loc.setRow(loc.getRow()-1);
            myWorld.makeVisible(loc);
            totalSteps++;
        }
    }

    public void moveDown() {
        if(loc.getRow() + 1 < myWorld.getNumRows()) {
            loc.setRow(loc.getRow()+1);
            myWorld.makeVisible(loc);
            totalSteps++;
        }
    }

    public int getTotalSteps() {
        return totalSteps;
    }

    public Location getLoc() {
        return loc;
    }

    public void reset(Location loc) {
        this.loc = loc;
        myWorld.makeVisible(loc);
        totalSteps = 0;
        killedWumpus = false;
    }

    public boolean hasGold() {
        return hasGold;
    }

    public void setHasGold(boolean hasGold) {
        this.hasGold = hasGold;
    }

    public void draw(SpriteBatch spriteBatch) {
        Point myPoint = myWorld.convertRowColToCoords(loc);
        spriteBatch.draw(texture,(int)myPoint.getX(),(int)myPoint.getY());
    }
}

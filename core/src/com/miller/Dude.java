package com.miller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Dude {
    private Location loc;
    private WumpusWorld myWorld;

    private Texture texture;
    public Dude(Location loc, WumpusWorld myWorld) {
        this.loc = loc;
        this.myWorld = myWorld;
        texture =new Texture("guy.png");
        myWorld.makeVisible(loc);
    }
    public void draw(SpriteBatch spriteBatch){
        Point myPoint = myWorld.convertRowColtoCoords(loc);
        spriteBatch.draw(texture,myPoint.x,myPoint.y);
    }

    public void moveUp() {
        if(loc.getRow()-1>=0){
            loc.setRow(loc.getRow()-1);
            myWorld.makeVisible(loc);
        }
    }

    public void moveLeft() {
        if(loc.getCol()-1>=0){
            loc.setCol(loc.getCol()-1);
            myWorld.makeVisible(loc);
        }
    }

    public void moveRight() {
        if(loc.getCol()+1<10){
            loc.setCol(loc.getCol()+1);
            myWorld.makeVisible(loc);
        }
    }
    public void moveDown() {
        if(loc.getRow()+1<10){
            loc.setRow(loc.getRow()+ 1);
            myWorld.makeVisible(loc);
        }
    }
}

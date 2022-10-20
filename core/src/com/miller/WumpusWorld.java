package com.miller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;

public class WumpusWorld {
    private int world[][] = {
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0}
    };

    boolean visible[][] = new boolean [10][10];

    private final int xoffset = 20, yoffset = 500;
    private final int tileWidth;

    private Texture groundTile,spiderTile,pitTile,wumpusTile,goldTile,
            webTile,windTile,glitterTile,stinkTile,blackTile;
    public static final int GROUND = 0, SPIDER = 1, PIT = 2, WUMPUS = 3, GOLD = 4,
            WEB = 11, WIND = 12, STINK = 13, GLITTER = 14;

    public WumpusWorld() {
        groundTile = new Texture("groundTile.png");
        spiderTile = new Texture("spiderTile.png");
        pitTile = new Texture("pitTile.png");
        wumpusTile = new Texture("wumpusTile.png");
        goldTile = new Texture("goldTile.png");
        webTile = new Texture("webTile.png");
        windTile = new Texture("windTile.png");
        glitterTile = new Texture("glitterTile.png");
        stinkTile = new Texture("stinkTile.png");
        blackTile = new Texture("blackTile.png");
        tileWidth = blackTile.getWidth();
    }

    public Location convertCoordsToRowCol(int x, int y) {
        int row;//y
        int col;//x

        //x=30, col = 0
        col = (x-xoffset)/50;
        //y = 73, row = 0
        //y = 130, row = 1
        row = (y-50)/50;//not happy with this solution

        return new Location(row,col);
    }

    public boolean isValid(Location loc) {
        return loc.getRow() >=0 && loc.getRow() < world.length &&
                loc.getCol() >= 0 && loc.getCol() < world[0].length;
    }

    public void placeTile(int tileId, Location loc) {
        if(isValid(loc)) {
            world[loc.getRow()][loc.getCol()] = tileId;
            addHints(loc,tileId);
        }
    }

    public  void makeVisible(Location loc){
        visible[loc.getRow()][loc.getCol()]=true;
    }
    public void draw(SpriteBatch spriteBatch, boolean show) {
        for(int row=0; row < world.length; row++) {
            for(int col=0; col < world[row].length; col++) {
                if(world[row][col] == GROUND&& (visible[row][col]||show))
                    spriteBatch.draw(groundTile, xoffset+col*tileWidth, yoffset-row*tileWidth);
                else if(world[row][col] == SPIDER&& (visible[row][col]||show))
                    spriteBatch.draw(spiderTile, xoffset+col*tileWidth, yoffset-row*tileWidth);
                else if(world[row][col] == WUMPUS&& (visible[row][col]||show))
                    spriteBatch.draw(wumpusTile, xoffset+col*tileWidth, yoffset-row*tileWidth);
                else if(world[row][col] == PIT&& (visible[row][col]||show))
                    spriteBatch.draw(pitTile, xoffset+col*tileWidth, yoffset-row*tileWidth);
                else if(world[row][col] == WIND&& (visible[row][col]||show))
                    spriteBatch.draw(windTile, xoffset+col*tileWidth, yoffset-row*tileWidth);
                else if(world[row][col] == STINK&& (visible[row][col]||show))
                    spriteBatch.draw(stinkTile, xoffset+col*tileWidth, yoffset-row*tileWidth);
                else if(world[row][col] == GLITTER&& (visible[row][col]||show))
                    spriteBatch.draw(glitterTile, xoffset+col*tileWidth, yoffset-row*tileWidth);
                else if(world[row][col] == GOLD&& (visible[row][col]||show))
                    spriteBatch.draw(goldTile, xoffset+col*tileWidth, yoffset-row*tileWidth);
                else if(world[row][col] == WEB&& (visible[row][col]||show))
                    spriteBatch.draw(webTile, xoffset+col*tileWidth, yoffset-row*tileWidth);
            }//end inner for
        }//end outer for
    }//end method draw
    public void addHints(Location loc, int type){
        ArrayList<Location> neighbors = getNeighbors(loc);
        for(int i=0; i<neighbors.size();i++){
            if(type!=0){
                world[neighbors.get(i).getRow()][(neighbors.get(i).getCol())]=type+10;
            }
        }
    }
    public Point convertRowColtoCoords(Location loc){
        int x =(loc.getCol()*50)+xoffset;
        int y =600-(loc.getRow()*50)-(600-yoffset);
        return new Point(x,y);
    }
    public ArrayList<Location> getNeighbors(Location loc){
        ArrayList<Location> send = new ArrayList<>();
        Location up = new Location(loc.getRow()+1,loc.getCol());
        Location down = new Location(loc.getRow()-1,loc.getCol());
        Location left = new Location(loc.getRow(),loc.getCol()+1);
        Location right = new Location(loc.getRow(),loc.getCol()-1);
        if(isValid(up)){
            send.add(up);
        }
        if (isValid(down)) {
            send.add(down);
        }
        if (isValid(left)) {
            send.add(left);
        }
        if (isValid(right)) {
            send.add(right);
        }
        return send;
    }
    public Texture getGroundTile() {
        return groundTile;
    }

    public Texture getSpiderTile() {
        return spiderTile;
    }

    public Texture getPitTile() {
        return pitTile;
    }

    public Texture getWumpusTile() {
        return wumpusTile;
    }

    public Texture getGoldTile() {
        return goldTile;
    }
}//end class
package com.miller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Dude {
    private Location loc;
    private int UP=1,RIGHT=2,LEFT=3,DOWN=4;
    private int direction=UP;
    private WumpusWorld myWorld;
    private Texture texture;
    private boolean hasGold = false;
    private int totalSteps = 0;
    private boolean killedWumpus = false;
    private boolean[][] acorn;
    int[][] map;
    private myDoublyLinkedList<Location> PathFinding;

    public Dude(Location loc, WumpusWorld myWorld) {
        this.loc = loc;
        this.myWorld = myWorld;
        texture = new Texture("guy.png");
        myWorld.makeVisible(loc);
        acorn=new boolean[myWorld.getNumRows()][myWorld.getNumCols()];
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
    public void pathingSolution(){
        myWorld.makeVisible(loc);
        if(PathFinding.size==0|| PathFinding==null) {
            map = weightMap();
            Location target = findTarget(acorn, loc);
            PathFinding=greedy(loc, target);
        }
        else {
            loc.setRow(PathFinding.get(PathFinding.size-1).getRow());
            loc.setCol(PathFinding.get(PathFinding.size-1).getCol());
            totalSteps++;
        }
    }
    private myDoublyLinkedList<Location> greedy(Location start, Location end){
        myDoublyLinkedList<Location> send = new myDoublyLinkedList<>();
        int x=start.getRow();
        int y=start.getCol();
        while(x!= end.getRow()){
            if(x<end.getRow()){
                x++;
            }
            else{
                x--;
            }
            send.addLast(new Location(x,y));
        }
        while(y!= end.getCol()){
            if(y<end.getCol()){
                y++;
            }
            else{
                y--;
            }
            send.addLast(new Location(x,y));
        }
        return send;
    }
    public myDoublyLinkedList setPath() {
        return PathFinding;
    }

    private int[][] weightMap() {
        int map[][]=new int[myWorld.getNumRows()][myWorld.getNumCols()];
        Location looking;
        ArrayList<Location> lookNeighbors;
        boolean suckMyCock=false;
        boolean hintsAround=true;
        int couner =0;
        for (int i=0;i<myWorld.getNumRows();i++){
            for(int j=0;j<myWorld.getNumCols();j++){
                couner=0;
                looking = new Location(i,j);
                lookNeighbors=myWorld.getNeighbors(looking);
                for(int k =0;k<lookNeighbors.size();k++){
                    if(myWorld.visible[lookNeighbors.get(k).getRow()][lookNeighbors.get(k).getCol()]){
                        suckMyCock=true;
                        break;
                    }
                    suckMyCock=false;
                }
                if(suckMyCock) {
                    couner=0;
                    for(int k =0;k<lookNeighbors.size();k++){
                        if(myWorld.world[lookNeighbors.get(k).getRow()][lookNeighbors.get(k).getCol()]>10/*&&map[lookNeighbors.get(k).getRow()][lookNeighbors.get(k).getCol()]>4*/){
                            couner++;
                        }
                    }
                    if(couner==myWorld.getNeighbors(looking).size()){
                        map[i][j]=20+myWorld.getTileId(lookNeighbors.get(0))%10;
                    }
                    else {
                        if (myWorld.getTileId(looking) == 0 && myWorld.visible[i][j]) {
                            map[i][j] = 11;
                        } else if (myWorld.getTileId(looking) < 4 && myWorld.visible[i][j]) {
                            map[i][j] = 20;
                        } else if (myWorld.getTileId(looking) == 4 && myWorld.visible[i][j]) {
                            map[i][j] = 1;
                        } else if (myWorld.getTileId(looking) < 14 && myWorld.visible[i][j]) {
                            hasGold=true;
                            map[i][j] = 15;
                        } else if (myWorld.getTileId(looking) == 14 && myWorld.visible[i][j]) {
                            map[i][j] = 5;
                        } else {
                            map[i][j] = 10;
                        }
                    }
                }
            }
        }
        for(int i=0; i<myWorld.getNumRows(); i++){
            for(int j=0; j< myWorld.getNumCols(); j++){
                System.out.print(map[i][j]+" | ");
            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
        return map;
    }

    private Location findTarget(boolean[][]beenThere, Location spot) {
        if(hasGold){
            return new Location(9,0);
        }
        if(map[spot.getRow()][spot.getCol()]==10){
            return spot;
        }
        beenThere[spot.getRow()][spot.getCol()]=true;
        ArrayList<Location> blackAdam =myWorld.getNeighbors(spot);
        for(int i=0; blackAdam.size()>i; i++){
            if(!beenThere[blackAdam.get(i).getRow()][blackAdam.get(i).getCol()])
                return findTarget(beenThere,blackAdam.get(i));
        }
        return null;
    }

    //this method makes ONE step
    public void step() {
        pathingSolution();
    }


    public boolean killedWumpus() {
        return killedWumpus;
    }

    public void moveRight() {
        if(loc.getCol()+1 < myWorld.getNumCols()) {
            loc.setCol(loc.getCol() + 1);
            myWorld.makeVisible(loc);
            totalSteps++;
            direction=RIGHT;
        }
    }

    public void moveLeft() {
        if(loc.getCol()-1 >= 0) {
            loc.setCol(loc.getCol() - 1);
            myWorld.makeVisible(loc);
            totalSteps++;
            direction=LEFT;
        }
    }

    public void moveUp() {
        if(loc.getRow() - 1 >= 0) {
            loc.setRow(loc.getRow()-1);
            myWorld.makeVisible(loc);
            totalSteps++;
            direction=UP;
        }
    }

    public void moveDown() {
        if(loc.getRow() + 1 < myWorld.getNumRows()) {
            loc.setRow(loc.getRow()+1);
            myWorld.makeVisible(loc);
            totalSteps++;
            direction=DOWN;
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

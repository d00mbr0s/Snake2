package snake;

import java.util.ArrayList;

/**
 * Created by Emil Erik on 2015-11-06.
 */

/**
 * Brick är en nod som har en referens till sin förälder samt till alla sina barn/neighbours
 */
public class Brick {
    private boolean isObstacle;
    private boolean isVisited;
    private int order;
    private ArrayList<Brick> neightburs = new ArrayList<>();
    private ArrayList<Brick> visitedChilds = new ArrayList<>();
    private int height = 0;
    private Brick parent;
    private String id;


    public Brick(int x, int y) {
        this.id = x + "," + y;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public Brick getParent(){
        return this.parent;
    }

    public boolean hasNoVisitedChild(){
        if (visitedChilds.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasParent(){
        if(parent == null) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Brick> getNeightburs() {
        return neightburs;
    }

    public void setNeightburs(ArrayList<Brick> neightburs) {
        this.neightburs = neightburs;
    }

    public void setIsObstacle(boolean isObstacle) {
        this.isObstacle = isObstacle;
    }
    public boolean isObstacle(){
        return isObstacle;
    }

    public String toString(){
        if(isVisited) {
            return "V" + order;
        } else if (isObstacle){
            return "X";
        }
        else {
            return "0";
        }
    }
    public boolean isVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder(){
        return order;
    }

    public ArrayList<Brick> getVisitedChilds() {
        return visitedChilds;
    }

    public void setVisitedChilds(ArrayList<Brick> visitedChilds) {
        this.visitedChilds = visitedChilds;
    }

    public void addVisitedChild(Brick brick) {
        int high = -1;
        visitedChilds.add(brick);
        brick.setParent(this);
        for (Brick b : visitedChilds) {
            if (high < b.getHeight()) {
                high = b.getHeight();
            }
        }
    }

    public void setParent(Brick parent) {
        this.parent = parent;
    }

    }

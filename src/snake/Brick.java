package snake;

import java.util.ArrayList;

/**
 * Created by Emil Erik on 2015-11-06.
 */
public class Brick {
    private boolean isObstacle;
    private boolean isVisited;
    private int order;
    private ArrayList<Brick> neightburs = new ArrayList<>();
    private ArrayList<Brick> visitedChilds = new ArrayList<>();
    private int height = 0;
    private Brick parent;
    private boolean isLast = false;
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

    public boolean isLast() {
        return isLast;
    }

    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
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

    public void setHeight(int height) {
        this.height = height;
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

    public Brick returnNeightbour() {
        for(Brick brick : neightburs) {
            if(!brick.isVisited) {
                return brick;
            }
        }
        return null;
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

    public void printTree(Brick pre){
            System.out.print(String.format("%15s", this.toString() + " came from " + pre.toString() + "     " ));
        for(Brick b : visitedChilds) {
            b.printTree(this);
        }
    }
}

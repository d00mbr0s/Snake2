package snake;

import java.util.ArrayList;


public class Tree {
    private Brick root;

    public Brick getRoot() {
        return root;
    }

    public void setRoot(Brick root) {
        this.root = root;
    }

   /* public ArrayList<Brick> getVisitedChilds() {
        return visitedChilds;
    }

    public void setVisitedChilds(ArrayList<Brick> visitedChilds) {
        this.visitedChilds = visitedChilds;
    }

    public void addVisitedChild(Brick current, Brick brick) {
        int high = -1;
        visitedChilds.add(brick);
        for (Brick b : visitedChilds) {
            if (high < b.getHeight()) {
                high = b.getHeight();
            }
            root.setHeight(high + 1);
        }
    }

    public void printTree(Brick brick){
        System.out.println(brick);
        for(Brick b : visitedChilds) {
            printTree(b);
        }
    }
*/

}

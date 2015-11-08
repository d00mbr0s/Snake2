package snake;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Exempel {
    private Brick[][] brickArray;
    private int order = 0;
    private ArrayList<ArrayList<Brick>> paths = new ArrayList<>();
    private ArrayList<Integer> pathsLengths = new ArrayList<>();
    private SnakeUI ui;

    /**
     * Metod som h�mtar textfilen fr�n datorn
     * @param textToParse S�kv�gen d�r textfilen ligger p� datorn
     */
    public void parseTextFile(String textToParse)
    {
        ArrayList<String> splitted = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(textToParse)))
        {
            String line;
            String[] cutLine;
            while ((line = br.readLine()) != null)
            {
                cutLine = line.split(",");
                if(cutLine.length > 2){
                    for(int i = 0; i < 3; i++)
                        splitted.add(cutLine[i]);

                }
                else{
                    splitted.add(cutLine[0]);
                    splitted.add(cutLine[1]);
                }
            }
            initializeVariables(splitted);

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * Metod som initierar alla variabler, h�mtar rader
     * @param parsedText ArrayList med kartan
     */

    private void initializeVariables(ArrayList<String> parsedText){
        System.out.println("INPUT\n------------------");
        int nbrColums = Integer.parseInt(parsedText.get(0));
        System.out.print(parsedText.get(0) + ",");
        int nbrRows = Integer.parseInt(parsedText.get(1));
        System.out.print(parsedText.get(1) + ",");
        int nbrOfObstacles = Integer.parseInt(parsedText.get(2));
        System.out.println(parsedText.get(2));

        int obstacle_X;
        int obstacle_Y;

        // Array med storleken enligt rader & kolumner.
        brickArray = new Brick[nbrColums][nbrRows];

        // Initierar brickorna och hindren i spelet.
        initializeBricks();

        // En 3:a d� f�rsta positionen p� f�rsta hindret �r [3] enligt input.txt
        int indexCounter = 3;

        // Loop som k�rs lika m�nga ggr som det finns hinder.
        for (int i = 0; i < nbrOfObstacles; i++)
        {
            obstacle_X = Integer.parseInt(parsedText.get(indexCounter));
            obstacle_Y = Integer.parseInt(parsedText.get(indexCounter + 1));
            System.out.println(obstacle_X + "," + obstacle_Y);
            brickArray[obstacle_X][obstacle_Y].setIsObstacle(true)	;

            // �ka med 2 pga av varje hinder representeras av [x],[y]
            indexCounter += 2;
        }
        ui = new SnakeUI(brickArray);


        setNeightburs();
        startSnake(brickArray[0][0]);

        findLongestPath();
        ui.buildUI(brickArray);

       /* for(Integer x : pathsLengths) {
            System.out.print(x + " ");
        }
        System.out.println();
        for(ArrayList<Brick> path : paths) {
            for(Brick b : path) {
                System.out.print(b.getId() + "  ");
            }
            System.out.println();
        }*/
        setLongestPath();



    }

    private void printPath(ArrayList<Brick> bricks) {
        for(Brick brick : bricks) {
            System.out.println(brick.getId());
        }
    }

    private void setLongestPath() {
        for (int i = 0; i < brickArray.length; i++) {
            for (int j = 0; j < brickArray[i].length; j++) {
                brickArray[i][j].setIsVisited(false);
            }
        }

        ArrayList<Brick> temp =paths.get(0);

        for (int i = 0; i < paths.size(); i++) {
            if (paths.get(i).size() > temp.size()) {
                temp = paths.get(i);
            }
        }

        for(Brick[] ba : brickArray) {
            for (Brick b : ba) {
                for (Brick c : temp) {
                    if(b == c) {
                        b.setIsVisited(true);
                    }
                }
            }
        }


        ui.buildUI(brickArray);
        ui.setPnlSouth(temp.size());
        System.out.println("\n\n\nOUTPUT\n------------------");
        System.out.println(temp.size());
        printPath(temp);
    }

    private void setNeightburs(){
        for(int x = 0; x < brickArray.length; x++) {
            for(int y = 0; y < brickArray[x].length; y++) {
                ArrayList<Brick> neightbours = new ArrayList<>();
                if(x-1 >= 0 &&  !brickArray[x-1][y].isObstacle()) {
                    neightbours.add(brickArray[x-1][y]);
                }
                if(y-1 >= 0 &&  !brickArray[x][y-1].isObstacle()) {
                    neightbours.add(brickArray[x][y-1]);
                }
                if(x+1 < brickArray.length &&  !brickArray[x+1][y].isObstacle()) {
                    neightbours.add(brickArray[x+1][y]);
                }
                if(y+1 < brickArray[x].length &&  !brickArray[x][y+1].isObstacle()) {
                    neightbours.add(brickArray[x][y+1]);
                }

                brickArray[x][y].setNeightburs(neightbours);
            }

        }
    }

    public void printArray() {
        for(Brick[] brick : brickArray) {
            for(Brick b : brick) {
               // System.out.print(b.toString() + "   ");
                System.out.print(String.format("%15s", b.toString() + "   "));
            }
            System.out.println();
        }

    }

    /**
     * Metod som skapar lika m�nga brick-objekt som det finns rader & kolumner och l�gger dom i brick-Arrayen.
     */
    private void initializeBricks()
    {
        for (int x = 0; x < brickArray.length; x ++)
        {
            for (int y = 0; y < brickArray[x].length; y ++)
            {
                brickArray[x][y] = new Brick(x,y);

            }
        }
    }

    public void startSnake(Brick current) {
        snake(current);
      //  printArray();
      //  current.printTree(current);
    }

    public void snake(Brick current) {
        try {
            current.setIsVisited(true);
            current.setOrder(order++);
            for (Brick brick : current.getNeightburs()) {
                if (!brick.isVisited()) {
                    snake(brick);
                    current.addVisitedChild(brick);
                    brick.setParent(current);
                }
            }
        } catch (Exception e) {
            System.out.println("NAAJ");
        }
    }


    private int calculateHeight(Brick start) {
        ArrayList<Brick> currentPath = new ArrayList<>();
        paths.add(currentPath);
        int count = 1;
        Brick temp = start;
        while(temp.hasParent()) {
            currentPath.add(0, temp);
            temp = temp.getParent();
            count++;
        }
        currentPath.add(0, temp);
       // System.out.println("hej");
        return count;
    }

    private void findLongestPath() {
        for(int i = 0; i < brickArray.length; i++) {
            for(int j = 0; j < brickArray[i].length; j++) {
                if(!brickArray[i][j].isObstacle() && brickArray[i][j].isVisited() && brickArray[i][j].hasNoVisitedChild()) {
                    pathsLengths.add(calculateHeight(brickArray[i][j]));
                }
            }
        }
    }

}

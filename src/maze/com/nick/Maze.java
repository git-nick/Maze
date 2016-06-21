package maze.com.nick;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by N on 16/04/2016.
 */
public class Maze {

    private int numVisited;
    private MazeBlockCollection maze;
    private MazeBlock currentBlock;
    private MazeBlock endBlock;
    private char[] directionMap;
    private int width; //arbitrary units, 1 is 1 'maze block'
    private int height; //arbitrary units, 1 is 1 'maze block'

    private static final char DIR_NORTH = 'N';
    private static final char DIR_EAST = 'E';
    private static final char DIR_SOUTH = 'S';
    private static final char DIR_WEST = 'W';

    Maze(int width, int height) {
        //The number of first time visits to maze blocks
        //so we can easily know when we've reached the end
        this.numVisited = 1;
        this.width=width;
        this.height=height;
        //The maze consists of arrays of Block objects
        this.maze = new MazeBlockCollection(width, height);
        //valid directions - N, S, E and W
        this.directionMap = new char[]{DIR_NORTH, DIR_SOUTH, DIR_EAST, DIR_WEST};
        //finds a start point along one of the walls at any location
        //returns a Block object
        this.currentBlock = this.configureEntryExitBlock(width, height);
        this.currentBlock.setStartBlock(true);

        this.start();
    }

    public MazeBlockCollection getMaze() { return this.maze; }

    private void start() {
        boolean foundAnotherBlock;
        do {
              foundAnotherBlock = this.updateNextBlock();
              this.numVisited++;
        }
        while (foundAnotherBlock);
    }

    private static HashMap<Character, Boolean> getNewDirectionVisited() {
        HashMap<Character, Boolean> directionsVisited = new HashMap<>();

        directionsVisited.put(DIR_NORTH, false);
        directionsVisited.put(DIR_EAST, false);
        directionsVisited.put(DIR_SOUTH, false);
        directionsVisited.put(DIR_WEST, false);
        return directionsVisited;
    }

    private MazeBlock configureEntryExitBlock(int width, int height)
    {
        HashMap<Character,Boolean> directionsVisited=getNewDirectionVisited();

        //in this case we are using this to get a starting or ending wall, N,S,E or W
        char direction=getNextDirection(directionsVisited);

        MazeBlock block;
        int x;
        int y;
        //gets a random starting block along the north, south, east or west wall
        switch(direction)
        {
            case 'N':
                x=getRandomBetween(0,width-1);
                block=this.maze.getBlock(x,0);
                block.setNorthWall(false);
                break;
            case 'S':
                x=getRandomBetween(0,width-1);
                block=this.maze.getBlock(x,height-1);
                block.setSouthWall(false);
                break;
            case 'E':
                y=getRandomBetween(0,height-1);
                block=this.maze.getBlock(width-1,y);
                block.setEastWall(false);
                break;
            case 'W':
            default:
                y=getRandomBetween(0,height-1);
                block=this.maze.getBlock(0,y);
                block.setWestWall(false);
                break;
        }
        block.setVisited(true);

        return block;
    }

    // returns a random int between min and max
    private static int getRandomBetween(int min,int max)
    {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    /*
    * Given a hash map of N,S,E or W = true/false (representing visited),
    * this function will return a viable, unvisited direction as a char N,S,E, or w
    * */
    private char getNextDirection(HashMap<Character,Boolean> attemptedDirections)
    {
        boolean validDirection=false;
        char direction=DIR_NORTH;

        while(!validDirection) {
            //get a random int between 0 and 3 that represents N,S,E or W
            int dir=getRandomBetween(0,3);
            direction = this.directionMap[dir];
            Boolean hasBeenAttempted=attemptedDirections.get(direction);

            if(!hasBeenAttempted) {
                validDirection=true;
            }
        }
        return direction;
    }

    private boolean updateNextBlock()
    {
        boolean validDirection=false;
        HashMap<Character,Boolean> attemptedDirections=getNewDirectionVisited();

        while(!validDirection)
        {
            //if we've tried them all and dont have a valid block then go back
            if(attemptedDirections.get(this.directionMap[0]) &&
                    attemptedDirections.get(this.directionMap[1]) &&
                    attemptedDirections.get(this.directionMap[2]) &&
                    attemptedDirections.get(this.directionMap[3]))
            {
                //if we've visited them all
                if(this.numVisited==(this.width*this.height))
                {
                    MazeBlock possibleEnd = this.configureEntryExitBlock(width, height);
                    while(possibleEnd.isStartBlock()) {
                        possibleEnd = this.configureEntryExitBlock(width, height);
                    }
                    this.endBlock = possibleEnd;
                    this.endBlock.setEndBlock(true);
                    return false;
                }
                //if we couldn't find another way but this is not the end of the maze then backtrack
                this.currentBlock=this.currentBlock.getPrevious();
                attemptedDirections=getNewDirectionVisited();
            }
            char dir=this.getNextDirection(attemptedDirections);
            attemptedDirections.put(dir,true);
            switch(dir)
            {
                case 'N':
                    //if it's within the boundaries and it has not been visited
                    if(this.currentBlock.getY()>0 &&
                            !this.maze.getBlock(this.currentBlock.getX(),this.currentBlock.getY()-1).getVisited()) {
                        MazeBlock nextBlock = this.maze.getBlock(this.currentBlock.getX(), this.currentBlock.getY() - 1);
                        this.currentBlock.setNext(nextBlock);
                        this.currentBlock.setNorthWall(false);
                        nextBlock.setSouthWall(false);
                        nextBlock.setVisited(true);
                        nextBlock.setPrevious(this.currentBlock);
                        this.currentBlock = nextBlock;
                        validDirection = true;
                    }
                    break;
                case 'S':
                    if(this.currentBlock.getY()<this.height-1 &&
                            !this.maze.getBlock(this.currentBlock.getX(),this.currentBlock.getY()+1).getVisited())
                    {
                        MazeBlock nextBlock=this.maze.getBlock(this.currentBlock.getX(),this.currentBlock.getY()+1);
                        this.currentBlock.setNext(nextBlock);
                        this.currentBlock.setSouthWall(false);
                        nextBlock.setNorthWall(false);
                        nextBlock.setVisited(true);
                        nextBlock.setPrevious(this.currentBlock);
                        this.currentBlock=nextBlock;
                        validDirection=true;
                    }
                    break;
                case 'E':
                    if(this.currentBlock.getX()<this.width-1 &&
                            !this.maze.getBlock(this.currentBlock.getX()+1,this.currentBlock.getY()).getVisited())
                    {
                        MazeBlock nextBlock=this.maze.getBlock(this.currentBlock.getX()+1,this.currentBlock.getY());
                        this.currentBlock.setNext(nextBlock);
                        this.currentBlock.setEastWall(false);
                        nextBlock.setWestWall(false);
                        nextBlock.setVisited(true);
                        nextBlock.setPrevious(this.currentBlock);
                        this.currentBlock=nextBlock;
                        validDirection=true;
                    }
                    break;
                case 'W':
                    if(this.currentBlock.getX()>0 &&
                            !this.maze.getBlock(this.currentBlock.getX()-1,this.currentBlock.getY()).getVisited())
                    {
                        MazeBlock nextBlock=this.maze.getBlock(this.currentBlock.getX()-1,this.currentBlock.getY());
                        this.currentBlock.setNext(nextBlock);
                        this.currentBlock.setWestWall(false);
                        nextBlock.setEastWall(false);
                        nextBlock.setVisited(true);
                        nextBlock.setPrevious(this.currentBlock);
                        this.currentBlock=nextBlock;
                        validDirection=true;
                    }
                    break;
            }
        }
        return true;
    }


}

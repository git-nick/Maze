package maze.com.nick;

/**
 * Created by N on 16/04/2016.
 */
public class MazeBlock
{
    private MazeBlock previous;
    private MazeBlock next;
    private boolean northWall;
    private boolean southWall;
    private boolean eastWall;
    private boolean westWall;
    private boolean visited;
    private boolean startBlock;
    private boolean endBlock;

    private int x;
    private int y;

    public MazeBlock(int x, int y) {
        this.x=x;
        this.y=y;
        this.previous = null;
        this.next = null;
        this.northWall = true;
        this.southWall = true;
        this.eastWall = true;
        this.westWall = true;
        this.visited = false;
        this.startBlock = false;
        this.endBlock = false;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getVisited() {
        return this.visited;
    }

    public void setNext(MazeBlock nxt) {
        this.next=nxt;
    }

    public void setPrevious(MazeBlock prv) {
        this.previous=prv;
    }

    public void setStartBlock( boolean start) {
        this.startBlock=start;
    }

    public void setEndBlock(boolean end) {
        this.endBlock=end;
    }

    public MazeBlock getPrevious() {
        return this.previous;
    }

    public void setVisited(boolean visited) {
        this.visited=visited;
    }

    public void setNorthWall(boolean northWallValue) {
        this.northWall=northWallValue;
    }

    public void setSouthWall(boolean southWallValue) {
        this.southWall=southWallValue;
    }

    public void setEastWall(boolean eastWallValue) {
        this.eastWall=eastWallValue;
    }

    public void setWestWall(boolean westWallValue) {
        this.westWall=westWallValue;
    }

    public boolean hasNorthWall() {
        return this.northWall;
    }

    public boolean hasSouthWall() {
        return this.southWall;
    }

    public boolean hasEastWall() {
        return this.eastWall;
    }

    public boolean hasWestWall() {
        return this.westWall;
    }

    public boolean isStartBlock() { return this.startBlock; }

    public String toString() {
        String str="";
        String prevStr=(this.previous==null ? "[-][-]" : "["+this.previous.getX()+"]["+this.previous.getY()+"]");
        String nextStr=(this.next==null ? "[-][-]" : "["+this.next.getX()+"]["+this.next.getY()+"]");

        str+="Prev = "+prevStr+"\n";
        str+="Next = "+nextStr+"\n";
        str+="N = "+this.northWall+"\n";
        str+="S = "+this.southWall+"\n";
        str+="E = "+this.eastWall+"\n";
        str+="W = "+this.westWall+"\n";
        str+="Visited = "+this.visited+"\n";
        str+="Start = "+this.startBlock+"\n";
        str+="End = "+this.endBlock+"\n";
        return str;
    }

}

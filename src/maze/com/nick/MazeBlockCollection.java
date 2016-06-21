package maze.com.nick;

/**
 * Created by N on 16/04/2016.
 */
public class MazeBlockCollection {

    private MazeBlock[][] blocks;

    public MazeBlockCollection(int width, int height) {
        blocks=new MazeBlock[width][height];

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                this.blocks[w][h] = new MazeBlock(w, h);
            }
        }
    }

    public int getLength() { return this.blocks.length; }

    public int getHeight() { return this.blocks[0].length; }

    public MazeBlock getBlock(int x, int y) {
        return this.blocks[x][y];
    }

    public void setStartBlock(MazeBlock currentBlock, boolean startVal) {
        this.blocks[currentBlock.getX()][currentBlock.getY()].setStartBlock(startVal);
    }

    public void setVisited(MazeBlock currentBlock, boolean visitedVal) {
        this.blocks[currentBlock.getX()][currentBlock.getY()].setVisited(true);
    }

    public String toString() {
        String str="\n***** Start *****\n";
        for(int i=0; i<this.blocks.length; i++) {
            for (int j = 0; j < this.blocks[i].length; j++) {
                str += "Block "+i+" - "+j+"\n"+this.blocks[i][j].toString() + "\n---\n";
            }
        }
        str+="\n***** End *****\n";
        return str;
    }
}

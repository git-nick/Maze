package maze.com.nick;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by N on 01/05/2016.
 */

public class MazeDrawer extends JComponent {

    public MazeDrawer(Maze m, int blockSize, int mazeBlockWidth, int mazeBlockHeight)
    {
        JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(((mazeBlockWidth*blockSize)+40), ((mazeBlockHeight*blockSize)+40)));
        testFrame.getContentPane().add(this, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel();
        testFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        testFrame.pack();
        testFrame.setVisible(true);

        MazeBlockCollection mazeBlocks=m.getMaze();
        int len=mazeBlocks.getLength();
        int ht=mazeBlocks.getHeight();
        int padding=20;
        for (int a=0; a<len; a++)
        {
            for (int b=0; b<ht; b++)
            {
                MazeBlock block=mazeBlocks.getBlock(a,b);
                if(block.hasNorthWall())
                {
                    this.addLine((a*blockSize)+padding, (b*blockSize)+padding, (a*blockSize)+blockSize+padding, (b*blockSize)+padding);
                }
                if(block.hasSouthWall())
                {
                    this.addLine((a*blockSize)+padding, (b*blockSize)+blockSize+padding, (a*blockSize)+blockSize+padding, (b*blockSize)+blockSize+padding);
                }
                if(block.hasEastWall())
                {
                    this.addLine((a*blockSize)+blockSize+padding, (b*blockSize)+padding, (a*blockSize)+blockSize+padding, (b*blockSize)+blockSize+padding);
                }
                if(block.hasWestWall())
                {
                    this.addLine((a*blockSize)+padding, (b*blockSize)+padding, (a*blockSize)+padding, (b*blockSize)+blockSize+padding);
                }
            }
        }
        this.repaint();

    }

    private static class Line{
        final int x1;
        final int y1;
        final int x2;
        final int y2;
        final Color color;

        public Line(int x1, int y1, int x2, int y2, Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }
    }

    private final LinkedList<Line> lines = new LinkedList<Line>();

    public void addLine(int x1, int x2, int x3, int x4) {
        addLine(x1, x2, x3, x4, Color.black);
    }

    public void addLine(int x1, int x2, int x3, int x4, Color color) {
        lines.add(new Line(x1,x2,x3,x4, color));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Line line : lines) {
            g.setColor(line.color);
            g.drawLine(line.x1, line.y1, line.x2, line.y2);
        }
    }

}
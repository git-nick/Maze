package maze.com.nick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by N on 13/05/2016.
 */
public class MazeIntro extends JFrame {
    static final String mazeDimensions[] = {"20", "30", "40", "50"};
    static final String pathWidths[] = {"6", "8", "10", "12", "14", "16", "18"};

    final static int maxSideLength = 200;
    final static int maxPathWidth = 12;

    private JComboBox<String> widthOptions;
    private JComboBox<String> heightOptions;
    private JComboBox<String> pathOptions;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MazeIntro mazeIntro=new MazeIntro();
                mazeIntro.createAndShowGUI();
            }
        });
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Maze");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Create a yellow label to put in the content pane.
        JLabel widthLabel = new JLabel("Maze Width");
        this.widthOptions = new JComboBox<>(mazeDimensions);

        JLabel heightLabel = new JLabel("Maze Height");
        this.heightOptions = new JComboBox<>(mazeDimensions);

        JLabel pathLabel = new JLabel("Path Width");
        this.pathOptions = new JComboBox<>(pathWidths);

        JButton jb = new JButton("Build");

        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int width= Integer.parseInt(widthOptions.getSelectedItem().toString());
                int height= Integer.parseInt(heightOptions.getSelectedItem().toString());

                Maze m = new Maze(width,height);
                int size= Integer.parseInt(pathOptions.getSelectedItem().toString());
                new MazeDrawer(m,size,width,height);
            }
        });
        JPanel panel=new JPanel( new FlowLayout() );

        //Set the menu bar and add the label to the content pane.
        panel.add(widthLabel);
        panel.add(widthOptions);
        panel.add(heightLabel);
        panel.add(heightOptions);
        panel.add(pathLabel);
        panel.add(pathOptions);
        panel.add(jb);
        //panel.addBorder( new EmptyBorder(10, 10, 10, 10) );
        panel.setBackground(new Color(248, 213, 131));
        frame.add(panel, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(400, 150));
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
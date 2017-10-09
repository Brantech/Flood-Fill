package FloodFill;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import static java.awt.Color.*;

// Main window that holds all elements of the program.
class Window extends JFrame {
    static JFrame self;
    static ArrayList<Tile> tiles;
    static int start;
    static int flag;
    static int tileNum;
    private static JPanel content;
    private static Toolbar toolBar;
    
    public Window() {
        tiles = new ArrayList<>();
        self = this;
        flag = 0;
        
        setTitle("Flood Fill");
        setMinimumSize(new Dimension(606, 729));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        
        toolBar = new Toolbar();
        add(toolBar);
        pixels();
        
        // Changes the size of the components relative to the main window.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized( ComponentEvent e ) {
                content.setSize(self.getContentPane().getSize().width, self.getContentPane().getSize().height * 6 / 7);
                toolBar.setSize(self.getContentPane().getSize().width, self.getContentPane().getSize().height * 1 / 7);
                toolBar.setLocation(0, Window.self.getContentPane().getSize().height * 6 / 7);
                repaint();
                revalidate();
            }
        });
    }
    
    /* Changes the amount of tiles in content */
    static void pixels() {
        flag = 0;
        // On start-up this will ask for the dimensions of the board until you enter a valid number.
        // If there already is a dimension set, the dialogue will close properly even with invalid input.
        while( true ) {
            try {
                String input = JOptionPane.showInputDialog(content, "Enter n. 1 < n < 101");
                tileNum = Integer.parseInt(input);
                
                if( tileNum > 100 || tileNum < 2 ) continue;
                break;
            } catch ( Exception e ) {
                if( tiles.size() != 0 ) return;
            }
        }
        start = 0;
        
        // Wipes and prepares the content pane for new tiles.
        tiles.clear();
        if( content == null ) {
            content = new JPanel();
            content.setBackground(Color.gray);
            content.setSize(self.getContentPane().getSize().width, self.getContentPane().getSize().height * 6 / 7);
            content.setLocation(0, 0);
            content.setLayout(new GridLayout(tileNum, tileNum, 0, 0));
            
            self.add(content);
        }
        content.removeAll();
        content.repaint();
        
        // Fills the content pane with tileNum * tileNum tiles.
        for( int i = 0; i < tileNum * tileNum; i++ ) {
            Tile temp = new Tile(white);
            if( i == 0 ) temp.setBackground(red);
            tiles.add(temp);
            content.add(temp);
        }
        content.revalidate();
    }
    
    /* Starts the flood fill algorithm. */
    static void start() {
        if( flag == 1 ) return;
        for( Tile x : tiles ) {
            if( x.getBackground() == cyan ) x.setBackground(white);
        }
        Thread fill = new Thread(new FloodFill());
        fill.start();
        flag = 1;
    }
    
    /* Resets content to it's original state. */
    static void reset() {
        for( Tile x : tiles ) {
            x.setBackground(white);
        }
        tiles.get(0).setBackground(red);
        start = 0;
        flag = 0;
    }
    
    /* Stops the flood fill algorithm and allows you to restart or edit the board. */
    static void stop() {
        flag = 0;
    }
    
    /* Changes the starting point of the algorithm. */
    static void changeRed( Tile x ) {
        tiles.get(start).setBackground(white);
        x.setBackground(red);
        start = tiles.indexOf(x);
    }
}

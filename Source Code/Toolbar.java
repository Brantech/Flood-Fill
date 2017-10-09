package FloodFill;


import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class will contain the buttons at the bottom of the window.
class Toolbar extends JPanel {
    public Toolbar() {
        setBackground(Color.black);
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
        setLocation(0, Window.self.getContentPane().getSize().height * 6 / 7);
        setSize(Window.self.getContentPane().getSize().width, Window.self.getContentPane().getSize().height / 7);
        
        // Stops the algorithm and resets the content pane to the default setup.
        JButton reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(100, 50));
        reset.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( Window.tileNum == 0 ) return;
                Window.reset();
            }
        });
        
        // Starts the algorithm.
        JButton start = new JButton("Start");
        start.setPreferredSize(new Dimension(100, 50));
        start.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( Window.tileNum == 0 ) return;
                Window.start();
            }
        });
        
        // Stops the algorithm without resetting the content pane
        JButton stop = new JButton("Stop");
        stop.setPreferredSize(new Dimension(100, 50));
        stop.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                if( Window.tileNum == 0 ) return;
                Window.stop();
            }
        });
        
        // Changes the number of tiles in the content pane to n by n.
        JButton resize = new JButton("Resize");
        resize.setPreferredSize(new Dimension(100, 50));
        resize.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                Window.pixels();
            }
        });
        
        // Changes the wait time after each operation in the algorithm.
        JButton delay = new JButton("Delay");
        delay.setPreferredSize(new Dimension(100, 50));
        delay.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                try {
                    Window.flag = 0;
                    String input = JOptionPane.showInputDialog(null, "Enter the amount of seconds you want the algorithm to change a tile. You can use decimals. ");
                    FloodFill.delay = (int) ( Double.parseDouble(input) * 1000 );
                } catch ( Exception ignored ) {
                }
            }
        });
        
        
        add(start);
        add(stop);
        add(reset);
        add(resize);
        add(delay);
    }
}

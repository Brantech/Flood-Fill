package FloodFill;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Each tile is a JPanel with its own MouseAdapter and color. While this is probably not efficient when you have a lot of
// tiles, this worked well enough for when I made it.
class Tile extends JPanel {
    private final Tile self;
    
    public Tile( Color c ) {
        self = this;
        setBackground(c);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setOpaque(true);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed( MouseEvent e ) {
                // Does nothing if the algorithm is running
                if( Window.flag == 1 ) ;
                    
                    // Changes its color to black if it isn't already red.
                else if( SwingUtilities.isLeftMouseButton(e) ) {
                    if( getBackground() == Color.red ) ;
                    else setBackground(Color.black);
                }
                
                // Changes its color to red.
                else if( SwingUtilities.isMiddleMouseButton(e) )
                    Window.changeRed(self);
                    
                    // Changes its color to white if it isn't already red
                else if( SwingUtilities.isRightMouseButton(e) ) {
                    if( getBackground() == Color.red ) ;
                    else setBackground(Color.white);
                }
            }
            
            // This handles when the mouse is clicked and dragged into this panel. Changes the color to black or white.
            @Override
            public void mouseEntered( MouseEvent e ) {
                if( Window.flag == 1 ) ;
                else if( SwingUtilities.isLeftMouseButton(e) ) {
                    if( getBackground() == Color.red ) ;
                    else setBackground(Color.black);
                } else if( SwingUtilities.isRightMouseButton(e) ) {
                    if( getBackground() == Color.red ) ;
                    else setBackground(Color.white);
                }
                
            }
        });
    }
}

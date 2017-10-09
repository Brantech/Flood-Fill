package FloodFill;

import java.util.HashSet;

import static FloodFill.Window.*;
import static java.awt.Color.*;

// Starts the program by creating a new Window. Also contains the actual flood fill algorithm.
class FloodFill implements Runnable {
    static int delay;
    
    public static void main( String[] args ) {
        delay = 10;
        new Window();
    }
    
    // When the flag in the Window class is set to 0, the algorithm stops.
    @Override
    public void run() {
        GenQueue<Integer> fill = new GenQueue<Integer>(0);
        HashSet<Integer> duplicates = new HashSet<>();
        fill.enqueue(start);
        
        // Does a breadth-first search through the tiles in the content pane.
        while( fill.peek() != null ) {
            
            int temp = fill.dequeue();
            if( flag == 0 ) break;
            
            // The tile to be changed will first turn yellow.
            if( tiles.get(temp).getBackground() == white ) tiles.get(temp).setBackground(yellow);
            
            // The thread sleeps for the specified amount of time.
            try {
                Thread.sleep(delay);
            } catch ( InterruptedException ignored ) {
            }
            
            // Checks if the flag changed before proceeding.
            if( flag == 0 ) {
                tiles.get(temp).setBackground(white);
                break;
            }
            
            // Changes the tile to cyan.
            if( tiles.get(temp).getBackground() == yellow ) tiles.get(temp).setBackground(cyan);
            
            // Enqueues the white tiles adjacent to it. It moves up and down by adding or subtracting the width.
            int width = Window.tileNum;
            
            // Up
            if( temp - width > 0 && tiles.get(temp - width).getBackground() == white )
                fill.enqueue(temp - width);
            // Right
            if( ( temp + 1 ) % width != 0 && tiles.get(temp + 1).getBackground() == white )
                fill.enqueue(temp + 1);
            // Down
            if( temp + width < width * width && tiles.get(temp + width).getBackground() == white )
                fill.enqueue(temp + width);
            // Left
            if( temp - 1 > -1 && ( temp - 1 ) % width != width - 1 && tiles.get(temp - 1).getBackground() == white )
                fill.enqueue(temp - 1);
        }
        // Resets the flag and clears the queue.
        fill.clear();
        flag = 0;
    }
}

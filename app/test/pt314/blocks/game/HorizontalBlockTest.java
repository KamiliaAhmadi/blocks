package pt314.blocks.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import pt314.blocks.gui.SimpleGUI;

/**
 * JUnit tests for <code>HorizontalBlock</code>.
 */
public class HorizontalBlockTest {
    
    private Block block;
   
    
    @Before
    public void setUp() {     
        
        block = new HorizontalBlock();
    }
    
    @Test
    public void testIsValidDirectionLeftRight() {
        assertTrue(block.isValidDirection(Direction.LEFT));
        assertTrue(block.isValidDirection(Direction.RIGHT));
    }
    
    @Test
    public void testIsValidDirectionUpDown() {
        assertFalse(block.isValidDirection(Direction.UP));
        assertFalse(block.isValidDirection(Direction.DOWN));
    }
    
    
}

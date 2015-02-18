package pt314.blocks.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import pt314.blocks.gui.SimpleGUI;

@RunWith(Suite.class)
@SuiteClasses({
    HorizontalBlockTest.class,
    VerticalBlockTest.class,
    TargetBlockTest.class
})

public class AllTests {
    
    private GameBoard gameBoard;
    private int numRows;
    private int numCols;
    @Before
    public void setUp() throws FileNotFoundException, IOException {
        numRows=SimpleGUI.NUM_ROWS;
        numCols=SimpleGUI.NUM_COLS;
        gameBoard=new GameBoard(numCols, numRows);
    }
    
    @Test
    public void testGetBlock() {
        
        //Test for the places we don't have block
        assertEquals(null,gameBoard.getBlockAt(0,5));
        assertEquals(null,gameBoard.getBlockAt(0,6));
        assertEquals(null,gameBoard.getBlockAt(0,7));
        assertEquals(null,gameBoard.getBlockAt(1,1));
        assertEquals(null,gameBoard.getBlockAt(1,2));
        assertEquals(null,gameBoard.getBlockAt(1,3));
        assertEquals(null,gameBoard.getBlockAt(4,5));
        assertEquals(null,gameBoard.getBlockAt(4,6));
        assertEquals(null,gameBoard.getBlockAt(4,7));
        
        //For the existance of horizontal block
        Block HB=new HorizontalBlock();
        assertEquals(HB,gameBoard.getBlockAt(0,1));
        assertEquals(HB,gameBoard.getBlockAt(0,2));
        assertEquals(HB,gameBoard.getBlockAt(1,5));
        assertEquals(HB,gameBoard.getBlockAt(1,6));
        assertEquals(HB,gameBoard.getBlockAt(2,0));
        assertEquals(HB,gameBoard.getBlockAt(4,1));
        assertEquals(HB,gameBoard.getBlockAt(4,2));
        assertEquals(HB,gameBoard.getBlockAt(4,3));
        
        
         //For the existance of vertical block
        Block VB=new VerticalBlock();
        assertEquals(VB,gameBoard.getBlockAt(0,0));
        assertEquals(VB,gameBoard.getBlockAt(1,0));
        assertEquals(VB,gameBoard.getBlockAt(1,4));
        assertEquals(VB,gameBoard.getBlockAt(2,2));
        assertEquals(VB,gameBoard.getBlockAt(2,4));
        assertEquals(VB,gameBoard.getBlockAt(4,0));
        assertEquals(VB,gameBoard.getBlockAt(5,0));
        
        //For the existance of Target block
        Block TB=new TargetBlock();
        assertEquals(TB,gameBoard.getBlockAt(2,5));
    }
    
    @Test
    public void testIsMoveWithinBound() {
        assertFalse(gameBoard.isWithinBounds(numRows+1, numCols+1));
        assertFalse(gameBoard.isWithinBounds(numRows+5, numCols+6));
        assertFalse(gameBoard.isWithinBounds(numRows, numCols));
        assertFalse(gameBoard.isWithinBounds(numRows, numCols-1));
        assertFalse(gameBoard.isWithinBounds(0, numCols-1));
        assertTrue(gameBoard.isWithinBounds(numRows-1, numCols-1));
        assertTrue(gameBoard.isWithinBounds(1, numCols-1));
        assertTrue(gameBoard.isWithinBounds(3, 4));
        assertTrue(gameBoard.isWithinBounds(4, 6));
        assertTrue(gameBoard.isWithinBounds(2, 6));
        assertFalse(gameBoard.isWithinBounds(-1, -2));
    }
    
    @Test
    public void testIsThereBlockInTheWayToDestination() {
        assertFalse(gameBoard.moveBlock(numRows, numCols, Direction.UP, numRows));
        assertTrue(gameBoard.moveBlock(0, 5, Direction.RIGHT, 2));
        assertTrue(gameBoard.moveBlock(1, 6, Direction.RIGHT, 1));
        assertTrue(gameBoard.moveBlock(3, 5, Direction.RIGHT, 2));
        assertTrue(gameBoard.moveBlock(2, 2, Direction.UP, 1));
        assertTrue(gameBoard.moveBlock(2, 2, Direction.DOWN, 1));
        assertTrue(gameBoard.moveBlock(4, 4, Direction.RIGHT, 3));
        assertFalse(gameBoard.moveBlock(3, 4, Direction.UP, 1));
        assertFalse(gameBoard.moveBlock(0, 3, Direction.LEFT, 3));
        assertFalse(gameBoard.moveBlock(3, 4, Direction.UP, 1));
        assertFalse(gameBoard.moveBlock(4, 0, Direction.UP, 3));
        
    }
    
    @Test
    public void testMoveBlock() {
        
        //invalid move
        assertFalse(gameBoard.moveBlock(numRows, numCols, Direction.UP, numRows));
        
        //valid move for a horizontal block and check if it reached the destination
        assertTrue(gameBoard.moveBlock(0, 5, Direction.RIGHT, 2));
        Block bH=new HorizontalBlock();
        assertEquals(bH, gameBoard.getBlockAt(0, 7));
        
        //valid move for a vertical block and check if it reached the destination
        Block bV=new VerticalBlock();
        assertTrue(gameBoard.moveBlock(2, 2, Direction.UP, 1));
        assertEquals(bV, gameBoard.getBlockAt(0, 7));

        
    }
}

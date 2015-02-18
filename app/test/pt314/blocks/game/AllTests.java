package pt314.blocks.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

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
        BufferedReader br = new BufferedReader(new FileReader("C://Users//Kam//Documents//blocks//app//res//puzzles//puzzle-003.txt"));
        String line = br.readLine();
        line=line.trim();
        String[] firstLineSplitted=line .split(" ");
        try{
            numRows=Integer.parseInt(firstLineSplitted[0]);
            numCols=Integer.parseInt(firstLineSplitted[1]);
        }
        catch (Exception e)
        {
            System.out.println( "Input Dimensions are not correct");
            return;
        }
        gameBoard=new GameBoard(numCols, numRows);
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
}

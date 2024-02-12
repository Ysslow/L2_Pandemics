import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import pandemics.Board;
import pandemics.ClassicalBoard;

public class ClassicalBoardTest {
	
	private Board board;
	
	@Before
	public void before() throws FileNotFoundException {
		board = new ClassicalBoard("Map.json");
		ClassicalBoard.SetMAXCUBE(0);
	}
	
	@Test
	public void testGetNumbersStations() {
		assertEquals(board.getNumbersStations(), 0);
		this.board.getCities().get(0).setResearch(true);
		assertEquals(board.getNumbersStations(), 1);
	}

	@Test
	public void testMoveStation() {
		this.board.getCities().get(0).setResearch(true);
		boolean res = this.board.moveStation(this.board.getCities().get(0), this.board.getCities().get(6));
		assertEquals(res, false);
		this.board.getCities().get(1).setResearch(true);
		this.board.getCities().get(2).setResearch(true);
		this.board.getCities().get(3).setResearch(true);
		this.board.getCities().get(4).setResearch(true);
		this.board.getCities().get(5).setResearch(true);
		res = this.board.moveStation(this.board.getCities().get(5), this.board.getCities().get(6));
		assertEquals(res, true);
	}
	
	@Test
	public void testSetMAXCUBE() {
		assertEquals(ClassicalBoard.MAXCUBE, 0);
		ClassicalBoard.SetMAXCUBE(3);
		assertEquals(ClassicalBoard.MAXCUBE, 3);
	}
}

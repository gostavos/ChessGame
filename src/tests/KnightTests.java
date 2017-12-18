package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Color;
import game.Type;
import pieces.*;

public class KnightTests {

	@Test
	public void rightAndUpMovement() throws Exception{
		Piece knight = new Knight(Color.WHITE, Type.KNIGHT);
		boolean validMove = knight.isValidMovementForPiece(0, 0, 1, 2);
		assertEquals(validMove, true);
	}
	
	@Test
	public void upAndRightMovement() throws Exception{
		Piece knight = new Knight(Color.WHITE, Type.KNIGHT);
		boolean validMove = knight.isValidMovementForPiece(5, 5, 7, 6);
		assertEquals(validMove, true);
	}
	
	@Test
	public void downAndLeftMovement() throws Exception{
		Piece knight = new Knight(Color.WHITE, Type.KNIGHT);
		boolean validMove = knight.isValidMovementForPiece(4, 4, 2, 3);
		assertEquals(validMove, true);
	}
	
	@Test
	public void rightAndDownMovement() throws Exception{
		Piece knight = new Knight(Color.WHITE, Type.KNIGHT);
		boolean validMove = knight.isValidMovementForPiece(4, 4, 3, 6);
		assertEquals(validMove, true);
	}
	
	@Test
	public void tooFarRightMovement() throws Exception{
		Piece knight = new Knight(Color.WHITE, Type.KNIGHT);
		boolean validMove = knight.isValidMovementForPiece(3, 3, 2, 6);
		assertEquals(validMove, false);
	}
	
	@Test
	public void tooFarUpMovement() throws Exception{
		Piece knight = new Knight(Color.WHITE, Type.KNIGHT);
		boolean validMove = knight.isValidMovementForPiece(1, 1, 4, 2);
		assertEquals(validMove, false);
	}

}

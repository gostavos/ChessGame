package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import game.*;
import pieces.*;

public class KingTests {

	@Test
	public void moveLeft() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 5, 4);
		assertEquals(validMove, true);
	}
	
	@Test
	public void moveUpLeft() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 4, 4);
		assertEquals(validMove, true);
	}
	
	@Test
	public void moveUp() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 4, 5);
		assertEquals(validMove, true);
	}
	
	@Test
	public void moveUpRight() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 4, 6);
		assertEquals(validMove, true);
	}
	
	@Test
	public void moveRight() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 5, 6);
		assertEquals(validMove, true);
	}
	
	@Test
	public void moveDownRight() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 6, 6);
		assertEquals(validMove, true);
	}
	
	@Test
	public void moveDown() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 6, 5);
		assertEquals(validMove, true);
	}
	
	@Test
	public void moveDownLeft() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 6, 4);
		assertEquals(validMove, true);
	}
	
	@Test
	public void move2Down() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 7, 5);
		assertEquals(validMove, false);
	}
	
	@Test
	public void move2Left() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 5, 3);
		assertEquals(validMove, false);
	}
	
	@Test
	public void move2Up() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 3, 5);
		assertEquals(validMove, false);
	}
	
	@Test
	public void move2Right() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 5, 7);
		assertEquals(validMove, false);
	}
	
	@Test
	public void moveDiagonallyTooFarRight() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 7, 7);
		assertEquals(validMove, false);
	}
	
	@Test
	public void moveDiagonallyTooFarLeft() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 3, 3);
		assertEquals(validMove, false);
	}
	
	@Test
	public void moveDiagonallyTooFarDownLeft() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 7, 3);
		assertEquals(validMove, false);
	}
	
	@Test
	public void mvoeTooHorseLike() throws Exception{
		Piece king = new King(Color.WHITE, Type.PAWN);
		boolean validMove = king.isValidMovementForPiece(5, 5, 7, 6);
		assertEquals(validMove, false);
	}

}
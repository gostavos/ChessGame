package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Color;
import game.Type;
import pieces.*;


public class BishopTests {

	@Test
	public void diagonalUpRightMovement() throws Exception{
		Piece bishop = new Bishop(Color.WHITE, Type.BISHOP);
		boolean validMove = bishop.isValidMovementForPiece(1, 1, 3, 3);
		assertEquals(validMove, true);
	}
	
	@Test
	public void diagonalUpLeftMovement() throws Exception{
		Piece bishop = new Bishop(Color.WHITE, Type.BISHOP);
		boolean validMove = bishop.isValidMovementForPiece(3, 3, 1, 1);
		assertEquals(validMove, true);
	}
	
	@Test
	public void diagonalDownLeftMovement() throws Exception{
		Piece bishop = new Bishop(Color.WHITE, Type.BISHOP);
		boolean validMove = bishop.isValidMovementForPiece(5, 5, 7, 3);
		assertEquals(validMove, true);
	}
	
	@Test
	public void diagonalDownRightMovement() throws Exception{
		Piece bishop = new Bishop(Color.WHITE, Type.BISHOP);
		boolean validMove = bishop.isValidMovementForPiece(5, 5, 7, 7);
		assertEquals(validMove, true);
	}
	
	@Test
	public void verticalUpMovement() throws Exception{
		Piece bishop = new Bishop(Color.WHITE, Type.BISHOP);
		boolean validMove = bishop.isValidMovementForPiece(5, 5, 3, 5);
		assertEquals(validMove, false);
	}
	
	@Test
	public void verticalDownMovement() throws Exception{
		Piece bishop = new Bishop(Color.WHITE, Type.BISHOP);
		boolean validMove = bishop.isValidMovementForPiece(5, 5, 7, 5);
		assertEquals(validMove, false);
	}
	
	@Test
	public void horizontalRightMovement() throws Exception{
		Piece bishop = new Bishop(Color.WHITE, Type.BISHOP);
		boolean validMove = bishop.isValidMovementForPiece(5, 5, 5, 7);
		assertEquals(validMove, false);
	}
	
	@Test
	public void horizontalLeftMovement() throws Exception{
		Piece bishop = new Bishop(Color.WHITE, Type.BISHOP);
		boolean validMove = bishop.isValidMovementForPiece(5, 5, 5, 3);
		assertEquals(validMove, false);
	}

}

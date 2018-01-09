package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import board.Tile;
import game.Chess;
import game.Color;
import game.Type;
import pieces.*;

public class PawnTests {

//	@Test
//	public void whitePawnGoingUp() throws Exception{
//		Piece pawn = new Pawn(Color.WHITE, Type.PAWN);
//		boolean validMove = pawn.isValidMovementForPiece(7, 0, 6, 0);
//		assertEquals(validMove, true);
//	}
//	
//	@Test
//	public void whitePawnGoing2Steps() throws Exception{
//		Piece pawn = new Pawn(Color.WHITE, Type.PAWN);
//		boolean validMove = pawn.isValidMovementForPiece(7, 0, 5, 0);
//		assertEquals(validMove, true);
//	}
//	
//	@Test
//	public void whitePawnAttackRight() throws Exception{
//		Piece pawn = new Pawn(Color.WHITE, Type.PAWN);
//		boolean validMove = pawn.isValidMovementForPiece(7, 0, 6, 1);
//		assertEquals(validMove, true);
//	}
//	
//	@Test
//	public void whitePawnGoing2Steps2Times() throws Exception{
//		Piece pawn = new Pawn(Color.WHITE, Type.PAWN);
//		boolean validFirstMove = pawn.isValidMovementForPiece(7, 0, 5, 0);
//		boolean validSecondMove = pawn.isValidMovementForPiece(5, 0, 3, 0);
//		assertEquals(validFirstMove, true);
//		assertEquals(validSecondMove, false);
//	}
//	
//	@Test
//	public void whitePawnGoing2StepsUp1StepRight() throws Exception{
//		Piece pawn = new Pawn(Color.WHITE, Type.PAWN);
//		boolean validMove = pawn.isValidMovementForPiece(7, 0, 5, 1);
//		assertEquals(validMove, false);
//	}
//	
//	@Test
//	public void blackPawnGoingDown() throws Exception{
//		Piece pawn = new Pawn(Color.BLACK, Type.PAWN);
//		boolean validMove = pawn.isValidMovementForPiece(1, 4, 2, 4);
//		assertEquals(validMove, true);
//	}
//	
//	@Test
//	public void blackPawnGoing2Steps() throws Exception{
//		Piece pawn = new Pawn(Color.BLACK, Type.PAWN);
//		boolean validMove = pawn.isValidMovementForPiece(1, 4, 3, 4);
//		assertEquals(validMove, true);
//	}
//	
//	@Test
//	public void blackPawnAttackLeft() throws Exception{
//		Piece pawn = new Pawn(Color.BLACK, Type.PAWN);
//		boolean validMove = pawn.isValidMovementForPiece(1, 4, 2, 3);
//		assertEquals(validMove, true);
//	}
//	
//	@Test
//	public void blackPawnGoing1StepThen2Steps() throws Exception{
//		Piece pawn = new Pawn(Color.BLACK, Type.PAWN);
//		boolean validFirstMove = pawn.isValidMovementForPiece(1, 4, 2, 4);
//		boolean validSecondMove = pawn.isValidMovementForPiece(2, 4, 4, 4);
//		assertEquals(validFirstMove, true);
//		assertEquals(validSecondMove, false);
//	}
//	
//	@Test
//	public void blackPawnGoingWrongWay() throws Exception{
//		Piece pawn = new Pawn(Color.BLACK, Type.PAWN);
//		boolean validMove = pawn.isValidMovementForPiece(5, 0, 4, 0);
//		assertEquals(validMove, false);
//	}
//	
//	@Test
//	public void whitePawnGoingWrongWay() throws Exception{
//		Piece pawn = new Pawn(Color.WHITE, Type.PAWN);
//		boolean validMove = pawn.isValidMovementForPiece(2, 1, 3, 1);
//		assertEquals(validMove, false);
//	}
//	
//	@Test
//	public void whitePawnTooFarAttack() throws Exception{
//		Piece pawn = new Pawn(Color.WHITE, Type.PAWN);
//		boolean validMove = pawn.isValidMovementForPiece(2, 1, 1, 3);
//		assertEquals(validMove, false);
//	}
//	
//	@Test
//	public void blackPawnTooFarAttack() throws Exception{
//		Piece pawn = new Pawn(Color.BLACK, Type.PAWN);
//		boolean validMove = pawn.isValidMovementForPiece(1, 1, 2, 3);
//		assertEquals(validMove, false);
//	}
	
	@Test
	public void whitePawnMoveOneForward() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Tile pawnTile = chessBoard[6][1];
		Pawn pawn = (Pawn)pawnTile.getPiece();
		assertEquals(chess.isValidPawnMove(pawn, 6, 1, 5, 1), true);
	}
	
	@Test
	public void whitePawnMoveOneBlocked() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Tile pawnTile = chessBoard[6][1];
		Pawn pawn = (Pawn)pawnTile.getPiece();
		
		Tile blockedTile = chessBoard[5][1];
		Piece blockingPiece = new Pawn(game.Color.WHITE, game.Type.PAWN);
		blockedTile.setPiece(blockingPiece);
		
		assertEquals(chess.isValidPawnMove(pawn, 6, 1, 5, 1), false);

	}
	
	@Test
	public void whitePawnBlockedByEnemy() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Tile pawnTile = chessBoard[6][1];
		Pawn pawn = (Pawn)pawnTile.getPiece();
		
		Tile blockedTile = chessBoard[5][1];
		Piece blockingPiece = new Pawn(game.Color.BLACK, game.Type.PAWN);	
		blockedTile.setPiece(blockingPiece);
		
		assertEquals(chess.isValidPawnMove(pawn, 6, 1, 5, 1), false);
	}

}

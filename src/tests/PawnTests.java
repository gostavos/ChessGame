package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import board.Tile;
import game.Chess;
import game.Color;
import game.Type;
import pieces.*;

public class PawnTests {
	
	@Test
	public void whitePawnMoveOneForward() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Tile pawnTile = chessBoard[6][1];
		Pawn pawn = (Pawn)pawnTile.getPiece();
		assertEquals(chess.isValidPawnMove(chessBoard, pawn, 6, 1, 5, 1), true);
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
		
		assertEquals(chess.isValidPawnMove(chessBoard, pawn, 6, 1, 5, 1), false);

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
		
		assertEquals(chess.isValidPawnMove(chessBoard, pawn, 6, 1, 5, 1), false);
	}
	
	@Test
	public void allow2StepsFirstMoveThenNot() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		
		chess.movePiece(chessBoard[6][0], chessBoard[4][0]);
		assertEquals(chess.isValidPath(chessBoard[4][0], chessBoard[2][0], chessBoard), false);
	}

}

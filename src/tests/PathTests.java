package tests;

import static org.junit.Assert.*;

import org.junit.Test;


import board.Tile;
import game.Chess;
import game.Type;
import pieces.*;

public class PathTests {
	
	@Test
	public void friendlyInWayHorizontalTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Piece rook = new Rook(game.Color.WHITE, game.Type.ROOK);
		Piece blockingPiece = new Pawn(game.Color.WHITE, game.Type.PAWN);
		
		Tile rookTile = chessBoard[0][7];
		Tile blockingTile = chessBoard[0][5];
		
		rookTile.setPiece(rook);
		blockingTile.setPiece(blockingPiece);
		
		assertEquals(chess.straightLinePathIsClear(chessBoard, game.Color.WHITE, 0, 7, 0, 3), false);
	}
	
	@Test
	public void friendlyInWayVerticalTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();

		Piece blockingPiece = new Pawn(game.Color.WHITE, game.Type.PAWN);
		Tile blockingTile = chessBoard[4][2];
		
		blockingTile.setPiece(blockingPiece);
		
		assertEquals(chess.straightLinePathIsClear(chessBoard, game.Color.WHITE,2, 2, 5, 2), false);
	}
	
	@Test
	public void horizontalPathIsClearTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		
		assertEquals(chess.straightLinePathIsClear(chessBoard, game.Color.WHITE,4, 0, 4, 4), true);
	}
	
	@Test
	public void verticalPathIsClear() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		
		assertEquals(chess.straightLinePathIsClear(chessBoard, game.Color.WHITE,2, 3, 4, 3), true);
	}
	
	@Test
	public void diagonalUpRightBlockedTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Piece blockingPiece = new Pawn(game.Color.WHITE, game.Type.PAWN);
		Tile blockingTile = chessBoard[3][3];
		blockingTile.setPiece(blockingPiece);
		
		assertEquals(chess.diagonalPathIsClear(chessBoard, game.Color.WHITE,5, 1, 2, 4), false);
	}
	
	@Test
	public void diagonalUpRightClearTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		
		assertEquals(chess.diagonalPathIsClear(chessBoard, game.Color.WHITE,5, 1, 2, 4), true);

	}
	
	@Test 
	public void diagonalDownRightBlockedTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Piece blockingPiece = new Pawn(game.Color.WHITE, game.Type.PAWN);
		Tile blockingTile = chessBoard[3][3];
		blockingTile.setPiece(blockingPiece);
		
		assertEquals(chess.diagonalPathIsClear(chessBoard, game.Color.WHITE,2, 2, 4, 4), false);
	}
	
	@Test
	public void diagonalDownRightClearTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		
		assertEquals(chess.diagonalPathIsClear(chessBoard, game.Color.WHITE,2, 2, 4, 4), true);
	}
	
	@Test
	public void diagonalUpLeftBlockedTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Piece blockingPiece = new Pawn(game.Color.WHITE, game.Type.PAWN);
		Tile blockingTile = chessBoard[3][3];
		blockingTile.setPiece(blockingPiece);
		
		assertEquals(chess.diagonalPathIsClear(chessBoard, game.Color.WHITE,5, 5, 2, 2), false);
	}
	
	@Test
	public void diagonalUpLeftClearTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();

		assertEquals(chess.diagonalPathIsClear(chessBoard, game.Color.WHITE,5, 5, 2, 2), true);
	}
	
	@Test
	public void diagonalDownLeftBlockedTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Piece blockingPiece = new Pawn(game.Color.WHITE, game.Type.PAWN);
		Tile blockingTile = chessBoard[3][3];
		blockingTile.setPiece(blockingPiece);
		
		assertEquals(chess.diagonalPathIsClear(chessBoard, game.Color.WHITE,1, 5, 4, 2), false);
	}
	
	@Test
	public void diagonalDownLeftClearTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		
		assertEquals(chess.diagonalPathIsClear(chessBoard, game.Color.WHITE,2, 4, 4, 2), true);
	}
	
	@Test
	public void endTileBlockedByFriendlyTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Piece blockingPiece = new Pawn(game.Color.WHITE, game.Type.PAWN);
		Tile blockingTile = chessBoard[3][3];
		blockingTile.setPiece(blockingPiece);
		
		assertEquals(chess.pieceCanMoveToCurrentTile(chessBoard, game.Color.WHITE, 3, 3, 3, 3), false);
	}
	
	@Test
	public void endTileIsUnoccupiedTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		
		assertEquals(chess.pieceCanMoveToCurrentTile(chessBoard, game.Color.WHITE, 3, 3, 3, 3), true);
	}
	
	@Test
	public void currentTileIsOccupiedTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Piece blockingPiece = new Pawn(game.Color.WHITE, game.Type.PAWN);
		Tile blockingTile = chessBoard[3][3];
		blockingTile.setPiece(blockingPiece);
		
		assertEquals(chess.pieceCanMoveToCurrentTile(chessBoard, game.Color.WHITE, 3, 3, 2, 4), false);
	}
	
	

}

package tests;

import static org.junit.Assert.*;
import pieces.*;

import org.junit.Test;

import board.Tile;
import game.Chess;
import game.Color;

public class StraightLineTests {

	@Test
	public void nextTileRightIsFriendlyTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Piece rook = new Rook(game.Color.WHITE, game.Type.ROOK);
		Piece pawn = new Pawn(game.Color.WHITE, game.Type.PAWN);
		
		chessBoard[4][2].setPiece(rook);
		chessBoard[4][3].setPiece(pawn);
		
		assertEquals(chess.straightLinePathIsClear(chessBoard, rook.getColor(), 4, 2, 4, 3), false);
	}
	
	@Test
	public void nextTileRightIsEmptyTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Piece rook = new Rook(game.Color.WHITE, game.Type.ROOK);
		
		chessBoard[4][2].setPiece(rook);
		
		assertEquals(chess.straightLinePathIsClear(chessBoard, rook.getColor(), 4, 2, 4, 3), true);
	}
	
	@Test
	public void rowIsClearRightTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Piece rook = new Rook(game.Color.WHITE, game.Type.ROOK);
		
		chessBoard[4][0].setPiece(rook);
		
		assertEquals(chess.straightLinePathIsClear(chessBoard, rook.getColor(), 4, 0, 4, 7), true);
	}
	
	@Test
	public void nextTileLeftIsFriendlyTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Piece pawn = new Pawn(game.Color.WHITE, game.Type.ROOK);
		
		chessBoard[4][3].setPiece(pawn);
		
		assertEquals(chess.straightLinePathIsClear(chessBoard, game.Color.WHITE, 4, 4, 4, 3), false);
	}
	
	@Test
	public void nextTileLeftIsEmptyTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		assertEquals(chess.straightLinePathIsClear(chessBoard, game.Color.WHITE, 4, 4, 4, 3), true);
	}
	
	@Test
	public void rowIsClearLeftTest() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		assertEquals(chess.straightLinePathIsClear(chessBoard, game.Color.WHITE, 4, 7, 4, 0), true);
	}
	
	@Test
	public void pieceFurthestToTheLeft() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
	
		Piece pawn = new Pawn(game.Color.WHITE, game.Type.PAWN);
		chessBoard[4][0].setPiece(pawn);
		
		assertEquals(chess.straightLinePathIsClear(chessBoard, game.Color.WHITE, 4, 7, 4, 0), false);
	}
	
	@Test
	public void pieceFurthestToTheRight() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
	
		Piece pawn = new Pawn(game.Color.WHITE, game.Type.PAWN);
		chessBoard[4][7].setPiece(pawn);
		
		assertEquals(chess.straightLinePathIsClear(chessBoard, game.Color.WHITE, 4, 0, 4, 7), false);
	}
	
	@Test
	public void pieceInWayOfPathLeft() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Piece pawn = new Pawn(game.Color.WHITE, game.Type.PAWN);
		
		chessBoard[4][2].setPiece(pawn);
		
		assertEquals(chess.straightLinePathIsClear(chessBoard, game.Color.WHITE, 4, 7, 4, 0), false);
	}
	
	@Test
	public void pieceInWayOfPathRight() throws Exception{
		Chess chess = new Chess();
		Tile[][] chessBoard = chess.populateBoard();
		Piece pawn = new Pawn(game.Color.WHITE, game.Type.PAWN);
		
		chessBoard[4][2].setPiece(pawn);
		assertEquals(chess.straightLinePathIsClear(chessBoard, game.Color.WHITE, 4, 0, 4, 7), false);
	}

}

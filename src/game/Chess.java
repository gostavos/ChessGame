package game;

import board.Board;
import board.Tile;
import pieces.*;

public class Chess {
	
	private Board board;
	private Tile[][] chessBoard;
	private Color color;
	private Type type;
	
	public Chess(){
		board = new Board();
		chessBoard = board.getBoard();
		populateBoard();
		
	}
	
	public void populateBoard(){
		addWhitePieces(); //adds white pieces
		addBlackPieces(); //adds black pieces
		addUnoccupiedTiles(); //adds rest of the tiles
	}
	
//	public void moveTest(){
//		Tile startTile = chessBoard[6][1];
//		Tile endTile = chessBoard[5][1];
//		board.moveTo(startTile, endTile);
//	}
	
	public void addWhitePieces(){
		for(int i = 0; i < 8; i++){
			Piece pawn = new Pawn(color.WHITE, type.PAWN);
			Tile tile = new Tile(6, i, pawn); //adds pawn to row 6
			chessBoard[6][i] = tile; //adds pawn to that tile
		}
		
		for(int i = 0; i < 8; i+=7){
			Piece rook = new Rook(color.WHITE, type.ROOK);
			Tile tile = new Tile(7, i, rook); //adds 2 rooks
			chessBoard[7][i] = tile;
		}
		
		for(int i = 1; i < 8; i+=5){
			Piece knight = new Knight(color.WHITE, type.KNIGHT);
			Tile tile = new Tile(7, i, knight);
			chessBoard[7][i] = tile;
		}
		
		for(int i = 2; i < 8; i +=3){
			Piece bishop = new Bishop(color.WHITE, type.BISHOP);
			Tile tile = new Tile(7, i, bishop);
			chessBoard[7][i] = tile;
		}
		
		Piece queen = new Queen(color.WHITE, type.QUEEN);
		Piece king = new King(color.WHITE, type.KING);
		Tile tile = new Tile(7, 3, queen);
		Tile tile2 = new Tile(7, 4, king);
		chessBoard[7][3] = tile;
		chessBoard[7][4] = tile2;
	}
	
	public void addBlackPieces(){
		for(int i = 0; i < 8; i++){
			Piece pawn = new Pawn(color.BLACK, type.PAWN);
			Tile tile = new Tile(1, i, pawn);
			chessBoard[1][i] = tile;
		}
		
		for(int i = 0; i < 8; i+=7){
			Piece rook = new Rook(color.BLACK, type.ROOK);
			Tile tile = new Tile(0, i, rook);
			chessBoard[0][i] = tile;
		}
		
		for(int i = 1; i < 8; i +=5){
			Piece knight = new Knight(color.BLACK, type.KNIGHT);
			Tile tile = new Tile(0, i, knight);
			chessBoard[0][i] = tile;
		}
		
		for(int i = 2; i < 8; i +=3){
			Piece bishop = new Bishop(color.BLACK, type.BISHOP);
			Tile tile = new Tile(0, i, bishop);
			chessBoard[0][i] = tile;
		}
		
		Piece queen = new Queen(color.BLACK, type.QUEEN);
		Piece king = new King(color.BLACK, type.KING);
		Tile tile = new Tile(0, 3, queen);
		Tile tile2 = new Tile(0, 4, king);
		chessBoard[0][3] = tile;
		chessBoard[0][4] = tile2;
 	}

	public void addUnoccupiedTiles(){
		for(int row = 2; row < 6; row++){
			for(int col = 0; col < 8; col++){
				Tile tile = new Tile(row, col);
				chessBoard[row][col] = tile;
			}
		}
	}
	
	public void movePiece(Piece piece, Tile startTile, Tile endTile){
		if(piece.isValidMove(startTile, endTile)){
			if(endTile.isOccupied()){ //is an attack move
				//addera poäng eller nåt
			}
			board.moveTo(startTile, endTile);
		}
	}
	
	public Tile[][] getChessBoard(){
		return chessBoard;
	}
	
}

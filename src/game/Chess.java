package game;

import board.Board;
import board.Tile;
import pieces.*;


public class Chess {
	
	private Board board;
	private Tile[][] chessBoard;
	private Rules rules;
	
	public Chess(){
		board = new Board();
		chessBoard = board.getBoard();
		rules = new Rules(chessBoard);
		
	}
	
	public Tile[][] placePiecesOnStartingPositions(){
		removeAllPiecesFromBoard();
		addWhitePieces(); //adds white pieces
		addBlackPieces(); //adds black pieces
		return chessBoard;
	}
	
	public void removeAllPiecesFromBoard() {
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				chessBoard[row][col].setPiece(null);
			}
		}	
	}
	
//	public void moveTest(){
//		Tile startTile = chessBoard[6][1];
//		Tile endTile = chessBoard[5][1];
//		board.moveTo(startTile, endTile);
//	}
	
	public Tile[][] initiateChessBoard(){
		//returns double array [8][8] of empty tiles
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				Tile tile = new Tile(row, col);
				chessBoard[row][col] = tile;
			}
		}
		return chessBoard;
	}
	
	public void addWhitePieces(){
		for(int i = 0; i < 8; i++){
			Piece pawn = new Pawn(game.Color.WHITE, game.Type.PAWN);
			chessBoard[6][i].setPiece(pawn);
		}
		
		for(int i = 0; i < 8; i+=7){
			Piece rook = new Rook(game.Color.WHITE, game.Type.ROOK);
			chessBoard[7][i].setPiece(rook);
		}
		
		for(int i = 1; i < 8; i+=5){
			Piece knight = new Knight(game.Color.WHITE, game.Type.KNIGHT);
			chessBoard[7][i].setPiece(knight);
		}
		
		for(int i = 2; i < 8; i +=3){
			Piece bishop = new Bishop(game.Color.WHITE, game.Type.BISHOP);
			chessBoard[7][i].setPiece(bishop);
		}
		
		Piece queen = new Queen(game.Color.WHITE, game.Type.QUEEN);
		Piece king = new King(game.Color.WHITE, game.Type.KING);

		chessBoard[7][3].setPiece(queen);
		chessBoard[7][4].setPiece(king);
	}
	
	public void addBlackPieces(){
		for(int i = 0; i < 8; i++){
			Piece pawn = new Pawn(game.Color.BLACK, game.Type.PAWN);
			chessBoard[1][i].setPiece(pawn);
		}
		
		for(int i = 0; i < 8; i+=7){
			Piece rook = new Rook(game.Color.BLACK, game.Type.ROOK);
			chessBoard[0][i].setPiece(rook);
		}
		
		for(int i = 1; i < 8; i +=5){
			Piece knight = new Knight(game.Color.BLACK, game.Type.KNIGHT);
			chessBoard[0][i].setPiece(knight);
		}
		
		for(int i = 2; i < 8; i +=3){
			Piece bishop = new Bishop(game.Color.BLACK, game.Type.BISHOP);
			chessBoard[0][i].setPiece(bishop);
		}
		
		Piece queen = new Queen(game.Color.BLACK, game.Type.QUEEN);
		Piece king = new King(game.Color.BLACK, game.Type.KING);
		chessBoard[0][3].setPiece(king);
		chessBoard[0][4].setPiece(queen);
 	}
	
	//Loads chessboard from a saved file	
	public void addLoadedDataForTile(Tile tile, String loadedData) {
		Piece piece = null;
		
		if(loadedData.contains("WHITE PAWN")) {
			piece = new Pawn(game.Color.WHITE, game.Type.PAWN);
		}else if(loadedData.contains("BLACK PAWN")) {
			 piece = new Pawn(game.Color.BLACK, game.Type.PAWN);
			
		}else if(loadedData.contains("WHITE ROOK")){
			 piece = new Rook(game.Color.WHITE, game.Type.ROOK);

		}else if(loadedData.contains("BLACK ROOK")) {
			 piece = new Rook(game.Color.BLACK, game.Type.ROOK);
			
		}else if(loadedData.contains("WHITE KING")) {
			 piece = new King(game.Color.WHITE, game.Type.KING);
		}else if(loadedData.contains("BLACK KING")) {
			 piece = new King(game.Color.BLACK, game.Type.KING);
		}
		
		else if(loadedData.contains("WHITE KNIGHT")){
			 piece = new Knight(game.Color.WHITE, game.Type.KNIGHT);
		}else if(loadedData.contains("BLACK KNIGHT")) {
			 piece = new Knight(game.Color.BLACK, game.Type.KNIGHT);
		}
		
		else if(loadedData.contains("WHITE BISHOP")) {
			 piece = new Bishop(game.Color.WHITE, game.Type.BISHOP);
		}else if(loadedData.contains("BLACK BISHOP")) {
			 piece = new Bishop(game.Color.BLACK, game.Type.BISHOP);
		}
		
		else if(loadedData.contains("WHITE QUEEN")) {
			 piece = new Queen(game.Color.WHITE, game.Type.QUEEN);
		}else if(loadedData.contains("BLACK QUEEN")) {
			 piece = new Queen(game.Color.BLACK, game.Type.QUEEN);
		}
		
		if(piece != null) {
			if(loadedData.contains("false")) {
				piece.setFirstMove(false);
			}
			tile.setPiece(piece);
		}
	}
	
	public Tile[][] getChessBoard(){
		return chessBoard;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public Rules getRules() {
		return rules;
	}
	
}

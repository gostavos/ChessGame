package game;

import board.Board;
import board.Tile;
import pieces.*;
import game.*;

public class Chess {
	
	private Board board;
	private Tile[][] chessBoard;
	private Color Color;
	private Type Type;
	
	public Chess(){
		board = new Board();
		chessBoard = board.getBoard();
		
	}
	
	public Tile[][] populateBoard(){
		addWhitePieces(); //adds white pieces
		addBlackPieces(); //adds black pieces
		addUnoccupiedTiles(); //adds rest of the tiles
		return chessBoard;
	}
	
//	public void moveTest(){
//		Tile startTile = chessBoard[6][1];
//		Tile endTile = chessBoard[5][1];
//		board.moveTo(startTile, endTile);
//	}
	
	public void addWhitePieces(){
		for(int i = 0; i < 8; i++){
			Piece pawn = new Pawn(Color.WHITE, Type.PAWN);
			Tile tile = new Tile(6, i, pawn); //adds pawn to row 6
			chessBoard[6][i] = tile; //adds pawn to that tile
		}
		
		for(int i = 0; i < 8; i+=7){
			Piece rook = new Rook(Color.WHITE, Type.ROOK);
			Tile tile = new Tile(7, i, rook); //adds 2 rooks
			chessBoard[7][i] = tile;
		}
		
		for(int i = 1; i < 8; i+=5){
			Piece knight = new Knight(Color.WHITE, Type.KNIGHT);
			Tile tile = new Tile(7, i, knight);
			chessBoard[7][i] = tile;
		}
		
		for(int i = 2; i < 8; i +=3){
			Piece bishop = new Bishop(Color.WHITE, Type.BISHOP);
			Tile tile = new Tile(7, i, bishop);
			chessBoard[7][i] = tile;
		}
		
		Piece queen = new Queen(Color.WHITE, Type.QUEEN);
		Piece king = new King(Color.WHITE, Type.KING);
		Tile tile = new Tile(7, 3, queen);
		Tile tile2 = new Tile(7, 4, king);
		chessBoard[7][3] = tile;
		chessBoard[7][4] = tile2;
	}
	
	public void addBlackPieces(){
		for(int i = 0; i < 8; i++){
			Piece pawn = new Pawn(Color.BLACK, Type.PAWN);
			Tile tile = new Tile(1, i, pawn);
			chessBoard[1][i] = tile;
		}
		
		for(int i = 0; i < 8; i+=7){
			Piece rook = new Rook(Color.BLACK, Type.ROOK);
			Tile tile = new Tile(0, i, rook);
			chessBoard[0][i] = tile;
		}
		
		for(int i = 1; i < 8; i +=5){
			Piece knight = new Knight(Color.BLACK, Type.KNIGHT);
			Tile tile = new Tile(0, i, knight);
			chessBoard[0][i] = tile;
		}
		
		for(int i = 2; i < 8; i +=3){
			Piece bishop = new Bishop(Color.BLACK, Type.BISHOP);
			Tile tile = new Tile(0, i, bishop);
			chessBoard[0][i] = tile;
		}
		
		Piece queen = new Queen(Color.BLACK, Type.QUEEN);
		Piece king = new King(Color.BLACK, Type.KING);
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
	
	public Tile[][] movePiece(Tile startTile, Tile endTile){
		
		endTile.setPiece(startTile.getPiece());
		startTile.setPiece(null);

		return chessBoard;
	}
	
	public boolean isValidPath(Tile startTile, Tile endTile){
		if(startTile.getPiece().isValidMovementForPiece(startTile.getY(), startTile.getX(), endTile.getY(), endTile.getX())){
			Type type = startTile.getPiece().getType();
			if(type == game.Type.ROOK || type == game.Type.QUEEN ){
				if(straightLinePathIsClear(startTile.getPiece().getColor(), startTile.getY(), startTile.getX(), endTile.getY(), endTile.getX())) {
					//do something
					return true;
				}
			}
			
			if(type == game.Type.BISHOP || type == game.Type.QUEEN){
				if(diagonalPathIsClear(startTile.getPiece().getColor(), startTile.getY(), startTile.getX(), endTile.getY(), endTile.getX())) {
					//do something
					return true;
				}
			}
			
			if(type == game.Type.PAWN) {
				Pawn pawn = (Pawn)startTile.getPiece();
				if(isValidPawnMove(pawn, startTile.getY(), startTile.getX(), endTile.getY(), endTile.getX())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isValidPawnMove(Pawn pawn, int startY, int startX, int endY, int endX) {
		
		if(pawn.getColor() == game.Color.WHITE && startY - endY == 1 && Math.abs(startX - endX) == 1 && isTileOccupiedByEnemy(chessBoard[endY][endX], pawn.getColor()) ||
				pawn.getColor() == game.Color.BLACK && endY - startY == 1 && Math.abs(startX - endX) == 1 && isTileOccupiedByEnemy(chessBoard[endY][endX], pawn.getColor())) {
			//pawn attack-move
			return true;
			
		}
		
		if(startX == endX) {
			if(pawn.isFirstMove() && pawn.getColor() == game.Color.WHITE && startY - endY == 2 ||
					pawn.isFirstMove() && pawn.getColor() == game.Color.BLACK && endY - startY == 2) {
				//pawn 2 steps on first move
				//method straigtLinePathIsClear doesn't work fully since pawns can only attack diagonally (which that method doesn't check for)
				if(straightLinePathIsClear(pawn.getColor(), startY, startX, endY, endX) && !isOccupiedTile(chessBoard[endY][endX])) {
					return true;
				}
			}else if(pawn.getColor() == game.Color.WHITE && startY - endY == 1 ||
						pawn.getColor() == game.Color.BLACK && endY - startY == 1) {
				//pawn normal move
				return !isOccupiedTile(chessBoard[endY][endX]);
			}
		}

		return false;
	}
	
	/*
	 * returns true if no piece (regardless of color) is blocking the path AND
	 * the end tile is either unoccupied or occupied by an enemy (considered an attack)
	 */
	public boolean straightLinePathIsClear(game.Color teamColor, int startY, int startX, int endY, int endX){
		if(isVerticalMovement(startX, endX)){ 
			int yDir = startY - endY; // if yDir is less than 0: direction is down, and vice versa	
			if(yDir < 0){
				//for loop must start on the first tile to be traversed to avoid it confusing
				//itself for an occupied tile (start tile is always occupied)
				for(int i = ++startY; i < endY; i++){
					if(!pieceCanMoveToCurrentTile(teamColor, i, startX, endY, endX))
						return false;
				}
			}else{
				for(int i = --startY; i > endY; i--){
					if(!pieceCanMoveToCurrentTile(teamColor, i, startX, endY, endX))
						return false;
				}
			}
		}else if(isHorizontalMovement(startY, endY)) {
			int xDir = startX - endX;
			if(xDir < 0) { // if xDir is less than 0: direction is right
				for(int i = ++startX; i < endX; i++) {
					if(!pieceCanMoveToCurrentTile(teamColor, startY, i, endY, endX))
						return false;				
				}
			}else {
				for(int i = --startX; i > endX; i--) {
					if(!pieceCanMoveToCurrentTile(teamColor, startY, i, endY, endX))
						return false;				
				}
			}
		}
		return true;
	}
	
	/*
	 * returns true if no piece (regardless of color) is blocking the path AND
	 * the end tile is either unoccupied or occupied by an enemy (considered an attack)
	 */
	public boolean diagonalPathIsClear(game.Color teamColor, int startY, int startX, int endY, int endX) {
		int currentX = startX, currentY = startY;
		if(endY < startY && endX > startX) {
			//up right
			while(currentY != endY && currentX != endX) {
				currentY--;
				currentX++;
				if(!pieceCanMoveToCurrentTile(teamColor, currentY, currentX, endY, endX))
					return false;

			}
		}else if(endY > startY && endX > startX) {
			//down right
			while(currentY != endY && currentX != endX) {
				currentY++;
				currentX++;
				if(!pieceCanMoveToCurrentTile(teamColor, currentY, currentX, endY, endX))
					return false;

			}
		}else if(endY > startY && endX < startX) {
			//down left
			while(currentY != endY && currentX != endX) {
				currentY++;
				currentX--;
				if(!pieceCanMoveToCurrentTile(teamColor, currentY, currentX, endY, endX))
					return false;

			}
		}else if(endY < startY && endX < startX) {
			//up left
			while(currentY != endY && currentX != endX) {
				currentY--;
				currentX--;
				if(!pieceCanMoveToCurrentTile(teamColor, currentY, currentX, endY, endX))
					return false;

			}
		}
		
		return true;
	}
	
	public boolean pieceCanMoveToCurrentTile(game.Color teamColor, int currentY, int currentX, int endY, int endX) {
		if(isPathsEndPoint(currentY, currentX, endY, endX)) 
			return !isTileOccupiedByFriendly(chessBoard[currentY][currentX], teamColor);
		else
			return !isOccupiedTile(chessBoard[currentY][currentX]);
	}
	
	public boolean isPathsEndPoint(int currentY, int currentX, int endY, int endX) {
		return currentY == endY && currentX == endX;
	}
	
	public boolean isVerticalMovement(int startX, int endX) {
		return startX == endX;
	}
	
	public boolean isHorizontalMovement(int startY, int endY) {
		return startY == endY;
	}
	
	public void moveDiagonally(){
		
	}
	
	//returns true if tile is occupied by a friendly (same color) piece
	public boolean isTileOccupiedByFriendly(Tile tile, game.Color teamColor){
		if(tile.getPiece() != null) {
			return tile.getPiece().getColor() == teamColor;
		}
		return false;
	}
	
	public boolean isTileOccupiedByEnemy(Tile tile, game.Color teamColor) {
		if(tile.getPiece() != null) {
			return tile.getPiece().getColor() != teamColor;
		}
		return false;
	}
	
	public boolean isOccupiedTile(Tile tile) {
		return tile.getPiece() != null;
	}
	
	
	
	public Tile[][] getChessBoard(){
		return chessBoard;
	}
	
	public Board getBoard(){
		return board;
	}
	
}

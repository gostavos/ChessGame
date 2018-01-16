package game;

import board.Board;
import board.Tile;
import pieces.*;
import game.*;
import java.awt.Color;

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
			Piece pawn = new Pawn(game.Color.WHITE, game.Type.PAWN);
			Tile tile = new Tile(6, i, pawn); //adds pawn to row 6
			chessBoard[6][i] = tile; //adds pawn to that tile
		}
		
		for(int i = 0; i < 8; i+=7){
			Piece rook = new Rook(game.Color.WHITE, game.Type.ROOK);
			Tile tile = new Tile(7, i, rook); //adds 2 rooks
			chessBoard[7][i] = tile;
		}
		
		for(int i = 1; i < 8; i+=5){
			Piece knight = new Knight(game.Color.WHITE, game.Type.KNIGHT);
			Tile tile = new Tile(7, i, knight);
			chessBoard[7][i] = tile;
		}
		
		for(int i = 2; i < 8; i +=3){
			Piece bishop = new Bishop(game.Color.WHITE, game.Type.BISHOP);
			Tile tile = new Tile(7, i, bishop);
			chessBoard[7][i] = tile;
		}
		
		Piece queen = new Queen(game.Color.WHITE, game.Type.QUEEN);
		Piece king = new King(game.Color.WHITE, game.Type.KING);
		Tile tile = new Tile(7, 3, queen);
		Tile tile2 = new Tile(7, 4, king);
		chessBoard[7][3] = tile;
		chessBoard[7][4] = tile2;
	}
	
	public void addBlackPieces(){
		for(int i = 0; i < 8; i++){
			Piece pawn = new Pawn(game.Color.BLACK, game.Type.PAWN);
			Tile tile = new Tile(1, i, pawn);
			chessBoard[1][i] = tile;
		}
		
		for(int i = 0; i < 8; i+=7){
			Piece rook = new Rook(game.Color.BLACK, game.Type.ROOK);
			Tile tile = new Tile(0, i, rook);
			chessBoard[0][i] = tile;
		}
		
		for(int i = 1; i < 8; i +=5){
			Piece knight = new Knight(game.Color.BLACK, game.Type.KNIGHT);
			Tile tile = new Tile(0, i, knight);
			chessBoard[0][i] = tile;
		}
		
		for(int i = 2; i < 8; i +=3){
			Piece bishop = new Bishop(game.Color.BLACK, game.Type.BISHOP);
			Tile tile = new Tile(0, i, bishop);
			chessBoard[0][i] = tile;
		}
		
		Piece queen = new Queen(game.Color.BLACK, game.Type.QUEEN);
		Piece king = new King(game.Color.BLACK, game.Type.KING);
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
	
	//returns copy of both the 2d array (chessboard) and the objects inside it
	public Tile[][] copyBoard() {
		Tile[][] copiedBoard = new Tile[8][8];
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				Tile tile = chessBoard[row][col].clone();
				if(tile.getPiece()!=null) {
					Piece piece = tile.getPiece().clone();
					tile.setPiece(piece);
				}
				copiedBoard[row][col] = tile;
			}
		}
		return copiedBoard;
	}
	
	//creates copies of start and endtile and moves them to their proposed new positions
	public Tile[][] movePiecesOnCopiedBoard(Tile[][] copiedBoard, Tile startTile, Tile endTile){
		Tile copiedStartTile = startTile.clone();
		Tile copiedEndTile = endTile.clone();

		movePiece(copiedStartTile, copiedEndTile);
		
		copiedBoard[copiedStartTile.getY()][copiedStartTile.getX()] = copiedStartTile;
		copiedBoard[copiedEndTile.getY()][copiedEndTile.getX()] = copiedEndTile;
		return copiedBoard;
	}

	//returns true if an enemy piece has a valid path to friendly king
	public boolean pieceHasKingChecked(Tile[][] copiedBoard, Tile startTile, Tile kingTile, game.Color teamColor) {
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				Tile tile = copiedBoard[row][col];
				if(tile.getPiece() != null && tile.getPiece().getColor() != teamColor && isValidPath(tile, kingTile, copiedBoard)) {
					return true;
				}
			}
		}
		return false;
	}
	
	//moves pieces to new location according to player's move, then checks if king is checked
	public boolean isKingChecked(Tile startTile, Tile endTile) {
		game.Color teamColor = startTile.getPiece().getColor();
		
		Tile[][] copiedBoard = copyBoard();
		copiedBoard = movePiecesOnCopiedBoard(copiedBoard, copiedBoard[startTile.getY()][startTile.getX()], copiedBoard[endTile.getY()][endTile.getX()]);
		
		Tile kingTile = findKingsCurrentTile(teamColor, copiedBoard);
		
		return pieceHasKingChecked(copiedBoard, startTile, kingTile, teamColor);
	}
	
	//returns the tile where friendly king is standing
	public Tile findKingsCurrentTile(game.Color teamColor, Tile[][] copiedBoard) {
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				Tile tile = copiedBoard[row][col];
				if(tile.getPiece() != null && tile.getPiece().getType() == game.Type.KING && tile.getPiece().getColor() == teamColor) {
					return tile;
				}
			}
		}
		return null;
	}
	
	public void movePiece(Tile startTile, Tile endTile){
		setFirstMoveToFalse(startTile.getPiece());
		endTile.setPiece(startTile.getPiece());
		startTile.setPiece(null);

	}
	
	public void setFirstMoveToFalse(Piece piece){
		game.Type pieceType = piece.getType();
		if(pieceType == game.Type.PAWN ||
				pieceType == game.Type.ROOK || 
					pieceType == game.Type.KING) {
			piece.setFirstMove(false);
		}
			
	}
	
	//finds all valid moves piece can make, turns those tiles green
	public void findAllValidMoves(Tile startTile) {
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				Tile tile  = chessBoard[row][col];
				if(startTile != tile && isValidMove(startTile, tile, chessBoard)) {
					tile.setBackground(java.awt.Color.green);
				}
			}
		}
	}
	
	public boolean isValidMove(Tile startTile, Tile endTile, Tile[][] currentChessBoard) {
		return isValidPath(startTile, endTile, currentChessBoard) && !isKingChecked(startTile, endTile);
	}
	
	public boolean isValidPath(Tile startTile, Tile endTile, Tile[][] currentChessBoard){
		if(startTile.getPiece().isValidMovementForPiece(startTile.getY(), startTile.getX(), endTile.getY(), endTile.getX())){
			Type type = startTile.getPiece().getType();
			if(type == game.Type.ROOK){
				if(straightLinePathIsClear(currentChessBoard, startTile.getPiece().getColor(), startTile.getY(), startTile.getX(), endTile.getY(), endTile.getX())) {
					//do something
					return true;
				}
			}
			
			if(type == game.Type.BISHOP){
				if(diagonalPathIsClear(currentChessBoard, startTile.getPiece().getColor(), startTile.getY(), startTile.getX(), endTile.getY(), endTile.getX())) {
					//do something
					return true;
				}
			}
			
			if(type == game.Type.QUEEN) {
				return straightLinePathIsClear(currentChessBoard, startTile.getPiece().getColor(),startTile.getY(), startTile.getX(), endTile.getY(), endTile.getX()) ||
						diagonalPathIsClear(currentChessBoard, startTile.getPiece().getColor(), startTile.getY(), startTile.getX(), endTile.getY(), endTile.getX());
			}
			
			//returns true if tile is occupied by enemy or is unoccupied
			if(type == game.Type.KNIGHT || type == game.Type.KING) { 
				
				//neither king or knight has to worry about traversed tiles: knights jump and kings only move 1 tile
				return isTileOccupiedByEnemy(endTile, startTile.getPiece().getColor()) || !isOccupiedTile(endTile);
				
			}
			
			if(type == game.Type.PAWN) {
				Pawn pawn = (Pawn)startTile.getPiece();
				if(isValidPawnMove(currentChessBoard, pawn, startTile.getY(), startTile.getX(), endTile.getY(), endTile.getX())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isValidPawnMove(Tile[][] currentChessBoard, Pawn pawn, int startY, int startX, int endY, int endX) {
		
		if(pawn.getColor() == game.Color.WHITE && startY - endY == 1 && Math.abs(startX - endX) == 1 && isTileOccupiedByEnemy(currentChessBoard[endY][endX], pawn.getColor()) ||
				pawn.getColor() == game.Color.BLACK && endY - startY == 1 && Math.abs(startX - endX) == 1 && isTileOccupiedByEnemy(currentChessBoard[endY][endX], pawn.getColor())) {
			//pawn attack-move
			return true;
			
		}
		
		if(startX == endX) {
			if(pawn.isFirstMove() && pawn.getColor() == game.Color.WHITE && startY - endY == 2 ||
					pawn.isFirstMove() && pawn.getColor() == game.Color.BLACK && endY - startY == 2) {
				//pawn 2 steps on first move
				//method straigtLinePathIsClear doesn't work fully since pawns can only attack diagonally (which that method doesn't check for)
				if(straightLinePathIsClear(currentChessBoard, pawn.getColor(), startY, startX, endY, endX) && !isOccupiedTile(currentChessBoard[endY][endX])) {
					return true;
				}
			}else if(pawn.getColor() == game.Color.WHITE && startY - endY == 1 ||
						pawn.getColor() == game.Color.BLACK && endY - startY == 1) {
				//pawn normal move
				return !isOccupiedTile(currentChessBoard[endY][endX]);
			}
		}

		return false;
	}
	
	/*
	 * returns true if no piece (regardless of color) is blocking the path AND
	 * the end tile is either unoccupied or occupied by an enemy (considered an attack)
	 */
	public boolean straightLinePathIsClear(Tile[][] currentChessBoard, game.Color teamColor, int startY, int startX, int endY, int endX){
		if(isVerticalMovement(startX, endX)){ 
			int yDir = startY - endY; // if yDir is less than 0: direction is down, and vice versa	
			if(yDir < 0){
				//for loop must start on the first tile to be traversed to avoid it confusing
				//itself for an occupied tile (start tile is always occupied)
				for(int i = ++startY; i < endY+1; i++){
					if(!pieceCanMoveToCurrentTile(currentChessBoard, teamColor, i, startX, endY, endX))
						return false;
				}
				return true;
			}else{
				for(int i = --startY; i > endY-1; i--){
					if(!pieceCanMoveToCurrentTile(currentChessBoard, teamColor, i, startX, endY, endX))
						return false;
				}
				return true;
			}
		}else if(isHorizontalMovement(startY, endY)) {
			int xDir = startX - endX;
			if(xDir < 0) { // if xDir is less than 0: direction is right
				for(int i = ++startX; i < endX+1; i++) {
					if(!pieceCanMoveToCurrentTile(currentChessBoard, teamColor, startY, i, endY, endX))
						return false;				
				}
				return true;
			}else {
				for(int i = --startX; i > endX-1; i--) {
					if(!pieceCanMoveToCurrentTile(currentChessBoard, teamColor, startY, i, endY, endX))
						return false;				
				}
				return true;
			}
		}
		return false;
	}
	
	/*
	 * returns true if no piece (regardless of color) is blocking the path AND
	 * the end tile is either unoccupied or occupied by an enemy (considered an attack)
	 */
	public boolean diagonalPathIsClear(Tile[][] currentChessBoard, game.Color teamColor, int startY, int startX, int endY, int endX) {
		int currentX = startX, currentY = startY;
		if(endY < startY && endX > startX) {
			//up right
			while(currentY != endY && currentX != endX) {
				currentY--;
				currentX++;
				if(!pieceCanMoveToCurrentTile(currentChessBoard, teamColor, currentY, currentX, endY, endX))
					return false;

			}
			return true;
		}else if(endY > startY && endX > startX) {
			//down right
			while(currentY != endY && currentX != endX) {
				currentY++;
				currentX++;
				if(!pieceCanMoveToCurrentTile(currentChessBoard, teamColor, currentY, currentX, endY, endX))
					return false;

			}
			return true;
		}else if(endY > startY && endX < startX) {
			//down left
			while(currentY != endY && currentX != endX) {
				currentY++;
				currentX--;
				if(!pieceCanMoveToCurrentTile(currentChessBoard, teamColor, currentY, currentX, endY, endX))
					return false;

			}
			return true;
		}else if(endY < startY && endX < startX) {
			//up left
			while(currentY != endY && currentX != endX) {
				currentY--;
				currentX--;
				if(!pieceCanMoveToCurrentTile(currentChessBoard, teamColor, currentY, currentX, endY, endX))
					return false;

			}
			return true;
		}
		
		return false;
	}
	
	public boolean pieceCanMoveToCurrentTile(Tile[][] currentChessBoard, game.Color teamColor, int currentY, int currentX, int endY, int endX) {
		if(isPathsEndPoint(currentY, currentX, endY, endX)) 
			return !isTileOccupiedByFriendly(currentChessBoard[currentY][currentX], teamColor);
		else
			return !isOccupiedTile(currentChessBoard[currentY][currentX]);
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

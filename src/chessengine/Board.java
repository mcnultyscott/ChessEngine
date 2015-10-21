/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessengine;
import java.util.ArrayList;

/**
 *
 * @author Scott
 */

public class Board {
    private final String BLACK = "black";
    private final String WHITE = "white";
    private final int DIMENSION = 8;  
    final int SQUARE_WIDTH = 100;
    final int SQUARE_HEIGHT = 100;
    PieceTypeEnum typeOfPiece;
    Square target;
    
    // [ ] [ ] [2] [ ] [ ]
    // [7] [ ] [2] [ ] [4]
    // [ ] [7] [2] [4] [ ]
    // [0] [0] [X] [1] [1]
    // [ ] [5] [3] [6] [ ]
    // [5] [ ] [3] [ ] [6]
    // [ ] [ ] [3] [ ] [ ]
    //
    //  If X is the target square,
    //  0: Left of square
    //  1: Right of square
    //  2: Upward from square
    //  3: Downward from square
    //  4: Right up diagonal from square
    //  5: Left down diagonal from square
    //  6: Right down diagonal from square
    //  7: Left up diagonal from square 
    
    //                                 row  |  rank
    // [R] [N] [B] [Q] [K] [B] [N] [R]  0   |   8  
    // [P] [P] [P] [P] [P] [P] [P] [P]  1   |   7
    // [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]  2   |   6
    // [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]  3   |   5  
    // [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]  4   |   4 
    // [ ] [ ] [ ] [ ] [ ] [ ] [ ] [ ]  5   |   3
    // [P] [P] [P] [P] [P] [P] [P] [P]  6   |   2
    // [R] [N] [B] [Q] [K] [B] [N] [R]  7   |   1
    //
    //  0   1   2   3   4   5   6   7   |   column
    //  A   B   C   D   E   F   G   H   |   file
    
    Square[][] squares = new Square[8][8];
    //ArrayList<Square> squares = new ArrayList<Square>();
    //  0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
    // [P][P][P][P][P][P][P][P][N][N][B][B][R][R][Q][K] 
    ArrayList<Piece> whitePieces = new ArrayList<Piece>();
    ArrayList<Piece> blackPieces = new ArrayList<Piece>();
    
    public Board(){  
        initializeSquares();
        setUpNewGame();
    }
    
    public ArrayList<Piece> getWhitePieces(){
        return whitePieces;
    }

    public ArrayList<Piece> getBlackPieces(){
        return blackPieces;
    }
    
    // Sets fields in each Square object to correct value
    private void initializeSquares(){
        for (int i = 0; i < DIMENSION; i++){
            for (int j = 0; j < DIMENSION; j++){
                squares[i][j] = new Square(
                        i,
                        j,
                        j * SQUARE_WIDTH,
                        i * SQUARE_HEIGHT,
                        SQUARE_WIDTH,
                        SQUARE_HEIGHT);
                
                squares[i][j].setOccupied(false);
            }
        }
    }
    
    public void setUpNewGame(){
        whitePieces.clear();
        blackPieces.clear();
        clearSquares();
        
        makePawns();
        makeKnights();
        makeBishops();
        makeRooks();
        makeQueens();
        makeKings();
        
        setPawnsOnSquares();
        setWhiteOnSquares();
        setBishopsOnSquares();
        setRooksOnSquares();
        setQueenOnSquares();
        setWKingOnSquares();
    }
    
    // Create pawns, add to ArrayLists
    private void makePawns(){
        for (int i = 0; i < 8; i++){
            whitePieces.add(new Pawn(WHITE, typeOfPiece.PAWN));
            blackPieces.add(new Pawn(BLACK, typeOfPiece.PAWN));
        }
    }
    
    // Put pawns on starting squares
    private void setPawnsOnSquares(){
        for (int i = 0; i < 8; i++){
            
            // White pawns on second rank
            squares[6][i].setOccupied(true);
            squares[6][i].setOccupyingPiece(whitePieces.get(i));
            
            // Black pawns on seventh rank
            squares[1][i].setOccupied(true);
            squares[1][i].setOccupyingPiece(blackPieces.get(i));
        }
    }
    
    // Make knights, add to ArrayLists 
    private void makeKnights(){
        whitePieces.add(new Knight(WHITE, typeOfPiece.KNIGHT));
        whitePieces.add(new Knight(WHITE, typeOfPiece.KNIGHT));
        
        blackPieces.add(new Knight(BLACK, typeOfPiece.KNIGHT));
        blackPieces.add(new Knight(BLACK, typeOfPiece.KNIGHT));
    }
    
    // Put knights on starting squares
    private void setWhiteOnSquares(){
        squares[7][1].setOccupied(true);
        squares[7][1].setOccupyingPiece(whitePieces.get(8)); 
        squares[7][6].setOccupied(true);
        squares[7][6].setOccupyingPiece(whitePieces.get(9));
        
        squares[0][1].setOccupied(true);
        squares[0][1].setOccupyingPiece(blackPieces.get(8));
        squares[0][6].setOccupied(true);
        squares[0][6].setOccupyingPiece(blackPieces.get(9));
    }
    
    // Make bishops, add to ArrayLists
    private void makeBishops(){
        whitePieces.add(new Bishop(WHITE, typeOfPiece.BISHOP));
        whitePieces.add(new Bishop(WHITE, typeOfPiece.BISHOP));
        
        blackPieces.add(new Bishop(BLACK, typeOfPiece.BISHOP));
        blackPieces.add(new Bishop(BLACK, typeOfPiece.BISHOP));
    }
    
    // Putbishops on starting squares
    private void setBishopsOnSquares(){                
        squares[7][2].setOccupied(true);
        squares[7][2].setOccupyingPiece(whitePieces.get(10));
        squares[7][5].setOccupied(true);
        squares[7][5].setOccupyingPiece(whitePieces.get(11));
        
        squares[0][2].setOccupied(true);
        squares[0][2].setOccupyingPiece(blackPieces.get(10));
        squares[0][5].setOccupied(true);
        squares[0][5].setOccupyingPiece(blackPieces.get(11));
    }
    
    // Make Rooks, add to ArrayLists
    private void makeRooks(){
        whitePieces.add(new Rook(WHITE, typeOfPiece.ROOK));
        whitePieces.add(new Rook(WHITE, typeOfPiece.ROOK));
        
        blackPieces.add(new Rook(BLACK, typeOfPiece.ROOK));
        blackPieces.add(new Rook(BLACK, typeOfPiece.ROOK));
    }
    
    // Put rooks on starting squares
    private void setRooksOnSquares(){
        squares[7][0].setOccupied(true);
        squares[7][0].setOccupyingPiece(whitePieces.get(12)); 
        squares[7][7].setOccupied(true);
        squares[7][7].setOccupyingPiece(whitePieces.get(13)); 
        
        squares[0][0].setOccupied(true);
        squares[0][0].setOccupyingPiece(blackPieces.get(12)); 
        squares[0][7].setOccupied(true);
        squares[0][7].setOccupyingPiece(blackPieces.get(13)); 
    }
    
    // Make Queens, add to ArrayLists
    private void makeQueens(){
        whitePieces.add(new Queen(WHITE, typeOfPiece.QUEEN));
        blackPieces.add(new Queen(BLACK, typeOfPiece.QUEEN));
    }
    
    // Put queesn on starting squares
    private void setQueenOnSquares(){
        squares[7][3].setOccupied(true);
        squares[7][3].setOccupyingPiece(whitePieces.get(14));
        
        squares[0][3].setOccupied(true);
        squares[0][3].setOccupyingPiece(blackPieces.get(14));
    }
    
    // Make Kings, add to ArrayLists
    private void makeKings(){
        whitePieces.add(new King(WHITE, typeOfPiece.KING));
        blackPieces.add(new King(BLACK, typeOfPiece.KING));
    }
    
    // Put Kings on starting squares
    private void setWKingOnSquares(){
        squares[7][4].setOccupied(true);
        squares[7][4].setOccupyingPiece(whitePieces.get(15));
        
        squares[0][4].setOccupied(true);
        squares[0][4].setOccupyingPiece(blackPieces.get(15));
    }
    
    // Removes pieces from squares on board
    private void clearSquares(){
        for (int i = 0; i < 8; ++i){
            for (int j = 0; j < 8; ++j){
                target = squares[i][j];
                target.setOccupied(false);
                target.setOccupyingPiece(null);
            }
        }
    }
    
    // Move a piece to another square
    public void movePiece(Piece piece, Square toSquare){
        piece.getCurrentSquare().setOccupied(false);
        piece.getCurrentSquare().setOccupyingPiece(null);
        piece.setCurrentSquare(toSquare);
        toSquare.setOccupyingPiece(piece);
    }
    
    // Given a piece the possible squares the piece can
    // move to will be calculated.
    private void calcMovementSquares (Piece piece){
        ArrayList<Square> moves = new ArrayList<Square>();
        
        switch (piece.getPieceType()){
            case PAWN: 
                calcPawnMoves(piece, moves);
                break;
            
            case KNIGHT:
                calcKnightMoves(piece, moves);
                break;
                
            case BISHOP:
                calcBishopMoves(piece, moves);
                break;
                
            case ROOK:
                calcRookMoves(piece, moves);
                break;
                
            case QUEEN:
                calcQueenMoves(piece, moves);
                break;
                
            case KING:
                calcKingMoves(piece, moves);
                break;
        }
    }
    
    private void calcPawnMoves(Piece piece, ArrayList<Square> moves){
        target = piece.getCurrentSquare();
        int row = target.getRow();
        int column = target.getColumn();
        Pawn pawn = (Pawn) piece;
        
        if (pawn.getColor().equals(WHITE)){
            switch(row){
                case 1:
                    moves.add(squares[row+1][column]);
                    moves.add(squares[row+2][column]);
                    pawn.setEnPassantable(true);
                    break;
                case 7:
                    break;
                default:
                    moves.add(squares[row+1][column]);
                    break;
            }
        }
        else{
            switch(row){
                case 6:
                    moves.add(squares[row-1][column]);
                    moves.add(squares[row-2][column]);
                    pawn.setEnPassantable(true);
                    break;
                case 0:
                    break;
                default:
                    moves.add(squares[row-1][column]);
                    break;
            }
        }
        
        // Give possibe moves to piece
        pawn.setPossibleMoves(moves);
    }
    
    // TODO: This needs to be better; it looks very ugly.
    // Think of better way to get the potential moves.
    private void calcKnightMoves(Piece piece, ArrayList<Square> moves){
        target = piece.getCurrentSquare();
        int row = target.getRow();
        int column = target.getColumn();    
        
        int[] newUpDownRows = {row + 2, row - 2};
        int[] newLeftRightRows = {row + 1, row - 1};
        int[] newUpDownColumns = {column + 2, column - 2};
        int[] newLeftRightColumns = {column + 1, column - 1};
        
        for (int i = 0; i < newUpDownRows.length; i++){
            for (int j = 0; j < newLeftRightColumns.length; j++){
                if (newUpDownRows[i] <= DIMENSION && newLeftRightColumns[j] <= DIMENSION){
                    if (squares[newUpDownRows[i]][newLeftRightColumns[j]].getOccupied()){
                        moves.add(squares[newUpDownRows[i]][newLeftRightColumns[j]]);
                    }
                }
            }
        }
        
        for (int i = 0; i < newLeftRightRows.length; i++){
            for (int j = 0; j < newUpDownColumns.length; j++){
                if (newLeftRightRows[i] <= DIMENSION && newUpDownColumns[j] <= DIMENSION){
                    if (squares[newLeftRightRows[i]][newUpDownColumns[j]].getOccupied()){
                        moves.add(squares[newLeftRightRows[i]][newUpDownColumns[j]]);
                    }
                }
            }
        }
    }
    
    private void calcBishopMoves(Piece piece, ArrayList<Square> moves){
        target = piece.getCurrentSquare();
        
        // Add all possible squares left up diagonal from piece
        moves.addAll(collectPossibleMovesLeftUpDiagOfSquare(target, moves));
        
        // Add all possible squares right up diagonal from piece
        moves.addAll(collectPossibleMovesRightUpDiagOfSquare(target, moves));
        
        // Add all possible squares left down diagonal from piece
        moves.addAll(collectPossibleMovesLeftDownDiagFromSquare(target, moves));
        
        // Add all possible squares right down diagonal from piece
        moves.addAll(collectPossibleMovesRightDownDiagFromSquare(target, moves));
        
        // Give possible moves to piece
        piece.setPossibleMoves(moves);
    }
    
    private void calcRookMoves(Piece piece, ArrayList<Square> moves){
        target = piece.getCurrentSquare();
        
        // Add all possible squares to left of piece
        moves.addAll(collectPossibleMovesLeftOfSquare(target, moves));
        
        // Add all possible squares to right of piece
        moves.addAll(collectPossibleMovesRightOfSquare(target, moves));
        
        // Add all possible squares upward from piece
        moves.addAll(collectPossibleMovesUpwardFromSquare(target, moves));
        
        // Add all possible squares downward from piece
        moves.addAll(collectPossibleMovesDownwardFromSquare(target, moves));
        
        // Give possible moves to piece
        piece.setPossibleMoves(moves);
    }
    
    private void calcQueenMoves(Piece piece, ArrayList<Square> moves){
        target = piece.getCurrentSquare();
        
        // Add all possible squares to left of piece
        moves.addAll(collectPossibleMovesLeftOfSquare(target, moves));
        
        // Add all possible squares to right of piece
        moves.addAll(collectPossibleMovesRightOfSquare(target, moves));
        
        // Add all possible squares upward from piece
        moves.addAll(collectPossibleMovesUpwardFromSquare(target, moves));
        
        // Add all possible squares downward from piece
        moves.addAll(collectPossibleMovesDownwardFromSquare(target, moves));
        
        // Add all possible squares left up diagonal from piece
        moves.addAll(collectPossibleMovesLeftUpDiagOfSquare(target, moves));
        
        // Add all possible squares right up diagonal from piece
        moves.addAll(collectPossibleMovesRightUpDiagOfSquare(target, moves));
        
        // Add all possible squares left down diagonal from piece
        moves.addAll(collectPossibleMovesLeftDownDiagFromSquare(target, moves));
        
        // Add all possible squares right down diagonal from piece
        moves.addAll(collectPossibleMovesRightDownDiagFromSquare(target, moves));
        
        // Give possible moves to piece
        piece.setPossibleMoves(moves);
    }
    
    private void calcKingMoves(Piece piece, ArrayList<Square> moves){
        
    }
    
    // Adds sqaures to the left of a square until the path is
    // occupied or the edge of the board.
    private ArrayList<Square> collectPossibleMovesLeftOfSquare(
                            Square square, ArrayList<Square> moves){
        int row = square.getRow();
        int column = square.getColumn();
        int count;
        
        count = column - 1;
        while (count >= 0){
            if (squares[row][count].getOccupied()){
                break;
            }
            moves.add(squares[row][count]);
            --count;
        }
        
        return moves;
    }
    
    // Adds sqaures to the right of a square until the path is
    // occupied or the edge of the board.
    private ArrayList<Square> collectPossibleMovesRightOfSquare(
                            Square square, ArrayList<Square> moves){
        int row = square.getRow();
        int column = square.getColumn();
        int count;
        
        count = column + 1;
        while (count < DIMENSION){
            if (squares[row][count].getOccupied()){
                break;
            }
            moves.add(squares[row][count]);
            ++count;
        }
        
        return moves;
    }
    
    // Adds sqaures upward from a square until the path is
    // occupied or the edge of the board.
    private ArrayList<Square> collectPossibleMovesUpwardFromSquare(
                            Square square, ArrayList<Square> moves){
        int row = square.getRow();
        int column = square.getColumn();
        int count;
        
        count = row + 1;
        while (count < DIMENSION){
            if (squares[count][column].getOccupied()){
                break;
            }
            moves.add(squares[count][column]);
            ++count;
        }
        
        return moves;
    }
    
    // Adds sqaures downward from a square until the path is
    // occupied or the edge of the board.
    private ArrayList<Square> collectPossibleMovesDownwardFromSquare(
                            Square square, ArrayList<Square> moves){
        int row = square.getRow();
        int column = square.getColumn();
        int count;
        
        count = row - 1;
        while (count >= 0){
            if (squares[count][column].getOccupied()){
                break;
            }
            moves.add(squares[count][column]);
            --count;
        }
        
        return moves;
    }
    
    // Adds sqaures diagonally left-up of a square until the path is
    // occupied or the edge of the board.
    private ArrayList<Square> collectPossibleMovesLeftUpDiagOfSquare(
                            Square square, ArrayList<Square> moves){
        int row = square.getRow();
        int column = square.getColumn();
        
        column = column - 1;
        row = row + 1;
        while (column >= 0 && row < DIMENSION){
            if (squares[row][column].getOccupied()){
                break;
            }
            moves.add(squares[row][column]);
            --column;
            --row;
        }
        
        return moves;
    }
    
    // Adds sqaures diagonally right-up of a square until the path is
    // occupied or the edge of the board.
    private ArrayList<Square> collectPossibleMovesRightUpDiagOfSquare(
                            Square square, ArrayList<Square> moves){
        int row = square.getRow();
        int column = square.getColumn();
        
        column = column + 1;
        row = row + 1;   
        while (column < DIMENSION && row < DIMENSION){
            if (squares[row][column].getOccupied()){
                break;
            }
            moves.add(squares[row][column]);
            ++column;
            ++row;
        }
        
        return moves;
    }
    
    // Adds sqaures diagonally left-down of a square until the path is
    // occupied or the edge of the board.
    private ArrayList<Square> collectPossibleMovesLeftDownDiagFromSquare(
                            Square square, ArrayList<Square> moves){
        int row = square.getRow();
        int column = square.getColumn();
        
        column = column - 1;
        row = row - 1;
        while (column >= 0 && row >= 0){
            if (squares[row][column].getOccupied()){
                break;
            }
            moves.add(squares[row][column]);
            --column;
            --row;
        }
        
        return moves;
    }
    
    // Adds sqaures diagonally right-down of a square until the path is
    // occupied or the edge of the board.
    private ArrayList<Square> collectPossibleMovesRightDownDiagFromSquare(
                            Square square, ArrayList<Square> moves){
        int row = square.getRow();
        int column = square.getColumn();
        
        column = column + 1;
        row = row - 1;
        while (column < DIMENSION && row >= 0){
            if (squares[row][column].getOccupied()){
                break;
            }
            moves.add(squares[row][column]);
            --column;
            --row;
        }
        
        return moves;
    }
    
    public Square[][] getSquares(){
        return squares;
    }
}


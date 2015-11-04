/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessengine;
import java.util.ArrayList;
import java.util.HashMap;

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
    Square target;
    
    // maps keeping track of where pieces are.
    private HashMap<Piece, Square> mapWhitePiecesToSquares = new HashMap<Piece, Square>();
    private HashMap<Piece, Square> mapBlackPiecesToSquares = new HashMap<Piece, Square>();
    private HashMap<Piece, Square> piecesToSquaresMap = new HashMap<Piece, Square>();
    private HashMap<Square, Piece> squaresToPiecesMap = new HashMap<Square, Piece>(64);
    // to check if a square is occupied, just check if a piece is mapped to it.
    
    // Paths to png files
    private final String whitePawnPNGPath = "ChessPiecePNGs/whitePawn.png";
    private final String blackPawnPNGPath = "ChessPiecePNGs/blackPawn.png";
    private final String whiteKnightPNGPath = "ChessPiecePNGs/whiteKnight.png";
    private final String blackKnightPNGPath = "ChessPiecePNGs/blackKnight.png";
    private final String whiteBishopPNGPath = "ChessPiecePNGs/whiteBishop.png";
    private final String blackBishopPNGPath = "ChessPiecePNGs/blackBishop.png";
    private final String whiteRookPNGPath = "ChessPiecePNGs/whiteRook.png";
    private final String blackRookPNGPath = "ChessPiecePNGs/blackRook.png";
    private final String whiteQueenPNGPath = "ChessPiecePNGs/whiteQueen.png";
    private final String blackQueenPNGPath = "ChessPiecePNGs/blackQueen.png";
    private final String whiteKingPNGPath = "ChessPiecePNGs/whiteKing.png";
    private final String blackKingPNGPath = "ChessPiecePNGs/blackKing.png";
    
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
                
                // add square to map. initially maps to nothing.
                // may not need this since mapping of squares to pieces
                // can be done when setting up pieces.
                squaresToPiecesMap.put(squares[i][j], null);
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
        setKnightsOnSquares();
        setBishopsOnSquares();
        setRooksOnSquares();
        setQueensOnSquares();
        setKingsOnSquares();
    }
    
    // Create pawns, add to ArrayLists
    private void makePawns(){
        for (int i = 0; i < 8; i++){
            whitePieces.add(new Pawn(WHITE, PieceTypeEnum.PAWN, whitePawnPNGPath));
            blackPieces.add(new Pawn(BLACK, PieceTypeEnum.PAWN, blackPawnPNGPath));
        }
    }
    
    // Put pawns on starting squares
    private void setPawnsOnSquares(){
        for (int i = 0; i < 8; i++){
            piecesToSquaresMap.put(whitePieces.get(i), squares[6][i]);
            squaresToPiecesMap.put(squares[6][i], whitePieces.get(i));
            
            piecesToSquaresMap.put(blackPieces.get(i), squares[1][i]);
            squaresToPiecesMap.put(squares[1][i], blackPieces.get(i));
        }
    }
    
    // Make knights, add to ArrayLists 
    private void makeKnights(){
        whitePieces.add(new Knight(WHITE, PieceTypeEnum.KNIGHT, whiteKnightPNGPath));
        whitePieces.add(new Knight(WHITE, PieceTypeEnum.KNIGHT, whiteKnightPNGPath));
        
        blackPieces.add(new Knight(BLACK, PieceTypeEnum.KNIGHT, blackKnightPNGPath));
        blackPieces.add(new Knight(BLACK, PieceTypeEnum.KNIGHT, blackKnightPNGPath));
    }
    
    // Put knights on starting squares
    private void setKnightsOnSquares(){
        piecesToSquaresMap.put(whitePieces.get(8), squares[7][1]);
        piecesToSquaresMap.put(whitePieces.get(9), squares[7][6]);
        squaresToPiecesMap.put(squares[7][1], whitePieces.get(8));
        squaresToPiecesMap.put(squares[7][6], whitePieces.get(9));

        piecesToSquaresMap.put(blackPieces.get(8), squares[0][1]);
        piecesToSquaresMap.put(blackPieces.get(9), squares[0][6]);
        squaresToPiecesMap.put(squares[0][1], blackPieces.get(8));
        squaresToPiecesMap.put(squares[0][6], blackPieces.get(9));
    }
    
    // Make bishops, add to ArrayLists
    private void makeBishops(){
        whitePieces.add(new Bishop(WHITE, PieceTypeEnum.BISHOP, whiteBishopPNGPath));
        whitePieces.add(new Bishop(WHITE, PieceTypeEnum.BISHOP, whiteBishopPNGPath));
        
        blackPieces.add(new Bishop(BLACK, PieceTypeEnum.BISHOP, blackBishopPNGPath));
        blackPieces.add(new Bishop(BLACK, PieceTypeEnum.BISHOP, blackBishopPNGPath));
    }
    
    // Putbishops on starting squares
    private void setBishopsOnSquares(){   
        piecesToSquaresMap.put(whitePieces.get(10), squares[7][2]);
        piecesToSquaresMap.put(whitePieces.get(11), squares[7][5]);
        squaresToPiecesMap.put(squares[7][2], whitePieces.get(10));
        squaresToPiecesMap.put(squares[7][5], whitePieces.get(11));

        piecesToSquaresMap.put(blackPieces.get(10), squares[0][2]);
        piecesToSquaresMap.put(blackPieces.get(11), squares[0][5]);
        squaresToPiecesMap.put(squares[0][2], blackPieces.get(10));
        squaresToPiecesMap.put(squares[0][5], blackPieces.get(11));
    }
    
    // Make Rooks, add to ArrayLists
    private void makeRooks(){
        whitePieces.add(new Rook(WHITE, PieceTypeEnum.ROOK, whiteRookPNGPath));
        whitePieces.add(new Rook(WHITE, PieceTypeEnum.ROOK, whiteRookPNGPath));
        
        blackPieces.add(new Rook(BLACK, PieceTypeEnum.ROOK, blackRookPNGPath));
        blackPieces.add(new Rook(BLACK, PieceTypeEnum.ROOK, blackRookPNGPath));
    }
    
    // Put rooks on starting squares
    private void setRooksOnSquares(){
        piecesToSquaresMap.put(whitePieces.get(12), squares[7][0]);
        piecesToSquaresMap.put(whitePieces.get(13), squares[7][7]);
        squaresToPiecesMap.put(squares[7][0], whitePieces.get(12));
        squaresToPiecesMap.put(squares[7][7], whitePieces.get(13));

        piecesToSquaresMap.put(blackPieces.get(12), squares[0][0]);
        piecesToSquaresMap.put(blackPieces.get(13), squares[0][7]);
        squaresToPiecesMap.put(squares[0][0], blackPieces.get(12));
        squaresToPiecesMap.put(squares[0][7], blackPieces.get(13));
    }
    
    // Make Queens, add to ArrayLists
    private void makeQueens(){
        whitePieces.add(new Queen(WHITE, PieceTypeEnum.QUEEN, whiteQueenPNGPath));
        blackPieces.add(new Queen(BLACK, PieceTypeEnum.QUEEN, blackQueenPNGPath));
    }
    
    // Put queesn on starting squares
    private void setQueensOnSquares(){
        piecesToSquaresMap.put(whitePieces.get(14), squares[7][3]);
        squaresToPiecesMap.put(squares[7][3], whitePieces.get(14));

        piecesToSquaresMap.put(blackPieces.get(14), squares[0][3]);
        squaresToPiecesMap.put(squares[0][3], blackPieces.get(14));
    }
    
    // Make Kings, add to ArrayLists
    private void makeKings(){
        whitePieces.add(new King(WHITE, PieceTypeEnum.KING, whiteKingPNGPath));
        blackPieces.add(new King(BLACK, PieceTypeEnum.KING, blackKingPNGPath));
    }
    
    // Put Kings on starting squares
    private void setKingsOnSquares(){
        piecesToSquaresMap.put(whitePieces.get(15), squares[7][4]);
        squaresToPiecesMap.put(squares[7][4], whitePieces.get(15));

        piecesToSquaresMap.put(blackPieces.get(15), squares[0][4]);
        squaresToPiecesMap.put(squares[0][4], blackPieces.get(15));
    }
    
    // Removes pieces from squares on board
    private void clearSquares(){
        for (int i = 0; i < 8; ++i){
            for (int j = 0; j < 8; ++j){
                target = squares[i][j];
                
                squaresToPiecesMap.replace(target, null);
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
    public void calcMovementSquares (Piece piece){
        ArrayList<Square> moves = new ArrayList<Square>();
        
        switch (piece.getPieceType()){
            case PAWN: 
                calcPawnMoves((Pawn) piece, moves);
                break;
            
            case KNIGHT:
                calcKnightMoves((Knight) piece, moves);
                break;
                
            case BISHOP:
                calcBishopMoves((Bishop) piece, moves);
                break;
                
            case ROOK:
                calcRookMoves((Rook) piece, moves);
                break;
                
            case QUEEN:
                calcQueenMoves((Queen) piece, moves);
                break;
                
            case KING:
                calcKingMoves((King) piece, moves);
                break;
        }
    }
    
    
    private void calcPawnMoves(Pawn pawn, ArrayList<Square> moves){
        target = pawn.getCurrentSquare();
        int row = target.getRow();
        int column = target.getColumn();
        
        if (pawn.getColor().equals(WHITE)){
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
        else{
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
        
        // Give possibe moves to piece
        pawn.setPossibleMoves(moves);
    }
    
    private void calcKnightMoves(Knight knight, ArrayList<Square> moves){
        target = knight.getCurrentSquare();
        int row = target.getRow();
        int column = target.getColumn();
        
        if ((row - 2) > 0){
            if ((column - 1) > 0){
                moves.add(squares[row-2][column-1]);
            }
            if ((column + 1) < DIMENSION){
                moves.add(squares[row-2][column+1]);
            }
        }
        
        if ((row + 2) < DIMENSION){
            if ((column - 1) > 0){
                moves.add(squares[row+2][column-1]);
            }
            if ((column + 1) < DIMENSION){
                moves.add(squares[row+2][column+1]);
            }
        }
        
        if ((column - 2) > 0){
            if ((row - 1) > 0){
                moves.add(squares[row-1][column-2]);
            }
            if ((row + 1) < DIMENSION){
                moves.add(squares[row+1][column-2]);
            }
        }
        
        if ((column + 2) < DIMENSION){
            if ((row - 1) > 0){
                moves.add(squares[row-1][column+2]);
            }
            if ((row + 1) < DIMENSION){
                moves.add(squares[row+1][column+2]);
            }
        }
        
        knight.setPossibleMoves(moves);
    }
    
    private void calcBishopMoves(Bishop bishop, ArrayList<Square> moves){
        target = bishop.getCurrentSquare();
        
        // Add all possible squares left up diagonal from piece
        moves.addAll(collectPossibleMovesLeftUpDiagOfSquare(target, moves));
        
        // Add all possible squares right up diagonal from piece
        moves.addAll(collectPossibleMovesRightUpDiagOfSquare(target, moves));
        
        // Add all possible squares left down diagonal from piece
        moves.addAll(collectPossibleMovesLeftDownDiagFromSquare(target, moves));
        
        // Add all possible squares right down diagonal from piece
        moves.addAll(collectPossibleMovesRightDownDiagFromSquare(target, moves));
        
        // Give possible moves to piece
        bishop.setPossibleMoves(moves);
    }
    
    private void calcRookMoves(Rook rook, ArrayList<Square> moves){
        target = rook.getCurrentSquare();
        
        // Add all possible squares to left of piece
        moves.addAll(collectPossibleMovesLeftOfSquare(target, moves));
        
        // Add all possible squares to right of piece
        moves.addAll(collectPossibleMovesRightOfSquare(target, moves));
        
        // Add all possible squares upward from piece
        moves.addAll(collectPossibleMovesUpwardFromSquare(target, moves));
        
        // Add all possible squares downward from piece
        moves.addAll(collectPossibleMovesDownwardFromSquare(target, moves));
        
        // Give possible moves to piece
        rook.setPossibleMoves(moves);
    }
    
    private void calcQueenMoves(Queen queen, ArrayList<Square> moves){
        target = queen.getCurrentSquare();
        
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
        queen.setPossibleMoves(moves);
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
    
    public HashMap<Piece, Square> getPiecesToSquaresMap(){
        return piecesToSquaresMap;
    }
    
    public HashMap<Square, Piece> getSquaresToPiecesMap(){
        return squaresToPiecesMap;
    }
    
    public Square[][] getSquares(){
        return squares;
    }
}


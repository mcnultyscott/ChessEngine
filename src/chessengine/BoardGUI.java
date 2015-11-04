/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessengine;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Scott
 */

public class BoardGUI extends Application {
    final int DIMENSION = 8;
    final int SQUARE_WIDTH = 100;
    final int SQUARE_HEIGHT = 100;
    final int PIECE_WIDTH = 70;
    final int PIECE_HEIGHT = 70;
    final double STROKE_WIDTH = 50;
    final Font COORDINATE_SIZE = new Font(20);
    
    String[] fileLex = {"A", "B", "C", "D", "E", "F", "G", "H"};
    String[] rankLex = {"1", "2", "3", "4", "5", "6", "7", "8"};
    
    ArrayList<Square> squares;
    ArrayList<Piece> whitePieces;
    ArrayList<Piece> blackPieces;
    ArrayList<PieceImageView> whitePieceImages = new ArrayList<PieceImageView>();
    ArrayList<PieceImageView> blackPieceImages = new ArrayList<PieceImageView>();;
    
    Square[][] squaresFromBoard;
    Square target;
    
    HashMap<Piece, Square> piecesToSquaresMap;
    HashMap<Square, Piece> squaresToPiecesMap;
    
    @Override
    public void start(Stage primaryStage) {   
        final Board board = new Board();
        Group root = new Group();
        Scene scene = new Scene(root, DIMENSION * SQUARE_WIDTH, 
                                    DIMENSION * SQUARE_HEIGHT);
        
        piecesToSquaresMap = board.getPiecesToSquaresMap();
        squaresToPiecesMap = board.getSquaresToPiecesMap();
        
        whitePieces = board.getWhitePieces();
        blackPieces = board.getBlackPieces();
        
        // place images for white an black pieces on board
        placePieceImages(whitePieces, whitePieceImages, piecesToSquaresMap);
        placePieceImages(blackPieces, blackPieceImages, piecesToSquaresMap);
        
        whitePieceImages.stream().forEach((v) -> {
            givePieceEvents(v, board);
        });
        
        blackPieceImages.stream().forEach((v) -> {
            givePieceEvents(v, board);
        });
        
        // calculate initial movement squares for white and black
        for (Piece whitePiece : whitePieces){
            board.calcMovementSquares(whitePiece);
        }
        
        for (Piece blackPiece : blackPieces){
            board.calcMovementSquares(blackPiece);
        }    
        
        squaresFromBoard = board.getSquares();
        squares = new ArrayList<Square>();
        convertFrom2DArrayToArrayList(squares, squaresFromBoard);
              
        giveSquaresColor(squares);
        root.getChildren().addAll(squares);
        addRankAndFileTexts(root);
        
        for (Square square : squares) {
            giveSquareEventHandling(square);
        }
        
        root.getChildren().addAll(whitePieceImages);
        root.getChildren().addAll(blackPieceImages);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void placePieceImages(ArrayList<Piece> pieces,
            ArrayList<PieceImageView> pieceImages,
            HashMap<Piece, Square> map){
        
        PieceImageView pv;        
        for (Piece p : pieces) {
            target = map.get(p);
            pv = new PieceImageView(p, PIECE_HEIGHT, PIECE_WIDTH, 
                target.getColumn() * SQUARE_WIDTH + SQUARE_WIDTH / 5, 
                target.getRow() * SQUARE_HEIGHT + SQUARE_WIDTH / 10);
            
            System.out.println(p.toString() + "\t| row: " +
                    target.getRow() + "\t| column: " +
                    target.getColumn());
            pieceImages.add(pv); 
        }
    }
    
    private void addRankAndFileTexts(Group root){
        Text text;
        for (int i = 0; i < DIMENSION; ++i){
            for (int j = DIMENSION-1; j >= 0; --j){
                text = new Text (
                        ((i * SQUARE_WIDTH)),
                        ((Math.abs(j-(DIMENSION-1)) * SQUARE_HEIGHT) + SQUARE_HEIGHT),
                        (fileLex[i] + rankLex[j]));
                
                text.setFont(COORDINATE_SIZE);
                root.getChildren().add(text);
            }
        }
    }
    
    private void giveSquaresColor(ArrayList<Square> squares){
        for (int i = 0; i < DIMENSION; ++i){
            for (int j = 0; j < DIMENSION; ++j){
                
                // Acts as a normal '2D' array access
                target = squares.get((i * DIMENSION) + j);
                
                // Sets color
                if (i % 2 == 0){
                    if (j % 2 == 0){
                        target.setFill(Color.BEIGE);
                    }
                    else{
                        target.setFill(Color.DARKGREEN);
                    }
                }
                else{
                    if (j % 2 != 0){
                        target.setFill(Color.BEIGE);
                    }
                    else{
                        target.setFill(Color.DARKGREEN);
                    }
                }
            } // end j for
        } // end i for
    }
    
    private void giveSquareEventHandling (Square square){        
        square.setOnMouseClicked(new EventHandler <MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked square\t| row: " + 
                        square.getRow() + "\t| column: " +
                        square.getColumn() + "\t| occupied: " +
                        square.getOccupied());       
            }
        });
    }
    
    // Converts the 2D array of squares into an array list.
    private void convertFrom2DArrayToArrayList(ArrayList<Square> squares,
                                                Square[][] s){
        for (int i = 0; i < DIMENSION; i++){
            for (int j = 0; j < DIMENSION; j++){
                squares.add(s[i][j]);
            }
        }
    }
    
    private void givePieceEvents(PieceImageView pv, Board b){
        final Delta dragDelta = new Delta();
        final Delta start = new Delta();
        
        pv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Piece piece = pv.getPiece();
                    
                System.out.println(piece.toString() +
                        "\t| row: " + piecesToSquaresMap.get(piece).getRow() +
                        "\t| column: " + piecesToSquaresMap.get(piece).getColumn());
                
            }    
        });
        
        pv.setOnMousePressed(new EventHandler<MouseEvent>() {         
            @Override
            public void handle(MouseEvent mouseEvent) {
                start.x = pv.getLayoutX();
                start.y = pv.getLayoutY();
                System.out.println("start x: " + start.x + "\t| start y: " + start.y);
                
                Piece piece = pv.getPiece();
                for (Square s : piece.getPossibleMoves()){
                    System.out.println("possible square\t| row: " +
                            s.getRow() + "\t| column: " +
                            s.getColumn());
                }
                
                // record a delta distance for the drag and drop operation.
                dragDelta.x = pv.getLayoutX() - mouseEvent.getSceneX();
                dragDelta.y = pv.getLayoutY() - mouseEvent.getSceneY();
                pv.setCursor(Cursor.MOVE);
            }
        });
        
        pv.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent mouseEvent) {
                pv.setCursor(Cursor.HAND);

                double xDrop = pv.getLayoutX() + (SQUARE_WIDTH / 2);
                double yDrop = pv.getLayoutY() + (SQUARE_HEIGHT / 2);

                // Squares a piece is moving from and to
                Square sourceSquare;
                Square targetSquare;
                int count = 0;

                // finds the index of the square that the piece
                // was dropped into
                while (!squares.get(count).contains(xDrop, yDrop) && 
                        count < squares.size()){
                    count++;
                }

                // Checks if the piece is brought off the board.
                // If the piece is, put it back to its starting posision.
                if (count >= squares.size()){
                    pv.setLayoutX(start.x);
                    pv.setLayoutY(start.y);
                }
                else{
                
                    targetSquare = squares.get(count);

                    // square in map maps to no piece
                    if (squaresToPiecesMap.get(targetSquare) == null){
                        System.out.println("TARGET SQUARE NOT OCCUPIED");
                        
                        Piece piece = pv.getPiece();
                        
                        // if the target square is within the possible moves
                        // of the piece, move it
                        if (piece.getPossibleMoves().contains(targetSquare)){
                            
                        // make the square that the piece was occupying empty
                        sourceSquare = piecesToSquaresMap.get(piece);
                        squaresToPiecesMap.replace(sourceSquare, null);
                        
//                        sourceSquare = piece.getCurrentSquare();
//                        sourceSquare.setOccupied(false);
//                        sourceSquare.setOccupyingPiece(null);
                        
                        System.out.println("source square\t| row: " + 
                                sourceSquare.getRow() + "\t| column: " +
                                sourceSquare.getColumn() + "\t| occupied: " +
                                sourceSquare.getOccupied());
                        
                        // move image of piece to target sqaure
                        pv.setLayoutX(targetSquare.getX() + (SQUARE_WIDTH / 5));
                        pv.setLayoutY(targetSquare.getY() + (SQUARE_HEIGHT / 10));
                        
                        // make the sqaure that the piece moves to occupied
                        squaresToPiecesMap.replace(targetSquare, piece);
                        
                        //targetSquare.setOccupied(true);
                        //targetSquare.setOccupyingPiece(piece);
                        
                        // set piece's current square
                        piecesToSquaresMap.replace(piece, targetSquare);
                        
                        //piece.setCurrentSquare(targetSquare);
                        
                        System.out.println("target square\t| row: " + 
                                targetSquare.getRow() + "\t| column: " +
                                targetSquare.getColumn() + "\t| occupied: " +
                                targetSquare.getOccupied());
                        } 
                        else{
                            System.out.println("target square not in possible moves");
                            pv.setLayoutX(start.x);
                            pv.setLayoutY(start.y);
                        }
                    }
                    else{
                        System.out.println("TARGET SQUARE OCCUPIED");
                                     
                        // move the image of the piece back to the 
                        // starting position
                        pv.setLayoutX(start.x);
                        pv.setLayoutY(start.y);
                    }
                }
                
                // calculate initial movement squares for white and black
                for (Piece whitePiece : whitePieces){
                    b.calcMovementSquares(whitePiece);
                }

                for (Piece blackPiece : blackPieces){
                    b.calcMovementSquares(blackPiece);
                }
            }
        });
        
        pv.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent mouseEvent) {
                pv.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
                pv.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
            }
        });
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
// records relative x and y co-ordinates.
class Delta { double x, y; }
    
}

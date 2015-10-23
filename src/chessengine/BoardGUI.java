/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessengine;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
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
    Square[][] squaresFromBoard;
    Square target;
    PieceTypeEnum typeOfPiece;
    
    @Override
    public void start(Stage primaryStage) {   
        
        /////////////////////////////////////////////////////////////
        // Experimenting with images
//        Image king = new Image("/ChessPiecePNGS/BlackKing.png");
       ImageView v = new ImageView();
//        v.setImage(king);     
        Image im;
        Pane canvas = new Pane();
        canvas.setPrefSize(200,200);
        Rectangle rectangle = new Rectangle(100,100,Color.RED);
        rectangle.relocate(70,70);
        Pawn whitePawn = new Pawn("white", typeOfPiece.PAWN, "ChessPiecePNGs/whitePawn.png");
        //whitePawn.relocate(200, 200);
        whitePawn.setImage(whitePawn.getCurrentImage());
//        im = whitePawn.getCurrentImage();
//        v.setImage(im);
//        v.setFitWidth(PIECE_WIDTH);
//        v.setFitHeight(PIECE_HEIGHT);
        whitePawn.setFitWidth(PIECE_WIDTH);
        whitePawn.setFitHeight(PIECE_HEIGHT);
        whitePawn.preserveRatioProperty();
        //canvas.getChildren().addAll(rectangle);
        
        // allow the label to be dragged around.
        final Delta dragDelta = new Delta();
        final Delta start = new Delta();
        
        whitePawn.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                start.x = whitePawn.getLayoutX();
                start.y = whitePawn.getLayoutY();
                // record a delta distance for the drag and drop operation.
                dragDelta.x = whitePawn.getLayoutX() - mouseEvent.getSceneX();
                dragDelta.y = whitePawn.getLayoutY() - mouseEvent.getSceneY();
                whitePawn.setCursor(Cursor.MOVE);
            }
        });
        
        whitePawn.setOnMouseReleased(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent mouseEvent) {
            whitePawn.setCursor(Cursor.HAND);
            
            double xDrop = whitePawn.getLayoutX() + (SQUARE_WIDTH / 2);
            double yDrop = whitePawn.getLayoutY() + (SQUARE_HEIGHT / 2);
            
            Square target;
            int count = 0;
            
            while (count < squares.size() && 
                    !squares.get(count).contains(xDrop, yDrop)){
                count++;
            }
            System.out.println("count: " + count);
            
            target = squares.get(count);
            System.out.println("target | X: " + 
                    target.getX() + " Y : " +
                    target.getY());
            
            if (!target.getOccupied()){
                System.out.println("!target.getOccupied()");
                whitePawn.setLayoutX(target.getX() + (SQUARE_WIDTH / 10));
                whitePawn.setLayoutY(target.getY() + (SQUARE_HEIGHT / 10));
            }
            else{
                System.out.println("else");
                whitePawn.setLayoutX(start.x);
                whitePawn.setLayoutY(start.y);
            }
          }
        });
        
        whitePawn.setOnMouseDragged(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent mouseEvent) {
            whitePawn.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
            whitePawn.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
            
            double xDrag = whitePawn.getLayoutX() + (SQUARE_WIDTH / 2);
            double yDrag = whitePawn.getLayoutY() + (SQUARE_HEIGHT / 2);
            
            Square target;
            int count = 0;
            
            while (count < squares.size() && 
                    !squares.get(count).contains(xDrag, yDrag)){
                count++;
            }
            
//            target = squares.get(count);
//            target.setFill(Color.BLUEVIOLET);
          }
        });
        
        //////////////////////////////////////////////
        
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                start.x = rectangle.getLayoutX();
                start.y = rectangle.getLayoutY();
                // record a delta distance for the drag and drop operation.
                dragDelta.x = rectangle.getLayoutX() - mouseEvent.getSceneX();
                dragDelta.y = rectangle.getLayoutY() - mouseEvent.getSceneY();
                rectangle.setCursor(Cursor.MOVE);
            }
        });
        
        rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent mouseEvent) {
            rectangle.setCursor(Cursor.HAND);
            
            double xDrop = rectangle.getLayoutX() + (SQUARE_WIDTH / 2);
            double yDrop = rectangle.getLayoutY() + (SQUARE_HEIGHT / 2);
            
            Square target;
            int count = 0;
            
            while (count < squares.size() && 
                    !squares.get(count).contains(xDrop, yDrop)){
                count++;
            }
            System.out.println("count: " + count);
            
            target = squares.get(count);
            System.out.println("target | X: " + 
                    target.getX() + " Y : " +
                    target.getY());
            
            if (!target.getOccupied()){
                System.out.println("!target.getOccupied()");
                rectangle.setLayoutX(target.getX());
                rectangle.setLayoutY(target.getY());
            }
            else{
                System.out.println("else");
                rectangle.setLayoutX(start.x);
                rectangle.setLayoutY(start.y);
            }
          }
        });
        
        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent mouseEvent) {
            rectangle.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
            rectangle.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
            
            double xDrag = rectangle.getLayoutX() + (SQUARE_WIDTH / 2);
            double yDrag = rectangle.getLayoutY() + (SQUARE_HEIGHT / 2);
            
            Square target;
            int count = 0;
            
            while (count < squares.size() && 
                    !squares.get(count).contains(xDrag, yDrag)){
                count++;
            }
            
//            target = squares.get(count);
//            target.setFill(Color.BLUEVIOLET);
          }
        });
     
        //////////////////////////////////////////////////////////
        
        Board board = new Board();
        squaresFromBoard = board.getSquares();
        squares = new ArrayList<Square>();
        convertFrom2DArrayToArrayList(squares, squaresFromBoard);
        
        Group root = new Group();
        Scene scene = new Scene(root, DIMENSION * SQUARE_WIDTH, 
                                    DIMENSION * SQUARE_HEIGHT);
        
        giveSquaresColor(squares, root);
        addRankAndFileTexts(root);
        
        for (int i = 0; i < squares.size(); i++){
            giveSquareEventHandling(squares.get(i));
        }
        
        root.getChildren().add(canvas);
        root.getChildren().add(v);
        root.getChildren().add(whitePawn);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void addRankAndFileTexts(Group root){
        for (int i = 0; i < DIMENSION; ++i){
            for (int j = DIMENSION-1; j >= 0; --j){
                Text text = new Text (
                        ((i * SQUARE_WIDTH)),
                        ((Math.abs(j-(DIMENSION-1)) * SQUARE_HEIGHT) + SQUARE_HEIGHT),
                        (fileLex[i] + rankLex[j]));
                
                text.setFont(COORDINATE_SIZE);
                root.getChildren().add(text);
            }
        }
    }
    
    // Makes squarea and adds them to the ArrayList and root. To save writing
    // another loop + nested loop the squares are also added to the root, even
    // though this isn't implied by the function name.
    private void giveSquaresColor(ArrayList<Square> squares, Group root){
        for (int i = 0; i < DIMENSION; ++i){
            for (int j = 0; j < DIMENSION; ++j){
                
                // Acts as a normal 2D array access
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

                root.getChildren().add(target);
            } // end j for
        } // end i for
    }
    
    private void giveSquareEventHandling (Square square){
        
        square.setOnMouseClicked(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                
            }
        });
        
        square.setOnDragDetected(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                Piece piece;
                System.out.println("OnDragDetected: " + 
                        "X: " + square.getX() +
                        " Y: " + square.getY());
                
                if (square.getOccupied()){
                    piece = square.getOccupyingPiece();
                    
                    //Dragboard db = piece.startDragAndDrop(TransferMode.ANY);
                    Dragboard db = square.startDragAndDrop(TransferMode.ANY);

                    ClipboardContent content = new ClipboardContent();
                    
                    // What should really be put on the clipboard is the 
                    // picture for the piece. What is here is just a test.
                    //content.putString("Piece: " + piece.toString());
                    System.out.println("Piece: " + piece.toString());
                    db.setContent(content);
                }
                else{
                    System.out.println("No piece");
                }

                event.consume();
            }
        });

        square.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the rect */
                square.setStrokeType(StrokeType.INSIDE);
                square.setStrokeWidth(STROKE_WIDTH);
                System.out.println("OnDragOver: " + 
                        "X: " + square.getX() +
                        " Y: " + square.getY());
                //System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node 
                 * and if it has a string data */
                if (event.getGestureSource() != square &&
                        event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        square.setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the rect */
                System.out.println("OnDragEntered: " + 
                                "X: " + square.getX() +
                                " Y: " + square.getY());
                //square.setFill(Color.BLACK);
                square.setStrokeType(StrokeType.INSIDE);
                square.setStrokeWidth(STROKE_WIDTH);

                /* show to the user that it is an actual gesture rect */
//                        if (event.getGestureSource() != rect &&
//                                event.getDragboard().hasString()) {
//                            rect.setFill(Color.GREEN);
//                        }

                event.consume();
            }
        });

        square.setOnDragExited(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                square.setStrokeType(null);
                square.setStrokeWidth(0);
                //rect.setFill(Color.WHITE);

                event.consume();
            }
        });
        
//        square.setOnDragDropped(new EventHandler <DragEvent>() {
//            public void handle(DragEvent event) {
//                /* data dropped */
//                System.out.println("Dropped: " + 
//                        "X: " + rect.getX() +
//                        "Y: " + rect.getY());
//                /* if there is a string data on dragboard, read it and use it */
//                Dragboard db = event.getDragboard();
//                boolean success = false;
//                if (db.hasString()) {
////                            rect.setText(db.getString());
//                    success = true;
//                }
//                /* let the rect know whether the string was successfully 
//                 * transferred and used */
//                event.setDropCompleted(success);
//
//                event.consume();
//            }
//        });

//        rect.setOnDragDone(new EventHandler <DragEvent>() {
//            public void handle(DragEvent event) {
//                /* the drag-and-drop gesture ended */
//                System.out.println("onDragDone");
//                /* if the data was successfully moved, clear it */
//                if (event.getTransferMode() == TransferMode.MOVE) {
////                            rect.setText("");
//                }
//
//                event.consume();
//            }
//        });
    }
    
    // Converts the 2D array of squares into an array list. Annoying result
    // of using two different data structures without thinking about it
    private void convertFrom2DArrayToArrayList(ArrayList<Square> squares,
                                                Square[][] s){
        for (int i = 0; i < DIMENSION; i++){
            for (int j = 0; j < DIMENSION; j++){
                squares.add(s[i][j]);
            }
        }
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

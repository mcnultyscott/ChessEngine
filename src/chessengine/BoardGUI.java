/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessengine;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
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
    final double STROKE_WIDTH = 15;
    
    @Override
    public void start(Stage primaryStage) {
        String[] fileLex = {"A", "B", "C", "D", "E", "F", "G", "H"};
        String[] rankLex = {"1", "2", "3", "4", "5", "6", "7", "8"};
        ArrayList<Square> squares = new ArrayList<Square>();
        ArrayList<Text> rankFileStringTexts = new ArrayList<Text>();
        
        Group root = new Group();
        Scene scene = new Scene(root, DIMENSION * SQUARE_WIDTH, DIMENSION * SQUARE_HEIGHT);
        
        addSquares(squares, root);
        
        for (int i = 0; i < squares.size(); i++){
            giveSquareEventHandling(squares.get(i));
        }
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    // Makes squarea and adds them to the ArrayList and root
    private void addSquares(ArrayList<Square> squares, Group root){
        for (int i = 0; i < DIMENSION; ++i){
            for (int j = 0; j < DIMENSION; ++j){
                Square s = new Square(
                         i, j,
                        (j * SQUARE_WIDTH),
                        (i * SQUARE_HEIGHT),
                        (SQUARE_WIDTH),
                        (SQUARE_HEIGHT));
                
                // Sets color
                if (i % 2 == 0){
                    if (j % 2 == 0){
                        s.setFill(Color.BEIGE);
                    }
                    else{
                        s.setFill(Color.DARKGREEN);
                    }
                }
                else{
                    if (j % 2 != 0){
                        s.setFill(Color.BEIGE);
                    }
                    else{
                        s.setFill(Color.DARKGREEN);
                    }
                }
                
                squares.add(s);
                root.getChildren().add(s);
            } // end j for
        } // end i for
    }
    
    private void giveSquareEventHandling (Square square){
        square.setOnDragDetected(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                Piece piece;
                System.out.println("X: " + square.getX() + 
                                   "Y: " + square.getY());
                
                if (square.getOccupied()){
                    piece = square.getOccupyingPiece();
                    
                    Dragboard db = piece.startDragAndDrop(TransferMode.ANY);

                    ClipboardContent content = new ClipboardContent();
                    
                    // What should really be put on the clipboard is the 
                    // picture for the piece. What is here is just a test.
                    content.putString("Piece: " + piece.toString());
                    db.setContent(content);

                    event.consume();
                }

                event.consume();
            }
        });

//        rect.setOnDragOver(new EventHandler <DragEvent>() {
//            public void handle(DragEvent event) {
//                /* data is dragged over the rect */
//                System.out.println("onDragOver");
//
//                /* accept it only if it is  not dragged from the same node 
//                 * and if it has a string data */
//                if (event.getGestureSource() != rect &&
//                        event.getDragboard().hasString()) {
//                    /* allow for both copying and moving, whatever user chooses */
//                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//                }
//
//                event.consume();
//            }
//        });

        square.setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the rect */
                square.setStrokeType(StrokeType.INSIDE);
                square.setStrokeWidth(STROKE_WIDTH);
                System.out.println("Entered: " + 
                        "X: " + square.getX() +
                        "Y: " + square.getY());

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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

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
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    
    @Override
    public void start(Stage primaryStage) {
        String[] fileLex = {"A", "B", "C", "D", "E", "F", "G", "H"};
        String[] rankLex = {"1", "2", "3", "4", "5", "6", "7", "8"};
        ArrayList<Square> squares = new ArrayList<Square>();
        ArrayList<Text> rankFileStringTexts = new ArrayList<Text>();
        
        Group root = new Group();
        Scene scene = new Scene(root, DIMENSION * SQUARE_WIDTH, DIMENSION * SQUARE_HEIGHT);
        
        addSquares(squares, root);
        
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
    
    private void giveSquaresEventHandling (ArrayList<Square> squares){
        
    }

    public void handle(MouseEvent event, Square square) {
        /* drag was detected, start drag-and-drop gesture*/
        System.out.println("X: " + square.getX() + "Y: " + square.getY());

        if (square.getOccupied()){
            
            Piece p = square.getOccupyingPiece();
            System.out.println("Occupying piece: " + p.getPieceType());
            Dragboard db = p.startDragAndDrop(TransferMode.ANY);
            
            ClipboardContent content = new ClipboardContent();
            content.putString("X: " + square.getX() + "Y: " + square.getY());
            db.setContent(content);
        }
        /* allow any transfer mode */
//        Dragboard db = square.startDragAndDrop(TransferMode.ANY);

        /* put a string on dragboard */
//        ClipboardContent content = new ClipboardContent();
//        content.putString("X: " + square.getX() + "Y: " + square.getY());
//        db.setContent(content);

        event.consume();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

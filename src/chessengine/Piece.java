/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessengine;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Scott
 */
public class Piece extends Rectangle {
    String color;
    Square currentSquare;
    PieceTypeEnum typeOfPiece;
    ArrayList<Square> moveSquares;// = new ArrayList<Square>();
    ArrayList<Square> attackSquares;// = new ArrayList<Square>();
    
    public Piece(String col, PieceTypeEnum type){
           color = col;
           typeOfPiece = type;
    }
    
    public String getColor(){
        return color;
    }
    
    public void setCurrentSquare(Square square){
        currentSquare = square;
    }
    
    public Square getCurrentSquare(){
        return currentSquare;
    }
    
    public void setColor(String col){
        color = col;
    }
    
    public PieceTypeEnum getPieceType(){
        return typeOfPiece;
    }
    
    public ArrayList<Square> getPossibleMoves(){
        return moveSquares;
    }
    
    public void setPossibleMoves(ArrayList<Square> moves){
        moveSquares = moves;
    }
    
    public ArrayList<Square> getAttackingSquares(){
        return attackSquares;
    }
    
    public void setAttackingSquares(ArrayList<Square> attack){
        attackSquares = attack;
    }
}

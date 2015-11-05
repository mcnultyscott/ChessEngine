/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessengine;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Scott
 */
public class Piece{
    String color;
    Square currentSquare;
    String[] pieceStringList = {"Pawn", "Knight", "Bishop", "Rook", "Queen", "King"};
    PieceTypeEnum typeOfPiece;
    ArrayList<Square> moveSquares;
    ArrayList<Square> directAttackSquares;
    ArrayList<Square> indirectAttackSquares;
    String pathForPNG;
    
    public Piece(String col, PieceTypeEnum type){
           color = col;
           typeOfPiece = type;      
    }
    
    public Piece(String col, PieceTypeEnum type, String path){
           color = col;
           typeOfPiece = type;      
           pathForPNG = path;
    }
    
    public String getColor(){
        return color;
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
    
    public ArrayList<Square> getDirectAttackSquares(){
        return directAttackSquares;
    }
    
    public void setDirectAttackSquares(ArrayList<Square> directAttack){
        directAttackSquares = directAttack;
    }    
    
    public ArrayList<Square> getIndirectAttackSquares(){
        return indirectAttackSquares;
    }
    
    public void setindirectAttackSquares(ArrayList<Square> indirectAttack){
        indirectAttackSquares = indirectAttack;
    }
    
    @Override
    public String toString(){
        return getColor() +  " " + 
                pieceStringList[typeOfPiece.ordinal()];
                
    }
}

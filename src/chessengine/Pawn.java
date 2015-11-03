/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessengine;

/**
 *
 * @author Scott
 */
public class Pawn extends Piece{
    Boolean hasMoved = false;
    Boolean promotionPossible = false;
    Boolean enPassantable = false;
    
    public Pawn(String col, PieceTypeEnum type, String path){
        super(col, type);
        pathForPNG = path;
        
    }
    
    public void setHasMoved(Boolean move){
        hasMoved = move;
    }
    
    public Boolean getHasMoved(){
        return hasMoved;
    }
    
    public void setEnPassantable (Boolean en){
        enPassantable = true;
    }
    
    public Boolean getEnPassantable (){
        return enPassantable;
    }    
}


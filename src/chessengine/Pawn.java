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
    private Boolean promotionPossible;
    private Boolean enPassantable;
    
    public Pawn(String col, PieceTypeEnum type, String path){
        super(col, type, path);
        promotionPossible = false;
        enPassantable = false;
    }
    
    public void setEnPassantable (Boolean en){
        enPassantable = true;
    }
    
    public Boolean getEnPassantable (){
        return enPassantable;
    }   
    
    public void setPromotionPossible (Boolean promotion){
        promotionPossible = promotion;
    }
    
    public Boolean getPromotionPossible (){
        return promotionPossible;
    } 
}


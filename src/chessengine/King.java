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
public class King extends Piece{
    private Boolean inCheck = false;
    private Boolean canCastle = true;
    
    public King(String col, PieceTypeEnum type, String path){
        super(col, type, path);
    }
    
    public void setInCheck(Boolean check){
        inCheck = check;
    }
    
    public Boolean getInCheck(){
        return inCheck;
    }
    
    public void setCanCastle(Boolean castle){
        canCastle = castle;
    }
    
    public Boolean getCanCastle(){
        return canCastle;
    }
}

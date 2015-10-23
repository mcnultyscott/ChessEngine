/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessengine;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Scott
 */
public class Pawn extends Piece{
    Image pawnImage;
    Boolean hasMoved = false;
    Boolean promotionPossible = false;
    Boolean enPassantable = false;
    String pathForPNG;
    Image image;
    ImageView view;
    
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
    
    public Image getCurrentImage(){
        image = new Image(pathForPNG);
        return image;
    }
    
}


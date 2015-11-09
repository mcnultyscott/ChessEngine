/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessengine;

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author Scott
 */
public class PieceImageView extends ImageView{
    private Image image;
    private Piece piece;
    
    public PieceImageView(Piece p, double height, double width, double x, double y){
        image = new Image(p.pathForPNG);
        piece = p;
        this.setImage(image);
        this.setPreserveRatio(true);
        this.setFitHeight(height);
        this.setFitWidth(width);
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setCursor(Cursor.HAND);
    }
    
    public Piece getPiece(){
        return piece;
    }
}

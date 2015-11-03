/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessengine;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author Scott
 */
public class PieceImageView extends ImageView{
    Image image;
    Piece piece;
    
    public PieceImageView(Piece p, double height, double width, double x, double y){
        image = new Image(p.pathForPNG);
        piece = p;
        this.setImage(image);
        this.setPreserveRatio(true);
        this.setFitHeight(height);
        this.setFitWidth(width);
        this.setX(x);
        this.setY(y);
    }
    
    public Piece getPiece(){
        return piece;
    }
}

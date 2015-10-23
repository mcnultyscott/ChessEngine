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
public class Knight extends Piece{
    String pathForPNG;
    
    public Knight(String col, PieceTypeEnum type, String path){
        super(col, type);
        pathForPNG = path;
    }
}

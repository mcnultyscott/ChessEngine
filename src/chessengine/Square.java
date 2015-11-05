/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessengine;

import javafx.scene.shape.Rectangle;
/**
 *
 * @author Scott
 */
public class Square extends Rectangle{
    public int row;
    public int column;
    
    public Square(){
        
    }
    
    public Square(int r, int c, double x, double y, 
            double width, double height){
        
        super(x, y, width, height);
        row = r;
        column = c;
    }
    
    public int getRow(){
        return row;
    }
    
    public void setRow(int r){
        row = r;
    }
    
    public int getColumn(){
        return column;
    }
    
    public void setColumn(int c){
        column = c;
    }
}

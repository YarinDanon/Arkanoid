package com.yarin_haim.ex2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class BrickCollection {

    public Brick [][] arrayBrick;
    private int rows,columns;
    private boolean finish;


    public BrickCollection(float canvasWidth , float canvasHeight,int rows,int columns,int textSize){

        this.rows = rows;
        this.columns = columns;
        this.finish =false;
        arrayBrick = new Brick [rows][columns];
        float space = (float) ((canvasWidth/columns) * 0.05);
        float width = (float) ((canvasWidth/columns) * 0.95 - (space/columns));
        float height = (canvasHeight/4)/rows;
        float left = space;
        float top = space + textSize;
        for(int i = 0 ; i < rows ; i++)
        {
            for(int j = 0 ; j < columns ; j++)
            {
                Brick brick = new Brick(left, top, left+width, top+height);
                arrayBrick[i][j] = brick;
                left = left + width + space;
            }
            top = top + height +space;
            left = space;
        }
    }
    public void draw(Canvas canvas){
        Paint  p = new Paint();
        p.setColor(Color.BLUE);
        boolean empty = true;
        for(int i = 0 ; i < rows ; i++)
            for(int j = 0 ; j < columns ; j++)
                if(!arrayBrick[i][j].getHit()) {
                    arrayBrick[i][j].draw(canvas, p);
                    empty =false;
                }
        if(empty)
            this.finish = true;
    }

    public void reset(){
        for(int i = 0 ; i < rows ; i++)
            for(int j = 0 ; j < columns ; j++)
                arrayBrick[i][j].setHit(false);

        this.finish = false;
    }
    public boolean getFinish(){
        return this.finish;
    }
    public Brick[][] getArrayBrick(){
        return arrayBrick;
    }
    public int getRows(){
        return this.rows;
    }
    public int getColumns(){
        return this.columns;
    }
}

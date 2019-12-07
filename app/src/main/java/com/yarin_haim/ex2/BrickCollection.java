package com.yarin_haim.ex2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class BrickCollection {

    public Brick [][] arrayBrick;
    private int rows,columns;


    public BrickCollection(float canvasWidth , float canvasHeight,int rows,int columns){

        this.rows = rows;
        this.columns = columns;
        arrayBrick = new Brick [rows][columns];
        float space = (float) ((canvasWidth/columns) * 0.1);
        float width = (float) ((canvasWidth/columns) * 0.9 - (space/columns));
        float height = (canvasHeight/3)/rows;
        float left = space;
        float top = space + 40;
        String ss = canvasWidth + "  " + canvasHeight;
        Log.d("screen size",ss);
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
        for(int i = 0 ; i < rows ; i++)
            for(int j = 0 ; j < columns ; j++)
                if(!arrayBrick[i][j].getHit())
                    arrayBrick[i][j].draw(canvas, p);

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

package com.yarin_haim.ex2;

import android.graphics.Canvas;
import android.graphics.Paint;

public class BrickCollection {

    public Brick [][] arrayBrick;
    private int rows,columns;


    public BrickCollection(float canvasWidth , float canvasHeight, Canvas canvas, Paint p){

        this.rows = 4;
        this.columns = 5;
        arrayBrick = new Brick [4][5];
        float x = canvasWidth*(float)(0.024);
        float y = canvasHeight*(float)(0.06);
        for(int i = 0 ; i < 4 ; i++)
        {
            for(int j = 0 ; j < 5 ; j++)
            {
                Brick brick = new Brick(x, y, x + canvasWidth * (float) (0.13), y + canvasHeight * (float) (0.063));
                arrayBrick[i][j] = brick;
                canvas.drawRect( x,y,x+canvasWidth*(float)(0.13),y+canvasHeight*(float)(0.063),p);
                x+=canvasWidth*(float)(0.137);
            }
            y+=canvasHeight*(float)(0.07);
            x = canvasWidth*(float)(0.024);
        }

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

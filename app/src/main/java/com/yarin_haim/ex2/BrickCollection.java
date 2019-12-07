package com.yarin_haim.ex2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BrickCollection {

    public Brick [][] arrayBrick;
    private int rows,columns;


    public BrickCollection(float canvasWidth , float canvasHeight){

        this.rows = 4;
        this.columns = 7;
        arrayBrick = new Brick [rows][columns];
        float x = canvasWidth*(float)(0.024);
        float y = canvasHeight*(float)(0.06);
        for(int i = 0 ; i < rows ; i++)
        {
            for(int j = 0 ; j < columns ; j++)
            {
                Brick brick = new Brick(x, y, x + canvasWidth * (float) (0.13), y + canvasHeight * (float) (0.063));
                arrayBrick[i][j] = brick;
                x+=canvasWidth*(float)(0.137);
            }
            y+=canvasHeight*(float)(0.07);
            x = canvasWidth*(float)(0.024);
        }

    }

    public void draw(Canvas canvas){
        Paint  p = new Paint();
        p.setColor(Color.BLUE);
        for(int i = 0 ; i < rows ; i++)
            for(int j = 0 ; j < columns ; j++)
                if(!arrayBrick[i][j].getHit())
                    canvas.drawRect(arrayBrick[i][j].getLeft(),arrayBrick[i][j].getTop() ,arrayBrick[i][j].getRight(),arrayBrick[i][j].getBottom(),p);
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

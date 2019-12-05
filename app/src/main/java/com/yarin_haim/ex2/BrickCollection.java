package com.yarin_haim.ex2;

public class BrickCollection {

    private Brick [][] arrayBrick;

    public BrickCollection(int canvasWidth , int canvasHeight){

        arrayBrick = new Brick [7][4];
        for(int i = 0 ; i < 7 ; i++)
        {
            for(int j = 0 ; j < 4 ; j++)
            {
                Brick b = new Brick( 50+(i*250),(canvasHeight/12+100)+(j*100),200+(i*canvasWidth/7),150+(canvasHeight/12)*j);
                arrayBrick[i][j] = b;
            }
        }

    }
}

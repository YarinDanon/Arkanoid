package com.yarin_haim.ex2;


import android.widget.Toast;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;



public class GameView extends View {

    private Score scoreObj;
    private boolean isTouch = false;
    private int canvasWidth;
    private int canvasHeight;

    public GameView (Context context , AttributeSet atr){
        super(context,atr);

    }

    protected void onDraw(Canvas canvas) {

        if(this.scoreObj == null ) {
            initGame();
        }
        super.onDraw(canvas);
        Paint p = new Paint();
        Paint text = new Paint();
        Paint data = new Paint();
        text.setTextSize(40);
        text.setColor(Color.RED);
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.FILL);


        canvas.drawText("Score: "+scoreObj.getScorePoint() , 50, 50 ,text);
        canvas.drawText("Lives: "+scoreObj.getLives() , canvasWidth-150, 50 ,text);
        /*
        for(int i = 0 ; i < 10 ; i++)
        {
            canvas.drawRect(50+(i*250),100,200+(i*250),150,p);
        }
         */


        //Brick [][] arrayBrick = new Brick [7][4];
        for(int i = 0 ; i < 7 ; i++)
        {
            for(int j = 0 ; j < 4 ; j++)
            {
                canvas.drawRect( 50+(i*250),(j*100)+100,200+(i*canvasWidth/7),150+(canvasHeight/12)*j,p);
                //Brick b = new Brick( 50+(i*250),(canvasHeight/12+100)+(j*100),200+(i*canvasWidth/7),150+(canvasHeight/12)*j);
                //arrayBrick[i][j] = b;
            }
        }


        if(isTouch == true)
        {
            if(scoreObj.getLives() == 0){
                initGame();

            }
            canvas.drawText("PLAYING MODE" , canvasWidth/2, canvasHeight/2 ,text);
            //if(ballfall)
                this.isTouch = false;
                this.scoreObj.updateLives();

        }
        else {
            if(scoreObj.getLives() == 0)
            {
                canvas.drawText("Game Over Click to PLAY AGAIN!", canvasWidth / 2, canvasHeight / 2, text);
            }
            else {

                canvas.drawText("Click to PLAY!", canvasWidth / 2, canvasHeight / 2, text);

            }
        }
        invalidate();

    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasWidth = w;
        canvasHeight = h;
    }

    public boolean onTouchEvent(MotionEvent event) {

        int X = (int) event.getX();
        int Y = (int) event.getY();
        int e = event.getAction();

        switch (e) {
            case MotionEvent.ACTION_DOWN:
                isTouch = true;
                break;
        }
        invalidate();
        return true;
    }

    public void initGame(){

        this.scoreObj = new Score();

    }

}


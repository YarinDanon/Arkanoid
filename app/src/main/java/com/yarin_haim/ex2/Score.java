package com.yarin_haim.ex2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import static android.graphics.Paint.Align.LEFT;
import static android.graphics.Paint.Align.RIGHT;

public class Score {

    private int scorePoint , lives ;
    private int textSize;
    private float canvasWidth;

    public Score(float canvasWidth){
        this.canvasWidth = canvasWidth;
        init();
    }

    public void init(){
        this.scorePoint = 0;
        this.lives = 3;
        textSize = 40;
    }
    public int getTextSize(){
        return this.textSize;
    }

    public void draw(Canvas canvas){
        Paint text = new Paint();
        text.setTextSize(this.textSize);
        text.setColor(Color.RED);

        canvas.drawText("Score: " + this.scorePoint , this.textSize, this.textSize ,text);
        text.setTextAlign(RIGHT);
        canvas.drawText("Lives: "+ this.lives , canvasWidth - textSize, this.textSize ,text);
    }

    public int updateScore() {
        this.scorePoint+= 5*lives ;
        return this.scorePoint;
    }

    public int getScorePoint(){
        return this.scorePoint;
    }

    public int updateLives (){

        this.lives--;
        return  this.lives;
    }

    public int getLives(){
        return this.lives;
    }

}

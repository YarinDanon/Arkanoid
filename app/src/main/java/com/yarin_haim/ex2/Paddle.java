package com.yarin_haim.ex2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;


public class Paddle {

    private float x,y;
    private int width,height,color;
    private int balance = 0;
    private static final int IN_BALANCE = 5;


    public Paddle(int width,int height,int color){
        this.width = width;
        this.height = height;
        this.color = color;
    }
    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }

    public void initPaddle(float widthScreen,float heightScreen){
        this.x = widthScreen/2 - width/2;
        this.y = (float) (heightScreen - height*2);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void draw(Canvas canvas){
        Paint pPaddle = new Paint();
        pPaddle.setColor(this.color);
        canvas.drawRoundRect(this.x,this.y,this.x+this.width,this.y+this.height,10,10,pPaddle);
    }
    public void move(float widthScreen){
        int SPACE = 10;
        if(this.x+this.balance > SPACE && this.x+this.balance < widthScreen - this.width - SPACE)
            this.x += this.balance;
    }

    public void setBalance(float x){
        if(Math.abs(x)*180 > IN_BALANCE) {
            if (x < 0)
                this.balance = -5;
            else
                this.balance = 5;
        }
        else
            this.balance = 0;
    }

}

package com.yarin_haim.ex2;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Brick {

    private float left,top,right,bottom;
    private boolean isHit;

    public Brick(float left,float top, float right,float bottom){

        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.isHit = false;
    }

    public void draw(Canvas canvas, Paint p){
        canvas.drawRect(this.left,this.top ,this.right,this.bottom,p);
    }

    public void hit() {
        this.isHit = true;
    }
    public boolean getHit(){
        return this.isHit;
    }

    public float getLeft(){
        return this.left;
    }

    public float getTop(){
        return this.top;
    }

    public float getRight(){
        return this.right;
    }

    public float getBottom(){
        return this.bottom;
    }


}

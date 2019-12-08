package com.yarin_haim.ex2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Ball {
    private double[] vector;
    private float x,y,radius;
    private int color;
    private int SPEED = 3;
    private boolean isFall =false;


    public Ball(int radius,int color,Paddle p){
        this.radius = radius;
        this.color = color;
        this.vector = new double[2];
        init(p);
    }
    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    public float getRadius(){
        return this.radius;
    }

    public void draw(Canvas canvas)
    {
        Paint ballPen = new Paint();
        ballPen.setColor(this.color);
        canvas.drawCircle(this.x,this.y, this.radius, ballPen);
    }

    public void init(Paddle p) {
        this.x = p.getX() + p.getWidth()/2;
        this.y = p.getY() - this.radius - 1;
        this.vector[0] = (Math.random() * 2 - 1) * this.SPEED;
        this.vector[1] =  this.SPEED * -1;
        this.isFall = false;
    }

    public int getSPEED(){
        return this.SPEED;
    }
    public boolean getIsFall(){
        return this.isFall;
    }
    public void setIsFall(boolean b){
        this.isFall = b;
    }
    public void setVetor(int i,double v){
        this.vector[i] *= v;
    }
    public double getVector(int i){
        return this.vector[i];
    }

    public void move(){
        this.x += this.vector[0];
        this.y += this.vector[1];
    }

    public boolean Ycollision(float y){
        if(y > this.y - this.radius && y < this.y + this.radius)
            return true;
        return false;
    }
    public boolean Xcollision(float x){
        if(x > this.x - this.radius && x < this.x + this.radius)
            return true;
        return false;
    }
    public void setVectorPaddleCollision(double v){
        this.vector[0] = v;
    }


}

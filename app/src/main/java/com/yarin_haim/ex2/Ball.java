package com.yarin_haim.ex2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Ball {
    private double[] vector;
    private float x,y,radius;
    private int color;
    private int SPEED = 3;
    private boolean isHit = false;

    public Ball(int x,int y,int radius,int color,Paddle p){
        this.radius = radius;
        this.color = color;
        this.vector = new double[2];
        init(p);
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
        this.vector[0] = (Math.random() * 2 - 1) + this.SPEED;
        this.vector[1] =  this.SPEED;
    }
//BrickCollection bc,
    public void move(Paddle p,int widthSrceen,int heightScreen){
        this.x += vector[0];
        this.y += vector[1];

        if(this.y >= p.getY())
            return;
        checkBallHit(p,widthSrceen,heightScreen);
    }

//BrickCollection bc, Paddle p,
    private void checkBallHit(Paddle p,int widthSrceen,int heightScreen) {

        // screen hit
        if(this.x - this.radius <= 0 || this.x + this.radius >= widthSrceen)
            this.vector[0]*= -1;
        if(this.y - this.radius <= 0)
            this.vector[1] *= -1;

        // paddle hit
        if(this.x -this.radius - this.vector[0] < p.getX()+p.getWidth() && this.x + this.radius + vector[0] > p.getX() && this.y + this.radius + vector[1] > p.getY() ){
            if(!isHit) {
                double facrot = ((p.getX() + (p.getWidth() / 2)) - this.x) / (p.getWidth() / 2);
                this.vector[0] = (facrot * SPEED) * -1;
                this.vector[1] *= -1;
            }
            isHit = true;
        }
        else
            isHit = false;

    }

}

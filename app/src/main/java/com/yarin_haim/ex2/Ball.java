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

    public Ball(float x,float y,int radius,int color,Paddle p){
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
        this.vector[0] = (Math.random() * 2 - 1) + this.SPEED;
        this.vector[1] =  this.SPEED;
    }
    public void move(BrickCollection bricks,Paddle p,float widthSrceen,float heightScreen){
        this.x += vector[0];
        this.y += vector[1];

        if(this.y-this.radius >= p.getY()) { // lose
            vector[1] *= -1;
            return;
        }
        checkBallHit(bricks,p,widthSrceen,heightScreen);
    }

    private void checkBallHit(BrickCollection bricks,Paddle p,float widthSrceen,float heightScreen) {

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


        // brick hit
        for (int i=0 ; i<bricks.getRows();i++){
            for(int j = 0 ; j < bricks.getColumns() ; j++){
                Brick brick = bricks.getArrayBrick()[i][j];

                if(brick.getRight() >= this.x - this.radius && brick.getLeft() <= this.x + this.radius && brick.getBottom() >= this.y - this.radius && brick.getTop() <= this.y + this.radius){
                    // hit from the sides
                    if(this.x > brick.getRight() || this.x < brick.getLeft())
                        this.vector[0] *= -1;
                    else // hit from the bottom or from the top
                        this.vector[1] *= -1;
                    brick.hit();
                    return;
                }
            }
        }

    }

}

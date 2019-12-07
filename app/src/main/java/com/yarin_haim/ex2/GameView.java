package com.yarin_haim.ex2;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;


public class GameView extends View implements SensorEventListener {

    private boolean reGame;
    private Score scoreObj;
    private boolean isTouch = false;
    private float canvasWidth;
    private float canvasHeight;
    private Ball ball;
    private Paddle paddle;
    private BrickCollection bricks;

    GameView(Context context) {
        this(context, null);
    }

    public GameView (Context context , AttributeSet atr){
        super(context,atr);
        paddle = new Paddle(250,30,Color.RED);
        fork();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        p.setTextSize(70);
        p.setColor(Color.YELLOW);
        p.setStyle(Paint.Style.FILL);

        canvas.drawText("Score: "+scoreObj.getScorePoint() , 50, 50 ,text);
        canvas.drawText("Lives: "+scoreObj.getLives() , canvasWidth-150, 50 ,text);

        if(isTouch == true) {

            if(reGame)
            {
                this.reGame = false;
                canvas.drawText("Click to PLAY!", (canvasWidth / 2)-150, (canvasHeight / 2)+100, p);
                paddle.initPaddle(canvasWidth,canvasHeight);
                ball.init(paddle);
                initGame();
                isTouch = false;
            }

            else if (this.ball.getIsFall()) {
                this.isTouch = false;
                this.scoreObj.updateLives();
            }
            else if(bricks.getFinish()){
                this.isTouch = false;
                this.reGame = true;
            }
            ball.move(bricks,paddle,canvasWidth,canvasHeight,scoreObj);
        }
        else {
            if(reGame){
                canvas.drawText("YOU WON!! Click to PLAY AGAIN!", (canvasWidth / 2) - 500, (canvasHeight / 2) + 100, p);
            }
            else if(scoreObj.getLives() == 0)
            {
                canvas.drawText("Game Over Click to PLAY AGAIN!", (canvasWidth / 2) - 500, (canvasHeight / 2)+100, p);
                paddle.initPaddle(canvasWidth,canvasHeight);
                ball.init(paddle);
                this.reGame = true;

            }
            else {

                canvas.drawText("Click to PLAY!", (canvasWidth / 2)-150, (canvasHeight / 2)+100, p);
                paddle.initPaddle(canvasWidth,canvasHeight);
                ball.init(paddle);
            }
        }

        bricks.draw(canvas);
        ball.draw(canvas);
        paddle.draw(canvas);
        paddle.move(canvasWidth);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasWidth = w;
        canvasHeight = h;

        this.reGame = false;
        paddle.initPaddle(canvasWidth,canvasHeight);
        ball = new Ball(20,Color.GREEN,paddle);
        bricks = new BrickCollection(canvasWidth,canvasHeight,4,7);

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
        return true;
    }

    public void initGame(){

        paddle.initPaddle(canvasWidth,canvasHeight);
        ball.init(paddle);
        this.scoreObj = new Score();
        bricks = new BrickCollection(canvasWidth,canvasHeight,4,7);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType())
        {
            case Sensor.TYPE_ORIENTATION:
                // Get Sensor Orientation Angles (in degrees) around 3 axis
                float x = sensorEvent.values[0];  // Pitch (the angle around the x-axis)
                paddle.setBalance(x);
                break;
            default:
                // do nothing
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void fork(){

        new Thread(new Runnable() {
            @Override
            public void run()
            {
                while(true)
                {
                    if(isTouch)
                        postInvalidate();
                }
            }
        }).start();
    }
}
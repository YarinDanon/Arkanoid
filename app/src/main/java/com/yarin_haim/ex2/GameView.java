package com.yarin_haim.ex2;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.MediaPlayer;
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

import static android.graphics.Paint.Align.CENTER;
import static android.graphics.Paint.Align.LEFT;


public class GameView extends View implements SensorEventListener {

    private MediaPlayer mp;
    private Score scoreObj;
    private float canvasWidth;
    private float canvasHeight;
    private Ball ball;
    private Paddle paddle;
    private BrickCollection bricks;
    private ScreenManager screenManager;
    private int State;
    private boolean running;
    private String title;
    public static final int GET_READY = 1,PLAYING = 2 , GAME_OVER = 3 ,
                            ROWS = 4 , COLUMNS = 7;

    GameView(Context context) {
        this(context, null);
    }

    public GameView (Context context , AttributeSet atr){
        super(context,atr);
        paddle = new Paddle(250,30,Color.RED);
        State = GET_READY;
        running = false;
        fork();
        mp = MediaPlayer.create(context,R.raw.music);
        mp.setLooping(false);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasWidth = w;
        canvasHeight = h;

        paddle.initPaddle(canvasWidth,canvasHeight);
        ball = new Ball(20,Color.GREEN,paddle);
        scoreObj = new Score(canvasWidth);
        bricks = new BrickCollection(canvasWidth,canvasHeight,ROWS,COLUMNS,scoreObj.getTextSize());

        screenManager = new ScreenManager(paddle,ball,bricks,scoreObj,canvasWidth,canvasHeight,mp);
        screenManager.initScreen();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("state",State + " ");
        switch (State) {
            case GET_READY:
                running = false;
                title = "Click to PLAY!";
                break;

            case PLAYING:
                title = "";
                screenManager.updateScreen();
                if(ball.getIsFall()) {
                    scoreObj.updateLives();
                    paddle.initPaddle(canvasWidth,canvasHeight);
                    ball.init(paddle);
                    State = GET_READY;
                }
                if(bricks.getFinish() || scoreObj.getLives() == 0)
                    State = GAME_OVER;
                break;

            case GAME_OVER:
                running = false;
                if(bricks.getFinish())
                    title = "YOU WON!! Click to PLAY AGAIN!";
                if(scoreObj.getLives() == 0)
                    title = "Game Over Click to PLAY AGAIN!";
                break;
        }

        scoreObj.draw(canvas);
        bricks.draw(canvas);
        ball.draw(canvas);
        paddle.draw(canvas);
        drawTitle(canvas,title);

    }

    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            switch (State) {
                case GET_READY:
                    State = PLAYING;
                    running = true;
                    break;
                case PLAYING: // do nothing
                    break;
                case GAME_OVER:
                    screenManager.initScreen();
                    State = GET_READY;
                    invalidate();
                    break;
            }
        }
        return true;
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType())
        {
            case Sensor.TYPE_ORIENTATION:
                float x = sensorEvent.values[0];  // Pitch (the angle around the x-axis)
                paddle.setBalance(x);
                break;
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
                    if(running)
                        postInvalidate();
                }
            }
        }).start();
    }


    public void drawTitle(Canvas canvas,String text){
        Paint p = new Paint();
        p.setTextSize(70);
        p.setColor(Color.YELLOW);
        p.setStyle(Paint.Style.FILL);
        p.setTextAlign(CENTER);
        canvas.drawText(text,canvasWidth/2,canvasHeight/2,p);
    }
}
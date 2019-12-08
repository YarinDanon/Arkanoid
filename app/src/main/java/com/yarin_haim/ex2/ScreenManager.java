package com.yarin_haim.ex2;

import android.media.MediaPlayer;
import android.util.Log;

public class ScreenManager {
    private Score score;
    private float screenWidth;
    private float screenHeight;
    private Ball ball;
    private Paddle paddle;
    private BrickCollection bricks;
    private boolean inPaddleCollision = false;
    private MediaPlayer mp;

    public ScreenManager(Paddle paddle, Ball ball, BrickCollection bricks, Score score, float screenWidth, float screenHeight,MediaPlayer mp) {
        this.ball = ball;
        this.bricks = bricks;
        this.paddle = paddle;
        this.score = score;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.mp = mp;
    }

    public void initScreen() {
        paddle.initPaddle(screenWidth, screenHeight);
        ball.init(paddle);
        bricks.reset();
        score.init();
    }

    public void updateScreen() {
        paddle.move(screenWidth);
        ball.move();
        if (ball.getY() - ball.getRadius() >= paddle.getY()) {
            ball.setIsFall(true); // the ball fall
            return;
        }

        if(ScreenCollision())
            return;
        else if (paddleCollision())
            return;
        else if (bricksCollision()) {
            score.updateScore();
            mp.start();
        }

    }

    private boolean ScreenCollision() {
        if (ball.Xcollision(0) || ball.Xcollision(screenWidth))
            ball.setVetor(0, -1);
        else if (ball.Ycollision(0))
            ball.setVetor(1, -1);
        else
            return false;
        return true;

    }

    private boolean paddleCollision() {
        if (ball.getX() - ball.getRadius() - ball.getSPEED() < paddle.getX() + paddle.getWidth() && ball.getX() + ball.getRadius() + ball.getSPEED()> paddle.getX() && ball.getY() + ball.getRadius() + ball.getSPEED() > paddle.getY()) {
            if (!inPaddleCollision) {
                double facrot = ((paddle.getX() + (paddle.getWidth() / 2)) - ball.getX()) / (paddle.getWidth() / 2);
                facrot*= ball.getSPEED()*-1;
                ball.setVectorPaddleCollision(facrot);
                ball.setVetor(1, -1);
            }
            inPaddleCollision = true;
            return true;
        } else
            inPaddleCollision = false;
        return false;
    }

    private boolean bricksCollision() {
        for (int i = 0; i < bricks.getRows(); i++) {
            for (int j = 0; j < bricks.getColumns(); j++) {
                Brick brick = bricks.getArrayBrick()[i][j];
                if (!brick.getHit() && brick.getRight() >= ball.getX() - ball.getRadius() && brick.getLeft() <= ball.getX() + ball.getRadius() && brick.getBottom() >= ball.getY() - ball.getRadius() && brick.getTop() <= ball.getY() + ball.getRadius()) {
                    int a = (int) (ball.getRadius() - ball.getSPEED());
                    if (ball.getX() - brick.getRight() < a && ball.getX() - brick.getLeft() > -a)
                        ball.setVetor(1, -1);
                    else if (ball.getY() - brick.getBottom() < a && ball.getY() - brick.getTop() > -a)
                        ball.setVetor(0, -1);
                    else {
                        if((ball.getVector(0) > 0 && ball.getX() - ball.getRadius() > brick.getLeft()) || (ball.getVector(0) < 0 && ball.getX() + ball.getRadius() > brick.getRight()))
                            ball.setVetor(1,-1);
                        else if((ball.getVector(1) > 0 && ball.getY()- ball.getRadius() > brick.getTop()) || (ball.getVector(1) < 0 && ball.getY() + ball.getRadius() < brick.getBottom()))
                            ball.setVetor(0,-1);
                        else {
                            ball.setVetor(0, -1);
                            ball.setVetor(1, -1);
                        }
                    }
                    brick.setHit(true);
                    return true;
                }
            }
        }
        return false;
    }
}

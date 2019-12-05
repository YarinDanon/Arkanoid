package com.yarin_haim.ex2;

public class Score {

    private int scorePoint , lives ;

    public Score(){

        this.scorePoint = 0;
        this.lives = 3;
    }

    public int updateScore(int scorePoint) {
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

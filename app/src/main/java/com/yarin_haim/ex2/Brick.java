package com.yarin_haim.ex2;

public class Brick {

    private int left,top,right,bottom;
    private boolean isHit;

    public Brick(int left,int top, int right,int bottom){

        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.isHit = false;


    }

    public void hit() {

        this.isHit = true;
    }



}

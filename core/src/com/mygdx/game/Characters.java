package com.mygdx.game;

public interface Characters {
    public void move(int x, int y);
    public void aim(int x, int y);
    public void damage();
    public void setX(int x);
    public void setY(int y);
}

package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Characters {
    public void move(int x, int y);
    public int aim(float x, float y);
    public void damage(Characters w1, Characters h1);
    public void setX(int x);
    public void setY(int y);
    public String getName();
    public void setAll(int X, int Y, int width, int height);
    public void draw(SpriteBatch batch, float delta);
    public float getX();
    public float getY();
    public void setRun(int run);
    public double getAngle();
    public int getRun();

    GameStuff getHealthbar();
}

package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Clickables {
    public boolean isClicked(int x, int y);
    public void draw(SpriteBatch batch, float delta);
    public float getX();
    public float getHeight();
    public float getWidth();
    public float getY();
    public String getName();

}

package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

class Weapon{}
class Buttons extends Actor implements Clickables{
	String name;
	int x;
	int y;
	int width;
	int height;
	public Buttons(String name, int x, int y, int width, int height){
		this.name=name;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	public void draw(SpriteBatch batch, float delta){
		Texture texture=new Texture(Gdx.files.internal(this.name));
		batch.draw(texture, this.x, this.y, this.width, this.height);
	}
	@Override
	public boolean isClicked(int x, int y) {
		if(x>=this.x && x<=this.x+this.width && y>=this.y && y<=this.y+height){
			return true;
		}
		return false;
	}

	@Override
	public void move(int x, int y) {

	}
	public String getName() {
		return name;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}
}
class Tank extends Actor implements Clickables{
	private String name;
	private int x;
	private int y;
	private int width;
	private int height;

	@Override
	public boolean isClicked(int x, int y) {
		return false;
	}
	public void move(int x, int y){

	}
	public void draw(SpriteBatch batch, float delta){
		Texture texture=new Texture(Gdx.files.internal(this.name));
		batch.draw(texture, this.x, this.y, this.width, this.height);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}
}
public class MyGdxGame extends Game {
	public SpriteBatch batch;
	public Stage stage;
	@Override
	public void create() {
		Gdx.graphics.setWindowedMode(800,600);
		batch=new SpriteBatch();
		stage=new Stage();
		this.setScreen(new Tank_Intro(this));
	}
	public void render(){
		super.render();
	}
	public void dispose(){
		batch.dispose();
		stage.dispose();
	}
}
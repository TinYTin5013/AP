package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import jdk.internal.icu.text.UnicodeSet;

import java.util.ArrayList;
import java.util.Iterator;

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
class Tank extends Actor implements Clickables, Characters, Runnable{
	private String name;
	private int x;
	private int y;
	private int width;
	private int height;
	private int health;
	private double angle;
	private int run;
	public Tank(String name, int x, int y, int width, int height){
		this.name=name;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}

	@Override
	public boolean isClicked(int x, int y) {
		return false;
	}
	public void move(int x, int y){

	}

	@Override
	public void aim(int x, int y) {
		double test=Math.tanh((y-this.y)/(x-this.x));
		if(test>180){return;}
		this.angle=test;
	}
	@Override
	public void damage() {

	}

	@Override
	public void setX(int x) {
		this.x=x;
	}

	@Override
	public void setY(int y) {
		this.y=y;
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

	public void setAll(int X, int Y, int width, int height){
		this.x=X;
		this.y=Y;
		this.width=width;
		this.height=height;
	}

	public void setHealth(int health) {this.health = health;}

	@Override
	public void run() {

	}
}

public class MyGdxGame extends Game implements Iterable{
	public SpriteBatch batch;
	public Stage stage;
	private ArrayList<Tank> tankBoy;
	public Iterator tankList;
	@Override
	public void create() {
		Gdx.graphics.setWindowedMode(800,600);
		tankBoy=new ArrayList<Tank>();
		this.initialiseTank();
		batch=new SpriteBatch();
		stage=new Stage();
		this.setScreen(new Tank_Intro(this));
	}
	public void render(){
		super.render();
	}
	public void initialiseTank(){
		Tank tank=new Tank("Basics/Abrams_Tank.png", 0,0,0,0);

		tankBoy.add(tank);
		tank=new Tank("Basics/Atomic_Tank.png", 0,0,0,0);
		tankBoy.add(tank);
		tank=new Tank("Basics/Mark_Tank.png", 0,0,0,0);
		tankBoy.add(tank);
		tank=new Tank("Basics/Frost_Tank.png", 0,0,0,0);
		tankBoy.add(tank);
		tank=new Tank("Basics/Helios_Tank.png", 0,0,0,0);
		tankBoy.add(tank);
	}
	public void dispose(){
		batch.dispose();
		stage.dispose();
	}

	@Override
	public Iterator createIterator() {
		return tankBoy.iterator();
	}
}

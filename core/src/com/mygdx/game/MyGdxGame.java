package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

class Weapon extends Actor implements Characters, Serializable{
	private String name;
	private int x;
	private int y;
	private int width;
	private int height;
	private int damage;
	public Weapon(String name, int x, int y, int width, int height){
		this.name=name;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	@Override
	public void move(int x, int y) {

	}

	@Override
	public int aim(float x, float y) {return 0;}

	@Override
	public void damage(Characters w1, Characters h1) {

	}
	public int collided(Tank t1){
		if(t1.getX()-t1.getWidth()/2<=x && t1.getX()+t1.getWidth()/2>=x && t1.getY()+t1.getHeight()/2>=y && t1.getY()-t1.getHeight()/2<=y){
			return 1;
		}
		return 0;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public void setX(int x) {
		this.x=x;
	}
	public String getName() {
		return name;
	}
	@Override
	public void setY(int y) {
		this.y=y;
	}

	@Override
	public void setAll(int X, int Y, int width, int height) {
		this.x=X;
		this.y=Y;
		this.width=width;
		this.height=height;
	}

	@Override
	public void draw(SpriteBatch batch, float delta) {
		Texture texture=new Texture(Gdx.files.internal(this.name));
		batch.draw(texture, this.x, this.y, this.width, this.height);
	}

	@Override
	public void setRun(int run) {}

	@Override
	public double getAngle() {
		return 0;
	}

	@Override
	public int getRun() {return 0;}

	@Override
	public GameStuff getHealthbar() {
		return null;
	}
}
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
class Tank extends Actor implements Clickables, Characters, Runnable, Serializable {
	private String name;
	private int x;
	private int y;
	private int width;
	private int height;
	private int health;
	private double angle;
	private int run;
	public Tank(String name, int x, int y, int width, int height) {
		this.name=name;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.health=100;
		this.run=80;
		this.angle=45;
	}
	@Override
	public boolean isClicked(int x, int y) {
		return false;
	}
	public void move(int pos_X_One, int y){
		if(this.run>0){
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				pos_X_One+=1f;
				if(pos_X_One>800){
					pos_X_One=800;
				}else{
					this.run-=1f;
				}

			}else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				pos_X_One-=1f;
				if(pos_X_One<0){
					pos_X_One=0;
				}else {this.run-=1f;}
			}
			this.setX((int)pos_X_One);
		}
	}

	@Override
	public int aim(float x, float y) {
		double test=this.angle;
		if(this.getY()>y){return (int)this.angle;}
		try{
			test=(90/57.29577945897575)*Math.toDegrees(Math.tanh((y-(this.getY()+(this.getHeight()/2)))/(x-(this.getX()+(this.getWidth()/2)))));
			this.angle=test;
		}finally {
			return (int)test;
		}

	}
	@Override
	public void damage(Characters w1, Characters h1) {
		if(w1.getX()<=this.getX()+this.getWidth() && w1.getX()>=this.getX()){
			h1.getHealthbar().decrease(20);
		}
	}

	public double getAngle() {
		return angle;
	}

	@Override
	public void setX(int x) {
		this.x=x;
	}

	@Override
	public void setY(int y) {
		this.y=y;
	}

	public void setRun(int run) {this.run = run;}

	public int getRun() {return run;}

	@Override
	public GameStuff getHealthbar() {
		return null;
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
interface GameStuff{
	public void decrease(int health);
}
class Health implements GameStuff{
	private String name;
	private int x;
	private int y;
	private int width;
	private int height;
	public Health(String name, int x, int y, int width, int height){
		this.name=name;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	@Override
	public void decrease(int health) {
		this.width-=health;
	};
	public void draw(SpriteBatch batch, float delta){
		Texture texture=new Texture(Gdx.files.internal(this.name));
		batch.draw(texture, this.x, this.y, this.width, this.height);
	}

	public String getName() {return name;}
	public int getX() {return x;}

	public int getY() {return y;}

	public int getWidth() {return width;}

	public int getHeight() {return height;}
}
class Healthbar extends Actor implements Characters{
	private Health healthbar;
	@Override
	public void move(int x, int y) {
		healthbar.decrease(x);
	}
	public void setHealthbar(Health healthbar) {
		this.healthbar = healthbar;
	}

	public Health getHealthbar() {
		return healthbar;
	}

	@Override
	public int aim(float x, float y) {return 0;}

	@Override
	public void damage(Characters w1, Characters h1) {

	}



	@Override
	public void setX(int x) {

	}

	@Override
	public void setY(int y) {

	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void setAll(int X, int Y, int width, int height){};

	@Override
	public void draw(SpriteBatch batch, float delta) {
		Texture bob=new Texture(Gdx.files.internal(this.healthbar.getName()));
		batch.draw(bob, this.healthbar.getX(), this.healthbar.getY(), this.healthbar.getWidth(), this.healthbar.getHeight());
	}

	@Override
	public void setRun(int run) {}

	@Override
	public double getAngle() {
		return 0;
	}

	@Override
	public int getRun() {return 0;}
}
public class MyGdxGame extends Game implements Serializable{
	public SpriteBatch batch;
	public Characters player_One;
	public Characters player_Two;
	public Characters weapon_One;
	public Characters weapon_Two;
	public Stage stage;
	public static ArrayList<Tank> tankList;
	public static ArrayList<Weapon> weaponList;
	public static String fileOne;
	public static String fileTwo;
	public static String fileThree;
	public static int file_int;
	public HashMap<Integer, String> mapper;
	@Override
	public void create() {
		file_int=1;
		Gdx.graphics.setWindowedMode(800,600);
		tankList=new ArrayList<Tank>();
		weaponList=new ArrayList<Weapon>();
		mapper=new HashMap<Integer, String>();
		this.initialiseTank();
		this.initialiseWeapons();
		batch=new SpriteBatch();
		stage=new Stage();
		this.setScreen(new Tank_Intro(this));
	}
	public void render(){
		super.render();
	}
	public void initialiseTank(){
		Tank tank=new Tank("Basics/Abrams_Tank.png", 0,0,0,0);
		tankList.add(tank);
		tank=new Tank("Basics/Atomic_Tank.png", 0,0,0,0);
		tankList.add(tank);
		tank=new Tank("Basics/Mark_Tank.png", 0,0,0,0);
		tankList.add(tank);
		tank=new Tank("Basics/Frost_Tank.png", 0,0,0,0);
		tankList.add(tank);
		tank=new Tank("Basics/Helios_Tank.png", 0,0,0,0);
		tankList.add(tank);
	}
	public void initialiseWeapons(){
		Weapon weapon=new Weapon("Basics/Bomb_One.png",0,0,0,0);
		weaponList.add(weapon);
		weapon=new Weapon("Basics/Bomb_Two.png",0,0,0,0);
		weaponList.add(weapon);
		weapon=new Weapon("Basics/Bomb_Three.png",0,0,0,0);
		weaponList.add(weapon);
		weapon=new Weapon("Basics/Bomb_Four.png",0,0,0,0);
		weaponList.add(weapon);
		weapon=new Weapon("Basics/Bomb_Five.png",0,0,0,0);
		weaponList.add(weapon);
	}
	public void dispose(){
		batch.dispose();
		stage.dispose();
	}
}

package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

//Range=us
public class Ground extends InputAdapter implements Screen {
    private Texture background;
    private Texture ground;
    private Texture versus;
    private Buttons Pause;
    private OrthographicCamera camera;
    private int angle;
    private int game_status;
    private boolean pause;
    private Healthbar health_One;
    private Healthbar health_Two;
    private Characters player_One;
    private Characters player_Two;
    private Characters weapon_One;
    private int storeOne;
    private int storeTwo;
    private Characters weapon_Two;
    private Clickables Fire;
    private MyGdxGame parent;
    private float pos_X_One;
    private float pos_X_Two;
    private int playable;
    private double thrownVelocity;
    private int fire;
    public Ground(MyGdxGame parent){
        this.parent=parent;
        background=new Texture(Gdx.files.internal("Basics/Background.png"));
        versus=new Texture(Gdx.files.internal("Basics/Versus.png"));
        ground=new Texture(Gdx.files.internal("Basics/Terrain.png"));
        health_One=new Healthbar();
        health_One.setHealthbar(new Health("Basics/Health_One.png", 140, 500, 180, 70));
        health_Two=new Healthbar();
        health_Two.setHealthbar(new Health("Basics/Health_Two.png", 600-135, 500, 180, 70));
        Pause=new Buttons("Basics/Pause.png", 50, 510, 50, 50);
        camera=new OrthographicCamera();
        Fire=new Buttons("Basics/Fire.png", 550, 50, 150, 100);
        player_One=parent.player_One;
        player_Two=parent.player_Two;
        weapon_One=parent.weapon_One;
        weapon_Two=parent.weapon_Two;
        player_One.setAll(100, 175, 64, 64);
        player_Two.setAll(632, 175, 64, 64);
        pos_X_One=100;
        pos_X_Two=632;
        fire=0;
        storeOne=0;
        storeTwo=0;
        parent.stage.addActor((Actor) player_One);
        parent.stage.addActor((Actor) player_Two);
        parent.stage.addActor(Pause);
        parent.stage.addActor(health_One);
        parent.stage.addActor(health_Two);
        playable=1;
        Gdx.input.setInputProcessor(parent.stage);
    }
    public void render(){

    }
    public void tank_Actions(Characters p1){
        Vector3 touch=new Vector3();
        camera.unproject(touch);
        touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        if(p1.equals(player_One)){
            p1.move((int)pos_X_One, 0);
            pos_X_One=p1.getX();
        }else if(p1.equals(player_Two)){
            p1.move((int)pos_X_Two,0);
            pos_X_Two=p1.getX();
        }
        if(Gdx.input.isTouched()){
            angle=p1.aim((int)touch.x, 600-(int)touch.y);
            storeOne=(int) (touch.x-p1.getX());
            storeTwo=600-(int) (touch.y-p1.getY());
            if(Fire.isClicked((int)touch.x, 600-(int)touch.y)){
                fire=1;
                playable=3^playable;
                System.out.println(playable);
            }
        }
    }
    public void game_Update(int player){
        Vector3 touch=new Vector3();
        camera.unproject(touch);
        touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        if(Pause.isClicked((int)touch.x, 600-(int)touch.y)){
            pause=true;
            try{
                Thread.sleep(200);
            }catch(Exception e){
                e.printStackTrace();
            }
        }if(player==1){
            tank_Actions(player_One);
            parent.batch.begin();
            parent.weapon_One.setAll((int)player_One.getX()+64, (int)player_One.getY()+50, 20, 20);
            parent.weapon_One.draw(parent.batch, 1f);
            parent.batch.end();
        }
        else if(player==2){
            tank_Actions(player_Two);
            parent.batch.begin();
            parent.weapon_Two.setAll((int)player_Two.getX(), (int)player_One.getY()+50, 20, 20);
            parent.weapon_Two.draw(parent.batch, 1f);
            parent.batch.end();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(1,1,1,0);
        camera.update();
        parent.batch.begin();
        parent.batch.draw(background, 0, 0, 800, 600);
        parent.batch.draw(versus,400-32, 500, 64, 64);
        parent.batch.draw(ground, 0, 0, 800, 200);
        Pause.draw(parent.batch, 1f);
        health_One.draw(parent.batch, 1f);
        health_Two.draw(parent.batch, 1f);
        player_One.draw(parent.batch, 1f);
        player_Two.draw(parent.batch, 1f);
        Fire.draw(parent.batch, 1f);
        parent.batch.end();
        if(Gdx.input.isTouched() && fire==0){
            Vector3 touch=new Vector3();
            camera.unproject(touch);
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            if(pause==true){
                if(touch.x>=250 && touch.x<=550 && touch.y<=600-370 && touch.y>=600-520){
                    pause=false;
                    try{
                        Thread.sleep(200);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                else if(touch.x>=250 && touch.x<=550 && touch.y<=600-215 && touch.y>=600-350){
                    ObjectOutputStream out=null;
                    try{
                        String gabe=Integer.toString(parent.file_int);
                        parent.file_int++;
                        out=new ObjectOutputStream(new FileOutputStream("PlayerOne_"+gabe+".txt"));
                        out.writeObject(parent.player_One);
                        out=new ObjectOutputStream(new FileOutputStream("PlayerTwo_"+gabe+".txt"));
                        out.writeObject(parent.player_Two);
                        out=new ObjectOutputStream(new FileOutputStream("WeaponeOne_"+gabe+".txt"));
                        out.writeObject(parent.weapon_One);
                        out=new ObjectOutputStream(new FileOutputStream("WeaponTwo_"+gabe+".txt"));
                        out.writeObject(parent.weapon_Two);
//                        out=new ObjectOutputStream(new FileOutputStream(gabe+".txt"));
//                        out.writeObject(parent);
                        if(!parent.mapper.containsKey(1)){
                            parent.mapper.put(1, gabe);
                        }else{
                            if(!parent.mapper.containsKey(2)){
                                parent.mapper.put(2,parent.mapper.get(1));
                            }else{
                                if(!parent.mapper.containsKey(3)){
                                    parent.mapper.put(3, parent.mapper.get(2));
                                }else{
                                    parent.mapper.remove(3);
                                    parent.mapper.put(3, parent.mapper.get(2));
                                }
                                parent.mapper.remove(2);
                                parent.mapper.put(2, parent.mapper.get(1));
                            }
                            parent.mapper.remove(1);
                            parent.mapper.put(1, gabe);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        if(out!=null){
                            try {
                                out.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    try{
                        Thread.sleep(200);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    try {
                        parent.stage = new Stage();
                        parent.setScreen(new Tank_Main(parent));
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
            }else if(pause==false){
                game_Update(playable);
            }
        }
        if(pause==false && fire==0){
            game_Update(playable);
        }
        if(fire==1){

        }
//        if(this.player_One()){
//            parent.batch.begin();
//            parent.batch.end();
//        }
        if(pause==true){
            parent.batch.begin();
            Color c=parent.batch.getColor();
            parent.batch.setColor(c.r, c.g, c.b, 0.9f);
            Texture t1=new Texture(Gdx.files.internal("Basics/Menu.png"));
            parent.batch.draw(t1, 75, 75, 650, 450);
            parent.batch.end();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
    public void dispose(){
        background.dispose();

    }
}

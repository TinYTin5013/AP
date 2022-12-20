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

public class Ground extends InputAdapter implements Screen {
    private Texture background;
    private Texture ground;
    private Texture versus;
    private Buttons Pause;
    private OrthographicCamera camera;
    private int game_status;
    private boolean pause;
    private Healthbar health_One;
    private Healthbar health_Two;
    private Characters player_One;
    private Characters player_Two;
    private MyGdxGame parent;
    float pos_X_One;
    float pos_X_Two;
    int player;
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
        player_One=parent.player_One;
        player_Two=parent.player_Two;
        player_One.setAll(100, 175, 64, 64);
        player_Two.setAll(632, 175, 64, 64);
        pos_X_One=100;
        pos_X_Two=632;
        parent.stage.addActor((Actor) player_One);
        parent.stage.addActor((Actor) player_Two);
        parent.stage.addActor(Pause);
        parent.stage.addActor(health_One);
        parent.stage.addActor(health_Two);
        Gdx.input.setInputProcessor(parent.stage);
    }
    public void render(){

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
            player_One.move((int)pos_X_One, 0);
            pos_X_One=player_One.getX();
        }else if(player==2){
            player_Two.move((int)pos_X_Two, 0);
            pos_X_Two=player_Two.getX();
            System.out.println(Gdx.graphics.getDeltaTime());
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
        parent.batch.end();
        if(Gdx.input.isTouched()){
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
                game_Update(2);
            }
        }
        if(pause==false){
            game_Update(2);
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

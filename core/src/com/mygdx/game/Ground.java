package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Ground extends ApplicationAdapter implements Screen {
    private Texture background;
    private Texture ground;
    private Texture versus;
    private Buttons Pause;
    private Rectangle rect;
    private OrthographicCamera camera;
    private int game_status;
    private boolean pause;
    private Tank player_One;
    private Tank player_Two;
    private final MyGdxGame parent;
    public Ground(MyGdxGame parent){
        this.parent=parent;
        Texture background=new Texture(Gdx.files.internal("Basics/Background.png"));
        Texture versus=new Texture(Gdx.files.internal("Basics/Versus.png"));
        Texture ground=new Texture(Gdx.files.internal("Basics/Terrain.png"));
        Healthbar health_One=new Healthbar();
        health_One.setHealthbar(new Health("Basics/Health_One.png", 140, 500, 180, 70));
        Healthbar health_Two=new Healthbar();
        health_Two.setHealthbar(new Health("Basics/Health_Two.png", 600-135, 500, 180, 70));
        Pause=new Buttons("Basics/Pause.png", 50, 510, 50, 50);
        camera=new OrthographicCamera();
        parent.stage.addActor(player_One);
        parent.stage.addActor(player_Two);
        parent.stage.addActor(Pause);
        Gdx.input.setInputProcessor(parent.stage);
    }
    public void render(){

    }
    public void game_Update(){
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
        }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        parent.batch.begin();;
        parent.batch.draw(background, 0, 0, 800, 600);
        parent.batch.draw(ground,0, 0, 800, 200);
        parent.batch.draw(versus,  400-32, 500, 64, 64);

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
            }else{
                game_Update();
            }
        }
//        if(this.player_One()){
//            parent.batch.begin();
//            parent.batch.end();
//        }
        if(pause==true){
            parent.batch.begin();
            Color c=parent.batch.getColor();
            parent.batch.setColor(c.r, c.g, c.b, 0.9f);
            Texture t1=new Texture(Gdx.files.internal("Menu.png"));
            parent.batch.draw(t1, 75, 75, 650, 450);
            parent.batch.end();
        }
    }

    @Override
    public void hide() {

    }
    public void dispose(){
        background.dispose();

    }
}

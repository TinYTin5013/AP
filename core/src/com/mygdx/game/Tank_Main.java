package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.SerializationException;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.w3c.dom.Text;
import java.awt.*;
import com.badlogic.gdx.math.Rectangle;
public class Tank_Main extends ApplicationAdapter implements Screen {
    private Texture bg;
    private Texture new_game;
    private Clickables tank_child;
    private OrthographicCamera camera;
    private Texture purple_bg;
    private Texture logo;
    private Clickables Vs_Computer;
    private Clickables Vs_Friend;
    private Clickables Back;
    private Clickables Settings;
    MyGdxGame parent;
    public Tank_Main(MyGdxGame parent){
        this.parent=parent;
        bg=new Texture(Gdx.files.internal("Basics/Blue_Bg.png"));
        purple_bg=new Texture(Gdx.files.internal("Basics/Purple.png"));
        new_game=new Texture(Gdx.files.internal("Basics/New_Game.png"));
        tank_child=new Buttons("Basics/Abrams_Tank.png",40, 125, 330, 250);
        logo=new Texture(Gdx.files.internal("Basics/Logo_Bland.png"));
        camera=new OrthographicCamera();
        Vs_Computer=new Buttons("Basics/New_Game.png",  550, 350, 150, 50);
        Vs_Friend=new Buttons("Basics/LOAD GAME.png", 550, 250, 150, 50);
        Settings=new Buttons("Basics/Settings.png", 550, 150, 150, 50);
        Back=new Buttons("Basics/BackButton.png", 0, 510, 80, 80);
        parent.stage.addActor((Actor) Vs_Computer);
        parent.stage.addActor((Actor) Vs_Friend);
        parent.stage.addActor((Actor) Settings);
        parent.stage.addActor((Actor) Back);
        parent.stage.addActor((Actor) tank_child);
        Gdx.input.setInputProcessor(parent.stage);
    }
    public void back(){
        bg.dispose();
        purple_bg.dispose();
        new_game.dispose();
        logo.dispose();
        parent.stage.dispose();
        try{
            Thread.sleep(200);
        }catch(Exception e){
            e.printStackTrace();
        }
        parent.stage=new Stage();
        parent.setScreen(new Tank_Intro(parent));
    }
    public void fire(){}
    public void openNewGame(){
        bg.dispose();
        purple_bg.dispose();
        new_game.dispose();
        logo.dispose();
        parent.stage.dispose();
        parent.stage=new Stage();
        parent.setScreen(new New_Game(parent));
    }
    public void openLoadGame(){
//                bg.dispose();
//                purple_bg.dispose();
//                new_game.dispose();
//                tank_child.dispose();
//                logo.dispose();
//                Vs_Computer.dispose();
//                Vs_Friend.dispose();
//                Back.dispose();
//                parent.stage=new Stage();
//                parent.setScreen(new Load_Game(parent));
    }
    public void openSettings(){}
    public void render(){

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        parent.batch.begin();
        parent.batch.draw(bg, 0,0, 400, 600);
        tank_child.draw(parent.batch, 1f);
        parent.batch.draw(purple_bg, 400,0, 400, 600);
        parent.batch.draw(logo, (400)-125, 400, 250, 150);
        Vs_Computer.draw(parent.batch, 1f);
        Vs_Friend.draw(parent.batch, 1f);
        Settings.draw(parent.batch, 1f);
        Back.draw(parent.batch, 1f);
        parent.batch.enableBlending();
        parent.batch.end();
        if(Gdx.input.isTouched()){
            Vector3 touch=new Vector3();
            camera.unproject(touch);
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            int timX=(int)touch.x;
            int timY=600-(int)touch.y;
            System.out.println(timY);
            System.out.println(Back.getY());
            if(Back.isClicked(timX, timY)){
                this.back();
            }else if(Vs_Computer.isClicked(timX, timY)){
                this.openNewGame();
            }else if(Vs_Friend.isClicked(timX, timY)){
                this.openLoadGame();
            }

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
    }
}
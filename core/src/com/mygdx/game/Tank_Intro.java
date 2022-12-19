package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.w3c.dom.Text;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Tank_Intro extends ApplicationAdapter implements Screen{
    private SpriteBatch batch;
    private Clickables start;
    private Clickables end;
    private Texture back;
    private Texture yellow;

    private MyGdxGame parent;
    private OrthographicCamera camera;
    Tank_Intro(MyGdxGame parent){
        this.parent=parent;
        camera=new OrthographicCamera();
        back=new Texture(Gdx.files.internal("Basics/Tank_Stars.png"));
        yellow=new Texture(Gdx.files.internal("Basics/Yellow_Shot (1).png"));
        start=new Buttons("Basics/Start_Game.png", 250, 200, 300,100);
        end=new Buttons("Basics/Exit_Game.png", 250, 80, 300, 100);
        camera=new OrthographicCamera();
        parent.stage.addActor((Actor) start);
        parent.stage.addActor((Actor) end);
        Gdx.input.setInputProcessor(parent.stage);
    }
    public void render(){

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        try{
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            parent.batch.begin();
            Color c=parent.batch.getColor();
            parent.batch.setColor(c.r, c.g, c.b, 1f);
            parent.batch.draw(back, 0,0, 800, 600);
            c=parent.batch.getColor();
            parent.batch.setColor(c.r, c.g, c.b, 0.9f);
            parent.batch.draw(yellow, 150, 50, 500, 300);
            start.draw(parent.batch, 1f);
            end.draw(parent.batch, 1f);
            parent.batch.end();
            if(Gdx.input.isTouched()){
                Vector3 touch=new Vector3();
                camera.unproject(touch);
                touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                if(this.start.isClicked(800-(int) touch.x, 600-(int) touch.y)){
                    back.dispose();
                    yellow.dispose();
                    parent.stage.dispose();
                    parent.stage=new Stage();
                    parent.setScreen(new Tank_Main(parent));
                }else if(this.end.isClicked(800-(int)touch.x, 600-(int)touch.y)){
                    Gdx.app.exit();
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
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
        batch.dispose();
    }
}

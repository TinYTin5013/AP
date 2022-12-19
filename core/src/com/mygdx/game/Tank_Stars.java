package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.w3c.dom.Text;
import com.badlogic.gdx.math.Rectangle;
public class Tank_Stars extends ApplicationAdapter {
    private Texture bucket;
    private Texture drop;
    private SpriteBatch batch;
    private Texture sketch;
    private OrthographicCamera camera;

    private Rectangle bucket_rect;
    @Override
    public void create () {
        bucket=new Texture(Gdx.files.internal("bucket.png"));
        drop=new Texture(Gdx.files.internal("drop.png"));
        batch=new SpriteBatch();
        camera=new OrthographicCamera();
        camera.setToOrtho(false, 800,600);
        bucket_rect=new Rectangle();
        bucket_rect.x=(800/2)-(64/2);
        bucket_rect.y=0;
        bucket_rect.height=64;
        bucket_rect.height=64;
    }
    @Override
    public void render () {
        ScreenUtils.clear(0,0,0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sketch=new Texture(Gdx.files.internal("Scene.png"));

        batch.begin();
        batch.draw(sketch, 0,0, 800, 600);
        batch.draw(bucket, bucket_rect.x, 0, 64, 64);
        batch.draw(drop, (600-(30/2)), 600-64, 64, 64);
        batch.end();

        if(Gdx.input.isTouched()){
            Vector3 touch=new Vector3();
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if(touch.x>=bucket_rect.x && touch.x<=bucket_rect.x+64){
                bucket_rect.x=touch.x-64/2;}
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket_rect.x -= 200 * Gdx.graphics.getDeltaTime();
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket_rect.x += 200 * Gdx.graphics.getDeltaTime();

            if(bucket_rect.x<0){
                bucket_rect.x=0;
            }if(bucket_rect.x>800-64){
                bucket_rect.x=800-64;
            }
        }
    }

    @Override
    public void dispose () {
        bucket.dispose();
        drop.dispose();
        sketch.dispose();
        batch.dispose();
    }
}

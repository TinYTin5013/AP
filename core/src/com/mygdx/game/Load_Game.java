package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import com.badlogic.gdx.math.Rectangle;
public class Load_Game implements Screen{
    private MyGdxGame parent;
    private Texture bg;
    private Clickables Back;
    private Clickables Game1;
    private Clickables Game2;
    private Clickables Game3;
    private OrthographicCamera camera;
    private Texture save;
    public Load_Game(MyGdxGame parent){
        this.parent=parent;
        bg=new Texture(Gdx.files.internal("Basics/Night_Blah.png"));
        Back=new Buttons("Basics/BackButton.png",0, 510, 80, 80);
        Game1=new Buttons("Basics/Game1.png",50, 100, 200, 250);
        Game2=new Buttons("Basics/Game2.png",300, 100, 200, 250);
        Game3=new Buttons("Basics/Game3.png",550, 100, 200, 250);
        save=new Texture(Gdx.files.internal("Basics/Saved_GameBlaj.png"));
        parent.stage.addActor((Actor) Game1);
        parent.stage.addActor((Actor) Game2);
        parent.stage.addActor((Actor) Game3);
        parent.stage.addActor((Actor) Back);
        camera=new OrthographicCamera();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        parent.batch.begin();
        parent.batch.draw(bg, 0,0, 800, 600);
        Game1.draw(parent.batch,1f);
        Game2.draw(parent.batch,1f);
        Game3.draw(parent.batch,1f);
        Back.draw(parent.batch,1f);
        parent.batch.draw(save, 250, 350, 300, 100);
        parent.batch.end();
        if(Gdx.input.isTouched()){
            Vector3 touch=new Vector3();
            camera.unproject(touch);
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            int timX=(int)touch.x;
            int timY=600-(int)touch.y;
            if(Back.isClicked(timX, timY)){
                bg.dispose();
                parent.stage.dispose();
                save.dispose();
                try{
                    Thread.sleep(200);
                }catch(Exception e){
                    e.printStackTrace();
                }
                parent.stage=new Stage();
                parent.setScreen(new Tank_Main(parent));
            }else if(timX>=50 && timX<=250 && timY>=100 && timY<=350){
                String gabe=parent.mapper.get(1);
                ObjectInputStream input = null;
                try{
                    input=new ObjectInputStream(new FileInputStream("PlayerOne_"+gabe+".txt"));
                    Characters timOne=(Characters) input.readObject();
                    input=new ObjectInputStream(new FileInputStream("PlayerTwo_"+gabe+".txt"));
                    Characters timTwo=(Characters) input.readObject();
                    input=new ObjectInputStream(new FileInputStream("WeaponOne_"+gabe+".txt"));
                    Characters timThree=(Characters) input.readObject();
                    input=new ObjectInputStream(new FileInputStream("WeaponTwo_"+gabe+".txt"));
                    Characters timFour=(Characters) input.readObject();
                    parent.player_One=timOne;
                    parent.player_Two=timTwo;
                    parent.weapon_One=timOne;
                    parent.weapon_Two=timTwo;
                }catch (Exception e){
                    System.out.println(e);
                }finally {
                    if(input!=null){
                        try {
                            input.close();
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
                if(parent.player_One==null || parent.player_Two==null || parent.weapon_One==null || parent.weapon_Two==null){
                    System.out.println("Does not exist");
                }else{
                    bg.dispose();
                    parent.stage.dispose();
                    save.dispose();
                    parent.stage=new Stage();
                    parent.setScreen(new Ground(parent));
                }
            }else if(timX>=300 && timX<=500 && timY>=100 && timY<=350){
                String gabe=parent.mapper.get(2);
                ObjectInputStream input = null;
                try{
                    input=new ObjectInputStream(new FileInputStream("PlayerOne_"+gabe+".txt"));
                    Characters timOne=(Characters) input.readObject();
                    input=new ObjectInputStream(new FileInputStream("PlayerTwo_"+gabe+".txt"));
                    Characters timTwo=(Characters) input.readObject();
                    input=new ObjectInputStream(new FileInputStream("WeaponOne_"+gabe+".txt"));
                    Characters timThree=(Characters) input.readObject();
                    input=new ObjectInputStream(new FileInputStream("WeaponTwo_"+gabe+".txt"));
                    Characters timFour=(Characters) input.readObject();
                    parent.player_One=timOne;
                    parent.player_Two=timTwo;
                    parent.weapon_One=timOne;
                    parent.weapon_Two=timTwo;
                }catch (Exception e){
                    System.out.println(e);
                }finally {
                    if(input!=null){
                        try {
                            input.close();
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
                if(parent.player_One==null || parent.player_Two==null || parent.weapon_One==null || parent.weapon_Two==null){
                    System.out.println("Does not exist");
                }else{
                    bg.dispose();
                    parent.stage.dispose();
                    save.dispose();
                    parent.stage=new Stage();
                    parent.setScreen(new Ground(parent));
                }
            }
            else if(timX>=550 && timX<=750 && timY>=100 && timY<=350){
                String gabe=parent.mapper.get(3);
                ObjectInputStream input = null;
                try{
                    input=new ObjectInputStream(new FileInputStream("PlayerOne_"+gabe+".txt"));
                    Characters timOne=(Characters) input.readObject();
                    input=new ObjectInputStream(new FileInputStream("PlayerTwo_"+gabe+".txt"));
                    Characters timTwo=(Characters) input.readObject();
                    input=new ObjectInputStream(new FileInputStream("WeaponOne_"+gabe+".txt"));
                    Characters timThree=(Characters) input.readObject();
                    input=new ObjectInputStream(new FileInputStream("WeaponTwo_"+gabe+".txt"));
                    Characters timFour=(Characters) input.readObject();
                    parent.player_One=timOne;
                    parent.player_Two=timTwo;
                    parent.weapon_One=timOne;
                    parent.weapon_Two=timTwo;
                }catch (Exception e){
                    System.out.println(e);
                }finally {
                    if(input!=null){
                        try {
                            input.close();
                        } catch (IOException e) {
                            System.out.println("Not existent");
                        }
                    }
                }
                try{
                    Thread.sleep(200);
                }catch(Exception e){
                    e.printStackTrace();
                }
                if(parent.player_One==null || parent.player_Two==null || parent.weapon_One==null || parent.weapon_Two==null){
                    System.out.println("Does not exist");
                }else{
                    bg.dispose();
                    parent.stage.dispose();
                    save.dispose();
                    parent.stage=new Stage();
                    parent.setScreen(new Ground(parent));
                }
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

    @Override
    public void dispose() {

    }
}

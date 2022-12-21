package com.mygdx.game;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.w3c.dom.Text;
import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;

import com.badlogic.gdx.math.Rectangle;
public class New_Game_Two extends ApplicationAdapter implements Screen {
    private Texture bg;
    private Texture purple_bg;
    private SpriteBatch batch;
    private Texture logo;
    private Texture tank_choice;
    private OrthographicCamera camera;
    private Texture abrams;
    private Clickables tank_child;
    private Texture current;
    private Texture abrams_current;
    private Texture atom_current;
    private Texture frost_current;
    private Texture mark_current;
    private Texture helios_current;
    private Buttons Back;
    private Clickables Play;
    private Tank tim;
    private Stage stage;
    private Clickables arrow;
    private Clickables reverse_arrow;
    private int i;
    private MyGdxGame parent;
    public New_Game_Two(MyGdxGame parent){
        this.parent=parent;
        batch=new SpriteBatch();
        stage=new Stage();
        int i=0;
        bg=new Texture(Gdx.files.internal("Basics/Blue_Bg.png"));
        arrow=new Buttons("Basics/Arrow.png", 690, 260, 100,100);
        reverse_arrow=new Buttons("Basics/Reverse_Arrow.png", 450, 260, 100,100);
        purple_bg=new Texture(Gdx.files.internal("Basics/Purple.png"));
        logo=new Texture(Gdx.files.internal("Basics/Logo_Bland.png"));
        tank_choice=new Texture(Gdx.files.internal("Basics/Tank_Choice.png"));
        tank_child=new Buttons("Basics/Abrams_Tank.png", 40, 125, 330, 250);
        abrams_current=new Texture(Gdx.files.internal("Basics/Abrams_Current.png"));
        atom_current=new Texture(Gdx.files.internal("Basics/Atom_Current.jpeg"));
        frost_current=new Texture(Gdx.files.internal("Basics/Frost_Current.jpeg"));
        mark_current=new Texture(Gdx.files.internal("Basics/Mark_Current.jpeg"));
        helios_current=new Texture(Gdx.files.internal("Basics/Helios_Current.jpeg"));
        camera=new OrthographicCamera();
        Play=new Buttons("Basics/Play.png",((450+800)/2) -110, 100, 250, 100);
        Back=new Buttons("Basics/BackButton.png",0, 510, 80, 80);
        abrams=new Texture(Gdx.files.internal("Basics/Abrams.png"));
        parent.stage.addActor((Actor) Play);
        parent.stage.addActor((Actor) arrow);
        Gdx.input.setInputProcessor(stage);
    }
    public void previous(){}
    public void next(){}
    public void play(){}
    public void back(){bg.dispose();
        purple_bg.dispose();
        logo.dispose();
        tank_choice.dispose();
        abrams.dispose();
        try{
            Thread.sleep(200);
        }catch(Exception e){
            e.printStackTrace();
        }
        parent.stage.dispose();
        parent.stage=new Stage();
        parent.setScreen(new Tank_Main(parent));}
    public void fire(){}
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        parent.batch.begin();
        parent.batch.draw(bg, 0,0, 450, 600);
        parent.batch.draw(purple_bg, 450,0, 350, 600);
        parent.batch.draw(tank_choice, ((450+800)/2)-150, 350, 300, 100);
        parent.batch.draw(abrams, 225-125, 425, 250, 100);
        if(i==0){
            parent.batch.draw(abrams_current, ((450+800)/2)-75, 250, 125, 100);
        }else if(i==1){
            parent.batch.draw(atom_current, ((450+800)/2)-75, 250, 125, 100);
        }else if(i==2){
            parent.batch.draw(mark_current, ((450+800)/2)-75, 250, 125, 100);
        }else if(i==3){
            parent.batch.draw(frost_current, ((450+800)/2)-75, 250, 125, 100);
        }else if(i==4){
            parent.batch.draw(helios_current, ((450+800)/2)-75, 250, 125, 100);
        }
        Back.draw(parent.batch, 1f);
        Play.draw(parent.batch, 1f);
        parent.tankList.get(i).setAll(40, 125, 330, 250);
        parent.tankList.get(i).draw(parent.batch, 1f);
        if(i<4) {
            arrow.draw(parent.batch, 1f);
        }
        if(i>0){
            reverse_arrow.draw(parent.batch, 1f);
        }
        parent.batch.end();
        parent.stage.act(Gdx.graphics.getDeltaTime());
        if(Gdx.input.isTouched()){
            Vector3 touch=new Vector3();
            camera.unproject(touch);
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            int timX=(int)touch.x;
            int timY=600-(int)touch.y;
            if(arrow.isClicked(timX,timY)){
                i++;
                try{
                    Thread.sleep(350);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(i>=4){
                    i=4;
                }
            }else if(reverse_arrow.isClicked(timX, timY)){
                i--;
                if(i<0){
                    i=0;
                }
                try{
                    Thread.sleep(350);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            else if(Back.isClicked(timX, timY)){
                back();
            }else if(Play.isClicked(timX, timY)){
                bg.dispose();
                purple_bg.dispose();
                logo.dispose();
                tank_choice.dispose();
                abrams.dispose();
                parent.player_Two=new Tank_Creator().getInstance().createCharacters(parent.tankList.get(i).getName(), 2);
                parent.stage.dispose();
                parent.stage=new Stage();
                try{
                    Thread.sleep(200);
                }catch (Exception e){
                    System.out.println(e);
                }
                parent.setScreen(new Weapon_One(parent));
            }
        }
    }

    @Override
    public void hide() {

    }
    public void dispose(){
        bg.dispose();
        purple_bg.dispose();
        logo.dispose();
        tank_choice.dispose();
    }
}

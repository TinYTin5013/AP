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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.w3c.dom.Text;
import java.awt.*;
import com.badlogic.gdx.math.Rectangle;
public class New_Game extends ApplicationAdapter implements Screen {
    private Texture bg;
    private Texture purple_bg;
    private SpriteBatch batch;
    private Texture logo;
    private Texture tank_choice;
    private OrthographicCamera camera;
    private Texture abrams;
    private Texture tank_child;
    private Texture current;
    private Buttons Back;
    private Buttons Play;
    private Stage stage;
    private TextureRegion region;
    private TextureRegionDrawable drawable;
    private Clickables arrow;
    private MyGdxGame parent;
    public New_Game(MyGdxGame parent){
        this.parent=parent;
        batch=new SpriteBatch();
        stage=new Stage();
        bg=new Texture(Gdx.files.internal("Basics/Blue_Bg.png"));
//        arrow=new Texture(Gdx.files.internal("Basics/Arrow.png"));
        purple_bg=new Texture(Gdx.files.internal("Basics/Purple.png"));
        logo=new Texture(Gdx.files.internal("Basics/Logo_Bland.png"));
        tank_choice=new Texture(Gdx.files.internal("Basics/Tank_Choice.png"));
        tank_child=new Texture(Gdx.files.internal("Basics/Tank_Baby.png"));
        current=new Texture(Gdx.files.internal("Basics/Current.png"));
        camera=new OrthographicCamera();
        Play=new Buttons("Basics/Play.png",((450+800)/2) -110, 100, 250, 100);
//        Back=new Buttons(Gdx.files.internal("Basics/BackButton.png"));
        abrams=new Texture(Gdx.files.internal("Basics/Abrams.png"));
        drawable=new TextureRegionDrawable(region);
        parent.stage.addActor(Play);
        Gdx.input.setInputProcessor(stage);
    }
    public void previous(){}
    public void next(){}
    public void play(){}
    public void back(){}
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
        parent.batch.draw(tank_child, (225-150), 50, 300, 400);
        parent.batch.draw(abrams, 225-125, 425, 250, 100);
        parent.batch.draw(current, ((450+800)/2)-75, 250, 125, 100);
        Play.draw(parent.batch, 1f);
        parent.batch.end();
        //parent.stage.act(Gdx.graphics.getDeltaTime());
        parent.stage.draw();
        if(Gdx.input.isTouched()){
            Vector3 touch=new Vector3();
            camera.unproject(touch);
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            int timX=(int)touch.x;
            int timY=600-(int)touch.y;
            if(timX>=0 && timX<=100 && timY>=510 && timY<=640){
//                bg.dispose();
//                purple_bg.dispose();
//                logo.dispose();
//                tank_choice.dispose();
//                abrams.dispose();
//                tank_child.dispose();
//                current.dispose();
//                Back.dispose();
//                Play.dispose();
//                arrow.dispose();
//                try{
//                    Thread.sleep(200);
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//                parent.setScreen(new Tank_Main(parent));
            }else if(timX>=((450+800)/2) -110 && timX<=((450+800)/2) -110+250 && timY>=100 && timY<=200){
//                bg.dispose();
//                purple_bg.dispose();
//                logo.dispose();
//                tank_choice.dispose();
//                abrams.dispose();
//                tank_child.dispose();
//                current.dispose();
//                Back.dispose();
//                Play.dispose();
//                stage.dispose();
//                arrow.dispose();
//                //parent.setScreen(new Ground(parent));
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

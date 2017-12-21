package net.wospy.swirly;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Swirly extends ApplicationAdapter {
    SpriteBatch batch;
    public static final boolean DEBUG=false;
    OrthographicCamera camera;
    Slide slide;
    private Texture img;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(1280,720);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        img= new Texture("badlogic.jpg");

        //using t instead of L because too lazy to make one
        slide = new Slide(Curves.fromFile(100,"curves/W.txt"),Curves.fromFile(100,"curves/O.txt")
                ,Curves.fromFile(100,"curves/S.txt")
                ,Curves.fromFile(100,"curves/P.txt"),Curves.fromFile(100,"curves/fancy_y.txt"));
        slide.setTotalTime(3f);

    }

    @Override
    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
        batch.begin();
        slide.draw(batch);
        batch.end();

       /* if(slide.finished()){
            PixmapIO.writePNG(new FileHandle("Hey"),slide.p );
            Gdx.app.exit();
        }*/
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}

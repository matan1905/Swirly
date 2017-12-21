package net.wospy.swirly;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Slide {
    private  float totalTime;
    Pen pen;
    Texture t;
    Pixmap p;//canvas
    private Curves[] curves;
    private float spacing=30;
    public Slide(Curves... curves){
        this.curves = curves;
        Pixmap br=new Pixmap(Gdx.files.getFileHandle("brush.png", Files.FileType.Internal));
        p= new Pixmap(1280,720, Pixmap.Format.RGBA8888);
        p.setColor(Color.BLACK);
        t=new Texture(p);
        t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        pen= new PixmapPen(br);
        //pen= new ElipsePen(5,Color.BLACK,1f);
        for (int i = 1; i < curves.length; i++) {

            curves[i].position.x+=curves[i-1].getX()+curves[i-1].getWidth()+pen.getWidth()+spacing;
            curves[i].position.y+=curves[0].getHeight()-curves[i].getHeight();
           // curves[i].setX(curves[i-1].getX()+curves[i-1].getWidth()+pen.getWidth()+spacing);

        }

        totalTime=curves.length;
    }
    private Vector2 offset= new Vector2(100,300);
    void setTotalTime(float seconds){this.totalTime=seconds;}
    public void setOffset(float x, float y) {
        this.offset.set(x,y);
    }

    boolean finished(){
        boolean finished =true;
        for (Curves curve : curves) {
            finished&=curve.finished();
        }
        return finished;
    }

    private boolean waitForOthers=true;
    public void draw(SpriteBatch batch){//might be used for saving as well
        for (int i = 0; i < curves.length; i++) {
            if(i==0 || (curves[i-1].finished()&&waitForOthers) || !waitForOthers)
            pen.draw(curves[i],p,totalTime/curves.length,Gdx.graphics.getDeltaTime(),offset);
        }

        t.draw(p,0,0);
        batch.draw(t,0,0,1280,720);
    }

    public void setQueueDraw(boolean waitForOthers) {
        this.waitForOthers = waitForOthers;
    }
}

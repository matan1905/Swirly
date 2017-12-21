package net.wospy.swirly;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class ElipsePen extends Pen {
    private int size;
    private Color c;
    private float scale;

    public ElipsePen(int size,Color c,float scale){
        this.size = size;
        this.c = c;
        this.scale = scale;
    }
    @Override
    public void drawPoint(Pixmap canvas, Point p, Vector2 offset, Vector2 offset2) {
        canvas.setColor(c);
        canvas.fillCircle((int)((p.x+offset.x+offset2.x)*scale),(int)((p.y+offset.y+offset2.y)*scale),size);
    }
    @Override
    float getWidth(){
        return size;
    }
    @Override
    float getHeight(){
        return size;
    }
}
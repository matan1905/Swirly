package net.wospy.swirly;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class PixmapPen extends Pen {
    private Pixmap brush;

    public PixmapPen(Pixmap brush){
        this.brush = brush;
    }
    @Override
    public void drawPoint(Pixmap canvas, Point p, Vector2 offset, Vector2 offset2) {
        canvas.drawPixmap(brush,(int)((p.x+offset.x+offset2.x)),(int)((p.y+offset.y+offset2.y)));
    }
    @Override
    float getWidth(){
        return brush.getWidth();
    }
    @Override
    float getHeight(){
        return brush.getWidth();
    }
}

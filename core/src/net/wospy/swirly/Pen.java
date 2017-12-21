package net.wospy.swirly;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public abstract class Pen {
    public abstract void drawPoint(Pixmap canvas, Point p, Vector2 offset, Vector2 offset2);

    //secs - how many seconds the entire curve draw will take
    //delta - libgdx provided
    public void draw(Curves curve, Pixmap canvas, float secs, float delta, Vector2 offset){
        float xCycle=secs/delta;
        int pointsPerCycle;
        if(secs>0) {
        pointsPerCycle = (int) Math.floor(curve.getMaxPoints()/xCycle + Math.floor(curve.carry));
        curve.carry-=Math.floor(curve.carry);
        curve.carry+=curve.getMaxPoints()/xCycle-Math.floor(curve.getMaxPoints()/xCycle);
        pointsPerCycle = Math.min(pointsPerCycle,curve.getMaxPoints());
        }
        else pointsPerCycle=curve.getMaxPoints();
        for(int i=0;i<pointsPerCycle;i++) {
            Point cp = curve.step();
            if (cp != null)
                drawPoint(canvas,cp,curve.position,offset);
        }

        if(Swirly.DEBUG){
            canvas.setColor(Color.BLACK);
            canvas.drawRectangle((int)(curve.getX()+offset.x),(int)(curve.getY()+offset.y),
                    (int)(curve.getWidth()+getWidth()),
                    (int)(curve.getHeight()+getHeight()));
        }
    }

    abstract float getWidth();
    abstract float getHeight();

}

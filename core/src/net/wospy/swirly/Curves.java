package net.wospy.swirly;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.io.UnsupportedEncodingException;


public class Curves {
    public Vector2 position = new Vector2(0,0);
    private Array<Point> path= new Array<Point>();
    Array<Curves> innerCurves= new Array<Curves>();
    float width,height;
    public Curves(int detail,Array<Point> points){
        float minX=0,minY=0,maxX=0,maxY=0;
        int curves = (points.size-1) /3;
        for (int j = 0; j < curves; j++) {
            float i=0;
            while(i<1){
                Point np = CalculateBezierPoint(i,points.get(j*3),points.get(j*3+1),points.get(j*3+2),points.get(j*3+3));
                i+=(1f/detail);
                path.add(np);
                minX=Math.min(np.x,minX);
                minY=Math.min(np.y,minY);
                maxX=Math.max(np.x,maxX);
                maxY=Math.max(np.y,maxY);
            }
        }

        for (Point point : path) {
            point.x-=minX;
            point.y-=minY;
        }


        width=Math.abs(minX-maxX);
        height=Math.abs(minY-maxY);
    }



    float getWidth(){return width;}
    float getHeight(){return height;}
    float getX(){return position.x;}
    float getY(){return position.y;}

    public Curves setOffset(float x,float y) {
        this.position.add(x,y);
        return this;
    }

    Curves addInnerCurve(Curves value){
        innerCurves.add(value);
        return this;
    }


    public static Curves fromFile(int detail, String fileName){
        try {
            String curve=new String(Gdx.files.getFileHandle(fileName, Files.FileType.Internal).readBytes(),"UTF-8");
            String[] pts = curve.split(",");
           if(pts.length%2==1 || (pts.length/2 -1) %3 != 0) throw new IllegalArgumentException();

            Array<Point> _points= new Array<Point>();
            for (int i = 0; i < pts.length; i+=2) {
                _points.add(new Point(Float.valueOf(pts[i]),Float.valueOf(pts[i+1])));
            }

            return new Curves(detail,_points);

        } catch(IllegalArgumentException e){
            System.out.println("not a curve file!");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;

    }
    public  float carry=0;
    private float index=-1;
    public Point step() {
        index++;
        if(path.size-1 > index) return path.get((int) index);
        for (Curves innerCurve : innerCurves) {
            if(!innerCurve.finished()) return innerCurve.step()==null?null:innerCurve.step().offset(innerCurve.position.x,innerCurve.position.y);
        }
        return path.size-1 > index? path.get((int) index):null;
    }

    Point CalculateBezierPoint(float t, Point s, Point c1, Point c2, Point e)
    {
        float u = 1 - t;
        float tt = t*t;
        float uu = u*u;
        float uuu = uu * u;
        float ttt = tt * t;

        Point p = new Point(s.x * uuu, s.y * uuu);
        p.x += 3 * uu * t * c1.x;
        p.y += 3 * uu * t * c1.y;
        p.x += 3 * u * tt * c2.x;
        p.y += 3 * u * tt * c2.y;
        p.x += ttt * e.x;
        p.y += ttt * e.y;

        return p;
    }

    public int getMaxPoints() {

        int sum= path.size;
        for (Curves innerCurve : innerCurves) {
            sum+=innerCurve.getMaxPoints();
        }
        return sum;
    }


    public boolean finished() {
        boolean finished=true;
        finished =finished && path.size-1 <= index;
        for (Curves innerCurve : innerCurves) {
            finished&=innerCurve.finished();
        }
        return finished;
    }
}


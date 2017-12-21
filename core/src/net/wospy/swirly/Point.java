package net.wospy.swirly;

class Point{
    float x,y;

    public Point(float x, float y) {
        this.x=x;
        this.y=y;
    }

    public Point offset(float x, float y) {
        this.x+=x;
        this.y+=y;
        return this;
    }
}
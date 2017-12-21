
ArrayList<Point> points = new ArrayList<Point>();


Point cPoint=null;
void setup(){
  size(800,600);
points.add(new Point(50,50));

}


void draw(){
  background(0);
  noFill();
  stroke(255);
  rect(width/4,height/4,width/3,width/3);
 if(points.size()>1 && (points.size()-1) %3==0){
   int curves = (points.size()-1) /3;
   for(int i=0;i<curves;i++){
        stroke(255, 102, 0);

 bezier(points.get(i*3).x,points.get(i*3).y,points.get(i*3+1).x,points.get(i*3+1).y
 ,points.get(i*3+2).x,points.get(i*3+2).y,points.get(i*3+3).x,points.get(i*3+3).y);
 }
 
 }

for(int i=0;i<points.size();i++){
  Point p = points.get(i);
  fill(255);
  if(i==0)fill(255,0,0);
else if(i%3!=0) {
  stroke(127,127,127,50);
if(i%3 ==1) line(p.x,p.y,points.get(i-1).x,points.get(i-1).y);
else if(i%3 ==2)line(p.x,p.y,points.get(i+1).x,points.get(i+1).y);

  fill(0,0,255);
}
  if(drawElipse)
 ellipse(p.x,p.y,10,10);
 }
}
boolean drawElipse=true;
void keyReleased(){
switch(key){
  case 'R':points.clear();
  points.add(new Point(50,50));
break;
case 'Z':
  points.remove(points.size()-1);
  points.remove(points.size()-1);
  points.remove(points.size()-1);
break;
  case 'H':drawElipse=!drawElipse; break;
case 78://N
points.add(new Point(100,100));
points.add(new Point(200,200));
points.add(new Point(300,300));
break;
case 83://S
String s="";
for(int i=0;i<points.size();i++){
  Point p = points.get(i);
  Point p0 = points.get(0);
s+=(p.x-p0.x) + "," + (p.y-p0.y);
if(i!=points.size()-1) s+=",";
 }
writeToFile(s);
break;
}

}

void writeToFile(String content){
PrintWriter file = createWriter((int)random(10000)+".txt");
file.print(content);
file.flush();
file.close();
println("saved");
}
void mouseDragged(){
if(cPoint==null) return;
cPoint.x=mouseX;
cPoint.y=mouseY;
}

void mousePressed(){
for(Point p:points){
 if(dist(p.x,p.y,mouseX,mouseY)<10) {
  cPoint=p;
  return;
 }
}
cPoint=null;
}

class Point{
  float x,y;
  Point(float x,float y){
  this.x=x;
  this.y=y;
  }
  
}
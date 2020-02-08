

ArrayList<Integer> x = new ArrayList<Integer>(), y = new ArrayList<Integer>(), z = new ArrayList<Integer>();
int[]dx={0,0,1,-1,0,0}, dy={1,-1,0,0,0,-0}, dz={0,0,0,0,1,-1};
boolean gameover=false;

int OFF_MAX=300;
int xloc = 0, dir = 4;
int yloc = 0;
int zloc = 0;
int prevxloc = 0;
int prevyloc = 0;
int prevzloc = 0;

int xtgt = (int)random(-OFF_MAX/50,OFF_MAX/50)*50;
int ytgt = (int)random(-OFF_MAX/50,OFF_MAX/50)*50;
int ztgt = (int)random(-OFF_MAX/50,OFF_MAX/50)*50;

void setup() {
  size(500, 500,P3D);
  x.add(50);
  y.add(50);
  z.add(50);
}

void keyPressed() {
  int newdir = key=='s' ? 0 : (key=='w' ? 1 : (key=='d' ? 2 : (key == 'a' ? 3 : ((key==CODED && keyCode==DOWN) ? 4 : (key == CODED && keyCode==UP) ? 5 : -1))));
  if(newdir != -1) dir = newdir;
  if (key == CODED) {
    if (keyCode == DOWN && yloc < OFF_MAX) {
      //prevyloc = yloc;
      //yloc+= 50;
      
    } else if (keyCode == UP && yloc > -OFF_MAX) {
      //prevyloc = yloc;
      //yloc-= 50;
    }
    if (keyCode == RIGHT && xloc < OFF_MAX) {
      //prevxloc = xloc;
      //xloc+= 50;
    } else if (keyCode == LEFT && xloc > -OFF_MAX) {
      //prevxloc = xloc;
      //xloc-= 50;
    }
  }
  if (key == 's' && zloc < OFF_MAX) {
      //prevzloc = zloc;
      //zloc+= 50;
   } else if (key == 'w' && zloc > -OFF_MAX) {
      //prevzloc = zloc;
      //zloc-= 50;
   }
}

void resetTarget()
{
  //x.add(5);
  //y.add(5);
  //z.add(5);
  xtgt = (int)random(-OFF_MAX/50,OFF_MAX/50)*50;
  ytgt = (int)random(-OFF_MAX/50,OFF_MAX/50)*50;
  ztgt = (int)random(-OFF_MAX/50,OFF_MAX/50)*50;
}

void draw() {
  if(frameCount%10 == 0)
  {
    if(x.get(0) + dx[dir]*50 <= OFF_MAX && x.get(0) + dx[dir]*50 >= -OFF_MAX)
    {
      x.add(0, x.get(0) + dx[dir]*50);
    }
    else if(x.get(0) + dx[dir]*50 >= OFF_MAX)
    {
      x.add(0, -OFF_MAX);
    }
    else
    {
      x.add(0, OFF_MAX);
    }
    if(y.get(0) + dy[dir]*50 <= OFF_MAX && y.get(0) + dy[dir]*50 >= -OFF_MAX)
    {
      y.add(0, y.get(0) + dy[dir]*50);
    }
    else if(y.get(0) + dy[dir]*50 >= OFF_MAX)
    {
      y.add(0, -OFF_MAX);
    }
    else
    {
      y.add(0, OFF_MAX);
    }
    if(z.get(0) + dz[dir]*50 <= OFF_MAX && z.get(0) + dz[dir]*50 >= -OFF_MAX)
    {
      z.add(0, z.get(0) + dz[dir]*50);
    }
    else if(z.get(0) + dz[dir]*50 >= OFF_MAX)
    {
      z.add(0, -OFF_MAX);
    }
    else
    {
      z.add(0, OFF_MAX);
    }
    if(x.get(0) == xtgt && y.get(0) == ytgt && z.get(0) == ztgt)
    {
      resetTarget();
    }
    else
    {
      x.remove(x.size()-1);
      y.remove(y.size()-1);
      z.remove(z.size()-1);
    }
  }
  background(0);
  translate(width/2, height/2, -2*OFF_MAX);
  //rotateX(frameCount * .01);
  //rotateY(frameCount * .01);
  //rotateZ(frameCount * .01);
  
  for(int xo = -OFF_MAX; xo <= OFF_MAX; xo += 50) {
    for(int yo = -OFF_MAX; yo <= OFF_MAX; yo += 50) {
      for(int zo = -OFF_MAX; zo <= OFF_MAX && zo <= z.get(0); zo += 50) {
        pushMatrix();
        translate(xo, yo, zo);
        //rotateX(frameCount * .02);
        //rotateY(frameCount * .02);
        //rotateZ(frameCount * .02);
        //if(xo == xloc && yo == yloc && zo == zloc) {
         /* if(xo == xtgt && yo == ytgt && zo == ztgt) {
            resetTarget();
          }
          fill(255);*/
        //}
        if(xo == x.get(0) && yo == y.get(0) && zo == z.get(0)) {
          fill(255, 0, 0);
        }
        else if (xo == xtgt && yo == ytgt && zo == ztgt)
        {
          fill(0,255,255);
        }
        else
        {
          fill(colorFromOffset(xo), colorFromOffset(yo), colorFromOffset(zo));
        }
        box(30);
        popMatrix();
      }
    }
  }
  
  // Draw Snake
  
  for(int i = 0; i < x.size(); i++) {
    pushMatrix();
    translate(x.get(i), y.get(i), z.get(i));
    //fill(255,0,0);
    box(30);
    popMatrix();
  }
}

int colorFromOffset (int offset) {
  return(int) ((offset + OFF_MAX) / (2.8 * OFF_MAX) * 255);
}

import tester.Tester;
import javalib.colors.*;
import javalib.worldimages.*;
 
class BouncingBall {
    Posn pos;
    IColor color;
    int size;
    int dx; // how fast is the ball moving to the right?
    int dy; // how fast is the ball moving downward?
 
    BouncingBall(Posn pos, IColor color, int size, int dx, int dy) {
         this.pos = pos;
         this.color = color;
         this.size = size;
         this.dx = dx;
         this.dy = dy;
    }
 
    // Returns a new BouncingBall that's just like this BouncingBall, but moved
    // by this BouncingBall's dx and dy
    BouncingBall move() {
    	return new BouncingBall(
    			new Posn(this.pos.x + this.dx, this.pos.y + dy),
    			this.color,
    			this.size,
    			this.dx,
    			this.dy);
    }	
 
    // Returns a new BouncingBall that represents this BouncingBall just after
    // it has bounced off a side wall. Does not actually move the ball
    BouncingBall bounceX() {
    	return new BouncingBall(this.pos, this.color, this.size,
    							-this.dx, this.dy);
    }
 
    // Like bounceX, except for using the top or bottom walls
    BouncingBall bounceY() {
         return new BouncingBall(this.pos, this.color, this.size,
        		 		 		 this.dx, -this.dy);
    }
 
    // Does the ball collide with a side wall?
    boolean collidesX(Posn topLeft, Posn botRight) {
        return (this.pos.x - this.size >= topLeft.x)
        	&& (this.pos.x + this.size <= botRight.x);
    }
 
    // Does the ball collide with a top or bottom wall?
    boolean collidesY(Posn topLeft, Posn botRight) {
         return (this.pos.y - this.size >= topLeft.y)
        	&&	(this.pos.y + this.size <= botRight.y);
    }
}
 
class ExamplesBouncingBalls {
    int WIDTH = 300;
    int HEIGHT = 300;
 
    // NOTE: We have provided BouncingWorld for you, in the starter code.
    // We'll see how it works in a few lectures
    boolean testBigBang(Tester t) {
        BouncingWorld w = new BouncingWorld(WIDTH, HEIGHT);
        return w.bigBang(WIDTH, HEIGHT, 0.1);
    }
}
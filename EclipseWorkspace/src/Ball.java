import tester.Tester;
import javalib.colors.*;

// A Ball is a new Ball(int, int, int, IColor)
// REPRESENTS: a ball
class Ball {
	int x;
	int y;
	int radius;
	IColor color;
	
	Ball(int x, int y, int radius, IColor color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}
	
	// RETURNS: the area of this Ball
	double area() {
		return Math.PI * Math.pow(this.radius, 2);
	}
	
	// RETURNS: the circumference of this Ball
	double circumference() {
		return 2 * Math.PI * this.radius;
	}
	
	// RETURNS: the distance between this ball and that ball
	double distance(Ball that){
		return Math.sqrt(Math.pow(this.x - that.x, 2) + 
				Math.pow(this.y - that.y, 2));
	}
	
	// RETURNS: true iff this ball overlaps with that ball
	boolean overlaps(Ball that) {
		return this.distance(that) <= (this.radius + that.radius);
	}
}

// REPRESENTS: examples and tests for balls
class ExamplesBalls{
	Ball b = new Ball(0, 0, 5, new Blue());
	Ball b2 = new Ball(6, 8, 5, new Red());
	Ball b3 = new Ball(3,  4, 5, new Yellow());
	
	boolean testBalls(Tester t) {
		return t.checkInexact(b.area(), 78.5, 0.001)
			&& t.checkInexact(b.circumference(), 31.4, 0.001)
			&& t.checkInexact(b.distance(b2), 10.0, 0.001)
			&& t.checkExpect(b.overlaps(b3), true);
	}
	
	
}

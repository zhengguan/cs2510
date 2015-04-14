import com.sun.javafx.geom.Area;

import tester.Tester;
import javalib.colors.*;

// A Rectangle is a new Rectangle(int, int, int, int, IColor)
// REPRESENTS: a Rectangle
class Rectangle {
	int x;
	int y;	// x, y coordinates of center
	int width;
	int height;
	IColor color;
	
	Rectangle(int x, int y, int width, int height, IColor color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	// RETURNS: the area of this Rectangle
	int area() {
		return width * height;
	}
	
	// RETURNS: the circumference of this Rectangle
	int circumference() {
		return 2 * (width + height);
	}
	
	// RETURNS: the distance between this and that Rectangle
	double distance(Rectangle that) {
		return Math.sqrt(Math.pow(that.x - this.x, 2) + 
						 Math.pow(that.y - this.y, 2));
	}
	
	// RETURNS: true iff this Rectangle overlaps with that Rectangle
	boolean overlaps(Rectangle that) {
		return (Math.abs(this.x - that.x) <=
				(this.width + that.width) / 2)
			&& (Math.abs(this.y - that.y) <=
				(this.height + that.height) / 2);
	}
}

// REPRESENTS: examples and tests for Rectangles
class ExamplesRectangles {
	Rectangle rectangle1 = new Rectangle(0, 0, 6, 8, new Blue());
	Rectangle rectangle2 = new Rectangle(10, 10, 6, 8, new Red());
	Rectangle rectangle3 = new Rectangle(6, 8, 6, 8, new Yellow());
	
	boolean testRectangles(Tester t) {
		return t.checkExpect(rectangle1.area(), 48)
			&& t.checkExpect(rectangle1.circumference(), 28)
			&& t.checkInexact(rectangle1.distance(rectangle2), 
					14.14, 0.001)
			&& t.checkExpect(rectangle1.overlaps(rectangle2), false)
			&& t.checkExpect(rectangle1.overlaps(rectangle3), true);
	}
}


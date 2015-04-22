import tester.Tester;

// to represent a geometric shape
interface IShape {
  // to compute the area of this shape
  double area();
  // We'll add more methods later
  
	//In IShape
	//to compute the distance from this shape to the origin
	double distanceToOrigin();
	
	//to increase the size of this shape by the given increment
	IShape grow(int inc);
	
	//is the area of this shape bigger than
	//the area of the given shape?
	boolean isBiggerThan(IShape that);
	
	//RETURNS: true iff the given point is within this shape
	boolean contains(CartPt point);
}
 
// to represent a circle
class Circle implements IShape {
//  int x; // represents the center of the circle
//  int y;
  CartPt center;
  int radius;
  String color;
 
  Circle(CartPt center, int radius, String color) {
    this.center = center;
    this.radius = radius;
    this.color = color;
  }
 
  /* TEMPLATE
     FIELDS:
     ... this.center ...              -- CartPt
     ... this.radius ...              -- int
     ... this.color ...               -- String
     METHODS
     ... this.area() ...              -- double
     ... this.distanceToOrigin() ...  -- double
     ... this.grow(int) ...			  -- IShape
	 ... this.contains(CartPt) ...	-- boolean     
     ... this.isBiggerThan(IShape) ... -- boolean
  */
 
  // to compute the area of this shape
  public double area() {
    return Math.PI * this.radius * this.radius;
  }
  
  // RETURNS: the distance from this shape to the origin
  public double distanceToOrigin() {
	  return this.center.distanceToOrigin() - this.radius;
  }
  
  // RETURNS: a IShape like the this, but with its size increased by
  // the given increment
  public IShape grow(int inc) {
	  return new Circle(this.center, 
			  			this.radius + inc, this.color);
  }
  
  // RETURNS: true iff this Shape's area is greater than that one
  public boolean isBiggerThan(IShape that) {
	  return this.area() > that.area();
  }
  
  public boolean contains(CartPt point) {
	  return this.center.distanceTo(point) <= this.radius;
  }
}
 
// to represent a square
class Square implements IShape {
//  int x; // represents the top-left corner of the square
//  int y;
  CartPt topLeft;
  int size;
  String color;
 
  Square(CartPt topLeft, int size, String color) {
    this.topLeft = topLeft;
    this.size = size;
    this.color = color;
  }
 
  /* TEMPLATE
     FIELDS:
     ... this.topLeft ...         -- CartPt
     ... this.size ...            -- int
     ... this.color ...           -- String
     METHODS:
     ... this.area() ...                  -- double
	 ... this.area() ...				  -- double
	 ... this.grow(int) ...				  -- IShape
	 ... this.contains(CartPt) ...	      -- boolean
	 ... this.isBiggerThan(IShape) ... -- boolean	      
     METHOSDS ON FIELDS:
     ... this.topLeft.distanceToOrigin() ... -- double
  */
 
  // to compute the area of this shape
  public double area() {
    return this.size * this.size;
  }
  
  // RETURNS: the distance from this shape to the origin
  // DETAILS: the distance starts from the top left  of this shape
  public double distanceToOrigin() {
	  return this.topLeft.distanceToOrigin();
  }
  
  // RETURNS: a shape like this one, but increase its size of by the
  // given increment
  public IShape grow(int inc) {
	  return 
	  new Square(new CartPt(topLeft.x, topLeft.y),
			  	 size + inc, this.color);
  }
  
  
  // RETURNS: true iff this is bigger than the given IShape
  public boolean isBiggerThan(IShape that) {
	  return this.area() > that.area();
  }
  
  // RETURNS: true iff the given CartPt is located in this IShape
  public boolean contains(CartPt point) {
	  return (this.topLeft.x <= point.x) && 
			 (point.x <= this.topLeft.x + this.size) &&
			 (this.topLeft.y <= point.y) &&
			 (point.y <= this.topLeft.y + this.size);
  }
}
 

class CartPt {
	int x;
	int y;
	
	CartPt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// RETURNS: the distance from this to origin point
	double distanceToOrigin() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	// RETURNS: the distance from this to that
	double distanceTo(CartPt that) {
		return Math.sqrt(
			(this.x - that.x) * (this.x - that.x)
			+ (this.y - that.y) * (this.y - that.y));
	}
}


class PolarPt{
	int r;
	int theta;
	
	PolarPt(int r, int theta) {
		this.r = r;
		this.theta = theta;
	}
}

class Combo implements IShape {
	IShape shape1;
	IShape shape2;
	
	Combo(IShape shape1, IShape shape2) {
		this.shape1 = shape1;
		this.shape2 = shape2;
	}
	
	/* TEMPLATE:
	   FIELDS:
	   ... this.shape1 ... 	-- IShape
	   ... this.shape2 ... 	-- IShape
	   METHODS:
	   ... this.distanceToOrigin() ... 	-- double
	   ... this.area() ...				-- double
	   ... this.grow(int) ...			-- IShape
	   ... this.contains(CartPt) ...	-- boolean
	   ... this.isBiggerThan(IShape) ... -- boolean
	 */
	
	
	// RETURNS: the distance from this shape to the origin
	public double distanceToOrigin() {
		return Math.min(this.shape1.distanceToOrigin(), 
				this.shape2.distanceToOrigin());
	}
	
	// RETURNS: the area of this shape
	public double area() {
		return this.shape1.area() + this.shape2.area();
	}
	
	// RETURNS: a shape like this but with its size increase the given increment
	public IShape grow(int inc) {
		return new Combo(this.shape1.grow(inc), this.shape2.grow(inc));
	}
	
	// RETURNS: true iff this shape contains the given point
	public boolean contains(CartPt point) {
		return this.shape1.contains(point)
			&& this.shape2.contains(point);
	}
	
	// RETURNS: true iff this shape is bigger the given shape
	public boolean isBiggerThan(IShape that) {
		return this.area() > that.area();
	}
}

class ExamplesShapes {
  ExamplesShapes() {}
  
  CartPt pt1 = new CartPt(0,  0);
  CartPt pt2 = new CartPt(3,  4);
  CartPt pt3 = new CartPt(7,  1);
 
  IShape c1 = new Circle(new CartPt(50, 50), 10, "red");
  IShape c2 = new Circle(new CartPt(50, 50), 30, "red");
  IShape c3 = new Circle(new CartPt(30,  100), 30, "blue");
  
  IShape s1 = new Square(new CartPt(50, 50), 30, "red");
  IShape s2 = new Square(new CartPt(50, 50), 50, "red");
  IShape s3 = new Square(new CartPt(20, 40), 10, "green");
  
  IShape co1 = new Combo(c1, s1);
  IShape co2 = new Combo(c2, s2);
  IShape co3 = new Combo(c3, s3);
 
  // test method distanceToOrigin in the class CartPt
  boolean testDistanceToOrigin(Tester t) {
	  return 
	  t.checkInexact(this.pt1.distanceToOrigin(), 0.0, 0.0001) &&
	  t.checkInexact(this.pt2.distanceToOrigin(), 5.0, 0.0001);
  }
  
  // test the method area in the class Circle
  boolean testCircleArea(Tester t) {
    return
    t.checkInexact(this.c1.area(), 314.15, 0.01);
  }
 
  // test the method area in the class Square
  boolean testSquareArea(Tester t) {
    return
    t.checkInexact(this.s1.area(), 900.0, 0.01);
  }
  
  // test the method area in the class Combo
  boolean testCoboArea(Tester t) {
	  return
	  t.checkInexact(this.co1.area(), 1214.15, 0.01);
  }
 
  // test the method distanceToOrigin in the class Circle
  boolean testCircleDistanceToOrigin(Tester t) {
    return
    t.checkInexact(this.c1.distanceToOrigin(), 60.71, 0.01) &&
    t.checkInexact(this.c3.distanceToOrigin(), 74.40, 0.01);
  }
 
  // test the method distanceToOrigin in the class Square
  boolean testSquareDistanceToOrigin(Tester t) {
    return
    t.checkInexact(this.s1.distanceToOrigin(), 70.71, 0.01) &&
    t.checkInexact(this.s3.distanceToOrigin(), 44.72, 0.01);
  }
 
  // test the method distanceToOrigin in the class Combo
  boolean testComboDistanceToOrigin(Tester t) {
	  return
	  t.checkInexact(this.co1.distanceToOrigin(), 60.71, 0.01) &&
	  t.checkInexact(this.co3.distanceToOrigin(), 44.72, 0.01);
  }
  
  // test the method grow in the class Circle
  boolean testCircleGrow(Tester t) {
    return
    t.checkExpect(this.c1.grow(20), this.c2);
  }
 
  // test the method grow in the class Square
  boolean testSquareGrow(Tester t) {
    return
    t.checkExpect(this.s1.grow(20), this.s2);
  }
  
  // test the method grow in the class Combo
  boolean testComboGrow(Tester t) {
	  return
	  t.checkExpect(this.co1.grow(20), this.co2);
  }
 
  // test the method isBiggerThan in the class Circle
  boolean testCircleIsBiggerThan(Tester t) {
    return
    t.checkExpect(this.c1.isBiggerThan(this.c2), false) &&
    t.checkExpect(this.c2.isBiggerThan(this.c1), true) &&
    t.checkExpect(this.c1.isBiggerThan(this.s1), false) &&
    t.checkExpect(this.c1.isBiggerThan(this.s3), true);
  }
 
  // test the method isBiggerThan in the class Square
  boolean testSquareIsBiggerThan(Tester t) {
    return
    t.checkExpect(this.s1.isBiggerThan(this.s2), false) &&
    t.checkExpect(this.s2.isBiggerThan(this.s1), true) &&
    t.checkExpect(this.s1.isBiggerThan(this.c1), true) &&
    t.checkExpect(this.s3.isBiggerThan(this.c1), false);
  }

  // test the method isBiggerThan in the class Combo
  boolean testComboIsBiggerThan(Tester t) {
	  return
	  t.checkExpect(this.co1.isBiggerThan(this.co2), false) &&
	  t.checkExpect(this.co2.isBiggerThan(this.co1), true) &&
	  t.checkExpect(this.co3.isBiggerThan(this.co1), true) &&
	  t.checkExpect(this.s2.isBiggerThan(this.co1), true);
  }
  
  // test the method contains in the class Circle
  boolean testCircleContains(Tester t) {
    return
    t.checkExpect(this.c1.contains(new CartPt(100, 100)), false) &&
    t.checkExpect(this.c2.contains(new CartPt(40, 60)), true);
  }
 
 
  // test the method contains in the class Square
  boolean testSquareContains(Tester t) {
    return
    t.checkExpect(this.s1.contains(new CartPt(100, 100)), false) &&
    t.checkExpect(this.s2.contains(new CartPt(55, 60)), true);
  }
  
  // test the method contains in the class Combo
  boolean testComboContains(Tester t) {
	  return
	  t.checkExpect(co1.contains(new CartPt(50, 50)), true) &&
	  t.checkExpect(co2.contains(new CartPt(90, 90)), false) &&
	  t.checkExpect(co3.contains(new CartPt(35, 60)), false);
  }
}

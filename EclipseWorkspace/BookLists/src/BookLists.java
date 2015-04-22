import tester.Tester;

// REPRESENTS: a list of book
interface ILoBook {
	// RETURNS: count the book in this list
	int count();
	
	// RETURNS: a list of books published before the given date
	// from this list of books
	ILoBook allBefore(int year);
	
	// RETURNS: the total price of all books in this list
	double totalPrice();
	
	// RETURNS: a list of all books in this list, sorted by their
	// title
	ILoBook sortByTitle();
	
	// RETURNS: a list of books like this one, but with the given book 
	// inserted to its proper place
	ILoBook insert(Book b);
	
}

// REPRESENTS: an empty list of books
class MtLoBook implements ILoBook {
	MtLoBook(){}
	
	/* TEMPLATE:
	   METHODS:
	   ... this.count() ...					-- int
	   ... this.allBefore(int year) ...		-- ILoBook
	   ... this.totalPrice() ... 			-- double
	   ... this.sortByPrice() ... 			-- ILoBook
	 */
	
	// RETURNS: the number of books in this list
	public int count() {
		return 0;
	}
	
	// RETURNS: a list of books published before the given date
	// from this list of books
	public ILoBook allBefore(int year) {
		return this;
	}
	
	// RETURNS: the total price of all books in this list
	public double totalPrice() {
		return 0;
	}
	
	// RETURNS: a list of all books in this list, sorted by their
	// price
	public ILoBook sortByTitle() {
		return this;
	}
	
    // RETURNS: a list of books like this one, but with the given book 
    // inserted to its proper place
    public ILoBook insert(Book b) {
        return new ConsLoBook(b, this);
    }
}

// REPRESENTS: an non-empty list of books
class ConsLoBook implements ILoBook {
	Book first;
	ILoBook rest;
	
	ConsLoBook(Book first, ILoBook rest) {
		this.first = first;
		this.rest = rest;
	}

	/* TEMPLATE:
	   FIELDS:
	   ... this.first ... 						-- Book
	   ... this.rest ...						-- ILoBook
	   METHODS:
	   ... this.count() ...						-- int
	   ... this.allBefore(int year) ...			-- ILoBook
	   ... this.totalPrice() ... 				-- double
	   ... this.sortByPrice() ... 				-- ILoBook
	   METHODS FOR FIELDS:
	   ... this.rest.count() ...				-- int
	   ... this.rest.totalPrice() ... 			--double
	   ... this.rest.allBefore(int year) ...  	-- ILoBook
	 */	
	
	// RETURNS: count the book in this list
	public int count() {
		return 1 + this.rest.count();
	}
	
	// RETURNS: a list of books published before the given date
	// from this list of books
	public ILoBook allBefore(int year) {
		if (this.first.publishedBefore(year)) {
			return new ConsLoBook(this.first, this.rest.allBefore(year));
		}
		else {
			return this.rest.allBefore(year);
		}
	}
	
	// RETURNS: the total price of all books in this list
	public double totalPrice() {
		return this.first.price + this.rest.totalPrice();
	}
	
	// RETURNS: a list of all books in this list, sorted by their
	// price
	public ILoBook sortByTitle() {
	    return this.rest.sortByTitle()
	            .insert(this.first);
	}
	
	// RETURNS: a list of books like the given one, but with the 
	// given book inserted to its proper place
	// WHERE: the list of books is already sorted by price
	public ILoBook insert(Book b) {
	    if (this.first.titleBefore(b)) {
	        return new ConsLoBook(this.first, this.rest.insert(b));
	    }
	    else {
	        return new ConsLoBook(b, this);
	    }
	}
	
	
}

// REPRESENTS: a book
class Book {
	String title;
	String author;
	int year;
	double price;
	
	Book(String title, String author, int year, double price) {
		this.title = title;
		this.author = author;
		this.year = year;
		this.price = price;
	}
	
	// RETURNS: true iff this book is published before the given year
	public boolean publishedBefore(int year) {
		return this.year < year;
	}
	
	// RETURNS: true iff this book is cheaper than that book
	public boolean cheaperThan(Book that) {
	    return this.price < that.price;
	}
	
	// RETURNS: true iff the title of this book is lexicographically 
	// before that book
	public boolean titleBefore(Book that) {
	    return this.title.compareTo(that.title) < 0;
	}
}


class ExamplesBookLists {
	ExamplesBookLists() {}
	
	// Books
	Book htdp = new Book("HtDP", "MF", 2001, 60);
	Book lpp = new Book("LPP", "STX", 1942, 25);
	Book ll = new Book("LL", "FF", 1986, 10);
	
	// lists of Books
	ILoBook mtlist = new MtLoBook();
	ILoBook lista = new ConsLoBook(this.lpp, this.mtlist);
	ILoBook listb = new ConsLoBook(this.htdp, this.mtlist);
	ILoBook listc = new ConsLoBook(this.lpp,
						new ConsLoBook(this.ll,  this.listb));
	ILoBook listd = new ConsLoBook(this.ll, 
					  new ConsLoBook(this.lpp, 
					    new ConsLoBook(this.htdp, this.mtlist)));
	ILoBook listdUnsorted = 
	  new ConsLoBook(this.lpp,
	    new ConsLoBook(this.htdp,
	      new ConsLoBook(this.ll, this.mtlist)));
	
	ILoBook listdSortedByTitle = 
	  new ConsLoBook(this.htdp,
	    new ConsLoBook(this.ll, 
	      new ConsLoBook(this.lpp, this.mtlist)));
	
	
	// test for method titleBefore in Book
	boolean testTitleBefore(Tester t) {
	    return t.checkExpect(this.htdp.titleBefore(this.ll), true);
	}
	
	// test for method publishedBefore in Book
	boolean testBookPublishedBefore(Tester t) {
		return
		t.checkExpect(this.htdp.publishedBefore(2001), false) &&
		t.checkExpect(this.htdp.publishedBefore(2002), true);
	}
	
	// test for method count
	boolean testCount(Tester t) {
	    return
	    t.checkExpect(this.mtlist.count(), 0) &&
	    t.checkExpect(this.listd.count(), 3);
	}
	
	// test for method totalPrice
	boolean testTotalPrice(Tester t) {
	    return
	    t.checkInexact(this.mtlist.totalPrice(), 0.0, 0.01) &&
	    t.checkInexact(this.listd.totalPrice(), 95.0, 0.01);
	}
	
	// test for method allBefore
	boolean testAllBefore(Tester t) {
	    return
	    t.checkExpect(this.mtlist.allBefore(2000), this.mtlist) &&
	    t.checkExpect(this.listd.allBefore(1960), 
	            new ConsLoBook(this.lpp, this.mtlist));
	}
	
	// test for method sortByPrice
//	boolean testSort(Tester t) {
//	    return
//	    t.checkExpect(this.listdUnsorted.sortByPrice(), this.listd);
//	}
	
	// test for method sortByTitle
	boolean testSortByTitle(Tester t) {
	    return
	    t.checkExpect(this.listd.sortByTitle(), this.listdSortedByTitle);
	}
	
}

public class l3 {

}

// Represents a book in a bookstore
class Book{
	String title;
	Author author;
	int price;	// in dollars
	
	// the constructor
	Book(String title, Author author, int price){
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	/* TEMPLATE:
	 * Fields:
	   ... this.title ... -- String
	   ... this.author ... -- Author
	   ... this.price ... -- int
	   
	   Methods:
	   ... this.salePrince(int) ... -- int
	   ... this.sameAuthor(Book) ... -- boolean
	   
	   Methods for fields:
	   ... this.author.mmm(??) .. -- ??
	 */
	
	/* METHOD DEFINITIONS */
	// GIVEN: discount rate (as a percentage)
	// RETURNS: the sale price of this Book under given discount
	int salePrice(int discount) {
		return this.price - (this.price * discount) / 100;
	}
	
	// GIVEN: a Book
	// RETURNS: true iff this Book is written by the same Author as the
	// given book
	boolean sameAuthor(Book that) {
		return this.author.name == that.author.name
			&&	this.author.yob == that.author.yob;
	}
}

class Author {
	String name;
	int yob;
	
	Author(String name, int yob) {
		this.name = name;
		this.yob = yob;
	}
	
	// GIVEN: a Author that
	// RETURNS: true iff this Author is the same as that Author
}

// examples and tests for the classes that represent books and authors
class ExampleBooksAuthors{
	public ExampleBooksAuthors() {

	}
	
	// examples of Authors
	Author pat = new Author("Pat Conroy", 1948);
	Author dan = new Author("Dan Brown", 1962);
	
	// examples of books
	Book beaches = new Book("Beaches", this.pat, 20);
	Book prince = new Book("Prince of Tides", this.pat, 15);
	Book code = new Book("Da Vinci Code", this.dan, 20);
	
	boolean testSalePrice(Tester t) {
		return t.checkExpect(this.htdp.salePrice(30), 42)
			&& t.checkExpect(this.ror.salePrice(20), 16);
	}
	
	boolean testSameBookAuthor(Tester t) {
		return t.checkExpect(this.beaches.sameAuthor(this.prince),true);
			&& t.checkExpect(this.beaches.sameAuthor(this.code), false);
	}
}
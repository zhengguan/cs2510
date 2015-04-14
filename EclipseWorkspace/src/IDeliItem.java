// REPRESENTS: A deli menu item
interface IDeliItem{}

// A Soup is a new Soup(String, int, boolean)
// REPRESENTS: soup
class Soup implements IDeliItem{
	String name;
	int price;
	boolean isVegetarian;
	
	Soup(String name, int price, boolean isVegetarian){
		this.name = name;
		this.price = price;
		this.isVegetarian = isVegetarian;
	}
	
}

// A Salad is a new Salad(String, int, boolean, String)
// REPRESENTS: salad
class Salad implements IDeliItem{
	String name;
	int price;
	boolean isVegetarian;
	String dressing;
	
	Salad(String name, int price, boolean isVegetarian, String dressing){
		this.name = name;
		this.price = price;
		this.isVegetarian = isVegetarian;
		this.dressing = dressing;
	}
}

// A Sandwich is a 
//  new Sandwich(String, int, boolean, String, String, String)
// REPRESENT: sandwich
class Sandwich implements IDeliItem{
	String name;
	int price;
	String bread;
	String filling1;
	String filling2;
	
	Sandwich(String name, int price, String bread, 
			 String filling1, String filling2){
		this.name = name;
		this.price = price;
		this.bread = bread;
		this.filling1 = filling1;
		this.filling2 = filling2;
	}
}

// REPRESENTS: examples and tests for deli items
class ExamplesDeliItems{
	Soup fishSoup = new Soup("fish soup", 20, false);
	Soup tofuSoup = new Soup("tofu soup", 10, true);
	Salad chefSalad = new Salad("chef salad", 25, false, "vinegar");
	Salad toamtoSalad = new Salad("tomato salad", 15, true, "italian");
	Sandwich chickenSandwich = new Sandwich("chicken sandwich", 20, 
			"grain bread", "cheese", "jelly");
	Sandwich butterSandwich = new Sandwich("butter sandwich", 10, 
			"wheat bread", "butter", "lettuce");
}

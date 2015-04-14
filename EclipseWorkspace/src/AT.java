
// REPRESENTS: An Ancestor Tree
interface IAT{}

// REPRESENTS: unknown
class Unknown implements IAT{}

// A Tree is a new Tree(Person, AT, AT)
// REPRESENTS: An Ancestor Tree with Person
class ATree implements IAT{
	Person person;
	IAT at1;
	IAT at2;
	
	ATree(Person person, IAT at1, IAT at2){
		this.person = person;
		this.at1 = at1;
		this.at2 = at2;
	}
}

// REPRESENTS: examples and tests for ancestor tree
class ExamplesATs{
	Address bostonMA = new Address("Boston", "MA");
	Address warwickRI = new Address("Warwick", "RI");
	Address nashuaNH = new Address("Nashua", "NH");		
	
	Person tim = new Person("Tim", 20, "M", bostonMA);
	Person pat = new Person("Pat", 19, "F", warwickRI);
	Person kim = new Person("Kim", 17, "F", bostonMA);
	Person dan = new Person("Dan", 22, "M", nashuaNH);
	
	IAT at1 = new ATree(dan, new Unknown(), new Unknown());
	IAT at2 = new ATree(kim, new Unknown(), at1);
	IAT at3 = new ATree(pat, new Unknown(), new Unknown());
	IAT at4 = new ATree(tim, at3, at2);
}
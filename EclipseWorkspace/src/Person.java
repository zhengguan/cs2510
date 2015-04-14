// A Person is a new Person(String, int, String)
// REPRESENTS: A person
class Person{
	String name;
	int age;
	String gender;
	Address address;
	
	Person(String name, int age, String gender, Address address){
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
	}
}

// A Address is a new Address(String, String)
// REPRESENTS: An address
class Address{
	String city;
	String state;
	
	Address(String city, String state){
		this.city = city;
		this.state = state;
	}
}

// REPRESENTS: examples and tests for Persons
class ExamplesPersons{
	Address bostonMA = new Address("Boston", "MA");
	Address warwickRI = new Address("Warwick", "RI");
	Address nashuaNH = new Address("Nashua", "NH");		
	
	Person tim = new Person("Tim", 20, "M", bostonMA);
	Person pat = new Person("Pat", 19, "F", warwickRI);
	Person kim = new Person("Kim", 17, "F", bostonMA);
	Person dan = new Person("Dan", 22, "M", nashuaNH);
}
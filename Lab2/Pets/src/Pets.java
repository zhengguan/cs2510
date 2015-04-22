import tester.Tester;

//to represent a pet owner
class Person {
 String name;
 IPet pet;
 int age;

 Person(String name, IPet pet, int age) {
     this.name = name;
     this.pet = pet;
     this.age = age;
     
 }
 
 /* TEMPLATE:
    FIELDS:
    ... this.name ...           -- String
    ... this.pet ...            -- IPet
    ... this.age ...            -- int
    METHODS:
    ... this.isOlder(Person other) ...  -- boolean
  */
 
 // RETURNS: true iff this person is older thant the given person
 boolean isOlder(Person other) {
     return this.age > other.age;
 }
 
 // RETURNS: true iff the pet of this person is the same as the given
 // name
 boolean sameNamePet(String name) {
     return this.pet.sameName(name);
 }
 
 // RETURENS: a Person whose pet has perished
 Person perish() {
     return new Person(this.name, new NoPet(), this.age);
 } 
}
//to represent a pet
interface IPet { 
    // RETURNS: true iff this pet's name is the same as the given name
    boolean sameName(String name);
}

//to represent a pet cat
class Cat implements IPet {
 String name;
 String kind;
 boolean longhaired;

 Cat(String name, String kind, boolean longhaired) {
     this.name = name;
     this.kind = kind;
     this.longhaired = longhaired;
 }
 
 // RETURNS: true iff this pet's name is the same as the given name
 public boolean sameName(String name) {
     return this.name.equals(name);
 }
}

//to represent a pet dog
class Dog implements IPet {
 String name;
 String kind;
 boolean male;

 Dog(String name, String kind, boolean male) {
     this.name = name;
     this.kind = kind;
     this.male = male;
 }
 
 // RETURNS: true iff this pet's name is the same as the given name
 public boolean sameName(String name) {
     return this.name.equals(name);
 } 
}

// REPRESENTS: no pets
class NoPet implements IPet {
    NoPet(){}
    
    // RETURENS: true iff this pet's name is the same as the given name
    public boolean sameName(String name) {
        return false;
    }
}

// REPRESENTS: examples for pets
class ExamplesPets {
    IPet yellow = new Dog("Yellow", "machine", true);
    IPet dot = new Dog("Dot", "dot", false);
    IPet garfield = new Cat("Garfield", "birman", true);
    IPet dora = new Cat("Dora", "machine", false);
    
    Person tom = new Person("Tom", yellow, 20);
    Person lucy = new Person("Lucy", dot, 18);
    Person tim = new Person("Tim", garfield, 21);
    Person lily = new Person("Lily", dora, 18);
    Person lilyPetPerished = new Person("Lily", new NoPet(), 18);
    
    // tests for method isOlder in Person
    boolean testIsOlder(Tester t) {
        return
        t.checkExpect(tom.isOlder(lucy), true) &&
        t.checkExpect(tom.isOlder(tim), false);
    }
    
    // tests for method sameName in Pet
    boolean testSameName(Tester t) {
        return
        t.checkExpect(dot.sameName("Dot"), true) &&
        t.checkExpect(dot.sameName("Garfield"), false);
    }
    
    // tests for method sameNamePet in Person
    boolean testSameNamePet(Tester t) {
        return
        t.checkExpect(tom.sameNamePet("Yellow"), true) &&
        t.checkExpect(tom.sameNamePet("Dot"), false);
    }
    
    // tests for method perish in Person
    boolean testPerish(Tester t) {
        return
        t.checkExpect(lily.perish(),lilyPetPerished);
    }
}
import tester.Tester;

//to represent a pets owner
class Person {
 String name;
 ILoPet pets;
 int age;

 Person(String name, ILoPet pets, int age) {
     this.name = name;
     this.pets = pets;
     this.age = age;
     
 }
 
 /* TEMPLATE:
    FIELDS:
    ... this.name ...           -- String
    ... this.pets ...            -- ILoPet
    ... this.age ...            -- int
    METHODS:
    ... this.isOlder(Person other) ...  -- boolean
  */
 
 // RETURNS: true iff this person is older thant the given person
 boolean isOlder(Person other) {
     return this.age > other.age;
 }
 
 // RETURNS: true iff the pet of this person has a pet named the given
 // name
 boolean hasPetNamed(String name) {
     return this.pets.hasPetNamed(name);
 }
 
 // RETURENS: a Person whose pet has perished
 Person perish(String name) {
     return new Person(this.name, this.pets.perish(name), this.age);
 } 
}

// REPRESENTS: a list of pets
interface ILoPet {
    
    // RETURNS: true iff this list has a pet named the given name
    boolean hasPetNamed(String name);
    
    // RETURNS: a ILoPet like this one, but with the pet of the given 
    // name removed
    ILoPet perish(String name);
}

class MtLoPet implements ILoPet {
    MtLoPet() {}

    // RETURNS: true iff this list has a pet named the given name
    public boolean hasPetNamed(String name) {
        return false;
    }
    
    // RETURNS: a ILoPet like this one, but with the pet of the given 
    // name removed
    public ILoPet perish(String name) {
        return this;
    }
}

class ConsLoPet implements ILoPet {
    IPet first;
    ILoPet rest;
    
    ConsLoPet(IPet first, ILoPet rest) {
        this.first = first;
        this.rest = rest;
    }
    
    // RETURNS: true iff this list has a pet named the given name
    public boolean hasPetNamed(String name) {
        if (this.first.sameName(name)) {
            return true;
        }
        else {
            return this.rest.hasPetNamed(name);
        }
    }
    
    // RETURNS: a ILoPet like this one, but with the pet of the given 
    // name removed
    public ILoPet perish(String name) {
        if (this.first.sameName(name)) {
            return this.rest;
        }
        else {
            return new ConsLoPet(this.first,
                     this.rest.perish(name));
        }
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



// REPRESENTS: examples for pets
class ExamplesLoPets {
    IPet yellow = new Dog("Yellow", "machine", true);
    IPet dot = new Dog("Dot", "dot", false);
    IPet garfield = new Cat("Garfield", "birman", true);
    IPet dora = new Cat("Dora", "machine", false);
    
    ILoPet mtLop = new MtLoPet();
    ILoPet consLop1 = new ConsLoPet(yellow, mtLop);
    ILoPet consLop2 = new ConsLoPet(dot,
                        new ConsLoPet(yellow, mtLop));
    ILoPet consLop3 = new ConsLoPet(dora,
                        new ConsLoPet(garfield, 
                          new ConsLoPet(dot, mtLop)));
    
    Person tom = new Person("Tom", mtLop, 20);
    Person lucy = new Person("Lucy", consLop1, 18);
    Person tim = new Person("Tim", consLop2, 21);
    Person lily = new Person("Lily", consLop3, 18);
    Person timAfterPerish = new Person("Tim", consLop1, 21);
    
    
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
    
    
    
    // tests for method hasPetNamed
    boolean testHasPetNamed(Tester t) {
        return
        t.checkExpect(consLop3.hasPetNamed("Yellow"), false) &&
        t.checkExpect(consLop3.hasPetNamed("Dot"), true) &&
        t.checkExpect(lily.hasPetNamed("Yellow"), false) &&
        t.checkExpect(lily.hasPetNamed("Dot"), true);
    }
    
    // tests for method perish in Person
//    boolean testPerish(Tester t) {
//        return
//        t.checkExpect(lily.perish(),lilyPetPerished);
//    }
    // tests for method perish in Person
    boolean testPerish(Tester t) {
        return 
        t.checkExpect(tim.perish("Dot"), timAfterPerish);
    }
}
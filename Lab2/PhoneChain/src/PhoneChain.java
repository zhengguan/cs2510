import tester.Tester;

// REPRESENTS: a phone chain
interface IPhoneChain {
    
    // RETURNS: the number of Players rely on this link's
    // Player's phone call, including the player himself
    int countPlayers();
    
    // RETURNS: the number of Players that rely on this Link's 
    // Player's phone call, excluding this one
    int countPlayersCalled();
    
    // RETURNS: true iff this Player in this Link will call
    // a player with the given name
    boolean willCall(String name);
    
    // RETURNS: the length of the longest sequence of phone calls
    // that have to be made to reach any of the players
    int longestChain();
    
    // RETURNS: the number of Players in this phone chain have the 
    // given name
    int countPlayerNamed(String name);
    
    // RETURNS: the name of the league that the team should play in
    //  If everyone is younger than 13, "little league"
    //  If everyone is younger than 15, "junior varsity"
    //  If everyone is younger than 18, "varsity"
    //  If everyone is younger than 21, "collegiate"
    //  Otherwise, "FIFA"
    String teamLeague();
    
    // RETURNS: true iff all Players in this phone chain is younger
    // than the given value
    boolean allYoungerThan(int age);
    
}


// REPRESENTS: an empty phone chain
class MtPhoneChain implements IPhoneChain {
    MtPhoneChain(){}
    
    // RETURNS: the number of Players rely on this link's
    // Player's phone call, including the player himself
    public int countPlayers() {
        return 0;
    }
    
    // RETURNS: the number of Players that rely on this Link's 
    // Player's phone call, excluding this one
    public int countPlayersCalled() {
        return 0;
    }
    
    // RETURNS: true iff this Player in this Link will call
    // a player with the given name
    public boolean willCall(String name) {
        return false;
    }
    
    // RETURNS: the length of the longest sequence of phone calls
    // that have to be made to reach any of the players
    public int longestChain() {
        return 0;
    }
    
    // RETURNS: the number of Players in this phone chain have the 
    // given name
    public int countPlayerNamed(String name) {
        return 0;
    }
    
    // RETURNS: the name of the league that the team should play in
    //  If everyone is younger than 13, "little league"
    //  If everyone is younger than 15, "junior varsity"
    //  If everyone is younger than 18, "varsity"
    //  If everyone is younger than 21, "collegiate"
    //  Otherwise, "FIFA"
    public String teamLeague() {
        return "No member";
    }
    
    // RETURNS: true iff all Players in this phone chain is younger
    // than the given value
    public boolean allYoungerThan(int age) {
        return true;
    }
}

// REPRESENTS: an non-empty phone chain
class StruPhoneChain implements IPhoneChain {
    Player player;
    IPhoneChain pc1;
    IPhoneChain pc2;
    
    StruPhoneChain(Player player, IPhoneChain pc1, IPhoneChain pc2) {
        this.player = player;
        this.pc1 = pc1;
        this.pc2 = pc2;
    }
    
    // RETURNS: the number of Players rely on this link's
    // Player's phone call, including the player himself
    public int countPlayers() {
        return 1 + this.pc1.countPlayers() + this.pc2.countPlayers();
    }
    
    // RETURNS: the number of Players that rely on this Link's 
    // Player's phone call, excluding this one
    public int countPlayersCalled() {
        return this.pc1.countPlayers() + this.pc2.countPlayers();
    }
    
    // RETURNS: true iff this Player in this Link will call
    // a player with the given name
    public boolean willCall(String name) {
        if (this.player.sameName(name)) {
            return true;
        }
        else {
            return this.pc1.willCall(name) || this.pc2.willCall(name);
        }
    }
    
    // RETURNS: the length of the longest sequence of phone calls
    // that have to be made to reach any of the players
    public int longestChain() {
        return 1 + Math.max(this.pc1.longestChain(), 
                this.pc2.longestChain());
    }
    
    // RETURNS: the number of Players in this phone chain have the 
    // given name
    public int countPlayerNamed(String name) {
        if (this.player.sameName(name)) {
            return 1 + this.pc1.countPlayerNamed(name) +
                    this.pc2.countPlayerNamed(name);
        }
        else {
            return this.pc1.countPlayerNamed(name) + 
                    this.pc2.countPlayerNamed(name);
        }
    }
    
    // RETURNS: the name of the league that the team should play in
    //  If everyone is younger than 13, "little league"
    //  If everyone is younger than 15, "junior varsity"
    //  If everyone is younger than 18, "varsity"
    //  If everyone is younger than 21, "collegiate"
    //  Otherwise, "FIFA"
    public String teamLeague() {
        if (this.allYoungerThan(13)) {
            return new String("little league");
        }
        else if (this.allYoungerThan(15)) {
            return new String("junior varsity");
        }
        else if (this.allYoungerThan(18)) {
            return new String("varsity");
        }
        else if (this.allYoungerThan(21)) {
            return new String("collegiate");
        }
        else {
            return new String("FIFA");
        }
    }
    
    // RETURNS: true iff all Players in this phone chain is younger
    // than the given value
    public boolean allYoungerThan(int age) {
        return this.player.youngerThan(age) &&
               this.pc1.allYoungerThan(age) &&
               this.pc2.allYoungerThan(age);
    }
}

// REPRESENTS: a player
class Player {
    String name;
    String phoneNum;
    int age;
    
    Player(String name, String phoneNum, int age) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.age = age;
    }
    
    // RETURNS: true iff this Player's name is the same as the given name
    boolean sameName(String name) {
        return this.name.equals(name);
    }
    
    // RETURNS: true iff this player is younger than the given age
    boolean youngerThan(int age) {
        return this.age < age;
    }
}


// REPRESENTS: examples for phone chain
class ExamplesPhoneChains {
    // players
    Player jen = new Player("Jen", "1", 20);
    Player may = new Player("May", "1", 17);
    Player bea = new Player("Bea", "1", 17);
    Player kim = new Player("Kim", "1", 14);
    Player pat = new Player("Pat", "1", 14);
    Player ann = new Player("Ann", "1", 14);
    Player joy = new Player("Joy", "1", 14);
    Player tay = new Player("Tay", "2345", 12);
    Player zoe = new Player("Tay", "3456", 12);
    Player meg = new Player("Tay", "4567", 12);
    Player lou = new Player("Tay", "5678", 12);
    Player cam = new Player("Tay", "6789", 12);
    Player eve = new Player("Tay", "7890", 12);
    Player tam = new Player("Tay", "2233", 12);
    Player bea2 = new Player("Bea", "789", 22);
    
    IPhoneChain empty = new MtPhoneChain();
    IPhoneChain pc11 = new StruPhoneChain(this.tay, this.empty, this.empty);
    IPhoneChain pc12 = new StruPhoneChain(this.zoe, this.empty, this.empty);
    IPhoneChain pc13 = new StruPhoneChain(this.meg, this.empty, this.empty);
    IPhoneChain pc14 = new StruPhoneChain(this.lou, this.empty, this.empty);
    IPhoneChain pc15 = new StruPhoneChain(this.cam, this.empty, this.empty);
    IPhoneChain pc16 = new StruPhoneChain(this.eve, this.empty, this.empty);
    IPhoneChain pc17 = new StruPhoneChain(this.tam, this.empty, this.empty);
    IPhoneChain pc21 = new StruPhoneChain(this.kim, this.pc11, this.pc12);
    IPhoneChain pc22 = new StruPhoneChain(this.pat, this.pc13, this.pc14);
    IPhoneChain pc23 = new StruPhoneChain(this.ann, this.pc15, this.pc16);
    IPhoneChain pc24 = new StruPhoneChain(this.joy, this.pc17, this.empty);
    IPhoneChain pc31 = new StruPhoneChain(this.may, this.pc21, this.pc22);
    IPhoneChain pc32 = new StruPhoneChain(this.bea, this.pc23, this.pc24);
    IPhoneChain pc41 = new StruPhoneChain(this.jen, this.pc31, this.pc32);
    IPhoneChain pc51 = new StruPhoneChain(this.bea2, this.pc41, this.empty);
    
    // tests for method countPlayers
    boolean testCountPlayers(Tester t) {
        return
        t.checkExpect(this.pc41.countPlayers(), 14);
    }
    
    // tests for method countPlayersCalled
    boolean testCountPlayersCalled(Tester t) {
        return
        t.checkExpect(this.pc41.countPlayersCalled(), 13) &&
        t.checkExpect(this.pc32.countPlayersCalled(), 5);
    }
    
    // tests for method willCall
    boolean testWillCall(Tester t) {
        return
        t.checkExpect(this.pc31.willCall("Bea"), false) &&
        t.checkExpect(this.pc31.willCall("May"), true);
    }
    
    // tests for method longestChain
    boolean testLongestChain(Tester t) {
        return
        t.checkExpect(this.pc41.longestChain(), 4) &&
        t.checkExpect(this.pc17.longestChain(), 1);
    }
    
    // tests for method countPlayersNamed
    boolean testCountPlayersNamed(Tester t) {
        return
        t.checkExpect(this.pc41.countPlayerNamed("Bea"), 1) &&
        t.checkExpect(this.pc41.countPlayerNamed("Zgs"), 0) &&
        t.checkExpect(this.pc51.countPlayerNamed("Bea"), 2);
    }
    
    // tests for method teamLeague
    boolean testTeamLeague(Tester t) {
        return
        t.checkExpect(this.empty.teamLeague(), new String("No member")) &&
        t.checkExpect(this.pc11.teamLeague(), new String("little league")) &&
        t.checkExpect(this.pc21.teamLeague(), new String("junior varsity")) &&
        t.checkExpect(this.pc31.teamLeague(), new String("varsity")) &&
        t.checkExpect(this.pc41.teamLeague(), new String("collegiate")) &&
        t.checkExpect(this.pc51.teamLeague(), new String("FIFA"));
    }
}

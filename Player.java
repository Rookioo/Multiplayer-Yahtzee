public class Player {
    
    private String name;
    private int rollValue;

    // Constructor
    public Player(String name) {
        this.name = name;
    }
    
    // Gets the player name
    public String getName() {
        return name;
    }

    // Sets the roll value
    public void setRollValue(int rollValue) {
        this.rollValue = rollValue;
    }

    // Gets the roll value
    public int getRollValue() {
        return rollValue;
    }
}

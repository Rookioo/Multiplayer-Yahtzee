public class Player {
    
    private String name;
    private int rollValue;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRollValue(int rollValue) {
        this.rollValue = rollValue;
    }

    public int getRollValue() {
        return rollValue;
    }
}

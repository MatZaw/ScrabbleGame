package sample;
public class BoardField {
    private String bonus; //holds value of bonus
    private LetterCube letter;
    private boolean isAvailable;
    private boolean isUsed;

    public BoardField() {
        this.bonus = null;
        isAvailable = false;
        isUsed = false;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getBonus() {
        return bonus;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public LetterCube getLetter() {
        return letter;
    }

    public void setLetter(LetterCube letter) {
        this.letter = letter;
    }
}
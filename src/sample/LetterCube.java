package sample;

public class LetterCube {
    private final String letter;
    private final int value;

    LetterCube(String letter, int value){
        this.letter = letter;
        this.value = value;
    }

    public String getLetter() {
        return letter;
    }

    public int getValue() {
        return value;
    }
}
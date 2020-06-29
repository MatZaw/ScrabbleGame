package sample;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CubeBag {
    private List<LetterCube> bagContent;

    CubeBag(){
        bagContent = new ArrayList<LetterCube>();
        for(int i = 0; i < 9; i++) bagContent.add(new LetterCube("A", 1));
        bagContent.add(new LetterCube("A", 5));
        for(int i = 0; i < 2; i++) bagContent.add(new LetterCube("B", 3));
        for(int i = 0; i < 3; i++) bagContent.add(new LetterCube("C", 2));
        bagContent.add(new LetterCube("Ć", 6));
        for(int i = 0; i < 3; i++) bagContent.add(new LetterCube("D", 2));
        for(int i = 0; i < 7; i++) bagContent.add(new LetterCube("E", 1));
        bagContent.add(new LetterCube("Ę", 5));
        bagContent.add(new LetterCube("F", 5));
        for(int i = 0; i < 2; i++) bagContent.add(new LetterCube("G", 3));
        for(int i = 0; i < 2; i++) bagContent.add(new LetterCube("H", 3));
        for(int i = 0; i < 8; i++) bagContent.add(new LetterCube("I", 1));
        for(int i = 0; i < 2; i++) bagContent.add(new LetterCube("J", 3));
        for(int i = 0; i < 3; i++) bagContent.add(new LetterCube("K", 2));
        for(int i = 0; i < 3; i++) bagContent.add(new LetterCube("L", 2));
        for(int i = 0; i < 2; i++) bagContent.add(new LetterCube("Ł", 3));
        for(int i = 0; i < 3; i++) bagContent.add(new LetterCube("M", 2));
        for(int i = 0; i < 5; i++) bagContent.add(new LetterCube("N", 1));
        bagContent.add(new LetterCube("Ń", 7));
        for(int i = 0; i < 6; i++) bagContent.add(new LetterCube("O", 1));
        bagContent.add(new LetterCube("Ó", 5));
        for(int i = 0; i < 3; i++) bagContent.add(new LetterCube("P", 2));
        for(int i = 0; i < 4; i++) bagContent.add(new LetterCube("R", 1));
        for(int i = 0; i < 4; i++) bagContent.add(new LetterCube("S", 1));
        bagContent.add(new LetterCube("Ś", 5));
        for(int i = 0; i < 3; i++) bagContent.add(new LetterCube("T", 2));
        for(int i = 0; i < 2; i++) bagContent.add(new LetterCube("U", 3));
        for(int i = 0; i < 4; i++) bagContent.add(new LetterCube("W", 1));
        for(int i = 0; i < 4; i++) bagContent.add(new LetterCube("Y", 2));
        for(int i = 0; i < 5; i++) bagContent.add(new LetterCube("Z", 1));
        bagContent.add(new LetterCube("Ź", 9));
        bagContent.add(new LetterCube("Ż", 5));
        for(int i = 0; i < 2; i++) bagContent.add(new LetterCube("", 0));
    }

    public LetterCube getRandomCube() throws EmptyBagException{
        if(bagContent.isEmpty()) throw new EmptyBagException();
        Random random = new Random();
        int index = random.nextInt(this.bagContent.size());
        LetterCube cube = this.bagContent.get(index);
        this.bagContent.remove(index);
        return cube;
    }

    public LetterCube replaceCube(LetterCube returnedCube){
        Random random = new Random();
        int index = random.nextInt(this.bagContent.size());
        LetterCube cube = this.bagContent.get(index);
        this.bagContent.remove(index);
        this.bagContent.add(returnedCube);
        return cube;
    }

    public List<LetterCube> getBagContent() {
        return bagContent;
    }
}
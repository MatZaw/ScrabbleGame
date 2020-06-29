package sample;
import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    private int currentPoints;
    private List<LetterCube> playerCubes;
    private CubeBag currentCubeBagReference;


    Player(String name, CubeBag cubeBag) throws EmptyBagException{
        this.currentCubeBagReference = cubeBag;
        this.name = name;
        this.playerCubes = new ArrayList<LetterCube>();
        for(int i = 0; i < 7; i++) playerCubes.add(currentCubeBagReference.getRandomCube());
    }

    public void replaceCube(int index) throws  EmptyBagException{
        playerCubes.remove(index);
        playerCubes.add(currentCubeBagReference.getRandomCube());
    }

    public int getNumberOfCubes(){
        return playerCubes.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public void getRandomCube() throws EmptyBagException{
        playerCubes.add(currentCubeBagReference.getRandomCube());
    }

    public List<LetterCube> getPlayerCubes() {
        return playerCubes;
    }
}
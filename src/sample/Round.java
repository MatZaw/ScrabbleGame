package sample;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Round {
    private Player activePlayer;
    private Board currentBoardReference;
    private Board temporaryBoard;
    private List<BoardIndex> usedFields;
    private List<LetterCube> usedCubes;

    Round(Player player, Board board){
        activePlayer = player;
        currentBoardReference = board;
        temporaryBoard = new Board(board);
        usedFields = new Stack<BoardIndex>();
        usedCubes = new Stack<LetterCube>();
    }

    public void putALetterOnBoard(int letterIndex, BoardIndex index) {
        if (index.getY() < 0 || index.getY() > 14 || index.getX() < 0 || index.getX() > 14) return;
        if(temporaryBoard.getCurrentBoard()[7][7].isAvailable()){
            try{
                putFirstLetterOnBoard(letterIndex, index);
            }catch (UnavailableFieldException e){
                return;
            }
        }
        else{
            try{
                putNextLetterOnBoard(letterIndex, index);
            }catch (UnavailableFieldException e){
                return;
            }
        }
    }

    private void putFirstLetterOnBoard(int letterIndex, BoardIndex index) throws UnavailableFieldException{
        if(temporaryBoard.getCurrentBoard()[index.getY()][index.getX()].isAvailable() && !temporaryBoard.getCurrentBoard()[index.getY()][index.getX()].isUsed()){
            usedFields.add(index);
            usedCubes.add(activePlayer.getPlayerCubes().get(letterIndex));
            temporaryBoard.getCurrentBoard()[index.getY()][index.getX()].setUsed(true);
            temporaryBoard.getCurrentBoard()[index.getY()][index.getX()].setLetter(activePlayer.getPlayerCubes().get(letterIndex));
            activePlayer.getPlayerCubes().remove(letterIndex);
            temporaryBoard.getCurrentBoard()[index.getY() - 1][index.getX()].setAvailable(true);
            temporaryBoard.getCurrentBoard()[index.getY() + 1][index.getX()].setAvailable(true);
            temporaryBoard.getCurrentBoard()[index.getY()][index.getX() - 1].setAvailable(true);
            temporaryBoard.getCurrentBoard()[index.getY()][index.getX() + 1].setAvailable(true);
            temporaryBoard.getCurrentBoard()[index.getY()][index.getX()].setAvailable(false);
        }
        else throw new UnavailableFieldException();
    }

    private void putNextLetterOnBoard(int letterIndex, BoardIndex index) throws UnavailableFieldException{
        String side;
        if(!this.usedFields.isEmpty()){
            BoardIndex previousElement = this.usedFields.get(this.usedFields.size() - 1);
            if(previousElement.getY() == index.getY()){
                if(previousElement.getX() == index.getX() - 1){
                    side = "RIGHT";
                }
                else{
                    side = "LEFT";
                }
            }
            else if(previousElement.getX() == index.getX()){
                if(previousElement.getY() == index.getY() - 1){
                    side = "BOTTOM";
                }
                else{
                    side = "TOP";
                }
            }
            else{
                throw new UnavailableFieldException();
            }

            for (int i = 0; i < 15; i++){
                for(int j = 0; j < 15; j++){
                    temporaryBoard.getCurrentBoard()[i][j].setAvailable(false);
                }
            }

            switch(side){
                case "RIGHT":
                    temporaryBoard.getCurrentBoard()[previousElement.getY()][previousElement.getX() + 1].setAvailable(true);
                    break;

                case "LEFT":
                    temporaryBoard.getCurrentBoard()[previousElement.getY()][previousElement.getX() - 1].setAvailable(true);
                    break;

                case "TOP":
                    temporaryBoard.getCurrentBoard()[previousElement.getY() - 1][previousElement.getX()].setAvailable(true);
                    break;

                case "BOTTOM":
                    temporaryBoard.getCurrentBoard()[previousElement.getY() + 1][previousElement.getX()].setAvailable(true);
                    break;
            }

            if(temporaryBoard.getCurrentBoard()[index.getY()][index.getX()].isAvailable() && !temporaryBoard.getCurrentBoard()[index.getY()][index.getX()].isUsed()){
                usedFields.add(index);
                usedCubes.add(activePlayer.getPlayerCubes().get(letterIndex));
                temporaryBoard.getCurrentBoard()[index.getY()][index.getX()].setUsed(true);
                temporaryBoard.getCurrentBoard()[index.getY()][index.getX()].setLetter(activePlayer.getPlayerCubes().get(letterIndex));
                activePlayer.getPlayerCubes().remove(letterIndex);
                switch (side) {
                    case "RIGHT":
                        if (index.getX() + 1 <= 14)
                            temporaryBoard.getCurrentBoard()[index.getY()][index.getX() + 1].setAvailable(true);
                        break;
                    case "LEFT":
                        if (index.getX() - 1 >= 0)
                            temporaryBoard.getCurrentBoard()[index.getY()][index.getX() - 1].setAvailable(true);
                        break;
                    case "TOP":
                        if (index.getY() >= 0)
                            temporaryBoard.getCurrentBoard()[index.getY() - 1][index.getX()].setAvailable(true);
                        break;
                    case "BOTTOM":
                        if (index.getY() <= 14)
                            temporaryBoard.getCurrentBoard()[index.getY() + 1][index.getX()].setAvailable(true);
                        break;
                }
            }
            else throw new UnavailableFieldException();
        }
        else{
            if(temporaryBoard.getCurrentBoard()[index.getY()][index.getX()].isAvailable() && !temporaryBoard.getCurrentBoard()[index.getY()][index.getX()].isUsed()) {
                usedFields.add(index);
                usedCubes.add(activePlayer.getPlayerCubes().get(letterIndex));
                temporaryBoard.getCurrentBoard()[index.getY()][index.getX()].setUsed(true);
                temporaryBoard.getCurrentBoard()[index.getY()][index.getX()].setLetter(activePlayer.getPlayerCubes().get(letterIndex));
                activePlayer.getPlayerCubes().remove(letterIndex);
            }
        }
    }

    public Board endRound(){
        if(!checkCorrectness()){
            for(LetterCube cube: usedCubes) activePlayer.getPlayerCubes().add(cube);
            return currentBoardReference;
        }
        Stack<String> bonusList = new Stack<String>();
        this.setAvailability();
        BoardIndex tmpIndex;
        LetterCube tmp;
        int points = 0;
        while(!this.usedFields.isEmpty()){
            tmpIndex = this.usedFields.get(this.usedFields.size() - 1);
            this.usedFields.remove(this.usedFields.size() - 1);
            tmp = temporaryBoard.getCurrentBoard()[tmpIndex.getY()][tmpIndex.getX()].getLetter();
            if(temporaryBoard.getCurrentBoard()[tmpIndex.getY()][tmpIndex.getX()].getBonus() == "3L"){
                points += tmp.getValue() * 3;
            }
            else if(temporaryBoard.getCurrentBoard()[tmpIndex.getY()][tmpIndex.getX()].getBonus() == "2L"){
                points += tmp.getValue() * 2;
            }
            else{
                points += tmp.getValue();
            }
            if(temporaryBoard.getCurrentBoard()[tmpIndex.getY()][tmpIndex.getX()].getBonus() == "3W"){
                bonusList.push("3W");
            }
            else if(temporaryBoard.getCurrentBoard()[tmpIndex.getY()][tmpIndex.getX()].getBonus() == "2W"){
                bonusList.push("2W");
            }
        }
        String tmpString;
        while(!bonusList.isEmpty()){
            tmpString = bonusList.pop();
            if(tmpString == "3W"){
                points *= 3;
            }
            else if(tmpString == "2W"){
                points *= 2;
            }
        }
        activePlayer.setCurrentPoints(activePlayer.getCurrentPoints() + points);
        for(int i = activePlayer.getNumberOfCubes(); i < 7; i++){
            try{
                activePlayer.getRandomCube();
            }catch (EmptyBagException e){
                System.out.println("error");
                return null;
            }
        }
        return this.temporaryBoard;
    }

    private void setAvailability(){
        for(BoardIndex boardIndex: usedFields){
            temporaryBoard.getCurrentBoard()[boardIndex.getY() + 1][boardIndex.getX()].setAvailable(true);
            temporaryBoard.getCurrentBoard()[boardIndex.getY() - 1][boardIndex.getX()].setAvailable(true);
            temporaryBoard.getCurrentBoard()[boardIndex.getY()][boardIndex.getX() + 1].setAvailable(true);
            temporaryBoard.getCurrentBoard()[boardIndex.getY()][boardIndex.getX() - 1].setAvailable(true);
        }
    }

    private boolean checkCorrectness(){
        String word = "";
        String reversedWord;
        for(LetterCube letterCube: usedCubes){
            word += letterCube.getLetter();
        }
        reversedWord = new StringBuilder(word).reverse().toString();
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader("src/sample/slowa.txt"));
            String line = reader.readLine();
            while(line != null){
                if(line == word || line == reversedWord){
                    reader.close();
                    return true;
                }
                line = reader.readLine();
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public Board getTemporaryBoard() {
        return temporaryBoard;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }
}
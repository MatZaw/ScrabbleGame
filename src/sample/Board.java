package sample;
import java.util.Arrays;

public class Board {
    private BoardField[][] currentBoard;
    Board(){
        currentBoard = new BoardField[15][15];
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                currentBoard[i][j] = new BoardField();
                currentBoard[i][j].setLetter(new LetterCube("", 0));
            }
        }
        currentBoard[0][0].setBonus("3W");
        currentBoard[0][7].setBonus("3W");
        currentBoard[0][14].setBonus("3W");
        currentBoard[7][0].setBonus("3W");
        currentBoard[7][14].setBonus("3W");
        currentBoard[14][0].setBonus("3W");
        currentBoard[14][7].setBonus("3W");
        currentBoard[14][14].setBonus("3W");

        currentBoard[0][3].setBonus("2L");
        currentBoard[0][11].setBonus("2L");
        currentBoard[2][6].setBonus("2L");
        currentBoard[2][8].setBonus("2L");
        currentBoard[3][0].setBonus("2L");
        currentBoard[3][7].setBonus("2L");
        currentBoard[3][14].setBonus("2L");
        currentBoard[6][2].setBonus("2L");
        currentBoard[6][6].setBonus("2L");
        currentBoard[6][8].setBonus("2L");
        currentBoard[6][12].setBonus("2L");
        currentBoard[7][3].setBonus("2L");
        currentBoard[7][11].setBonus("2L");
        currentBoard[8][2].setBonus("2L");
        currentBoard[8][6].setBonus("2L");
        currentBoard[8][8].setBonus("2L");
        currentBoard[8][12].setBonus("2L");
        currentBoard[11][0].setBonus("2L");
        currentBoard[11][7].setBonus("2L");
        currentBoard[11][14].setBonus("2L");
        currentBoard[14][3].setBonus("2L");
        currentBoard[14][11].setBonus("2L");

        currentBoard[1][1].setBonus("2W");
        currentBoard[1][13].setBonus("2W");
        currentBoard[2][2].setBonus("2W");
        currentBoard[2][12].setBonus("2W");
        currentBoard[3][3].setBonus("2W");
        currentBoard[3][11].setBonus("2W");
        currentBoard[4][4].setBonus("2W");
        currentBoard[4][10].setBonus("2W");
        currentBoard[10][4].setBonus("2W");
        currentBoard[10][10].setBonus("2W");
        currentBoard[11][3].setBonus("2W");
        currentBoard[11][11].setBonus("2W");
        currentBoard[12][2].setBonus("2W");
        currentBoard[12][12].setBonus("2W");
        currentBoard[13][1].setBonus("2W");
        currentBoard[13][13].setBonus("2W");

        currentBoard[1][5].setBonus("3L");
        currentBoard[1][9].setBonus("3L");
        currentBoard[5][1].setBonus("3L");
        currentBoard[5][5].setBonus("3L");
        currentBoard[5][9].setBonus("3L");
        currentBoard[5][13].setBonus("3L");
        currentBoard[9][1].setBonus("3L");
        currentBoard[9][5].setBonus("3L");
        currentBoard[9][9].setBonus("3L");
        currentBoard[9][13].setBonus("3L");
        currentBoard[13][5].setBonus("3L");
        currentBoard[13][9].setBonus("3L");

        currentBoard[7][7].setAvailable(true);
    }

    Board(Board board){
        currentBoard = new BoardField[15][15];
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                currentBoard[i][j] = new BoardField();
            }
        }
        currentBoard[0][0].setBonus("3W");
        currentBoard[0][7].setBonus("3W");
        currentBoard[0][14].setBonus("3W");
        currentBoard[7][0].setBonus("3W");
        currentBoard[7][14].setBonus("3W");
        currentBoard[14][0].setBonus("3W");
        currentBoard[14][7].setBonus("3W");
        currentBoard[14][14].setBonus("3W");

        currentBoard[0][3].setBonus("2L");
        currentBoard[0][11].setBonus("2L");
        currentBoard[2][6].setBonus("2L");
        currentBoard[2][8].setBonus("2L");
        currentBoard[3][0].setBonus("2L");
        currentBoard[3][7].setBonus("2L");
        currentBoard[3][14].setBonus("2L");
        currentBoard[6][2].setBonus("2L");
        currentBoard[6][6].setBonus("2L");
        currentBoard[6][8].setBonus("2L");
        currentBoard[6][12].setBonus("2L");
        currentBoard[7][3].setBonus("2L");
        currentBoard[7][11].setBonus("2L");
        currentBoard[8][2].setBonus("2L");
        currentBoard[8][6].setBonus("2L");
        currentBoard[8][8].setBonus("2L");
        currentBoard[8][12].setBonus("2L");
        currentBoard[11][0].setBonus("2L");
        currentBoard[11][7].setBonus("2L");
        currentBoard[11][14].setBonus("2L");
        currentBoard[14][3].setBonus("2L");
        currentBoard[14][11].setBonus("2L");

        currentBoard[1][1].setBonus("2W");
        currentBoard[1][13].setBonus("2W");
        currentBoard[2][2].setBonus("2W");
        currentBoard[2][12].setBonus("2W");
        currentBoard[3][3].setBonus("2W");
        currentBoard[3][11].setBonus("2W");
        currentBoard[4][4].setBonus("2W");
        currentBoard[4][10].setBonus("2W");
        currentBoard[10][4].setBonus("2W");
        currentBoard[10][10].setBonus("2W");
        currentBoard[11][3].setBonus("2W");
        currentBoard[11][11].setBonus("2W");
        currentBoard[12][2].setBonus("2W");
        currentBoard[12][12].setBonus("2W");
        currentBoard[13][1].setBonus("2W");
        currentBoard[13][13].setBonus("2W");

        currentBoard[1][5].setBonus("3L");
        currentBoard[1][9].setBonus("3L");
        currentBoard[5][1].setBonus("3L");
        currentBoard[5][5].setBonus("3L");
        currentBoard[5][9].setBonus("3L");
        currentBoard[5][13].setBonus("3L");
        currentBoard[9][1].setBonus("3L");
        currentBoard[9][5].setBonus("3L");
        currentBoard[9][9].setBonus("3L");
        currentBoard[9][13].setBonus("3L");
        currentBoard[13][5].setBonus("3L");
        currentBoard[13][9].setBonus("3L");

        currentBoard[7][7].setAvailable(true);

        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                currentBoard[i][j].setBonus(board.getCurrentBoard()[i][j].getBonus());
                currentBoard[i][j].setAvailable(board.getCurrentBoard()[i][j].isAvailable());
                currentBoard[i][j].setUsed(board.getCurrentBoard()[i][j].isUsed());
                currentBoard[i][j].setLetter(board.getCurrentBoard()[i][j].getLetter());
            }
        }
    }

    public BoardField[][] getCurrentBoard() {
        return currentBoard;
    }
}
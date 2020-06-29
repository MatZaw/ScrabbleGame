package sample;
public class ConsoleTestMain {
    public static void main(String[] args){
        Game game = new Game("playerA", "playerB");
        System.out.println("xD");

        for(int i = 0; i < 15; i++){
            System.out.println();
            for(int j = 0; j < 15; j++){
                if(game.getBoard().getCurrentBoard()[i][j].getBonus() == null) System.out.print("");
                else System.out.print(game.getBoard().getCurrentBoard()[i][j].getBonus());
                if(game.getBoard().getCurrentBoard()[i][j].isAvailable()) System.out.print("A");
                if(game.getBoard().getCurrentBoard()[i][j].isUsed()) System.out.print("U");
                System.out.print("\t");
            }
        }

        System.out.println();
        System.out.println();
        System.out.print("Player A letters: ");

        for(LetterCube cube: game.getPlayerA().getPlayerCubes()){
            System.out.print(cube.getLetter() + "("+ cube.getValue() +")   ");
        }

        System.out.println();
        System.out.print("Player B letters: ");

        for(LetterCube cube: game.getPlayerB().getPlayerCubes()){
            System.out.print(cube.getLetter() + "("+ cube.getValue() +")   ");
        }

        System.out.println();
        System.out.print("Letters in bag: ");

        for(LetterCube cube: game.getCubeBag().getBagContent()){
            System.out.print(cube.getLetter() + "("+ cube.getValue() +")   ");
        }
        System.out.println();
        System.out.print(game.getCubeBag().getBagContent().size());

        game.newRound(game.getPlayerA());

        BoardIndex boardIndex = new BoardIndex(7, 7);
        game.getOngoingRound().putALetterOnBoard(0, boardIndex);
        boardIndex = new BoardIndex(7, 8);
        game.getOngoingRound().putALetterOnBoard(0, boardIndex);
        boardIndex = new BoardIndex(7, 9);
        game.getOngoingRound().putALetterOnBoard(0, boardIndex);
        boardIndex = new BoardIndex(7, 10);
        game.getOngoingRound().putALetterOnBoard(0, boardIndex);
        boardIndex = new BoardIndex(7, 11);
        game.getOngoingRound().putALetterOnBoard(0, boardIndex);
        game.endOfRound();

        for(int i = 0; i < 15; i++){
            System.out.println();
            for(int j = 0; j < 15; j++){
                if(game.getBoard().getCurrentBoard()[i][j].getBonus() == null) System.out.print("");
                else System.out.print(game.getBoard().getCurrentBoard()[i][j].getBonus());
                if(game.getBoard().getCurrentBoard()[i][j].isAvailable()) System.out.print("A");
                if(game.getBoard().getCurrentBoard()[i][j].isUsed()) System.out.print("U");
                System.out.print("\t");
            }
        }
    }
}
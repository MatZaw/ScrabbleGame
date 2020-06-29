package sample;
import java.util.List;

public class Game {
    private short players;
    private int roundCount;
    private Board board;
    private CubeBag cubeBag;
    private Player playerA;
    private Player PlayerB;
    private Round ongoingRound;

    Game(String playerAName, String playerBName){
        this.board = new Board();
        this.cubeBag = new CubeBag();
        try{
            this.playerA = new Player(playerAName, this.cubeBag);
            this.PlayerB = new Player(playerBName, this.cubeBag);
        }catch (EmptyBagException e){
            System.out.println("error");
        }
    }

    public void newRound(Player player){
        ongoingRound = new Round(player, board);
    }

    public void endOfRound(){
        board = ongoingRound.endRound();
        ongoingRound = null;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return PlayerB;
    }

    public CubeBag getCubeBag() {
        return cubeBag;
    }

    public Round getOngoingRound() {
        return ongoingRound;
    }
}
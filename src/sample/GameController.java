package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController  implements Initializable {

    private TextField[][] table; // tablica do dodania pól na start do View
    public AnchorPane top; // pojemnik na pola planszy
    public Button letter, letter1,letter2,letter3,letter4,letter5,letter6; // litery gracza
    ArrayList<String> currentIndexes;
    Game game;

    public Label player1_name, player1_score, player2_name, player2_score;

    public GameController() {game = new Game("Gracz 1:", "Gracz 2:"); game.newRound(game.getPlayerA());}

    // ustawianie string literki do spuszczenia w pole
    @FXML
    private void handleDragDetection(MouseEvent event){
        Dragboard db = letter.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(letter.getText()+",1");
        db.setContent(cb);
        event.consume();
    }
    @FXML
    private void handleDragDetection1(MouseEvent event){
        Dragboard db = letter1.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(letter1.getText()+",2");
        db.setContent(cb);
        event.consume();
    }
    @FXML
    private void handleDragDetection2(MouseEvent event){
        Dragboard db = letter2.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(letter2.getText()+",3");
        db.setContent(cb);
        event.consume();
    }
    @FXML
    private void handleDragDetection3(MouseEvent event){
        Dragboard db = letter3.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(letter3.getText()+",4");
        db.setContent(cb);
        event.consume();
    }
    @FXML
    private void handleDragDetection4(MouseEvent event){
        Dragboard db = letter4.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(letter4.getText()+",5");
        db.setContent(cb);
        event.consume();
    }
    @FXML
    private void handleDragDetection5(MouseEvent event){
        Dragboard db = letter5.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(letter5.getText()+",6");
        db.setContent(cb);
        event.consume();
    }
    @FXML
    private void handleDragDetection6(MouseEvent event){
        Dragboard db = letter6.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cb = new ClipboardContent();
        cb.putString(letter6.getText()+",7");
        db.setContent(cb);
        event.consume();
    }
    //------------------------------------------

    // Zaczepienie odbioru danych przez pole:
    @FXML
    private void handleDragOver(DragEvent event){
        if(event.getDragboard().hasString()){
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
    @FXML
    private String handleTextDrop(DragEvent event){
        String str = event.getDragboard().getString();
        String[] split = str.split(",");
        switch(split[1]){
            case "1": letter.setVisible(false); break;
            case "2": letter1.setVisible(false); break;
            case "3": letter2.setVisible(false); break;
            case "4": letter3.setVisible(false); break;
            case "5": letter4.setVisible(false); break;
            case "6": letter5.setVisible(false); break;
            case "7": letter6.setVisible(false); break;
        }

        return str;
    }
// --------------------------------------------------


    //---------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int size = 15; // rozmiar planszy

       currentIndexes = new ArrayList<>();
        for(int i=1;i<8;i++) currentIndexes.add(String.valueOf(i));

        table = new TextField[size][size];  // tablica z polami planszy
        // START GRY
      //  game = new Game("Gracz 1:", "Gracz 2:");
        player1_name.setText(game.getPlayerA().getName());
        player2_name.setText(game.getPlayerB().getName());

        player1_score.setText(String.valueOf(game.getPlayerA().getCurrentPoints()));
        player2_score.setText(String.valueOf(game.getPlayerA().getCurrentPoints()));



        setLetters();

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){

                table[i][j] = new TextField();
                table[i][j].setText(game.getBoard().getCurrentBoard()[i][j].getBonus());
                table[i][j].setEditable(false);
                table[i][j].maxWidthProperty().set(45);
                table[i][j].maxHeightProperty().set(45);
                table[i][j].setOnDragOver(e -> handleDragOver(e));

                // Czynnosci po uposzczeniu na dane pole litery

                int finalI1 = i; // wymagane by moc uzyc w setOnDragDropped
                int finalJ1 = j;

                table[i][j].setOnDragDropped(e->{
                    // Odebranie Stringa z litera (np.: s = "A,1" tj. "Litera,Indeks")
                    String s = handleTextDrop(e);
                    String[] split = s.split(",");

                    // Sprawdzenie czy pole na pewno jest dostepne?
                    if(game.getOngoingRound().getTemporaryBoard().getCurrentBoard()[finalI1][finalJ1].isAvailable()){
                        table[finalI1][finalJ1].setText(split[0]);
                        game.getOngoingRound().putALetterOnBoard(currentIndexes.indexOf(split[1]), new BoardIndex(finalI1, finalJ1));

                        //jesli dostepne to usuwam indeks litery z listy dostepnych liter
                        System.out.println("Przed: " + currentIndexes.toString());
                        currentIndexes.remove(currentIndexes.indexOf(split[1]));
                        System.out.println("Po: " + currentIndexes.toString());

                    }else{

                        // Przywrocenie widocznosci przycisku
                        switch(split[1]){
                            case "1": letter.setVisible(true); break;
                            case "2": letter1.setVisible(true); break;
                            case "3": letter2.setVisible(true); break;
                            case "4": letter3.setVisible(true); break;
                            case "5": letter4.setVisible(true); break;
                            case "6": letter5.setVisible(true); break;
                            case "7": letter6.setVisible(true); break;
                        }
                    }

                    // --------------------------------
                });
                top.getChildren().add(table[i][j]);
            }
        }

        // -----------zmiana koloru pól-----------
        String red = "-fx-control-inner-background: #" + "eb4034";
        String light_blue = "-fx-control-inner-background: #" + "7abcc2";
        String dark_blue = "-fx-control-inner-background: #" + "1d578a";
        String pink = "-fx-control-inner-background: #" + "f7c8bc";

        table[0][0].setStyle(red);
        table[0][7].setStyle(red);
        table[0][14].setStyle(red);
        table[7][0].setStyle(red);
        table[7][14].setStyle(red);
        table[14][0].setStyle(red);
        table[14][7].setStyle(red);
        table[14][14].setStyle(red);

        table[0][3].setStyle(light_blue);
        table[0][11].setStyle(light_blue);
        table[2][6].setStyle(light_blue);
        table[2][8].setStyle(light_blue);
        table[3][0].setStyle(light_blue);
        table[3][7].setStyle(light_blue);
        table[3][14].setStyle(light_blue);
        table[6][2].setStyle(light_blue);
        table[6][6].setStyle(light_blue);
        table[6][8].setStyle(light_blue);
        table[6][12].setStyle(light_blue);
        table[7][3].setStyle(light_blue);
        table[7][11].setStyle(light_blue);
        table[8][2].setStyle(light_blue);
        table[8][6].setStyle(light_blue);
        table[8][8].setStyle(light_blue);
        table[8][12].setStyle(light_blue);
        table[11][0].setStyle(light_blue);
        table[11][7].setStyle(light_blue);
        table[11][14].setStyle(light_blue);
        table[14][3].setStyle(light_blue);
        table[14][11].setStyle(light_blue);

        table[1][1].setStyle(pink);
        table[1][13].setStyle(pink);
        table[2][2].setStyle(pink);
        table[2][12].setStyle(pink);
        table[3][3].setStyle(pink);
        table[3][11].setStyle(pink);
        table[4][4].setStyle(pink);
        table[4][10].setStyle(pink);
        table[10][4].setStyle(pink);
        table[10][10].setStyle(pink);
        table[11][3].setStyle(pink);
        table[11][11].setStyle(pink);
        table[12][2].setStyle(pink);
        table[12][12].setStyle(pink);
        table[13][1].setStyle(pink);
        table[13][13].setStyle(pink);

        table[1][5].setStyle(dark_blue);
        table[1][9].setStyle(dark_blue);
        table[5][1].setStyle(dark_blue);
        table[5][5].setStyle(dark_blue);
        table[5][9].setStyle(dark_blue);
        table[5][13].setStyle(dark_blue);
        table[9][1].setStyle(dark_blue);
        table[9][5].setStyle(dark_blue);
        table[9][9].setStyle(dark_blue);
        table[9][13].setStyle(dark_blue);
        table[13][5].setStyle(dark_blue);
        table[13][9].setStyle(dark_blue);

        table[7][7].setStyle(pink);
        //--------------------------------------

        // Rozmieszczenie planszy:

        double x = (1000-((15*45)+(5*15)))/2;
        double y = 30;

        int iter = 0;
        for(Object e : top.getChildren().toArray()){
            TextField n = (TextField) e;
            if(iter < 15){
                n.setLayoutX(n.getLayoutX()+x);
                x+=50;
                n.setLayoutY(n.getLayoutY()+y);
                iter++;
            }else{
                x = (1000-((15*45)+(5*15)))/2;
                y += 30;
                iter = 1;

                n.setLayoutX(n.getLayoutX()+x);
                n.setLayoutY(n.getLayoutY()+y);

                x+=50;
            }
        }
        //-------------------------------------------
    }

    private void setLetters() {
        letter.setText(game.getPlayerA().getPlayerCubes().get(0).getLetter());
        letter1.setText(game.getPlayerA().getPlayerCubes().get(1).getLetter());
        letter2.setText(game.getPlayerA().getPlayerCubes().get(2).getLetter());
        letter3.setText(game.getPlayerA().getPlayerCubes().get(3).getLetter());
        letter4.setText(game.getPlayerA().getPlayerCubes().get(4).getLetter());
        letter5.setText(game.getPlayerA().getPlayerCubes().get(5).getLetter());
        letter6.setText(game.getPlayerA().getPlayerCubes().get(6).getLetter());
    }

    private void updateBoard(){

        String letter;
        String bonus;
        int X = 0, Y = 0;

        for(Node e : top.getChildren()){

            //TextField n = ;
            if(e instanceof TextField){
                 letter = game.getOngoingRound().getTemporaryBoard().getCurrentBoard()[X][Y].getLetter().getLetter();
                 bonus = game.getOngoingRound().getTemporaryBoard().getCurrentBoard()[X][Y].getBonus();

                if(letter == ""){
                    ((TextField) e).setText(bonus);
                }else ((TextField) e).setText(letter);

                if(Y < 15){
                    Y++;
                }else{
                    X++; Y = 0;
                }

            }
        }

    }

    // Obsluga tur:
    public void koniecTury(){
        Player lastPlayer = game.getOngoingRound().getActivePlayer();
        if(lastPlayer == game.getPlayerA()){

          //  updateBoard();

            game.endOfRound(); // koniec rundy

            game.newRound(game.getPlayerB()); // start nowej rundy



            System.out.println(game.getOngoingRound().getActivePlayer().getCurrentPoints());
            player2_score.setText(String.valueOf(game.getOngoingRound().getActivePlayer().getCurrentPoints())); // Aktualizacja punktacji
            // Reset listy z indeksami
            currentIndexes.removeAll(currentIndexes);
            for(int i=1;i<8;i++) currentIndexes.add(String.valueOf(i));

            // Zmiana liter:
            letter.setText(game.getPlayerB().getPlayerCubes().get(0).getLetter());
            letter1.setText(game.getPlayerB().getPlayerCubes().get(1).getLetter());
            letter2.setText(game.getPlayerB().getPlayerCubes().get(2).getLetter());
            letter3.setText(game.getPlayerB().getPlayerCubes().get(3).getLetter());
            letter4.setText(game.getPlayerB().getPlayerCubes().get(4).getLetter());
            letter5.setText(game.getPlayerB().getPlayerCubes().get(5).getLetter());
            letter6.setText(game.getPlayerB().getPlayerCubes().get(6).getLetter());
            //-----

        }else{
            //updateBoard();
            game.endOfRound(); // koniec rundy
            game.newRound(game.getPlayerA()); // start nowej rundy
            System.out.println(game.getOngoingRound().getActivePlayer().getCurrentPoints());
            player1_score.setText(String.valueOf(game.getOngoingRound().getActivePlayer().getCurrentPoints())); // Aktualizacja punktacji
            // Reset listy z indeksami
            currentIndexes.removeAll(currentIndexes);
            for(int i=1;i<8;i++) currentIndexes.add(String.valueOf(i));

            // Zmiana liter:
            setLetters();
            //-----

        }

        // widocznosc liter:
        letter.setVisible(true);
        letter1.setVisible(true);
        letter2.setVisible(true);
        letter3.setVisible(true);
        letter4.setVisible(true);
        letter5.setVisible(true);
        letter6.setVisible(true);
    }
    //-----------------------------

    // Koniec programu
    public void close(){
        Stage w = (Stage) top.getScene().getWindow();
        w.close();
    }
}

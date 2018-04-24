package Game;
import Pieces.*;

import java.util.ArrayList;

public class Player {

    private int turn, wins, statlemates = 0;
    private PieceColour pieceColour;

    public Player(PieceColour pieceColour, int turn) {
        this.pieceColour = pieceColour;
        this.turn = turn;
    }

    public void setPieceColour(PieceColour pieceColour) {
        this.pieceColour = pieceColour;
    }

    public PieceColour getPieceColour() {
        return pieceColour;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Spot getPlayerKing(Board board, PieceColour color){
        if(color == PieceColour.WHITE)
            return board.getWhite_King();

        return board.getBlack_King();
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getStatlemates() {
        return statlemates;
    }

    public void setStatlemates(int statlemates) {
        this.statlemates = statlemates;
    }
}

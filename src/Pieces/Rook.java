package Pieces;

import Game.PieceColour;
import Game.PieceTypes;


public class Rook extends Piece{

    private PieceTypes type;
    private PieceColour piececolour;
    public boolean hasMovedAlready = false;

    public Rook(PieceColour piececolour){
        super(piececolour);
        this.piececolour = piececolour;
        type = PieceTypes.ROOK;
    }


    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if(toX > 7 || toY > 7 || toX < 0 || toY < 0){ //is the next position in the board
            return false;
        }
        int x_diff = toX - fromX;
        int y_diff = toY - fromY;

        if((x_diff != 0 && y_diff == 0) || ((x_diff == 0 && y_diff != 0)))
            return true;
        return false;
    }

    @Override
    public PieceTypes getPieceTypes() { return PieceTypes.ROOK; }

    @Override
    public void setHasMovedAlready(boolean hasMovedAlready) {
        this.hasMovedAlready = hasMovedAlready;
    }

    @Override
    public boolean getHasMovedAlready() {
        return hasMovedAlready;
    }

    @Override
    public PieceColour getPieceColour() {
        return piececolour;
    }
}

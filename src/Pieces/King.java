package Pieces;
import Game.*;

import java.util.ArrayList;

public class King extends Piece{

    private PieceTypes type;
    private PieceColour piececolour;
    private boolean hasMovedAlready = false;

    public King(PieceColour piececolour){
        super(piececolour);
        this.piececolour = piececolour;
        type = PieceTypes.KING;
    }

    @Override
    public PieceTypes getPieceTypes() { return PieceTypes.KING; }

    /*
       Ensures that the move follows the piece specific rules
       fromX - initial X position of the piece in the board
       fromY - initial Y position of the piece in the board
       toX   - final X position of the piece in the board
       toY   - final Y position of the piece in the board
     */

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if(toX > 7 || toY > 7 || toX < 0 || toY < 0){ //is the next position in the board
            return false;
        }
        int x_diff = Math.abs(toX - fromX);
        int y_diff = Math.abs(toY - fromY);

        if((x_diff == 1 && y_diff <= 1) || ((x_diff <= 1 && y_diff == 1))){
            return true;
        }
        return false;
    }

    @Override
    public PieceColour getPieceColour() {
        return piececolour;
    }

    @Override
    public boolean getHasMovedAlready() {
        return hasMovedAlready;
    }

    @Override
    public void setHasMovedAlready(boolean hasMovedAlready) {
        this.hasMovedAlready = hasMovedAlready;
    }
}

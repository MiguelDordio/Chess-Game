package Pieces;
import Game.*;

public class Knight extends Piece{

    private PieceTypes type;
    private PieceColour piececolour;
    private boolean hasMovedAlready = false;

    public Knight(PieceColour piececolour){
        super(piececolour);
        this.piececolour = piececolour;
        type = PieceTypes.KNIGHT;
    }

    @Override
    public PieceTypes getPieceTypes() {
        return PieceTypes.KNIGHT;
    }

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

        int row = Math.abs(toX - fromX);
        int col = Math.abs(toY - fromY);
        return ((row == 2 && col == 1) || (row == 1 && col == 2));
    }

    @Override
    public PieceColour getPieceColour() {
        return piececolour;
    }

    @Override
    public void setHasMovedAlready(boolean hasMovedAlready) {
        this.hasMovedAlready = hasMovedAlready;
    }

    @Override
    public boolean getHasMovedAlready() {
        return hasMovedAlready;
    }
}

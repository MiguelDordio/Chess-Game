package Pieces;
import Game.*;

public class Pawn extends Piece{
    private PieceColour piececolour;
    private PieceTypes type;
    private boolean hasMovedAlready = false;

    public Pawn(PieceColour piececolour){
        super(piececolour);
        this.piececolour = piececolour;
        type = PieceTypes.PAWN;
    }

    @Override
    public PieceTypes getPieceTypes() {
        return PieceTypes.PAWN;
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        if(toX <= 7 && toY <= 7 && toX >= 0 && toY >= 0){ //is the next position in the board
            int x_diff = Math.abs(toX-fromX);
            int y_diff = Math.abs(toY-fromY);
            if(!hasMovedAlready){
                if((x_diff == 1 && y_diff == 0)){
                    if(toX < fromX && piececolour == PieceColour.WHITE){
                        return true;
                    }else if(toX > fromX && piececolour == PieceColour.BLACK){
                        return true;
                    }
                }else if(x_diff == 2 && y_diff == 0){
                    if(toX < fromX && piececolour == PieceColour.WHITE){
                        return true;
                    }else if(toX > fromX && piececolour == PieceColour.BLACK){
                        return true;
                    }
                }
            }else{
                if(x_diff == 1 && y_diff == 0){
                    if(toX < fromX && piececolour == PieceColour.WHITE){
                        return true;
                    }else if(toX > fromX && piececolour == PieceColour.BLACK){
                        return true;
                    }
                }
            }

        }
        return false;
    }

    @Override
    public boolean getHasMovedAlready() {
        return hasMovedAlready;
    }

    @Override
    public void setHasMovedAlready(boolean hasMovedAlready) {
        this.hasMovedAlready = hasMovedAlready;
    }

    @Override
    public PieceColour getPieceColour() {
        return piececolour;
    }
}

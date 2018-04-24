package Pieces;
import Game.*;

import java.util.ArrayList;

public abstract class Piece {
    private PieceColour piececolour;

    public Piece(PieceColour piececolour) {
        this.piececolour = piececolour;
    }

    public abstract boolean isValidMove(int fromX, int fromY, int toX, int toY);

    public abstract PieceTypes getPieceTypes();

    public abstract PieceColour getPieceColour();

    public abstract boolean getHasMovedAlready();

    public abstract void setHasMovedAlready(boolean hasMovedAlready);

}

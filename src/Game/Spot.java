package Game;

import Pieces.Piece;

public class Spot {
    private Piece piece;
    private int x, y;

    public Spot(int i, int j, Piece p){
        x = i;
        y = j;
        piece = p;
    }

    public boolean isOccupied(){
        if(piece != null)
            return true;
        return false;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setAvailable(){
        piece = null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}

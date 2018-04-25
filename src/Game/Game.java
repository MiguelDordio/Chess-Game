package Game;
import Pieces.*;

import java.util.*;

public class Game {

    private Piece piece;
    private ArrayList<Point> moves = new ArrayList<Point>();
    private int fromX, fromY, toX, toY;

    public int getFromX() {
        return fromX;
    }

    public void setFromX(int fromX) {
        this.fromX = fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public void setFromY(int fromY) {
        this.fromY = fromY;
    }

    public int getToX() {
        return toX;
    }

    public void setToX(int toX) {
        this.toX = toX;
    }

    public int getToY() {
        return toY;
    }

    public void setToY(int toY) {
        this.toY = toY;
    }

    public boolean isValidMove(Board board, Player player){
        Spot current = board.getSpot(getFromX(), getFromY());
        Spot newSpot = board.getSpot(getToX(), getToY());
        if(board.getSpot(getToX(), getToY()).isOccupied()){
            if(board.getSpot(getToX(), getToY()).getPiece().getPieceColour() != player.getPieceColour()){
                if(move(board, current, newSpot, player.getPlayerKing(board, player.getPieceColour()))){
                    player.setTurn(player.getTurn()+1);
                    return true;
                }
            }
        }else{
            if(!Castle(board, player.getPlayerKing(board, player.getPieceColour()), newSpot)){
                if(move(board, current, newSpot, player.getPlayerKing(board, player.getPieceColour()))){
                    player.setTurn(player.getTurn()+1);
                    return true;
                }
            }else{
                player.setTurn(player.getTurn()+1);
                return true;
            }
        }
        return false;
    }

    public boolean move(Board board, Spot current, Spot newSpot, Spot King){
        Point direction = new Point(Math.signum(newSpot.getX()-current.getX()), Math.signum(newSpot.getY()-current.getY()));
        if(!inCheck(board, King, King.getPiece().getPieceColour())){ // if the player is not in check
            if(newSpot.isOccupied()){ // Only moves if the spot is occupied by an enemy piece
                if(newSpot.getPiece().getPieceColour() != current.getPiece().getPieceColour()){
                    if(current.getPiece().getPieceTypes() == PieceTypes.KNIGHT && current.getPiece().isValidMove(current.getX(), current.getY(), newSpot.getX(), newSpot.getY())){
                        current.getPiece().setHasMovedAlready(true);
                        makeMove(board, current, newSpot);
                        return true;
                    }else if(current.getPiece().getPieceTypes() == PieceTypes.PAWN){
                        if(current.getPiece().getPieceColour() == PieceColour.WHITE &&
                                ((Math.round(direction.getX()) == -1 && Math.round(direction.getY()) == -1) ||
                                        (Math.round(direction.getX()) == -1 && Math.round(direction.getY()) == 1))){
                            current.getPiece().setHasMovedAlready(true);
                            makeMove(board, current, newSpot);
                            return true;
                        }else if(current.getPiece().getPieceColour() == PieceColour.BLACK &&
                                ((Math.round(direction.getX()) == 1 && Math.round(direction.getY()) == -1) ||
                                        (Math.round(direction.getX()) == 1 && Math.round(direction.getY()) == 1))){
                            current.getPiece().setHasMovedAlready(true);
                            makeMove(board, current, newSpot);
                            return true;
                        }else{
                            if(current.getPiece().isValidMove(current.getX(), current.getY(), newSpot.getX(), newSpot.getY())){
                                current.getPiece().setHasMovedAlready(true);
                                makeMove(board, current, newSpot);
                                return true;
                            }
                        }
                    }
                    if(isPathClear(board, current, newSpot) && current.getPiece().isValidMove(current.getX(), current.getY(), newSpot.getX(), newSpot.getY())){ //checks if the path to the target is clear
                        if(current.getPiece().getPieceTypes() == PieceTypes.KING){
                            if(current.getPiece().getPieceColour() == PieceColour.WHITE){
                                board.setWhite_King(newSpot);
                            }else{
                                board.setBlack_King(newSpot);
                            }
                        }
                        current.getPiece().setHasMovedAlready(true);
                        makeMove(board, current, newSpot);
                        return true;
                    }
                }
            }else{
                if(current.getPiece().isValidMove(current.getX(), current.getY(), newSpot.getX(), newSpot.getY())){
                    if(current.getPiece().getPieceTypes() == PieceTypes.KNIGHT){
                        current.getPiece().setHasMovedAlready(true);
                        makeMove(board, current, newSpot);
                        return true;
                    }
                    if(isPathClear(board, current, newSpot)){ //checks if the path to the target is clear
                        if(current.getPiece().getPieceTypes() == PieceTypes.KING){
                            if(current.getPiece().getPieceColour() == PieceColour.WHITE){
                                board.setWhite_King(newSpot);
                            }else{
                                board.setBlack_King(newSpot);
                            }
                        }
                        current.getPiece().setHasMovedAlready(true);
                        makeMove(board, current, newSpot);
                        return true;
                    }
                }
            }
            if(inCheck(board, King, King.getPiece().getPieceColour())){ //if the previous movement results in check undo it
                undoMove(board, newSpot, current);
            }
        }
        return false;

    }

    public void makeMove(Board board, Spot current, Spot newSpot){
        board.getSpot(newSpot.getX(), newSpot.getY()).setPiece(current.getPiece()); //Updates the target spot
        board.getSpot(current.getX(), current.getY()).setAvailable(); //frees the original spot

    }

    public void undoMove(Board board, Spot current, Spot newSpot){
        board.getSpot(current.getX(), current.getY()).setPiece(newSpot.getPiece());
        board.getSpot(newSpot.getX(), newSpot.getY()).setAvailable();
    }

    public boolean checkMate(Board board, Spot spot){
        Point[] direction = {new Point(-1, -1), new Point(-1, 1), new Point(1, -1), new Point(1, 1),
                new Point(-1, 0), new Point(0, 1), new Point(1, 0), new Point(0, -1)};
        int x = spot.getX();
        int y = spot.getY();
        boolean checkmate = false;
        // verifies if all the surrounding tiles around the king are in check, if yes it´s checkmate
        if(inCheck(board, spot, spot.getPiece().getPieceColour())){
            for(int i = 0; i < direction.length;i++){
                x+=Math.round(direction[i].getX());
                y+=Math.round(direction[i].getY());
                if((x <= 7 && x >= 0) && (y >= 0 && y <= 7)){
                    if(board.getSpot(x,y).isOccupied()){
                        if(board.getSpot(x,y).getPiece().getPieceColour() == spot.getPiece().getPieceColour())
                            checkmate = true;
                    }else if(inCheck(board, board.getSpot(x,y), spot.getPiece().getPieceColour())){
                        checkmate = true;
                    }else{
                        checkmate = false;
                    }
                }else{
                    checkmate = true;
                }
                x = spot.getX();
                y = spot.getY();
            }
        }

        return checkmate;
    }

    public boolean statlemate(){
        return false;
    }

    public boolean isPathClear(Board board,Spot current, Spot newSpot){

        int x = current.getX();
        int y = current.getY();
        //calculates the direction it needs to take (NW, N, NE, E, SE, S, SW, W)
        Point direction = new Point(Math.signum(newSpot.getX()-current.getX()), Math.signum(newSpot.getY()-current.getY()));

        while(!board.getSpot(x, y).equals(board.getSpot(newSpot.getX()-Math.round(direction.getX()), newSpot.getY()-Math.round(direction.getY())))){
            x+=Math.round(direction.getX());
            y+=Math.round(direction.getY());
            if(board.getSpot(x, y).isOccupied())
                return false;
        }
        return true;
    }

    public boolean isPromote(Spot current, Spot newSpot){
        if(current.getPiece().getPieceTypes() == PieceTypes.PAWN){
            if(newSpot.getX() == 0 || newSpot.getX() == 7){
                return true;
            }
        }
        return false;
    }

    public void makeCastleShort(Board board,Spot King, Spot newSpot){
        board.getSpot(newSpot.getX(),newSpot.getY()).setPiece(King.getPiece());
        if(King.getPiece().getPieceColour() == PieceColour.WHITE){
            board.getSpot(newSpot.getX(), newSpot.getY()-1).setPiece(board.getWhite_Rook2().getPiece());
            board.getSpot(King.getX(), King.getY()).setAvailable();
            board.getSpot(newSpot.getX(), newSpot.getY()+1).setAvailable();
            board.setWhite_King(newSpot);
        }else{
            board.getSpot(newSpot.getX(), newSpot.getY()-1).setPiece(board.getBlack_Rook2().getPiece());
            board.getSpot(King.getX(), King.getY()).setAvailable();
            board.getSpot(newSpot.getX(), newSpot.getY()+1).setAvailable();
            board.setBlack_King(newSpot);
        }
    }

    public void makeCastleLong(Board board,Spot King, Spot newSpot){
        board.getSpot(newSpot.getX(),newSpot.getY()).setPiece(King.getPiece());
        if(King.getPiece().getPieceColour() == PieceColour.WHITE){
            board.getSpot(newSpot.getX(), newSpot.getY()+1).setPiece(board.getWhite_Rook1().getPiece());
            board.getSpot(newSpot.getX(), newSpot.getY()-2).setAvailable();
            board.getSpot(King.getX(), King.getY()).setAvailable();
            board.setWhite_King(newSpot);
        }else{
            board.getSpot(newSpot.getX(), newSpot.getY()+1).setPiece(board.getWhite_Rook1().getPiece());
            board.getSpot(newSpot.getX(), newSpot.getY()-2).setAvailable();
            board.getSpot(King.getX(), King.getY()).setAvailable();
            board.setBlack_King(newSpot);
        }
    }

    public boolean Castle(Board board,Spot King, Spot newSpot){
        if(King.getPiece().getPieceColour() == PieceColour.WHITE && !King.getPiece().getHasMovedAlready()){
            if(newSpot.getX() == 7 && newSpot.getY() == 6){
                if(!board.getWhite_Rook2().getPiece().getHasMovedAlready()){
                    if(!board.getSpot(7,5).isOccupied() && !board.getSpot(7,6).isOccupied()){
                        if(!inCheck(board, board.getSpot(7,6), PieceColour.WHITE)){
                            makeCastleShort(board, King, newSpot);
                            return true;
                        }
                    }
                }
            }else if(newSpot.getX() == 7 && newSpot.getY() == 2){
                if(!board.getWhite_Rook1().getPiece().getHasMovedAlready()){
                    if(!board.getSpot(7,3).isOccupied() && !board.getSpot(7,2).isOccupied()){
                        if(!inCheck(board, board.getSpot(7,2), PieceColour.WHITE)){
                            makeCastleLong(board, King, newSpot);
                            return true;
                        }
                    }
                }
            }
        }
        if(King.getPiece().getPieceColour() == PieceColour.BLACK && !King.getPiece().getHasMovedAlready()){
            if(newSpot.getX() == 0 && newSpot.getY() == 6){
                if(!board.getBlack_Rook2().getPiece().getHasMovedAlready()){
                    if(!board.getSpot(0,5).isOccupied() && !board.getSpot(0,6).isOccupied()){
                        if(!inCheck(board, board.getSpot(0,6), PieceColour.BLACK)){
                            makeCastleShort(board, King, newSpot);
                            return true;
                        }
                    }
                }
            }else if(newSpot.getX() == 0 && newSpot.getY() == 2){
                if(!board.getBlack_Rook1().getPiece().getHasMovedAlready()){
                    if(!board.getSpot(0,3).isOccupied() && !board.getSpot(0,2).isOccupied()){
                        if(!inCheck(board, board.getSpot(0,2), PieceColour.BLACK)){
                            makeCastleLong(board, King, newSpot);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void enPassant(){

    }

    public boolean inCheck(Board board, Spot spot, PieceColour color_spot){
        Point[] direction = {new Point(-1, -1), new Point(-1, 1), new Point(1, -1), new Point(1, 1),
                new Point(-1, 0), new Point(0, 1), new Point(1, 0), new Point(0, -1)};
        Point[] Knight = {new Point(-2, -1), new Point(-2, 1), new Point(-1, -2), new Point(-1, 2),
                new Point(1, -2), new Point(1, 2), new Point(2, -1), new Point(2, 1)};
        PieceColour color;
        int x = spot.getX();
        int y = spot.getY();
        Point north = new Point(-1, 0);
        Point east = new Point(0, 1);
        Point south = new Point(1, 0);
        Point west = new Point(0, -1);

        for(int i = 0; i < direction.length;i++){
            /*
               iterates the board in a given direction until it finds an occupied spot
            */
            x+=Math.round(direction[i].getX());
            y+=Math.round(direction[i].getY());
            while((x <= 7 && x >= 0) && (y >= 0 && y <= 7)){
                if(board.getSpot(x, y).isOccupied()){
                    color = board.getSpot(x, y).getPiece().getPieceColour();
                    if(color_spot != color){
                        if(direction[i] == north || direction[i] == east || direction[i] == south || direction[i] == west){
                            if(board.getSpot(x,y).getPiece().getPieceTypes() == PieceTypes.QUEEN
                                    || board.getSpot(x,y).getPiece().getPieceTypes() == PieceTypes.ROOK)
                                return true;
                        }else{
                            if(board.getSpot(x,y).getPiece().getPieceTypes() == PieceTypes.QUEEN
                                    || board.getSpot(x,y).getPiece().getPieceTypes() == PieceTypes.BISHOP){
                                return true;
                            }else if(board.getSpot(x,y).getPiece().getPieceTypes() == PieceTypes.PAWN){
                                if(board.getSpot(x,y).getX()-Math.round(direction[i].getX()) == spot.getX() && board.getSpot(x,y).getY()-Math.round(direction[i].getY()) == spot.getY()){
                                    return true;
                                }
                            }
                        }
                    }else{
                        break;
                    }

                }
                x+=Math.round(direction[i].getX());
                y+=Math.round(direction[i].getY());
            }
            x = spot.getX();
            y = spot.getY();
        }
        for(int i = 0; i < Knight.length;i++){ //checks if there is a knight making a check
            x+=Math.round(Knight[i].getX());
            y+=Math.round(Knight[i].getY());
            if((x <= 7 && x >= 0) && (y >= 0 && y <= 7)){
                if(board.getSpot(x, y).isOccupied()){
                    color = board.getSpot(x, y).getPiece().getPieceColour();
                    if(board.getSpot(x,y).getPiece().getPieceTypes() == PieceTypes.KNIGHT && color_spot != color){
                        return true;
                    }
                }
            }
            x = spot.getX();
            y = spot.getY();
        }
        return false;
    }
}

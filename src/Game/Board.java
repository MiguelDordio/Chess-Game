package Game;
import Pieces.*;

import java.util.ArrayList;

public class Board {

    public final Spot[][] board;
    private Piece piece;
    public ArrayList movesHistory;
    private Spot White_King = new Spot(7, 4, new King(PieceColour.WHITE));
    private Spot Black_King = new Spot(0, 4, new King(PieceColour.BLACK));
    private Spot White_Rook1 = new Spot(7, 0, new Rook(PieceColour.WHITE));
    private Spot White_Rook2 = new Spot(7, 7, new Rook(PieceColour.WHITE));
    private Spot Black_Rook1 = new Spot(0, 0, new Rook(PieceColour.BLACK));
    private Spot Black_Rook2 = new Spot(0, 7, new Rook(PieceColour.BLACK));



    public Board(){
        this.board = new Spot[8][8];
    }

    public void initializeBoard(){
        board[0][0] = new Spot(0, 0, new Rook(PieceColour.BLACK));
        board[0][1] = new Spot(0, 1, new Knight(PieceColour.BLACK));
        board[0][2] = new Spot(0, 2, new Bishop(PieceColour.BLACK));
        board[0][3] = new Spot(0, 3, new Queen(PieceColour.BLACK));
        board[0][4] = new Spot(0, 4, new King(PieceColour.BLACK));
        board[0][5] = new Spot(0, 5, new Bishop(PieceColour.BLACK));
        board[0][6] = new Spot(0, 6, new Knight(PieceColour.BLACK));
        board[0][7] = new Spot(0, 7, new Rook(PieceColour.BLACK));
        for(int j = 0; j < board.length;j++){
            board[1][j] = new Spot(1, j, new Pawn(PieceColour.BLACK)); //initializes the black pawns
        }

        board[7][0] = new Spot(7, 0, new Rook(PieceColour.WHITE));
        board[7][1] = new Spot(7, 1, new Knight(PieceColour.WHITE));
        board[7][2] = new Spot(7, 2, new Bishop(PieceColour.WHITE));
        board[7][3] = new Spot(7, 3, new Queen(PieceColour.WHITE));
        board[7][4] = new Spot(7, 4, new King(PieceColour.WHITE));
        board[7][5] = new Spot(7, 5, new Bishop(PieceColour.WHITE));
        board[7][6] = new Spot(7, 6, new Knight(PieceColour.WHITE));
        board[7][7] = new Spot(7, 7, new Rook(PieceColour.WHITE));
        for(int j = 0; j < board.length;j++){
            board[6][j] = new Spot(6, j, new Pawn(PieceColour.WHITE)); //initializes the white pawns
        }

        for(int i = 2; i < 6;i++){ //initializes the rest of the board tiles to null
            for(int j = 0; j < board.length;j++){
                board[i][j] = new Spot(i, j, null);
            }
        }
    }

    public void printBoard(){
        for(int i = 0; i < board.length;i++){
            for(int j = 0; j < board.length;j++){
                if(board[i][j].isOccupied()){
                    if(board[i][j].getPiece().getPieceColour() == PieceColour.WHITE){
                        if(board[i][j].getPiece().getPieceTypes() == PieceTypes.BISHOP) {
                            System.out.print("B ");
                        }else if(board[i][j].getPiece().getPieceTypes() == PieceTypes.KING){
                            System.out.print("K ");
                        }else if(board[i][j].getPiece().getPieceTypes() == PieceTypes.KNIGHT){
                            System.out.print("C ");
                        }else if(board[i][j].getPiece().getPieceTypes() == PieceTypes.ROOK){
                            System.out.print("R ");
                        }else if(board[i][j].getPiece().getPieceTypes() == PieceTypes.QUEEN){
                            System.out.print("Q ");
                        }else if(board[i][j].getPiece().getPieceTypes() == PieceTypes.PAWN){
                            System.out.print("P ");
                        }
                    }else{
                        if(board[i][j].getPiece().getPieceTypes() == PieceTypes.BISHOP) {
                            System.out.print("b ");
                        }else if(board[i][j].getPiece().getPieceTypes() == PieceTypes.KING){
                            System.out.print("k ");
                        }else if(board[i][j].getPiece().getPieceTypes() == PieceTypes.KNIGHT){
                            System.out.print("c ");
                        }else if(board[i][j].getPiece().getPieceTypes() == PieceTypes.ROOK){
                            System.out.print("r ");
                        }else if(board[i][j].getPiece().getPieceTypes() == PieceTypes.QUEEN){
                            System.out.print("q ");
                        }else if(board[i][j].getPiece().getPieceTypes() == PieceTypes.PAWN){
                            System.out.print("p ");
                        }
                    }
                }else{
                    System.out.print(". ");
                }
            }
            System.out.println("");
        }
    }

    public Spot getWhite_King() {
        return White_King;
    }

    public void setWhite_King(Spot white_King) {
        White_King = white_King;
    }

    public Spot getBlack_King() {
        return Black_King;
    }

    public void setBlack_King(Spot black_King) {
        Black_King = black_King;
    }

    public Spot getSpot(int x, int y){
        return board[x][y];
    }

    public Spot getWhite_Rook1() {
        return White_Rook1;
    }

    public void setWhite_Rook1(Spot white_Rook1) {
        White_Rook1 = white_Rook1;
    }

    public Spot getWhite_Rook2() {
        return White_Rook2;
    }

    public void setWhite_Rook2(Spot white_Rook2) {
        White_Rook2 = white_Rook2;
    }

    public Spot getBlack_Rook1() {
        return Black_Rook1;
    }

    public void setBlack_Rook1(Spot black_Rook1) {
        Black_Rook1 = black_Rook1;
    }

    public Spot getBlack_Rook2() {
        return Black_Rook2;
    }

    public void setBlack_Rook2(Spot black_Rook2) {
        Black_Rook2 = black_Rook2;
    }
}

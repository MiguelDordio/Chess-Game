package Game;
import GUI.*;
import Pieces.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args){
        Board board = new Board();
        board.initializeBoard();
        ChessGUI gui = new ChessGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.printChess(board);
    }
}

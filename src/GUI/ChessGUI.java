package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Game.*;

public class ChessGUI extends JFrame{

    private Container contents;
    private int clicked = 0;
    private Game game = new Game();
    private Board board = new Board();
    private Player player1 = new Player(PieceColour.WHITE, 0);
    private Player player2 = new Player(PieceColour.BLACK, 0);
    private JButton[][] squares = new JButton[8][8];
    private Color darkcolor = Color.decode("#D2B48C");
    private Color lightcolor = Color.decode("#A0522D");

    private ImageIcon BKnight = new ImageIcon("C:\\Users\\migue\\IdeaProjects\\Chess Game\\src\\GUI\\Assets\\BKnight.png");
    private ImageIcon BBishop = new ImageIcon("C:\\Users\\migue\\IdeaProjects\\Chess Game\\src\\GUI\\Assets\\BBishop.png");
    private ImageIcon BKing = new ImageIcon("C:\\Users\\migue\\IdeaProjects\\Chess Game\\src\\GUI\\Assets\\BKing.png");
    private ImageIcon BQueen = new ImageIcon("C:\\Users\\migue\\IdeaProjects\\Chess Game\\src\\GUI\\Assets\\BQueen.png");
    private ImageIcon BRook = new ImageIcon("C:\\Users\\migue\\IdeaProjects\\Chess Game\\src\\GUI\\Assets\\BRook.png");
    private ImageIcon BPawn = new ImageIcon("C:\\Users\\migue\\IdeaProjects\\Chess Game\\src\\GUI\\Assets\\BPawn.png");

    private ImageIcon WKnight = new ImageIcon("C:\\Users\\migue\\IdeaProjects\\Chess Game\\src\\GUI\\Assets\\WKnight.png");
    private ImageIcon WBishop = new ImageIcon("C:\\Users\\migue\\IdeaProjects\\Chess Game\\src\\GUI\\Assets\\WBishop.png");
    private ImageIcon WKing = new ImageIcon("C:\\Users\\migue\\IdeaProjects\\Chess Game\\src\\GUI\\Assets\\Wking.png");
    private ImageIcon WQueen = new ImageIcon("C:\\Users\\migue\\IdeaProjects\\Chess Game\\src\\GUI\\Assets\\WQueen.png");
    private ImageIcon WRook = new ImageIcon("C:\\Users\\migue\\IdeaProjects\\Chess Game\\src\\GUI\\Assets\\WRook.png");
    private ImageIcon WPawn = new ImageIcon("C:\\Users\\migue\\IdeaProjects\\Chess Game\\src\\GUI\\Assets\\WPawn.png");

    public ChessGUI(){
        super("Chess");
        board.initializeBoard();
        contents = getContentPane();
        contents.setLayout(new GridLayout(8,8));

        ButtonHandler buttonHandler = new ButtonHandler();

        for(int i = 0; i< 8; i++){
            for(int j = 0;j < 8;j++){
                squares[i][j] = new JButton();
                if((i+j) % 2 != 0){
                    squares[i][j].setBackground(lightcolor);
                }else{
                    squares[i][j].setBackground(darkcolor);
                }
                contents.add(squares[i][j]);
                squares[i][j].setBorderPainted(false);
                squares[i][j].addActionListener(buttonHandler);
                squares[i][j].setSize(75, 75);

            }
        }
        setSize(600, 600);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void printChess(Board board){
        for(int i = 0; i< 8; i++){
            for(int j = 0;j < 8;j++){
                if(board.getSpot(i,j).isOccupied()){
                    if(board.getSpot(i,j).getPiece().getPieceTypes() == PieceTypes.KING && board.getSpot(i,j).getPiece().getPieceColour() == PieceColour.WHITE){
                        squares[i][j].setIcon(WKing);
                    }else if(board.getSpot(i,j).getPiece().getPieceTypes() == PieceTypes.BISHOP && board.getSpot(i,j).getPiece().getPieceColour() == PieceColour.WHITE){
                        squares[i][j].setIcon(WBishop);
                    }else if(board.getSpot(i,j).getPiece().getPieceTypes() == PieceTypes.KNIGHT && board.getSpot(i,j).getPiece().getPieceColour() == PieceColour.WHITE){
                        squares[i][j].setIcon(WKnight);
                    }else if(board.getSpot(i,j).getPiece().getPieceTypes() == PieceTypes.QUEEN && board.getSpot(i,j).getPiece().getPieceColour() == PieceColour.WHITE){
                        squares[i][j].setIcon(WQueen);
                    }else if(board.getSpot(i,j).getPiece().getPieceTypes() == PieceTypes.ROOK && board.getSpot(i,j).getPiece().getPieceColour() == PieceColour.WHITE){
                        squares[i][j].setIcon(WRook);
                    }else if(board.getSpot(i,j).getPiece().getPieceTypes() == PieceTypes.PAWN && board.getSpot(i,j).getPiece().getPieceColour() == PieceColour.WHITE){
                        squares[i][j].setIcon(WPawn);
                    }else if(board.getSpot(i,j).getPiece().getPieceTypes() == PieceTypes.KING && board.getSpot(i,j).getPiece().getPieceColour() == PieceColour.BLACK){
                        squares[i][j].setIcon(BKing);
                    }else if(board.getSpot(i,j).getPiece().getPieceTypes() == PieceTypes.BISHOP && board.getSpot(i,j).getPiece().getPieceColour() == PieceColour.BLACK){
                        squares[i][j].setIcon(BBishop);
                    }else if(board.getSpot(i,j).getPiece().getPieceTypes() == PieceTypes.KNIGHT && board.getSpot(i,j).getPiece().getPieceColour() == PieceColour.BLACK){
                        squares[i][j].setIcon(BKnight);
                    }else if(board.getSpot(i,j).getPiece().getPieceTypes() == PieceTypes.QUEEN && board.getSpot(i,j).getPiece().getPieceColour() == PieceColour.BLACK){
                        squares[i][j].setIcon(BQueen);
                    }else if(board.getSpot(i,j).getPiece().getPieceTypes() == PieceTypes.ROOK && board.getSpot(i,j).getPiece().getPieceColour() == PieceColour.BLACK){
                        squares[i][j].setIcon(BRook);
                    }else if(board.getSpot(i,j).getPiece().getPieceTypes() == PieceTypes.PAWN && board.getSpot(i,j).getPiece().getPieceColour() == PieceColour.BLACK){
                        squares[i][j].setIcon(BPawn);
                    }
                }else{
                    if(squares[i][j].getIcon() != null)
                        squares[i][j].setIcon(null);
                }
            }
        }
    }

    public JButton getSquare(int x, int y) {
        return squares[x][y];
    }

    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Object source = e.getSource();
            for(int i = 0;i< 8; i++){
                for(int j = 0;j < 8;j++){
                    if(source == squares[i][j]){
                        if(clicked == 0){
                            game.setFromX(i);
                            game.setFromY(j);
                            if(board.getSpot(i,j).isOccupied()){
                                if(player1.getTurn() == player2.getTurn()){ //if turn have the same value for both players its white´s turn
                                    if(board.getSpot(i,j).getPiece().getPieceColour() == player1.getPieceColour()){
                                        clicked++;
                                    }
                                }else if(player1.getTurn() > player2.getTurn()){
                                    if(board.getSpot(i,j).getPiece().getPieceColour() == player2.getPieceColour()){
                                        clicked++;
                                    }
                                }
                            }
                        }else if(clicked == 1){
                            game.setToX(i);
                            game.setToY(j);
                            if(player1.getTurn() == player2.getTurn()){ //if turn have the same value for both players its white´s turn
                                if(game.isValidMove(board, player1)){
                                    printChess(board);
                                    clicked = 0;
                                }
                            }else if(player1.getTurn() > player2.getTurn()){
                                if(game.isValidMove(board, player2)){
                                    printChess(board);
                                    clicked = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

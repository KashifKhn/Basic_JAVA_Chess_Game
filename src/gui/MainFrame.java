package gui;

import logic.board.LogicBoard;
import logic.board.logicBoardTilePkg.LogicTile;
import logic.pieces.Piece;
import utils.Constants;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public LogicBoard chessLogicBoard;
    LogicTile sourceLogicTile;
    LogicTile destinationLogicTile;

    Piece movedPiece;

    public MainFrame() {
        super("JChess");
        this.chessLogicBoard = LogicBoard.createStanderBoard();
        this.setSize(Constants.MAIN_FRAME_DIMENSION);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(new BoardPanel(this), BorderLayout.CENTER);
    }

}

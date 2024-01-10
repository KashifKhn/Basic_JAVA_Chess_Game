package logic.player;

import logic.board.LogicBoard;
import logic.board.logicMovePkg.LogicMove;
import logic.pieces.Piece;
import utils.PlayerColor;

import java.util.ArrayList;

public class BlackPlayer extends Player {
    public BlackPlayer(LogicBoard logicBoard, ArrayList<LogicMove> whiteStandardLegalLogicMoves, ArrayList<LogicMove> blackStandardLegalLogicMoves) {
        super(logicBoard, blackStandardLegalLogicMoves, whiteStandardLegalLogicMoves);
    }
    @Override
    public ArrayList<Piece> getActivePieces() {
        return this.logicBoard.getBlackPieces();
    }

    @Override
    public PlayerColor getAlliance() {
        return PlayerColor.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.logicBoard.getWhitePlayer();
    }

}

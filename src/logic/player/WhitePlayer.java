package logic.player;

import logic.board.LogicBoard;
import logic.board.logicMovePkg.LogicMove;
import logic.pieces.Piece;
import utils.PlayerColor;

import java.util.ArrayList;

public class WhitePlayer extends Player {


    public WhitePlayer(LogicBoard logicBoard, ArrayList<LogicMove> whiteStandardLegalLogicMoves, ArrayList<LogicMove> blackStandardLegalLogicMoves) {
        super(logicBoard, whiteStandardLegalLogicMoves, blackStandardLegalLogicMoves);
    }

    @Override
    public ArrayList<Piece> getActivePieces() {
        return this.logicBoard.getWhitePieces();
    }

    @Override
    public PlayerColor getAlliance() {
        return PlayerColor.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.logicBoard.getBlackPlayer();
    }

}

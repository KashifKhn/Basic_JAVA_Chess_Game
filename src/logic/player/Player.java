package logic.player;

import logic.board.LogicBoard;
import logic.board.logicMovePkg.LogicMove;
import logic.board.logicMovePkg.LogicMoveStatus;
import logic.board.logicMovePkg.LogicMoveTransition;
import logic.pieces.King;
import logic.pieces.Piece;
import utils.PlayerColor;

import java.util.ArrayList;

public abstract class Player {
    protected LogicBoard logicBoard;
    protected King playerKing;
    protected ArrayList<LogicMove> legalLogicMoves;
    public Player(LogicBoard logicBoard, ArrayList<LogicMove> legalLogicMoves, ArrayList<LogicMove> opponentLogicMoves) {
        this.logicBoard = logicBoard;
        this.playerKing = establishKing();
        this.legalLogicMoves =  new ArrayList<>();
        this.legalLogicMoves.addAll(legalLogicMoves);
    }

    public static ArrayList<LogicMove> calculateAttacksOnTile(int piecePosition, ArrayList<LogicMove> logicMoves) {
        ArrayList<LogicMove> attackLogicMoves = new ArrayList<>();
        for (LogicMove logicMove : logicMoves) {
            if (piecePosition == logicMove.getDestinationCoordinate()) {
                attackLogicMoves.add(logicMove);
            }
        }
        return attackLogicMoves;
    }

    private King establishKing() {
        for (final Piece piece : getActivePieces()) {
            if (piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        throw new RuntimeException("Should not reach here! Not a valid board!");
    }

    public boolean isMoveLegal(final LogicMove logicMove) {
        return this.legalLogicMoves.contains(logicMove);
    }

    public abstract ArrayList<Piece> getActivePieces();
    public abstract PlayerColor getAlliance();
    public abstract Player getOpponent();

    public LogicMoveTransition makeMove(final LogicMove logicMove) {
        if (!isMoveLegal(logicMove)) {
            return new LogicMoveTransition(this.logicBoard, logicMove, LogicMoveStatus.ILLEGAL_MOVE);
        }
        final LogicBoard transitionLogicBoard = logicMove.execute();
        System.out.println(logicMove.execute());
        final ArrayList<LogicMove> kingAttacks = Player.calculateAttacksOnTile(transitionLogicBoard.getCurrentPlayer().getOpponent().getPlayerKing().getPiecePosition(), transitionLogicBoard.getCurrentPlayer().getLegalMoves());
        if (!kingAttacks.isEmpty()) {
            return new LogicMoveTransition(this.logicBoard, logicMove, LogicMoveStatus.LEAVES_PLAYER_IN_CHECK);
        }
        return new LogicMoveTransition(transitionLogicBoard, logicMove, LogicMoveStatus.DONE);
    }

    public ArrayList<LogicMove> getLegalMoves() {
        return this.legalLogicMoves;
    }

    private Piece getPlayerKing() {
        return this.playerKing;
    }

}

package logic.board.logicMovePkg;

import logic.board.LogicBoard;
import logic.pieces.Piece;

public class LogicAttackMove extends LogicMove {
    private final Piece attackedPiece;
    public LogicAttackMove(LogicBoard logicBoard, Piece movedPiece, int destinationCoordinate, Piece attackedPiece) {
        super(logicBoard, movedPiece, destinationCoordinate);
        this.attackedPiece = attackedPiece;
    }

    public Piece getAttackedPiece() {
        return this.attackedPiece;
    }
}

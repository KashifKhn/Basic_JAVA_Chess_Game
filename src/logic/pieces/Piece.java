package logic.pieces;

import logic.board.LogicBoard;
import logic.board.logicMovePkg.LogicMove;
import utils.PlayerColor;

import java.util.Collection;

public abstract class Piece {

    protected PieceType pieceType;
    protected final int piecePosition;
    protected final PlayerColor piecePlayerColor;
    protected final boolean isFirstMove;

    Piece(int piecePosition, PlayerColor piecePlayerColor, PieceType pieceType) {
        this.piecePosition = piecePosition;
        this.piecePlayerColor = piecePlayerColor;
        this.isFirstMove = false;
        this.pieceType = pieceType;
    }


    public PieceType getPieceType() {
        return this.pieceType;
    }

    public PlayerColor getPieceAlliance() {
        return this.piecePlayerColor;
    }

    public abstract Collection<LogicMove> calculateLegalMoves(LogicBoard logicBoard);

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public int getPiecePosition() {
        return this.piecePosition;
    }


    public abstract Piece movePiece(LogicMove logicMove);

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Piece)) {
            return false;
        }
        Piece otherPiece = (Piece) other;
        return this.piecePosition == otherPiece.getPiecePosition() &&
                this.pieceType == otherPiece.getPieceType() &&
                this.piecePlayerColor == otherPiece.getPieceAlliance() &&
                this.isFirstMove == otherPiece.isFirstMove();
    }

}

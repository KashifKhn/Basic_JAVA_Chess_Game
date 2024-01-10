package logic.board.logicBoardTilePkg;

import logic.pieces.Piece;

public final class EmptyLogicTile extends LogicTile {
    EmptyLogicTile(int coordinate) {
        super(coordinate);
    }

    @Override
    public boolean isTileOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }

    @Override
    public String toString() {
        return "-";
    }
}
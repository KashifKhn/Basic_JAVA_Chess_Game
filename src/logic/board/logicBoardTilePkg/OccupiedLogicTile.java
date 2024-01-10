package logic.board.logicBoardTilePkg;

import logic.pieces.Piece;

public final class OccupiedLogicTile extends LogicTile {
    private final Piece pieceOnTile;

    OccupiedLogicTile(int tileCoordinates, Piece pieceOnTile) {
        super(tileCoordinates);
        this.pieceOnTile = pieceOnTile;
    }

    @Override
    public boolean isTileOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return this.pieceOnTile;
    }

}
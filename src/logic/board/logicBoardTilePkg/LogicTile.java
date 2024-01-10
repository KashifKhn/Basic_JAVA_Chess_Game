package logic.board.logicBoardTilePkg;

import java.util.HashMap;
import java.util.Map;

import logic.pieces.Piece;
import utils.Constants;

public abstract class LogicTile {
    protected final int tileCoordinates;
    private static final Map<Integer, EmptyLogicTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    LogicTile(int tileCoordinates) {
        this.tileCoordinates = tileCoordinates;
    }

    public int getTileCoordinate() {
        return this.tileCoordinates;
    }

    private static Map<Integer, EmptyLogicTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyLogicTile> emptyTileMap = new HashMap<>();
        for (int i = 0; i < Constants.NUM_TILES; i++) {
            emptyTileMap.put(i, new EmptyLogicTile(i));
        }
        return emptyTileMap;
    }

    public static LogicTile createTile(final int tileCoordinates, final Piece piece) {
        return piece != null ? new OccupiedLogicTile(tileCoordinates, piece) : EMPTY_TILES_CACHE.get(tileCoordinates);
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

}

package logic.pieces;

import logic.board.LogicBoard;
import logic.board.logicBoardTilePkg.LogicTile;
import logic.board.logicMovePkg.LogicMove;
import utils.HelperMethods;
import utils.PlayerColor;
import utils.Constants;

import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(int piecePosition, PlayerColor piecePlayerColor) {
        super(piecePosition, piecePlayerColor, PieceType.BISHOP);
    }

    @Override
    public ArrayList<LogicMove> calculateLegalMoves(LogicBoard logicBoard) {
        final ArrayList<LogicMove> legalLogicMoves = new ArrayList<>();

        for (final int candidateCoordinateOffset : Constants.BISHOP_LEGAL_MOVE_COORDINATE) {
            int candidateDestinationCoordinate = this.piecePosition;

            while (HelperMethods.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)) {
                    break;
                }
                if (isEighthColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)) {
                    break;
                }
                candidateDestinationCoordinate += candidateCoordinateOffset;
                if (HelperMethods.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    final LogicTile candidateDestinationLogicTile = logicBoard.getTile(candidateDestinationCoordinate);
                    if (!candidateDestinationLogicTile.isTileOccupied()) {
                        legalLogicMoves.add(new LogicMove(logicBoard, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationLogicTile.getPiece();
                        final PlayerColor piecePlayerColor = pieceAtDestination.getPieceAlliance();
                        if (this.piecePlayerColor != piecePlayerColor) {
                            legalLogicMoves.add(new LogicMove(logicBoard, this, candidateDestinationCoordinate));
                        }
                        break;
                    }
                }
            }
        }
        return legalLogicMoves;
    }

    @Override
    public Piece movePiece(LogicMove logicMove) {
        return new Bishop(logicMove.getDestinationCoordinate(), logicMove.getMovedPiece().getPieceAlliance());
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return Constants.FIRST_FILE[currentPosition] && ((candidateOffset == -9) || (candidateOffset == 7));
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return Constants.EIGHTH_FILE[currentPosition] && ((candidateOffset == -7) || (candidateOffset == 9));
    }

    @Override
    public String toString() {
        return PieceType.BISHOP.toString();
    }

}

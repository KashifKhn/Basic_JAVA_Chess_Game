package logic.pieces;

import logic.board.LogicBoard;
import logic.board.logicBoardTilePkg.LogicTile;
import logic.board.logicMovePkg.LogicMove;
import utils.HelperMethods;
import utils.PlayerColor;
import utils.Constants;

import java.util.ArrayList;
import java.util.Collection;

public class Knight extends Piece {
    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final int piecePosition, final PlayerColor piecePlayerColor) {
        super(piecePosition, piecePlayerColor, PieceType.KNIGHT);
    }
    @Override
    public Collection<LogicMove> calculateLegalMoves(LogicBoard logicBoard) {
        ArrayList<LogicMove> legalLogicMoves = new ArrayList<>();
        for (final int currentCandidate : CANDIDATE_MOVE_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition + currentCandidate;
            if (HelperMethods.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(this.piecePosition, currentCandidate)) {
                    continue;
                }
                if (isSecondColumnExclusion(this.piecePosition, currentCandidate)) {
                    continue;
                }
                if (isSeventhColumnExclusion(this.piecePosition, currentCandidate)) {
                    continue;
                }
                if (isEighthColumnExclusion(this.piecePosition, currentCandidate)) {
                    continue;
                }
                final LogicTile candidateDestinationLogicTile = logicBoard.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationLogicTile.isTileOccupied()) {
                    legalLogicMoves.add(new LogicMove(logicBoard, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtDestination = candidateDestinationLogicTile.getPiece();
                    final PlayerColor piecePlayerColor = pieceAtDestination.getPieceAlliance();
                    if (this.piecePlayerColor != piecePlayerColor) {
                        legalLogicMoves.add(new LogicMove(logicBoard, this, candidateDestinationCoordinate));
                    }
                }
            }
        }
        return legalLogicMoves;
    }

    @Override
    public Piece movePiece(LogicMove logicMove) {
        return new Knight(logicMove.getDestinationCoordinate(), logicMove.getMovedPiece().getPieceAlliance());
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return Constants.FIRST_FILE[currentPosition] && ((candidateOffset == -17) || (candidateOffset == -10) ||
                (candidateOffset == 6) || (candidateOffset == 15));
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset) {
        return Constants.SECOND_FILE[currentPosition] && ((candidateOffset == -10) || (candidateOffset == 6));
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset) {
        return Constants.SEVENTH_FILE[currentPosition] && ((candidateOffset == -6) || (candidateOffset == 10));
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return Constants.EIGHTH_FILE[currentPosition] && ((candidateOffset == -15) || (candidateOffset == -6) ||
                (candidateOffset == 10) || (candidateOffset == 17));
    }

    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }

}

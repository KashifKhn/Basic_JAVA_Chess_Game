package logic.pieces;

import logic.board.LogicBoard;
import logic.board.logicBoardTilePkg.LogicTile;
import logic.board.logicMovePkg.LogicAttackMove;
import logic.board.logicMovePkg.LogicMajorMove;
import logic.board.logicMovePkg.LogicMove;
import utils.HelperMethods;
import utils.PlayerColor;
import utils.Constants;

import java.util.ArrayList;
import java.util.Collection;

public class Rook extends Piece {
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-8, -1, 1, 8};

    public Rook(int piecePosition, PlayerColor piecePlayerColor) {
        super(piecePosition, piecePlayerColor, PieceType.ROOK);
    }

    @Override
    public Collection<LogicMove> calculateLegalMoves(LogicBoard logicBoard) {
        final ArrayList<LogicMove> legalLogicMoves = new ArrayList<>();

        for (int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
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
//                        legalLogicMoves.add(new LogicMove.MajorLogicMove(logicBoard, this, candidateDestinationCoordinate));
                        legalLogicMoves.add(new LogicMajorMove(logicBoard, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationLogicTile.getPiece();
                        final PlayerColor piecePlayerColor = pieceAtDestination.getPieceAlliance();
                        if (this.piecePlayerColor != piecePlayerColor) {
                            legalLogicMoves.add(new LogicAttackMove(logicBoard, this, candidateDestinationCoordinate, pieceAtDestination));
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
        return new Rook(logicMove.getDestinationCoordinate(), logicMove.getMovedPiece().getPieceAlliance());
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return Constants.FIRST_FILE[currentPosition] && (candidateOffset == -1);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return Constants.EIGHTH_FILE[currentPosition] && ((candidateOffset == 1));
    }

    @Override
    public String toString() {
        return PieceType.ROOK.toString();
    }
}

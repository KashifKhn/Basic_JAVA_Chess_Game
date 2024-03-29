package logic.pieces;

import logic.board.LogicBoard;
import logic.board.logicBoardTilePkg.LogicTile;
import logic.board.logicMovePkg.LogicMove;
import utils.HelperMethods;
import utils.PlayerColor;
import utils.Constants;

import java.util.ArrayList;

public class King extends Piece {
    public King(int piecePosition, PlayerColor piecePlayerColor) {
        super(piecePosition, piecePlayerColor, PieceType.KING);
    }

    @Override
    public ArrayList<LogicMove> calculateLegalMoves(LogicBoard logicBoard) {
        ArrayList<LogicMove> legalLogicMoves = new ArrayList<>();
        for (int currentCandidateOffset : Constants.KING_LEGAL_MOVE_COORDINATE) {
            int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
            if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                    isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                continue;
            }
            if (HelperMethods.isValidTileCoordinate(candidateDestinationCoordinate)) {
                LogicTile candidateDestinationLogicTile = logicBoard.getTile(candidateDestinationCoordinate);
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
        return new King(logicMove.getDestinationCoordinate(), logicMove.getMovedPiece().getPieceAlliance());
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return Constants.FIRST_FILE[currentPosition] && (candidateOffset == -9 || candidateOffset == -1 ||
                candidateOffset == 7);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return Constants.EIGHTH_FILE[currentPosition] && (candidateOffset == -7 || candidateOffset == 1 ||
                candidateOffset == 9);
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }
}

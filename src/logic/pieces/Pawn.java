package logic.pieces;

import logic.board.LogicBoard;
import logic.board.logicMovePkg.LogicMove;
import utils.HelperMethods;
import utils.PlayerColor;
import utils.Constants;

import java.util.ArrayList;
import java.util.Collection;

public class Pawn extends Piece {
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {8, 16, 7, 9};

    public Pawn(int piecePosition, PlayerColor piecePlayerColor) {
        super(piecePosition, piecePlayerColor, PieceType.PAWN);
    }

    @Override
    public Collection<LogicMove> calculateLegalMoves(LogicBoard logicBoard) {
        ArrayList<LogicMove> legalLogicMoves = new ArrayList<>();

        for (int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition + (this.piecePlayerColor.getDirection() * candidateCoordinateOffset);
            if (!HelperMethods.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }

            if (!logicBoard.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                legalLogicMoves.add(new LogicMove(logicBoard, this, candidateDestinationCoordinate));
            } else if (candidateCoordinateOffset == 16 && this.isFirstMove() &&
                    ((Constants.SECOND_RANK[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
                            (Constants.SEVENTH_RANK[this.piecePosition] && this.getPieceAlliance().isWhite()))) {
                int behindCandidateDestinationCoordinate = this.piecePosition + (this.piecePlayerColor.getDirection() * 8);
                if (!logicBoard.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                        !logicBoard.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    legalLogicMoves.add(new LogicMove(logicBoard, this, candidateDestinationCoordinate));
                }
            } else if (candidateCoordinateOffset == 7 &&
                    !((Constants.EIGHTH_FILE[this.piecePosition] && this.piecePlayerColor.isWhite() ||
                            (Constants.FIRST_FILE[this.piecePosition] && this.piecePlayerColor.isBlack())))) {
                if (logicBoard.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    Piece pieceOnCandidate = logicBoard.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.piecePlayerColor != pieceOnCandidate.getPieceAlliance()) {
                        legalLogicMoves.add(new LogicMove(logicBoard, this, candidateDestinationCoordinate));
                    }
                }
            } else if (candidateCoordinateOffset == 9 &&
                    !((Constants.FIRST_FILE[this.piecePosition] && this.piecePlayerColor.isWhite() ||
                            (Constants.EIGHTH_FILE[this.piecePosition] && this.piecePlayerColor.isBlack())))) {
                if (logicBoard.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    Piece pieceOnCandidate = logicBoard.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.piecePlayerColor != pieceOnCandidate.getPieceAlliance()) {
                        legalLogicMoves.add(new LogicMove(logicBoard, this, candidateDestinationCoordinate));
                    }
                }
            }
        }
        return legalLogicMoves;
    }

    @Override
    public Piece movePiece(LogicMove logicMove) {
        return new Pawn(logicMove.getDestinationCoordinate(), logicMove.getMovedPiece().getPieceAlliance());
    }

    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }
}

package logic.board.logicMovePkg;

import logic.board.LogicBoard;
import logic.pieces.Piece;
public abstract class LogicMove {
    final LogicBoard logicBoard;
    final Piece movedPiece;
    final int destinationCoordinate;

    public LogicMove(LogicBoard logicBoard, Piece movedPiece, int destinationCoordinate) {
        this.logicBoard = logicBoard;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }
    public int getCurrentCoordinate() {
        return this.movedPiece.getPiecePosition();
    }

    public LogicBoard execute() {
        LogicBoard.LogicBoardBuilder logicBoardBuilder = new LogicBoard.LogicBoardBuilder();
        for (Piece piece : this.logicBoard.getCurrentPlayer().getActivePieces()) {
            if (!this.movedPiece.equals(piece)) {
                logicBoardBuilder.setPiece(piece);
            }
        }

        for (Piece piece : this.logicBoard.getCurrentPlayer().getOpponent().getActivePieces()) {
            logicBoardBuilder.setPiece(piece);
        }
        logicBoardBuilder.setPiece(this.movedPiece.movePiece(this));
        logicBoardBuilder.setMoveMaker(this.logicBoard.getCurrentPlayer().getOpponent().getAlliance());
        return logicBoardBuilder.logicBoardBuild();
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }


}

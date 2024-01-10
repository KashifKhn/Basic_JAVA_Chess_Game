package logic.board.logicMovePkg;

import logic.board.LogicBoard;
import logic.pieces.Piece;

public class LogicMajorMove extends LogicMove{
    public LogicMajorMove(LogicBoard logicBoard, Piece movedPiece, int destinationCoordinate) {
        super(logicBoard, movedPiece, destinationCoordinate);
    }
}
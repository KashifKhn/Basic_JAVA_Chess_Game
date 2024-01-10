package logic.board.logicMovePkg;

import logic.board.LogicBoard;

public class LogicMoveFactory {
    public static final LogicMove NULL_LOGIC_MOVE = new LogicNullMove();

    public static LogicMove createMove(LogicBoard logicBoard, int currentCoordinate, int destinationCoordinate) {
        for (LogicMove logicMove : logicBoard.getAllLegalMoves()) {
            if (logicMove.getCurrentCoordinate() == currentCoordinate &&
                    logicMove.getDestinationCoordinate() == destinationCoordinate) {
                return logicMove;
            }
        }
        return NULL_LOGIC_MOVE;
    }
}

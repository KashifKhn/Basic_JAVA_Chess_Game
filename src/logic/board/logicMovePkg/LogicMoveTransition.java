package logic.board.logicMovePkg;
import logic.board.LogicBoard;

public class LogicMoveTransition {
    private final LogicBoard transitionLogicBoard;
    private final LogicMoveStatus logicMoveStatus;

    public LogicMoveTransition(LogicBoard transitionLogicBoard, LogicMoveStatus logicMoveStatus) {
        this.transitionLogicBoard = transitionLogicBoard;
        this.logicMoveStatus = logicMoveStatus;
    }

    public LogicBoard getTransitionBoard() {
        return this.transitionLogicBoard;
    }

    public LogicMoveStatus getMoveStatus() {
        return this.logicMoveStatus;
    }
}
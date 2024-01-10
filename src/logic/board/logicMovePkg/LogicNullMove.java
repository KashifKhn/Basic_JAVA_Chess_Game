package logic.board.logicMovePkg;

import logic.board.LogicBoard;

public class LogicNullMove extends LogicMove {
    public LogicNullMove() {
        super(null, null, -1);
    }

    @Override
    public LogicBoard execute() {
        throw new RuntimeException("cannot execute the null move!");
    }
}

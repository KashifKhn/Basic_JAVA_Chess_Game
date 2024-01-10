package logic.pieces;

public enum PieceType {

    PAWN("P") {
        @Override
        public boolean isKing() {
            return false;
        }

    },
    KNIGHT("N") {
        @Override
        public boolean isKing() {
            return false;
        }
    },
    BISHOP("B") {
        @Override
        public boolean isKing() {
            return false;
        }
    },

    ROOK("R") {
        @Override
        public boolean isKing() {
            return false;
        }
    },

    QUEEN("Q") {
        @Override
        public boolean isKing() {
            return false;
        }
    },

    KING("K") {
        @Override
        public boolean isKing() {
            return true;
        }
    };

    private final String pieceName;

    PieceType(String pieceName) {
        this.pieceName = pieceName;
    }

    @Override
    public String toString() {
        return this.pieceName;
    }

    public abstract boolean isKing();

}
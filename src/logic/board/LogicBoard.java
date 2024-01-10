package logic.board;

import logic.board.logicBoardTilePkg.LogicTile;
import logic.board.logicMovePkg.LogicMove;
import logic.pieces.*;
import logic.player.BlackPlayer;
import logic.player.Player;
import logic.player.WhitePlayer;
import utils.PlayerColor;
import utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogicBoard {

    ArrayList<LogicTile> gameBoard;
    private final ArrayList<Piece> whitePieces;
    private final ArrayList<Piece> blackPieces;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Player currentPlayer;

    private LogicBoard(LogicBoardBuilder logicBoardBuilder) {
        gameBoard = createGameBoard(logicBoardBuilder);
        whitePieces = calculateActivePieces(gameBoard, PlayerColor.WHITE);
        blackPieces = calculateActivePieces(gameBoard, PlayerColor.BLACK);

        final ArrayList<LogicMove> whiteStandardLegalLogicMoves = calculateLegalMoves(whitePieces);
        final ArrayList<LogicMove> blackStandardLegalLogicMoves = calculateLegalMoves(blackPieces);

        whitePlayer = new WhitePlayer(this, whiteStandardLegalLogicMoves, blackStandardLegalLogicMoves);
        blackPlayer = new BlackPlayer(this, whiteStandardLegalLogicMoves, blackStandardLegalLogicMoves);
        this.currentPlayer = logicBoardBuilder.nextMoveMaker.choosePlayer(this.whitePlayer, this.blackPlayer);
    }

    public Player getWhitePlayer() {
        return this.whitePlayer;
    }
    public Player getBlackPlayer() {
        return this.blackPlayer;
    }
    public ArrayList<Piece> getWhitePieces() {
        return this.whitePieces;
    }
    public ArrayList<Piece> getBlackPieces() {
        return this.blackPieces;
    }
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    public LogicTile getTile(int coordinate) {
        return gameBoard.get(coordinate);
    }

    private ArrayList<LogicMove> calculateLegalMoves(ArrayList<Piece> pieces) {
        ArrayList<LogicMove> legalLogicMoves = new ArrayList<>();
        for (Piece piece : pieces) {
            legalLogicMoves.addAll(piece.calculateLegalMoves(this));
        }
        return legalLogicMoves;
    }

    private static ArrayList<Piece> calculateActivePieces(ArrayList<LogicTile> gameBoard, PlayerColor playerColor) {
        ArrayList<Piece> activePieces = new ArrayList<>();
        for (LogicTile logicTile : gameBoard) {
            if (logicTile.isTileOccupied()) {
                Piece piece = logicTile.getPiece();
                if (piece.getPieceAlliance() == playerColor) {
                    activePieces.add(piece);
                }
            }
        }
        return activePieces;
    }

    private ArrayList<LogicTile> createGameBoard(LogicBoardBuilder logicBoardBuilder) {
        ArrayList<LogicTile> logicTiles = new ArrayList<>();
        for (int i = 0; i < Constants.NUM_TILES; i++) {
            logicTiles.add(LogicTile.createTile(i, logicBoardBuilder.logicBoardConfig.get(i)));
        }
        return logicTiles;
    }

    public ArrayList<LogicMove> getAllLegalMoves() {
        ArrayList<LogicMove> allLegalLogicMoves = new ArrayList<>();
        allLegalLogicMoves.addAll(this.whitePlayer.getLegalMoves());
        allLegalLogicMoves.addAll(this.blackPlayer.getLegalMoves());
        return allLegalLogicMoves;
    }

    public static LogicBoard createStanderBoard() {
        LogicBoardBuilder logicBoardBuilder = new LogicBoardBuilder();
        logicBoardBuilder.setPiece(new Rook(0, PlayerColor.BLACK));
        logicBoardBuilder.setPiece(new Knight(1, PlayerColor.BLACK));
        logicBoardBuilder.setPiece(new Bishop(2, PlayerColor.BLACK));
        logicBoardBuilder.setPiece(new Queen(3, PlayerColor.BLACK));
        logicBoardBuilder.setPiece(new King(4, PlayerColor.BLACK));
        logicBoardBuilder.setPiece(new Bishop(5, PlayerColor.BLACK));
        logicBoardBuilder.setPiece(new Knight(6, PlayerColor.BLACK));
        logicBoardBuilder.setPiece(new Rook(7, PlayerColor.BLACK));
        logicBoardBuilder.setPiece(new Rook(56, PlayerColor.WHITE));
        logicBoardBuilder.setPiece(new Knight(57, PlayerColor.WHITE));
        logicBoardBuilder.setPiece(new Bishop(58, PlayerColor.WHITE));
        logicBoardBuilder.setPiece(new Queen(59, PlayerColor.WHITE));
        logicBoardBuilder.setPiece(new King(60, PlayerColor.WHITE));
        logicBoardBuilder.setPiece(new Bishop(61, PlayerColor.WHITE));
        logicBoardBuilder.setPiece(new Knight(62, PlayerColor.WHITE));
        logicBoardBuilder.setPiece(new Rook(63, PlayerColor.WHITE));
        for (int i = 8; i < 16; i++) {
            logicBoardBuilder.setPiece(new Pawn(i, PlayerColor.BLACK));
            logicBoardBuilder.setPiece(new Pawn(i + 40, PlayerColor.WHITE));
        }
        logicBoardBuilder.setMoveMaker(PlayerColor.WHITE);
        return logicBoardBuilder.logicBoardBuild();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Constants.NUM_TILES; i++) {
            String tileText = this.gameBoard.get(i).toString();
            builder.append(String.format("%3s", tileText));
            if ((i + 1) % Constants.NUM_TILES_PER_ROW == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }


    public static class LogicBoardBuilder {
        Map<Integer, Piece> logicBoardConfig;
        PlayerColor nextMoveMaker;

        public LogicBoardBuilder() {
            this.logicBoardConfig = new HashMap<>();
        }

        public LogicBoardBuilder setPiece(Piece piece) {
            this.logicBoardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        public void setMoveMaker(PlayerColor nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
        }

        public LogicBoard logicBoardBuild() {

            return new LogicBoard(this);
        }
    }
}

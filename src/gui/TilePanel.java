package gui;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import logic.board.LogicBoard;
import logic.board.logicMovePkg.LogicMove;
import logic.board.logicMovePkg.LogicMoveFactory;
import logic.board.logicMovePkg.LogicMoveTransition;
import utils.Constants;

class TilePanel extends JPanel {
    private final MainFrame mainFrame;
    private final int tileId;
    private final BoardPanel boardPanel;


    TilePanel(MainFrame mainFrame, BoardPanel boardPanel, int tileId) {
        super(new GridBagLayout());
        this.mainFrame = mainFrame;
        this.tileId = tileId;
        this.boardPanel = boardPanel;
        this.setPreferredSize(Constants.TILE_PANEL_DIMENSION);
        this.assignTileColor();
        this.placePieceOnTile(this.mainFrame.chessLogicBoard);
        this.highlightLegals(this.mainFrame.chessLogicBoard);
        this.addMouseListener(new MouseClickAction() );
        validate();
    }

    private void placePieceOnTile(LogicBoard logicBoard) {
        this.removeAll();
        if (logicBoard.getTile(this.tileId).isTileOccupied()) {
            try {
                String imgPath = "src/assets/img/" + logicBoard.getTile(this.tileId).getPiece().getPieceAlliance().toString().charAt(0) + logicBoard.getTile(this.tileId).getPiece().toString() + ".png";
                final BufferedImage image = ImageIO.read(new File(imgPath));
                add(new JLabel(new ImageIcon(image.getScaledInstance(image.getWidth() / 2, image.getHeight() / 2, Image.SCALE_SMOOTH))));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void assignTileColor() {
        if (Constants.FIRST_RANK[this.tileId] || Constants.THIRD_RANK[this.tileId] || Constants.FIFTH_RANK[this.tileId] || Constants.SEVENTH_RANK[this.tileId]) {
            setBackground(this.tileId % 2 == 0 ? Constants.LIGHT_TILE_COLOR : Constants.DARK_TILE_COLOR);
        } else if (Constants.SECOND_RANK[this.tileId] || Constants.FOURTH_RANK[this.tileId] || Constants.SIXTH_RANK[this.tileId] || Constants.EIGHTH_RANK[this.tileId]) {
            setBackground(this.tileId % 2 != 0 ? Constants.LIGHT_TILE_COLOR : Constants.DARK_TILE_COLOR);
        }
    }

    private void highlightLegals(LogicBoard logicBoard) {
        for (final LogicMove logicMove : pieceLegalMoves(logicBoard)) {
            if (logicMove.getDestinationCoordinate() == this.tileId) {
                try {
                    add(new JLabel(new ImageIcon(ImageIO.read(new File("src/assets/gray_dot.png")).getScaledInstance(20, 20, Image.SCALE_SMOOTH))));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private Collection<LogicMove> pieceLegalMoves(final LogicBoard logicBoard) {
        if (this.mainFrame.movedPiece != null && this.mainFrame.movedPiece.getPieceAlliance() == logicBoard.getCurrentPlayer().getAlliance()) {
            return this.mainFrame.movedPiece.calculateLegalMoves(logicBoard);
        }
        return Collections.emptyList();
    }

    public void drawTile(LogicBoard logicBoard) {
        assignTileColor();
        placePieceOnTile(logicBoard);
        highlightLegals(logicBoard);
        validate();
        repaint();
    }

    private class MouseClickAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (isRightMouseButton(e)) {
                TilePanel.this.mainFrame.sourceLogicTile = null;
                TilePanel.this.mainFrame.destinationLogicTile = null;
                TilePanel.this.mainFrame.movedPiece = null;
            } else if (isLeftMouseButton(e)) {
                if (TilePanel.this.mainFrame.sourceLogicTile == null) {
                    TilePanel.this.mainFrame.sourceLogicTile = TilePanel.this.mainFrame.chessLogicBoard.getTile(tileId);
                    TilePanel.this.mainFrame.movedPiece = TilePanel.this.mainFrame.sourceLogicTile.getPiece();
                    if (TilePanel.this.mainFrame.movedPiece == null) {
                        TilePanel.this.mainFrame.sourceLogicTile = null;
                    }
                } else {
                    TilePanel.this.mainFrame.destinationLogicTile = TilePanel.this.mainFrame.chessLogicBoard.getTile(tileId);
                    LogicMove logicMove = LogicMoveFactory.createMove(TilePanel.this.mainFrame.chessLogicBoard, TilePanel.this.mainFrame.sourceLogicTile.getTileCoordinate(), TilePanel.this.mainFrame.destinationLogicTile.getTileCoordinate());
                    LogicMoveTransition transition = TilePanel.this.mainFrame.chessLogicBoard.getCurrentPlayer().makeMove(logicMove);
                    if (transition.getMoveStatus().isDone()) {
                        TilePanel.this.mainFrame.chessLogicBoard = transition.getTransitionBoard();
                    }
                    TilePanel.this.mainFrame.sourceLogicTile = null;
                    TilePanel.this.mainFrame.destinationLogicTile = null;
                    TilePanel.this.mainFrame.movedPiece = null;
                }
                SwingUtilities.invokeLater(() -> boardPanel.drawBoard(TilePanel.this.mainFrame.chessLogicBoard));
            }
        }
    }
}
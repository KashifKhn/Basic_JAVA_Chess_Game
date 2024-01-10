package gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import logic.board.LogicBoard;
import utils.Constants;

class BoardPanel extends JPanel {

    List<TilePanel> boardTiles;

    public BoardPanel(MainFrame mainFrame) {
        super(new GridLayout(8, 8));
        this.boardTiles = new ArrayList<>();
        for (int i = 0; i < Constants.NUM_TILES; i++) {
            TilePanel tilePanel = new TilePanel(mainFrame, this, i);
            this.boardTiles.add(tilePanel);
            add(tilePanel);
        }
        setPreferredSize(Constants.BOARD_PANEL_DIMENSION);
        validate();
    }

    public void drawBoard(LogicBoard logicBoard) {
        removeAll();
        for (TilePanel tilePanel : boardTiles) {
            tilePanel.drawTile(logicBoard);
            add(tilePanel);
        }
        validate();
        repaint();
    }
}
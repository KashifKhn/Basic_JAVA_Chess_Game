package utils;

import java.awt.*;

public class Constants {

    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_PER_ROW = 8;
    public static final boolean[] FIRST_FILE = HelperMethods.initColumn(0);
    public static final boolean[] SECOND_FILE = HelperMethods.initColumn(1);
    public static final boolean[] SEVENTH_FILE = HelperMethods.initColumn(6);
    public static final boolean[] EIGHTH_FILE = HelperMethods.initColumn(7);
    public static final boolean[] FIRST_RANK = HelperMethods.intiRow(0);
    public static final boolean[] SECOND_RANK = HelperMethods.intiRow(8);
    public static final boolean[] THIRD_RANK = HelperMethods.intiRow(16);
    public static final boolean[] FOURTH_RANK = HelperMethods.intiRow(24);
    public static final boolean[] FIFTH_RANK = HelperMethods.intiRow(32);
    public static final boolean[] SIXTH_RANK = HelperMethods.intiRow(40);
    public static final boolean[] SEVENTH_RANK = HelperMethods.intiRow(48);
    public static final boolean[] EIGHTH_RANK = HelperMethods.intiRow(56);
    //    Pieces Legal Move Cordinate
    public static final int[] BISHOP_LEGAL_MOVE_COORDINATE = {-9, -7, 7, 9};
    public static final int[] ROOK_LEGAL_MOVE_COORDINATE = { -8, -1, 1, 8 };
    public static final int[] QUEEN_LEGAL_MOVE_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};
    public static final int[] KNIGHT_LEGAL_MOVE_COORDINATE = {-17, -15, -10, -6, 6, 10, 15, 17};
    public static final int[] KING_LEGAL_MOVE_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};
    public static final int[] PAWN_LEGAL_MOVE_COORDINATE = {8, 16, 7, 9};


    //    GUI Constants
    public final static String APP_NAME = "Basic Chess Game";
    public static final Dimension MAIN_FRAME_DIMENSION = new Dimension(600, 600);
    public static final Dimension BOARD_PANEL_DIMENSION = new Dimension(450, 450);
    public static final Dimension TILE_PANEL_DIMENSION = new Dimension(10, 10);
    public static final Color LIGHT_TILE_COLOR = Color.decode("#eaebd5");
    public static final Color DARK_TILE_COLOR = Color.decode("#779556");

}

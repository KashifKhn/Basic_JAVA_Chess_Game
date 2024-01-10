package utils;

public class HelperMethods {
    public static boolean[] intiRow(int rowNumber) {
        final boolean[] row = new boolean[Constants.NUM_TILES];
        do {
            row[rowNumber] = true;
            rowNumber++;
        } while (rowNumber % Constants.NUM_TILES_PER_ROW != 0);
        return row;
    }

    public static boolean[] initColumn(int columnNumber) {
        boolean[] column = new boolean[Constants.NUM_TILES];
        do {
            column[columnNumber] = true;
            columnNumber += Constants.NUM_TILES_PER_ROW;
        } while (columnNumber < Constants.NUM_TILES);
        return column;
    }

    public static boolean isValidTileCoordinate(int candidateDestinationCoordinate) {
        return candidateDestinationCoordinate >= 0 && candidateDestinationCoordinate < Constants.NUM_TILES;
    }
}

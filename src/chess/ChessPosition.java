package chess;
/**
 *
 * @author Shaan Patel
 * @author Jay Prasad
 *
 */

/**
 * A row and column pair on the board that translates to a specific square
 */
public class ChessPosition {
    /**
     * states the row on the board that the position is in
     */
    private int row;
    /**
     * states the column on the board that the position is in
     */
    private int col;

    /**
     * constructor that takes in a row and column and defines the fields
     * @param r row
     * @param c column
     */
    public ChessPosition(int r, int c)
    {
        this.row = r;
        this.col = c;
    }

    /**
     * gets the row of the chess position
     * @return row
     */
    public int getRow()
    {
        return row;
    }

    /**
     * gets the column of the chess position
     * @return column
     */

    public int getCol()
    {
        return col;
    }

    /**
     * sets the row of the position
     * @param r row
     */
    public void setRow(int r)
    {
        this.row = r;
    }

    /**
     * sets the column of the position
     * @param c column
     */
    public void setCol(int c)
    {
        this.col = c;
    }

    /**
     * prints the row and column of the ChessPosition based on letter and number
     * @return position on board
     */
    @Override
    public String toString() {
        String s = "";
        switch (col) {
            case 0:
                s += "h";
                break;
            case 1:
                s += "g";
                break;
            case 2:
                s += "f";
                break;
            case 3:
                s += "e";
                break;
            case 4:
                s += "d";
                break;
            case 5:
                s += "c";
                break;
            case 6:
                s += "b";
                break;
            case 7:
                s += "a";
                break;
        }
        s += (row + 1);
        return s;
    }
}

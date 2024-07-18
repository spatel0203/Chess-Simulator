package chess;
/**
 *
 * @author Shaan Patel
 * @author Jay Prasad
 *
 */

/**
 * Bishop class that defines the behavior of a bishop
 */
public class Bishop implements Chesspiece{
    /**
     * defines piece name as bishop
     */
    String piecename = "chess.Bishop";
    /**
     * color field
     */
    ChessColor color;
    /**
     * Current position field
     */
    ChessPosition position;

    /**
     * Constructor that defines the color of the bishop
     *
     * @param col color of piece
     */
    public Bishop(ChessColor col)
    {
        color = col;
    }

    /**
     * Gets the position of the bishop
     *
     * @return void
     */
    @Override
    public ChessPosition getPosition() {
        return position;
    }

    /**
     * sets the position of the bishop
     *
     * @param pos position of piece
     */
    public void setPosition(ChessPosition pos) {
        position = pos;
    }

    /**
     * gets the color of the bishop
     *
     * @return void
     */
    public ChessColor getColor() {
        return color;
    }

    /**
     * gets the name of the bishop
     *
     * @return void
     */
    public String getName() {
        return piecename;
    }

    /**
     * checks to see if the piece is checking something
     */
    Boolean ischecking = false;

    /**
     * sets the checking status of the bishop
     *
     * @param val boolean that defines the checking status of the piece
     */
    public void setCheck(boolean val)
    {
        ischecking = val;
    }

    /**
     * gets the checking status of the bishop
     *
     * @return void
     */
    public boolean getCheck()
    {
        return ischecking;
    }

    /**
     * checks to see if the bishop can make the move that is requested
     *
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @return void
     */
    public boolean isValidMove(ChessPosition curPos, ChessPosition endPos) // checks if it would theoretically be possible to move to the ending position
    {
        return Math.abs(curPos.getRow() - endPos.getRow()) == Math.abs(curPos.getCol() - endPos.getCol());
    }

    /**
     * gets the path that the bishop can move on
     *
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @return void
     */
    public ChessPosition[] getPath(ChessPosition curPos, ChessPosition endPos) // Bishops can only move diagonally, so the difference betweeen columns and rows would be the same for both the beginning and ending position
    {
        ChessPosition[] path = new ChessPosition[8];
        // Checks different scenarios the chess.Bishop is moving (top Left, top Right, Bottom Left, Bottom Right)
        for (int i = 0; i < Math.abs(curPos.getRow() - endPos.getRow()); i++)
        {
            if (endPos.getRow() > curPos.getRow() && endPos.getCol() < curPos.getCol())
            {
                ChessPosition temp = new ChessPosition(curPos.getRow() + i, curPos.getCol() - i);
                path[i] = temp;
            }
            if (endPos.getRow() < curPos.getRow() && endPos.getCol() > curPos.getCol())
            {
                ChessPosition temp = new ChessPosition(curPos.getRow() - i, curPos.getCol() + i);
                path[i] = temp;
            }
            if (endPos.getRow() < curPos.getRow() && endPos.getCol() < curPos.getCol())
            {
                ChessPosition temp = new ChessPosition(curPos.getRow() - i, curPos.getCol() - i);
                path[i] = temp;
            }
            if (endPos.getRow() > curPos.getRow() && endPos.getCol() > curPos.getCol())
            {
                ChessPosition temp = new ChessPosition(curPos.getRow() + i, curPos.getCol() + i);
                path[i] = temp;
            }
        }
        return path;
    }
}
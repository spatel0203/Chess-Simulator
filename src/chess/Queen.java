package chess;
/**
 *
 * @author Shaan Patel
 * @author Jay Prasad
 *
 */

/**
 * Queen class that defines the behavior of a queen
 */
public class Queen implements Chesspiece{
    /**
     * defines the piece name
     */
    String piecename = "chess.Queen";
    /**
     * defines the color of the queen
     */
    ChessColor color;
    /**
     * defines the position of the queen
     */
    ChessPosition position;
    /**
     * defines the checking status of the queen
     */
    Boolean ischecking = false;

    /**
     * constructor that takes in the color of the queen and sets it to the queen
     * @param col color
     */
    public Queen(ChessColor col)
    {
        color = col;
    }

    /**
     * gets the position of the queen
     * @return position of piece
     */
    public ChessPosition getPosition() {
        return position;
    }

    /**
     * sets the position of the queen
     * @param pos position
     */
    public void setPosition(ChessPosition pos) {
        position = pos;
    }

    /**
     * gets the color of the queen
     * @return color
     */
    public ChessColor getColor() {
        return color;
    }

    /**
     * gets the name of the queen
     * @return name
     */
    public String getName() {
        return piecename;
    }
    /**
     * sets the checking status of the queen
     */
    public void setCheck(boolean val)
    {
        ischecking = val;
    }

    /**
     * gets the checking status of the queen
     * @return check value
     */
    public boolean getCheck()
    {
        return ischecking;
    }

    /**
     * tells whether the move of the queen is valid or not based on its movements
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @return true if valid move, else false
     */
    public boolean isValidMove(ChessPosition curPos, ChessPosition endPos) // check theoretical validity of a move
    {
        if (Math.abs(curPos.getRow() - endPos.getRow()) == Math.abs(curPos.getCol() - endPos.getCol()))
            return true;
        if (Math.abs(curPos.getRow() - endPos.getRow()) >= 1 && (Math.abs(curPos.getCol() - endPos.getCol()) == 0))
            return true;
        if (Math.abs(curPos.getRow() - endPos.getRow()) == 0 && Math.abs(curPos.getCol() - endPos.getCol()) >= 1)
            return true;
        return false;
    }

    /**
     * gets the path of the queen
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @return array of path
     */
    public ChessPosition[] getPath(ChessPosition curPos, ChessPosition endPos) // combination of checking path for a bishop and rook
    {
        ChessPosition[] path = new ChessPosition[8];
        // Path if moving up and down or left and right
        if (curPos.getRow() - endPos.getRow() == 0)
        {
            for (int i = 0; i < Math.abs(curPos.getCol() - endPos.getCol()); i++)
            {
                if (curPos.getCol() > endPos.getCol())
                {
                    ChessPosition temp = new ChessPosition(curPos.getRow(), curPos.getCol() - i);
                    path[i] = temp;
                }
                else {
                    ChessPosition temp = new ChessPosition(curPos.getRow(), curPos.getCol() + i);
                    path[i] = temp;
                }
            }
        }
        else if (curPos.getCol() - endPos.getCol() == 0)
        {
            for (int i = 0; i < Math.abs(curPos.getRow() - endPos.getRow()); i++)
            {
                if (curPos.getRow() > endPos.getRow())
                {
                    ChessPosition temp = new ChessPosition(curPos.getRow() - i, curPos.getCol());
                    path[i] = temp;
                }
                else {
                    ChessPosition temp = new ChessPosition(curPos.getRow() + i, curPos.getCol());
                    path[i] = temp;
                }
            }
        }
        // validity for a on a diagonal
        else {
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
        }
        return path;
    }
}
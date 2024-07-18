package chess;
/**
 *
 * @author Shaan Patel
 * @author Jay Prasad
 *
 */

/**
 * interface that defines certain methods and allows for the use of polymorphism in code
 */
public interface Chesspiece {
    /**
     * Gets the position of the piece on the board
     * @return position
     */
    public ChessPosition getPosition(); // returns position

    /**
     * gets the color of the specific piece
     * @return color
     */
    public ChessColor getColor(); // returns color

    /**
     * gets the name of the piece
     * @return string
     */
    public String getName(); // returns Name

    /**
     * sets the position of the piece on the board
     * @param pos position
     */
    public void setPosition(ChessPosition pos); // sets position

    /**
     * gets the checking status of the piece
     * @return check value
     */
    public boolean getCheck(); // returns if a piece is checking the chess.King

    /**
     * sets the checking status of the piece
     * @param val check value
     */
    public void setCheck(boolean val); // returns the value of a position's checking

    /**
     * checks to see if a certain move is valid based on the rules in play
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @return true if valid move, else false
     */
    public boolean isValidMove(ChessPosition curPos, ChessPosition endPos); // Checks theoretical validity of a move

    /**
     * gets the path of the specific piece for where it can move
     * @param curPos current position of piece
     * @param endPos end position of piece
     * @return array of positions in path
     */
    public ChessPosition[] getPath(ChessPosition curPos, ChessPosition endPos); // returns path between the start and ending position
}

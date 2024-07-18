package chess;

import java.util.Scanner;
/**
 * Executable chess class that runs the game mechanics
 *
 * @author Shaan Patel
 * @author Jay Prasad
 *
 */
public class Chess {
    /**
     * runs chess game
     * @param args cmd line
     */
    public static void main (String args[]) {
        Chessboard board = new Chessboard();
        Scanner sc = new Scanner(System.in);
        boolean resign = false;
        board.display();
        boolean draw = false;
        boolean d1 = false;
        boolean playing = true, legal = false;
        int player = 0; // If player is 0, it is white's turn, and if it 1 it is black's turn.
        while(playing)
        {
            while (!legal) {
                if (player == 0) {
                    System.out.print("\nWhite's move: ");
                } else {
                    System.out.print("\nBlack's move: ");
                }
                // TODO: Max of 3 inputs (for pawn promotion)
                String[] inputs = sc.nextLine().split(" ");
                if (draw) {
                    if (!inputs[0].equals("draw") || inputs.length > 1) {
                        System.out.println("You are obligated to accept the draw.");
                        continue;
                    }
                    else {
                        d1 = true;
                        break;
                    }
                }
                draw = false;
                // Hey
                String first = inputs[0];
                if (first.equals("resign") || first.equals("Resign")) {
                    if (player == 1) {
                        System.out.println("White wins");
                        resign = true;
                        break;
                    }
                    else {
                        System.out.println("Black wins");
                        resign = true;
                        break;
                    }
                }
                if (inputs.length < 2) {
                    System.out.println("Invalid Entry.");
                    continue;
                }
                String second = inputs[1];
                char promotion = ' ';
                Chesspiece c = null;
                if (inputs.length == 3) {
                    if (inputs[2].equals("draw?")) {
                        draw = true;
                    }
                    else {
                        promotion = inputs[2].charAt(0);
                    }
                }
                Chesspiece p = null;
                if (player == 0)
                    p = createPiece(promotion, ChessColor.WHITE);
                else
                    p = createPiece(promotion, ChessColor.BLACK);
//            }

//                if (first.equals("Q")) // check if there is a forfeit
//                {
//                    if (second.equals("W"))
//                    {
//                        System.out.println("White has forfeited, Black Wins!\n");
//                        playing = false;
//                        System.out.println("Thanks for playing!");
//                        return;
//                    }
//                    if (second.equals("B"))
//                    {
//                        System.out.println("Black has forfeited, White Wins!\n");
//                        playing = false;
//                        System.out.println("Thanks for playing!");
//                        return;
//                    }
//                    else {
//                        System.out.println("Invalid entry.");
//                        continue;
//                    }
//                }
                // parse input into the correct chess position and filter out illegal moves
                ChessPosition curPos = checkValidity(first);
                if (curPos == null) {
                    System.out.println("Invalid entry.");
                    continue;
                }
                ChessPosition endPos = checkValidity(second);
                if (endPos == null) {
                    System.out.println("Invalid entry.");
                    continue;
                }
//                int curPoscol = 0, endPoscol = 0;
//                if (first.charAt(0) == 'H')
//                    curPoscol = 0;
//                else if (first.charAt(0) == 'G')
//                    curPoscol = 1;
//                else if (first.charAt(0) == 'F')
//                    curPoscol = 2;
//                else if (first.charAt(0) == 'E')
//                    curPoscol = 3;
//                else if (first.charAt(0) == 'D')
//                    curPoscol = 4;
//                else if (first.charAt(0) == 'C')
//                    curPoscol = 5;
//                else if (first.charAt(0) == 'B')
//                    curPoscol = 6;
//                else if (first.charAt(0) == 'A')
//                    curPoscol = 7;
//                else {
//                    System.out.println("Move is illegal. Enter a new move");
//                    continue;
//                }
//                if (first.length() != 2)
//                {
//                    System.out.println("Move is illegal. Enter a new move.");
//                    continue;
//                }
//                int curPosrow = Character.getNumericValue(first.charAt(1)) - 1;
//                if (second.charAt(0) == 'H')
//                    endPoscol = 0;
//                else if (second.charAt(0) == 'G')
//                    endPoscol = 1;
//                else if (second.charAt(0) == 'F')
//                    endPoscol = 2;
//                else if (second.charAt(0) == 'E')
//                    endPoscol = 3;
//                else if (second.charAt(0) == 'D')
//                    endPoscol = 4;
//                else if (second.charAt(0) == 'C')
//                    endPoscol = 5;
//                else if (second.charAt(0) == 'B')
//                    endPoscol = 6;
//                else if (second.charAt(0) == 'A')
//                    endPoscol = 7;
//                else {
//                    System.out.println("Move is illegal. Enter a new move");
//                    continue;
//                }
//                if (second.length() > 2 || second.length() < 2)
//                {
//                    System.out.println("Move is illegal. Enter a new move.");
//                    continue;
//                }
//                int endPosrow = Character.getNumericValue(second.charAt(1)) - 1;
//                if (curPosrow < 0 || curPosrow > 7 || curPoscol < 0 || curPoscol > 7 || endPosrow < 0 || endPoscol > 7 || endPosrow < 0 || endPosrow > 7)
//                {
//                    System.out.println("Move is illegal. Enter a new move.");
//                    continue;
//                }
                if (board.gameboard[curPos.getRow()][curPos.getCol()] == null)
                {
                    System.out.println("Illegal Move, try again.");
                    continue;
                }
                else {
                    if (board.gameboard[curPos.getRow()][curPos.getCol()].getColor() == ChessColor.WHITE && player == 1)
                    {
                        System.out.println("It is black's turn. Please have black make a move.");
                        continue;
                    }
                    if (board.gameboard[curPos.getRow()][curPos.getCol()].getColor() == ChessColor.BLACK && player == 0)
                    {
                        System.out.println("It is white's turn. Please have white make a move.");
                        continue;
                    }
                }
                legal = board.movePiece(curPos, endPos, p); // call chessboard class
                if (board.getPawnPromote()) {
                    board.setPawnPromote(false);
                }
                if (!legal)
                {
                    System.out.println("Illegal Move, try again.");
                    continue;
                }
                System.out.print("\n");
            }
            if (player == 0) {
                for (int i = 0; i < board.blackpieces.length; i++) {
                    board.blackpieces[i].setCheck(false);
                }
            }
            if (player == 1) {
                for (int i = 0; i < board.whitepieces.length; i++) {
                    board.whitepieces[i].setCheck(false);
                }
            }
            if (d1)
                break;
            if (resign)
                break;
            board.display();
            if (board.whitecheckmate || board.blackcheckmate) {
                break;
            }
            if (player == 0) {
//                System.out.println("It is now Black's turn: ");
                player = 1;
            }
            else {
//                System.out.println("It is now White's turn: ");
                player = 0;
            }
            legal = false;
        }
        sc.close();
    }

    /**
     * checks validity of position input
     * @param input input
     * @return position
     */
    public static ChessPosition checkValidity(String input) {
        if (input.length() != 2)
            return null;
        int col = 0, row = 0;
        if (input.charAt(0) == 'h')
            col = 0;
        else if (input.charAt(0) == 'g')
            col = 1;
        else if (input.charAt(0) == 'f')
            col = 2;
        else if (input.charAt(0) == 'e')
            col = 3;
        else if (input.charAt(0) == 'd')
            col = 4;
        else if (input.charAt(0) == 'c')
            col = 5;
        else if (input.charAt(0) == 'b')
            col = 6;
        else if (input.charAt(0) == 'a')
            col = 7;
        else
            return null;
        try {
            row = Character.getNumericValue(input.charAt(1)) - 1;
        } catch (Exception e) {
            return null;
        }
        if (row > 7 || row < 0 || col > 7 || col < 0)
            return null;
        ChessPosition res = new ChessPosition(row, col);
        return res;
    }

    /**
     * creates a piece
     * @param c piece type
     * @param color color
     * @return piece
     */
    private static Chesspiece createPiece(char c, ChessColor color) {
        Chesspiece p = null;
        switch (c) {
            case 'N':
                p = new Knight(color);
                break;
            case 'B':
                p = new Bishop(color);
                break;
            case 'R':
                p = new Rook(color);
                break;
            default:
                p = new Queen(color);
                break;
        }
        return p;
    }
}

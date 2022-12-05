public class Board {
    public char[][] board;
    public Board(){
        board = new char[9][9];
    }
    public Board(Board board_) {
        board = new char[9][9];
        for (int i = 1; i < 9; ++i) {
            for (int j = 1; j < 9; ++j) {
                board[i][j] = board_.board[i][j];
            }
        }
    }
}

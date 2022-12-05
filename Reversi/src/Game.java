import java.util.function.BiPredicate;

public class Game {
    public int firstPlayerScore_, secondPlayerScore_;
    private final int gameMode_; // 1 - против лёгкого; 2 - против сложного; 3 - друг с другом
    public int gameStatus_; // 0 - игра началась; 1 - игра окончена
    private int moveCount_; // счётчик ходов
    private Board board = new Board();
    private final Board[] board_history = new Board[65];
    public Game(int gameMode) {
        for (int i = 1; i <= 8; ++i) {
            for (int j = 1; j <= 8; ++j) {
                board.board[i][j] = '-';
            }
        }
        board.board[4][4] = 'X';
        board.board[5][5] = 'X';
        board.board[4][5] = '0';
        board.board[5][4] = '0';
        firstPlayerScore_ = 2;
        secondPlayerScore_ = 2;
        gameMode_ = gameMode;
        moveCount_ = 0;
        board_history[moveCount_] = new Board(board);
    }
    public void printBoard(int player) {
        System.out.print("\n\n  ");
        for (int i = 1; i <= 8; ++i) {
            System.out.print(i + " ");
        }
        System.out.print('\n');
        for (int i = 1; i <= 8; ++i) {
            System.out.print(i + " ");
            for (int j = 1; j <= 8; ++j) {
                if (checkMove(i, j, player)) {
                    System.out.print("* ");
                } else {
                    System.out.print(board.board[i][j] + " ");
                }
            }
            System.out.print('\n');
        }
        System.out.print('\n');
        printScore();
    }
    public void printScore() {
        if (gameMode_ == 1 || gameMode_ == 2) {
            System.out.println("Счёт игрока (X): " + firstPlayerScore_);
            System.out.println("Счёт ИИ (0): " + secondPlayerScore_);
        } else {
            System.out.println("Счёт первого игрока (X): " + firstPlayerScore_);
            System.out.println("Счёт второго игрока (0): " + secondPlayerScore_);
        }
    }
    public boolean makeMove(int i, int j, int player)  {
        if (!checkMove(i, j, player)) {
            System.out.println("Такой ход невозможен =(");
            return false;
        }
        char player_char, enemy_char;
        if (player == 1) {
            player_char = 'X';
            enemy_char = '0';
            ++firstPlayerScore_;
        } else {
            player_char = '0';
            enemy_char = 'X';
            ++secondPlayerScore_;
        }

        board.board[i][j] = player_char;
        int add_points = 0;

        int q = 1;
        while (i + q < 9) {
            if (board.board[i + q][j] == '-') {
                break;
            }
            ++q;
        }
        --q;
        boolean has_same = false;
        while (q != 0) {
            if (board.board[i + q][j] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    board.board[i + q][j] = player_char;
                    ++add_points;
                }
            }
            --q;
        }

        q = 1;
        while (i - q > 0) {
            if (board.board[i - q][j] == '-') {
                break;
            }
            ++q;
        }
        --q;
        has_same = false;
        while (q != 0) {
            if (board.board[i - q][j] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    board.board[i - q][j] = player_char;
                    ++add_points;
                }
            }
            --q;
        }

        q = 1;
        while (j - q > 0) {
            if (board.board[i][j - q] == '-') {
                break;
            }
            ++q;
        }
        --q;
        has_same = false;
        while (q != 0) {
            if (board.board[i][j - q] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    board.board[i][j - q] = player_char;
                    ++add_points;
                }
            }
            --q;
        }

        q = 1;
        while (j + q < 9) {
            if (board.board[i][j + q] == '-') {
                break;
            }
            ++q;
        }
        --q;
        has_same = false;
        while (q != 0) {
            if (board.board[i][j + q] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    board.board[i][j + q] = player_char;
                    ++add_points;
                }
            }
            --q;
        }

        q = 1;
        while (i + q < 9 && j + q < 9) {
            if (board.board[i + q][j + q] == '-') {
                break;
            }
            ++q;
        }
        --q;
        has_same = false;
        while (q != 0) {
            if (board.board[i + q][j + q] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    board.board[i + q][j + q] = player_char;
                    ++add_points;
                }
            }
            --q;
        }

        q = 1;
        while (i - q > 0 && j - q > 0) {
            if (board.board[i - q][j - q] == '-') {
                break;
            }
            ++q;
        }
        --q;
        has_same = false;
        while (q != 0) {
            if (board.board[i - q][j - q] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    board.board[i - q][j - q] = player_char;
                    ++add_points;
                }
            }
            --q;
        }

        q = 1;
        while (i + q < 9 && j - q > 0) {
            if (board.board[i + q][j - q] == '-') {
                break;
            }
            ++q;
        }
        --q;
        has_same = false;
        while (q != 0) {
            if (board.board[i + q][j - q] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    board.board[i + q][j - q] = player_char;
                    ++add_points;
                }
            }
            --q;
        }

        q = 1;
        while (i - q > 0 && j + q < 9) {
            if (board.board[i - q][j + q] == '-') {
                break;
            }
            ++q;
        }
        --q;
        has_same = false;
        while (q != 0) {
            if (board.board[i - q][j + q] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    board.board[i - q][j + q] = player_char;
                    ++add_points;
                }
            }
            --q;
        }

        ++moveCount_;
        board_history[moveCount_] = new Board(board);

        if (player == 1) {
            firstPlayerScore_ += add_points;
            secondPlayerScore_ -= add_points;
        } else {
            secondPlayerScore_ += add_points;
            firstPlayerScore_ -= add_points;
        }
        if (!checkCanMakeMove(1) && !checkCanMakeMove(2)) {
            gameStatus_ = 1;
        }
        return true;
    }
    public boolean checkCanMakeMove(int player) {
        for (int i = 1; i < 9; ++i) {
            for (int j = 1; j < 9; ++j) {
                if (checkMove(i, j, player)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkMove(int i, int j, int player) {
        if (board.board[i][j] != '-') {
            return false;
        }
        char player_char, enemy_char;
        if (player == 1) {
            player_char = 'X';
            enemy_char = '0';
        } else {
            player_char = '0';
            enemy_char = 'X';
        }
        if (i - 1 > 0) {
            if(board.board[i - 1][j] == enemy_char) {
                for (int ii = i - 2; ii > 0; --ii) {
                    if (board.board[ii][j] == '-') {
                        break;
                    } else if (board.board[ii][j] == player_char) {
                        return true;
                    }
                }
            }
        }
        if (i + 1 < 9) {
            if(board.board[i + 1][j] == enemy_char) {
                for (int ii = i + 2; ii < 9; ++ii) {
                    if (board.board[ii][j] == '-') {
                        break;
                    } else if (board.board[ii][j] == player_char) {
                        return true;
                    }
                }
            }
        }
        if (j + 1 < 9) {
            if(board.board[i][j + 1] == enemy_char) {
                for (int jj = j + 2; jj < 9; ++jj) {
                    if (board.board[i][jj] == '-') {
                        break;
                    } else if (board.board[i][jj] == player_char) {
                        return true;
                    }
                }
            }
        }
        if (j - 1 > 0) {
            if(board.board[i][j - 1] == enemy_char) {
                for (int jj = j - 2; jj > 0; --jj) {
                    if (board.board[i][jj] == '-') {
                        break;
                    } else if (board.board[i][jj] == player_char) {
                        return true;
                    }
                }
            }
        }
        if (i - 1 > 0 && j - 1 > 0) {
            if(board.board[i - 1][j - 1] == enemy_char) {
                int q = 2;
                while (i - q > 0 && j - q > 0) {
                    if (board.board[i - q][j - q] == '-') {
                        break;
                    } else if (board.board[i - q][j - q] == player_char) {
                        return true;
                    }
                    ++q;
                }
            }
        }
        if (i + 1 < 9 && j + 1 < 9) {
            if(board.board[i + 1][j + 1] == enemy_char) {
                int q = 2;
                while (i + q < 9 && j + q < 9) {
                    if (board.board[i + q][j + q] == '-') {
                        break;
                    } else if (board.board[i + q][j + q] == player_char) {
                        return true;
                    }
                    ++q;
                }
            }
        }
        if (i - 1 > 0 && j + 1 < 9) {
            if(board.board[i - 1][j + 1] == enemy_char) {
                int q = 2;
                while (i - q > 0 && j + q < 9) {
                    if (board.board[i - q][j + q] == '-') {
                        break;
                    } else if (board.board[i - q][j + q] == player_char) {
                        return true;
                    }
                    ++q;
                }
            }
        }
        if (i + 1 < 9 && j - 1 > 0) {
            if(board.board[i + 1][j - 1] == enemy_char) {
                int q = 2;
                while (i + q < 9 && j - q > 0) {
                    if (board.board[i + q][j - q] == '-') {
                        break;
                    } else if (board.board[i + q][j - q] == player_char) {
                        return true;
                    }
                    ++q;
                }
            }
        }
        return false;
    }
    private int rateMove(int i, int j, int player) {
        char player_char;
        if (player == 1) {
            player_char = 'X';
        } else {
            player_char = '0';
        }
        int cur_R = 0;
        if ((i == 1 && j == 1) || (i == 8 && j == 8)
                || (i == 1 && j == 8) || (i == 8 && j == 1)) {
            cur_R += 8;
        } else if (i == 1 || i == 8 || j == 1 || j == 8) {
            cur_R += 4;
        }

        int q = 1;
        while (i + q < 9) {
            if (board.board[i + q][j] == '-') {
                break;
            }
            ++q;
        }
        --q;
        boolean has_same = false;
        while (q != 0) {
            if (board.board[i + q][j] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    if (i + q == 1 || i + q == 8 || j == 1 || j == 8) {
                        cur_R += 20;
                    } else {
                        cur_R += 10;
                    }
                }
            }
            --q;
        }

        q = 1;
        while (i - q > 0) {
            if (board.board[i - q][j] == '-') {
                break;
            }
            ++q;
        }
        --q;
        has_same = false;
        while (q != 0) {
            if (board.board[i - q][j] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    if (i - q == 1 || i - q == 8 || j == 1 || j == 8) {
                        cur_R += 20;
                    } else {
                        cur_R += 10;
                    }
                }
            }
            --q;
        }

        q = 1;
        while (j - q > 0) {
            if (board.board[i][j - q] == '-') {
                break;
            }
            ++q;
        }
        --q;
        has_same = false;
        while (q != 0) {
            if (board.board[i][j - q] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    if (i == 1 || i == 8 || j - q == 1 || j - q == 8) {
                        cur_R += 20;
                    } else {
                        cur_R += 10;
                    }
                }
            }
            --q;
        }

        q = 1;
        while (j + q < 9) {
            if (board.board[i][j + q] == '-') {
                break;
            }
            ++q;
        }
        --q;
        has_same = false;
        while (q != 0) {
            if (board.board[i][j + q] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    if (i == 1 || i == 8 || j + q == 1 || j + q == 8) {
                        cur_R += 20;
                    } else {
                        cur_R += 10;
                    }
                }
            }
            --q;
        }

        q = 1;
        while (i + q < 9 && j + q < 9) {
            if (board.board[i + q][j + q] == '-') {
                break;
            }
            ++q;
        }
        --q;
        has_same = false;
        while (q != 0) {
            if (board.board[i + q][j + q] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    if (i + q == 1 || i + q == 8 || j + q == 1 || j + q == 8) {
                        cur_R += 20;
                    } else {
                        cur_R += 10;
                    }
                }
            }
            --q;
        }

        q = 1;
        while (i - q > 0 && j - q > 0) {
            if (board.board[i - q][j - q] == '-') {
                break;
            }
            ++q;
        }
        --q;
        has_same = false;
        while (q != 0) {
            if (board.board[i - q][j - q] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    if (i - q == 1 || i - q == 8 || j - q == 1 || j - q == 8) {
                        cur_R += 20;
                    } else {
                        cur_R += 10;
                    }
                }
            }
            --q;
        }

        q = 1;
        while (i + q < 9 && j - q > 0) {
            if (board.board[i + q][j - q] == '-') {
                break;
            }
            ++q;
        }
        --q;
        has_same = false;
        while (q != 0) {
            if (board.board[i + q][j - q] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    if (i + q == 1 || i + q == 8 || j - q == 1 || j - q == 8) {
                        cur_R += 20;
                    } else {
                        cur_R += 10;
                    }
                }
            }
            --q;
        }

        q = 1;
        while (i - q > 0 && j + q < 9) {
            if (board.board[i - q][j + q] == '-') {
                break;
            }
            ++q;
        }
        --q;
        has_same = false;
        while (q != 0) {
            if (board.board[i - q][j + q] == player_char) {
                has_same = true;
            } else {
                if (has_same) {
                    if (i - q == 1 || i - q == 8 || j + q == 1 || j + q == 8) {
                        cur_R += 20;
                    } else {
                        cur_R += 10;
                    }
                }
            }
            --q;
        }
        return cur_R;
    }
    public void easyBotMove(int player) {
        if (!checkCanMakeMove(player)) {
            return;
        }
        int res = 0;
        int res_i = 1;
        int res_j = 1;
        char player_char;
        if (player == 1) {
            player_char = 'X';
        } else {
            player_char = '0';
        }
        for (int i = 1; i < 9; ++i) {
            for (int j = 1; j < 9; ++j) {
                if (checkMove(i, j, player)) {
                    int cur_R = rateMove(i, j, player);
                    if (cur_R > res) {
                        res = cur_R;
                        res_i = i;
                        res_j = j;
                    }
                }
            }
        }
        makeMove(res_i, res_j, player);
    }
    private int rateMoveWithDeep(int i, int j, int player, int deep) {
        if (deep == 0) {
            return 0;
        }
        char player_char;
        if (player == 1) {
            player_char = 'X';
        } else {
            player_char = '0';
        }
        int max_rate = -100;
        for (int ii = 1; ii < 9; ++ii) {
            for (int jj = 1; jj < 9; ++jj) {
                if (checkMove(ii, jj, player)) {
                    board.board[i][j] = player_char;
                    int rate_this = rateMove(i, j, player) -
                            rateMoveWithDeep(ii, jj, (player - 2) * -1 + 1, deep - 1);
                    if (rate_this > max_rate) {
                        max_rate = rate_this;
                    }
                    board.board[i][j] = '-';
                }
            }
        }
        return max_rate;
    }
    public void hardBotMove(int player) {
        if (!checkCanMakeMove(player)) {
            return;
        }
        int res = -100;
        int res_i = 1;
        int res_j = 1;
        char player_char;
        if (player == 1) {
            player_char = 'X';
        } else {
            player_char = '0';
        }
        for (int i = 1; i < 9; ++i) {
            for (int j = 1; j < 9; ++j) {
                if (checkMove(i, j, player)) {
                    int cur_R = rateMoveWithDeep(i, j, player, 4);
                    if (cur_R > res) {
                        res = cur_R;
                        res_i = i;
                        res_j = j;
                    }
                }
            }
        }
        makeMove(res_i, res_j, player);
    }
    public void makeBackMove() {
        if (moveCount_ < 2) {
            System.out.println("Больше вернуть ход нельзя!\n\n");
            return;
        }
        moveCount_ -= 2;
        board = new Board(board_history[moveCount_]);
        firstPlayerScore_ = 0;
        secondPlayerScore_ = 0;
        for (int i = 1; i < 9; ++i) {
            for (int j = 1; j < 9; ++j) {
                if (board.board[i][j] == 'X') {
                    firstPlayerScore_++;
                } else if (board.board[i][j] == '0') {
                    secondPlayerScore_++;
                }
            }
        }
    }
}
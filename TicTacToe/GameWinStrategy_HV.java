package cn.edu.nju.TicTacToe;

/**
 * 横竖方式获胜对应的类
 *
 * @author Xin Feng & Qiu Liu
 */
public class GameWinStrategy_HV implements GameWinStrategy {
    /**
     * 自行实现检测获胜的方法
     *
     * @param cells 棋盘对应的char二维数组
     * @return 检测结果
     */
    public Result check(char[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if (test('X', i, j, cells))
                    return Result.X_WIN;
                else if (test('O', i, j, cells))
                    return Result.O_WIN;
            }
        }

        for (int i = 0; i < cells.length; ++i) {
            for (int j = 0; j < cells.length; ++j) {
                if (cells[i][j] == '_')
                    return Result.GAMING;
            }
        }

        return Result.DRAW;
    }

    protected boolean test(char role, int x, int y, char[][] board) {
        //cols
        for (int i = 0; i < 3; i++) {
            boolean win = true;
            for (int j = 0; j < 3; j++) {
                if (!isValid(x + i, y + j, board) || board[x + i][y + j] != role) win = false;
            }
            if (win) return true;
        }

        // rows
        for (int i = 0; i < 3; i++) {
            boolean win = true;
            for (int j = 0; j < 3; j++) {
                if (!isValid(x + j, y + i, board) || board[x + j][y + i] != role) win = false;
            }
            if (win) return true;
        }

        return false;
    }

    protected boolean isValid(int x, int y, char[][] board) {
        return (x >= 0) && (y >= 0) && (x < board.length) && (y < board.length);
    }
}
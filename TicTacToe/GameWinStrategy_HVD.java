package cn.edu.nju.TicTacToe;

/**
 * 横竖斜方式获胜对应的类，应该考虑到可扩展性，当有新的获胜模式出现时更易于添加
 * hint：采用接口的方式，接口与实现相分离
 *
 * @author Xin Feng & Qiu Liu
 */
public class GameWinStrategy_HVD extends GameWinStrategy_HV {
    /**
     * 根据需要修改获胜的方法
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

        for (int i = 0; i < cells.length; ++i) {
            for (int j = 0; j < cells.length; ++j) {
                if (cells[i][j] == 'E')
                    return Result.ERROR;
            }
        }

        return Result.DRAW;
    }

    protected boolean test(char role, int x, int y, char[][] board) {
        if (super.test(role, x, y, board)) return true;

        boolean win = true;
        for (int i = 0; i < 3; i++) {
            if (!isValid(x + i, y + i, board) || board[x + i][y + i] != role) win = false;
        }
        if (win) return true;

         win = true;
        for (int i = 0; i < 3; i++) {
            if (!isValid(x + i, y - i, board) || board[x + i][y - i] != role) win = false;
        }
        if (win) return true;

        return false;
    }

}
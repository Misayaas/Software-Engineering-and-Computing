public class Game {
    
    //游戏主方法playGame
    //输入为对弈双方轮番落子的位置，以英文逗号为间隔，X先走
    public Result playGame(String s)
    {
		String[] steps = s.split(",");
        int [][] board = new int [3][3];

        Boolean Is_X = true;
        for(String step:steps)
        {
            board[step.charAt(0) - 'A'][step.charAt(1) - '1'] = Is_X ? 1 : 2;
            printBoard(board);
            Is_X = !Is_X;
        }

        if (test(1, board))
        {
            return Result.X_WIN;
        }
        if (test(2, board))
        {
            return Result.O_WIN;
        }
        if (steps.length == 9)
        {
            return Result.DRAW;
        }

        return Result.GAMING;
    }

    private void printBoard(int[][] board)
    {
        System.out.println("  A B C");
        for (int i = 1; i <= 3; i++)
        {
            System.out.printf("%d", i);
            for (int j = 0; j < 3; j++)
            {
                if (board[j][i - 1] == 0)
                    System.out.print(" _");
                else if (board[j][i - 1] == 1)
                    System.out.print(" X");
                else
                    System.out.print(" O");
            }
            System.out.println();
        }
    }

    private boolean test(int role, int[][] board)
    {
        //cols
        for (int i = 0; i < 3; i++)
        {
            boolean win = true;
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] != role)
                {
                    win = false;
                }
            }
            if (win)
            {
                return true;
            }
        }

        // rows
        for (int i = 0; i < 3; i++)
        {
            boolean win = true;
            for (int j = 0; j < 3; j++)
            {
                if (board[j][i] != role)
                {
                    win = false;
                }
            }
            if (win)
            {
                return true;
            }
        }

        // diagonal
        if (board[0][0] == role && board[1][1] == role && board[2][2] == role)
        {
            return true;
        }
        if (board[2][0] == role && board[1][1] == role && board[0][2] == role)
        {
            return true;
        }

        return false;
    }
    public static void main(String[] args)
    {
        Game game = new Game();
        Result result = game.playGame("B2,C2,C1,A3,B3,B1,A2,B2,C3,A1,A3,B3,C2,B1,B2,A2,A1,C1,C3");
        System.out.println(result);
    }
}

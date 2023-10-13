package cn.edu.nju.TicTacToe;

import java.util.LinkedList;
import java.util.Queue;

public class GameChessStrategyFive extends GameChessStrategy {
    LinkedList<String> positions_X = new LinkedList<String>();
    LinkedList<String> positions_O = new LinkedList<String>();

    public void putChess(char[][] cells, Player currentPlayer, String chessPos) {

        int i = chessPos.charAt(1) - '1';
        int j = chessPos.charAt(0) - 'A';
        cells[i][j] = currentPlayer == Player.X ? 'X' : 'O';

        if (cells[i][j] == 'X')
            recordPos(chessPos, positions_X, cells);
        if (cells[i][j] == 'O')
            recordPos(chessPos, positions_O, cells);
    }

    void recordPos(String chessPos, LinkedList<String> positions, char[][] cells) {
        if (positions.size() == 5) {
            String pos = positions.remove();
            int a = pos.charAt(1) - '1';
            int b = pos.charAt(0) - 'A';
            cells[a][b] = '_';
        }

        positions.add(chessPos);
    }
}

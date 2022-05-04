import java.util.Arrays;

public class GameBoard {

    public int[][] _boards;
    public int _board_size;

    public GameBoard(int N) {
        int total = ((N*N) + N)/2;
        int board[] = new int[total];
        Arrays.fill(board, 1);
        int [][] boards = new int[total][];
        for(int i=1;i<=total;i++) {
            int temp[];
            temp = board.clone();
            temp[i - 1] = 0;
            boards[i - 1] = temp;
        }
        this._board_size = total;
        this._boards = boards;
    }
}



